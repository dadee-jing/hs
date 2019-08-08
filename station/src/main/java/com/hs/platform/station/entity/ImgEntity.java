package com.hs.platform.station.entity;

import java.sql.Timestamp;

public class ImgEntity {
    public int getWeightingId() {
        return WeightingId;
    }

    public void setWeightingId(int weightingId) {
        WeightingId = weightingId;
    }

    public Timestamp getWeightingDate() {
        return WeightingDate;
    }

    public void setWeightingDate(Timestamp weightingDate) {
        WeightingDate = weightingDate;
    }

    public int getFileType() {
        return FileType;
    }

    public void setFileType(int fileType) {
        FileType = fileType;
    }

    public long getFileSize() {
        return FileSize;
    }

    public void setFileSize(long fileSize) {
        FileSize = fileSize;
    }

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String fileName) {
        FileName = fileName;
    }

    public String getFileData() {
        return FileData;
    }

    public void setFileData(String fileData) {
        FileData = fileData;
    }

    private int WeightingId;
    private Timestamp WeightingDate;
    private int FileType;
    private long FileSize;
    private String FileName;
    private String FileData;

}
