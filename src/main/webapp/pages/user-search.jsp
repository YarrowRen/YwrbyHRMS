<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <!-- 页面meta -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <title>Ywrby人员管理系统</title>
    <meta name="description" content="Ywrby">
    <meta name="keywords" content="Ywrby">

    <!-- Tell the browser to be responsive to screen width -->
    <meta
            content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no"
            name="viewport">

    <link rel="stylesheet"
          href="/plugins/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet"
          href="/plugins/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet"
          href="/plugins/ionicons/css/ionicons.min.css">
    <link rel="stylesheet"
          href="/plugins/iCheck/square/blue.css">
    <link rel="stylesheet"
          href="/plugins/morris/morris.css">
    <link rel="stylesheet"
          href="/plugins/jvectormap/jquery-jvectormap-1.2.2.css">
    <link rel="stylesheet"
          href="/plugins/datepicker/datepicker3.css">
    <link rel="stylesheet"
          href="/plugins/daterangepicker/daterangepicker.css">
    <link rel="stylesheet"
          href="/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">
    <link rel="stylesheet"
          href="/plugins/datatables/dataTables.bootstrap.css">
    <link rel="stylesheet"
          href="/plugins/treeTable/jquery.treetable.css">
    <link rel="stylesheet"
          href="/plugins/treeTable/jquery.treetable.theme.default.css">
    <link rel="stylesheet"
          href="/plugins/select2/select2.css">
    <link rel="stylesheet"
          href="/plugins/colorpicker/bootstrap-colorpicker.min.css">
    <link rel="stylesheet"
          href="/plugins/bootstrap-markdown/css/bootstrap-markdown.min.css">
    <link rel="stylesheet"
          href="/plugins/adminLTE/css/AdminLTE.css">
    <link rel="stylesheet"
          href="/plugins/adminLTE/css/skins/_all-skins.min.css">
    <link rel="stylesheet"
          href="/css/style.css">
    <link rel="stylesheet"
          href="/plugins/ionslider/ion.rangeSlider.css">
    <link rel="stylesheet"
          href="/plugins/ionslider/ion.rangeSlider.skinNice.css">
    <link rel="stylesheet"
          href="/plugins/bootstrap-slider/slider.css">

    <script>
        function delUser(userId) {
            if(confirm("是否删除该用户？")){
                //调用相关函数，利用用户ID删除该用户
                location.href="${pageContext.request.contextPath}/user/delete/"+userId
                //直接+userId是利用了restful编程风格
            }
        }

        function editUser(userId) {
            location.href="${pageContext.request.contextPath}/user/editUI/"+userId
        }

        function clientClickButton(event) {
            if (event.keyCode === 13){
                const searchName = $('input[name="search"]:first').val();
                location.href="${pageContext.request.contextPath}/user/find/"+searchName
            }
        }
    </script>
</head>

<body class="hold-transition skin-blue sidebar-mini">

<div class="wrapper">

    <!-- 页面头部 -->
    <jsp:include page="header.jsp"></jsp:include>
    <!-- 页面头部 /-->

    <!-- 导航侧栏 -->
    <jsp:include page="aside.jsp"></jsp:include>
    <!-- 导航侧栏 /-->

    <!-- 内容区域 -->
    <div class="content-wrapper">

        <!-- 内容头部 -->
        <section class="content-header">
            <h1>
                用户管理 <small>全部用户</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="${pageContext.request.contextPath}/index.jsp"><i
                        class="fa fa-dashboard"></i> 首页</a></li>
                <li><a
                        href="${pageContext.request.contextPath}/user/findAll.do">用户管理</a></li>

                <li class="active">全部用户</li>
            </ol>
        </section>
        <!-- 内容头部 /-->

        <!-- 正文区域 -->
        <section class="content"> <!-- .box-body -->
            <div class="box box-primary">
                <div class="box-header with-border">
                    <h3 class="box-title">列表</h3>
                </div>

                <div class="box-body">

                    <!-- 数据表格 -->
                    <div class="table-box">

                        <!--工具栏-->
                        <div class="pull-left">
                            <div class="form-group form-inline">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-default" title="新建" onclick="location.href='${pageContext.request.contextPath}/user/saveUI'">
                                        <i class="fa fa-file-o"></i> 新建
                                    </button>

                                    <button type="button" class="btn btn-default" title="刷新" onclick="location.href='${pageContext.request.contextPath}/user/list'">
                                        <i class="fa fa-refresh"></i> 刷新
                                    </button>
                                </div>
                            </div>
                        </div>
                        <div class="box-tools pull-right">
                            <div class="has-feedback">
                                <input type="text" class="form-control input-sm"
                                       placeholder="搜索用户名" name="search" onkeydown="clientClickButton(event)" >
                                <span onclick="" class="glyphicon glyphicon-search form-control-feedback"></span>
                            </div>
                        </div>
                        <!--工具栏/-->

                        <!--数据列表-->
                        <table id="dataList"
                               class="table table-bordered table-striped table-hover dataTable">
                            <thead>
                            <tr>
                                <th class="" style="padding-right: 0px"><input
                                        id="selall" type="checkbox" class="icheckbox_square-blue">
                                </th>
                                <th class="sorting_asc">ID</th>
                                <th class="sorting_desc">用户名</th>
                                <th class="sorting_asc sorting_asc_disabled">邮箱</th>
                                <th class="sorting_desc sorting_desc_disabled">联系电话</th>
                                <th class="sorting">具有角色</th>
                                <th class="sorting">编辑用户</th>
                                <th class="sorting">删除用户</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${userList}" var="user">
                                <tr>
                                    <td><input name="ids" type="checkbox"></td>
                                    <td>${user.id}</td>
                                    <td>${user.username}</td>
                                    <td>${user.email}</td>
                                    <td>${user.phoneNum}</td>
                                    <td class="text-center">
                                        <c:forEach items="${user.roleList}" var="role">
                                            &nbsp;&nbsp;${role.roleName}
                                        </c:forEach>
                                    </td>
                                    <td class="text-center">
                                        <a href="javascript:void(0);" onclick="editUser('${user.id}')" class="btn bg-olive btn-xs">编辑</a>
                                    </td>
                                    <td class="text-center">
                                        <a href="javascript:void(0);" onclick="delUser('${user.id}')" class="btn bg-olive btn-xs">删除</a>
                                    </td>
                                </tr>
                            </c:forEach>

                            </tbody>

                        </table>
                        <!--数据列表/-->

                    </div>
                    <!-- 数据表格 /-->

                </div>
                <!-- /.box-body -->



            </div>

        </section>
        <!-- 正文区域 /-->

    </div>
    <!-- @@close -->
    <!-- 内容区域 /-->

    <!-- 底部导航 -->
    <footer class="main-footer">
        <div class="pull-right hidden-xs">
            <b>Version</b> 1.0.0
        </div>
        <strong>Copyright &copy; 2021 <a
                href="http://www.ywrby.cn">Ywrby</a>.
        </strong> All rights reserved. </footer>
    <!-- 底部导航 /-->

