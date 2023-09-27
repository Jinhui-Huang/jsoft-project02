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
    <link rel="stylesheet" href="assets/css/message.min.css">
    <style>
        .am-selected-status {
            font-size: 13px;
            color: #999;
        }
        .am-selected {
            width: 100%;
        }
        .am-span{
            color: red;
            position: absolute;
            margin: 0;
            padding: 0;
            left: 100%;
            top: 0;
            width: 200px;
            display: inline-block;
        }
    </style>
</head>
<body data-type="generalComponents">
<header class="am-topbar am-topbar-inverse admin-header">
    <button class="am-topbar-btn am-topbar-toggle am-btn am-btn-sm am-btn-success am-show-sm-only"
            data-am-collapse="{target: '#topbar-collapse'}"><span class="am-sr-only">导航切换</span> <span
            class="am-icon-bars"></span></button>
    <div class="am-collapse am-topbar-collapse" id="topbar-collapse">
        <ul class="am-nav am-nav-pills am-topbar-nav am-topbar-right admin-header-list tpl-header-list">
            <li class="am-dropdown" data-am-dropdown data-am-dropdown-toggle>
                <a class="am-dropdown-toggle tpl-header-list-link" href="javascript:;">
                    <span class="tpl-header-list-user-nick" id="userName"></span><span class="tpl-header-list-user-ico"> <img
                        src="assets/img/user01.png"></span>
                </a>
                <ul class="am-dropdown-content">
                    <li><a href="login-page" id="notAButton"><span class="am-badge am-badge-secondary am-radius">企业ID:</span> ${sessionScope.enterpriseId} </a></li>
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
                            <a  id="white-list">
                                <i class="am-icon-angle-right"></i>
                                <span>供应商白名单</span>
                            </a>
                            <a  id="black-list">
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
                                    <span class="am-span" name="idcardNameSpan"></span>
                                </div>
                            </div>
                            <div class="am-form-group">
                                <label for="user-QQ" class="am-u-sm-3 am-form-label">管理员身份证号</label>
                                <div class="am-u-sm-9">
                                    <input type="text" name="idcardNo" placeholder="请入管理员身份证号">
                                    <span class="am-span" name="idcardNoSpan"></span>
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
                                    <span class="am-span" name="nameSpan"></span>
                                </div>
                            </div>
                            <div class="am-form-group">
                                <label for="user-weibo" class="am-u-sm-3 am-form-label">统一社会信用代码</label>
                                <div class="am-u-sm-9">
                                    <input type="text" name="socialUniformCode" id="user-weibo" placeholder="请输入统一社会信用代码">
                                    <span class="am-span" name="socialUniformCodeSpan"></span>
                                </div>
                            </div>
                            <div class="am-form-group">
                                <label for="user-weibo" class="am-u-sm-3 am-form-label">企业规模</label>
                                <div class="am-u-sm-9">
                                    <div id="sel">
                                        <select name="scale" placeholder="请选择企业规模" data-am-selected>
                                            <option value="4">请选择企业规模</option>
                                            <option value="A">1-19人</option>
                                            <option value="B">20-50人</option>
                                            <option value="C">51-100人</option>
                                            <option value="D">101-200人</option>
                                            <option value="E">201-500人</option>
                                            <option value="F">500以上</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="am-form-group">
                                <label for="user-email" class="am-u-sm-3 am-form-label">企业邮箱</label>
                                <div class="am-u-sm-9">
                                    <input type="email" name="email" id="user-email" placeholder="请输入企业邮箱">
                                    <span class="am-span" name="emailSpan"></span>
                                </div>
                            </div>
                            <div class="am-form-group">
                                <label for="user-phone" class="am-u-sm-3 am-form-label">企业电话</label>
                                <div class="am-u-sm-9">
                                    <input type="tel" name="phone" id="user-phone" placeholder="请输入企业电话">
                                    <span class="am-span" name="phoneSpan"></span>
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

</body>

<%--
    @author: JoneElmo
    @date: 2023-9-24 21:45
--%>

<%--ajax处理异步请求--%>

