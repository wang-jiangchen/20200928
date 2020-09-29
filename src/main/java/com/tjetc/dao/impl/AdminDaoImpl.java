package com.tjetc.dao.impl;

import com.tjetc.dao.AdminDao;
import com.tjetc.domain.Admin;
import com.tjetc.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDaoImpl implements AdminDao {
    @Override
    public Admin selectByNameAndPassword(String account, String password) {
        Connection conn = DBUtil.getConnection();
        PreparedStatement pstmt = DBUtil.getPstmt(conn, "select * from admin where account=? and password =?",
                account, password);
        ResultSet rs = DBUtil.query(pstmt);
        try {
            if (rs.next()) {
                return new Admin(rs.getInt("id"),
                        rs.getString("account"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("role"));
            }
            return null;
        } catch(SQLException e){
            e.printStackTrace();
            return null;
        }finally{
            DBUtil.close(conn, pstmt, rs);
        }
    }
}
