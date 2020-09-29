package com.tjetc.dao.impl;

import com.tjetc.dao.GoodsDao;
import com.tjetc.domain.Goods;
import com.tjetc.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GoodsDaoImpl implements GoodsDao {
    @Override
    public boolean addGoods(Goods goods) {
        Connection connection = DBUtil.getConnection();
        PreparedStatement pstmt = DBUtil.getPstmt(connection, "insert into goods(name,image,price) values(?,?,?)", goods.getName(), goods.getImage(), goods.getPrice());
        int i = DBUtil.update(pstmt);
        DBUtil.close(connection,pstmt,null);
        return i>0;
    }

    @Override
    public List<Goods> findAllGoods() {
        Connection connection = DBUtil.getConnection();
        PreparedStatement pstmt = DBUtil.getPstmt(connection, "select * from goods");
        ResultSet rs = DBUtil.query(pstmt);
        List<Goods> goodss=new ArrayList<>();
        try {
            while (rs.next()){
                goodss.add(new Goods(rs.getInt("id"),rs.getString("name"),rs.getString("image"),rs.getDouble("price")));
            }
            return goodss;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }finally {
            DBUtil.close(connection,pstmt,rs);
        }
    }
}
