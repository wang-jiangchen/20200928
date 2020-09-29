package com.tjetc.dao;

import com.tjetc.domain.Admin;

public interface AdminDao {
    Admin selectByNameAndPassword(String account, String password);
}
