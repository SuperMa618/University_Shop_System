package com.java.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.mapper.StudentMapper;
import com.java.po.Student;
import com.java.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentMapper studentMapper;

	@Override
	public List<Student> findListStudent(Map<String, Object> queryMap) throws Exception {
		// TODO Auto-generated method stub
		return studentMapper.findList(queryMap);
	}



}
