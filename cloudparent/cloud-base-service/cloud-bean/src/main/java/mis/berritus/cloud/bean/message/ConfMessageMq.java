package mis.berritus.cloud.bean.message;

import mis.berritus.cloud.bean.base.MisBean;

import java.util.Date;

public class ConfMessageMq extends MisBean {
    private static final long serialVersionUID = 8171160131401869857L;
    private Long id;

    private String exchangeName;

    private Byte exchangeDurable;

    private Byte exchangeAutoDelete;

    private String queueName;

    private Byte queueDurable;

    private Byte queueAutoDelete;

    private Byte queueExclusive;

    private String routintKey;

    private Date crtDate;

    private Byte state;

    private Date modifyDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExchangeName() {
        return exchangeName;
    }

    public void setExchangeName(String exchangeName) {
        this.exchangeName = exchangeName == null ? null : exchangeName.trim();
    }

    public Byte getExchangeDurable() {
        return exchangeDurable;
    }

    public void setExchangeDurable(Byte exchangeDurable) {
        this.exchangeDurable = exchangeDurable;
    }

    public Byte getExchangeAutoDelete() {
        return exchangeAutoDelete;
    }

    public void setExchangeAutoDelete(Byte exchangeAutoDelete) {
        this.exchangeAutoDelete = exchangeAutoDelete;
    }

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName == null ? null : queueName.trim();
    }

    public Byte getQueueDurable() {
        return queueDurable;
    }

    public void setQueueDurable(Byte queueDurable) {
        this.queueDurable = queueDurable;
    }

    public Byte getQueueAutoDelete() {
        return queueAutoDelete;
    }

    public void setQueueAutoDelete(Byte queueAutoDelete) {
        this.queueAutoDelete = queueAutoDelete;
    }

    public Byte getQueueExclusive() {
        return queueExclusive;
    }

    public void setQueueExclusive(Byte queueExclusive) {
        this.queueExclusive = queueExclusive;
    }

    public String getRoutintKey() {
        return routintKey;
    }

    public void setRoutintKey(String routintKey) {
        this.routintKey = routintKey == null ? null : routintKey.trim();
    }

    public Date getCrtDate() {
        return crtDate;
    }

    public void setCrtDate(Date crtDate) {
        this.crtDate = crtDate;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    @Override
    public String toString() {
        return "ConfMessageMq{" +
                "id=" + id +
                ", exchangeName='" + exchangeName + '\'' +
                ", exchangeDurable=" + exchangeDurable +
                ", exchangeAutoDelete=" + exchangeAutoDelete +
                ", queueName='" + queueName + '\'' +
                ", queueDurable=" + queueDurable +
                ", queueAutoDelete=" + queueAutoDelete +
                ", queueExclusive=" + queueExclusive +
                ", routintKey='" + routintKey + '\'' +
                ", crtDate=" + crtDate +
                ", state=" + state +
                ", modifyDate=" + modifyDate +
                '}';
    }
}