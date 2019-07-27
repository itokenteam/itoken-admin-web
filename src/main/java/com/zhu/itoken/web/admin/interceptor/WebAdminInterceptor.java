package com.zhu.itoken.web.admin.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.zhu.itoken.common.domain.User;
import com.zhu.itoken.common.dto.CookieUtils;
import com.zhu.itoken.common.web.intercepter.utils.HttpServerletUtils;
import com.zhu.itoken.web.admin.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebAdminInterceptor implements HandlerInterceptor {

    @Autowired
    RedisService redisService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = CookieUtils.getCookieValue(request, "token");
        if (StringUtils.isEmpty(token)) {
            response.sendRedirect(String.format("http://localhost:9001/login?url=%s", HttpServerletUtils.getFullPath(request)));
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        User user = (User) request.getSession().getAttribute("user");
        //已登录
        if (user != null) {
            if (modelAndView != null) {
                modelAndView.addObject("user", user);
            }
        } else {//未登录
            String token = CookieUtils.getCookieValue(request, "token");
            if (token != null) {
                String s = redisService.get(token);
                if (s != null) {
                    String s1 = redisService.get(s);
                    User user1 = JSONObject.parseObject(s1, User.class);
                    if (modelAndView != null) {
                        modelAndView.addObject("user", user);
                    }
                    request.getSession().setAttribute("user", user1);
                }
            }
        }

        //二次确认是不是登录
        if (user != null) {
            if (modelAndView != null) {
                modelAndView.addObject("user", user);
            }else{
                response.sendRedirect("http://localhost:9001/login?url=http://localhost:8280");
            }
        }
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
