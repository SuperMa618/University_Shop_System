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
                        <dd class="layui-this">
                            <a href="/page/user/submitGoods">提交商品</a>
                        </dd>
                        <dd>
                            <a href="">已发布商品</a>
                        </dd>
                        <dd>
                            <a href="">已购商品</a>
                        </dd>
                    </dl>
                </li>
                <li class="layui-nav-item"><a>我的订单</a>
                </li>
                <li class="layui-nav-item"><a>我的留言</a></li>
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
                                          style="font-size: 20px; color: #1E9FFF;"></i>&nbsp提交商品
                </li>
            </ul>
        </div>

        <div class="layui-tab layui-tab-brief layui-col-md4"
             lay-filter="docDemoTabBrief"
             style=" background: white; position: fixed; left: 33%; top: 15%; ">
            <div class="layui-tab-content">
                <!--  填写商品信息界面-->
                <div class="layui-tab-item layui-show">
                    <form class="layui-form layui-col-md9 "
                          lay-filter="login-student-form">
                        <div class="layui-form-item " style="margin-top: 25px"></div>

                        <div class="layui-form-item layui-col-md-offset1">
                            <label class="layui-form-label ">商品名称：</label>
                            <div class="layui-input-block ">
                                <input id="goodsname" type="text" name="goodsname"
                                       lay-verify="required" autocomplete="off"
                                       class="layui-input" maxlength="20">
                            </div>
                        </div>
                        <div class="layui-form-item layui-col-md-offset1">
                            <label class="layui-form-label ">商品种类：</label>
                            <div class="layui-input-block ">
                                <select name="type" lay-verify="" lay-filter="test" id="type">
                                    <option value="">请选择商品种类</option>
                                    <option value="电子产品">电子产品</option>
                                    <option value="书籍资料">书籍资料</option>
                                    <option value="时尚装饰">时尚装饰</option>
                                    <option value="男装鞋包">男装鞋包</option>
                                    <option value="女装鞋包">女装鞋包</option>
                                    <option value="护肤彩妆">护肤彩妆</option>
                                    <option value="生活用品">生活用品</option>
                                    <option value="运动用品">运动用品</option>
                                    <option value="寝室用品">寝室用品</option>
                                    <option value="限时票卡">限时票卡</option>
                                    <option value="其他">其他</option>
                                </select>
                            </div>
                        </div>
                        <div class="layui-form-item layui-col-md-offset1">
                            <label class="layui-form-label">商品价格：</label>
                            <div class="layui-input-block">
                                <input id="price" type="text" name="price"
                                       onkeyup="value=value.replace(/[^\d]/g,'')"
                                       autocomplete="off" class="layui-input" maxlength="6">
                            </div>
                        </div>
                        <div class="layui-form-item layui-form-text layui-col-md-offset1">
                            <label class="layui-form-label">商品描述：</label>
                            <div class="layui-input-block">
                                <textarea id="describe" placeholder="请输入内容，如：几成新。。使用时间。。"
                                          lay-verify="required" class="layui-textarea"
                                          maxlength="50"></textarea>
                            </div>
                        </div>
                        <%--上传图片--%>
                        <input type="hidden" id="images" name="images" value="">
                        <div class="layui-form-item layui-col-md-offset1">
                            <label class="layui-form-label ">商品图片:</label>
                            <div class="layui-upload">
                                <button type="button" class="layui-btn" id="image">上传图片</button>
                                <div class="layui-upload-list">
                                    <img id="demo1" style="height: 50px;margin-left: 40%">
                                    <p id="demoText" style="margin-left: 40%"></p>
                                </div>
                            </div>
                        </div>
                        <div id="inputAndcode"
                             class="layui-form-item layui-col-md-offset1">
                            <label class="layui-form-label ">验证码：</label>
                            <div class="layui-input-block">
                                <input id="code" type="text" name="code" lay-verify="required"
                                       placeholder="" autocomplete="off"
                                       class="layui-input layui-col-md8" maxlength="4"
                                       style="width: 50%;"> <img id="cpacha-img1"
                                                                 src="../../system/get_cpacha?vl=4&w=144&h=37&type=accountLoginCpacha"
                                                                 class="code" style="cursor: pointer;" width="100px"/>
                                <!-- position: absolute; right: 0px; top: 150px; -->
                            </div>
                        </div>

                        <div class="layui-form-item"
                             style="text-align: center; margin-top: 40px;">
                            <div class="layui-input-block">
                                <button class="layui-btn " lay-submit
                                        lay-filter="goods-form-submit">确认提交
                                </button>
                                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                            </div>
                        </div>
                    </form>
                </div>

            </div>
        </div>
    </div>
    <div class="layui-footer" align="center">© xyjy.com 2019-2020 MYJ.All Right Reserved.</div>
