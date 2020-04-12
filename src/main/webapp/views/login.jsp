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
    <div class="layui-header" style="background-color: #1E9FFF;height: 150px">
        <h1 style="position: fixed; left: 40%; top: 5%; color: whitesmoke;">校园交易系统&nbspBeta1.0</h1>
    </div>

    <div class="layui-body" style="background-color: whitesmoke;left: 0;">
        <div class="layui-row">
            <div class="layui-col-md3" style="margin-top:80px;padding: 20px 15px">
                <a href="/page/index">
                    <button class="layui-btn layui-btn-primary">返回</button>
                </a>
            </div>
            <div class="layui-tab layui-tab-brief layui-col-md4"
                 lay-filter="docDemoTabBrief"
                 style=" background: white; position: fixed; left: 33%; top: 20%; box-shadow: 1px 1px 15px #5a7682;">
                <ul class="layui-tab-title">
                    <li class="layui-this layui-col-md6">登录</li>
                    <li class="layui-col-md6">注册</li>
                </ul>
                <div class="layui-tab-content">
                    <!-- 登录界面 -->
                    <div class="layui-tab-item layui-show">
                        <form class="layui-form layui-col-md9 "
                              lay-filter="report-student-form">
                            <div class="layui-form-item " style="margin-top: 20px"></div>
                            <div class="layui-form-item layui-col-md-offset1">
                                <label class="layui-form-label ">用户名：</label>
                                <div class="layui-input-block ">
                                    <input id="username1" type="text" name="userName"
                                           lay-verify="required" placeholder="请输入用户名" autocomplete="off"
                                           class="layui-input" maxlength="20">
                                </div>
                            </div>
                            <div class="layui-form-item layui-col-md-offset1">
                                <label class="layui-form-label">密码：</label>
                                <div class="layui-input-block">
                                    <input id="password" type="password" name="passWord"
                                           lay-verify="required" placeholder="请输入密码" autocomplete="off"
                                           class="layui-input" maxlength="20">
                                </div>
                            </div>
                            <div id="inputAndcode1"
                                 class="layui-form-item layui-col-md-offset1">
                                <label class="layui-form-label ">验证码：</label>
                                <div class="layui-input-block">
                                    <input id="code1" type="text" name="code" lay-verify="required"
                                           autocomplete="off" class="layui-input layui-col-md8" maxlength="4"
                                           style="width: 50%;"> <img
                                        src="../system/get_cpacha?vl=4&w=144&h=37&type=accountLoginCpacha"
                                        class="code" style="cursor: pointer;" width="100px"/>
                                    <!-- position: absolute; right: 0px; top: 150px; -->
                                </div>
                            </div>
                            <div class="layui-form-item pane">
                                <label class="layui-form-label layui-col-md-offset1">登陆方式：</label>
                                <div class="layui-input-block" id="IsPurchased">
                                    <input id="user" type="radio" name="loginType"
                                           lay-filter="loginType" value="u" title="用户" checked> <input
                                        id="admin" type="radio" name="loginType" lay-filter="loginType"
                                        value="a" title="管理员">
                                </div>
                            </div>
                            <div class="layui-form-item"
                                 style="text-align: center; margin-top: 50px">
                                <div class="layui-input-block">
                                    <button class="layui-btn" lay-submit
                                            lay-filter="login-form-submit">登录
                                    </button>
                                    <button type="reset" class="layui-btn layui-btn-primary cz">重置</button>
                                    <li class="noneAndblock"><a id="upwd" href="javascript:void(0);"
                                                                style="cursor: pointer;">忘记密码？</a></li>
                                    <!-- <button id="button" type="button" class="layui-btn">显示</button> -->
                                </div>
                            </div>
                        </form>
                    </div>

                    <!--  注册界面-->
                    <div class="layui-tab-item">
                        <form class="layui-form layui-col-md9 "
                              lay-filter="register-student-form">
                            <div class="layui-form-item " style="margin-top: 20px"></div>
                            <div class="layui-form-item layui-col-md-offset1">
                                <label class="layui-form-label ">用户名：</label>
                                <div class="layui-input-block ">
                                    <input id="username2" type="text" name="username"
                                           lay-verify="required" placeholder="请输入用户名" autocomplete="off"
                                           class="layui-input" maxlength="20">
                                </div>
                            </div>
                            <div class="layui-form-item layui-col-md-offset1">
                                <label class="layui-form-label">密码：</label>
                                <div class="layui-input-block">
                                    <input id="password1" type="password" name="password1"
                                           lay-verify="required" placeholder="请输入密码" class="layui-input"
                                           maxlength="20">
                                </div>
                            </div>
                            <div class="layui-form-item layui-col-md-offset1">
                                <label class="layui-form-label">确认密码：</label>
                                <div class="layui-input-block">
                                    <input id="password2" type="password" name="password2"
                                           lay-verify="required" placeholder="确认密码" class="layui-input"
                                           maxlength="20">
                                </div>
                            </div>
                            <div class="layui-form-item layui-col-md-offset1">
                                <label class="layui-form-label">电话：</label>
                                <div class="layui-input-block">
                                    <input id="tel" type="text" name="studentid"
                                           placeholder="请输入电话" onkeyup="value=value.replace(/[^\d]/g,'')"
                                           autocomplete="off" class="layui-input" maxlength="11">
                                </div>
                            </div>
                            <%--上传图片--%>
                            <input type="hidden" id="images" name="images" value="">
                            <div class="layui-form-item layui-col-md-offset1">
                                <label class="layui-form-label ">头像:</label>
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
                                    <input id="code2" type="text" name="code" lay-verify="required"
                                           placeholder="" autocomplete="off"
                                           class="layui-input layui-col-md8" maxlength="4"
                                           style="width: 50%;"> <img id="cpacha-img1"
                                                                     src="../system/get_cpacha?vl=4&w=144&h=37&type=accountLoginCpacha"
                                                                     class="code" style="cursor: pointer;"
                                                                     width="100px"/>
                                    <!-- position: absolute; right: 0px; top: 150px; -->
                                </div>
                            </div>

                            <div class="layui-form-item"
                                 style="text-align: center; margin-top: 40px;">
                                <div class="layui-input-block">
                                    <button class="layui-btn " lay-submit
                                            lay-filter="register-form-submit">注册
                                    </button>
                                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>


    <!-- 找回密码弹出层 -->
    <div id="forget-password-layer" style="display: none; padding: 20px">
        <form id="forget-password-form" class="layui-form layui-form-pane"
              lay-filter="update-password-form">
            <div class="layui-form-item">
                <label class="layui-form-label ">用户名：</label>
                <div class="layui-input-block ">
                    <input id="usernameForFind" type="text" name="usernameForFind"
                           lay-verify="required" placeholder="请输入用户名" autocomplete="off"
                           class="layui-input" maxlength="20">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">电话</label>
                <div class="layui-input-block">
                    <input id="telForFind" type="text" name="telForFind" class="layui-input"
                           placeholder="请输入联系电话" lay-verify="required"
                           onkeyup="value=value.replace(/[^\d]/g,'')" autocomplete="off"
                           maxlength="20">
                </div>
            </div>
            <div class="layui-form-item" style="text-align: center;">
                <button class="layui-btn" lay-submit
                        lay-filter="forget-password-form-submit">提交
                </button>
                <button class="layui-btn layui-btn-primary" type="reset">重置</button>
            </div>
        </form>
    </div>
    <!-- 		<div class="layui-footer"
        style="background-color: transparent; text-align: center;">
        底部固定区域
        ------------------------------------------------------------©
        yunyiqing.com - 底部固定区域
    </div> -->
    <div class="layui-footer" style="left: 0px;text-align: center">© xyjy.com 2019-2020 MYJ.All Right Reserved.
    </div>
