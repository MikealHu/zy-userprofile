package cn.zy.userprofile.dw.topic

import cn.zy.userprofile.spark.conf.KafkaConf
import cn.zy.userprofile.spark.constants.KafkaConstant
import cn.zy.userprofile.spark.utils.SparkStreamingUtils
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.streaming.kafka010._
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe

/**
 * author: hufenggang
 * email: hufenggang2019@gmail.com
 * date: 2019/12/23 15:51
 */
object TpEventLogTask {

    def main(args: Array[String]): Unit = {

        val ssc = SparkStreamingUtils.getStreamingContext("TpEventLogTask", 2L)
        ssc.sparkContext.setLogLevel("WARN")

        val kafkaParams = Map[String, Object](
            "bootstrap.servers" -> KafkaConf.KAFKA_BOOTSTRAP_SERVERS,
            "key.deserializer" -> classOf[StringDeserializer],
            "value.deserializer" -> classOf[StringDeserializer],
            "group.id" -> KafkaConf.KAFKA_CONSUMER_GROUP_ID,
            "auto.offset.reset" -> KafkaConf.KAFKA_AUTO_OFFSET_RESET,
            "enable.auto.commit" -> (false: java.lang.Boolean)
        )

        val topics = Array(KafkaConstant.KAFKA_TOPIC_PV, KafkaConstant.KAFKA_TOPIC_EVENT)
        val stream = KafkaUtils.createDirectStream[String, String](
            ssc,
            PreferConsistent,
            Subscribe[String, String](topics, kafkaParams)
        )

        val sink = stream.map(record => (record.topic, record.value))

        // stream数据输出到Hudi
        sink.foreachRDD(rdd => {

        })

        ssc.start()
        ssc.awaitTermination()
    }

}
