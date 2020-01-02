package cn.zy.userprofile.common.utils.date;

/**
 * author: hufenggang
 * email: hufenggang2019@gmail.com
 * date: 2019/12/31 14:03
 */
public enum DateTimeFormat {
    DATE_FORMAT("yyyyMMdd"),
    DATE_SIGN_FORMAT("yyyy-MM-dd"),
    TIME_FORMAT("yyyyMMdd HH:mm:ss"),
    TIME_SIGN_FORMAT("yyyy-MM-dd HH:mm:ss");

    private final String value;

    DateTimeFormat(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }
}
