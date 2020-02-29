package com.java.mapper;

import java.util.List;
import java.util.Map;

import com.github.abel533.mapper.Mapper;
import com.java.po.Student;

public interface StudentMapper extends Mapper<Student>{

	// 查询多条数据
	List<Student> findList(Map<String, Object> queryMap);
	
	// 查询数据的最大值
	Integer MaxStudentId();
	
	
}
