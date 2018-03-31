<!DOCTYPE html>
<html>
<head>
    <title>定时任务日志</title>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <#include "common_css.ftl">
    <#include "common_js.ftl">
</head>
<body>
    <div class="grid-btn">
        <div class="form-group col-sm-2">
            <input type="text" class="form-control" id="qJobId" placeholder="任务ID">
        </div>
        <a class="btn btn-default" onclick="query()"><i class="fa fa-search"></i>&nbsp;查询</a>
        <a class="btn btn-warning" onclick="back()"><i class="fa fa-mail-reply"></i>&nbsp;返回</a>
    </div>
    <table id="jqGrid"></table>
    <div id="jqGridPager"></div>

<script src="/static/js/schedule_log.js"></script>
</body>
</html>