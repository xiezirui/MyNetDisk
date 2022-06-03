package com.disk.util;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class FileDownloadUtil {
    public static void download(HttpServletRequest req, HttpServletResponse resp, String address, String name){
        try {
            //1.获取下载文件路径
            String fileUrl = req.getSession().getServletContext().getRealPath("") + "WEB-INF\\upload\\" + address + "\\" + name;

            //2.下载文件名
            String fileName = fileUrl.substring(fileUrl.lastIndexOf("\\") + 1);

            //3.让浏览器支持我们下载文件
            resp.setHeader("Content-Disposition","attachment;filename="+fileName);

            //4.下载文件输入流
            FileInputStream fileInputStream = null;

            fileInputStream = new FileInputStream(fileUrl);

            //5.缓冲区
            int len = 0;
            byte[] buffer = new byte[1024];

            //6.输出流
            ServletOutputStream outputStream = resp.getOutputStream();

            //7.FileOutputStream-->缓冲区   用输出流把文件输出到客户端
            while ((len = fileInputStream.read(buffer)) > 0){
                outputStream.write(buffer,0,len);
            }

            fileInputStream.close();
            outputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
