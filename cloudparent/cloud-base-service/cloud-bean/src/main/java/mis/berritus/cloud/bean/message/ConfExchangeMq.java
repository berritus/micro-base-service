package mis.berritus.cloud.bean.message;

import mis.berritus.cloud.bean.base.MisBean;

import java.util.Date;

public class ConfExchangeMq extends MisBean {
    private static final long serialVersionUID = -8223733330570481395L;
    private Long id;

    private String exchangeName;

    private Byte exchangeDurable;

    private Byte exchangeAutoDelete;

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
        return "ConfExchangeMq{" +
                "id=" + id +
                ", exchangeName='" + exchangeName + '\'' +
                ", exchangeDurable=" + exchangeDurable +
                ", exchangeAutoDelete=" + exchangeAutoDelete +
                ", crtDate=" + crtDate +
                ", state=" + state +
                ", modifyDate=" + modifyDate +
                '}';
    }
}