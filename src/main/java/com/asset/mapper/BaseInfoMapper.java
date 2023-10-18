package com.asset.mapper;

import com.asset.model.Brand;
import com.asset.model.Department;
import com.asset.model.AssetCategory;
import java.util.List;

public interface BaseInfoMapper {
    List<Department> findAllDepartments();
    List<Brand> findAllBrands();
    List<AssetCategory> findAllAssetCategories();
}