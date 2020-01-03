package cn.zy.userprofile.common.network;

/**
 * created by hufenggang on 2020/1/2
 */
public interface Message {

    enum Type {
        MonitorMessageSuccess(1),MonitorMessageFailure(2),MonitorMessageWarn(3);

        private final byte id;

        Type(int id) {
            this.id = (byte) id;
        }
    }
}
