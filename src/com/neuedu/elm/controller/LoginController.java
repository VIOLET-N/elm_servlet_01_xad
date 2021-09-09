package com.neuedu.elm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginController {
    // http://localhost:8080/elm/LoginController/login?uname=tom
    public Object login(HttpServletRequest request, HttpServletResponse response){
        String uname = request.getParameter("uname");

        return "haha " +  uname;
    }
}
