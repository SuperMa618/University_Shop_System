package com.java.controller.user;

import com.java.mapper.LogMapper;
import com.java.po.Admin;
import com.java.po.User;
import com.java.service.Impl.AdminServiceImpl;
import com.java.service.Impl.UserServiceImpl;
import com.java.util.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("user")
@Scope("prototype")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private AdminServiceImpl adminService;

    @Autowired
    private LogMapper logMapper;

    private User user = null;
    private LogUtil logUtil = null;

    public UserController() {
        user = new User();
        logUtil = new LogUtil();
    }

    /**
     * 用户管理员登陆
     *
     * @param data
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "login", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String, Object> login(@RequestBody Map<String, String> data,
                                     HttpServletRequest request) throws Exception {
        Map<String, Object> map = new HashMap<>();
        try {
            Object attribute = request.getSession().getAttribute("accountLoginCpacha");
            if (attribute == null) {
                map.put("state", 0);
                map.put("msg", "验证码过期，请刷新！");
                return map;
            }
            if (!data.get("code").equalsIgnoreCase(attribute.toString())) {
                map.put("state", 0);
                map.put("msg", "验证码错误！");
                return map;
            }
            char lv = data.get("logintype").charAt(1);
            if (lv == 'u') {
                user = userService.findUserByName(data.get("userName"));
                if (user !=null && user.getPassWord().equals(data.get("passWord"))) {
                    request.getSession().setAttribute("user", user);
                    map.put("state", 1);
                    map.put("msg", "登陆成功！");
                } else {
                    map.put("state", 0);
                    map.put("msg", "用户不存在,或者密码错误！");
                }
            } else {
                Admin admin = adminService.findAdminByName(data.get("userName"));
                if (admin != null && admin.getPassWord().equals(data.get("passWord"))) {
                    request.getSession().setAttribute("admin", admin);
                    map.put("state", 1);
                    map.put("msg", "登陆成功！");
                } else {
                    map.put("state", 0);
                    map.put("msg", "用户不存在,或者密码错误！");
                }
            }
        } catch (Exception e) {
            logMapper.insertLog("用户", logUtil.getLOGIN(), logUtil.getERROR());
            map.put("state", 0);
            map.put("msg", "发送未知错误，请联系管理员！");
            e.printStackTrace();
        }

        return map;
    }

    /**
     * 用户注册
     *
     * @param data
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "register", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String, Object> register(@RequestBody Map<String, String> data,
                                        HttpServletRequest request) throws Exception {
        Map<String, Object> map = new HashMap<>();
        try {
            Object attribute = request.getSession().getAttribute("accountLoginCpacha");
            if (attribute == null) {
                map.put("state", 0);
                map.put("msg", "验证码过期，请刷新！");
                return map;
            }
            if (!data.get("code").equalsIgnoreCase(attribute.toString())) {
                map.put("state", 0);
                map.put("msg", "验证码错误！");
                return map;
            }
            user = userService.findUserByName(data.get("userName"));
            if (user != null) {
                map.put("state", 0);
                map.put("msg", "用户名已存在！");
                return map;
            }
            if (!data.get("passWord").equals(data.get("rpassWord"))) {
                map.put("state", 0);
                map.put("msg", "两次密码不一致！");
                return map;
            }
            String img;
            if (data.get("images") == "" || data.get("images") == null) {
                img = "/Ushop-image/head/mrsptp202002021817.png";
            } else {
                img = data.get("images");
            }
            if (data.get("tel").length() == 6 || data.get("tel").length() == 11) {
                userService.saveUser(new User(data.get("userName"), data.get("passWord"),
                        data.get("tel"), img));
                user = userService.findUserByName(data.get("userName"));
                request.getSession().setAttribute("user", user);
                map.put("state", 1);
                map.put("msg", "注册成功！");
                return map;
            } else {
                map.put("state", 0);
                map.put("msg", "请输入11位号码或短号！");
                return map;
            }
        } catch (Exception e) {
            logMapper.insertLog("用户", logUtil.getLOGIN(), logUtil.getERROR());
            map.put("state", 0);
            map.put("msg", "发送未知错误，请联系管理员！");
            e.printStackTrace();
        }

        return map;
    }

    /**
     * 用户修改信息
     *
     * @param data
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "update", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String, Object> update(@RequestBody Map<String, String> data,
                                      HttpServletRequest request) throws Exception {
        Map<String, Object> map = new HashMap<>();
        try {
            Object attribute = request.getSession().getAttribute("accountLoginCpacha");
            if (attribute == null) {
                map.put("state", 0);
                map.put("msg", "验证码过期，请刷新！");
                return map;
            }
            if (!data.get("code").equalsIgnoreCase(attribute.toString())) {
                map.put("state", 0);
                map.put("msg", "验证码错误！");
                return map;
            }
            User oldUser = (User) request.getSession().getAttribute("user");
            user = userService.findUserByName(data.get("userName"));
            if (!data.get("opassWord").equals(oldUser.getPassWord())) {
                map.put("state", 0);
                map.put("msg", "原密码错误！");
                return map;
            }
            if (!data.get("npassWord").equals(data.get("rnpassWord"))) {
                map.put("state", 0);
                map.put("msg", "两次密码不一致！");
                return map;
            }
            //用户名是否存在
            if (oldUser.getUserName().equals(user.getUserName()) || user == null) {
                //号码是否正确
                if (data.get("tel").length() == 6 || data.get("tel").length() == 11) {
                    //图片是否更改
                    if (data.get("images") == null || data.get("images") == "") {
                        userService.updateUser(new User(oldUser.getId(),data.get("userName"), data.get("npassWord"),
                                data.get("tel"), oldUser.getHead()));
                    } else {
                        userService.updateUser(new User(oldUser.getId(),data.get("userName"), data.get("npassWord"),
                                data.get("tel"), data.get("images")));
                    }
                    user = userService.findUserByName(data.get("userName"));
                    request.getSession().setAttribute("user", user);
                    map.put("state", 1);
                    map.put("msg", "修改成功！");
                    return map;
                } else {
                    map.put("state", 0);
                    map.put("msg", "请输入11位号码或短号！");
                    return map;
                }
            } else {
                map.put("state", 0);
                map.put("msg", "用户名已存在！");
                return map;
            }

        } catch (Exception e) {
            logMapper.insertLog("用户", logUtil.getUPDATE(), logUtil.getERROR());
            map.put("state", 0);
            map.put("msg", "发送未知错误，请联系管理员！");
            e.printStackTrace();
        }

        return map;
    }

    /**
     * 找回密码
     * @param data
     * @return
     */
    @RequestMapping(value = "findpwd", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String,Object> findpwd(@RequestBody Map<String,String> data) {
        Map<String, Object> map = new HashMap<>();
        user = userService.findUserByName(data.get("userName"));
        if (user == null) {
            map.put("state", 0);
            map.put("msg", "用户名不存在！");
            return map;
        } else {
            if (user.getTel().equals(data.get("tel"))) {
                map.put("state", 1);
                map.put("msg", "密码："+user.getPassWord());
                return map;
            } else {
                map.put("state", 0);
                map.put("msg", "联系电话错误！");
                return map;
            }
        }
    }


    /**
     * 用户退出登录
     *
     * @param request
     * @return
     */
    @RequestMapping("out")
    public String out(HttpServletRequest request) {
        request.getSession().removeAttribute("user");
        return "index";
    }

    /**
     * 头像上传
     *
     * @param file
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("upload")
    public Map upload(MultipartFile file, HttpServletRequest request) {
        String prefix = "";
        String dateStr = "";
        //保存上传
        OutputStream out = null;
        InputStream fileInput = null;
        try {
            if (file != null) {
                String originalName = file.getOriginalFilename();
                prefix = originalName.substring(originalName.lastIndexOf(".") + 1);
                Date date = new Date();
                String uuid = UUID.randomUUID() + "";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                dateStr = simpleDateFormat.format(date);
                String filepath = "E:\\IDEAspace\\Graduation project\\Ushop-image\\head\\" + dateStr + "-" + uuid + "." + prefix;
                File files = new File(filepath);
                //打印查看上传路径
                System.out.println(filepath);
                if (!files.getParentFile().exists()) {
                    files.getParentFile().mkdirs();
                }
                file.transferTo(files);
                Map<String, Object> map2 = new HashMap<>();
                Map<String, Object> map = new HashMap<>();
                map.put("code", 0);
                map.put("msg", "");
                map.put("data", map2);
                map2.put("src", "/Ushop-image/head/" + dateStr + "-" + uuid + "." + prefix);
                return map;
            }

        } catch (Exception e) {
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (fileInput != null) {
                    fileInput.close();
                }
            } catch (IOException e) {
            }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("code", 1);
        map.put("msg", "");
        return map;
    }
}
