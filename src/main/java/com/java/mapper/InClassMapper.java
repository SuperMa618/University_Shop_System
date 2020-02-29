package com.java.mapper;

import java.util.List;
import java.util.Map;

import com.github.abel533.mapper.Mapper;
import com.java.po.InClass;

public interface InClassMapper extends Mapper<InClass>{

	// 查询多条数据
	List<InClass> findList(Map<String, Object> queryMap);
}
