package com.neuedu.elm.framework;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

// 前端控制类，接受所有请求
// 路径格式 /类名/方法名
@WebServlet(name = "DispatcherServlet", urlPatterns = "/")
public class DispatcherServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置字符集和内容类型
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");

        // 获取请求路径
        String path = request.getServletPath();
        String className = path.substring(1, path.lastIndexOf("/"));
        String methodName = path.substring(path.lastIndexOf("/") + 1);
        System.out.println("className: " + className);
        System.out.println("methodName: " + methodName);
        PrintWriter out = null;

        try {
            // 通过反射机制创建类对象
            Class<?> clazz = Class.forName("com.neuedu.elm.controller." + className);
            // 创建 controller 对象
            Object controller = clazz.newInstance();
            // 获取类对象中的方法
            Method method = clazz.getMethod(methodName, new Class[]{HttpServletRequest.class, HttpServletResponse.class});
            // 执行方法
            Object result = method.invoke(controller, new Object[]{request, response});

            // 把结果输出为 json 字符串
            out = response.getWriter();
            ObjectMapper mapper = new ObjectMapper();
            out.print(mapper.writeValueAsString(result));

        }catch (Exception e){
            System.out.println("|->DispatcherServlet 错误信息：请求url："+path);
            e.printStackTrace();
//            System.out.println("DispatcherServlet信息：类名："+className+"\t方法名："+methodName);
        }finally {
            if (out != null) {
                out.close();
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
