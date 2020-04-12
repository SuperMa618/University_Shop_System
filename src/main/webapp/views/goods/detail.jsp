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
    <style type="text/css">
        .fly-panel {
            margin-bottom: 15px;
            border-radius: 5px;
            box-shadow: 0 1px 2px 0 rgba(0, 9, 65, 0.99);
        }

        .detail-about-reply {
            padding: 0 0 0 35px;
            background: none;
        }

        .detail-body {
            margin: 10px 0 0;
            min-height: 306px;
            line-height: 26px;
            font-size: 16px;
            color: #333;
            word-wrap: break-word;
        }

        .jieda {
            margin-bottom: 30px;
        }

        .jieda-reply span {
            padding-right: 20px;
            color: #999;
            cursor: pointer;
        }

        .jieda-reply {
            position: relative;
        }

        .jieda-body {
            margin: 10px 0 20px;
            min-height: 0;
            line-height: 24px;
            font-size: 14px;
        }

        .fly-avatar img {
            display: block;
            width: 45px;
            height: 45px;
            margin: 0;
            border-radius: 2px;
        }

        .detail-about {
            position: relative;
            line-height: 20px;
            padding: 0px 0px 0px 75px;
            font-size: 13px;
            color: #999;
        }

        .detail-about-reply .fly-avatar {
            left: 0;
            top: 0;
        }

        .fly-avatar {
            position: absolute;
            left: 15px;
            top: 15px;
        }

        .fly-link {
            color: #01AAED;
        }

        .layui-icon {
            font-size: 10px;
            color: grey;
        }
    </style>
</head>
<body style="background-color: #f1f8ff">
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

        <div class="layui-col-md4"
             style="height: 360px;width: 420px;margin: 10px 10px;border:1px solid #c5cbc9;padding: 20px 15px">
            <input type="hidden" id="goodsId" name="goodsId" value="${goods.id}">
            <input type="hidden" id="sellerId" name="sellerId" value="${goods.userId}">
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
                        lay-filter="buy-form-submit">下订单
                </button>
            </form>
        </div>
        <div class="layui-col-md8" style="margin: 10px 10px;border:1px solid #c5cbc9;padding: 20px 15px">
            <div class="fly-panel detail-box" id="flyReply">
                <fieldset class="layui-elem-field layui-field-title" style="text-align: center;">
                    <legend>对卖家的留言</legend>
                </fieldset>
            </div>
            <div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
                <%--                <ul class="layui-tab-title">--%>
                <%--                    <li class="layui-this">用户留言</li>--%>
                <%--                </ul>--%>
                <div class="layui-tab-content">
                    <ul class="jieda" id="jieda">
                        <c:if test="${commentList != null}">
                            <c:forEach items="${commentList}" var="comment">
                                <li>
                                    <div class="detail-about detail-about-reply"><a class="fly-avatar">
                                        <img src="${comment.sHead}" class="layui-nav-img"></a>
                                        <div class="fly-detail-user"><a class="fly-link">
                                            <cite>匿名用户</cite>
                                        </a></div>
                                        <div class="detail-hits"><span>${comment.date}</span></div>
                                    </div>
                                    <div class="detail-body layui-text jieda-body photos"> ${comment.content}</div>

                                        <%--                            <div class="jieda-reply"><span class="jieda-zan " type="zan"> <i--%>
                                        <%--                                    class="iconfont icon-zan"></i> <em>0</em> </span> <span type="reply"> <i--%>
                                        <%--                                    class="iconfont icon-svgmoban53"></i> 回复 </span></div>--%>
                                </li>
                            </c:forEach>
                        </c:if>
                    </ul>

                    <form class="layui-form layui-form-pane">
                        <div class="layui-form-item layui-form-text">
                            <label class="layui-form-label">留言</label>
                            <div class="layui-input-block">
                                <textarea id="content" placeholder="请输入内容" class="layui-textarea"></textarea>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <button id="commit" class="layui-btn" lay-submit
                                    lay-filter="comment-form-submit">提交</button>
                        </div>
                    </form>
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
                                shade: 0.4,
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
                url: "/collect/collect",
                type: "POST",
                contentType: 'application/json',
                dataType: 'json',
                data: JSON.stringify(data),
                success: function (data) {
                    if (data.state == 0) {
                        layer.msg(data.msg,
                            {
                                icon: 1,
                                shade: 0.4,
                                time: 1500
                            });
                        $('#collect').html("已收藏");
                        $('#collect').attr("class", "layui-btn layui-btn-disabled");
                    } else {
                        layer.msg(data.msg,
                            {
                                icon: 2,
                                shade: 0.4,
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
                url: "/cart/cart",
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
                        $('#cart').attr("class", "layui-btn layui-btn-disabled");
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
                url: "/orders/buy",
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
                        $('#order').attr("class", "layui-btn layui-btn-disabled");
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

        // 留言
        form.on('submit(comment-form-submit)', function (data) {
            var data = {
                content: $('#content').val(),
                sellerId: $('#sellerId').val()
            };
            $.ajax({
                url: "/comment/commit",
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
                                time: 1000,
                                end: function () {
                                    location.href = "/goods/detail?goodsId=${goods.id}&sellerId=${goods.userId}";
                                }
                            });
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