package com.java.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.java.po.Admin;

public interface AdminMapper {

	// 添加一条数据
	int insertAdmin(Admin admin) throws Exception;

	// 修改一条数据
	int updateAdmin(Admin admin) throws Exception;

	// 删除一条数据
	int deleteAdmin(int id) throws Exception;

	// 查询一条数据
	Admin findByuserNameAndAdminId(@Param("id") Integer id,@Param("username") String username, @Param("password") String password);

	// 查询多条数据
	public List<Admin> findListAdmin(Map<String, Object> queryMap);

	Admin findAdminByName(String adminName);
}
