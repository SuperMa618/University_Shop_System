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

    <div class="layui-header" style="background-color: #426078">
        <div class="layui-logo" style="color: #00F7DE">
            <i class="layui-icon layui-icon-release" style="font-size: 20px; color: #1E9FFF;"></i>
            &nbsp校园二手交易平台
        </div>
        <ul class="layui-nav layui-layout-left">
            <li class="layui-nav-item"><a href="">
                <i class="layui-icon layui-icon-home" style="font-size: 20px; color: #1E9FFF;"></i>&nbsp首页</a>
            </li>
            <li class="layui-nav-item"><a href="">
                <i class="layui-icon layui-icon-cart" style="font-size: 20px; color: #1E9FFF;"></i>&nbsp商品</a>
            </li>
            <li class="layui-nav-item"><a href="">
                <i class="layui-icon layui-icon-face-surprised"
                   style="font-size: 20px; color: #1E9FFF;"></i>&nbsp联系我们</a>
            </li>
        </ul>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <c:choose>
                    <c:when test="${user.userName!=null}">
                    <a href="javascript:0">
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
            <li class="layui-nav-item"><a href="">
                <i class="layui-icon layui-icon-component" style="font-size: 20px; color: #1E9FFF;"></i>&nbsp个人中心</a>
                <dl class="layui-nav-child">
                    <dd>
                        <a href=""><i class="layui-icon layui-icon-face-smile"
                                      style="font-size: 15px; color: #1E9FFF;"></i>&nbsp个人信息</a>
                    </dd>
                    <dd>
                        <a href=""><i class="layui-icon layui-icon-cart-simple"
                                      style="font-size: 20px; color: #1E9FFF;"></i>商品管理</a>
                    </dd>
                    <dd>
                        <a href=""><i class="layui-icon layui-icon-file-b"
                                      style="font-size: 20px; color: #1E9FFF;"></i>订单管理</a>
                    </dd>
                    <c:if test="${user.userName!=null}">
                        <dd>
                            <a href=""><i class="layui-icon layui-icon-file-b"
                                          style="font-size: 20px; color: #1E9FFF;"></i></a>
                        </dd>
                    </c:if>
                </dl>
            </li>
        </ul>
    </div>
    <div class="layui-row">
        <div class="layui-col-md3" style="margin: 10px 10px;border:1px solid #c5cbc9;padding: 20px 15px">
            <li>电子产品</li>
            <hr class="layui-bg-red">
            <li>书籍资料</li>
            <hr>
            <li>时尚装配</li>
            <hr class="layui-bg-blue">
            <li>生活用品</li>
            <hr class="layui-bg-green">
            <li>运动用品</li>
            <hr class="layui-bg-red">
            <li>有趣玩意</li>
            <hr class="layui-bg-red">
            <li>有趣玩意</li>
            <hr class="layui-bg-orange">
            <li>其他</li>
        </div>

        <div style="background-color: #009f95;margin: 10px 1px;position: relative;
                        display: block;
                        box-sizing: border-box;
                        float: left;width: 47%">
            <div class="layui-carousel" id="test1">
                <div carousel-item>
                    <div style="background-color: #9F9F9F">1111</div>
                    <div style="background-color: #9F9F9F">2222</div>
                    <div style="background-color: #9F9F9F">333</div>
                    <div style="background-color: #9F9F9F">44444</div>
                    <div>5555</div>
                </div>
            </div>
        </div>
        <div class="layui-col-md3" style="background-color: #fafffb;
                                          margin: 10px 8px;border:1px solid #c5cbc9;
                                          padding: 25px 15px">
            <!--            <i style="width:5px;height:5px;border-radius:50%;background-color:orange;display: block"></i>-->
            <ul>校园大新闻滴滴滴</ul>
            <hr class="layui-bg-orange">
            <ul>校园大新闻滴滴滴</ul>
            <hr class="layui-bg-orange">
            <ul>校园大新闻滴滴滴</ul>
            <hr class="layui-bg-orange">
            <ul>校园大新闻滴滴滴</ul>
            <hr class="layui-bg-orange">
            <ul>校园大新闻滴滴滴</ul>
            <hr class="layui-bg-orange">
            <ul>校园大新闻滴滴滴</ul>
        </div>
        <div class="layui-col-md8" style="margin: 0px 0px;border:1px solid #c5cbc9;">
            <div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
                <ul class="layui-tab-title">
                    <li class="layui-this">网站设置</li>
                    <li>用户管理</li>
                </ul>
                <div class="layui-tab-content">
                    <div class="layui-tab-item layui-show">内容1</div>
                    <div class="layui-tab-item">内容2</div>
                </div>
            </div>
        </div>
    </div>

    <div class="layui-footer" style="left: 0px" align="center">
        © myj.com 二手交易系统
    </div>
</div>

<script>
    //JavaScript代码区域
    layui.use(['jquery', 'layer', 'element', 'carousel'], function () {
        var $ = layui.$;
        var layer = layui.layer;
        var element = layui.element;
        /*界面切换  */
        // $("a").click(function (e) {
        //     e.preventDefault();
        //     $("#iframeMain").attr("src", $(this).attr("href"));
        // });
        //$("#souye").click();
        //一下代码是根据窗口高度在设置iframe的高度
        var frame = $("#iframeMain");
        var frameheight = $(window).height();
        //console.log(frameheight);
        frame.css("height", frameheight);
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