</div>

<script src="/plugins/jQuery/jquery-2.2.3.min.js"></script>
<script src="/plugins/jQueryUI/jquery-ui.min.js"></script>
<script>
    $.widget.bridge('uibutton', $.ui.button);
</script>
<script src="/plugins/bootstrap/js/bootstrap.min.js"></script>
<script src="/plugins/raphael/raphael-min.js"></script>
<script src="/plugins/morris/morris.min.js"></script>
<script src="/plugins/sparkline/jquery.sparkline.min.js"></script>
<script src="/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
<script src="/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
<script src="/plugins/knob/jquery.knob.js"></script>
<script src="/plugins/daterangepicker/moment.min.js"></script>
<script src="/plugins/daterangepicker/daterangepicker.js"></script>
<script src="/plugins/daterangepicker/daterangepicker.zh-CN.js"></script>
<script src="/plugins/datepicker/bootstrap-datepicker.js"></script>
<script
        src="/plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
<script
        src="/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>
<script src="/plugins/slimScroll/jquery.slimscroll.min.js"></script>
<script src="/plugins/fastclick/fastclick.js"></script>
<script src="/plugins/iCheck/icheck.min.js"></script>
<script src="/plugins/adminLTE/js/app.min.js"></script>
<script src="/plugins/treeTable/jquery.treetable.js"></script>
<script src="/plugins/select2/select2.full.min.js"></script>
<script src="/plugins/colorpicker/bootstrap-colorpicker.min.js"></script>
<script
        src="/plugins/bootstrap-wysihtml5/bootstrap-wysihtml5.zh-CN.js"></script>
<script src="/plugins/bootstrap-markdown/js/bootstrap-markdown.js"></script>
<script
        src="/plugins/bootstrap-markdown/locale/bootstrap-markdown.zh.js"></script>
<script src="/plugins/bootstrap-markdown/js/markdown.js"></script>
<script src="/plugins/bootstrap-markdown/js/to-markdown.js"></script>
<script src="/plugins/ckeditor/ckeditor.js"></script>
<script src="/plugins/input-mask/jquery.inputmask.js"></script>
<script
        src="/plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
<script src="/plugins/input-mask/jquery.inputmask.extensions.js"></script>
<script src="/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="/plugins/datatables/dataTables.bootstrap.min.js"></script>
<script src="/plugins/chartjs/Chart.min.js"></script>
<script src="/plugins/flot/jquery.flot.min.js"></script>
<script src="/plugins/flot/jquery.flot.resize.min.js"></script>
<script src="/plugins/flot/jquery.flot.pie.min.js"></script>
<script src="/plugins/flot/jquery.flot.categories.min.js"></script>
<script src="/plugins/ionslider/ion.rangeSlider.min.js"></script>
<script src="/plugins/bootstrap-slider/bootstrap-slider.js"></script>
<script>
    $(document).ready(function() {
        // 选择框
        $(".select2").select2();

        // WYSIHTML5编辑器
        $(".textarea").wysihtml5({
            locale : 'zh-CN'
        });
    });

    // 设置激活菜单
    function setSidebarActive(tagUri) {
        var liObj = $("#" + tagUri);
        if (liObj.length > 0) {
            liObj.parent().parent().addClass("active");
            liObj.addClass("active");
        }
    }

    $(document)
        .ready(
            function() {

                // 激活导航位置
                setSidebarActive("admin-datalist");

                // 列表按钮
                $("#dataList td input[type='checkbox']")
                    .iCheck(
                        {
                            checkboxClass : 'icheckbox_square-blue',
                            increaseArea : '20%'
                        });
                // 全选操作
                $("#selall")
                    .click(
                        function() {
                            var clicks = $(this).is(
                                ':checked');
                            if (!clicks) {
                                $(
                                    "#dataList td input[type='checkbox']")
                                    .iCheck(
                                        "uncheck");
                            } else {
                                $(
                                    "#dataList td input[type='checkbox']")
                                    .iCheck("check");
                            }
                            $(this).data("clicks",
                                !clicks);
                        });
            });
</script>
</body>

</html>