package com.java.interceptor;

import com.java.po.Admin;
import com.java.po.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Mayj
 * @date 2020/4/13 8:24
 */
public class AdminInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取session
        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute("admin");
        //判断session中是否有用户数据，如果有，则返回true，继续向下执行
        if (admin != null) {
            return true;
        }
        //不符合条件的给出提示信息，并转发到登录页面
        //request.setAttribute("msg", "您还没有登录，请先登录！");
        request.getRequestDispatcher("/views/login.jsp").forward(request, response);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
