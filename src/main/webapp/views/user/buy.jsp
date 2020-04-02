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
                        <dd>
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
                <li class="layui-nav-item layui-this"><a >我的订单</a>
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
                                          style="font-size: 20px; color: #1E9FFF;"></i>&nbsp修改信息
                </li>
            </ul>
        </div>

        <div class="layui-tab layui-tab-brief layui-col-md4"
             lay-filter="docDemoTabBrief"
             style=" background: white; position: fixed; left: 33%; top: 15%; ">
            <div class="layui-tab-content">
                <!--  注册界面-->
                <div class="layui-tab-item layui-show">
                    <form class="layui-form layui-col-md9 "
                          lay-filter="login-student-form">
                        <div class="layui-form-item " style="margin-top: 25px"></div>
                        <div class="layui-form-item layui-col-md-offset1">
                            <label class="layui-form-label ">用户名：</label>
                            <div class="layui-input-block ">
                                <input id="username" type="text" name="username"
                                       lay-verify="required"value="${user.userName}" autocomplete="off"
                                       class="layui-input" maxlength="20">
                            </div>
                        </div>

                        <div class="layui-form-item layui-col-md-offset1">
                            <label class="layui-form-label">原密码：</label>
                            <div class="layui-input-block">
                                <input id="password" type="password" name="password"
                                       lay-verify="required" placeholder="请输入密码" class="layui-input"
                                       maxlength="20">
                            </div>
                        </div>
                        <div class="layui-form-item layui-col-md-offset1">
                            <label class="layui-form-label">新密码：</label>
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
                                       value="${user.tel}"
                                       onkeyup="value=value.replace(/[^\d]/g,'')"
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
                                    <c:if test="${user.head != null}">
                                        <img id="demo" style="height: 50px;margin-left: 40%" src="${user.head}">
                                    </c:if>
                                    <c:if test="${user.head == null}">
                                        <img id="demo1" style="height: 50px;margin-left: 40%">
                                    </c:if>
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
                                                                 src="../../system/get_cpacha?vl=4&w=144&h=37&type=accountLoginCpacha"
                                                                 class="code" style="cursor: pointer;" width="100px"/>
                                <!-- position: absolute; right: 0px; top: 150px; -->
                            </div>
                        </div>

                        <div class="layui-form-item"
                             style="text-align: center; margin-top: 40px;">
                            <div class="layui-input-block">
                                <button class="layui-btn " lay-submit
                                        lay-filter="update-form-submit">确认修改
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
        form.on('submit(update-form-submit)', function (data) {
            // ajax方式添加用户
            var data = {
                userName: $('#username').val(),
                opassWord: $('#password').val(),
                npassWord: $('#password1').val(),
                rnpassWord: $('#password2').val(),
                tel: $('#tel').val(),
                images: $('#images').val(),
                code: $('#code2').val()
            }
            $.ajax({
                url: "/user/update",
                type: "POST",
                data: JSON.stringify(data),
                contentType: 'application/json',
                dataType: 'json',
                success: function (data) {
                    console.log(data)
                    if (data.state == 1) {
                        //创建永久化数据
                        layer.open({
                            title: '修改成功！',
                            content: data.msg,
                            shade: 0.3,
                            time: 3000,
                            end: function () {
                                location.href = "/page/index";
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