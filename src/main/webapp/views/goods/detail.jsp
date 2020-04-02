<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>首页内容</title>
    <link rel="stylesheet" href="/js/layui-2.5.4/css/main.css">
    <link rel="stylesheet" href="/js/layui-2.5.4/css/layui.css">
    <script src="/js/layui-2.5.4/layui.js"></script>
</head>
<body>
<div class="layui-layout layui-layout-admin">

    <div class="layui-header" style="background-color: #426078">
        <div class="layui-logo" style="color: #00F7DE">
            <i class="layui-icon layui-icon-release" style="font-size: 20px; color: #1E9FFF;"></i>
            &nbsp校园二手交易平台
        </div>
        <ul class="layui-nav layui-layout-left">
            <li class="layui-nav-item"><a href="/page/index">
                <i class="layui-icon layui-icon-home" style="font-size: 20px; color: #1E9FFF;"></i>&nbsp首页</a>
            </li>
            <li class="layui-nav-item"><a href="/page/goods/index">
                <i class="layui-icon layui-icon-cart" style="font-size: 20px; color: #1E9FFF;"></i>&nbsp商品</a>
            </li>
            <li class="layui-nav-item"><a href="">
                <i class="layui-icon layui-icon-face-surprised"
                   style="font-size: 20px; color: #1E9FFF;"></i>&nbsp联系我们</a>
            </li>
            <li class="layui-nav-item">
                <form class="layui-form">
                    <div>
                        <input id="search" type="text" name="search" class="layui-input"
                               style="Float:left;height: 30px;"
                               placeholder="请输入搜索内容" lay-verify="required" autocomplete="off"
                               maxlength="13">
                        <button class="layui-btn layui-btn-normal layui-btn-sm" lay-submit
                                lay-filter="search2-form-submit" style="position:absolute;">
                            <i class="layui-icon layui-icon-search"></i></button>
                    </div>
                </form>
            </li>
        </ul>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <c:choose>
                    <c:when test="${user.userName!=null}">
                        <a href="/page/user/index">
                            <img src="${user.head}" class="layui-nav-img">&nbsp${user.userName}
                        </a>
                        <dl class="layui-nav-child">
                            <dd>
                                <a href="/page/user/index"><i class="layui-icon layui-icon-face-smile"
                                                              style="font-size: 15px; color: #1E9FFF;"></i>&nbsp个人中心</a>
                            </dd>
                            <dd>
                                <a href="/user/out"><i class="layui-icon layui-icon-face-smile"
                                                       style="font-size: 15px; color: #1E9FFF;"></i>&nbsp退出登录</a>
                            </dd>
                        </dl>
                    </c:when>
                    <c:otherwise>
                        <a href="/page/login">
                            <i class="layui-icon layui-icon-username" style="font-size: 20px; color: #1E9FFF;"></i>&nbsp未登录
                        </a>
                    </c:otherwise>
                </c:choose>
            </li>
        </ul>
    </div>
    <div class="layui-body">
        <div class="layui-col-md4" style="margin: 10px 10px;border:1px solid #c5cbc9;padding: 20px 15px">
            <img src="${goods.picture}" style="height: 320px;width: 320px;object-fit: cover">
        </div>

        <div class="layui-col-md4" style="height: 360px;width: 360px;margin: 10px 10px;border:1px solid #c5cbc9;padding: 20px 15px">
            <input type="hidden" id="goodsId" name="goodsId" value="${goods.id}"></input>
            <h3>商品名称：${goods.goodsName}</h3><br>
            <h2 style="color: #ee0000">价格：${goods.price}</h2><br>
            <h3>商品描述：${goods.describes}</h3><br>
            <h4>发布时间：${goods.time}</h4><br>
            <form id="form-submit" class="layui-form layui-form-pane"
                  lay-filter="collect-password-form">
                <button type="button" id="collect" class="layui-btn layui-btn-danger" lay-submit
                        lay-filter="collect-form-submit">收藏
                </button>
                <button type="button" id="cart" class="layui-btn layui-btn-danger" lay-submit
                        lay-filter="cart-form-submit">加入购物车
                </button>
                <button type="button" id="order" class="layui-btn layui-btn-danger" lay-submit
                        lay-filter="buy-form-submit">下订单</button>
            </form>
        </div>

        <div class="layui-col-md8" style="margin: 10px 10px;border:1px solid #c5cbc9;padding: 20px 15px">
            <div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
                <ul class="layui-tab-title">
                    <li class="layui-this">用户留言</li>
                </ul>
                <div class="layui-tab-content">
                    <div class="layui-tab-item layui-show">内容1</div>
                </div>
            </div>
        </div>
    </div>


    <div class="layui-footer" style="left: 0px" align="center">
        © xyjy.com 2019-2020 MYJ.All Right Reserved.
    </div>
