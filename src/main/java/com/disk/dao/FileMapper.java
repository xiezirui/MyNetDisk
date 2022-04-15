package com.disk.dao;

import com.disk.pojo.File;

import java.util.HashMap;
import java.util.List;

public interface FileMapper {
    int fileUpload(HashMap map);

    List<File> getFileByUserId(String id);
}
