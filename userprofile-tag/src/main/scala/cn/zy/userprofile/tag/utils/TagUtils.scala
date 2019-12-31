package cn.zy.userprofile.tag.utils

/**
 * author: hufenggang
 * email: hufenggang2019@gmail.com
 * date: 2019/12/31 18:10
 */
object TagUtils {

    /**
     * 解析sql语句，替换相关数据表
     * @param sql
     * @return
     */
    def getHql(sql: String, tableName: String): String = {
        sql.replace("target_table", tableName)
    }

}
