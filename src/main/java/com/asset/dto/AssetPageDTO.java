package com.asset.dto;

import com.asset.model.Asset;
import java.util.List;

public class AssetPageDTO {
    private List<Asset> assets;
    private int total;

    public AssetPageDTO(List<Asset> assets, int total) {
        this.assets = assets;
        this.total = total;
    }

    // Getter和Setter方法被省略

    public List<Asset> getAssets() {
        return assets;
    }

    public void setAssets(List<Asset> assets) {
        this.assets = assets;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