</div>

<script>
    //JavaScript代码区域
    layui.use(['jquery', 'layer', 'element', 'form'], function () {
        var $ = layui.$;
        var layer = layui.layer;
        var element = layui.element;
        var form = layui.form;
        // 搜索商品
        form.on('submit(search2-form-submit)', function (data) {
            var data = {
                search: $('#search').val()
            };
            $.ajax({
                url: "/goods/search",
                type: "POST",
                contentType: 'application/json',
                dataType: 'json',
                data: JSON.stringify(data),
                success: function (data) {
                    if (data.state == 0) {
                        parent.layer.msg(data.msg,
                            {
                                icon: 2,
                                shade: 0.3,
                                time: 2000
                            });
                    } else {
                        location.href = "/page/goods/indexSearch";
                    }
                },
                error: function () {
                    layer.open({
                        title: '系统提示',
                        content: '发生未知错误，请联系管理员！'
                    });
                }
            });
            // 阻止表单跳转
            return false;
        });
        // 收藏商品
        form.on('submit(collect-form-submit)', function (data) {
            var data = {
                goodsId: $('#goodsId').val()
            };
            debugger;
            $.ajax({
                url: "/goods/collect",
                type: "POST",
                contentType: 'application/json',
                dataType: 'json',
                data: JSON.stringify(data),
                success: function (data) {
                    if (data.state == 0) {
                        parent.layer.msg(data.msg,
                            {
                                icon: 1,
                                shade: 0.3,
                                time: 1500
                            });
                        $('#collect').html("已收藏");
                        $('#collect').attr("class","layui-btn layui-btn-disabled");
                    }else {
                        parent.layer.msg(data.msg,
                            {
                                icon: 2,
                                shade: 0.3,
                                time: 1500
                            });
                    }
                },
                error: function () {
                    layer.open({
                        title: '系统提示',
                        content: '发生未知错误，请联系管理员！'
                    });
                }
            });
            // 阻止表单跳转
            return false;
        });

        // 加购物车
        form.on('submit(cart-form-submit)', function (data) {
            var data = {
                goodsId: $('#goodsId').val()
            };
            $.ajax({
                url: "/goods/cart",
                type: "POST",
                contentType: 'application/json',
                dataType: 'json',
                data: JSON.stringify(data),
                success: function (data) {
                    if (data.state == 1) {
                        parent.layer.msg(data.msg,
                            {
                                icon: 1,
                                shade: 0.3,
                                time: 2000
                            });
                        $('#cart').html("已加购");
                        $('#cart').attr("class","layui-btn layui-btn-disabled");
                    } else {
                        parent.layer.msg(data.msg,
                            {
                                icon: 2,
                                shade: 0.3,
                                time: 2000
                            });
                    }
                },
                error: function () {
                    layer.open({
                        title: '系统提示',
                        content: '发生未知错误，请联系管理员！'
                    });
                }
            });
            // 阻止表单跳转
            return false;
        });


        // 下单
        form.on('submit(buy-form-submit)', function (data) {
            var data = {
                goodsId: $('#goodsId').val()
            };
            $.ajax({
                url: "/goods/buy",
                type: "POST",
                contentType: 'application/json',
                dataType: 'json',
                data: JSON.stringify(data),
                success: function (data) {
                    if (data.state == 1) {
                        parent.layer.msg(data.msg,
                            {
                                icon: 1,
                                shade: 0.3,
                                time: 2000
                            });
                        $('#order').html("已下单");
                        $('#order').attr("class","layui-btn layui-btn-disabled");
                    } else {
                        parent.layer.msg(data.msg,
                            {
                                icon: 2,
                                shade: 0.3,
                                time: 2000
                            });
                    }
                },
                error: function () {
                    layer.open({
                        title: '系统提示',
                        content: '发生未知错误，请联系管理员！'
                    });
                }
            });
            // 阻止表单跳转
            return false;
        });
    });

</script>
</div>
</body>
</html>