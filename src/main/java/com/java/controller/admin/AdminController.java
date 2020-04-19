package com.java.controller.admin;

import com.java.po.Goods;
import com.java.po.Page;
import com.java.po.ResultMap;
import com.java.po.User;
import com.java.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {


    @Autowired
    private AdminService adminService;

    /**
     * 管理员退出登录
     *
     * @param request
     * @return
     */
    @RequestMapping("adminOut")
    public String out(HttpServletRequest request) {
        request.getSession().removeAttribute("admin");
        return "index";
    }

    /**
     * 获取用户列表
     *
     * @return
     */
    //@ResponseBody
    //@RequestMapping(value = "/getUser")
    //public ResultMap<List<User>> getUser(Page page, @RequestParam("limit") int limit,
    //                                     HttpServletRequest request) {
    //    String search = request.getParameter("keyword");
    //    page.setRows(limit);
    //    if (search != null) {
    //        page.setKeyWord(search);
    //    }
    //    List<User> collectList = adminService.getUserPageList(page);
    //    int totals = adminService.getUserPageCount(page);
    //    page.setTotalRecord(totals);
    //    return new ResultMap<List<User>>("", collectList, 0, totals);
    //}



    /**
     * 删除用户     删除用户会同时删除该用户的其他记录
     *
     * @param request
     * @return
     */
    //@ResponseBody
    //@RequestMapping(value = "/delUser")
    //public Map<String, Object> delUser(@RequestParam Integer userId, HttpServletRequest request) {
    //    Map<String, Object> map = new HashMap<>();
    //    int result = adminService.delUser(userId);
    //    if (result > 0) {
    //        map.put("state", 1);
    //        map.put("msg", "删除成功");
    //        return map;
    //    } else {
    //        map.put("state", 0);
    //        map.put("msg", "删除失败");
    //        return map;
    //    }
    //}



	//
	//@Autowired
	//private StudentMapper studentMapper;
	//
	//@Autowired
	//private CollegeMapper collegeMapper;
	//
	//@Autowired
	//private InClassMapper inClassMapper;
	//
	//@Autowired
	//private LogMapper logMapper;
	//
	//// 构造器
	//private Student student = null;
	//private LogUtil logUtil = null;
	//private InClass inClass = null;
	//private College college = null;
	//
	//public AdminController() {
	//	logUtil = new LogUtil();
	//	inClass = new InClass();
	//	college = new College();
	//	student = new Student();
	//}
	//
	///**
	// * 分页查询数据 student
	// *
	// * @param page
	// * @param limit
	// * @param account
	// * @return
	// */
	//@RequestMapping(value = "/findstudent", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	//@ResponseBody
	//private Map<String, Object> listAll(@RequestParam("page") String page, @RequestParam("limit") String limit,
	//		@RequestParam(name = "username", defaultValue = "") String username,
	//		@RequestParam(name = "studentid", defaultValue = "") String studentid,
	//		@RequestParam(name = "inclass", defaultValue = "") String inclass,
	//		@RequestParam(name = "state", defaultValue = "") String state) {
	//	Map<String, Object> map = new HashMap<String, Object>();// 查询map
	//	int Count = studentMapper.selectCount(null);
	//	PageUtil<Student> pageUtil = new PageUtil<Student>(Integer.parseInt(page), Integer.parseInt(limit), Count);
	//	map.put("username", username.trim());// 去除空格传值
	//	map.put("studentid", studentid.trim());
	//	map.put("inclass", inclass.trim());// 去除空格传值
	//	map.put("state", state.trim());
	//	map.put("page", null);// 当前页数起始值
	//	map.put("limit", null);// 显示数量
	//	List<Student> num = studentMapper.findList(map);// 不分页查询结果
	//	map.put("page", pageUtil.getStartIndex());// 当前页数起始值
	//	map.put("limit", pageUtil.getPageSize());// 显示数量
	//	List<Student> data = studentMapper.findList(map);// 分页查询结果
	//	Map<String, Object> reqMap = new HashMap<String, Object>();// 返回map
	//	reqMap.put("code", 0);
	//	reqMap.put("msg", "");
	//	reqMap.put("count", num.size());// 显示总数目
	//	reqMap.put("data", data);// 显示结果
	//	return reqMap;
	//}
	//
	///**
	// * 添加student数据
	// *
	// * @param user
	// * @return
	// */
	//@RequestMapping(value = "/addstudent", method = RequestMethod.POST)
	//@ResponseBody
	//private Map<String, Object> addStudent(@RequestBody HashMap<String, String> hashMap) throws Exception {
	//	Map<String, Object> map = new HashMap<String, Object>();
	//	try {
	//		String username = hashMap.get("username");
	//		String className = hashMap.get("className");
	//		inClass.setClassName(className);
	//		InClass inClassOne = inClassMapper.selectOne(inClass);
	//		college.setId(inClassOne.getCollegeid());
	//		College collegeOne = collegeMapper.selectOne(college);
	//		Integer maxStudentId = studentMapper.MaxStudentId();
	//		student.setId(null);
	//		student.setStudentid(maxStudentId + 1);
	//		student.setUsername(username);
	//		student.setCollege(collegeOne.getCollegename());
	//		student.setInclass(className);
	//		student.setUpdatetime(new Date());
	//		student.setState(1);
	//		int result = studentMapper.insertSelective(student);
	//		if (result > 0) {
	//			map.put("status", 1);
	//			map.put("msg", "添加数据成功！");
	//			logMapper.insertLog("管理员", logUtil.getINSERT() + "name:" + username, logUtil.getSUCCESS());
	//		} else {
	//			map.put("status", 0);
	//			map.put("msg", "添加数据失败！");
	//			logMapper.insertLog("管理员", logUtil.getINSERT(), logUtil.getERROR());
	//		}
	//	} catch (Exception e) {
	//		map.put("status", 0);
	//		map.put("msg", "发送未知错误，请联系管理员！");
	//		logMapper.insertLog("管理员", logUtil.getINSERT(), logUtil.getERROR());
	//		e.printStackTrace();
	//	}
	//
	//	return map;
	//}
	//
	///**
	// * 搜索框显示班级
	// *
	// * @return
	// */
	//@RequestMapping(value = "/tree", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	//@ResponseBody
	//private Map<String, Object> addStudent() {
	//	Map<String, Object> map = new HashMap<String, Object>();
	//	List<InClass> listInClass = inClassMapper.select(null);
	//	map.put("csize", listInClass.size());
	//	String inclass = "";
	//	for (int i = 0; i < listInClass.size(); i++) {
	//		InClass inClass = listInClass.get(i);
	//		inclass += inClass.getClassName() + "+";
	//	}
	//	map.put("inclass", inclass);
	//	return map;
	//}
	//
	///**
	// * 删除一条student数据
	// *
	// * @param id
	// * @return
	// */
	//@RequestMapping(value = "/delS/{id}", method = RequestMethod.DELETE)
	//@ResponseBody
	//private Map<String, Object> deleteById(@PathVariable("id") Integer id) {
	//	int result = studentMapper.deleteByPrimaryKey(id);
	//	Map<String, Object> map = new HashMap<String, Object>();
	//	if (result > 0) {
	//		map.put("status", 1);
	//	} else {
	//		map.put("status", 0);
	//	}
	//	return map;
	//}
	//
	///**
	// * 批量删除student
	// *
	// * @param ids
	// * @return
	// */
	//@RequestMapping(value = "/student/ids", method = RequestMethod.DELETE)
	//@ResponseBody
	//private Map<String, Object> deleteByIds(@RequestParam("ids") String ids) {
	//	Map<String, Object> map = new HashMap<String, Object>();
	//	String a[] = ids.split(",");
	//	try {
	//		for (int i = 0; i < a.length; i++) {
	//			String id = a[i];
	//			studentMapper.deleteByPrimaryKey(Integer.parseInt(id));
	//		}
	//		map.put("status", 1);
	//	} catch (Exception e) {
	//		map.put("status", 0);
	//		e.printStackTrace();
	//	}
	//	return map;
	//}
	//
	///**
	// * 更新数据student
	// *
	// * @param user
	// * @return
	// * @throws Exception
	// */
	//@RequestMapping(value = "/updateStu", method = RequestMethod.PUT)
	//@ResponseBody
	//private Map<String, Object> update(@RequestBody Student student) throws Exception {
	//	Map<String, Object> map = new HashMap<String, Object>();
	//	// System.out.println(student);
	//	try {
	//		String username = student.getUsername();
	//		Integer studentid = student.getStudentid();
	//		String Inclass = student.getInclass();
	//		if (username.trim().equals("") || studentid == null) {
	//			map.put("status", 0);
	//			map.put("msg", "该用户姓名或学号不可存在空置！");
	//			return map;
	//		}
	//		Student s = new Student();
	//		Student stu1 = studentMapper.selectByPrimaryKey(student.getId());
	//		Student stu2 = new Student();
	//		stu2.setStudentid(student.getStudentid());
	//		Student stu3 = studentMapper.selectOne(stu2);
	//		if (stu3 != null) {
	//			if (stu3.getId() != stu1.getId()) {
	//				map.put("status", 0);
	//				map.put("msg", "学号已经存在，请换个学号！");
	//				return map;
	//			}
	//		}
	//		if (Inclass.trim().equals("")) {
	//			s.setInclass(null);
	//			student.setInclass(null);
	//		} else {
	//			inClass.setClassName(Inclass);
	//			InClass inClassOne = inClassMapper.selectOne(inClass);
	//			college = collegeMapper.selectByPrimaryKey(inClassOne.getCollegeid());
	//			s.setInclass(Inclass);
	//			s.setCollege(college.getCollegename());
	//			student.setCollege(college.getCollegename());
	//		}
	//		if (stu1.getReporttotime() == null) {
	//			s.setId(student.getId());
	//			s.setStudentid(studentid);
	//			s.setUsername(username);
	//			s.setUpdatetime(new Date());
	//			int i = studentMapper.updateByPrimaryKeySelective(s);
	//			if (i > 0) {
	//				map.put("status", 2);
	//				map.put("msg", "更新数据成功，该用户由于未报到，只更新了姓名，学号与班级！");
	//				logMapper.insertLog("管理员", logUtil.getUPDATE() + "name:" + username, logUtil.getSUCCESS());
	//				return map;
	//			} else {
	//				map.put("status", 0);
	//				map.put("msg", "更新失败！");
	//				return map;
	//			}
	//
	//		}
	//		String password = student.getPassword();
	//		Integer sex = student.getSex();
	//		String images = student.getImages();
	//		Date birthdate = student.getBirthdate();
	//		String phone = student.getPhone();
	//		String address = student.getAddress();
	//		String qq = student.getQq();
	//		Integer state = student.getState();
	//		if (username.trim().equals("") || studentid == null || password.trim().equals("") || sex == null
	//				|| images.trim().equals("") || birthdate == null || phone.trim().equals("")
	//				|| address.trim().equals("") || qq.trim().equals("") || state == null) {
	//			map.put("status", 0);
	//			map.put("msg", "请填写内容,不可存在空置！");
	//			return map;
	//		}
	//		if (MD5Util.md5Password(password).equals(stu1.getPassword())) {
	//			student.setPassword(null);
	//		}else {
	//			student.setPassword(MD5Util.md5Password(password));
	//		}
	//		student.setUpdatetime(new Date());
	//		int result = studentMapper.updateByPrimaryKeySelective(student);
	//		if (result > 0) {
	//			map.put("status", 1);
	//			logMapper.insertLog("管理员", logUtil.getUPDATE() + "name:" + username, logUtil.getSUCCESS());
	//		} else {
	//			map.put("status", 0);
	//			map.put("msg", "更新失败！");
	//			logMapper.insertLog("管理员", logUtil.getUPDATE() + "name:" + username, logUtil.getERROR());
	//			return map;
	//		}
	//
	//	} catch (Exception e) {
	//		map.put("status", 0);
	//		map.put("msg", "发送未知错误，请联系管理员！");
	//		logMapper.insertLog("管理员", logUtil.getUPDATE(), logUtil.getERROR());
	//		e.printStackTrace();
	//	}
	//	return map;
	//}

}
