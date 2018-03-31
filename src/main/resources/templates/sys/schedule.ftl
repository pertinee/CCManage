<!DOCTYPE html>
<html>
<head>
    <title>定时任务</title>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
<#include "common_css.ftl">
<#include "common_js.ftl">
</head>
<body>
<div id="logDiv">
    <div class="grid-btn" style="height:34px;">
        <div class="form-group col-sm-2">
            <input type="text" class="form-control" id="qBeanName" placeholder="bean名称">
        </div>
        <a class="btn btn-default" onclick="query()"><i class="fa fa-search"></i>&nbsp;查询</a>
        <a class="btn btn-primary" onclick="addView()"><i class="fa fa-plus"></i>&nbsp;新增</a>
        <a class="btn btn-primary" onclick="updateView()"><i class="fa fa-pencil-square-o"></i>&nbsp;修改</a>
        <a class="btn btn-danger" onclick="del()"><i class="fa fa-trash-o"></i>&nbsp;删除</a>
        <a class="btn btn-warning" onclick="pause()"><i class="fa fa-pause"></i>&nbsp;暂停</a>
        <a class="btn btn-info" onclick="resume()"><i class="fa fa-play"></i>&nbsp;恢复</a>
        <a class="btn btn-success" onclick="runOnce()"><i class="fa fa-arrow-circle-right"></i>&nbsp;立即执行</a>
        <a class="btn btn-primary" style="float:right;" href="/sys/schedule_log"><i class="fa fa-list"></i>&nbsp;日志列表</a>
    </div>
    <table id="jqGrid"></table>
    <div id="jqGridPager"></div>
</div>

<div id="editLogDiv" class="panel panel-default hide">
    <div id="editHeaderDiv" class="panel-heading"></div>
    <form class="form-horizontal">
        <input type="text" class="form-control hide" id="jobId" name="jobId"/>
        <input type="text" class="form-control hide" id="status" name="status"/>
        <div class="form-group">
            <div class="col-sm-2 control-label">bean名称</div>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="beanName" placeholder="spring bean名称，如：testTask"/>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-2 control-label">方法名称</div>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="methodName" placeholder="方法名称"/>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-2 control-label">参数</div>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="params" placeholder="参数"/>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-2 control-label">cron表达式</div>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="cronExpression" placeholder="如：0 0 12 * * ?"/>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-2 control-label">备注</div>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="remark" placeholder="备注"/>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-2 control-label"></div>
            <input type="button" class="btn btn-primary" onclick="saveOrUpdate()" value="确定"/>
            &nbsp;&nbsp;<input type="button" class="btn btn-warning" onclick="reload()" value="返回"/>
        </div>
    </form>
</div>

<script src="/static/js/schedule.js"></script>
</body>
</html>