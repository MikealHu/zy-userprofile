package cn.zy.userprofile.dw.dim

import java.util.Properties

import cn.zy.userprofile.common.utils.date.{DateTimeFormat, DateUtils}
import cn.zy.userprofile.spark.utils.SparkSQLUtils
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.SaveMode.Overwrite
import org.apache.hudi.DataSourceWriteOptions._
import org.apache.hudi.QuickstartUtils.getQuickstartWriteConfigs
import org.apache.hudi.config.HoodieWriteConfig
import org.apache.hudi.config.HoodieWriteConfig.TABLE_NAME

/**
 * author: hufenggang
 * email: hufenggang2019@gmail.com
 * date: 2019/12/31 16:28
 * user-cookie对应关系表
 */
object DimUserCookieRelationTask {

    def main(args: Array[String]): Unit = {

        /** 初始化spark */
        val sparkSession: SparkSession = SparkSQLUtils.getSparkSession("userprofile-dw-user_cookie_relation")
        sparkSession.sparkContext.setLogLevel("WARN")
        import sparkSession.sql

        /** 参数设置 */
        var dataDate: String = DateUtils.yesterday(DateTimeFormat.DATE_FORMAT.value)
        if (args.length >= 1) {
            dataDate = args(0)
        }
        val sourceTab = "dw.dw_cookie_user_relation"
        val targetTab = "dw.dw_profile_cookie_user_relation"
        val basePath = "file:///tmp/hudi_cow_table"

        val df = sql(
            s"""
               |SELECT t.userid,
               |       t.cookie_id AS cookieid
               |FROM
               |  (SELECT cookie_id,
               |          userid,
               |          row_number() over(partition BY cookie_id
               |                            ORDER BY last_time DESC,last_date DESC,userid DESC) AS rank
               |   FROM ${sourceTab}
               |   WHERE cookie_id IS NOT NULL
               |     AND userid IS NOT NULL ) t
               |WHERE t.rank =1
               |HAVING cookie_id IS NOT NULL
               |""".stripMargin)

        val props = new Properties()

        val cfg = HoodieWriteConfig.newBuilder()
            .withPath(basePath)
            .forTable("")
            .withBulkInsertParallelism(1500)
            .withSchema("")
            .withProps(props)

        df.write.format("org.apache.hudi").
            options(getQuickstartWriteConfigs).
            // Hive表名，用于将数据集注册到其中
            option(TABLE_NAME_OPT_KEY, targetTab).
            option(PRECOMBINE_FIELD_OPT_KEY, "ts").
            option(RECORDKEY_FIELD_OPT_KEY, "uuid").
            option(PARTITIONPATH_FIELD_OPT_KEY, dataDate).
            option(TABLE_NAME, targetTab).
            mode(Overwrite).
            save(basePath)
    }
}
