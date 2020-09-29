package com.tjetc.servlet;

import com.tjetc.domain.Admin;
import com.tjetc.service.AdminService;
import com.tjetc.service.impl.AdminServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "adminServlet",urlPatterns = "/admin")
public class AdminServlet extends HttpServlet {
    private AdminService adminService=new AdminServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String op=req.getParameter("op");
        if ("login".equals(op)){
            this.login(req,resp);
        }else if("exit".equals(op)){
            this.exit(req,resp);
        }
    }

    private void exit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        session.removeAttribute("admin");
        resp.sendRedirect("manager/index.jsp");
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String account=req.getParameter("account");
        String password = req.getParameter("password");
        Admin admin = adminService.login(account, password);
        if (admin==null){
            req.setAttribute("message","对不起，登录失败，请重新登录");
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }else {
            HttpSession session = req.getSession();
            session.setMaxInactiveInterval(5);
            session.setAttribute("admin",admin);
            int num= (Integer)this.getServletContext().getAttribute("num");
            this.getServletContext().setAttribute("num",num+1);
            resp.sendRedirect("manager/index.jsp");
        }
    }
}
