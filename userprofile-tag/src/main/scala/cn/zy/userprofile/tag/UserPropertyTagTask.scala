package cn.zy.userprofile.tag

import java.util.concurrent.{LinkedBlockingQueue, ThreadPoolExecutor, TimeUnit}

import cn.zy.userprofile.common.utils.{DateTimeFormat, DateUtils}
import cn.zy.userprofile.spark.utils.SparkSQLUtils._
import cn.zy.userprofile.tag.utils.JdbcUtils
import com.google.common.base.Strings
import com.google.common.util.concurrent.ThreadFactoryBuilder
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
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
object UserPropertyTagTask {
    private final val logger:Logger = LoggerFactory.getLogger(this.getClass)

    def main(args: Array[String]): Unit = {

        /** 初始化线程池 */
        val threadFactory = new ThreadFactoryBuilder().build
        val tagCalcComputePoll = new ThreadPoolExecutor(20, 30, 0L, TimeUnit.MINUTES, new LinkedBlockingQueue[Runnable](1024), threadFactory, new ThreadPoolExecutor.AbortPolicy)

        /** 初始化spark */
        val conf: SparkConf = new SparkConf()
        conf.setAppName("userprofile-tag-property")
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
            tagCalcComputePoll.submit(new )
        })

    }

}
