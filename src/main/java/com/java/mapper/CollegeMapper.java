package com.java.mapper;

import java.util.List;
import java.util.Map;

import com.github.abel533.mapper.Mapper;
import com.java.po.College;

public interface CollegeMapper extends Mapper<College> {

	// 查询多条数据
	List<College> findList(Map<String, Object> queryMap);
}
