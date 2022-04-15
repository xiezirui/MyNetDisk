package com.disk.util;

import com.disk.pojo.User;
import org.apache.commons.fileupload.FileItem;;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import static com.disk.util.Constants.USER_SESSION;

public class FileUploadUtil {

    /*
    通过Apache的文件来获取流
    创建DiskFileItemFactory对象,处理文件上传路径和大小限制
    */
    public static DiskFileItemFactory getDiskFileItemFactory(File file){
        DiskFileItemFactory factory = new DiskFileItemFactory(1024*1024,file);
        return factory;
    }

    public static ServletFileUpload getservletFileUpload(DiskFileItemFactory factory){
        //获取ServletFileUpload
        ServletFileUpload fileUpload = new ServletFileUpload(factory);
        fileUpload.setHeaderEncoding("UTF-8");

        return fileUpload;

    }

    public static HashMap uploadFile(HttpServletRequest req, ServletFileUpload fileUpload, String uploadPath){

        HashMap<Object, Object> map = new HashMap<Object, Object>();

        int i = 0;

        try {
            List<FileItem> fileItems = fileUpload.parseRequest(req);

            for (FileItem fileItem : fileItems) {

                if (fileItem.isFormField()) {
                    String fieldName = fileItem.getFieldName();
                    String value = fileItem.getString();
                    System.out.println(fieldName + ":" + value);
                }else {
                    //判断是否为带有文件的表单
                    //fileItem每一个表单对象
                    //处理文件
                    String uploadFileName = fileItem.getName();
                    long fileSize = fileItem.getSize();

                    String fileName = uploadFileName.substring(uploadFileName.lastIndexOf("/") + 1);
                    String fileFormat = uploadFileName.substring(uploadFileName.lastIndexOf(".") + 1);

                    map.put("fileName",fileName);
                    map.put("fileFormat",fileFormat);
                    map.put("fileSize",ByteConversion.byteToOthers(fileSize));

                    //存放地址
                    String uuidPATH = UUID.randomUUID().toString();
                    String timePATH = String.valueOf(System.currentTimeMillis());
                    //文件真是存在的路径
                    String realPath = uploadPath + "/" + uuidPATH + timePATH;

                    User user = (User) req.getSession().getAttribute(USER_SESSION);

                    map.put("id",user.getId());
                    map.put("fileAddress",uuidPATH + timePATH);

                    //给每个文件创建文件夹
                    java.io.File realPathFile = new java.io.File(realPath);
                    if (!realPathFile.exists()){
                        realPathFile.mkdirs();
                    }

                    //文件传输
                    InputStream inputStream = fileItem.getInputStream();

                    FileOutputStream fileOutputStream = new FileOutputStream(realPath + "/" + fileName);

                    byte[] buffer = new byte[1024 * 1024];
                    int len = 0;
                    while ((len = inputStream.read(buffer)) > 0){
                        fileOutputStream.write(buffer,0,len);
                    }
                    //关闭流
                    fileOutputStream.close();

                    inputStream.close();

                    fileItem.delete();
                }


            }
            i = 1;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }






}
