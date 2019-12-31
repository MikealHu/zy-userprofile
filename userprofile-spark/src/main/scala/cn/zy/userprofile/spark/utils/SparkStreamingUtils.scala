package cn.zy.userprofile.spark.utils

import cn.zy.userprofile.common.utils.ConfigUtils
import cn.zy.userprofile.spark.constants.SparkConfConstant
import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * author: hufenggang
 * email: hufenggang2019@gmail.com
 * date: 2019/12/23 16:56
 */
object SparkStreamingUtils {

    /**
     *
     * @param sparkConf
     * @param batchSeconds
     * @return
     */
    def getStreamingContext(sparkConf: SparkConf, batchSeconds: Long): StreamingContext = {
        val ssc = new StreamingContext(sparkConf, Seconds(1))
        ssc
    }

    /**
     *
     * @param appName
     * @param batchSeconds
     * @return
     */
    def getStreamingContext(appName: String, batchSeconds: Long): StreamingContext = {

        val sparkConfigMap = ConfigUtils.getPropertiesMap(this.getClass, "/userprofile-spark.properties")

        // 判断是否本地调试环境
        val ssc = if (
            sparkConfigMap.contains(SparkConfConstant.SPARK_ENV_CONFIG_KEY)
                && sparkConfigMap(SparkConfConstant.SPARK_ENV_CONFIG_KEY).equals("dev")
        ) {
            val sparkConf = new SparkConf()
                .setMaster("local[*]")
                .setAppName(appName)
            new StreamingContext(sparkConf, Seconds(batchSeconds))
        } else {
            val sparkConf = new SparkConf()
                .setAppName(appName)
            new StreamingContext(sparkConf, Seconds(batchSeconds))
        }
        ssc
    }
}
