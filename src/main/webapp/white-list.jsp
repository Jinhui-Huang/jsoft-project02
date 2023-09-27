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
        .border {
            border: 0px solid red;
            width: 500px;
        }

        .row-flex {
            display: flex;
            flex-direction: row;
        }

        #btn_query {
            margin-left: 30px;
        }
    </style>
</head>

<body data-type="generalComponents">
<header class="am-topbar am-topbar-inverse admin-header">
    <div class="am-collapse am-topbar-collapse" id="topbar-collapse">
        <ul class="am-nav am-nav-pills am-topbar-nav am-topbar-right admin-header-list tpl-header-list">
            <li class="am-dropdown" data-am-dropdown data-am-dropdown-toggle>
                <a class="am-dropdown-toggle tpl-header-list-link" href="javascript:;">
                    <span class="tpl-header-list-user-nick">${sessionScope.userName}</span><span
                        class="tpl-header-list-user-ico"> <img
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
                            <input name="enterpriseName" type="text" class="am-form-field" placeholder="请输入企业名称"
                                   style="border: 1px solid #c2cad8;border-radius: 3px;">
                        </div>
                    </div>
                    <div class="am-u-sm-6 am-u-md-3">
                        <div class="am-form-group border row-flex">
                            <span style="font-size: 14px; margin-top: 2px; margin-right: 10px">企业评级</span>
                            <select name="supplierLevel" data-am-selected="{btnSize: 'sm'}">
                                <option value="0">请选择企业评级</option>
                                <option value="A">A级</option>
                                <option value="B">B级</option>
                                <option value="C">C级</option>
                            </select>
                            <div id="btn_query">
                                <button type="button" class="am-btn am-btn-primary am-btn-sm">搜索
                                </button>
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
                                                <%--在此动态拼接--%>
                                            </select>
                                        </div>
                                        <span class="am-form-caret"></span>
                                    </div>
                                    <div class="am-form-group">
                                        <label for="doc-select-2" class="am-u-sm-4 am-form-label"
                                               style="font-size: 15px;">统一社会信用代码</label>
                                        <div class="am-u-sm-6" style=" text-align: left;margin-top: 6px;">
                                            <span id="socialUniformCode"></span>
                                        </div>
                                        <span class="am-form-caret"></span>
                                    </div>
                                    <div class="am-form-group" style="margin-bottom: 20px;">
                                        <label for="doc-select-2" class="am-u-sm-4 am-form-label"
                                               style="font-size: 15px;">企业评级</label>
                                        <div class="am-u-sm-6">
                                            <select id="doc-select-2" required>
                                                <option value="0">请选择企业评级</option>
                                                <option value="A">A级</option>
                                                <option value="B">B级</option>
                                                <option value="C">C级</option>
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
                                            <span name="bb-name"></span>
                                        </div>
                                        <span class="am-form-caret"></span>
                                    </div>
                                    <div class="am-form-group">
                                        <label for="doc-select-2" class="am-u-sm-4 am-form-label"
                                               style="font-size: 15px;">统一社会信用代码</label>
                                        <div class="am-u-sm-6" style=" text-align: left;margin-top: 6px;">
                                            <span name="bb-social"></span>
                                        </div>
                                        <span class="am-form-caret"></span>
                                    </div>
                                    <div class="am-form-group" style="margin-bottom: 20px;">
                                        <label for="doc-select-2" class="am-u-sm-4 am-form-label"
                                               style="font-size: 15px;">添加理由</label>
                                        <div class="am-u-sm-6">
                                            <textarea name="bb-reason" class="" rows="4" id="user-intro"
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

</body>

