package cn.zy.userprofile.common.utils

/**
 * author: hufenggang
 * email: hufenggang2019@gmail.com
 * date: 2019/12/23 17:16
 */
object function {

    def nvl[T](v1: T, v2: T): T = {
        if (v1 == null) v2 else v1
    }

    def coalesce[T](v1: T, v2: T, v3: T): T = {
        if (v1 == null && v2 == null) v3
        else if (v1 == null) v2
        else v1
    }
}
