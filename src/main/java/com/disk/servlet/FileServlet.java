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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import static com.disk.util.Constants.MESSAGE;
import static com.disk.util.Constants.USER_SESSION;


public class FileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
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



}
