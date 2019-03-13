package mis.berritus.cloud.bean.message;

import mis.berritus.cloud.bean.base.MisBean;

import java.io.Serializable;

public class RequestMsg extends MisBean {
    private static final long serialVersionUID = -8195203939684813526L;
    private Integer msgId;
    private String code;
    private String sendData;
    private String exChange;
    private String routingKey;

    public Integer getMsgId() {
        return msgId;
    }

    public void setMsgId(Integer msgId) {
        this.msgId = msgId;
    }

    public String getExChange() {
        return exChange;
    }

    public void setExChange(String exChange) {
        this.exChange = exChange;
    }

    public String getRoutingKey() {
        return routingKey;
    }

    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSendData() {
        return sendData;
    }

    public void setSendData(String sendData) {
        this.sendData = sendData;
    }
}
