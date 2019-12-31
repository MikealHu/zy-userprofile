package cn.zy.userprofile.spark.constants;

/**
 * author: hufenggang
 * email: hufenggang2019@gmail.com
 * date: 2019/12/23 16:28
 */
public interface KafkaConstant {

    /** ******* Kafka配置常量 **********/
    String KAFKA_BOOTSTRAP_SERVERS = "kafka.bootstrap.servers";
    String KAFKA_CONSUMER_GROUP_ID = "kafka.consumer.group.id";
    String KAFKA_AUTO_OFFSET_RESET = "kafka.auto.offset.reset";

    String KAFKA_TOPIC_PV = "countly_pv";
    String KAFKA_TOPIC_EVENT = "countly_event";
    String KAFKA_TOPIC_STREAM1 = "stream1";
    String KAFKA_TOPIC_STREAM2 = "stream2";
}
