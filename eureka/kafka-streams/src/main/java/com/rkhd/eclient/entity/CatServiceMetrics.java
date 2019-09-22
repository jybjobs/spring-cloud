package com.rkhd.eclient.entity;


/**
 * CatServiceMetrics 处理后的结果集对象
 */
public class CatServiceMetrics extends CatServiceBase {

    private long durationInMillis; //响应时间
    private String messageId;
    private long timestampInMillis; // 时间
    private long count;

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public long getDurationInMillis() {
        return durationInMillis;
    }

    public void setDurationInMillis(long durationInMillis) {
        this.durationInMillis = durationInMillis;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public long getTimestampInMillis() {
        return timestampInMillis;
    }

    public void setTimestampInMillis(long timestampInMillis) {
        this.timestampInMillis = timestampInMillis;
    }


    @Override
    public String toString() {
        return "CatServiceProvider{" +
                ", durationInMillis=" + durationInMillis +
                ", messageId='" + messageId + '\'' +
                ", timestampInMillis=" + timestampInMillis +
                ", localAddress='" + super.getLocalAddress() + '\'' +
                ", remoteDomain='" + super.getRemoteDomain() + '\'' +
                ", domain='" + super.getDomain() + '\'' +
                ", tenantId='" + super.getTenantId() + '\'' +
                ", remoteAddress='" + super.getRemoteAddress() + '\'' +
                ", status='" + super.getStatus() + '\'' +
                '}';
    }

    public CatServiceMetrics (CatServiceProvider csp) {
        super(csp.getUserId(),csp.getLocalAddress(),csp.getRemoteDomain(),csp.getDomain(),csp.getTenantId(),
        csp.getRemoteAddress(),csp.getMethod(),csp.getStatus());
        this.durationInMillis = csp.getDurationInMillis();
        this.messageId = csp.getMessageId();
        this.count = csp.getCount();
    }
}
