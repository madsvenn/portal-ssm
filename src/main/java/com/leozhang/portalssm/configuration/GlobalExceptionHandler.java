package com.leozhang.portalssm.configuration;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

@ControllerAdvice
public class GlobalExceptionHandler {

    public static String getStackTrace(Throwable throwable)
    {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        try
        {
            throwable.printStackTrace(pw);
            return sw.toString();
        } finally
        {
            pw.close();
        }
    }
    @ExceptionHandler(UnauthorizedException.class)
    public void handler(HttpServletRequest request, HttpServletResponse response,Exception e){
        try {
//            e.printStackTrace();
            request.setAttribute("msg","当前用户无权限访问该模块，具体原因如下");
            request.setAttribute("errorInfo",e.getMessage());
            request.setAttribute("stackInfo",getStackTrace(e));
            request.getRequestDispatcher("/error").forward(request,response);
        } catch (IOException | ServletException ioException) {
            ioException.printStackTrace();
        }
    }

}
