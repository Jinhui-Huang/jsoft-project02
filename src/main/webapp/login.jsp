<!doctype html>
<html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>供应商管理系统</title>
    <meta name="description" content="这是一个 index 页面">
    <meta name="keywords" content="index">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <link rel="icon" type="image/png" href="assets/i/favicon.png">
    <link rel="apple-touch-icon-precomposed" href="assets/i/app-icon72x72@2x.png">
    <meta name="apple-mobile-web-app-title" content="Amaze UI"/>
    <link rel="stylesheet" href="assets/css/amazeui.min.css"/>
    <link rel="stylesheet" href="assets/css/admin.css">
    <link rel="stylesheet" href="assets/css/app.css">
    <script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.7.1/jquery.js"></script>
    <script type="text/javascript" >
        $.ajax({
            type: "get",
            url: "login",
            async: false,
            success: function (data){
                if (data.data) {
                    window.location.href="http://localhost:8080/info-certification"
                } else {
                    alert(data.msg)
                }
            }
        })

        $(document).ready(function (){
            /*发送请求进行异步验证*/

            $("#loginButton").click(function (){
                $.ajax({
                    type: "POST",
                    url: "login",
                    dataType: "json",
                    contentType:"application/json",
                    data:JSON.stringify({
                        "account":$("#doc-ipt-email-1").val(),
                        "password":$("#doc-ipt-pwd-1").val(),
                    }),
                    async: true,
                    success: function (data){
                        if (data.data) {
                            window.location.href="http://localhost:8080/info-certification"
                        } else {
                            alert(data.msg)
                        }
                    }
                })
            })

        });
    </script>
</head>

<body data-type="login">

<div class="am-g myapp-login">
    <div class="myapp-login-logo-block  tpl-login-max">
        <div class="myapp-login-logo-text">
            <div class="myapp-login-logo-text">
                供应商管理系统
            </div>
        </div>
        <div class="am-u-sm-10 login-am-center">
            <form class="am-form">
                <fieldset>
                    <div class="am-form-group">
                        <input type="email" class="" id="doc-ipt-email-1" placeholder="请输入账号">
                    </div>
                    <div class="am-form-group">
                        <input type="password" class="" id="doc-ipt-pwd-1" placeholder="请输入密码">
                    </div>
                    <a <%--href="info-certification"--%> style="margin-top: 20px;color:#fff;font-size:18px" id="loginButton">
                        <div style="    width: 100%;height: 40px;text-align: center;
						border-radius: 6px;
						background: #53d192;
						border: none;
						color: #fff;
						font-size: 18px;
						line-height: 40px;margin-top: 20px;">登录
                        </div>
                    </a>
                </fieldset>
            </form>
        </div>
    </div>
</div>

<script src="assets/js/jquery.min.js"></script>
<script src="assets/js/amazeui.min.js"></script>
<script src="assets/js/app.js"></script>
<script>

</script>
</body>

</html>