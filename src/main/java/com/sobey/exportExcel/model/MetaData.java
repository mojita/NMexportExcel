package com.sobey.exportExcel.model;

/**
 * Created by lijunhong on 17/3/29.
 * 素材信息:
 * 素材名
 * 时长
 * 导入时间
 */
public class MetaData {

    private String name;
    private String duration;
    private String importDate;

    public MetaData() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
