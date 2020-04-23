package com.java.controller.goods;

import com.java.po.*;
import com.java.service.GoodService;
import com.java.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("review")
@Scope("prototype")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    /**
     * 用户查看审核商品
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getReview")
    public ResultMap<List<GoodsReview>> getReview(Page page, @RequestParam("limit") int limit,
                                                  HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        page.setRows(limit);
        page.setUserId(user.getId());
        List<GoodsReview> collectList = reviewService.getReviewPageList(page);
        int totals = reviewService.getReviewPageCount(page);
        page.setTotalRecord(totals);
        return new ResultMap<List<GoodsReview>>("", collectList, 0, totals);
    }


    /**
     * 用户删除审核的商品
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delReview")
    public Map<String, Object> collectDelete(@RequestParam String id, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        int result = reviewService.delReview(id);
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
