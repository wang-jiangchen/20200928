package com.tjetc.listener;

import com.tjetc.util.DBUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebListener
public class VisitCountListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("服务器启动，context对象被创建，开始运行......");
        Connection conn= DBUtil.getConnection();
        PreparedStatement pstmt = DBUtil.getPstmt(conn, "select num from count");
        ResultSet rs = DBUtil.query(pstmt);
        int num=0;
        try {
            if (rs.next()){
                num=rs.getInt("num");
            }
            ServletContext sc = sce.getServletContext();
            sc.setAttribute("num",num);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(conn,pstmt,rs);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("服务器关闭，context对象正在被销毁......");
        Connection conn = DBUtil.getConnection();
        PreparedStatement pstmt = DBUtil.getPstmt(conn, "update count set num=?", sce.getServletContext().getAttribute("num"));
        DBUtil.update(pstmt);
        DBUtil.close(conn,pstmt,null);
    }
}
