package com.asset.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Asset {
    private long id;
    private String affiliation;  // 资产从属
    private String category;     // 资产类别
    private String assetCode;    // 资产编号
    private String status;       // 资产状态
    private String brand;        // 品牌
    private String model;        // 型号
    private String serialNumber; // 序列号
    private String department;   // 部门
    private String user;         // 使用人
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private Date entryDate;      // 录入时间
    private String computerName; // 电脑名称
    private String systemConfig; // 系统配置
    private String remark;       // 备注

    // Getter和Setter方法被省略

    // 你还可以为Asset类添加构造方法、hashCode()、equals()和toString()方法，来提高代码的完整性。


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAssetCode() {
        return assetCode;
    }

    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public String getComputerName() {
        return computerName;
    }

    public void setComputerName(String computerName) {
        this.computerName = computerName;
    }

    public String getSystemConfig() {
        return systemConfig;
    }

    public void setSystemConfig(String systemConfig) {
        this.systemConfig = systemConfig;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        StringBuilder sb = new StringBuilder("资产信息: {\n");

        if (affiliation != null && !affiliation.isEmpty()) sb.append("  资产从属: ").append(affiliation).append("\n");
        if (category != null && !category.isEmpty()) sb.append("  资产类别: ").append(category).append("\n");
        if (assetCode != null && !assetCode.isEmpty()) sb.append("  资产编号: ").append(assetCode).append("\n");
        if (status != null && !status.isEmpty()) sb.append("  资产状态: ").append(status).append("\n");
        if (brand != null && !brand.isEmpty()) sb.append("  品牌: ").append(brand).append("\n");
        if (model != null && !model.isEmpty()) sb.append("  型号: ").append(model).append("\n");
        if (serialNumber != null && !serialNumber.isEmpty()) sb.append("  序列号: ").append(serialNumber).append("\n");
        if (department != null && !department.isEmpty()) sb.append("  部门: ").append(department).append("\n");
        if (user != null && !user.isEmpty()) sb.append("  使用人: ").append(user).append("\n");
        if (entryDate != null) sb.append("  录入时间: ").append(sdf.format(entryDate)).append("\n");
        if (computerName != null && !computerName.isEmpty()) sb.append("  电脑名称: ").append(computerName).append("\n");
        if (systemConfig != null && !systemConfig.isEmpty()) sb.append("  系统配置: ").append(systemConfig).append("\n");
        if (remark != null && !remark.isEmpty()) sb.append("  备注: ").append(remark).append("\n");

        sb.append("}");
        return sb.toString();
    }


}
