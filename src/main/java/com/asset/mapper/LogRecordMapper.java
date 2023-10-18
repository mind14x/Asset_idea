package com.asset.mapper;

import com.asset.model.LogRecord;

import java.util.List;
import java.util.Map;

public interface LogRecordMapper {
    int insertLogRecord(LogRecord logRecord);
    List<LogRecord> findAll(Map<String, Object> params);
    int countAll(Map<String, Object> params);
}
