<!doctype html>
<html>

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
                    <span class="tpl-header-list-user-nick">禁言小张</span><span class="tpl-header-list-user-ico"> <img
                        src="assets/img/user01.png"></span>
                </a>
                <ul class="am-dropdown-content">
                    <li><a href="login-page"><span class="am-icon-power-off"></span> 退出</a></li>
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
                            <a href="info-certification" class="active">
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
                                    usernameTest01
                                </div>
                            </div>
                            <div class="am-form-group">
                                <label for="user-email" class="am-u-sm-3 am-form-label">管理员手机号码</label>
                                <div class="am-u-sm-9">
                                    18133686868
                                </div>
                            </div>
                            <div class="am-form-group">
                                <label for="user-phone" class="am-u-sm-3 am-form-label">管理员姓名</label>
                                <div class="am-u-sm-9">
                                    <input type="text" placeholder="请输入管理员姓名">
                                </div>
                            </div>
                            <div class="am-form-group">
                                <label for="user-QQ" class="am-u-sm-3 am-form-label">管理员身份证号</label>
                                <div class="am-u-sm-9">
                                    <input type="text" placeholder="请入管理员身份证号">
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
                                    <input type="text" id="user-name" placeholder="请输入企业名称">
                                </div>
                            </div>
                            <div class="am-form-group">
                                <label for="user-weibo" class="am-u-sm-3 am-form-label">统一社会信用代码</label>
                                <div class="am-u-sm-9">
                                    <input type="text" id="user-weibo" placeholder="请输入统一社会信用代码">
                                </div>
                            </div>
                            <div class="am-form-group">
                                <label for="user-weibo" class="am-u-sm-3 am-form-label">企业规模</label>
                                <div class="am-u-sm-9">
                                    <select placeholder="请选择企业规模" data-am-selected>
                                        <option value="4">请选择企业规模</option>
                                        <option value="a">0-20人</option>
                                        <option value="b">20-50人</option>
                                        <option value="d">50-100人</option>
                                        <option value="e">100-500人</option>
                                        <option value="f">500以上</option>
                                    </select>
                                </div>
                            </div>
                            <div class="am-form-group">
                                <label for="user-email" class="am-u-sm-3 am-form-label">企业邮箱</label>
                                <div class="am-u-sm-9">
                                    <input type="email" id="user-email" placeholder="请输入企业邮箱">
                                </div>
                            </div>
                            <div class="am-form-group">
                                <label for="user-phone" class="am-u-sm-3 am-form-label">企业电话</label>
                                <div class="am-u-sm-9">
                                    <input type="tel" id="user-phone" placeholder="请输入企业电话">
                                </div>
                            </div>
                            <div class="am-form-group">
                                <label for="user-QQ" class="am-u-sm-3 am-form-label">传真</label>
                                <div class="am-u-sm-9">
                                    <input type="number" pattern="[0-9]*" id="user-QQ" placeholder="请输入传真">
                                </div>
                            </div>
                            <div class="am-form-group">
                                <label for="user-weibo" class="am-u-sm-3 am-form-label">企业注册地</label>
                                <div class="am-u-sm-9">
                                    <input type="text" id="user-weibo" placeholder="请企业注册地">
                                </div>
                            </div>
                            <div class="am-form-group">
                                <label for="user-intro" class="am-u-sm-3 am-form-label">企业注册详细地址</label>
                                <div class="am-u-sm-9">
                                    <textarea class="" rows="5" id="user-intro" placeholder="请输入企业注册详细地址"></textarea>
                                    <small>250字以内...</small>
                                </div>
                            </div>
                            <div class="am-form-group">
                                <div class="am-u-sm-9 am-u-sm-push-3">
                                    <button type="button" class="am-btn am-btn-primary">提 交</button>
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