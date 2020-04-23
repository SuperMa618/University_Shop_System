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
        input:focus {
            border-style: solid;
            border-color: #03a9f4;
            box-shadow: 0 0 15px #03a9f4;
        }

        label {
            width: 50px
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
                <li class="layui-nav-item layui-bg-green"><a href="/page/index">返回主页</a></li>
                <li class="layui-nav-item layui-nav-itemed"><a>我的信息</a>
                    <dl class="layui-nav-child">
                        <dd>
                            <a href="/page/user/update">修改信息</a>
                        </dd>
                    </dl>
                </li>

                <li class="layui-nav-item layui-nav-itemed"><a>我的商品</a>
                    <dl class="layui-nav-child">
                        <dd>
                            <a href="/page/user/submitGoods">提交</a>
                        </dd>
                        <dd>
                            <a href="/page/user/collect">收藏夹</a>
                        </dd>
                        <dd>
                            <a href="/page/user/cart">购物车</a>
                        </dd>
                        <dd>
                            <a href="/page/user/orderPublish">已发布</a>
                        </dd>
                        <dd>
                            <a href="/page/user/review">审核中</a>
                        </dd>
                        <dd>
                            <a href="/page/user/orderHistory">历史交易</a>
                        </dd>
                    </dl>
                </li>
                <li class="layui-nav-item layui-nav-itemed"><a>我的订单</a>
                    <dl class="layui-nav-child layui-nav-itemed">
                        <dd>
                            <a href="/page/user/ordersell">发布</a>
                        </dd>
                        <dd>
                            <a href="/page/user/orders">我买的</a>
                        </dd>
                    </dl>
                </li>
                <li class="layui-nav-item layui-nav-itemed"><a>留言</a>
                    <dl class="layui-nav-child layui-nav-itemed">
                        <dd>
                            <a href="/page/user/comment">我的留言</a>
                        </dd>
                        <dd>
                            <a href="/page/user/commentForS">买家留言</a>
                        </dd>
                    </dl>
                </li>
            </ul>
        </div>

    </div>

    <div class="layui-body layui-tab-content site-demo site-demo-body">
        <div class="layui-tab layui-tab-brief" lay-filter="demoTitle">
            <ul class="layui-tab-title site-demo-title">
                <li class="layui-this"><i class="layui-icon layui-icon-home"
                                          style="font-size: 20px; color: #1E9FFF;"></i>&nbsp历史订单
                </li>
            </ul>
        </div>

        <table class="layui-hide" id="orders" lay-filter="orders"></table>
    </div>
    <div class="layui-footer" align="center">© xyjy.com 2019-2020 MYJ.All Right Reserved.</div>
</div>
<%--图片模板--%>
<script type="text/html" id="imgtmp">
    <img src="{{d.picture}}"/>
</script>

<script>
    //JavaScript代码区域
    layui.use(['jquery', 'table', 'layer', 'element', 'carousel', 'form', 'upload'], function () {
        var layer = layui.layer;
        var form = layui.form;
        var element = layui.element;
        var table = layui.table;

        //方法级渲染
        table.render({
            elem: '#orders'  //绑定table表格
            , height: 450
            , skin: 'line' //行边框风格
            , even: true //开启隔行背景
            , size: 'lg' //da尺寸的表格
            , totalRow: true
            , toolbar: '#toolbarDemo'
            , url: '/orders/historyOrders' //后台springmvc接收路径
            , page: true    //true表示分页
            , limit: 10
            , id: 'ordersTable'
            , cols: [[
                {field: 'goodsName', title: '商品名称', width: 130, edit: 'text'}
                , {field: 'type', title: '种类', width: 120}
                , {field: 'describes', title: '描述', width: 320}
                , {field: 'picture', title: '图片', width: 140, templet: "#imgtmp", totalRowText: '合计'}
                , {field: 'price', title: '价格', width: 120, edit: 'text', sort: true, totalRow: true}
                ,{field: 'state', title: '订单状态', align: 'center',width: 120,templet: function (d) {
                        if (d.state === '1'){
                            return '<span class="layui-badge layui-bg-green">完成</span>\
                            <a class="layui-btn layui-btn-xs" lay-event="comment">留言</a>'
                        }else {
                            return '<span class="layui-badge">未完成</span>'
                        }
                    }}
            ]]
        });

        //监听表格行点击
        table.on('tr', function (obj) {
            console.log(obj)
        });

        //监听表格复选框选择
        table.on('checkbox(test)', function (obj) {
            console.log(obj)
        });

        //监听表格单选框选择
        table.on('radio(test2)', function (obj) {
            console.log(obj)
        });

        //监听单元格编辑
        table.on('edit(test2)', function (obj) {
            var value = obj.value //得到修改后的值
                , data = obj.data //得到所在行所有键值
                , field = obj.field; //得到字段

        });
//监听数据操作(其中tableID就是页面中的lay-filter)
        table.on('tool(orders)', function (obj) {
            var data = obj.data;
            <%--var userid = "${sessionScope.user.id}";--%>
            if (obj.event === 'comment') {
                $.ajax({
                    url: '/comment/goWriteComment',
                    type: "post",
                    contentType: 'application/json',
                    dataType: 'json',
                    data: JSON.stringify(data),
                    success: function(d) {
                        if (d.state == 1) {
                            layer.msg(d.msg,
                                {
                                    icon: 1,
                                    shade: 0.3,
                                    time: 800,
                                    end: function () {
                                        location.href = "/page/goods/detail";
                                    }
                                });
                        } else {
                            layer.msg(d.msg,
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
            }

        });
        var $ = layui.$, active = {};
    });

</script>
</body>
</html>