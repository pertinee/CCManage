<!DOCTYPE html>
<html>
<head>
    <title>系统日志</title>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <#include "common_css.ftl">
    <#include "common_js.ftl">
    <#--<link rel="stylesheet" href="/static/dist/css/bootstrap.min.css">-->
    <#--<link rel="stylesheet" href="/static/dist/css/font-awesome.css">-->
    <#--<link rel="stylesheet" href="/static/plugins/jqgrid/ui.jqgrid-bootstrap.css">-->
    <#--<link rel="stylesheet" href="/static/css/main.css">-->
    <#--<script src="/static/dist/js/jquery.min.js"></script>-->
    <#--<script src="/static/dist/js/bootstrap.min.js"></script>-->
    <#--<script src="/static/plugins/jqgrid/grid.locale-cn.js"></script>-->
    <#--<script src="/static/plugins/jqgrid/jquery.jqGrid.min.js"></script>-->
    <#--<script src="/static/js/common.js"></script>-->
</head>
<body>
    <div>
        <div class="grid-btn">
            <div class="form-group col-sm-2">
                <input type="text" class="form-control" id="qKey" placeholder="用户名、用户操作">
            </div>
            <a class="btn btn-default" onclick="query()">查询</a>
        </div>
        <table id="jqGrid"></table>
        <div id="jqGridPager"></div>
    </div>

<script src="/static/js/log.js"></script>
</body>
</html>