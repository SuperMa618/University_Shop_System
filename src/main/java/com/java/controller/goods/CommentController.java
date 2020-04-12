package com.java.controller.goods;

import com.java.po.*;
import com.java.service.CommentService;
import com.java.service.GoodService;
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
@RequestMapping("comment")
@Scope("prototype")
public class CommentController {

    @Autowired
    private CommentService commentService;


    /**
     * 商品详情页提交留言
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/commit", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public Map<String, Object> commit(@RequestBody Map<String, String> data,
                                      HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        Comment comment = new Comment();
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            map.put("state", 0);
            map.put("msg", "未登录");
            return map;
        }

        //与该卖家交易的次数要大于留言次数才可以留言
        boolean isBuy = commentService.compare(user.getId());
        if (isBuy) {
            comment.setSellerId(Integer.parseInt(data.get("sellerId")));
            comment.setContent(data.get("content"));
            comment.setBuyerId(user.getId());
            comment.setsHead(user.getHead());
            int a = commentService.commit(comment);
            if (a == 1) {
                map.put("state", 1);
                map.put("msg", "留言成功");
                return map;
            } else {
                map.put("state", 0);
                map.put("msg", "服务器走丢了");
                return map;
            }
        } else {
            map.put("state", 0);
            map.put("msg", "您还没有与此卖家有过新交易哦");
            return map;
        }

    }

    /**
     * 个人中心查看我发的留言
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/selectComment")
    public ResultMap<List<Comment>> selectComment(Page page, @RequestParam("limit") int limit,
                                                  HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        page.setRows(limit);
        page.setUserId(user.getId());
        List<Comment> commentList = commentService.selectBCommentPageList(page);
        int totals = commentService.selectBCommentPageCount(page);
        page.setTotalRecord(totals);
        return new ResultMap<List<Comment>>("", commentList, 0, totals);
    }

    /**
     * 个人中心查看买家的留言
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getSellerComment")
    public ResultMap<List<CommentView>> getSellerComment(Page page, @RequestParam("limit") int limit,
                                                  HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        page.setRows(limit);
        page.setUserId(user.getId());
        List<CommentView> commentList = commentService.selectSCommentPageList(page);
        int totals = commentService.selectSCommentPageCount(page);
        page.setTotalRecord(totals);
        return new ResultMap<List<CommentView>>("", commentList, 0, totals);
    }

    /**
     * 去商品详情页留言
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/goWriteComment", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public Map<String, Object> goodsDetail(@RequestBody Map<String, String> data,
                                           HttpServletRequest request) {
        Map<String, Object> maps = new HashMap<>();
        Map<String, Object> map = new HashMap<>();
        maps.put("sellerId", data.get("userId"));
        List<Comment> commentList = commentService.goodsShowComment(maps);
        List<Goods> goodsList = commentService.findGoodsForComment(maps);
        if (request.getSession().getAttribute("goods") != null) {
            request.getSession().removeAttribute("goods");
            request.getSession().removeAttribute("comment");
        }

        if (goodsList.size() > 0) {
            request.getSession().setAttribute("goods", goodsList.get(0));
            request.getSession().setAttribute("commentList", commentList);
            map.put("state", 1);
            map.put("msg", "即将跳转");
            return map;
        } else {
            map.put("state", 0);
            map.put("msg", "该卖家无其他商品");
            return map;
        }
    }
    /**
     * 用户删除自己的留言
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/commentDelete")
    public Map<String, Object> collectDelete(@RequestParam String id, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> maps = new HashMap<>();
        User user = (User) request.getSession().getAttribute("user");
        maps.put("id", id);
        int result = commentService.delComment(maps);
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
