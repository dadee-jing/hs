package com.hs.platform.station.entity;

import java.util.Objects;


public class FTPReUploadInfo {
    String sourcePath;
    String targetPath;

    public FTPReUploadInfo(String sourcePath, String targetPath) {
        this.sourcePath = sourcePath;
        this.targetPath = targetPath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FTPReUploadInfo that = (FTPReUploadInfo) o;
        return Objects.equals(sourcePath, that.sourcePath) &&
                Objects.equals(targetPath, that.targetPath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sourcePath, targetPath);
    }

    public String getSourcePath() {
        return sourcePath;
    }

    public void setSourcePath(String sourcePath) {
        this.sourcePath = sourcePath;
    }

    public String getTargetPath() {
        return targetPath;
    }

    public void setTargetPath(String targetPath) {
        this.targetPath = targetPath;
    }
}
