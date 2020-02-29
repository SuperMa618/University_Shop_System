package com.java.service.Impl;

import com.java.mapper.AdminMapper;
import com.java.po.Admin;
import com.java.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("adminService")
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin findAdminByName(String adminName) {
        return adminMapper.findAdminByName(adminName);
    }


}
