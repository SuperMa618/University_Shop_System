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

    <div class="content content-nav-base buytoday-content">
        <div id="list-cont">
            <div class="product-list-box">
                <div class="product-list w1200">
                    <div class="tab-title">
                        <a href="javascript:;" class="active tuang">商品</a>
                    </div>
                    <div class="list-cont">
                        <div class="item-box layui-clear">
                            <c:if test="${goodsList != null}">
                                <form>
                                    <c:forEach var="goods" items="${goodsList}">
                                        <div class="item">
<%--                                            <input id="goodsId" name="goodsId" lay-submit--%>
<%--                                                   lay-filter="detail-form-submit"--%>
<%--                                                   value="${goods.id}" type="image" src="${goods.picture}"--%>
<%--                                                   style="height: 310px;width: 290px;object-fit: cover">--%>
                                            <a href="/goods/detail?goodsId=${goods.id}">
                                                <img src="${goods.picture}" style="height: 310px;width: 290px;object-fit: cover">
                                                <div class="text-box">
                                                    <p class="title">${goods.goodsName}</p>
                                                    <p class="plic">
                                                        <span class="ciur-pic">￥${goods.price}</span>
                                                        <span>${goods.time}</span>
                                                    </p>
                                                </div>
                                            </a>
                                                <%--                                            <img src="${goods.picture}"--%>
                                                <%--                                                 style="height: 310px;width: 290px;object-fit: cover">--%>

                                        </div>
                                    </c:forEach>
                                </form>
                            </c:if>
                        </div>
                        <div id="demo0" style="text-align: center;"></div>
                    </div>
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
            // dat = sessionStorage.getItem("msg")
            var data = {
                search: $('#search').val(),

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
        // 商品详细
        form.on('submit(detail-form-submit)', function (data) {
            var data = {
                goodsId: $('#goodsId').val()
            };
            $.ajax({
                url: "/goods/detail",
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
                        location.href = "/page/goods/detail";
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
</body>
</html>