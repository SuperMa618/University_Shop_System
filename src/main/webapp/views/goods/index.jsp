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
                            <div class="item">
                                    <img src="/Ushop-image/head/BaiduShurufa.jpg" style="height: 310px;width: 290px;object-fit: cover">
                                <div class="text-box">
                                    <p class="title">宝宝专用硅胶环保饭碗四套+调羹+筷子自助学吃饭套装</p>
                                    <p class="plic">
                                        <span class="ciur-pic">￥100.00</span>
                                        <span>2019.12.12</span>
                                    </p>
                                </div>
                            </div>
                            <div class="item">
                                <img src="/Ushop-image/head/mrsptp202002021817.png" style="height: 310px;width: 290px;object-fit: cover">
                                <div class="text-box">
                                    <p class="title">宝宝专用硅胶环保饭碗四套+调羹+筷子自助学吃饭套装</p>
                                    <p class="plic">
                                        <span class="ciur-pic">￥100.00</span>
                                        <span>2019.12.12</span>
                                    </p>
                                </div>
                            </div>
                            <div class="item">
                                <img src="/Ushop-image/head/xyjy.png" style="height: 310px;width: 290px;object-fit: cover">
                                <div class="text-box">
                                    <p class="title">宝宝专用硅胶环保饭碗四套+调羹+筷子自助学吃饭套装</p>
                                    <p class="plic">
                                        <span class="ciur-pic">￥100.00</span>
                                        <span>2019.12.12</span>
                                    </p>
                                </div>
                            </div>
                            <div class="item">
                                <img src="/Ushop-image/head/BaiduShurufa_2020-3-2_9-6-29.png" style="height: 310px;width: 290px;object-fit: cover">
                                <div class="text-box">
                                    <p class="title">宝宝专用硅胶环保饭碗四套+调羹+筷子自助学吃饭套装</p>
                                    <p class="plic">
                                        <span class="ciur-pic">￥100.00</span>
                                        <span>2019.12.12</span>
                                    </p>
                                </div>
                            </div>
                            <div class="item">
                                <img src="/Ushop-image/head/mrsptp202002021817.png" style="height: 310px;width: 290px;object-fit: cover">
                                <div class="text-box">
                                    <p class="title">宝宝专用硅胶环保饭碗四套+调羹+筷子自助学吃饭套装</p>
                                    <p class="plic">
                                        <span class="ciur-pic">￥100.00</span>
                                        <span>2019.12.12</span>
                                    </p>
                                </div>
                            </div>
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