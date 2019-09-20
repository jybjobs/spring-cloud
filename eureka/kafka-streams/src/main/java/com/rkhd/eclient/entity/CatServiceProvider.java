package com.rkhd.eclient.entity;


/**
 * CatServiceProvider
 */
public class CatServiceProvider extends CatServiceBase {

    private String id;
    private String data;
    private long durationInMillis; //响应时间
    private String messageId;
    private String message;
    private String rootMessageId;
    private String threadName;
    private String threadId;
    private long timestampInMillis; // 时间
    private String threadGroupName;
    private String parentMessageId;
    private long count=1L; //默认计数1 开始

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    //  servicLevel

   private Long metrics;

    public Long getMetrics() {
        return metrics;
    }

    public void setMetrics(Long metrics) {
        if(metrics != null) {
            this.metrics = metrics;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRootMessageId() {
        return rootMessageId;
    }

    public void setRootMessageId(String rootMessageId) {
        this.rootMessageId = rootMessageId;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public String getThreadId() {
        return threadId;
    }

    public void setThreadId(String threadId) {
        this.threadId = threadId;
    }

    public long getTimestampInMillis() {
        return timestampInMillis;
    }

    public void setTimestampInMillis(long timestampInMillis) {
        this.timestampInMillis = timestampInMillis;
    }

    public String getThreadGroupName() {
        return threadGroupName;
    }

    public void setThreadGroupName(String threadGroupName) {
        this.threadGroupName = threadGroupName;
    }

    public String getParentMessageId() {
        return parentMessageId;
    }

    public void setParentMessageId(String parentMessageId) {
        this.parentMessageId = parentMessageId;
    }

    @Override
    public String toString() {
        return "CatServiceProvider{" +
                "id='" + id + '\'' +
                ", data='" + data + '\'' +
                ", durationInMillis=" + durationInMillis +
                ", messageId='" + messageId + '\'' +
                ", message='" + message + '\'' +
                ", rootMessageId='" + rootMessageId + '\'' +
                ", userId='" + super.getUserId() + '\'' +
                ", threadName='" + threadName + '\'' +
                ", threadId='" + threadId + '\'' +
                ", timestampInMillis=" + timestampInMillis +
                ", localAddress='" + super.getLocalAddress() + '\'' +
                ", remoteDomain='" + super.getRemoteDomain() + '\'' +
                ", domain='" + super.getDomain() + '\'' +
                ", threadGroupName='" + threadGroupName + '\'' +
                ", tenantId='" + super.getTenantId() + '\'' +
                ", parentMessageId='" + parentMessageId + '\'' +
                ", remoteAddress='" + super.getRemoteAddress() + '\'' +
                ", status='" + super.getStatus() + '\'' +
                '}';
    }
}
