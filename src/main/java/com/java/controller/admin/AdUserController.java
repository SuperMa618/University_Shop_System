package com.java.controller.admin;

import com.java.po.Page;
import com.java.po.ResultMap;
import com.java.po.User;
import com.java.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/adUser")
public class AdUserController {


    @Autowired
    private AdminService adminService;


    /**
     * 获取用户列表
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getUser")
    public ResultMap<List<User>> getUser(Page page, @RequestParam("limit") int limit,
                                         HttpServletRequest request) {
        String search = request.getParameter("keyword");
        page.setRows(limit);
        if (search != null) {
            page.setKeyWord(search);
        }
        List<User> collectList = adminService.getUserPageList(page);
        int totals = adminService.getUserPageCount(page);
        page.setTotalRecord(totals);
        return new ResultMap<List<User>>("", collectList, 0, totals);
    }



    /**
     * 删除用户     删除用户会同时删除该用户的其他记录
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delUser")
    public Map<String, Object> delUser(@RequestParam Integer userId) {
        Map<String, Object> map = new HashMap<>();
        int result = adminService.delUser(userId);
        if (result > 0) {
            map.put("state", 1);
            map.put("msg", "删除成功");
            return map;
        } else {
            map.put("state", 0);
            map.put("msg", "删除失败");
            return map;
        }
    }

}
