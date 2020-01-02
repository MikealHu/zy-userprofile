package cn.zy.userprofile.dao.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * created by hufenggang on 2020/1/2
 */
public class KafkaClient {
    private final Logger logger = LoggerFactory.getLogger(KafkaClient.class);

    private static KafkaProducer<String, String> producer;

    static {
        Properties props = new Properties();
        props.put("bootstrap.servers", "master:9092,slave1:9092,slave2:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("key.serializer", StringSerializer.class.getName());
        props.put("value.serializer", StringSerializer.class.getName());
        producer = new KafkaProducer<String, String>(props);
    }

    public static void produce(String topic, String key, String value) {
        producer.send(new ProducerRecord<String, String>(topic,key,value));
    }
}
