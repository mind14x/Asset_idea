package com.asset.mapper;

import com.asset.model.Asset;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AssetMapper {
    //获取分页数据，以及分页数据对应的总数量
    List<Asset> listAssets(@Param("offset") int offset, @Param("size") int size, @Param("assetCode") String assetCode, @Param("category") String category, @Param("serialNumber") String serialNumber, @Param("department") String department, @Param("user") String user, @Param("remark") String remark);
    int countAssetsWithFilters(@Param("assetCode") String assetCode, @Param("category") String category, @Param("serialNumber") String serialNumber, @Param("department") String department, @Param("user") String user, @Param("remark") String remark);  // <-- 新增


    Asset findById(Long assetId);

    void update(Asset asset);


    void insertAsset(Asset asset);


    void deleteAssetById(Long assetId);

    //导出excel接口
    List<Asset> findAllAssets();

}
