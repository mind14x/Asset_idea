package com.asset.service.impl;

import com.asset.dto.AssetPageDTO;
import com.asset.mapper.AssetMapper;
import com.asset.mapper.LogRecordMapper;
import com.asset.model.Asset;
import com.asset.model.LogRecord;
import com.asset.service.AssetService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class AssetServiceImpl implements AssetService {

    @Autowired
    private AssetMapper assetMapper;

    @Autowired
    private LogRecordMapper logRecordMapper;  // Add this

    // 该方法用于获取资产列表，并支持分页功能
    @Override
    public AssetPageDTO listAssets(int page, int size, String assetCode, String category, String serialNumber, String department, String user, String remark) {
        int offset = (page - 1) * size;
        List<Asset> assets = assetMapper.listAssets(offset, size, assetCode, category, serialNumber, department, user, remark); // <-- 修改
        int total = assetMapper.countAssetsWithFilters(assetCode, category, serialNumber, department, user, remark); // <-- 修改
        return new AssetPageDTO(assets, total);
    }

    @Override
    public Asset getAssetDetail(Long assetId) {
        return assetMapper.findById(assetId);
    }

    @Override
    public void updateAsset(long assetId, Asset asset) {
        asset.setId(assetId); // 确保ID匹配
        assetMapper.update(asset);
    }


    @Override
    public void registerAsset(Asset asset) {
        assetMapper.insertAsset(asset);
    }


    @Override
    public void deleteAsset(Long assetId) {
        assetMapper.deleteAssetById(assetId);
    }


    /**
     * 日志记录的逻辑
     */
    @Override
    public void logOperation(String operationType, String operationUser, String operationDetail) {
        LogRecord logRecord = new LogRecord();
        logRecord.setOperationType(operationType);
        logRecord.setOperationUser(operationUser);
        logRecord.setOperationDate(new Date());
        logRecord.setOperationDetail(operationDetail);
        logRecordMapper.insertLogRecord(logRecord);
    }


    //导出excel接口实现
    @Override
    public List<Asset> getAllAssets() {
        return assetMapper.findAllAssets();
    }

    @Override
    public ByteArrayOutputStream exportAssetsToExcel() {
        List<Asset> assets = getAllAssets();

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Assets");

        // Create header row
        Row headerRow = sheet.createRow(0);
        String[] columns = {
                "ID", "资产从属", "资产类别", "资产编号", "资产状态", "品牌",
                "型号", "序列号", "部门", "使用人", "录入时间",
                "电脑名称", "系统配置", "备注"
        };

        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // Define date format

        // Fill the sheet with asset data
        int rowNum = 1;
        int idCounter = 1; // Counter for ID
        for (Asset asset : assets) {
            Row row = sheet.createRow(rowNum++);

            row.createCell(0).setCellValue(idCounter++); // Use counter for ID
            row.createCell(1).setCellValue(asset.getAffiliation());
            row.createCell(2).setCellValue(asset.getCategory());
            row.createCell(3).setCellValue(asset.getAssetCode());
            row.createCell(4).setCellValue(asset.getStatus());
            row.createCell(5).setCellValue(asset.getBrand());
            row.createCell(6).setCellValue(asset.getModel());
            row.createCell(7).setCellValue(asset.getSerialNumber());
            row.createCell(8).setCellValue(asset.getDepartment());
            row.createCell(9).setCellValue(asset.getUser());

            // Check for null on EntryDate and format the date
            if(asset.getEntryDate() != null) {
                row.createCell(10).setCellValue(sdf.format(asset.getEntryDate()));
            } else {
                row.createCell(10).setCellValue("N/A");
            }

            row.createCell(11).setCellValue(asset.getComputerName());
            row.createCell(12).setCellValue(asset.getSystemConfig());
            row.createCell(13).setCellValue(asset.getRemark());
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outputStream;
    }


}








