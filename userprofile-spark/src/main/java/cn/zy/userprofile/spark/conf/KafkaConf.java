package cn.zy.userprofile.spark.conf;

import cn.zy.userprofile.common.utils.ConfigUtils;
import cn.zy.userprofile.spark.constants.KafkaConstant;

import java.util.Map;

/**
 * author: hufenggang
 * email: hufenggang2019@gmail.com
 * date: 2019/12/23 16:29
 */
public class KafkaConf {

    private static final Map<String, String> configMap = ConfigUtils.getPropertiesJavaMap(KafkaConf.class, "/kafka.properties");

    /** ******* Kafka配置参数 **********/
    public static final String KAFKA_BOOTSTRAP_SERVERS = configMap.get(KafkaConstant.KAFKA_BOOTSTRAP_SERVERS);
    public static final String  KAFKA_CONSUMER_GROUP_ID = configMap.get(KafkaConstant.KAFKA_CONSUMER_GROUP_ID);
    public static final String  KAFKA_AUTO_OFFSET_RESET = configMap.get(KafkaConstant.KAFKA_AUTO_OFFSET_RESET);
}
