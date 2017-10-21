package smaug.cloud.data.entity.queue;

import java.util.Date;

public class QueuingEntity {
    private Long serialid;

    private String serialcode;

    private Byte qtype;

    private Integer queueid;

    private String shopqueueid;

    private Integer shopid;

    private Byte type;

    private Byte devtype;

    private String resettime;

    private Integer deviceid;

    private Integer userid;

    private String openid;

    private String mobile;

    private Integer people;

    private Integer number;

    private Date queuingtime;

    private Date createdate;

    private Date entrustdate;

    private Byte state;

    private Byte del;

    private String remark;

    private Byte reset;

    private Byte acked;

    private Date lasttime;

    private Integer eventno;

    private Date exectime;

    private Integer manageshopid;

    private Byte keepqueuing;

    private Short alltotal;

    private Integer estimatetime;

    private Short waitpeople;

    private Date alloctime;

    private Byte qrscan;

    private Byte smsview;

    private Byte weixinview;

    private Byte appview;

    private Byte creditusergot;

    private Byte wxnotice;

    private Integer discountid;

    private Byte offnotice;

    private String thirdurl;

    private Byte lastaccess;

    private Integer estqueueid;

    private Short estentime;

    private String mname;

    private Byte times;

    private Integer authid;

    private String authorderid;

    public Long getSerialid() {
        return serialid;
    }

    public void setSerialid(Long serialid) {
        this.serialid = serialid;
    }

    public String getSerialcode() {
        return serialcode;
    }

    public void setSerialcode(String serialcode) {
        this.serialcode = serialcode == null ? null : serialcode.trim();
    }

    public Byte getQtype() {
        return qtype;
    }

    public void setQtype(Byte qtype) {
        this.qtype = qtype;
    }

    public Integer getQueueid() {
        return queueid;
    }

    public void setQueueid(Integer queueid) {
        this.queueid = queueid;
    }

    public String getShopqueueid() {
        return shopqueueid;
    }

    public void setShopqueueid(String shopqueueid) {
        this.shopqueueid = shopqueueid == null ? null : shopqueueid.trim();
    }

    public Integer getShopid() {
        return shopid;
    }

    public void setShopid(Integer shopid) {
        this.shopid = shopid;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Byte getDevtype() {
        return devtype;
    }

    public void setDevtype(Byte devtype) {
        this.devtype = devtype;
    }

    public String getResettime() {
        return resettime;
    }

    public void setResettime(String resettime) {
        this.resettime = resettime == null ? null : resettime.trim();
    }

    public Integer getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(Integer deviceid) {
        this.deviceid = deviceid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public Integer getPeople() {
        return people;
    }

    public void setPeople(Integer people) {
        this.people = people;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Date getQueuingtime() {
        return queuingtime;
    }

    public void setQueuingtime(Date queuingtime) {
        this.queuingtime = queuingtime;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Date getEntrustdate() {
        return entrustdate;
    }

    public void setEntrustdate(Date entrustdate) {
        this.entrustdate = entrustdate;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public Byte getDel() {
        return del;
    }

    public void setDel(Byte del) {
        this.del = del;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Byte getReset() {
        return reset;
    }

    public void setReset(Byte reset) {
        this.reset = reset;
    }

    public Byte getAcked() {
        return acked;
    }

    public void setAcked(Byte acked) {
        this.acked = acked;
    }

    public Date getLasttime() {
        return lasttime;
    }

    public void setLasttime(Date lasttime) {
        this.lasttime = lasttime;
    }

    public Integer getEventno() {
        return eventno;
    }

    public void setEventno(Integer eventno) {
        this.eventno = eventno;
    }

    public Date getExectime() {
        return exectime;
    }

    public void setExectime(Date exectime) {
        this.exectime = exectime;
    }

    public Integer getManageshopid() {
        return manageshopid;
    }

    public void setManageshopid(Integer manageshopid) {
        this.manageshopid = manageshopid;
    }

    public Byte getKeepqueuing() {
        return keepqueuing;
    }

    public void setKeepqueuing(Byte keepqueuing) {
        this.keepqueuing = keepqueuing;
    }

    public Short getAlltotal() {
        return alltotal;
    }

    public void setAlltotal(Short alltotal) {
        this.alltotal = alltotal;
    }

    public Integer getEstimatetime() {
        return estimatetime;
    }

    public void setEstimatetime(Integer estimatetime) {
        this.estimatetime = estimatetime;
    }

    public Short getWaitpeople() {
        return waitpeople;
    }

    public void setWaitpeople(Short waitpeople) {
        this.waitpeople = waitpeople;
    }

    public Date getAlloctime() {
        return alloctime;
    }

    public void setAlloctime(Date alloctime) {
        this.alloctime = alloctime;
    }

    public Byte getQrscan() {
        return qrscan;
    }

    public void setQrscan(Byte qrscan) {
        this.qrscan = qrscan;
    }

    public Byte getSmsview() {
        return smsview;
    }

    public void setSmsview(Byte smsview) {
        this.smsview = smsview;
    }

    public Byte getWeixinview() {
        return weixinview;
    }

    public void setWeixinview(Byte weixinview) {
        this.weixinview = weixinview;
    }

    public Byte getAppview() {
        return appview;
    }

    public void setAppview(Byte appview) {
        this.appview = appview;
    }

    public Byte getCreditusergot() {
        return creditusergot;
    }

    public void setCreditusergot(Byte creditusergot) {
        this.creditusergot = creditusergot;
    }

    public Byte getWxnotice() {
        return wxnotice;
    }

    public void setWxnotice(Byte wxnotice) {
        this.wxnotice = wxnotice;
    }

    public Integer getDiscountid() {
        return discountid;
    }

    public void setDiscountid(Integer discountid) {
        this.discountid = discountid;
    }

    public Byte getOffnotice() {
        return offnotice;
    }

    public void setOffnotice(Byte offnotice) {
        this.offnotice = offnotice;
    }

    public String getThirdurl() {
        return thirdurl;
    }

    public void setThirdurl(String thirdurl) {
        this.thirdurl = thirdurl == null ? null : thirdurl.trim();
    }

    public Byte getLastaccess() {
        return lastaccess;
    }

    public void setLastaccess(Byte lastaccess) {
        this.lastaccess = lastaccess;
    }

    public Integer getEstqueueid() {
        return estqueueid;
    }

    public void setEstqueueid(Integer estqueueid) {
        this.estqueueid = estqueueid;
    }

    public Short getEstentime() {
        return estentime;
    }

    public void setEstentime(Short estentime) {
        this.estentime = estentime;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname == null ? null : mname.trim();
    }

    public Byte getTimes() {
        return times;
    }

    public void setTimes(Byte times) {
        this.times = times;
    }

    public Integer getAuthid() {
        return authid;
    }

    public void setAuthid(Integer authid) {
        this.authid = authid;
    }

    public String getAuthorderid() {
        return authorderid;
    }

    public void setAuthorderid(String authorderid) {
        this.authorderid = authorderid == null ? null : authorderid.trim();
    }
}