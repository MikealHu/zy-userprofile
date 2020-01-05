package cn.zy.userprofile.tag.thread

import cn.zy.userprofile.tag.task.UserTagCalcTask
import cn.zy.userprofile.tag.utils.{JdbcUtils, TagUtils}
import org.apache.spark.sql.{DataFrame, Row}

/**
 * Created by hufenggang on 2020/1/5.
 */
class TagCalcThread(
                       row: Row,
                       dataDate: String,
                       targetTab: String
                   ) extends Runnable {
    override def run(): Unit = {
        var flag: Boolean = true

        // 状态检查
        val tagIdArray: Array[String] = row.getAs[String]("tag_ids").split(",")
        for (tagId <- tagIdArray) {
            if (!JdbcUtils.selectTagCalcIsSuccess(tagId, dataDate)) {
                flag = false
            }
        }

        if (flag) {
            return
        }

        // 获取参数
        val tagTheme = row.getAs[String]("tag_theme")
        val tagCalcSql = row.getAs[String]("calc_sql")

        Thread.currentThread().setName(s"TagCalcThread tagTheme == ${tagTheme}")
        val sparkSession = UserTagCalcTask.getNewSparkSession()
        import sparkSession.sql

        val df: DataFrame = sql(TagUtils.getHql(tagCalcSql, targetTab))
    }
}
