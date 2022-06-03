package com.disk.service;

import com.disk.dao.ShareMapper;
import com.disk.pojo.Share;
import com.disk.util.mybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class ShareServiceImpl implements ShareService {
    @Override
    public int setSharePwd(String uid, String address, String password, String name, String shareAddress) {
        SqlSession sqlSession = null;
        ShareMapper mapper = null;
        int i = 0;
        try {
            sqlSession = mybatisUtil.getSqlSession();

            mapper = sqlSession.getMapper(ShareMapper.class);

            i = mapper.setSharePwd(uid,address,password,name,shareAddress);

            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();

            sqlSession.rollback();
        }finally {
            sqlSession.close();
        }

        return i;
    }

    @Override
    public int deleteFile(String address) {
        SqlSession sqlSession = null;
        ShareMapper mapper = null;
        int i = 0;

        try {
            sqlSession = mybatisUtil.getSqlSession();

            mapper = sqlSession.getMapper(ShareMapper.class);

            i = mapper.deleteFile(address);

            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();

            sqlSession.rollback();
        }finally {
            sqlSession.close();
        }

        return i;
    }

    @Override
    public List<Share> getFileInfo(String address) {
        SqlSession sqlSession = null;
        ShareMapper mapper = null;
        List<Share> files = null;

        try {
            sqlSession = mybatisUtil.getSqlSession();

            mapper = sqlSession.getMapper(ShareMapper.class);

            files = mapper.getFileInfo(address);

        } catch (Exception e) {
            e.printStackTrace();;
        }finally {
            sqlSession.close();
        }

        return files;
    }

    @Override
    public Share getShare(String address, String password) {
        SqlSession sqlSession = mybatisUtil.getSqlSession();

        ShareMapper mapper = sqlSession.getMapper(ShareMapper.class);

        Share share = mapper.getShare(address,password);

        sqlSession.close();

        return share;
    }

    @Test
    public void test01(){
        System.out.println(getShare("--f1601c91-fb90-476c-92ab-f8708bb8d0401653099774495--","123456"));
    }

}