</div>

<script>
    //JavaScript代码区域
    layui.use(['jquery', 'layer', 'element', 'carousel', 'form', 'upload'], function () {
        var $ = layui.$;
        var layer = layui.layer;
        var form = layui.form;
        var element = layui.element;
        var upload = layui.upload;

        //普通图片上传
        var uploadInst = upload.render({
            elem: '#image'
            , url: '/user/upload'
            , accept: 'images'
            , size: 50000
            , before: function (obj) {
                obj.preview(function (index, file, result) {

                    $('#demo1').attr('src', result);
                });
            }
            , done: function (res) {
                //如果上传失败
                if (res.code > 0) {
                    return layer.msg('上传失败');
                }
                //上传成功
                var demoText = $('#demoText');
                demoText.html('<span style="color: #4cae4c;">上传成功</span>');
                // var fileupload = $(".image");
                // fileupload.attr("value", res.data.src);
                $("#images").val(res.data.src);
                // layer.alert($("#images").val());
            }
            , error: function () {
                //演示失败状态，并实现重传
                var demoText = $('#demoText');
                demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
                demoText.find('.demo-reload').on('click', function () {
                    uploadInst.upload();
                });
            }
        });

        /* 设置验证码宽高 */
        var input_width = $("input[name=code]").width();//获取当前文本框的长度
        var code_width = parseInt(input_width + 10);
        //console.log(input_width);
        $(".code").attr({
            "width": code_width,
            "height": "39px"
        });
        /*加载验证码  */
        $('.code').on('click',
            function () {
                $(".code")
                    .attr(
                        "src",
                        '../../system/get_cpacha?vl=4&w=144&h=40&type=accountLoginCpacha&t='
                        + new Date()
                            .getTime());
            });

        // 用户注册表单提交
        form.on('submit(goods-form-submit)', function (data) {
            // ajax方式添加用户
            var data = {
                goodsName: $('#goodsname').val(),
                price: $('#price').val(),
                type: $('#type').val(),
                describe: $('#describe').val(),
                images: $('#images').val(),
                code: $('#code').val()
            }
            $.ajax({
                url: "/goods/submitGoods",
                type: "POST",
                data: JSON.stringify(data),
                contentType: 'application/json',
                dataType: 'json',
                success: function (data) {
                    console.log(data)
                    if (data.state == 1) {
                        //创建永久化数据
                        layer.open({
                            title: '提示',
                            content: data.msg,
                            shade: 0.3,
                            time: 3000,
                            end: function () {
                                location.href = "/page/user/index";
                            }
                        });
                        //location.href = "/student/index";
                    } else {
                        parent.layer.msg(data.msg,
                            {
                                icon: 2,
                                shade: 0.3,
                                time: 2000
                            });
                        $('.code').click();
                    }
                },
                error: function () {
                    layer.open({
                        title: '系统提示',
                        content: '发生未知错误，请联系管理员！'
                    });
                    $('.code').click();
                }
            });
            // 阻止表单跳转
            return false;
        });

    });

</script>
</body>
</html>