package com.sobey.exportExcel.model;

/**
 * Created by lijunhong on 17/3/29.
 * 素材信息:
 * 素材名
 * 时长
 * 导入时间
 */
public class MateData {

    private String materialName;
    private String duration;
    private String importDate;

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getImportDate() {
        return importDate;
    }

    public void setImportDate(String importDate) {
        this.importDate = importDate;
    }
}
