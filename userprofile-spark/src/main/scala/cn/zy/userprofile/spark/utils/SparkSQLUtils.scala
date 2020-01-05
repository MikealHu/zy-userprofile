package cn.zy.userprofile.spark.utils

import cn.zy.userprofile.common.utils.ConfigUtils
import cn.zy.userprofile.spark.constants.SparkConfConstant
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

/**
 * author: hufenggang
 * email: hufenggang2019@gmail.com
 * date: 2019/12/23 16:02
 */
object SparkSQLUtils {

    val lock = new Object

    val KryoSerializer = "org.apache.spark.serializer.KryoSerializer"

    /**
     * Spark项目初始化，获取对应的SparkSession对象
     *
     * @param sparkConf SparkConf对象
     * @return SparkSession对象
     */
    def getSparkSession(sparkConf: SparkConf): SparkSession = {
        val sparkConfigMap = ConfigUtils.getPropertiesMap(this.getClass, "/userprofile-spark.properties")

        // 判断是否本地调试环境
        val sparkSession = if (
            sparkConfigMap.contains(SparkConfConstant.SPARK_ENV_CONFIG_KEY)
                && sparkConfigMap(SparkConfConstant.SPARK_ENV_CONFIG_KEY).equals("dev")
        ) {
            SparkSession
                .builder()
                .master("local[*]")
                .config(sparkConf)
                .getOrCreate()
        } else {
            SparkSession
                .builder()
                .config(sparkConf)
                .getOrCreate()
        }

        sparkSession
    }

    /**
     * Spark项目初始化，获取对应的SparkSession对象
     *
     * @param sparkConf SparkConf对象
     * @param classes   需要Kryo序列化的类
     * @return SparkSession对象
     */
    def getSparkSessionWithKryoClasses(sparkConf: SparkConf, classes: Array[Class[_]]): SparkSession = {

        if (classes != null && !classes.isEmpty) {
            sparkConf.registerKryoClasses(classes)
        }

        val sparkConfigMap = ConfigUtils.getPropertiesMap(this.getClass, "/userprofile-spark.properties")

        // 判断是否本地调试环境
        val sparkSession = if (
            sparkConfigMap.contains(SparkConfConstant.SPARK_ENV_CONFIG_KEY)
                && sparkConfigMap(SparkConfConstant.SPARK_ENV_CONFIG_KEY).equals("dev")
        ) {
            SparkSession
                .builder()
                .master("local[*]")
                .config(sparkConf)
                .getOrCreate()
        } else {
            SparkSession
                .builder()
                .config(sparkConf)
                .getOrCreate()
        }

        sparkSession
    }

    /**
     * Spark项目初始化，获取对应的SparkSession对象
     *
     * @param appName AppName
     * @return SparkSession对象
     */
    def getSparkSession(appName: String): SparkSession = {

        val sparkConf = new SparkConf()
            .setAppName(appName)
            .set("spark.serializer", KryoSerializer)

        val sparkConfigMap = ConfigUtils.getPropertiesMap(this.getClass, "/userprofile-spark.properties")

        // 判断是否本地调试环境
        val sparkSession = if (
            sparkConfigMap.contains(SparkConfConstant.SPARK_ENV_CONFIG_KEY)
                && sparkConfigMap(SparkConfConstant.SPARK_ENV_CONFIG_KEY).equals("dev")
        ) {
            SparkSession
                .builder()
                .master("local[*]")
                .config(sparkConf)
                .getOrCreate()
        } else {
            SparkSession
                .builder()
                .config(sparkConf)
                .getOrCreate()
        }

        sparkSession
    }

    /**
     * Spark项目初始化，获取对应的SparkSession对象
     *
     * @param appName AppName
     * @param classes 需要Kryo序列化的类
     * @return SparkSession对象
     */
    def getSparkSessionWithKryoClasses(appName: String, classes: Array[Class[_]]): SparkSession = {

        val sparkConf = new SparkConf()
            .setAppName(appName)
            .set("spark.serializer", KryoSerializer)
            .set("spark.kryo.registrationRequired", "true")

        if (classes != null && !classes.isEmpty) {
            sparkConf.registerKryoClasses(classes)
        }

        val sparkConfigMap = ConfigUtils.getPropertiesMap(this.getClass, "/userprofile-spark.properties")

        // 判断是否本地调试环境
        val sparkSession = if (
            sparkConfigMap.contains(SparkConfConstant.SPARK_ENV_CONFIG_KEY)
                && sparkConfigMap(SparkConfConstant.SPARK_ENV_CONFIG_KEY).equals("dev")
        ) {
            SparkSession
                .builder()
                .master("local[*]")
                .config(sparkConf)
                .getOrCreate()
        } else {
            SparkSession
                .builder()
                .config(sparkConf)
                .getOrCreate()
        }

        sparkSession
    }

    /**
     * 获取一个新的SparkSession对象
     *
     * @return SparkSession对象
     */
    def getNewSparkSession(): SparkSession = {

        lock.synchronized {

            clearSparkSession()

            val conf = new SparkConf()
            conf.setAppName("up_ab_test_usergroup_grouping")
            conf.set("spark.serializer", "org.apache.spark.serializer.JavaSerializer")
            conf.set("spark.dynamicAllocation.enabled", "false")
            conf.set("spark.scheduler.mode", "FAIR")

            val sparkSession = getSparkSession(conf)

            sparkSession.catalog.setCurrentDatabase("dw")
            sparkSession.catalog.refreshTable("dw_profile_tag_usergroup_ABtest")

            sparkSession
        }
    }

    private def clearSparkSession() = {
        SparkSession.clearDefaultSession()
        SparkSession.clearActiveSession()
    }

    def refreshTable(tableName: String) = {

    }

    /**
     * 使用SparkSQl读取表注册为视图
     *
     * @param tbsName 读取的表名
     * @param vName   视图名称
     */
    def createTempViewMySqlTab(spark: SparkSession, tbsName: String, vName: String) = {

        val map = ConfigUtils.getPropertiesMap(SparkSQLUtils.getClass, "/jdbc.properties")

        val ruleDF = spark
            .read
            .format("jdbc")
            .option("driver", map.get("mysql.db.driver").get)
            .option("url", map.get("mysql.db.url").get)
            .option("dbtable", tbsName)
            .option("user", map.get("mysql.db.user").get)
            .option("password", map.get("mysql.db.password").get)
            .load()

        // 注册视图
        ruleDF.createOrReplaceTempView(vName)
        ruleDF
    }
}
