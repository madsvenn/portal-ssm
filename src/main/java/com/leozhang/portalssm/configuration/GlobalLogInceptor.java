package com.leozhang.portalssm.configuration;

import com.alibaba.fastjson.JSONObject;
import com.leozhang.portalssm.entity.Log;
import com.leozhang.portalssm.entity.LogMap;
import com.leozhang.portalssm.entity.User;
import com.leozhang.portalssm.mapper.LogMapMapper;
import com.leozhang.portalssm.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.InetAddress;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GlobalLogInceptor extends HandlerInterceptorAdapter {


    public static Map<String,String> actionMap = new HashMap<String,String>();

    @Autowired
    private LogMapMapper logMapMapper;

    @Autowired
    private LogService logService;

    public Map<String,String> setLogMap(){
        List<LogMap> list = logMapMapper.selectByExample(null);
        list.forEach( log -> {
            actionMap.put(log.getUrl(),log.getAction());
        });
        return actionMap;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
        String url = request.getServletPath();
        String ip = getIpAddress(request);
        String method = request.getMethod();
        HttpSession session = request.getSession();
        Map<String, String[]> req = request.getParameterMap();
        String reqStr = JSONObject.toJSONString(req);
        User user = (User)session.getAttribute("userInfo");
        String action = setLogMap().get(url);
        Log log = new Log();
        log.setAction(action);
        log.setIp(ip);
        log.setMethod(method);
        log.setRequest(reqStr);
        log.setTime(new Date());
        log.setUrl(url);
        if(user!= null){
            log.setUserId(user.getId());
            log.setUsername(user.getUsername());
            log.setNickname(user.getNickname());
        }
//        System.out.println(logService);
        logService.insertLog(log);
        System.out.println("拦截器执行");

    }

    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");

        if(ip == null || ip.length() == 0 || "unknow".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");

        }

        if (ip == null || ip.length () == 0 || "unknown".equalsIgnoreCase (ip)) {
            ip = request.getHeader ("WL-Proxy-Client-IP");

        }

        if (ip == null || ip.length () == 0 || "unknown".equalsIgnoreCase (ip)) {
            ip = request.getRemoteAddr ();

            if (ip.equals ("127.0.0.1")) {
//根据网卡取本机配置的IP

                InetAddress inet = null;

                try {
                    inet = InetAddress.getLocalHost ();

                } catch (Exception e) {
                    e.printStackTrace ();

                }

                ip = inet.getHostAddress ();

            }

        }

// 多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割

        if (ip != null && ip.length () > 15) {
            if (ip.indexOf (",") > 0) {
                ip = ip.substring (0, ip.indexOf (","));

            }

        }

        return ip;
    }
}
