<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>登录</title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="icon" type="image/x-icon" href="/static/favicon.ico">
    <link rel="stylesheet" href="/static/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="/static/css/login.css">
</head>
<body>
<div class="container">
    <div class="login-img">
        <img src="/static/image/head.png">
    </div>
    <div class="login-logo">
        <b>CCManage</b>
    </div>
    <!-- /.login-logo -->

    <div class="well col-lg-4 col-lg-offset-4 col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3 col-xs-12 shadow">
        <form role="form">
            <div class="form-group has-feedback">
                <input type="text" class="form-control" id="username" placeholder="用户名" autofocus>
                <span class="glyphicon glyphicon-user form-control-feedback"></span>
            </div>
            <div class="form-group has-feedback">
                <input type="password" class="form-control" id="password" placeholder="密码">
                <span class="glyphicon glyphicon-lock form-control-feedback"></span>
            </div>
            <div class="form-group has-feedback">
                <input type="text" class="form-control" id="captcha" placeholder="验证码">
                <span class="glyphicon glyphicon-warning-sign form-control-feedback"></span>
            </div>
            <div class="form-group has-feedback">
                <img alt="If you can not see, please click the image refresh" id="code" class="pointer" onclick="refreshCode()">
                <a href="javascript:;" onclick="refreshCode()">点击刷新</a>
            </div>
            <div class="form-group">
                <button type="button" class="btn btn-primary btn-flat" onclick="login()">登录</button>
            </div>
        </form>
    </div>
</div>
<script src="/static/dist/js/jquery.min.js"></script>
<script src="/static/dist/js/bootstrap.min.js"></script>
<script src="/static/plugins/layer/layer.js"></script>
<script type="text/javascript" src="/static/js/login.js"></script>
</body>
</html>
