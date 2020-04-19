package com.java.controller.admin;

import com.java.po.*;
import com.java.service.AdCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/adComment")
public class AdCommentController {

    @Autowired
    private AdCommentService adCommentService;

    /**
     * 获取留言列表
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getComment")
    public ResultMap<List<CommentView>> getComment(Page page, @RequestParam("limit") int limit,
                                                   HttpServletRequest request) {
        String search = request.getParameter("keyword");
        page.setRows(limit);
        if (search != null) {
            page.setKeyWord(search);
        }
        List<CommentView> collectList = adCommentService.getCommentPageList(page);
        int totals = adCommentService.getCommentPageCount(page);
        page.setTotalRecord(totals);
        return new ResultMap<List<CommentView>>("", collectList, 0, totals);
    }


    /**
     * 删除商品 会同时删除与此商品相关的其他记录
     *
     * @param commentId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delComment")
    public Map<String, Object> delComment(@RequestParam Integer commentId) {
        Map<String, Object> map = new HashMap<>();
        int result = adCommentService.delCommentById(commentId);
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
