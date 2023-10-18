package com.asset.service.impl;

import com.asset.mapper.BaseInfoMapper;
import com.asset.model.Brand;
import com.asset.model.Department;
import com.asset.model.AssetCategory;
import com.asset.service.BaseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BaseInfoServiceImpl implements BaseInfoService {

    @Autowired
    private BaseInfoMapper baseInfoMapper;

    @Override
    public List<Department> getAllDepartments() {
        return baseInfoMapper.findAllDepartments();
    }

    @Override
    public List<Brand> getAllBrands() {
        return baseInfoMapper.findAllBrands();
    }

    @Override
    public List<AssetCategory> getAllAssetCategories() {
        return baseInfoMapper.findAllAssetCategories();
    }
}
