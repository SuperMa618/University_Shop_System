package com.java.mapper;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.java.po.Log;

public interface LogMapper extends Mapper<Log> {

	// 添加数据
	void insertLog(@Param("name") String name, @Param("operation") String operation, @Param("state") String state)
			throws Exception;

	// 删除数据
	int deleteLog(Integer id) throws Exception;
}
