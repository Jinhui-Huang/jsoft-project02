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
</head>

<body data-type="generalComponents">
<header class="am-topbar am-topbar-inverse admin-header">
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
                    <a href="javascript:;" class="nav-link tpl-left-nav-link-list">
                        <i class="am-icon-wpforms"></i>
                        <span>企业管理</span>
                        <i class="am-icon-angle-right tpl-left-nav-more-ico am-fr am-margin-right"></i>
                    </a>
                    <ul class="tpl-left-nav-sub-menu">
                        <li>
                            <a href="info-certification">
                                <i class="am-icon-angle-right"></i>
                                <span>企业信息认证</span>
                            </a>
                        </li>
                    </ul>
                </li>
                <li class="tpl-left-nav-item">
                    <a href="javascript:;" class="nav-link tpl-left-nav-link-list active">
                        <i class="am-icon-table"></i>
                        <span>供应商管理</span>
                        <i class="am-icon-angle-right tpl-left-nav-more-ico am-fr am-margin-right tpl-left-nav-more-ico-rotate"></i>
                    </a>
                    <ul class="tpl-left-nav-sub-menu" style="display:block">
                        <li>
                            <a href="white-list" class="active">
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
            供应商白名单列表
        </div>
        <ol class="am-breadcrumb">
            <li class="am-active"><a style="color: #999999;">供应商管理</a></li>
            <li class="am-active">供应商白名单</li>
        </ol>
        <div class="tpl-portlet-components">
            <div class="tpl-block">
                <div class="am-g">
                    <div class="am-u-sm-6 am-u-md-2">
                        <div class="am-input-group am-input-group-sm">
                                <span class="am-input-group-btn">
                                    <span style="font-size: 14px;margin-right: 8px;margin-left: 8px">企业名称</span>
                                </span>
                            <input type="text" class="am-form-field" placeholder="&nbsp;&nbsp;请输入企业名称"
                                   style="border: 1px solid #c2cad8;border-radius: 3px;">
                        </div>
                    </div>
                    <div class="am-u-sm-6 am-u-md-3">
                        <div class="am-form-group">
                            <span style="font-size: 14px;">企业评级</span>
                            <select data-am-selected="{btnSize: 'sm'}">
                                <option value="">请选择企业评级</option>
                                <option value="a">1级</option>
                                <option value="b">2级</option>
                                <option value="c">3级</option>
                            </select>
                        </div>
                    </div>
                    <div style="float:right">
                        <div class="am-form-group">
                            <button type="button" class="am-btn am-btn-primary am-btn-sm"
                                    id="doc-prompt-toggle">添加供应商
                            </button>
                        </div>
                    </div>
                </div>
                <div class="am-g">
                    <div class="am-u-sm-12">
                        <form class="am-form">
                            <table class="am-table am-table-striped am-table-hover table-main">
                                <thead>
                                <tr>
                                    <th class="table-id">序号</th>
                                    <th class="table-title">企业名称</th>
                                    <th class="table-type">企业联系人</th>
                                    <th class="table-author am-hide-sm-only">联系方式</th>
                                    <th class="table-date am-hide-sm-only">联系邮箱</th>
                                    <th class="table-date am-hide-sm-only">企业评级</th>
                                    <th class="table-date am-hide-sm-only">更新日期</th>
                                    <th class="table-set">操作</th>
                                </tr>
                                </thead>
                                <tbody id="doc-modal-list">
                                <tr data-id="2">
                                    <td>1</td>
                                    <td class="am-hide-sm-only"><a href="#">腾讯科技</a></td>
                                    <td class="am-hide-sm-only">马化腾</td>
                                    <td class="am-hide-sm-only">18556658588</td>
                                    <td class="am-hide-sm-only">18556658588@163.com</td>
                                    <td class="am-hide-sm-only">
                                        <div class="">
                                            <select data-am-selected="{btnSize: 'xs'}">
                                                <option value="option2" selected>1级</option>
                                                <option value="option3">2级</option>
                                                <option value="option3">3级</option>
                                            </select>
                                        </div>
                                    </td>
                                    <td class="am-hide-sm-only">2021-12-31</td>
                                    <td>
                                        <div class="am-btn-toolbar">
                                            <div class="am-btn-group am-btn-group-xs">
                                                <span class="am-text-secondary am-icon"
                                                      style="margin-left: 20px;cursor:pointer"><span></span>
                                                            添加至黑名单</span>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                                <div class="am-modal am-modal-confirm" tabindex="-1" id="my-confirm">
                                    <div class="am-modal-dialog" style="font-size: 16px;">
                                        <div class="am-modal-hd">提示</div>
                                        <div class="am-modal-bd">将供应商添加黑名单后该企业将无法在平台进行融资，是否继续？
                                        </div>
                                        <div class="am-modal-footer">
                                            <span class="am-modal-btn" data-am-modal-cancel>取消</span>
                                            <span class="am-modal-btn" data-am-modal-confirm>确定</span>
                                        </div>
                                    </div>
                                </div>
                                <tr>
                                    <td>1</td>
                                    <td><a href="#">京东集团</a></td>
                                    <td>刘强东</td>
                                    <td class="am-hide-sm-only">18556658588</td>
                                    <td class="am-hide-sm-only">18556658588@163.com</td>
                                    <td class="am-hide-sm-only">
                                        <div class="">
                                            <select data-am-selected="{btnSize: 'xs'}">
                                                <option value="option2" selected>1级</option>
                                                <option value="option3">2级</option>
                                                <option value="option3">3级</option>
                                            </select>
                                        </div>
                                    </td>
                                    <td class="am-hide-sm-only">2021-12-31</td>
                                    <td>
                                        <div class="am-btn-toolbar">
                                            <div class="am-btn-group am-btn-group-xs">
                                                <span class="am-text-secondary am-icon"
                                                      style="margin-left: 20px;cursor:pointer"><span></span>
                                                            添加至黑名单</span>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td>1</td>
                                    <td><a href="#">搜狐</a></td>
                                    <td>张朝阳</td>
                                    <td class="am-hide-sm-only">18556658588</td>
                                    <td class="am-hide-sm-only">18556658588@163.com</td>
                                    <td class="am-hide-sm-only">
                                        <div class="">
                                            <select data-am-selected="{btnSize: 'xs'}">
                                                <option value="option2">1级</option>
                                                <option value="option3">2级</option>
                                                <option value="option3" selected>3级</option>
                                            </select>
                                        </div>
                                    </td>
                                    <td class="am-hide-sm-only">2021-12-31</td>
                                    <td>
                                        <div class="am-btn-toolbar">
                                            <div class="am-btn-group am-btn-group-xs">
                                                <span class="am-text-secondary am-icon"
                                                      style="margin-left: 20px;cursor:pointer"><span></span>
                                                            添加至黑名单</span>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td>1</td>
                                    <td><a href="#">阿里巴巴</a></td>
                                    <td>马云</td>
                                    <td class="am-hide-sm-only">18556658588</td>
                                    <td class="am-hide-sm-only">18556658588@163.com</td>
                                    <td class="am-hide-sm-only">
                                        <div class="">
                                            <select data-am-selected="{btnSize: 'xs'}">
                                                <option value="option2">1级</option>
                                                <option value="option3" selected>2级</option>
                                                <option value="option3">3级</option>
                                            </select>
                                        </div>
                                    </td>
                                    <td class="am-hide-sm-only">2021-12-31</td>
                                    <td>
                                        <div class="am-btn-toolbar">
                                            <div class="am-btn-group am-btn-group-xs">
                                                <span class="am-text-secondary am-icon"
                                                      style="margin-left: 20px;cursor:pointer"><span></span>
                                                            添加至黑名单</span>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td>1</td>
                                    <td><a href="#">小米</a></td>
                                    <td>雷军</td>
                                    <td class="am-hide-sm-only">18556658588</td>
                                    <td class="am-hide-sm-only">18556658588@163.com</td>
                                    <td class="am-hide-sm-only">
                                        <div class="">
                                            <select data-am-selected="{btnSize: 'xs'}">
                                                <option value="option2" selected>1级</option>
                                                <option value="option3">2级</option>
                                                <option value="option3">3级</option>
                                            </select>
                                        </div>
                                    </td>
                                    <td class="am-hide-sm-only">2021-12-31</td>
                                    <td>
                                        <div class="am-btn-toolbar">
                                            <div class="am-btn-group am-btn-group-xs">
                                                <span class="am-text-secondary am-icon"
                                                      style="margin-left: 20px;cursor:pointer"><span></span>
                                                            添加至黑名单</span>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                            <div class="am-cf">
                                <div class="am-fr">
                                    <ul class="am-pagination tpl-pagination">
                                        <li class="am-disabled"><a href="#">«</a></li>
                                        <li class="am-active"><a href="#">1</a></li>
                                        <li><a href="#">2</a></li>
                                        <li><a href="#">3</a></li>
                                        <li><a href="#">4</a></li>
                                        <li><a href="#">5</a></li>
                                        <li><a href="#">»</a></li>
                                    </ul>
                                </div>
                            </div>
                            <hr>
                        </form>
                        <!-- 添加供应商弹出框 -->
                        <div class="am-modal am-modal-prompt" tabindex="-1" id="my-prompt">
                            <div class="am-modal-dialog">
                                <form action="" class="am-form am-form-horizontal">
                                    <legend style="font-size: 18px;padding: 12px 0;">添加供应商</legend>
                                    <div class="am-form-group">
                                        <label for="doc-select-1" class="am-u-sm-4 am-form-label"
                                               style="font-size: 15px;">企业名称</label>
                                        <div class="am-u-sm-6">
                                            <select id="doc-select-1" required>
                                                <option value="">请选择企业</option>
                                                <option value="a">腾讯科技</option>
                                                <option value="b">京东集团</option>
                                                <option value="c">搜狐</option>
                                                <option value="d">阿里巴巴</option>
                                                <option value="e">小米</option>
                                            </select>
                                        </div>
                                        <span class="am-form-caret"></span>
                                    </div>
                                    <div class="am-form-group">
                                        <label for="doc-select-2" class="am-u-sm-4 am-form-label"
                                               style="font-size: 15px;">统一社会信用代码</label>
                                        <div class="am-u-sm-6" style=" text-align: left;margin-top: 6px;">
                                            <span>23456787657645343124567</span>
                                        </div>
                                        <span class="am-form-caret"></span>
                                    </div>
                                    <div class="am-form-group" style="margin-bottom: 20px;">
                                        <label for="doc-select-2" class="am-u-sm-4 am-form-label"
                                               style="font-size: 15px;">企业评级</label>
                                        <div class="am-u-sm-6">
                                            <select id="doc-select-2" required>
                                                <option value="">请选择企业评级</option>
                                                <option value="a">1级</option>
                                                <option value="b">2级</option>
                                                <option value="c">3级</option>
                                            </select>
                                        </div>
                                        <span class="am-form-caret"></span>
                                    </div>
                                </form>
                                <div class="am-modal-footer" style="border-top: 1px solid #dedede;">
                                    <span class="am-modal-btn" data-am-modal-cancel>取消</span>
                                    <span class="am-modal-btn" data-am-modal-confirm>提交</span>
                                </div>
                            </div>
                        </div>
                        <!-- 添加至黑名单弹出框 -->
                        <div class="am-modal am-modal-prompt" tabindex="-1" id="my-prompt1">
                            <div class="am-modal-dialog">
                                <form action="" class="am-form am-form-horizontal">
                                    <legend style="font-size: 18px;padding: 12px 0;">添加至黑名单</legend>
                                    <div class="am-form-group">
                                        <label for="doc-select-1" class="am-u-sm-4 am-form-label"
                                               style="font-size: 15px;">企业名称</label>
                                        <div class="am-u-sm-6" style=" text-align: left;margin-top: 6px;">
                                            <span>腾讯科技</span>
                                        </div>
                                        <span class="am-form-caret"></span>
                                    </div>
                                    <div class="am-form-group">
                                        <label for="doc-select-2" class="am-u-sm-4 am-form-label"
                                               style="font-size: 15px;">统一社会信用代码</label>
                                        <div class="am-u-sm-6" style=" text-align: left;margin-top: 6px;">
                                            <span>23456787657645343124567</span>
                                        </div>
                                        <span class="am-form-caret"></span>
                                    </div>
                                    <div class="am-form-group" style="margin-bottom: 20px;">
                                        <label for="doc-select-2" class="am-u-sm-4 am-form-label"
                                               style="font-size: 15px;">添加理由</label>
                                        <div class="am-u-sm-6">
                                            <textarea class="" rows="4" id="user-intro"
                                                      placeholder="请输入添加理由"></textarea>
                                        </div>
                                        <span class="am-form-caret"></span>
                                    </div>
                                </form>
                                <div class="am-modal-footer" style="border-top: 1px solid #dedede;">
                                    <span class="am-modal-btn" data-am-modal-cancel>取消</span>
                                    <span id="submitBtn" class="am-modal-btn" data-am-modal-confirm>提交</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="tpl-alert"></div>
        </div>
    </div>
