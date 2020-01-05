package cn.zy.userprofile.tag.task

import java.util.concurrent.{LinkedBlockingQueue, ThreadPoolExecutor, TimeUnit}

import cn.zy.userprofile.common.utils.date.{DateTimeFormat, DateUtils}
import cn.zy.userprofile.spark.utils.SparkSQLUtils
import cn.zy.userprofile.spark.utils.SparkSQLUtils._
import cn.zy.userprofile.tag.thread.TagCalcThread
import cn.zy.userprofile.tag.utils.{JdbcUtils, TagUtils}
import com.google.common.base.Strings
import com.google.common.util.concurrent.ThreadFactoryBuilder
import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, Row, SparkSession}
import org.slf4j.{Logger, LoggerFactory}

/**
 * author: hufenggang
 * email: hufenggang2019@gmail.com
 * date: 2019/12/31 13:45
 *
 * 用户属性标签计算
 * 标签计算的sql逻辑存储在mysql元数据表，spark程序从元数据表获取计算逻辑，组装sql语句
 * 数据输入为userprofile-dw模块输出数据
 * 数据输出为Hive标签表
 */
object UserTagCalcTask {
    private final val logger: Logger = LoggerFactory.getLogger(this.getClass)

    val lock = new Object

    def getNewSparkSession(): SparkSession = {

        lock.synchronized {

            SparkSession.clearDefaultSession()
            SparkSession.clearActiveSession()

            val conf = new SparkConf()
            conf.setAppName("up_ab_test_usergroup_grouping")
            conf.set("spark.serializer", "org.apache.spark.serializer.JavaSerializer")
            conf.set("spark.dynamicAllocation.enabled", "false")
            conf.set("spark.scheduler.mode", "FAIR")

            val sparkSession = SparkSQLUtils.getSparkSession(conf)

            sparkSession.catalog.setCurrentDatabase("dw")
            sparkSession.catalog.refreshTable("dw_profile_tag_usergroup_ABtest")

            sparkSession
        }
    }

    def main(args: Array[String]): Unit = {

        /** 初始化线程池 */
        val threadFactory = new ThreadFactoryBuilder().build
        val tagCalcComputePoll = new ThreadPoolExecutor(20, 30, 0L, TimeUnit.MINUTES, new LinkedBlockingQueue[Runnable](1024), threadFactory, new ThreadPoolExecutor.AbortPolicy)

        /** 初始化spark */
        val conf: SparkConf = new SparkConf()
        conf.setAppName("userprofile-tag-calc")
        conf.set("spark.serializer", "org.apache.spark.serializer.JavaSerializer")
        conf.set("spark.dynamicAllocation.enabled", "false")
        conf.set("spark.scheduler.mode", "FAIR")
        val sparkSession: SparkSession = getSparkSession(conf)
        sparkSession.sparkContext.setLogLevel("WARN")
        import sparkSession.sql

        /** 参数设置 */
        var dataDate: String = DateUtils.yesterday(DateTimeFormat.DATE_FORMAT.value)
        if (args.length >= 1) {
            dataDate = args(0)
        }

        /** 标签计算 */
        createTempViewMySqlTab(sparkSession, "userprofile_tag_calc_meta", "v_tag_calc_meta")
        val tagCalcMetaRdd = sql(
            s"""
               |SELECT
               |	tag_theme,tag_type,tag_ids,calc_sql
               |FROM
               |	v_tag_calc_meta
               |WHERE
               |	is_valid = 1
               |""".stripMargin).rdd
            .filter(x => !Strings.isNullOrEmpty(x.getAs[String]("calc_sql")))
            .cache()

        // 更新mysql监控表 (如果不存在，插入新记录)
        tagCalcMetaRdd.map(_.getAs[String]("tag_ids").toString).flatMap(_.split(",")).collect().foreach(x => {
            JdbcUtils.insertTagCalcMonitorRecord(x, dataDate)
        })

        tagCalcMetaRdd.foreach(row => {
            tagCalcComputePoll.submit( new TagCalcThread(
                row,
                "",
                ""
            ))
        })
        tagCalcComputePoll.shutdown()
        tagCalcComputePoll.awaitTermination(2, TimeUnit.HOURS)
        println(s"============== Calc tag end ==============")

        /** 数据检查 */

    }

}
