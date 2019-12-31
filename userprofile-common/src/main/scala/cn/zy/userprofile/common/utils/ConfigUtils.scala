package cn.zy.userprofile.common.utils

import java.io.{BufferedInputStream, InputStream}
import java.util.Properties
import scala.collection.JavaConversions._

/**
 * author: hufenggang
 * email: hufenggang2019@gmail.com
 * date: 2019/12/23 16:08
 */
object ConfigUtils {

    def getPropertiesMap(clazz: Class[_], path: String): Map[String, String] = {

        val propertiesMap = scala.collection.mutable.HashMap.empty[String, String]
        val properties: Properties = new Properties
        val in: InputStream = clazz.getResourceAsStream(path)
        properties.load(new BufferedInputStream(in))

        for (entry <- properties.entrySet()) {
            val key = entry.getKey.asInstanceOf[String]
            val value = entry.getValue.asInstanceOf[String]

            propertiesMap += (key -> value)
        }

        propertiesMap.toMap
    }

    /**
     * 获取配置文件
     *
     * @param clazz
     * @param path
     * @return java.util.Map对象
     */
    def getPropertiesJavaMap(clazz: Class[_], path: String): java.util.Map[String, String] = {
        getPropertiesMap(clazz, path)
    }
}
