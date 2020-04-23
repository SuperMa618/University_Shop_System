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
                    <c:when test="${admin.adminName!=null}">
                        <a href="javascript:0">&nbsp${admin.adminName}</a>
                        <dl class="layui-nav-child">
                            <dd>
                                <a href="/admin/adminOut"><i class="layui-icon layui-icon-return"
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
<%--                <li class="layui-nav-item layui-nav-itemed"><a href="/page/index">返回主页</a></li>--%>
                <li class="layui-nav-item layui-nav-itemed"><a class="javascript:;" href="javascript:;">信息管理</a>
                    <dl class="layui-nav-child">
                        <dd class="layui-this">
                            <a href="/page/admin/user">用户</a>
                        </dd>
                        <dd>
                            <a href="/page/admin/goods">商品</a>
                        </dd>
                        <dd>
                            <a href="/page/admin/comment">留言</a>
                        </dd>
                        <dd>
                            <a href="/page/admin/carousel">轮播图</a>
                        </dd>
                        <dd>
                            <a href="">校园活动</a>
                        </dd>
                    </dl>
                </li>
                <li class="layui-nav-item layui-nav-itemed"><a href="/page/admin/review">审核</a></li>
                <li class="layui-nav-item"><a>用户反馈</a></li>
            </ul>
        </div>

    </div>

    <div class="layui-body layui-tab-content site-demo site-demo-body">
        <div class="layui-tab layui-tab-brief" lay-filter="demoTitle">
            <ul class="layui-tab-title site-demo-title">
                <li class="layui-this"><i class="layui-icon layui-icon-home"
                                          style="font-size: 20px; color: #1E9FFF;"></i>&nbsp用户管理
                </li>
            </ul>
        </div>

        <div class="searchTable">
            搜索用户：
            <div class="layui-inline">
                <input class="layui-input" id="keyword" name="keyword" id="demoReload" placeholder="请输入用户名" autocomplete="off">
            </div>
            <button class="layui-btn" data-type="reload">搜索</button>
        </div>
        <table class="layui-hide" id="User" lay-filter="User"></table>
    </div>
    <div class="layui-footer" align="center">© xyjy.com 2019-2020 MYJ.All Right Reserved.</div>
</div>
<style type="text/css">
    .layui-table-cell{
        text-align:center;
        height: auto;
        white-space: normal;
    }
    .layui-table img{
        max-width:200px
    }
</style>
<%--图片模板--%>
<script type="text/html" id="imgtmp">
    <img src="{{d.head}}" style="height: 50px;"/>
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
            elem: '#User'  //绑定table表格
            , height: 450
            , skin: 'line' //行边框风格
            , even: true //开启隔行背景
            , size: 'lg' //da尺寸的表格
            , totalRow: true
            , toolbar: '#toolbarDemo'
            , url: '/adUser/getUser' //后台springmvc接收路径
            , page: true    //true表示分页
            /* page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
             layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'] //自定义分页布局
                 //,curr: 5 //设定初始在第 5 页
                 ,groups: 3 //只显示 1 个连续页码
                 ,first: true //不显示首页
                 ,last: true //不显示尾页
              }*/
            //,where:{search : $('#keyword').val()} //传参数
            , limit: 10
            , id: 'userTable'
            , cols: [[
                {type: 'checkbox', fixed: 'left'}
                , {field: 'id', title: 'id', width: 80, fixed: 'left', unresize: true, sort: true}
                , {field: 'userName', title: '用户名称', width: 200, edit: 'text'}
                , {field: 'passWord', title: '用户密码', width: 200, edit: 'text'}
                , {field: 'tel', title: '联系电话', width: 200}
                , {field: 'head', title: '头像', width: 200,templet:"#imgtmp"}
                , {
                    fixed: 'right', width: 120, align: 'center', templet: function () {
                        return ' \<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>';
                    }
                }
            ]]
        });
        var $ = layui.$, active = {
            reload: function() {
                var keyword = $('#keyword').val();
                table.reload('userTable', {
                    where: {keyword: keyword}
                });
            }
        };
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
        table.on('tool(User)', function (obj) {
            var data = obj.data;
            if (obj.event === 'del') {
                layer.confirm('删除用户会同时删除该用户的其他记录，确定删除吗？', function (index) {
                    //确认删除发送ajax请求
                    $.ajax({
                        url: '/adUser/delUser',
                        type: "get",
                        data: {
                            "userId": data.id
                        },
                        success: function (d) {
                            if (d.state == 1) {
                                obj.del();
                                parent.layer.msg(d.msg,
                                    {
                                        icon: 1,
                                        shade: 0.3,
                                        time: 1000
                                    });
                            } else {
                                parent.layer.msg(d.msg,
                                    {
                                        icon: 2,
                                        shade: 0.3,
                                        time: 1000
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
                    layer.close(index);
                });
            }
        });
        $('.layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
    });
</script>
</body>
</html>