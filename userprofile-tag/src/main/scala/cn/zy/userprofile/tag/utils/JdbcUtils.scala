package cn.zy.userprofile.tag.utils

/**
 * author: hufenggang
 * email: hufenggang2019@gmail.com
 * date: 2019/12/31 15:43
 */
object JdbcUtils {

    /** -------------< userprofile_tag_calc_monitor表 >------------- */
    /**
     * 更新mysql记录，如果不存在，则插入记录
     * @param tagId
     * @param dataDate
     * @return
     */
    def insertTagCalcMonitorRecord(tagId: String, dataDate: String): Boolean = {

        false
    }

    /**
     * 更新mysql记录，如果不存在，则插入记录
     * @param tagId
     * @param dataDate
     * @return
     */
    def selectTagCalcIsSuccess(tagId: String, dataDate: String): Boolean = {

        false
    }
}
