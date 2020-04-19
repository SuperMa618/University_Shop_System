package com.java.service;

import com.java.po.Admin;
import com.java.po.Goods;
import com.java.po.Page;
import com.java.po.User;

import java.util.List;

public interface AdminService {

    Admin findAdminByName(String adminName);

    List<User> getUserPageList(Page page);

    int getUserPageCount(Page page);

    int delUser(Integer userId);
}
