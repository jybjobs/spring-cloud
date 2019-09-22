package com.rkhd.eclient.entity;


/**
 * CatServiceBase
 */
public class CatServiceBase {

    public static final String DURATION_IN_MILLIS_NAME = "durationInMillis";
    public static final String TINESTAMP_IN_MILLIS ="timestampInMillis";
    public static final String TINESTAMP ="@timestamp";
    public static final String DOMAIN_NAME = "domain";
    public static final String LOCAL_ADDRESS = "localAddress";
    public static final String METHOD = "method";
    public static final String TENANT_ID = "tenantId";
    public static final String USER_ID = "userId";
    public static final String STATUS_NAME = "status";
    public static final String MESSAGE_ID = "messageId";
    public static final String REMOTE_DOMAIN = "remoteDomain";
    public static final String REMOTE_ADDRESS = "remoteAddress";

  //  private long durationInMillis; //响应时间
  //  private String messageId;
    private String userId;
   // private long timestampInMillis; // 时间
    private String localAddress;
    private String remoteDomain;
    private String domain;
    private String tenantId;
    private String remoteAddress;
    private String method;
    private String status; //异常类型 : 0  为正常
   // private long count;


    public CatServiceBase() {
    }

    public CatServiceBase(String userId, String localAddress, String remoteDomain, String domain, String tenantId, String remoteAddress, String method, String status) {
        this.userId = userId;
        this.localAddress = localAddress;
        this.remoteDomain = remoteDomain;
        this.domain = domain;
        this.tenantId = tenantId;
        this.remoteAddress = remoteAddress;
        this.method = method;
        this.status = status;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLocalAddress() {
        return localAddress;
    }

    public void setLocalAddress(String localAddress) {
        this.localAddress = localAddress;
    }

    public String getRemoteDomain() {
        return remoteDomain;
    }

    public void setRemoteDomain(String remoteDomain) {
        this.remoteDomain = remoteDomain;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getRemoteAddress() {
        return remoteAddress;
    }

    public void setRemoteAddress(String remoteAddress) {
        this.remoteAddress = remoteAddress;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CatServiceBase{" +
                ", userId='" + userId + '\'' +
                ", localAddress='" + localAddress + '\'' +
                ", remoteDomain='" + remoteDomain + '\'' +
                ", domain='" + domain + '\'' +
                ", tenantId='" + tenantId + '\'' +
                ", remoteAddress='" + remoteAddress + '\'' +
                ", method='" + method + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
