package com.asset.controller;

import com.asset.model.Brand;
import com.asset.model.Department;
import com.asset.model.AssetCategory;
import com.asset.service.BaseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/base-info")
public class BaseInfoController {

    @Autowired
    private BaseInfoService baseInfoService;

    @GetMapping("/departments")
    public List<Department> getAllDepartments() {
        return baseInfoService.getAllDepartments();
    }

    @GetMapping("/brands")
    public List<Brand> getAllBrands() {
        return baseInfoService.getAllBrands();
    }

    @GetMapping("/asset-categories")
    public List<AssetCategory> getAllAssetCategories() {
        return baseInfoService.getAllAssetCategories();
    }
}
