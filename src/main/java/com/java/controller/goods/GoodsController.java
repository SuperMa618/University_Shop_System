package com.java.controller.goods;

import com.java.mapper.LogMapper;
import com.java.po.Goods;
import com.java.po.User;
import com.java.service.GoodService;
import com.java.util.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("goods")
@Scope("prototype")
public class GoodsController {

    @Autowired
    private GoodService goodService;

    @Autowired
    private LogMapper logMapper;

    private Goods goods = null;
    private LogUtil logUtil = null;

    public GoodsController() {
        logUtil = new LogUtil();
        goods = new Goods();
    }

    /**
     * 商品搜索
     * @param data
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/search",method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public Map<String, Object> goodsSearch(@RequestBody Map<String, String> data,
                                           HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        if (request.getSession().getAttribute("user") == null) {
            map.put("state", 0);
            map.put("msg", "未登录");
        } else {
            Map<String, Object> maps = new HashMap<>();
            maps.put("goodsName", data.get("search"));
            maps.put("type", null);
            List<Goods> goodsList=goodService.findGoods(maps);
            request.getSession().setAttribute("goodsList", goodsList);
            map.put("state", 1);
        }
        return map;
    }


    @ResponseBody
    @RequestMapping(value = "/detail",method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public Map<String, Object> goodsDetail(@RequestBody Map<String, String> data,
                                           HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        if (request.getSession().getAttribute("user") == null) {
            map.put("state", 0);
            map.put("msg", "未登录");
        } else {
            Map<String, Object> maps = new HashMap<>();
            maps.put("goodsName", null);
            maps.put("type", null);
            maps.put("id", data.get("goodsId"));
            List<Goods> goodsList=goodService.findGoods(maps);
            if (request.getSession().getAttribute("goods") != null) {
                request.getSession().removeAttribute("goods");
            }
            request.getSession().setAttribute("goods", goodsList.get(0));
            map.put("state", 1);
        }
        return map;
    }



    /**
     * 商品种类
     * @param type
     * @param model
     * @return
     */
    @RequestMapping(value = "/type",
            method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String goodsType(@RequestParam(value = "type") String type, ModelMap model) {
        Map<String, Object> map = new HashMap<>();
        map.put("goodsName", null);
        map.put("type", type);
        List<Goods> goodsList=goodService.findGoods(map);
        model.addAttribute("goodsList", goodsList);
        return "goods/index";
    }

    /**
     * 用户提交商品
     *
     * @param data
     * @param request
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "submitGoods", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public Map<String, Object> submitGoods(@RequestBody Map<String, String> data,
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
            User user = (User) request.getSession().getAttribute("user");
            String dateStr = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            String img;
            if (data.get("images") == "" || data.get("images") == null) {
                img = "/Ushop-image/goods/mrtp_2020-3-3_9-1-51.png";
            } else {
                img = data.get("images");
            }
            goods = new Goods(user.getId(), data.get("goodsName"),
                    data.get("price"), data.get("type"),
                    data.get("describe"), img, dateStr);

            goodService.saveGoods(goods);
            map.put("state", 1);
            map.put("msg", "提交成功，等待审核！");

        } catch (Exception e) {
            //logMapper.insertLog("用户", logUtil.getLOGIN(), logUtil.getERROR());
            map.put("state", 0);
            map.put("msg", "发送未知错误，请联系管理员！");
            e.printStackTrace();
        }
        return map;
    }


    /**
     * 商品图片上传
     *
     * @param file
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("upload")
    public Map<String, Object> upload(MultipartFile file, HttpServletRequest request) {
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
                String filepath = "E:\\IDEAspace\\Graduation project\\Ushop-image\\goods\\" + dateStr + "-" + uuid + "." + prefix;
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
                map2.put("src", "/Ushop-image/goods/" + dateStr + "-" + uuid + "." + prefix);
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
