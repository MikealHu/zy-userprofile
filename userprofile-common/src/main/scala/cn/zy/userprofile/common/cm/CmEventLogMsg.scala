package cn.zy.userprofile.common.cm

import cn.zy.userprofile.common.utils.function._

/**
 * author: hufenggang
 * email: hufenggang2019@gmail.com
 * date: 2019/12/23 17:15
 */

/**
 *
 * @param eventid      事件ID
 * @param appversion   APP版本
 * @param siteid       站点ID
 * @param cookieid     cookieid
 * @param realmedium   真实媒介
 * @param realsource   真实来源
 * @param realcampaign 真实系列
 * @param medium       覆盖后媒介
 * @param source       覆盖后来源
 * @param campaign     覆盖后系列
 * @param isnewuser    是否新用户
 * @param deviceid     设备ID
 * @param devicename   设备名称
 * @param os           操作系统
 * @param os_version   操作系统版本
 * @param ipaddress    ip地址
 * @param ip_country   ip国家
 * @param ip_city      ip城市
 * @param eventkey     事件名称
 * @param eventcount
 * @param pagename     页面名称
 * @param userid       用户id
 * @param categoryid   类目id
 * @param referview
 * @param featurecode  专题跟踪码
 * @param goodsid      商品ID
 * @param goodsnum     商品数量
 * @param orderid      订单ID
 * @param pagenum
 * @param position     位置
 * @param keyword      搜索关键词
 * @param result       事件返回结果
 * @param algorithm    算法
 * @param area
 * @param revenue      收入
 * @param lable
 * @param clienttime   客户端时间
 * @param servertime   服务器时间
 * @param eventtime    事件时间
 * @param lang         语言
 * @param segment      segement字段:json格式存储
 * @param sid2         sid2
 * @param app_key      对应appsflyer的app_id
 * @param store_id     店铺id
 * @param data_date    数据日期
 */
case class CmEventLogMsg(
                            eventid: String
                            , appversion: String
                            , siteid: java.lang.Long
                            , cookieid: String
                            , realmedium: String
                            , realsource: String
                            , realcampaign: String
                            , medium: String
                            , source: String
                            , campaign: String
                            , isnewuser: java.lang.Integer
                            , deviceid: String
                            , devicename: String
                            , os: String
                            , os_version: String
                            , ipaddress: String
                            , ip_country: String
                            , ip_city: String
                            , eventkey: String
                            , eventcount: java.lang.Long
                            , pagename: String
                            , userid: java.lang.Long
                            , categoryid: java.lang.Long
                            , referview: String
                            , featurecode: String
                            , goodsid: java.lang.Long
                            , goodsnum: java.lang.Long
                            , orderid: java.lang.Long
                            , pagenum: java.lang.Long
                            , position: java.lang.Long
                            , keyword: String
                            , result: String
                            , algorithm: String
                            , area: String
                            , revenue: java.lang.Double
                            , lable: String
                            , clienttime: String
                            , servertime: String
                            , eventtime: String
                            , lang: String
                            , segment: String
                            , sid2: String
                            , app_key: String
                            , store_id: java.lang.Long
                            , data_date: String
                        ) {
}
