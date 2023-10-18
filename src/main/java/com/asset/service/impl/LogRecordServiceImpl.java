package com.asset.service.impl;

import com.asset.mapper.LogRecordMapper;
import com.asset.model.LogRecord;
import com.asset.service.LogRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LogRecordServiceImpl implements LogRecordService {

    @Autowired
    private LogRecordMapper logRecordMapper;

    @Override
    public List<LogRecord> findAll(int page, int size, String operationType, String operationUser, Date startDate, Date endDate) {
        Map<String, Object> params = new HashMap<>();
        params.put("offset", (page - 1) * size);
        params.put("size", size);
        params.put("operationType", operationType);  // 新增
        params.put("operationUser", operationUser);  // 新增
        params.put("startDate", startDate);          // 新增
        params.put("endDate", endDate);              // 新增
        return logRecordMapper.findAll(params);
    }

    @Override
    public int countAll(String operationType, String operationUser, Date startDate, Date endDate) {
        Map<String, Object> params = new HashMap<>();
        params.put("operationType", operationType);  // 新增
        params.put("operationUser", operationUser);  // 新增
        params.put("startDate", startDate);          // 新增
        params.put("endDate", endDate);              // 新增
        return logRecordMapper.countAll(params);
    }
}
