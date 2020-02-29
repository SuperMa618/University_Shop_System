package com.java.service;

import java.util.List;
import java.util.Map;

import com.java.po.Student;

public interface StudentService {

	/**
	 * 查询多条学生数据
	 * 
	 * @param queryMap
	 * @return
	 * @throws Exception
	 */
	List<Student> findListStudent(Map<String, Object> queryMap) throws Exception;

}
