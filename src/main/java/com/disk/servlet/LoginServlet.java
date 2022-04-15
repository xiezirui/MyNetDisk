package com.disk.servlet;

import com.disk.pojo.File;
import com.disk.pojo.User;
import com.disk.service.FileService;
import com.disk.service.FileServiceImpl;
import com.disk.service.UserService;
import com.disk.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.disk.util.Constants.MESSAGE;
import static com.disk.util.Constants.USER_SESSION;

/**
 *
 * @author XZR
 *
 * @date 2020-4-14
 *
 * */

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        System.out.println(email);
        System.out.println(password);

        UserService userService = new UserServiceImpl();

        User user = userService.getUser(email, password);

        if (user != null){
            req.getSession().setAttribute(USER_SESSION,user);
            try {
                resp.sendRedirect("/user.do?method=showFiles");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("fail");
            req.setAttribute(MESSAGE,"邮箱或密码错误");
            try {
                req.getRequestDispatcher("/login.jsp").forward(req,resp);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ServletException e) {
                e.printStackTrace();
            }
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
