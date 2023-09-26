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
    <script src="assets/js/jquery.min.js"></script>
    <script>
        $(document).ready(function () {
            var clickedPage = 1;
            var nextPage = 1;
            var prePage = 1;
            var Pages = 0;
            var pageNum = 1;

            showInfo()

            /*进入页面时 通过一次get请求获取列表信息*/
            function showInfo(){
                let name = "%"+$("input[name='enterpriseName']").val().trim()+"%"
                $.ajax({
                    url: "http://localhost:8080/blackList",
                    type: "get",
                    data: {
                        id: ${sessionScope.enterpriseId},
                        enterpriseName: name,
                        startPage:clickedPage
                    },
                    success: function (result) {
                        console.log(result.list)
                        replaceInfo(result)
                    }
                })
            }

            /*模糊查询*/
            $("#btn_query").on("click",function () {
                clickedPage = 1;
                showInfo()
            });

            /*遍历展示信息方法*/
            function replaceInfo(pageInfo) {
                /*总数据量*/
                let total = pageInfo.total
                /*当前页*/
                pageNum = pageInfo.pageNum
                /*页数量*/
                let pageSize = pageInfo.pageSize
                /*下一页*/
                nextPage = pageInfo.nextPage
                /*上一页*/
                prePage = pageInfo.prePage
                /*总页数*/
                Pages = pageInfo.pages
                /*白名单列表信息*/
                let list = pageInfo.list
                /*遍历展示,追加*/
                $("tr[name='forRemove']").remove()

                for (let i = 0; i < list.length ; i++) {
                    $("<tr name='forRemove' data-id='2'>"+
                        "<td>"+ (i+1) +"</td>"+
                        "<td class='am-hide-sm-only'><a href='#'>"+ list[i].enterpriseName +"</a></td>"+
                        "<td class='am-hide-sm-only'>"+ list[i].idcardName +"</td>"+
                        "<td class='am-hide-sm-only'>"+ list[i].phone +"</td>"+
                        "<td class='am-hide-sm-only'>"+ list[i].email +"</td>"+
                        "<td class='am-hide-sm-only'>"+ list[i].variableInfo +"</td>"+
                        "<td class='am-hide-sm-only'>"+ list[i].updateDate +"</td>"+
                        "<td>"+
                        "<div class='am-btn-toolbar'>"+
                        "<div class='am-btn-group am-btn-group-xs'>"+
                        "<span class='am-text-secondary am-icon'"+
                        "style='margin-left: 20px;cursor:pointer' name='解除黑名单' id='"+list[i].supplierId+"'>"+
                        " 解除黑名单"+"</span>"+
                        " </div>"+
                        "</div>"+
                        "</td>"+
                        "</tr>)").appendTo( $("#doc-modal-list") )
                }
                init()
            }

            function init() {
                $("#button_body").empty()
                let pageItem = "<li id='prePage'><a id='-1'>«</a></li>"

                for (let i = 1; i <= Pages ; i++) {
                    pageItem += "<li name='forRemove2'><a id='"+i+"'>"+i+"</a><li>"
                }
                pageItem += "<li id='nextPage'><a id='-1'>»</a></li>"

                $(pageItem).appendTo( $("#button_body") )

                // 找到当前页码对应的按钮，并添加高亮状态
                $(".am-pagination li a").each(function() {
                    if ($(this).attr("id") === clickedPage.toString() && $(this).attr("id") != -1) {
                        $(this).parent().addClass("am-active");
                    }
                });

                $(".am-pagination li a").on("click", function(e) {
                    // 获取被点击的页码按钮的id属性
                    if ($(this).attr("id") != -1) {
                        clickedPage = $(this).attr("id");
                        console.log("点击的页码是: "+clickedPage)
                        // 发送异步请求，请求对应页码的数据
                        showInfo()
                    }
                });

                /*上一页下一页*/
                $("#nextPage").on("click", function() {
                    if (nextPage === 0) {
                        nextPage = 1;
                    }
                    // 获取被点击的页码按钮的id属性
                    clickedPage = nextPage;
                    console.log("点击的页码是: "+clickedPage)
                    // 发送异步请求，请求对应页码的数据
                    showInfo()
                });

                $("#prePage").on("click", function() {
                    if (prePage === 0) {
                        prePage = Pages;
                    }
                    // 获取被点击的页码按钮的id属性
                    clickedPage = prePage;
                    console.log("点击的页码是: "+clickedPage)
                    // 发送异步请求，请求对应页码的数据
                    showInfo()
                });
            }

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
</head>

<body data-type="generalComponents">
<header class="am-topbar am-topbar-inverse admin-header">
    <div class="am-collapse am-topbar-collapse" id="topbar-collapse">
        <ul class="am-nav am-nav-pills am-topbar-nav am-topbar-right admin-header-list tpl-header-list">
            <li class="am-dropdown" data-am-dropdown data-am-dropdown-toggle>
                <a class="am-dropdown-toggle tpl-header-list-link" href="javascript:;">
                    <span class="tpl-header-list-user-nick">${sessionScope.userName}</span><span class="tpl-header-list-user-ico"> <img
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
                            <a href="white-list">
                                <i class="am-icon-angle-right"></i>
                                <span>供应商白名单</span>
                            </a>
                            <a href="black-list" class="active">
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
            供应商黑名单列表
        </div>
        <ol class="am-breadcrumb">
            <li class="am-active"><a style="color: #999999;">供应商管理</a></li>
            <li class="am-active">供应商黑名单</li>
        </ol>
        <div class="tpl-portlet-components">
            <div class="tpl-block">
                <div class="am-g">
                    <div class="am-u-sm-6 am-u-md-2">
                        <div class="am-input-group am-input-group-sm">
                                <span class="am-input-group-btn">
                                    <span style="font-size: 14px;margin-right: 8px;margin-left: 8px">企业名称</span>
                                </span>
                            <input name="enterpriseName" type="text" class="am-form-field" placeholder="&nbsp;&nbsp;请输入企业名称"
                                   style="border: 1px solid #c2cad8;border-radius: 3px;">
                        </div>
                    </div>
                    <div class="am-u-sm-6 am-u-md-3">
                        <div class="am-form-group border row-flex">
                            <div id="btn_query">
                                <button type="button" class="am-btn am-btn-primary am-btn-sm">搜索</button>
                            </div>
                        </div>
                    </div>
                    <div style="float:right">
                        <div class="am-form-group">
                            <button type="button" class="am-btn am-btn-primary am-btn-sm"
                                    id="doc-prompt-toggle">添加供应商
                            </button>
                        </div>
                    </div>
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
                                           style="font-size: 15px;">添加理由</label>
                                    <div class="am-u-sm-6">
                                        <textarea class="" rows="4" id="user-intro" placeholder="请输入添加理由"></textarea>
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
                                    <th class="table-date am-hide-sm-only">添加理由</th>
                                    <th class="table-date am-hide-sm-only">更新日期</th>
                                    <th class="table-set">操作</th>
                                </tr>
                                </thead>
                                <tbody id="doc-modal-list">

                                <div class="am-modal am-modal-confirm" tabindex="-1" id="my-confirm">
                                    <div class="am-modal-dialog" style="font-size: 16px;">
                                        <div class="am-modal-hd">提示</div>
                                        <div class="am-modal-bd">
                                            将供应商解除黑名单后该企业可继续在平台进行融资，是否继续？
                                        </div>
                                        <div class="am-modal-footer">
                                            <span class="am-modal-btn" data-am-modal-cancel>取消</span>
                                            <span class="am-modal-btn" data-am-modal-confirm>确定</span>
                                        </div>
                                    </div>
                                </div>
                                </tbody>
                            </table>
                            <div class="am-cf">

                                <div class="am-fr">
                                    <ul class="am-pagination tpl-pagination" id="button_body">

                                    </ul>
                                </div>
                            </div>
                            <hr>
                        </form>
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
    $(document).on(
        "click",
        "span[name = '解除黑名单']",
        function(){
            that = this
            $('#my-confirm').modal({
                relatedTarget: this,
                onConfirm: function (options) {
                    alert("点击了确认");
                    $.ajax({
                        type: "delete",
                        url: "blackList",
                        dataType: "json",
                        contentType:"application/json",
                        data:JSON.stringify({
                            enterpriseId:${sessionScope.enterpriseId},
                            supplierId:that.id
                        }),
                        success:function (result){
                            if (result.data){
                                alert(result.msg)
                            }else {
                                alert(result.msg)
                            }
                        }
                    })


                },
                onCancel: function (e) {
                    //点击取消调用函数
                    alert("点击了取消")
                }
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