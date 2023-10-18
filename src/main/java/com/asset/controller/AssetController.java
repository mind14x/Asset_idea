package com.asset.controller;

import com.asset.dto.AssetPageDTO;
import com.asset.model.Asset;
import com.asset.service.AssetService;
import com.asset.service.UserService;
import com.asset.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;

@RestController
@RequestMapping("/api/assets")
public class AssetController {

    @Autowired
    private AssetService assetService;

    @Autowired
    private UserService userService;

    // 用户通过该接口获取资产列表，并支持分页功能
    @GetMapping("/list")
    public ResponseEntity<AssetPageDTO> listAssets(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String assetCode,    // <-- 新增
            @RequestParam(required = false) String category,     // <-- 新增
            @RequestParam(required = false) String serialNumber, // <-- 新增
            @RequestParam(required = false) String department,   // <-- 新增
            @RequestParam(required = false) String user,         // <-- 新增
            @RequestParam(required = false) String remark) {     // <-- 新增
        AssetPageDTO assets = assetService.listAssets(page, size, assetCode, category, serialNumber, department, user, remark);
        System.out.println(1);
        System.out.println(2);
        return ResponseEntity.ok(assets);
    }


    // 获取资产详情
    @GetMapping("/{assetId}")
    public ResponseEntity<Asset> getAssetDetail(@PathVariable Long assetId) {
        Asset asset = assetService.getAssetDetail(assetId);
        return ResponseEntity.ok(asset);
    }

    // 修改资产详情
    @PutMapping("/{assetId}")
    public ResponseEntity<Void> updateAsset(@PathVariable Long assetId, @RequestBody Asset asset, @RequestHeader("Authorization") String token) {
        String userName = JwtUtil.getUserUsernameFromToken(token);

        if (!userService.isUserActive(userName)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        Asset old_asset = assetService.getAssetDetail(assetId);

        String assetDifferences = compareAssets(old_asset, asset);

        assetService.logOperation("修改", userName, assetDifferences);

        assetService.updateAsset(assetId, asset);

        return ResponseEntity.ok().build();
    }

    public String compareAssets(Asset oldAsset, Asset newAsset) {
        if (oldAsset == null || newAsset == null) {
            throw new IllegalArgumentException("Both assets must be non-null");
        }

        StringBuilder report = new StringBuilder();

        // 添加"操作动作：修改"到报告开始
        report.append("操作动作：修改\n");

        report.append("原资产:\n");
        report.append(oldAsset.toString()).append("\n");

        report.append("现资产:\n");
        report.append(newAsset.toString()).append("\n");

        StringBuilder differences = new StringBuilder();
        compareFields(differences, "资产从属", oldAsset.getAffiliation(), newAsset.getAffiliation());
        compareFields(differences, "资产类别", oldAsset.getCategory(), newAsset.getCategory());
        compareFields(differences, "资产编号", oldAsset.getAssetCode(), newAsset.getAssetCode());
        compareFields(differences, "资产状态", oldAsset.getStatus(), newAsset.getStatus());
        compareFields(differences, "品牌", oldAsset.getBrand(), newAsset.getBrand());
        compareFields(differences, "型号", oldAsset.getModel(), newAsset.getModel());
        compareFields(differences, "序列号", oldAsset.getSerialNumber(), newAsset.getSerialNumber());
        compareFields(differences, "部门", oldAsset.getDepartment(), newAsset.getDepartment());
        compareFields(differences, "使用人", oldAsset.getUser(), newAsset.getUser());
        if (oldAsset.getEntryDate() != null && newAsset.getEntryDate() != null && !oldAsset.getEntryDate().equals(newAsset.getEntryDate())) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            differences.append("录入时间: ").append(sdf.format(oldAsset.getEntryDate())).append(" -> ").append(sdf.format(newAsset.getEntryDate())).append("\n");
        }
        compareFields(differences, "电脑名称", oldAsset.getComputerName(), newAsset.getComputerName());
        compareFields(differences, "系统配置", oldAsset.getSystemConfig(), newAsset.getSystemConfig());
        compareFields(differences, "备注", oldAsset.getRemark(), newAsset.getRemark());

        if (differences.length() > 0) {
            report.append("资产修改详细:\n");
            report.append(differences.toString());
        } else {
            report.append("没有差异。");
        }

        return report.toString();
    }

    private void compareFields(StringBuilder differences, String label, Object oldValue, Object newValue) {
        if (oldValue == null && newValue == null) return;
        if (oldValue == null || newValue == null || !oldValue.equals(newValue)) {
            differences.append(label).append(": ").append(oldValue).append(" -> ").append(newValue).append("\n");
        }
    }


    //资产登记
    @PostMapping("/register")
    public ResponseEntity<?> registerAsset(@RequestBody Asset asset, @RequestHeader("Authorization") String token) {
        String userName = JwtUtil.getUserUsernameFromToken(token);

        System.out.println(userName);
        // 检查用户是否存在及其状态
        if (!userService.isUserActive(userName)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // 返回403 Forbidden或其他适当的状态ssssss


        }

        //资产登记
        assetService.registerAsset(asset);
        // 日志记录。使用StringBuilder来构建日志内容。
        StringBuilder logContent = new StringBuilder();
        logContent.append("操作动作: 新增\n");
        logContent.append(asset.toString());

        assetService.logOperation("新增", userName, logContent.toString());
        return ResponseEntity.ok("Asset registered successfully!");
    }



    // 删除资产
    @DeleteMapping("/{assetId}")
    public ResponseEntity<Void> deleteAsset(@PathVariable Long assetId, @RequestHeader("Authorization") String token) {
        String userName = JwtUtil.getUserUsernameFromToken(token);
        // 检查用户是否存在及其状态
        if (!userService.isUserActive(userName)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // 返回403 Forbidden或其他适当的状态
        }


        //根据id获取资产对象
        Asset asset = assetService.getAssetDetail(assetId);
        //日志记录——————>要先记录日志，再删除啊！！！！
        StringBuilder logContent = new StringBuilder();
        logContent.append("操作动作: 删除\n");
        logContent.append(asset.toString());

        assetService.logOperation("删除", userName, logContent.toString());
        //资产删除
        assetService.deleteAsset(assetId);

        return ResponseEntity.ok().build();
    }


    @GetMapping("/export")
    public ResponseEntity<ByteArrayResource> exportToExcel() {
        ByteArrayOutputStream outputStream = assetService.exportAssetsToExcel();
        ByteArrayResource resource = new ByteArrayResource(outputStream.toByteArray());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=assets.xlsx")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }


}

















