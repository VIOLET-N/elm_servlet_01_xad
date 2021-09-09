package com.neuedu.elm.controller;

import com.neuedu.elm.entity.User;
import com.neuedu.elm.service.UserService;
import com.neuedu.elm.service.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserController {
    private UserService service = new UserServiceImpl();

    // http://localhost:8080/elm/UserController/saveUser?userId=tom01&password=123&userName=tom&userSex=1&userImg=
    public Object saveUser(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String userId = request.getParameter("userId");
        String password = request.getParameter("password");
        String userName = request.getParameter("userName");
        String userSex = request.getParameter("userSex");
        int sex = 0;
        if (userSex != null && !userSex.equals("")){
            sex = Integer.parseInt(userSex);
        }
        String userImg = request.getParameter("userImg");
        User user = new User(userId, password, userName, sex, userImg, 0);
        return service.saveUser(user);
    }

    public Object getUserByIdByPass(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String userId = request.getParameter("userId");
        String password = request.getParameter("password");
        User user = service.getUserByIdByPass(userId, password);
        if (user != null){
            request.getSession().setAttribute("login_user", user);
        }
        return user;
    }

    public Object getUserById(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String userId = request.getParameter("userId");

        return service.getUserById(userId);
    }
}