</div>

<script>
    //JavaScript代码区域
    layui.use(['jquery', 'layer', 'element', 'carousel', 'form', 'upload'], function () {
        var $ = layui.$;
        var layer = layui.layer;
        var form = layui.form;
        var element = layui.element;
        var upload = layui.upload;
        //开启修改密码信息界面
        $('#upwd').on('click', function () {
            layer.open({
                type: 1,
                title: '请输入验证信息',
                skin: 'layui-layer-molv',
                area: ['400px'],
                content: $('#forget-password-layer')
            });
        })
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
                // demoText.html('<span style="color: #4cae4c;">上传成功</span>');
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
                        '../system/get_cpacha?vl=4&w=144&h=40&type=accountLoginCpacha&t='
                        + new Date()
                            .getTime());
            });

        // 登陆表单提交
        form.on('submit(login-form-submit)', function (data) {
            // ajax方式登录
            var data = {
                userName: $('#username1').val(),
                passWord: $('#password').val(),
                code: $('#code1').val(),
                logintype: JSON.stringify(data.field.loginType)
            };
            $.ajax({
                url: "/user/login",
                type: "POST",
                data: JSON.stringify(data),
                contentType: 'application/json',
                dataType: 'json',
                success: function (data) {
                    console.log(data)
                    if (data.state == 1) {
                        parent.layer.msg(data.msg,
                            {
                                icon: 1,
                                shade: 0.4,
                                time: 1200,
                                end: function () {
                                    location.href = "/page/index";
                                }
                            });
                    } else {
                        parent.layer.msg(data.msg,
                            {
                                icon: 2,
                                shade: 0.4,
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

        // 用户注册表单提交
        form.on('submit(register-form-submit)', function (data) {
            // ajax方式添加用户
            var data = {
                userName: $('#username2').val(),
                passWord: $('#password1').val(),
                rpassWord: $('#password2').val(),
                tel: $('#tel').val(),
                images: $('#images').val(),
                code: $('#code2').val()
            }
            $.ajax({
                url: "/user/register",
                type: "POST",
                data: JSON.stringify(data),
                contentType: 'application/json',
                dataType: 'json',
                success: function (data) {
                    console.log(data)
                    if (data.state == 1) {
                        //创建永久化数据
                        layer.open({
                            title: '注册成功！',
                            content: data.msg,
                            shade: 0.4,
                            time: 1000,
                            end: function () {
                                location.href = "/page/index";
                            }
                        });
                        //location.href = "/student/index";
                    } else {
                        parent.layer.msg(data.msg,
                            {
                                icon: 2,
                                shade: 0.4,
                                time: 1000
                            });
                        $('.code').click();
                    }
                },
                error: function () {
                    layer.open({
                        title: '系统提示',
                        content: '发生未知错误，请联系管理员2！'
                    });
                    $('.code').click();
                }
            });
            // 阻止表单跳转
            return false;
        });

        // 密码重置表单提交
        form.on('submit(forget-password-form-submit)', function (data) {
            var data = {
                tel: $('#telForFind').val(),
                userName: $('#usernameForFind').val()
            }
            parent.layer.msg('过程可能需要几秒...', {
                icon: 16,
                // shade: 0.4,
                time: 2000
            });
            $.ajax({
                url: "/user/findpwd",
                type: "POST",
                data: JSON.stringify(data),
                contentType: 'application/json',
                dataType: 'json',
                success: function (data) {
                    if (data.state == 1) {
                        layer.close(layer.index);
                        parent.layer.open({
                            title: '密码',
                            content: data.msg,
                            // shade: 0.4,
                            time: 3000
                        });
                    } else {
                        parent.layer.msg(data.msg,
                            {
                                icon: 2,
                                // shade: 0.3,
                                time: 2000
                            });
                    }
                },
                error: function () {
                    layer.close(layer.index);
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