package com.asset.service;

import com.asset.model.LogRecord;

import java.util.Date;
import java.util.List;

public interface LogRecordService {
    List<LogRecord> findAll(int page, int size, String operationType, String operationUser, Date startDate, Date endDate);
    int countAll(String operationType, String operationUser, Date startDate, Date endDate);
}