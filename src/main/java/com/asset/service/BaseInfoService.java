package com.asset.service;

import com.asset.model.Brand;
import com.asset.model.Department;
import com.asset.model.AssetCategory;
import java.util.List;

public interface BaseInfoService {
    List<Department> getAllDepartments();
    List<Brand> getAllBrands();
    List<AssetCategory> getAllAssetCategories();
}
