package com.java.mapper;

import com.java.po.Admin;
import com.java.po.Goods;
import com.java.po.Page;
import com.java.po.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminMapper {

	Admin findAdminByName(String adminName);

	List<User> getUserPageList(Page page);

	int getUserPageCount(Page page);

	int delUser(@Param("userId")Integer userId);

	void delGoodsByUserId(@Param("userId")Integer userId);

	void delOrderByUserId(@Param("userId")Integer userId);

	void delCartByUserId(@Param("userId")Integer userId);

	void delCollectByUserId(@Param("userId")Integer userId);
}
