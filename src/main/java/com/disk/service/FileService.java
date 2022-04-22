package com.disk.service;

import com.disk.pojo.File;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

public interface FileService {
    int fileUpload(HashMap map);

    List<File> getFileByUserId(String id);

    int deleteFile(String address);
}
