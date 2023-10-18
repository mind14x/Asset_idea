package com.asset.service;

import com.asset.dto.AssetPageDTO;
import com.asset.model.Asset;

import java.io.ByteArrayOutputStream;
import java.util.List;

public interface AssetService {
    //资产分页查询
    AssetPageDTO listAssets(int page, int size, String assetCode, String category, String serialNumber, String department, String user, String remark);

    Asset getAssetDetail(Long assetId);

    void updateAsset(long assetId, Asset asset);

    void registerAsset(Asset asset);

    void deleteAsset(Long assetId);

    void logOperation(String operationType, String operationUser, String operationDetail);

    //导出excel接口
    List<Asset> getAllAssets();
    ByteArrayOutputStream exportAssetsToExcel();

}