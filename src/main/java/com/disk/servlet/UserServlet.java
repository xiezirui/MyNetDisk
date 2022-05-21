package com.disk.servlet;

import com.disk.pojo.File;
import com.disk.pojo.User;
import com.disk.service.FileService;
import com.disk.service.FileServiceImpl;
import com.disk.service.UserService;
import com.disk.service.UserServiceImpl;
import com.disk.util.Constants;
import org.omg.PortableInterceptor.USER_EXCEPTION;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static com.disk.util.Constants.*;

/**
*
* @author XZR
 * @date 2022/4/10 8:11
*
* */

public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if (method.equals("addUser")){
            logon(req,resp);
        }else if (method.equals("showFiles")){
            showFiles(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    public void showFiles(HttpServletRequest req, HttpServletResponse resp){
        String fileName = req.getParameter("fileSelectName");

        FileService fileService = new FileServiceImpl();

        User user = (User) req.getSession().getAttribute(USER_SESSION);

        if (user != null){
            List<File> files = fileService.getFileByUserIdandName(user.getId(),fileName);

            req.setAttribute(Constants.files,files);

            try {
                req.getRequestDispatcher("/jsp/homePage.jsp").forward(req,resp);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void logon(HttpServletRequest req, HttpServletResponse resp){
        System.out.println("================");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");

        UserService userService = new UserServiceImpl();
        HashMap map = userService.addUser(username, password, email);
        if (map.get("flag")!="0"){
            try {
                req.getRequestDispatcher("/jsp/homePage.jsp").forward(req,resp);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            req.getSession().setAttribute(USER_SESSION,new User(((String) map.get("uid")),username,password,email));
            System.out.println(req.getSession().getAttribute(USER_SESSION));
        }
    }
}
