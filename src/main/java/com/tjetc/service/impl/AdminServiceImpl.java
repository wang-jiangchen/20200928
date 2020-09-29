package com.tjetc.service.impl;

import com.tjetc.dao.AdminDao;
import com.tjetc.dao.impl.AdminDaoImpl;
import com.tjetc.domain.Admin;
import com.tjetc.service.AdminService;

public class AdminServiceImpl implements AdminService {
    private AdminDao dao ;
    public AdminServiceImpl() {
        this.dao = new AdminDaoImpl();
    }

    @Override
    public Admin login(String account, String password) {
        return this.dao.selectByNameAndPassword(account, password);
    }
}
