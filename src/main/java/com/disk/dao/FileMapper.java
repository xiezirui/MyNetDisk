package com.disk.dao;

import com.disk.pojo.File;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface FileMapper {
    int fileUpload(HashMap map);

    List<File> getFileByUserIdandName(@Param("id") String id, @Param("name") String name);

    int deleteFile(String address);
}
