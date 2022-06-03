package com.disk.service;

import com.disk.pojo.Share;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShareService {
    int setSharePwd(String uid, String address, String password, String name, String shareAddress);

    int deleteFile(String address);

    List<Share> getFileInfo(String address);

    Share getShare(String address, String password);
}
