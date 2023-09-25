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
    <style>
        .am-selected-status {
            font-size: 13px;
            color: #999;
        }

        .am-selected {
            width: 100%;
        }
    </style>
    <script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.7.1/jquery.js"></script>
</head>
<body data-type="generalComponents">

<%--
    @author: JoneElmo
    @date: 2023-9-24 21:45
--%>

<%--ajax处理异步请求--%>
<script>
    $(document).ready(function (){

        /*进入页面进行用户认证进度校验*/
        $.ajax({
            type:"get",
            url:"enterprise",
            success:function (data){
                if(data.data[1] == null ){
                    alert("未认证")
                    $("#userAccount").text(data.data[0].account)
                    $("#userPhone").text(data.data[0].phone)
                    /*判断企业信息为null，则证明未进行认证，须进行认证再回显数据*/
                }else {
                    alert("已认证")
                    /*已进行认证直接回显数据*/
                    $(".am-form-group").find("input").prop("readonly",true)
                    $("#userAccount").text(data.data[0].account)
                    $("#userPhone").text(data.data[0].phone)
                    $("[name = 'idcardName']").val(data.data[0].idcardName)

                }
            }
        })
        /*点击企业信息认证界面的提交按钮，提交认证信息。接收回显结果并显示*/
        $("button[name='submit']").on("click",function (event) {
            console.log("点击了提交按钮")
            /*阻止超链接的跳转，执行jquery语句*/
            event.preventDefault();
            /*发送put请求，处理企业认证信息*/
            var enterpriseId = null;
            var address1 = $("input[name='address']").val()
            var address2 = $("textarea[name='addressDetails']").val()
            /*拼接两个地址信息*/
            var address = address1 + "#" + address2
            console.log(address)
            $.ajax({
                url: "http://localhost:8080/enterprise",
                type: "put",
                dataType: "json",
                async: false,
                data:JSON.stringify({
                    name: $("input[name='name']").val(),
                    socialUniformCode: $("input[name='socialUniformCode']").val(),
                    email:$("input[name='email']").val(),
                    phone:$("input[name='phone']").val(),
                    address: address,
                    scale:$("input[name='scale']").val(),
                    fax:$("input[name='fax']").val()
                }),
                success:function (result){
                    console.log(result)
                    enterpriseId = result.id

                    console.log(result)
                    console.log("回显的企业id:"+enterpriseId)
                    alert("数据提交成功!")
                    /*回显数据*/
                    $("input[name='name']").prop("readonly",true)
                    $("input[name='socialUniformCode']").prop("readonly",true)
                    $("input[name='email']").prop("readonly",true)
                    $("input[name='phone']").prop("readonly",true)
                    /*设置下拉列表只读（转换为Input）*/
                    let select = $("select[name='scale']")
                    let input = $("<input>")
                    input.attr("type","text")
                    input.val(select.find("option:selected").text())
                    input.prop("readonly",true)
                    $("#sel").replaceWith(input)
                    /*设置其他输入框只读*/
                    $("input[name='fax']").prop("readonly",true)
                    $("input[name='address']").prop("readonly",true)
                    $("textarea[name='addressDetails']").prop("readonly",true)
                    $("input[name='idcardName']").prop("readonly",true)
                    $("input[name='idcardNo']").prop("readonly",true)
                },
                error:function (result) {
                    console.log(result)
                }
            })

            /*获取输入的姓名和身份证号*/
            alert("获取id："+enterpriseId)
            var idcardName = $("input[name='idcardName']").val()
            var idcardNo = $("input[name='idcardNo']").val()
            /*发送post请求，处理用户认证信息*/
            $.ajax({
                url: "http://localhost:8080/enterprise",
                type: "post",
                dataType: "json",
                data:JSON.stringify({
                    /*put请求回显的企业id信息*/
                    id : enterpriseId,
                    /*输入框中的企业姓名信息*/
                    name : $("input[name='name']").val(),
                    idcardName : idcardName,
                    idcardNo : idcardNo
                }),
                success:function (result) {
                    console.log(result)
                },
                error:function (result){
                    console.log(result)
                }
            })
        })

        /*退出登录按钮*/
        $("#logoutButton").click(function (){
            $.ajax({
                type:"delete",
                url:"login",
                success:function (data){
                    if (data.data){
                        alert(data.msg)
                        window.location.href="http://localhost:8080"
                    }
                }
            })
        })








    })