</div>

<script src="assets/js/jquery.min.js"></script>
<script src="assets/js/amazeui.min.js"></script>
<script src="assets/js/app.js"></script>
<script>
    // 添加至黑名单
    $(function () {
        $('#submitBtn').on('click', function () {
            $('#my-confirm').modal({
                relatedTarget: this,
                onConfirm: function (options) {
                    //点击确认调用函数
                    alert("点击了确认");
                },
                onCancel: function () {
                    //点击取消调用函数
                    alert("点击了取消")
                }
            });
        });
    });
    // 二次确认
    $(function () {
        $('#doc-modal-list').find('.am-icon').add('#doc-confirm-toggle').on('click', function () {
            $('#my-prompt1').modal({
                relatedTarget: this,
                onConfirm: function (options) {
                    //点击确认调用函数
                    alert("点击了确认");
                },
                onCancel: function () {
                    //点击取消调用函数
                    alert("点击了取消")
                }
            });
        });
    });

    // 添加供应商
    $(function () {
        $('#doc-prompt-toggle').on('click', function () {
            $('#my-prompt').modal({
                relatedTarget: this,
                onConfirm: function (options) {
                    //点击确认调用函数
                    alert("点击了确认");
                },
                onCancel: function (e) {
                    //点击取消调用函数
                    alert("点击了取消")
                }
            });
        });
    });
</script>
</body>

</html>