/**
* @author xiezirui
* @date 2022/4/10 8:09
*/

package com.disk.servlet;


import com.disk.pojo.User;
import com.disk.service.FileService;
import com.disk.service.FileServiceImpl;
import com.disk.util.FileUploadUtil;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

import static com.disk.util.Constants.MESSAGE;
import static com.disk.util.Constants.USER_SESSION;


public class FileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if (method.equals("download")){
            fileDownload(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*
        * //判断表单类型
        if (!ServletFileUpload.isMultipartContent(req)){
            return;
        }*/

        //文件文件保持路径，建议再WEB-INF
        String uploadPath= this.getServletContext().getRealPath("/WEB-INF/upload");
        File uploadFile = new File(uploadPath);
        if (!uploadFile.exists()){
            uploadFile.mkdirs();
        }
        //调用已经封装好的方法

        DiskFileItemFactory factory = new DiskFileItemFactory(1024*1024,uploadFile);

        ServletFileUpload upload = FileUploadUtil.getservletFileUpload(factory);

        HashMap map = FileUploadUtil.uploadFile(req, upload, uploadPath);

        FileService fileService = new FileServiceImpl();

        System.out.println(map);

        int i = fileService.fileUpload(map);

        if (i > 0){
            resp.sendRedirect("/user.do?method=showFiles");
        }else {
            req.setAttribute(MESSAGE,"传输文件失败，请从试");
            req.getRequestDispatcher("/jsp/uploadFile.jsp").forward(req,resp);
        }
    }

    public void fileDownload(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String address = req.getParameter("address");
        String name = req.getParameter("name");




        //1.获取下载文件路径
        String fileUrl = req.getSession().getServletContext().getRealPath("") + "WEB-INF\\upload\\" + address + "\\" + name;

        //2.下载文件名
        String fileName = fileUrl.substring(fileUrl.lastIndexOf("\\") + 1);

        //3.让浏览器支持我们下载文件
        resp.setHeader("Content-Disposition","attachment;filename="+fileName);

        //4.下载文件输入流
        FileInputStream fileInputStream = new FileInputStream(fileUrl);

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

    }
}
