package com.java.controller.goods;

import com.java.mapper.LogMapper;
import com.java.po.*;
import com.java.service.CommentService;
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
    private CommentService commentService;

    @Autowired
    private LogMapper logMapper;

    private Goods goods = null;
    private LogUtil logUtil = null;

    public GoodsController() {
        logUtil = new LogUtil();
    }



    /**
     * 用户查看自己已发布的商品
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/selectPublish")
    public ResultMap<List<Orders>> selectPublish(Page page, @RequestParam("limit") int limit,
                                                HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        page.setRows(limit);
        page.setUserId(user.getId());
        List<Orders> collectList = goodService.selectPublishPageList(page);
        int totals = goodService.selectPublishPageCount(page);
        page.setTotalRecord(totals);
        return new ResultMap<List<Orders>>("", collectList, 0, totals);
    }

    /**
     * 用户删除发布的商品
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/publishDelete")
    public Map<String, Object> publishDelete(@RequestParam String goodsId, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> maps = new HashMap<>();
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            map.put("state", 0);
            map.put("msg", "未登录");
            return map;
        }
        maps.put("userId", user.getId());
        maps.put("goodsId", goodsId);
        int result = goodService.delPublish(maps);
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
     * 商品搜索
     *
     * @param data
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
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
            maps.put("goodsId", null);
            List<Goods> goodsList = goodService.findGoods(maps);
            request.getSession().setAttribute("goodsList", goodsList);
            map.put("state", 1);
        }
        return map;
    }


    /**
     * 商品详情
     *
     * @param goodsId
     * @param request
     * @return
     */
    @RequestMapping(value = "/detail", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String goodsDetail(@RequestParam(value = "goodsId") String goodsId,
                              @RequestParam(value = "sellerId") String sellerId,
                              HttpServletRequest request) {
        Map<String, Object> maps = new HashMap<>();
        //maps.put("goodsName", null);
        //maps.put("type", null);
        maps.put("sellerId", sellerId);
        List<Comment> commentList = commentService.goodsShowComment(maps);
        maps.put("goodsId", goodsId);
        List<Goods> goodsList = goodService.findGoods(maps);
        if (request.getSession().getAttribute("goods") != null) {
            request.getSession().removeAttribute("goods");
            request.getSession().removeAttribute("comment");
        }

        if (goodsList.size() > 0) {
            request.getSession().setAttribute("goods", goodsList.get(0));
        }else {
            request.getSession().setAttribute("goods",null);
        }
        if (commentList.size() > 0) {
            request.getSession().setAttribute("commentList", commentList);
        } else {
            request.getSession().setAttribute("commentList", null);
        }
        return "goods/detail";
    }

    /**
     * 商品种类
     *
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
        List<Goods> goodsList = goodService.findGoods(map);
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



    //
    ///**
    // * 收藏商品
    // *
    // * @param data
    // * @param request
    // * @return
    // */
    //@ResponseBody
    //@RequestMapping(value = "/collect", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    //public Map<String, Object> goodsCollect(@RequestBody Map<String, String> data,
    //                                        HttpServletRequest request) {
    //    Map<String, Object> map = new HashMap<>();
    //    Map<String, Object> maps = new HashMap<>();
    //    User user = (User) request.getSession().getAttribute("user");
    //    maps.put("userId", user.getId());
    //    maps.put("goodsId", data.get("goodsId"));
    //    List<Goods> goodsList = goodService.findGoods(maps);
    //    if (goodsList.size() < 1) {
    //        map.put("state", 1);
    //        map.put("msg", "该商品已被下单，不能收藏！");
    //        return map;
    //    }
    //    Integer uid = goodService.isGoodsCollect(maps);
    //    if (uid != null) {
    //        map.put("state", 1);
    //        map.put("msg", "不能重复收藏");
    //        return map;
    //    } else {
    //        goodService.goodsCollect(maps);
    //        map.put("state", 0);
    //        map.put("msg", "收藏成功！");
    //        return map;
    //    }
    //}
    //
    ///**
    // * 用户查看收藏的商品
    // *
    // * @param request
    // * @return
    // */
    //@ResponseBody
    //@RequestMapping(value = "/selectCollect")
    //public ResultMap<List<Goods>> selectCollect(Page page, @RequestParam("limit") int limit,
    //                                            HttpServletRequest request, ModelMap modelMap) {
    //    User user = (User) request.getSession().getAttribute("user");
    //    page.setRows(limit);
    //    page.setUserId(user.getId());
    //    List<Goods> collectList = goodService.selectPageList(page);
    //    int totals = goodService.selectPageCount(page);
    //    page.setTotalRecord(totals);
    //    return new ResultMap<List<Goods>>("", collectList, 0, totals);
    //}
    //
    //
    ///**
    // * 用户查看收藏或购物车的商品详情
    // *
    // * @param request
    // * @return
    // */
    //@ResponseBody
    //@RequestMapping(value = "/collectDetail")
    //public Map<String, Object> collectDetail(@RequestParam String goodsId, HttpServletRequest request) {
    //    Map<String, Object> map = new HashMap<>();
    //    Map<String, Object> maps = new HashMap<>();
    //    maps.put("goodsName", null);
    //    maps.put("type", null);
    //    maps.put("goodsId", goodsId);
    //    List<Goods> goodsList = goodService.findGoods(maps);
    //    if (request.getSession().getAttribute("goods") != null) {
    //        request.getSession().removeAttribute("goods");
    //    }
    //    if (goodsList.get(0) != null) {
    //        request.getSession().setAttribute("goods", goodsList.get(0));
    //        map.put("state", 1);
    //        return map;
    //    } else {
    //        map.put("state", 0);
    //        map.put("msg", "服务器走丢了");
    //        return map;
    //    }
    //}
    //
    //
    ///**
    // * 用户删除收藏的商品
    // *
    // * @param request
    // * @return
    // */
    //@ResponseBody
    //@RequestMapping(value = "/collectDelete")
    //public Map<String, Object> collectDelete(@RequestParam String goodsId, HttpServletRequest request) {
    //    Map<String, Object> map = new HashMap<>();
    //    Map<String, Object> maps = new HashMap<>();
    //    User user = (User) request.getSession().getAttribute("user");
    //    maps.put("userId", user.getId());
    //    maps.put("goodsId", goodsId);
    //    int result = goodService.delCollect(maps);
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
    ///**
    // * 加入购物车
    // *
    // * @param data
    // * @param request
    // * @return
    // */
    //@ResponseBody
    //@RequestMapping(value = "/cart", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    //public Map<String, Object> goodsCart(@RequestBody Map<String, String> data,
    //                                     HttpServletRequest request) {
    //    Map<String, Object> map = new HashMap<>();
    //    Map<String, Object> maps = new HashMap<>();
    //    User user = (User) request.getSession().getAttribute("user");
    //    maps.put("goodsId", data.get("goodsId"));
    //    List<Goods> goodsList = goodService.findGoods(maps);
    //    if (goodsList.size() < 1) {
    //        map.put("state", 0);
    //        map.put("msg", "该商品已被下单，不能加购！");
    //        return map;
    //    }
    //    if (goodsList.size() > 0 && goodsList.get(0).getUserId() == user.getId()) {
    //        map.put("state", 0);
    //        map.put("msg", "这是您自己的商品哦！");
    //        return map;
    //    }
    //    maps.put("userId", user.getId());
    //    Integer uid = goodService.isGoodsCart(maps);
    //    if (uid != null) {
    //        map.put("state", 0);
    //        map.put("msg", "已在购物车中");
    //        return map;
    //    } else {
    //        goodService.goodsCart(maps);
    //        map.put("state", 1);
    //        map.put("msg", "已加入购物车！");
    //        return map;
    //    }
    //}
    //
    ///**
    // * 用户查看购物车的商品
    // *
    // * @param request
    // * @return
    // */
    //@ResponseBody
    //@RequestMapping(value = "/selectCart")
    //public ResultMap<List<Goods>> selectCart(Page page, @RequestParam("limit") int limit,
    //                                         HttpServletRequest request) {
    //    User user = (User) request.getSession().getAttribute("user");
    //    page.setRows(limit);
    //    page.setUserId(user.getId());
    //    List<Goods> collectList = goodService.selectCartPageList(page);
    //    int totals = goodService.selectCartPageCount(page);
    //    page.setTotalRecord(totals);
    //    return new ResultMap<List<Goods>>("", collectList, 0, totals);
    //}
    //
    ///**
    // * 用户删除购物车的商品
    // *
    // * @param request
    // * @return
    // */
    //@ResponseBody
    //@RequestMapping(value = "/cartDelete")
    //public Map<String, Object> cartDelete(@RequestParam String goodsId, HttpServletRequest request) {
    //    Map<String, Object> map = new HashMap<>();
    //    Map<String, Object> maps = new HashMap<>();
    //    User user = (User) request.getSession().getAttribute("user");
    //    maps.put("userId", user.getId());
    //    maps.put("goodsId", goodsId);
    //    int result = goodService.delCart(maps);
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
    //
    ///**
    // * 购物车批量下单
    // *
    // * @param data
    // * @param request
    // * @return
    // */
    //@ResponseBody
    //@RequestMapping(value = "/batchBuy", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    //public Map<String, Object> batchBuy(@RequestBody Map<String, String> data,
    //                                    HttpServletRequest request) {
    //    //返回值
    //    Map<String, Object> map = new HashMap<>();
    //    User user = (User) request.getSession().getAttribute("user");
    //    String id = data.get("ids").toString();
    //    String ids[] = id.split(",");
    //    for (int i = 0; i < ids.length; i++) {
    //        //后台传参
    //        Map<String, Object> maps = new HashMap<>();
    //        maps.put("buyerId", user.getId());
    //        maps.put("goodsId", ids[i]);
    //        maps.put("buyerTel", user.getTel());
    //        List<Goods> goodsList = goodService.findGoods(maps);
    //        maps.put("sellerId", goodsList.get(0).getUserId());
    //        goodService.batchBuy(maps);
    //    }
    //    map.put("state", 1);
    //    map.put("msg", "已下单 请在七天内完成交易！");
    //    return map;
    //}
    //
    //
    ///**
    // * 下单
    // *
    // * @param data
    // * @param request
    // * @return
    // */
    //@ResponseBody
    //@RequestMapping(value = "/buy", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    //public Map<String, Object> buy(@RequestBody Map<String, String> data,
    //                               HttpServletRequest request) {
    //    Map<String, Object> map = new HashMap<>();
    //    Map<String, Object> maps = new HashMap<>();
    //    User user = (User) request.getSession().getAttribute("user");
    //    maps.put("goodsId", data.get("goodsId"));
    //    Integer uid = goodService.isGoodsOrders(maps);
    //    if (uid != null) {
    //        map.put("state", 0);
    //        map.put("msg", "已下单");
    //        return map;
    //    }
    //    List<Goods> goodsList = goodService.findGoods(maps);
    //    if (goodsList.size() > 0 && goodsList.get(0).getUserId() == user.getId()) {
    //        map.put("state", 0);
    //        map.put("msg", "这是您自己的商品哦！");
    //        return map;
    //    }
    //    maps.put("buyerId", user.getId());
    //    maps.put("sellerId", goodsList.get(0).getUserId());
    //    maps.put("buyerTel", user.getTel());
    //    goodService.goodsBuy(maps);
    //
    //    map.put("state", 1);
    //    map.put("msg", "下单成功，请在七天内完成交易！");
    //    return map;
    //
    //}
    //
    ///**
    // * 买家删除订单
    // *
    // * @param request
    // * @return
    // */
    //@ResponseBody
    //@RequestMapping(value = "/buyerDelete")
    //public Map<String, Object> buyerDelete(@RequestParam String goodsId, HttpServletRequest request) {
    //    Map<String, Object> map = new HashMap<>();
    //    Map<String, Object> maps = new HashMap<>();
    //    User user = (User) request.getSession().getAttribute("user");
    //    if (user == null) {
    //        map.put("state", 0);
    //        map.put("msg", "未登录");
    //        return map;
    //    }
    //    maps.put("buyerId", user.getId());
    //    maps.put("goodsId", goodsId);
    //    int result = goodService.delOrdersByBuyer(maps);
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
    ///**
    // * 卖家删除订单
    // *
    // * @param request
    // * @return
    // */
    //@ResponseBody
    //@RequestMapping(value = "/sellerDelete")
    //public Map<String, Object> sellerDelete(@RequestParam String goodsId, HttpServletRequest request) {
    //    Map<String, Object> map = new HashMap<>();
    //    Map<String, Object> maps = new HashMap<>();
    //    User user = (User) request.getSession().getAttribute("user");
    //    if (user == null) {
    //        map.put("state", 0);
    //        map.put("msg", "未登录");
    //        return map;
    //    }
    //    maps.put("sellerId", user.getId());
    //    maps.put("goodsId", goodsId);
    //    int result = goodService.delOrdersBySeller(maps);
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
    ///**
    // * 用户查看自己下的订单
    // *
    // * @param request
    // * @return
    // */
    //@ResponseBody
    //@RequestMapping(value = "/selectOrders")
    //public ResultMap<List<Orders>> selectOrders(Page page, @RequestParam("limit") int limit,
    //                                            HttpServletRequest request) {
    //    User user = (User) request.getSession().getAttribute("user");
    //    page.setRows(limit);
    //    page.setUserId(user.getId());
    //    List<Orders> collectList = goodService.selectOrdersPageList(page);
    //    int totals = goodService.selectOrdersPageCount(page);
    //    page.setTotalRecord(totals);
    //    return new ResultMap<List<Orders>>("", collectList, 0, totals);
    //}
    //
    ///**
    // * 用户查看自己发布的商品订单
    // *
    // * @param request
    // * @return
    // */
    //@ResponseBody
    //@RequestMapping(value = "/selectSell")
    //public ResultMap<List<Orders>> selectSell(Page page, @RequestParam("limit") int limit,
    //                                            HttpServletRequest request) {
    //    User user = (User) request.getSession().getAttribute("user");
    //    page.setRows(limit);
    //    page.setUserId(user.getId());
    //    List<Orders> collectList = goodService.selectSellPageList(page);
    //    int totals = goodService.selectSellPageCount(page);
    //    page.setTotalRecord(totals);
    //    return new ResultMap<List<Orders>>("", collectList, 0, totals);
    //}
    //
    ///**
    // * 用户 完成 自己发布的商品订单
    // *
    // * @param request
    // * @return
    // */
    //@ResponseBody
    //@RequestMapping(value = "/completeOrders")
    //public Map<String, Object> completeOrders(@RequestParam String goodsId,HttpServletRequest request) {
    //    Map<String, Object> map = new HashMap<>();
    //    Map<String, Object> maps = new HashMap<>();
    //    if (request.getSession().getAttribute("user") == null) {
    //        map.put("state", 0);
    //        map.put("msg", "未登录");
    //        return map;
    //    }
    //    maps.put("goodsId", goodsId);
    //    int result = goodService.compOrders(maps);
    //    if (result > 0) {
    //        map.put("state", 1);
    //        map.put("msg", "交易完成");
    //        return map;
    //    } else {
    //        map.put("state", 0);
    //        map.put("msg", "删除失败");
    //        return map;
    //    }
    //}
    //
    //
    ///**
    // * 用户查看历史订单
    // *
    // * @param request
    // * @return
    // */
    //@ResponseBody
    //@RequestMapping(value = "/historyOrders")
    //public ResultMap<List<Orders>> historyOrders(Page page, @RequestParam("limit") int limit,
    //                                         HttpServletRequest request) {
    //    User user = (User) request.getSession().getAttribute("user");
    //    page.setRows(limit);
    //    page.setUserId(user.getId());
    //    List<Orders> collectList = goodService.selectHistoryPageList(page);
    //    int totals = goodService.selectHistoryPageCount(page);
    //    page.setTotalRecord(totals);
    //    return new ResultMap<List<Orders>>("", collectList, 0, totals);
    //}
}
