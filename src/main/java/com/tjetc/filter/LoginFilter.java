package com.tjetc.filter;

import com.tjetc.domain.Admin;
import com.tjetc.util.DBUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebFilter(filterName = "loginFilter",urlPatterns = "/manager/*")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException { HttpServletRequest request=(HttpServletRequest)servletRequest;
        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin == null) {
            request.setAttribute("message", "对不起，您还没有登录，无法直接访问，请在登录后再进行操作！");
            request.getRequestDispatcher("../login.jsp").forward(request, servletResponse);
        } else if (admin != null && !("admin".equals(admin.getRole()))) {
            request.setAttribute("message", "对不起，您无权限访问！");
            request.getRequestDispatcher("../login.jsp").forward(request, servletResponse);
        }
        filterChain.doFilter(request,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
