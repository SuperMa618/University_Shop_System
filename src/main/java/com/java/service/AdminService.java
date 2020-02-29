package com.java.service;

import com.java.po.Admin;
import com.java.po.User;

import java.util.List;

public interface AdminService {

    Admin findAdminByName(String adminName);
}
