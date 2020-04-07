<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>首页内容</title>
    <link rel="stylesheet" href="/js/layui-2.5.4/css/layui.css">
    <script src="/js/layui-2.5.4/layui.js"></script>

    <style type="text/css">
        li {
            line-height: 2;
        }
    </style>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">

    <div class="layui-header" style="background-color: #426078;height: 62px">
        <div class="layui-logo" style="color: #00F7DE">
            <i class="layui-icon layui-icon-release" style="font-size: 20px; color: #1E9FFF;"></i>
            &nbsp校园二手交易平台
        </div>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <c:choose>
                    <c:when test="${user.userName!=null}">
                        <a href="javascript:0">
                            <img src="${user.head}" class="layui-nav-img">&nbsp${user.userName}
                        </a>
                        <dl class="layui-nav-child">
                            <dd>
                                <a href=""><i class="layui-icon layui-icon-face-smile"
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

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree">
                <li class="layui-nav-item layui-this"><a href="/page/index">返回主页</a></li>
                <li class="layui-nav-item layui-nav-itemed"><a>我的信息</a>
                    <dl class="layui-nav-child">
                        <dd>
                            <a href="/page/user/update">修改信息</a>
                        </dd>
                        <dd>
                            <a href="">查看信息</a>
                        </dd>
                    </dl>
                </li>

                <li class="layui-nav-item layui-nav-itemed"><a>我的商品</a>
                    <dl class="layui-nav-child">
                        <dd>
                            <a href="/page/user/submitGoods">提交商品</a>
                        </dd>
                        <dd>
                            <a href="/page/user/collect">收藏夹</a>
                        </dd>
                        <dd>
                            <a href="/page/user/cart">购物车</a>
                        </dd>
                        <dd>
                            <a href="">已发布商品</a>
                        </dd>
                        <dd>
                            <a href="">历史交易</a>
                        </dd>
                    </dl>
                </li>
                <li class="layui-nav-item"><a >我的订单</a>
                    <dl class="layui-nav-child layui-nav-itemed">
                        <dd>
                            <a href="/page/user/ordersell">我的发布</a>
                        </dd>
                        <dd>
                            <a href="/page/user/orders">我买的</a>
                        </dd>
                    </dl>
                </li>
                <li class="layui-nav-item"><a >我的留言</a></li>
                <li class="layui-nav-item" style="display: none;"><a
                        data-url="" data-id="4" data-title="test"
                        id="test" class="site-demo-active" href="javascript:;"
                        data-type="tabAdd">test</a></li>
            </ul>
        </div>

    </div>

    <div class="layui-body layui-tab-content site-demo site-demo-body">
        <div class="layui-tab layui-tab-brief" lay-filter="demoTitle">
            <ul class="layui-tab-title site-demo-title">
                <li class="layui-this"><i class="layui-icon layui-icon-home"
                                          style="font-size: 20px; color: #1E9FFF;"></i>&nbsp欢迎
                </li>
            </ul>
        </div>

    </div>
    <div class="layui-footer" align="center">
        © xyjy.com 2019-2020 MYJ.All Right Reserved.
    </div>
</div>

<script>
    //JavaScript代码区域
    layui.use(['jquery', 'layer', 'element', 'carousel'], function () {
        var $ = layui.$;
        var layer = layui.layer;
        var element = layui.element;
        var carousel = layui.carousel;
        //建造实例
        carousel.render({
            elem: '#test1',
            width: '100%',//设置容器宽度
            autoplay: true,
            interval: 2500,
            arrow: 'always' //始终显示箭头
            //,anim: 'updown' //切换动画方式
        });

    });

</script>
</body>
</html>