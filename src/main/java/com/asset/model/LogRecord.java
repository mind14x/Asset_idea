package com.asset.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class LogRecord {

    private int logId;
    private String operationType;
    private String operationUser;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Shanghai")
    private Date operationDate;
    private String operationDetail;

    public int getLogId() {
        return logId;
    }

    public void setLogId(int logId) {
        this.logId = logId;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getOperationUser() {
        return operationUser;
    }

    public void setOperationUser(String operationUser) {
        this.operationUser = operationUser;
    }

    public Date getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(Date operationDate) {
        this.operationDate = operationDate;
    }

    public String getOperationDetail() {
        return operationDetail;
    }

    public void setOperationDetail(String operationDetail) {
        this.operationDetail = operationDetail;
    }
}
