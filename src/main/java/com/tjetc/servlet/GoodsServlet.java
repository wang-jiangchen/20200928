package com.tjetc.servlet;

import com.tjetc.domain.Goods;
import com.tjetc.service.GoodsService;
import com.tjetc.service.impl.GoodsServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "goodsServlet",urlPatterns = "/goods")
@MultipartConfig
public class GoodsServlet extends HttpServlet {
    private GoodsService goodsService=new GoodsServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String op=req.getParameter("op");
        System.out.println(op);
        if ("add".equals(op)){
            this.add(req,resp);
        }
        else if ("findAll".equals(op)){
            this.findAll(req,resp);
        }
    }

    private void findAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Goods> allGoods = goodsService.findAllGoods();
        req.setAttribute("goodss",allGoods);
        req.getRequestDispatcher("manager/showAllGoods.jsp").forward(req,resp);
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String name = req.getParameter("name");
        String price = req.getParameter("price");
        Part part = req.getPart("image");
        String fileName = part.getSubmittedFileName();
        String newName= "/"+UUID.randomUUID().toString().replaceAll("-","").toUpperCase()
                +fileName.substring(fileName.lastIndexOf("."));
        String realPath = this.getServletContext().getRealPath("/upload");
        File file=new File(realPath);
        if (!file.exists()){
            file.mkdir();
        }
        part.write(realPath+newName);
        boolean b = goodsService.addGoods(new Goods(name, "/upload" + newName, Double.valueOf(price)));
        if (b){
            System.out.println("添加成功！");
        }else {
            System.out.println("添加失败！");
        }
        resp.sendRedirect("goods?op=findAll");
    }
}
