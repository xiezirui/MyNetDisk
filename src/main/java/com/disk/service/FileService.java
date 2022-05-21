package com.disk.service;

import com.disk.pojo.File;
import org.apache.ibatis.annotations.Param;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

public interface FileService {
    int fileUpload(HashMap map);

    List<File> getFileByUserIdandName(String id,String name);

    List<File> getShareFile(String id);

    int deleteFile(String address);

    int updataFileState(String address,String state);
}
