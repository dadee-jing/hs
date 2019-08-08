package com.hs.platform.station.db;

import com.google.gson.Gson;
import com.hs.platform.station.entity.ImgEntity;
import com.hs.platform.station.util.Image64Util;

import java.sql.Timestamp;

public class ImageStorageService {


    public static void imageStorage(String json, String sequenceTag) {
        Gson gson = new Gson();
        ImgEntity imgEntity = gson.fromJson(json, ImgEntity.class);
        //存储Base64字符串为文件
        String imgBase64 = imgEntity.getFileData();
        String fileName = imgEntity.getFileName();
        Image64Util.generateImage(imgBase64, fileName);
        int WeightingId = imgEntity.getWeightingId();
        Timestamp WeightingDate = imgEntity.getWeightingDate();
        int FileType = imgEntity.getFileType();
        long FileSize = imgEntity.getFileSize();
        System.out.println("WeightingId : " + WeightingId);
        System.out.println("WeightingDate: " + WeightingDate);
        System.out.println("FileType: " + FileType);
        System.out.println("FileSize: " + FileSize);
    }
}
