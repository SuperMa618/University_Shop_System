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
                <li class="layui-nav-item layui-nav-itemed"><a href="/page/index">返回主页</a></li>
                <li class="layui-nav-item layui-nav-itemed"><a class="javascript:;" href="javascript:;">我的信息</a>
                    <dl class="layui-nav-child">
                        <dd class="layui-this">
                            <a href="/page/user/update">修改信息</a>
                        </dd>
                        <dd>
                            <a href="">查看信息</a>
                        </dd>
                    </dl>
                </li>
                <li class="layui-nav-item layui-nav-itemed"><a >我的商品</a>
                    <dl class="layui-nav-child">
                        <dd>
                            <a href="/page/user/submitGoods">提交商品</a>
                        </dd>
                        <dd>
                            <a href="">收藏夹</a>
                        </dd>
                        <dd class="layui-this">
                            <a href="">购物车</a>
                        </dd>
                        <dd>
                            <a href="">已发布商品</a>
                        </dd>
                        <dd>
                            <a href="">已购商品</a>
                        </dd>
                    </dl>
                </li>
                <li class="layui-nav-item"><a >我的订单</a>
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
                                          style="font-size: 20px; color: #1E9FFF;"></i>&nbsp购物车
                </li>
            </ul>
        </div>

        <table class="layui-hide" id="Cart" lay-filter="Cart"></table>
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
            elem: '#Cart'  //绑定table表格
            , height: 450
            ,skin: 'line' //行边框风格
            ,even: true //开启隔行背景
            ,size: 'lg' //da尺寸的表格
            , url: '/goods/selectCart' //后台springmvc接收路径
            , page: true    //true表示分页
            /* page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
             layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'] //自定义分页布局
                 //,curr: 5 //设定初始在第 5 页
                 ,groups: 3 //只显示 1 个连续页码
                 ,first: true //不显示首页
                 ,last: true //不显示尾页
              }*/
//            ,where:{rows:limit} //传参数
            , limit: 10
            , id: 'cartTable'
            , toolbar: '#toolbarDemo'
            , cols: [[
                {type: 'checkbox', fixed: 'left'}
                , {field: 'id', title: 'id', width: 80, fixed: 'left', unresize: true, sort: true}
                , {field: 'goodsName', title: '商品名称', width: 120, edit: 'text'}
                , {field: 'price', title: '价格', width: 80, edit: 'text', sort: true}
                , {field: 'type', title: '种类', width: 100}
                , {field: 'describes', title: '描述', width: 380}
                , {field: 'picture', title: '图片', width: 120,templet:"#imgtmp"}
                , {
                    fixed: 'right', width: 120, align: 'center', templet: function () {
                        return ' <a class="layui-btn layui-btn-xs" lay-event="detail">查看</a>\
                                   <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>';
                    }
                }
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
        table.on('tool(Cart)', function (obj) {
            var data = obj.data;
            if (obj.event === 'del') {
                layer.confirm('真的删除行么', function (index) {
                    //确认删除发送ajax请求
                    $.ajax({
                        url: '/goods/cartDelete',
                        type: "get",
                        data: {
                            "goodsId": data.id,
                            "userId": userid
                        },
                        success: function(d) {
                            if (d.state == 1) {
                                obj.del();
                                parent.layer.msg(d.msg,
                                    {
                                        icon: 1,
                                        shade: 0.3,
                                        time: 1500
                                    });
                            } else {
                                layer.msg("删除失败!", {icon: 5});
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
            } else if (obj.event === 'detail') {
                layer.prompt({
                    formType: 2
                    , value: data.username
                }, function (value, index) {
                    obj.update({
                        username: value
                    });
                    layer.close(index);
                });
            }

        });


        var $ = layui.$, active = {
            getCheckData: function () {//获取选中数据
                var checkStatus = table.checkStatus('test')
                    , data = checkStatus.data;
                layer.alert(JSON.stringify(data));
            }
            , getCheckLength: function () {//获取选中数目
                var checkStatus = table.checkStatus('test')
                    , data = checkStatus.data;
                layer.msg('选中了：' + data.length + ' 个');
            }
            , isAll: function () {
                验证是否全选
                var checkStatus = table.checkStatus('test');
                layer.msg(checkStatus.isAll ? '全选' : '未全选')
            }
            , parseTable: function () {
                table.init('parse-table-demo', {
                    limit: 3
                });
            }
            , add: function () {
                table.addRow('test')
            }
            , delete: function () {
                layer.confirm('确认删除吗？', function (index) {
                    table.deleteRow('test')
                    layer.close(index);
                });
            }
            , reload: function () {
                var keyWord = $("#keyWord").val();
                var keyType = $("#key_type option:selected").val();
                table.reload('cartTable', {
                    where: {keyWord: keyWord, keyType: keyType}
                });
            }
        };
        $('i').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
        $('.layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
        $('.demoTable .layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

        /* 设置验证码宽高 */
        var input_width = $("input[name=code]").width();//获取当前文本框的长度
        var code_width = parseInt(input_width + 10);
        //console.log(input_width);
        $(".code").attr({
            "width": code_width,
            "height": "39px"
        });

    });

</script>
</body>
</html>