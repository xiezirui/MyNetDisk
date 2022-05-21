/**
* @author xiezirui
* @date 2022/4/10 8:09
*/

package com.disk.servlet;


import com.disk.pojo.Share;
import com.disk.pojo.User;
import com.disk.service.FileService;
import com.disk.service.FileServiceImpl;
import com.disk.service.ShareService;
import com.disk.service.ShareServiceImpl;
import com.disk.util.Constants;
import com.disk.util.FileDownloadUtil;
import com.disk.util.FileUploadUtil;
import com.disk.util.GetShareFileAddress;
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
import java.util.List;

import static com.disk.util.Constants.*;


public class FileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if (method.equals("download")){
            fileDownload(req,resp);
        }else if (method.equals("deleteFile")){
            deleteFile(req,resp);
        }else if (method.equals("showShareFile")){
            showShareFile(req,resp);
        }else if (method.equals("shareFile")){
            shareFile(req,resp);
        }else if (method.equals("gotoSetShareFilePwd")){
            gotoSetShareFilePwd(req,resp);
        }else if (method.equals("setSharePwd")){
            setSharePwd(req,resp);
        }else if (method.equals("fileInfo")){
            fileInfo(req,resp);
        }else if (method.equals("getShare")){
            getShare(req,resp);
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


        FileDownloadUtil.download(req,resp,address,name);



    }

    public void deleteFile(HttpServletRequest req, HttpServletResponse resp){
        String address = req.getParameter("address");
        String name = req.getParameter("name");

        String fileUrl = req.getSession().getServletContext().getRealPath("") + "WEB-INF\\upload\\" + address + "\\" + name;
        String fileUrlpar = req.getSession().getServletContext().getRealPath("") + "WEB-INF\\upload\\" + address;

        try {

            new File(fileUrl).delete();
            new File(fileUrlpar).delete();

            FileService fileService = new FileServiceImpl();
            ShareService shareService = new ShareServiceImpl();

            int i = fileService.deleteFile(address);
            shareService.deleteFile(address);

            resp.sendRedirect("/user.do?method=showFiles");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void showShareFile(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        FileServiceImpl fileService = new FileServiceImpl();

        User user = (User) req.getSession().getAttribute(USER_SESSION);

        List<com.disk.pojo.File> files = fileService.getShareFile(user.getId());

        for (com.disk.pojo.File file : files) {
            System.out.println(file);
        }

        req.setAttribute(Constants.files,files);

        req.getRequestDispatcher("/jsp/shareFile.jsp").forward(req,resp);
    }

    public void shareFile(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String address = req.getParameter("address");

        FileServiceImpl fileService = new FileServiceImpl();

        fileService.updataFileState(address,fileState_YES);

        resp.sendRedirect("/file.do?method=showShareFile");
    }

    public void gotoSetShareFilePwd(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        String address = req.getParameter("address");
        String uid = req.getParameter("uid");
        String name = req.getParameter("name");

        req.setAttribute(ADDRESS_PARAMETER,address);
        req.setAttribute(UID_PARAMETER,uid);
        req.setAttribute(FILENAME_PARAMETER,name);

        req.getRequestDispatcher("/jsp/setShareFilePwd.jsp").forward(req,resp);
    }

    public void setSharePwd(HttpServletRequest req,HttpServletResponse resp) throws IOException, ServletException {
        String address = req.getParameter(ADDRESS_PARAMETER);
        String uid = req.getParameter(UID_PARAMETER);
        String password = req.getParameter(PASSWORD_PARAMETER);
        String name = req.getParameter(FILENAME_PARAMETER);

        ShareServiceImpl shareService = new ShareServiceImpl();
        int isSuccess = shareService.setSharePwd(uid, address, password, name, GetShareFileAddress.getAddress());

        if (isSuccess == 1){
            shareFile(req,resp);
        }
    }

    public void fileInfo(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        String address = req.getParameter(ADDRESS_PARAMETER);

        ShareServiceImpl shareService = new ShareServiceImpl();

        List<Share> files = shareService.getFileInfo(address);

        req.setAttribute(shareFiles,files);

        req.getRequestDispatcher("/jsp/fileInfo.jsp").forward(req,resp);

    }

    public void getShare(HttpServletRequest req,HttpServletResponse resp){
        String address = req.getParameter("address");
        String password = req.getParameter("password");

        ShareServiceImpl shareService = new ShareServiceImpl();

        Share share = shareService.getShare(address, password);

        System.out.println(share);

        if (share != null){
            FileDownloadUtil.download(req,resp,share.getFileAddress(),share.getFileName());
        }if (share == null){
            System.out.println("wrong");
        }

    }
}

