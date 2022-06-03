package com.disk.dao;

import com.disk.pojo.Share;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface ShareMapper {
    int setSharePwd(@Param("uid") String uid,@Param("address") String address,@Param("password") String password, @Param("name") String nam, @Param("shareAddress") String shareAddress);

    int deleteFile(String address);

    List<Share> getFileInfo(String address);

    Share getShare(@Param("address") String address, @Param("password") String password);
}
