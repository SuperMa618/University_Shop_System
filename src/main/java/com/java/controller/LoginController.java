package com.java.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/report")
@Scope("prototype")
public class LoginController {
	//
	//@Autowired
	//private StudentMapper studentMapper;
	//
	//@Autowired
	//private LogMapper logMapper;
	//
	//// 构造器
	//private Student student = null;
	//private LogUtil logUtil = null;
	//
	//public LoginController() {
	//	logUtil = new LogUtil();
	//	student = new Student();
	//}
	//
	///**
	// * 获取seission值
	// *
	// * @param request
	// * @return
	// */
	//@RequestMapping(value = "/findlogin", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	//@ResponseBody
	//public Map<String, Object> onSession(HttpServletRequest request) {
	//	Map<String, Object> map = new HashMap<String, Object>();
	//	Student student = (Student) request.getSession().getAttribute("student");
	//	map.put("student", student);
	//	return map;
	//}
	//
	///**
	// * 用户登陆
	// *
	// * @param username
	// * @param studentid
	// * @param password
	// * @param code
	// * @param logintype
	// * @param request
	// * @return
	// * @throws Exception
	// */
	//@RequestMapping(value = "/login", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	//@ResponseBody
	//private Map<String, Object> LoginTo(@RequestParam(name = "username", defaultValue = "") String username,
	//		@RequestParam(name = "studentid", defaultValue = "") String studentid,
	//		@RequestParam(name = "password", defaultValue = "") String password,
	//		@RequestParam(name = "code", defaultValue = "") String code,
	//		@RequestParam(name = "logintype", defaultValue = "") String logintype, HttpServletResponse response,
	//		HttpServletRequest request) throws Exception {
	//	Map<String, Object> map = new HashMap<String, Object>();
	//	try {
	//		Object attribute = request.getSession().getAttribute("accountLoginCpacha");
	//		if (attribute == null) {
	//			map.put("status", 0);
	//			map.put("msg", "验证码过期，请刷新！");
	//			return map;
	//		}
	//		if (!code.equalsIgnoreCase(attribute.toString())) {
	//			map.put("status", 0);
	//			map.put("msg", "验证码错误！");
	//			return map;
	//		}
	//		char type = logintype.charAt(1);
	//		if (type == 's') {// 学生登录
	//			if (studentid.trim().equals("") || studentid.equals("") || studentid == null) {
	//				map.put("status", 0);
	//				map.put("msg", "请正确输入学号！");
	//				return map;
	//			}
	//			student.setUsername(username);
	//			student.setStudentid(Integer.parseInt(studentid));
	//			student.setPassword(MD5Util.md5Password(password));
	//			Student selectOne = studentMapper.selectOne(student);
	//			if (selectOne != null) {
	//				map.put("status", 1);
	//				map.put("msg", "登陆成功！");
	//				request.getSession().setAttribute("student", selectOne);
	//				logMapper.insertLog(selectOne.getUsername(), logUtil.getLOGIN(), logUtil.getSUCCESS());
	//			} else {
	//				map.put("status", 0);
	//				map.put("msg", "用户不存在,或者密码错误！");
	//				return map;
	//			}
	//		} else {
	//			map.put("status", 1);
	//			map.put("msg", "登陆成功！");
	//		}
	//	} catch (Exception e) {
	//		logMapper.insertLog("用户", logUtil.getLOGIN(), logUtil.getERROR());
	//		map.put("status", 0);
	//		map.put("msg", "发送未知错误，请联系管理员！");
	//		e.printStackTrace();
	//	}
	//
	//	return map;
	//
	//}
	//
	///**
	// * 学生报到
	// *
	// * @param request
	// * @return
	// * @throws Exception
	// */
	//@RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	//@ResponseBody
	//private Map<String, Object> ReportTo(HttpServletRequest request) throws Exception {
	//	Map<String, Object> map = new HashMap<String, Object>();
	//	String username = request.getParameter("username");
	//	String studentid = request.getParameter("studentid");
	//	String code = request.getParameter("code");
	//	Object attribute = request.getSession().getAttribute("accountLoginCpacha");
	//	if (attribute == null) {
	//		map.put("status", 0);
	//		map.put("msg", "验证码过期，请刷新！");
	//		return map;
	//	}
	//	if (!code.equalsIgnoreCase(attribute.toString())) {
	//		map.put("status", 0);
	//		map.put("msg", "验证码错误！");
	//		return map;
	//	}
	//	Example example = new Example(Student.class);
	//	Criteria criteria = example.createCriteria();
	//	criteria.andEqualTo("username", username);
	//	criteria.andEqualTo("studentid", Integer.parseInt(studentid));
	//	int count = studentMapper.selectCountByExample(example);
	//	if (count == 1) {
	//		student.setStudentid(Integer.parseInt(studentid));
	//		Student selectOne = studentMapper.selectOne(student);
	//		if (selectOne.getPassword() == null) {
	//			map.put("status", 1);
	//			map.put("id", selectOne.getId());
	//			map.put("msg", "请完善资料！");
	//			logMapper.insertLog(selectOne.getUsername(), logUtil.getLOGIN() + "转" + logUtil.getREGISTER(),
	//					logUtil.getSUCCESS());
	//		} else {
	//			map.put("status", 2);
	//			map.put("msg", "你已经报到过，请使用密码登陆！");
	//			return map;
	//		}
	//	} else {
	//		map.put("status", 0);
	//		map.put("msg", "用户不存在！");
	//		return map;
	//	}
	//	return map;
	//}
	//
	///**
	// * 学生完善信息（更新数据）
	// *
	// * @param student
	// * @return
	// */
	//@RequestMapping(value = "/update", method = RequestMethod.PUT)
	//@ResponseBody
	//private Map<String, Object> update(@RequestBody Student student) throws Exception {
	//	Map<String, Object> map = new HashMap<String, Object>();
	//	try {
	//		student.setPassword(MD5Util.md5Password(student.getPassword()));
	//		student.setReporttotime(new Date());
	//		student.setUpdatetime(new Date());
	//		if (student.getPhone().length() != 11) {
	//			map.put("status", 0);
	//			map.put("msg", "输入手机号有误，请重新输入！");
	//			return map;
	//		}
	//		if (student.getQq().length() < 5) {
	//			map.put("status", 0);
	//			map.put("msg", "输入QQ号有误，请重新输入！");
	//			return map;
	//		}
	//		int result = studentMapper.updateByPrimaryKeySelective(student);
	//		if (result > 0) {
	//			map.put("status", 1);
	//			map.put("msg", "完善成功，请使用密码进行登录！");
	//			logMapper.insertLog(student.getUsername(), logUtil.getREGISTER(), logUtil.getSUCCESS());
	//		} else {
	//			map.put("status", 0);
	//			map.put("msg", "完善信息失败，请联系管理员！");
	//			return map;
	//		}
	//	} catch (Exception e) {
	//		logMapper.insertLog(student.getUsername(), logUtil.getREGISTER(), logUtil.getERROR());
	//		map.put("status", 0);
	//		map.put("msg", "发送未知错误，请联系管理员！");
	//		e.printStackTrace();
	//	}
	//	return map;
	//
	//}
	//
	///**
	// * 密码重置
	// *
	// * @return
	// * @throws Exception
	// */
	//@RequestMapping(value = "/uppwd", method = RequestMethod.PUT)
	//@ResponseBody
	//private Map<String, Object> upatepwd(@RequestBody HashMap<String, String> hashMap) throws Exception {
	//	Map<String, Object> map = new HashMap<String, Object>();
	//	EmailUtil emailUtil = new EmailUtil();
	//	logUtil.setDowhere("密码重置");
	//	try {
	//		String username = hashMap.get("username");
	//		String phone = hashMap.get("phone");
	//		String qq = hashMap.get("qq");
	//		String upType = hashMap.get("upType");
	//		System.out.println(qq);
	//		if (phone.length() != 11) {
	//			map.put("status", 0);
	//			map.put("msg", "输入手机号有误，请重新输入！");
	//			return map;
	//		}
	//		if (qq.length() < 5) {
	//			map.put("status", 0);
	//			map.put("msg", "输入QQ号有误，请重新输入！");
	//			return map;
	//		}
	//		if (upType.equals("s")) {
	//			student.setUsername(username);
	//			student.setPhone(phone);
	//			student.setQq(qq);
	//			Student selectOneS = studentMapper.selectOne(student);
	//			System.out.println(selectOneS);
	//			if (selectOneS != null) {
	//				String QQEmail = qq + "@qq.com";
	//				String newPwdS = emailUtil.newPwd();
	//				emailUtil.doSendHtmlEmail("新生报到系统重置密码", "您的密码重置为：" + emailUtil.text(newPwdS) + ",为账号安全尽快修改改密码。",
	//						QQEmail);
	//				selectOneS.setUpdatetime(new Date());
	//				selectOneS.setPassword(MD5Util.md5Password(newPwdS));
	//				studentMapper.updateByPrimaryKeySelective(selectOneS);
	//				map.put("status", 1);
	//				map.put("msg", "重置成功，请通过邮箱查收！");
	//				logMapper.insertLog(selectOneS.getUsername(), logUtil.getDowhere(), logUtil.getSUCCESS());
	//			} else {
	//				map.put("status", 0);
	//				map.put("msg", "用户不存在或其他信息填写有误，请重新输入！");
	//				return map;
	//			}
	//		} else {
	//			map.put("status", 1);
	//			map.put("msg", "成功！");
	//		}
	//	} catch (Exception e) {
	//		logMapper.insertLog("用户", logUtil.getDowhere(), logUtil.getERROR());
	//		map.put("status", 0);
	//		map.put("msg", "发送未知错误，请联系管理员！");
	//		e.printStackTrace();
	//	}
	//
	//	return map;
	//}

}
