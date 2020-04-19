package com.java.service.Impl;

import com.java.mapper.AdminMapper;
import com.java.po.Admin;
import com.java.po.Goods;
import com.java.po.Page;
import com.java.po.User;
import com.java.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("adminService")
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public List<User> getUserPageList(Page page) {
        return adminMapper.getUserPageList(page);
    }

    @Override
    public int delUser(Integer userId) {
        adminMapper.delGoodsByUserId(userId);
        adminMapper.delOrderByUserId(userId);
        adminMapper.delCartByUserId(userId);
        adminMapper.delCollectByUserId(userId);
        return adminMapper.delUser(userId);
    }

    @Override
    public int getUserPageCount(Page page) {
        return adminMapper.getUserPageCount(page);
    }

    @Override
    public Admin findAdminByName(String adminName) {
        return adminMapper.findAdminByName(adminName);
    }

}
