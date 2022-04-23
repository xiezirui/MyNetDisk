package com.disk.service;

import com.disk.pojo.File;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

public interface FileService {
    int fileUpload(HashMap map);

    List<File> getFileByUserIdandName(String id,String name);

    int deleteFile(String address);
}
