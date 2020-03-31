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
            line-height: 1.5;
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
                        <input id="search" type="text" name="search" class="layui-input" style="Float:left;height: 30px;"
                               placeholder="请输入搜索内容" lay-verify="required" autocomplete="off"
                               maxlength="13">
                        <button class="layui-btn layui-btn-normal layui-btn-sm" lay-submit
                                lay-filter="search-form-submit" style="position:absolute;">
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
                                <a href="/user/out"><i class="layui-icon layui-icon-return"
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
    <div class="layui-row">
        <div class="layui-col-md3" style="margin: 10px 10px;border:1px solid #c5cbc9;padding: 20px 15px">
            <li><a href="/goods/type?type=电子产品"><img src="../images/icon/phone.png">电子产品</a><span
                    style="font-size: 13px;color: #999999">  手机 笔记本 主机 显示器 相机 平板 外设</span></li>
            <hr class="layui-bg-red">
            <li><a href="/goods/type?type=书籍资料"><img src="../images/icon/book.png"> 书籍资料</a><span
                    style="font-size: 13px;color: #999999">  教材 复习资料 其他</span></li>
            <hr>
            <li><a href="/goods/type?type=时尚装饰"><img src="../images/icon/zs.png"> 时尚装饰</a><span
                    style="font-size: 13px;color: #999999">  时尚饰品 珠宝首饰 手链手镯 潮流眼镜 奇珍异宝 其他</span></li>

            <hr>
            <li><a href="/goods/type?type=男装鞋包"><img src="../images/icon/xz.png"> 男装鞋包</a><span
                    style="font-size: 13px;color: #999999">  男装 男裤 男鞋 衬衫 T恤 男包 腰带 手套 帽子 其他</span></li>

            <hr>
            <li><a href="/goods/type?type=女装鞋包"><img src="../images/icon/qz.png"> 女装鞋包</a><span
                    style="font-size: 13px;color: #999999">  女装 女裤 女鞋 衬衫 T恤 女包 腰带 手套 帽子 其他</span></li>

            <hr>
            <li><a href="/goods/type?type=护肤彩妆"><img src="../images/icon/kh.png"> 护肤彩妆</a><span
                    style="font-size: 13px;color: #999999">  化妆品 美发造型 美容护肤 其他</span></li>
            <hr class="layui-bg-blue">
            <li><a href="/goods/type?type=生活用品"><img src="../images/icon/bz.png"> 生活用品</a><span
                    style="font-size: 13px;color: #999999">  文具 电器 耗材 其他</span></li>
            <hr class="layui-bg-green">
            <li><a href="/goods/type?type=运动用品"><img src="../images/icon/lq.png"> 运动用品</a><span
                    style="font-size: 13px;color: #999999">  运动鞋 运动服 运动装备 健身用品 其他</span></li>
            <hr class="layui-bg-red">
            <li><a href="/goods/type?type=寝室用品"><img src="../images/icon/td.png"> 寝室用品</a><span
                    style="font-size: 13px;color: #999999">  桌椅 挂件 帘子 收纳盒 其他</span></li>
            <hr>
            <li><a href="/goods/type?type=限时票卡"><img src="../images/icon/vip.png"> 限时票卡</a><span
                    style="font-size: 13px;color: #999999">  健身卡 会员卡 电影票 优惠券 其他</span></li>
            <hr class="layui-bg-orange">
            <li><a href="/goods/type?type=其他"><img src="../images/icon/kh.png">其他</a></li>
        </div>

        <div style="background-color: #009f95;margin: 10px 1px;position: relative;
                        display: block;
                        box-sizing: border-box;
                        float: left;width: 47%">
            <div class="layui-carousel" id="test1">
                <div carousel-item>
                    <div><img src="/Ushop-image/head/BaiduShurufa_2020-3-2_9-6-29.png" style="width: 100%;height: 100%">
                    </div>
                    <div><img src="/Ushop-image/head/BaiduShurufa_2020-3-2_11-34-29.png"
                              style="width: 100%;height: 100%"></div>
                    <div><img src="/Ushop-image/head/BaiduShurufa_2020-3-2_11-34-44.png"
                              style="width: 100%;height: 100%"></div>
                    <div><img src="/Ushop-image/head/BaiduShurufa_2020-3-2_11-34-53.png"
                              style="width: 100%;height: 100%"></div>
                    <div><img src="/Ushop-image/head/BaiduShurufa_2020-3-2_11-34-29.png"
                              style="width: 100%;height: 100%"></div>
                </div>
            </div>
        </div>
        <div class="layui-col-md3" style="background-color: #fafffb;
                                          margin: 10px 8px;border:1px solid #c5cbc9;
                                          padding: 25px 15px">
            <!--            <i style="width:5px;height:5px;border-radius:50%;background-color:orange;display: block"></i>-->
            <ul>校园大新闻活动</ul>
            <hr class="layui-bg-orange">
            <ul>校园大新闻活动</ul>
            <hr class="layui-bg-orange">
            <ul>校园大新闻活动</ul>
            <hr class="layui-bg-orange">
            <ul>校园大新闻活动</ul>
            <hr class="layui-bg-orange">
            <ul>校园大新闻活动</ul>
            <hr class="layui-bg-orange">
            <ul>校园大新闻活动</ul>
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
        © xyjy.com 2019-2020 MYJ.All Right Reserved.
    </div>
</div>

<script>
    //JavaScript代码区域
    layui.use(['jquery', 'layer', 'element', 'form', 'carousel'], function () {
        var $ = layui.$;
        var layer = layui.layer;
        var form = layui.form;
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

        // 搜索商品
        form.on('submit(search-form-submit)', function (data) {
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
    });

</script>
</body>
</html>