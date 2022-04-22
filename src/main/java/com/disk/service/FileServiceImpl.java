/**
* @author xiezirui
* @date 2022/4/10 9:01
*/

package com.disk.service;

import com.disk.dao.FileMapper;
import com.disk.pojo.File;
import com.disk.util.mybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

public class FileServiceImpl implements FileService{
    @Override
    public int fileUpload(HashMap map) {
        SqlSession sqlSession = mybatisUtil.getSqlSession();
        FileMapper mapper = sqlSession.getMapper(FileMapper.class);

        int i = mapper.fileUpload(map);

        sqlSession.commit();
        sqlSession.close();

        return i;
    }

    @Override
    public List<File> getFileByUserId(String id) {
        SqlSession sqlSession = mybatisUtil.getSqlSession();
        FileMapper mapper = sqlSession.getMapper(FileMapper.class);

        List<File> files = mapper.getFileByUserId(id);

        sqlSession.close();

        return files;
    }

    @Override
    public int deleteFile(String address) {
        SqlSession sqlSession = mybatisUtil.getSqlSession();

        FileMapper mapper = sqlSession.getMapper(FileMapper.class);

        int i = 0;
        try {

            i = mapper.deleteFile(address);

            sqlSession.commit();
        }catch (Exception e){
            e.printStackTrace();

            sqlSession.rollback();
        }finally {
            sqlSession.close();
        }

        return i;
    }

    @Test
    public void test(){
        HashMap<Object, Object> map = new HashMap<Object, Object>();
        map.put("id","anseaocd1");
        map.put("fileName","adwddxa");
        map.put("fileAddress","qwertyusdfghj");
        int i = fileUpload(map);
        if (i > 0){
            System.out.println("success");
        }
    }

    @Test
    public void test2(){
        List<File> files = getFileByUserId("2f2a6be513ca49658c0d8b626db3d077");

        for (File file : files) {
            System.out.println(file);
        }
    }

    @Test
    public void test3(){
        FileServiceImpl fileService = new FileServiceImpl();

        int i = fileService.deleteFile("6d9f7ae3-baa8-483f-8bf3-daf52b0459a41650369028002");

        System.out.println();
    }

}
