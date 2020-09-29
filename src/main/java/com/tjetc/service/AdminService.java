package com.tjetc.service;

import com.tjetc.domain.Admin;

public interface AdminService {
    Admin login(String account, String password);
}