</script>


<header class="am-topbar am-topbar-inverse admin-header">
    <button class="am-topbar-btn am-topbar-toggle am-btn am-btn-sm am-btn-success am-show-sm-only"
            data-am-collapse="{target: '#topbar-collapse'}"><span class="am-sr-only">导航切换</span> <span
            class="am-icon-bars"></span></button>
    <div class="am-collapse am-topbar-collapse" id="topbar-collapse">
        <ul class="am-nav am-nav-pills am-topbar-nav am-topbar-right admin-header-list tpl-header-list">
            <li class="am-dropdown" data-am-dropdown data-am-dropdown-toggle>
                <a class="am-dropdown-toggle tpl-header-list-link" href="javascript:;">
                    <span class="tpl-header-list-user-nick">禁言小张</span><span class="tpl-header-list-user-ico"> <img
                        src="assets/img/user01.png"></span>
                </a>
                <ul class="am-dropdown-content">
                    <li><a href="login-page" id="logoutButton"><span class="am-icon-power-off"></span> 退出</a></li>
                </ul>
            </li>
        </ul>
    </div>
</header>
<div class="tpl-page-container tpl-page-header-fixed">
    <div class="tpl-left-nav tpl-left-nav-hover">
        <div class="tpl-left-nav-list">
            <ul class="tpl-left-nav-menu">
                <li class="tpl-left-nav-item">
                    <a href="javascript:;" class="nav-link tpl-left-nav-link-list active">
                        <i class="am-icon-wpforms"></i>
                        <span>企业管理</span>
                        <i class="am-icon-angle-right tpl-left-nav-more-ico am-fr am-margin-right tpl-left-nav-more-ico-rotate"></i>
                    </a>
                    <ul class="tpl-left-nav-sub-menu" style="display: block;">
                        <li>
                            <a id="enterpriseInfoVeri" href="#" class="active">
                                <i class="am-icon-angle-right"></i>
                                <span>企业信息认证</span>
                            </a>
                        </li>
                    </ul>
                </li>
                <li class="tpl-left-nav-item">
                    <a href="javascript:;" class="nav-link tpl-left-nav-link-list">
                        <i class="am-icon-table"></i>
                        <span>供应商管理</span>
                        <i class="am-icon-angle-right tpl-left-nav-more-ico am-fr am-margin-right"></i>
                    </a>
                    <ul class="tpl-left-nav-sub-menu">
                        <li>
                            <a href="white-list">
                                <i class="am-icon-angle-right"></i>
                                <span>供应商白名单</span>
                            </a>
                            <a href="black-list">
                                <i class="am-icon-angle-right"></i>
                                <span>供应商黑名单</span>
                            </a>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
    <div class="tpl-content-wrapper">
        <div class="tpl-content-page-title">
            企业认证
        </div>
        <ol class="am-breadcrumb">
            <li class="am-active"><a style="color: #999999;">企业管理</a></li>
            <li class="am-active">企业信息认证</li>
        </ol>
        <div class="tpl-portlet-components">
            <div class="portlet-title">
                <div class="caption font-green bold">
                    企业管理员认证
                </div>
                <div class="tpl-portlet-input tpl-fz-ml">
                    <div class="portlet-input input-small input-inline">
                        <div class="input-icon right">
                        </div>
                    </div>
                </div>
            </div>
            <div class="tpl-block ">
                <div class="am-g tpl-amazeui-form">
                    <div class="am-u-sm-12 am-u-md-9">
                        <form class="am-form am-form-horizontal">
                            <div class="am-form-group">
                                <label for="user-name" class="am-u-sm-3 am-form-label">登录账号</label>
                                <div class="am-u-sm-9">
                                    <span id="userAccount">test</span>
                                </div>
                            </div>
                            <div class="am-form-group">
                                <label for="user-email" class="am-u-sm-3 am-form-label">管理员手机号码</label>
                                <div class="am-u-sm-9">
                                    <span id="userPhone">test</span>
                                </div>
                            </div>
                            <div class="am-form-group">
                                <label for="user-phone" class="am-u-sm-3 am-form-label">管理员姓名</label>
                                <div class="am-u-sm-9">
                                    <input type="text" name="idcardName" placeholder="请输入管理员姓名">
                                </div>
                            </div>
                            <div class="am-form-group">
                                <label for="user-QQ" class="am-u-sm-3 am-form-label">管理员身份证号</label>
                                <div class="am-u-sm-9">
                                    <input type="text" name="idcardNo" placeholder="请入管理员身份证号">
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div class="tpl-portlet-components">
            <div class="portlet-title">
                <div class="caption font-green bold">
                    企业信息
                </div>
                <div class="tpl-portlet-input tpl-fz-ml">
                    <div class="portlet-input input-small input-inline">
                        <div class="input-icon right">
                        </div>
                    </div>
                </div>
            </div>
            <div class="tpl-block ">
                <div class="am-g tpl-amazeui-form">
                    <div class="am-u-sm-12 am-u-md-9">
                        <form class="am-form am-form-horizontal">
                            <div class="am-form-group">
                                <label for="user-name" class="am-u-sm-3 am-form-label">企业名称</label>
                                <div class="am-u-sm-9">
                                    <input type="text" name="name" id="user-name" placeholder="请输入企业名称">
                                </div>
                            </div>
                            <div class="am-form-group">
                                <label for="user-weibo" class="am-u-sm-3 am-form-label">统一社会信用代码</label>
                                <div class="am-u-sm-9">
                                    <input type="text" name="socialUniformCode" id="user-weibo" placeholder="请输入统一社会信用代码">
                                </div>
                            </div>
                            <div class="am-form-group">
                                <label for="user-weibo" class="am-u-sm-3 am-form-label">企业规模</label>
                                <div class="am-u-sm-9">
                                    <div id="sel">
                                        <select name="scale" placeholder="请选择企业规模" data-am-selected>
                                            <option value="4">请选择企业规模</option>
                                            <option value="a">0-20人</option>
                                            <option value="b">20-50人</option>
                                            <option value="d">50-100人</option>
                                            <option value="e">100-500人</option>
                                            <option value="f">500以上</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="am-form-group">
                                <label for="user-email" class="am-u-sm-3 am-form-label">企业邮箱</label>
                                <div class="am-u-sm-9">
                                    <input type="email" name="email" id="user-email" placeholder="请输入企业邮箱">
                                </div>
                            </div>
                            <div class="am-form-group">
                                <label for="user-phone" class="am-u-sm-3 am-form-label">企业电话</label>
                                <div class="am-u-sm-9">
                                    <input type="tel" name="phone" id="user-phone" placeholder="请输入企业电话">
                                </div>
                            </div>
                            <div class="am-form-group">
                                <label for="user-QQ" class="am-u-sm-3 am-form-label">传真</label>
                                <div class="am-u-sm-9">
                                    <input type="number" name="fax" pattern="[0-9]*" id="user-QQ" placeholder="请输入传真">
                                </div>
                            </div>
                            <div class="am-form-group">
                                <label for="user-weibo" class="am-u-sm-3 am-form-label">企业注册地</label>
                                <div class="am-u-sm-9">
                                    <input type="text" name="address" id="user-weibo" placeholder="请企业注册地">
                                </div>
                            </div>
                            <div class="am-form-group">
                                <label for="user-intro" class="am-u-sm-3 am-form-label">企业注册详细地址</label>
                                <div class="am-u-sm-9">
                                    <textarea class="" name="addressDetails" rows="5" id="user-intro" placeholder="请输入企业注册详细地址"></textarea>
                                    <small>250字以内...</small>
                                </div>
                            </div>
                            <div class="am-form-group">
                                <div class="am-u-sm-9 am-u-sm-push-3">
                                    <button name="submit" type="button" class="am-btn am-btn-primary">提 交</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="assets/js/jquery.min.js"></script>
<script src="assets/js/amazeui.min.js"></script>
<script src="assets/js/app.js"></script>

</body>

</html>