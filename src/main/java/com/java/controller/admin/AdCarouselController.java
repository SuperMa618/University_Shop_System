package com.java.controller.admin;

import com.java.po.*;
import com.java.service.AdCarouselService;
import com.java.service.AdCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
@RequestMapping("/adCarousel")
public class AdCarouselController {

    @Autowired
    private AdCarouselService adCarouselService;

    /**
     * 获取轮播图列表
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getCarousel")
    public ResultMap<List<Carousel>> getCarousel(Page page, @RequestParam("limit") int limit,
                                                 HttpServletRequest request) {
        String search = request.getParameter("keyword");
        page.setRows(limit);
        if (search != null) {
            page.setKeyWord(search);
        }
        List<Carousel> collectList = adCarouselService.getCarouselPageList(page);
        int totals = adCarouselService.getCarouselPageCount(page);
        page.setTotalRecord(totals);
        return new ResultMap<List<Carousel>>("", collectList, 0, totals);
    }


    /**
     * 删除轮播图
     *
     * @param data
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/changeState", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public Map<String, Object> changeState(@RequestBody Map<String, String> data) {
        Map<String, Object> map = new HashMap<>();
        int result = adCarouselService.changeState(data);
        if (result > 0) {
            map.put("state", 1);
            map.put("msg", "修改成功");
            return map;
        } else {
            map.put("state", 0);
            map.put("msg", "修改失败");
            return map;
        }
    }


    /**
     * 删除轮播图
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delCarousel")
    public Map<String, Object> delCarousel(@RequestParam Integer id) {
        Map<String, Object> map = new HashMap<>();
        int result = adCarouselService.delCarouselById(id);
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

    /**
     * 添加轮播图
     *
     * @param data
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "addCarousel", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String, Object> register(@RequestBody Map<String, String> data,
                                        HttpServletRequest request) throws Exception {
        Map<String, Object> map = new HashMap<>();
        if (data.get("images") == "" || data.get("images") == null) {
            map.put("state", 0);
            map.put("msg", "请添加图片");
            return map;
        } else {
            Carousel carousel = new Carousel();
            carousel.setPicture(data.get("images"));
            carousel.setState("1");
            adCarouselService.addCarousel(carousel);
            map.put("state", 1);
            map.put("msg", "添加成功！");
            return map;
        }
    }

    /**
     * 图片上传
     *
     * @param file
     * @return
     */
    @ResponseBody
    @RequestMapping("upload")
    public Map upload(MultipartFile file) {
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
