<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>CCManage</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- 图标设置 -->
    <link rel="icon" href="/static/favicon.ico" type="image/x-icon">
    <!-- Bootstrap -->
    <link rel="stylesheet" href="/static/dist/css/bootstrap.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="/static/dist/css/font-awesome.css">
    <!-- AdminLTE css.  -->
    <link rel="stylesheet" href="/static/dist/css/AdminLTE.min.css">
    <!-- AdminLTE Skins.  -->
    <link rel="stylesheet" href="/static/dist/css/skins/_all-skins.min.css">
</head>
<!-- 后台主题：skin-blue -->
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <header class="main-header">
        <!-- Logo -->
        <a href="/sys" class="logo">
            <!-- mini logo for sidebar mini 50x50 pixels -->
            <span class="logo-mini"><b>CC</b></span>
            <!-- logo for regular state and mobile devices -->
            <span class="logo-lg"><b>CC</b>Manage</span>
        </a>
        <!-- Header Navbar: style can be found in header.less -->
        <nav class="navbar navbar-static-top" role="navigation">
            <!-- Sidebar toggle button-->
            <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
                <span class="sr-only">Toggle navigation</span>
            </a>

            <div class="navbar-custom-menu">
                <ul class="nav navbar-nav">
                    <#--<li><a data-toggle="modal" data-target="#updatePasswordModal"><i class="fa fa-lock"></i> &nbsp;修改密码</a></li>-->
                    <!-- User Account: style can be found in dropdown.less -->
                    <#--<li class="dropdown user user-menu">-->
                        <#--<a href="#" class="dropdown-toggle" data-toggle="dropdown">-->
                            <#--<img src="/static/image/head.png" class="user-image" alt="User Image">-->
                            <#--<span class="hidden-xs">管理员: ${currentUser.username}</span>-->
                        <#--</a>-->
                        <#--<ul class="dropdown-menu">-->
                            <!-- User image -->
                            <#--<li class="user-header">-->
                                <#--<img src="/static/image/head.png" class="img-circle" alt="User Image">-->
                                <#--<p>-->
                                    <#--${currentUser.username}-->
                                    <#--<small>${currentUser.email}</small>-->
                                <#--</p>-->
                            <#--</li>-->
                            <!-- Menu Footer-->
                            <#--<li>-->
                                <#--<div class="">-->
                                    <#--<a href="/logout" class="btn btn-default btn-block btn-flat">退出</a>-->
                                <#--</div>-->
                            <#--</li>-->
                        <#--</ul>-->
                    <#--</li>-->
                    <!-- Control Sidebar Toggle Button -->
                    <li>
                        <a href="#" data-toggle="control-sidebar"><i class="fa fa-gears"></i></a>
                    </li>
                </ul>
            </div>
        </nav>
    </header>
    <!-- Left side column. contains the logo and sidebar -->
    <aside class="main-sidebar">
        <!-- sidebar: style can be found in sidebar.less -->
        <section class="sidebar">
            <!-- Sidebar user panel -->
            <div class="user-panel">
                <div class="pull-left image">
                    <img src="/static/image/head.png" class="img-circle" alt="User Image">
                </div>
                <div class="pull-left info">
                    <p><i class="fa fa-user-circle-o"></i> ${currentUser.username}</p>
                    <i class="fa fa-circle text-success"></i> 在线
                </div>
            </div>

            <!-- sidebar menu: : style can be found in sidebar.less -->
            <ul id="menuList" class="sidebar-menu">
                <#--<li class="header">菜单</li>-->
                <#--<li class="treeview">-->
                    <#--<a href="#">-->
                        <#--<i class="fa fa-book"></i> <span>系统管理</span> <i class="fa fa-angle-left pull-right"></i>-->
                    <#--</a>-->
                    <#--<ul class="treeview-menu">-->
                        <#--<li><a href="javascript:void(0);" onclick="showMenuContent('/sys/user/page', '用户管理')"><i class="fa fa-user"></i> 用户管理</a></li>-->
                        <#--<li><a href="javascript:void(0);" onclick="showMenuContent('/sys/menu', '菜单管理')"><i class="fa fa-th-list"></i> 菜单管理</a></li>-->
                        <#--<li><a href="javascript:void(0);" onclick="showMenuContent('/sys/role', '权限管理')"><i class="fa fa-link"></i> 权限管理</a></li>-->
                        <#--<li><a href="javascript:void(0);" onclick="showMenuContent('/sys/log', '日志查看')"><i class="fa fa-file-text-o"></i> 日志查看</a></li>-->
                        <#--<li><a href="javascript:void(0);" onclick="showMenuContent('/druid/index.html', 'SQL监控')"><i class="fa fa-bug"></i> SQL监控</a></li>-->
                        <#--<li><a href="javascript:void(0);" onclick="showMenuContent('/sys/about', '关于我')"><i class="fa fa-heartbeat"></i><span>关于我</span></a></li>-->
                    <#--</ul>-->
                <#--</li>-->
            </ul>
        </section>
        <!-- /.sidebar -->
    </aside>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <ol class="breadcrumb" id="nav_title" style="position:static;float:none;">
                <li class="active"><a href="javascript:void(0);"><i class="fa fa-home" style="font-size:20px;position:relative;top:2px;left:-3px;"></i> &nbsp; 首页</a></li>
                <li class="active" id="targetBread"></li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content" style="background:#fff;">
            <iframe scrolling="yes" frameborder="0" style="width:100%;min-height:200px;overflow:visible;background:#fff;"></iframe>
        </section>
        <!-- /.content -->
        <#--#parse(${mainPage})-->
    </div>
    <footer class="main-footer">
        <div class="pull-right hidden-xs">
            Version 1.0
        </div>
        <strong>Copyright &copy; 2018 <a href="https://www.luchunzhou.cn" target="_blank">luchunzhou.cn</a></strong> All rights reserved.
    </footer>

    <#--lcz add-->
    <!-- Control Sidebar -->
    <aside class="control-sidebar control-sidebar-dark">
        <!-- Create the tabs -->
        <ul class="nav nav-tabs nav-justified control-sidebar-tabs">
            <li class="active"><a href="#control-sidebar-settings-tab" data-toggle="tab"><i class="fa fa-gears"></i></a></li>
        </ul>
        <!-- Tab panes -->
        <div class="tab-content">
            <!-- Settings tab content -->
            <div class="tab-pane active" id="control-sidebar-settings-tab">
                <h3 class="control-sidebar-heading">系统设置</h3>
                <ul class="control-sidebar-menu">
                    <li>
                        <a href="#" data-toggle="modal" data-target="#updatePasswordModal">
                            <i class="menu-icon fa fa-vcard bg-yellow"></i>

                            <div class="menu-info">
                                <h4 class="control-sidebar-subheading">修改密码</h4>

                                <p>点击修改您的密码</p>
                            </div>
                        </a>
                    </li>
                    <li>
                        <a href="#" onclick="logoutConfirm()">
                            <i class="menu-icon fa fa-sign-out bg-red"></i>

                            <div class="menu-info">
                                <h4 class="control-sidebar-subheading">退出系统</h4>

                                <p>点击退出系统</p>
                            </div>
                        </a>
                    </li>
                    <#--<li>-->
                        <#--<a href="javascript:void(0)">-->
                            <#--<i class="menu-icon fa fa-birthday-cake bg-red"></i>-->

                            <#--<div class="menu-info">-->
                                <#--<h4 class="control-sidebar-subheading">Langdon's Birthday</h4>-->

                                <#--<p>Will be 23 on April 24th</p>-->
                            <#--</div>-->
                        <#--</a>-->
                    <#--</li>-->
                    <#--<li>-->
                        <#--<a href="javascript:void(0)">-->
                            <#--<i class="menu-icon fa fa-envelope-o bg-light-blue"></i>-->

                            <#--<div class="menu-info">-->
                                <#--<h4 class="control-sidebar-subheading">Nora Joined Mailing List</h4>-->

                                <#--<p>nora@example.com</p>-->
                            <#--</div>-->
                        <#--</a>-->
                    <#--</li>-->
                </ul>
                <!-- /.control-sidebar-menu -->
            </div>
            <!-- /.tab-pane -->
            <!-- Stats tab content -->
            <div class="tab-pane" id="control-sidebar-stats-tab">Stats Tab Content</div>
            <!-- /.tab-pane -->
        </div>
    </aside>
    <!-- /.control-sidebar -->
    <#--lcz add end-->

    <!-- Control Sidebar -->
    <div class="control-sidebar-bg"></div>

    <!-- 修改密码 -->
    <!-- 模态框（Modal） -->
    <div class="modal fade" id="updatePasswordModal" tabindex="-1" role="dialog" aria-labelledby="updatePasswordModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                    <h4 class="modal-title" id="myModalLabel">
                        修改密码
                    </h4>
                </div>
                <div class="modal-body">
                    <form>
                        <div class="form-group">
                            <label for="username" class="control-label">账号:</label>
                            <span class="label label-success" style="vertical-align: bottom;">${currentUser.username}</span>
                        </div>
                        <div class="form-group">
                            <label for="password" class="control-label">原密码:</label>
                            <input type="password" class="form-control" id="password">
                        </div>
                        <div class="form-group">
                            <label for="newPassword" class="control-label">新密码:</label>
                            <input type="password" class="form-control" id="newPassword">
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary btn-flat" onclick="updatePassword()">
                        提交
                    </button>
                    <button type="button" class="btn btn-default btn-flat" data-dismiss="modal">
                        关闭
                    </button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>
</div>
<!-- ./wrapper -->
<!-- jQuery -->
<script src="/static/dist/js/jquery.min.js"></script>
<!-- Bootstrap -->
<script src="/static/dist/js/bootstrap.min.js"></script>
<!-- AdminLTE App -->
<script src="/static/dist/js/app.min.js"></script>
<!-- layer -->
<script src="/static/plugins/layer/layer.js"></script>
<script src="/static/js/index.js"></script>
</body>
</html>