<script src="assets/js/jquery.min.js"></script>
<script src="assets/js/amazeui.min.js"></script>
<script src="assets/js/app.js"></script>
<script src="assets/js/message.min.js"></script>
<script type="text/javascript">
    window.QMSG_GLOBALS = {
        DEFAULTS: {
            showClose: true,
            timeout: 1000
        }
    };
    Qmsg.success("")
    $(document).ready(function (){
        /*自动进行认证判断并进行数据回显*/
        EchoData();

        $("#white-list").on("click",function (){
            that = this;
            checkApprove()
        })
        $("#black-list").on("click",function (){
            that = this;
            checkApprove()
        })
        /*检测认证跳转*/
        function checkApprove(){
            $.ajax({
                type:"get",
                url:"enterprise",
                success:function (result){
                    if (result.data[1] == null){
                        Qmsg.warning("请先认证信息")
                    }else {
                        let href = "http://localhost:8080/"+that.id
                        window.location.href = href;
                    }
                }
            })
        }
        /*进入页面进行用户认证进度校验*/
        function EchoData() {
            $.ajax({
                type:"get",
                url:"enterprise",
                success:function (result){
                    $("#userName").text(result.data[0].name)
                    $("#userAccount").text(result.data[0].account)
                    $("#userPhone").text(result.data[0].phone)
                    if(result.data[1] == null ){
                        Qmsg.loading("请进行信息认证")
                        /*判断企业信息为null，则证明未进行认证，须进行认证再回显数据*/
                    }else {
                        Qmsg.success("已认证")
                        /*已进行认证直接回显数据*/
                        $(".am-form-group").find("input").prop("readonly",true)
                        $("[name = 'idcardName']").val(result.data[0].idcardName)
                        $("[name = 'idcardNo']").val(result.data[0].idcardNo)
                        $("[name = 'name']").val(result.data[1].name)
                        $("[name = 'socialUniformCode']").val(result.data[1].socialUniformCode)
                        $("[name = 'email']").val(result.data[1].email)
                        $("[name = 'phone']").val(result.data[1].phone)
                        $("[name = 'fax']").val(result.data[1].fax)
                        $("[name = 'address']").val(result.data[1].address.split("#")[0])
                        $("textarea[name='addressDetails']").prop("readonly",true)
                        $("[name = 'addressDetails']").val(result.data[1].address.split("#")[1])
                        var scale =  $("option[value = "+result.data[1].scale+"]").text()
                        $("#sel").replaceWith($("<input type='text' value='"+scale+"' readonly>"))
                        $("button[name='submit']").prop("disabled",true)
                    }
                }
            });
        }
        /*表单校验区*/
        /*校验身份证名字不超过五个*/
        function checkIdcardName() {
            // 获取输入框的内容
            var inputValue = $("input[name = 'idcardName']").val();
            // 定义你的正则表达式
            var chineseRegex = /^[\u4e00-\u9fa5]{1,5}$/;
            // 使用正则表达式进行校验
            if (chineseRegex.test(inputValue)) {
                // 符合规则，显示校验通过的消息
                $("[name='idcardNameSpan']").text("");
                $("button[name='submit']").prop("disabled",false)
            } else {
                // 不符合规则，显示校验失败的消息
                $("[name='idcardNameSpan']").text("请输入不超过五位的汉字");
                $("button[name='submit']").prop("disabled",true)
            }
            return ($("[name='idcardNameSpan']").text() === "");
        }
        $("input[name = 'idcardName']").blur(function (){
            checkIdcardName()
        })
        /*校验身份证号*/
        function checkIdcardNo(){
            var inputValue = $("input[name = 'idcardNo']").val();
            var Regex = /^\d{17}[\dX]$/;
            if (Regex.test(inputValue)) {
                $("[name='idcardNoSpan']").text("");
                $("button[name='submit']").prop("disabled",false)
            } else {
                $("[name='idcardNoSpan']").text("身份证号码格式不正确");
                $("button[name='submit']").prop("disabled",true)
            }
            return ($("[name='idcardNoSpan']").text() === "");
        }
        $("input[name = 'idcardNo']").blur(function (){
            checkIdcardNo()
        })
        /*校验企业电话*/
        function checkPhone() {
            var inputValue = $("input[name = 'phone']").val();
            var Regex = /^(0\d{2,3}-\d{7,8}(-\d{1,6})?|[1-9]\d{6,7}(-\d{1,6})?|1[3-9]\d{9})$/;
            if (Regex.test(inputValue)) {
                $("[name='phoneSpan']").text("");
                $("button[name='submit']").prop("disabled",false)
            } else {
                $("[name='phoneSpan']").text("号码格式不正确");
                $("button[name='submit']").prop("disabled",true)
            }
            return ($("[name='phoneSpan']").text() === "");
        }
        $("input[name = 'phone']").blur(function (){
            checkPhone()
        })
        /*企业名称重复校验*/
        function checkName() {
            $.ajax({
                type:"delete",
                url:"enterprise",
                dataType: "json",
                contentType:"application/json",
                async:false,
                data:JSON.stringify({
                    name:$("input[name='name']").val().trim()
                }),
                success:function (result){
                    /*如果为true则数据重复*/
                    if (result.data){
                        $("[name='nameSpan']").text("企业名称重复")
                        $("button[name='submit']").prop("disabled",true)
                    }else {
                        $("[name='nameSpan']").text("")
                        $("button[name='submit']").prop("disabled",false)
                    }
                }
            })
            return ($("[name='nameSpan']").text() === "");
        }
        $("input[name = 'name']").blur(function (){
            checkName()
        })
        /*校验统一信用代码是否重复*/
        function checkSocialUniformCode() {
            $.ajax({
                url:"enterprise",
                type:"delete",
                dataType: "json",
                contentType:"application/json",
                async:false,
                data:JSON.stringify({
                    socialUniformCode:$("input[name='socialUniformCode']").val().trim()
                }),
                success:function (result){
                    /*如果为true则数据重复*/
                    if (result.data){
                        $("[name='socialUniformCodeSpan']").text("企业统一信用代码重复")
                        $("button[name='submit']").prop("disabled",true)
                    }else {
                        $("[name='socialUniformCodeSpan']").text("")
                        $("button[name='submit']").prop("disabled",false)
                    }
                }
            })
            return ($("[name='socialUniformCodeSpan']").text() === "");
        }
        $("[name = 'socialUniformCode']").blur(function (){
            checkSocialUniformCode()
        })
        /*邮箱重复和格式校验*/
        function checkEmail() {
            var inputValue = $("input[name = 'email']").val();
            var Regex = /^[a-zA-Z0-9]+@[a-zA-Z0-9]+\.(com|cn)$/;
            if (Regex.test(inputValue)) {
                $("[name='emailSpan']").text("");
                /*格式正确再进行重复校验*/
                $.ajax({
                    url:"enterprise",
                    type:"delete",
                    contentType:"application/json",
                    async:false,
                    data:JSON.stringify({
                        email:$("input[name='email']").val()
                    }),
                    success:function (result){
                        /*如果为true则数据重复*/
                        if (result.data){
                            $("[name='emailSpan']").text("企业邮箱重复")
                            $("button[name='submit']").prop("disabled",true)
                        }else {
                            $("[name='emailSpan']").text("")
                            $("button[name='submit']").prop("disabled",false)
                        }
                    }
                })
            } else {
                $("[name='emailSpan']").text("邮箱格式不正确，请以com/cn结尾");
                $("button[name='submit']").prop("disabled",true)
            }
            return ($("[name='emailSpan']").text() === "");
        }
        $("[name = 'email']").blur(function (){
            checkEmail()
        })


        /*点击企业信息认证界面的提交按钮，提交认证信息。接收回显结果并显示*/
        $("button[name='submit']").on("click",function (event) {
            console.log("点击了提交按钮")
            if (checkSocialUniformCode() &&
                checkEmail() &&
                checkName() &&
                checkIdcardName() &&
                checkIdcardNo() &&
                checkPhone())
            {
                $("button[name='submit']").prop("disabled",false)
            }else {
                $("button[name='submit']").prop("disabled",true)
                return;
            }
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
                async:false,
                data:JSON.stringify({
                    name: $("input[name='name']").val(),
                    socialUniformCode: $("input[name='socialUniformCode']").val(),
                    email:$("input[name='email']").val(),
                    phone:$("input[name='phone']").val(),
                    address: address,
                    scale:$("select[name='scale']").val(),
                    fax:$("input[name='fax']").val()
                }),
                success:function (result){
                    /*企业认证判断成功再进行用户信息认证请求的发送*/
                    if (result.data[0]){
                        enterpriseId = result.data[1].id
                        /*获取输入的姓名和身份证号*/
                        var idcardName = $("input[name='idcardName']").val()
                        var idcardNo = $("input[name='idcardNo']").val()
                        /*发送post请求，处理用户认证信息*/
                        $.ajax({
                            url: "http://localhost:8080/enterprise",
                            type: "post",
                            dataType: "json",
                            data:JSON.stringify({
                                /*put请求回显的企业id信息*/
                                enterpriseId : enterpriseId,
                                /*输入框中的企业姓名信息*/
                                enterpriseName : $("input[name='name']").val(),
                                idcardName : idcardName,
                                idcardNo : idcardNo
                            }),
                            success:function (result) {
                                if (result.data){
                                    EchoData();
                                }else {
                                    Qmsg.warning(result.msg)
                                }
                            },
                            error:function (result){
                                console.log(result)
                            }
                        })
                    }else {
                        Qmsg.warning(result.msg)
                    }
                },
                error:function (result) {
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
                        Qmsg.success(data.msg)
                        window.location.href="http://localhost:8080"
                    }
                }
            })
        })
    })
</script>
</html>