<script src="assets/js/jquery.min.js"></script>
<script src="assets/js/amazeui.min.js"></script>
<script src="assets/js/app.js"></script>
<script src="assets/js/message.min.js"></script>
<script type="text/javascript">
    /*
    * @author: JoneElmo
    * @date: 2023-9-25 18:59
    * @email: mhangggggg@outlook.com
    * */
    window.QMSG_GLOBALS = {
        DEFAULTS: {
            showClose: true,
            timeout: 2000
        }
    };
    const msg = Qmsg
    Qmsg.success("")
    msg.success("")
    $(document).ready(function () {
        var clickedPage = 1
        var nextPage = 1
        /*上一页*/
        var prePage = 1
        var pages = 1
        /*进入页面时 通过一次get请求获取列表信息*/
        $.ajax({
            url: "http://localhost:8080/whiteList",
            type: "get",
            data: {
                id: ${sessionScope.enterpriseId},
                startPage: clickedPage
            },
            success: function (result) {
                console.log(result.list)
                replaceInfo(result)
            }
        })

        $("#btn_query").on("click", function () {
            let name = "%" + $("input[name='enterpriseName']").val() + "%"
            let lv = $("select[name='supplierLevel']").val()
            $.ajax({
                url: "http://localhost:8080/whiteList",
                type: "get",
                data: {
                    id: ${sessionScope.enterpriseId},
                    enterpriseName: name,
                    supplierLevel: lv,
                    startPage: clickedPage
                },
                success: function (result) {
                    console.log(result.list)
                    replaceInfo(result)
                }
            })
        })

        /*遍历展示信息方法*/
        function replaceInfo(pageInfo) {
            /*总数据量*/
            let total = pageInfo.total
            /*当前页*/
            let pageNum = pageInfo.pageNum
            /*每页大小*/
            let pageSize = pageInfo.pageSize
            /*下一页*/
            nextPage = pageInfo.nextPage
            /*上一页*/
            prePage = pageInfo.prePage
            /*总页数*/
            pages = pageInfo.pages

            /*白名单列表信息*/
            let list = pageInfo.list
            /*遍历展示,追加*/
            $("tr[name='forRemove']").remove()
            for (let i = 0; i < list.length; i++) {
                $("<tr name='forRemove' data-id='2'>" +
                    "<td>" + (i + 1) + "</td>" +
                    "<input name='h-supplierId-" + i + "' type='hidden' value='" + list[i].supplierId + "'>" +
                    "<td class='am-hide-sm-only'><a href='#'>" + list[i].enterpriseName + "</a></td>" +
                    "<td class='am-hide-sm-only'>" + list[i].idcardName + "</td>" +
                    "<td class='am-hide-sm-only'>" + list[i].phone + "</td>" +
                    "<td class='am-hide-sm-only'>" + list[i].email + "</td>" +
                    "<td class='am-hide-sm-only'>" + list[i].variableInfo + "</td>" +
                    "<td class='am-hide-sm-only'>" + list[i].updateDate + "</td>" +
                    "<td>" +
                    "<div class='am-btn-toolbar'>" +
                    "<div name='addToBlackList' class='am-btn-group am-btn-group-xs'>" +
                    "<span class='am-text-secondary am-icon'" +
                    "style='margin-left: 20px;cursor:pointer'>" +
                    " 添加至黑名单" + "</span>" +
                    " </div>" +
                    "</div>" +
                    "</td>" +
                    "</tr>)").appendTo($("#doc-modal-list"))
            }


            $("#button_body").empty()

            let pageItem =  "<li id='prePage'><a  id='-1'>«</a></li>"
            for (let i = 1; i <= pages; i++) {
                pageItem += "<li name='forRemove2'><a id='" + i + "' href='#'>" + i + "</a><li>"
            }
            pageItem += "<li id='nextPage'><a id='-1'>»</a></li>"
            $(pageItem).appendTo($("#button_body"))

            clickedPage = pageNum;
            init()
        }

        function init() {

            // 清除所有页码按钮的高亮状态
            $(".am-pagination li").removeClass("am-active");
            // 找到当前页码对应的按钮，并添加高亮状态

            let currentPage = clickedPage; // 获取当前页码
            $(".am-pagination li a").each(function () {
                if ($(this).attr("id") === currentPage.toString()) {
                    $(this).parent().addClass("am-active");
                }
            });

            // 给页码按钮绑定点击事件
            $(".am-pagination li a").on("click", function () {
                if ($(this).attr("id") != -1) {
                    // 获取被点击的页码按钮的id属性
                    clickedPage = $(this).attr("id");
                    console.log("点击的页码是: " + clickedPage)
                    // 发送异步请求，请求对应页码的数据
                    showInfo()
                }
            });


            $("#prePage").on("click", function () {
                console.log("点击了上一页")
                if (prePage === 0) {
                    prePage= pages
                }
                clickedPage = prePage
                showInfo()
            })

            $("#nextPage").on("click", function () {

                if (nextPage === 0) {
                    nextPage = 1
                }

                clickedPage = nextPage
                showInfo()

            })
        }

        function showInfo(){
            $.ajax({
                url: "http://localhost:8080/whiteList",
                type: "get",
                async: true,
                data: {
                    id: ${sessionScope.enterpriseId},
                    enterpriseName: "%" + $("input[name='enterpriseName']").val() + "%",
                    supplierLevel: $("select[name='supplierLevel']").val(),
                    startPage: clickedPage
                },
                success: function (result) {
                    console.log("Received data for page " + clickedPage);
                    replaceInfo(result)
                },
                error: function (error) {
                    console.error("Error: " + error);
                }
            })
        }

        /*添加至黑名单*/
        let supplierId
        $(function () {
            $(document).on('click', "div[name='addToBlackList']", function () {
                console.log("点击‘添加至黑名单’")
                let rowIndex = $(this).closest("tr").index();
                supplierId = $("input[name='h-supplierId-" + rowIndex + "']").val();
                console.log("选择的供应商id=" + supplierId);
                let name;
                let socialUniformCode;

                /*点击添加至黑名单，查询弹出框信息*/
                $.ajax({
                    url: "http://localhost:8080/enterprise",
                    type: "get",
                    async: true,
                    data: {
                        id: supplierId,
                        op: "2"
                    },
                    success: function (result) {
                        socialUniformCode = result.socialUniformCode
                        name = result.name
                        console.log("name=" + name + ";socialUniformCode=" + socialUniformCode)
                        $("span[name='bb-name']").text(name)
                        $("span[name='bb-social']").text(socialUniformCode)
                    }
                })

                $('#my-prompt1').modal({
                    relatedTarget: this,
                    onConfirm: function (options) {
                        console.log("添加至黑名单，点击了确认")
                    },
                    onCancel: function () {
                        //点击取消调用函数
                        console.log("添加至黑名单，点击了取消")
                    }
                });
            });
            /*二次确认框 二次确认后 发送处理请求*/
            $('#submitBtn').on('click', function () {
                $('#my-confirm').modal({
                    relatedTarget: this,
                    onConfirm: function (options) {
                        console.log("添加至黑名单，二次确认")
                        console.log("拿到的enterpriseId:" +${sessionScope.enterpriseId})
                        console.log("拿到的supplierId:" + supplierId)
                        console.log("拿到的reason:" + $("textarea[name='bb-reason']").val())

                        $.ajax({
                            url: "http://localhost:8080/whiteList",
                            type: "post",
                            dataType: "json",
                            async: true,
                            data: JSON.stringify({
                                enterpriseId: ${sessionScope.enterpriseId},
                                supplierId: supplierId,
                                reason: $("textarea[name='bb-reason']").val(),
                            }),
                            success: function (result) {
                                console.log(result)
                                if (result==true){
                                    $.ajax({
                                        url: "http://localhost:8080/whiteList",
                                        type: "get",
                                        data: {
                                            id: ${sessionScope.enterpriseId},
                                            startPage: clickedPage
                                        },
                                        success: function (result) {
                                            msg.success("移除成功！")
                                            console.log(result.list)
                                            replaceInfo(result)
                                        }
                                    })
                                }

                            },
                            error: function (){
                                $.ajax({
                                    url: "http://localhost:8080/whiteList",
                                    type: "get",
                                    data: {
                                        id: ${sessionScope.enterpriseId},
                                        startPage: clickedPage
                                    },
                                    success: function (result) {
                                        console.log(result.list)
                                        replaceInfo(result)
                                    }
                                })
                            }
                        })

                    },
                    onCancel: function () {
                        //点击取消调用函数
                        msg.info("取消")
                        alert("quxiao")
                    }
                });
            });

        });

        // 添加供应商
        $(function () {
            let selectSupplierId;
            $('#doc-prompt-toggle').on('click', function () {
                $("#doc-select-1").empty();
                $("<option value=''>"+"请选择企业"+"</option>").appendTo("#doc-select-1")
                /*点击添加供应商按钮，查询下拉列表信息*/
                $.ajax({
                    url: "http://localhost:8080/enterprise",
                    type: "get",
                    async: true,
                    data: {
                        id: ${sessionScope.enterpriseId},
                        op: "1"
                    },
                    success: function (result) {
                        console.log("获取到的list信息:" + result)
                        let id;
                        let name;
                        let socialUniformCode;
                        $("option[name='forRe']").remove()
                        for (let i = 0; i < result.length; i++) {
                            id = result[i].id;
                            name = result[i].name;
                            socialUniformCode = result[i].socialUniformCode;
                            /*将信用代码作为选项的value*/
                            $("<option name='forRe' id='" + id + "' value='" + socialUniformCode + "'>" + name + "</option>").appendTo($("#doc-select-1"))

                        }
                    }
                })
                /*输入添加供应商信息的弹出框*/
                $('#my-prompt').modal({
                    relatedTarget: this,
                    onConfirm: function (options) {
                        //点击确认，插入供应商信息
                        let supplierLevel = $("#doc-select-2").val()
                        console.info("选中的企业id是：" + selectSupplierId)
                        if (supplierLevel == "0") {
                            msg.warning("请选择企业评级!")
                        } else {
                            $.ajax({
                                url: "http://localhost:8080/whiteList",
                                type: "put",
                                contentType: "json",
                                data: JSON.stringify({
                                    enterpriseId: ${sessionScope.enterpriseId},
                                    supplierId: selectSupplierId,
                                    supplierLevel: supplierLevel
                                }),
                                success: function (result) {
                                    if (result == true) {
                                        msg.success("添加供应商信息成功！")
                                        /*清除输入弹出框的残留信息*/
                                        $("#socialUniformCode").text("")
                                        $("#doc-select-2").val("0")

                                        $.ajax({
                                            url: "http://localhost:8080/whiteList",
                                            type: "get",
                                            data: {
                                                id: ${sessionScope.enterpriseId},
                                                startPage: clickedPage
                                            },
                                            success: function (result) {
                                                console.log(result.list)
                                                replaceInfo(result)
                                            }
                                        })
                                    } else {
                                        msg.error("添加供应商信息失败！")
                                    }
                                }
                            })
                        }

                    },
                    onCancel: function (e) {
                        //点击取消调用函数
                        console.log("关闭添加供应商弹出框")
                    }
                });
            });
            /*设置选项点击事件*/
            $("#doc-select-1").on("change", function () {
                selectSupplierId = $("#doc-select-1").find("option:selected").attr("id");
                console.log("点击了信息" + $("#doc-select-1").find("option:selected").val())
                $("#socialUniformCode").text($("#doc-select-1").find("option:selected").val())
            })
        });
        /*退出登录按钮*/

        $("#logoutButton").click(function (){
            $.ajax({
                type:"delete",
                url:"login",
                success:function (data){
                    if (data.data){
                        msg.success(data.msg)
                        window.location.href="http://localhost:8080"
                    }
                }
            })
        })
    })

</script>
</html>