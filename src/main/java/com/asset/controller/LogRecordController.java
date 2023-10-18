package com.asset.controller;

import com.asset.model.LogRecord;
import com.asset.service.LogRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/logrecords")
public class LogRecordController {

    @Autowired
    private LogRecordService logRecordService;

    @GetMapping("/list")
    public Map<String, Object> getAllLogRecords(@RequestParam int page, @RequestParam int size,
                                                @RequestParam(required = false) String operationType, // 新增参数
                                                @RequestParam(required = false) String operationUser, // 新增参数
                                                @RequestParam(required = false)@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  Date startDate,       // 新增参数
                                                @RequestParam(required = false)@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  Date endDate) {       // 新增参数

        List<LogRecord> records = logRecordService.findAll(page, size, operationType, operationUser, startDate, endDate);
        int count = logRecordService.countAll(operationType, operationUser, startDate, endDate);

        Map<String, Object> response = new HashMap<>();
        response.put("data", records);
        response.put("count", count);

        return response;
    }
}

