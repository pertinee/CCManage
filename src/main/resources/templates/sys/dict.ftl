<!DOCTYPE html>
<html>
<head>
    <title>数据字典管理</title>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
<#include "common_css.ftl">
<#include "common_js.ftl">
<#--<link rel="stylesheet" href="/static/dist/css/bootstrap.min.css">-->
<#--<link rel="stylesheet" href="/static/dist/css/font-awesome.min.css">-->
<#--<link rel="stylesheet" href="/static/plugins/jqgrid/ui.jqgrid-bootstrap.css">-->
<#--<link rel="stylesheet" href="/static/plugins/ztree/css/metroStyle/metroStyle.css">-->
<#--<link rel="stylesheet" href="/static/css/main.css">-->
<#--<script src="/static/dist/js/jquery.min.js"></script>-->
<#--<script src="/static/plugins/layer/layer.js"></script>-->
<#--<script src="/static/dist/js/bootstrap.min.js"></script>-->
<#--<script src="/static/plugins/jqgrid/grid.locale-cn.js"></script>-->
<#--<script src="/static/plugins/jqgrid/jquery.jqGrid.min.js"></script>-->
<#--<script src="/static/plugins/ztree/jquery.ztree.all.min.js"></script>-->
<#--<script src="/static/js/common.js"></script>-->
</head>
<body>
<div>
    <div id="dictDiv">
        <div class="grid-btn">
            <div class="form-group col-sm-2">
                <input type="text" class="form-control" id="qDictName" placeholder="字典名称">
            </div>
            <a class="btn btn-default" onclick="query()"><i class="fa fa-search"></i>&nbsp;查询</a>

            <a class="btn btn-primary" onclick="addView()"><i class="fa fa-plus"></i>&nbsp;新增</a>
            <a class="btn btn-primary" onclick="updateView()"><i class="fa fa-pencil-square-o"></i>&nbsp;修改</a>
            <a class="btn btn-danger" onclick="del()"><i class="fa fa-trash-o"></i>&nbsp;删除</a>
        </div>
        <table id="jqGrid"></table>
        <div id="jqGridPager"></div>
    </div>

    <div id="editDictDiv" class="panel panel-default hide">
        <div id="editHeaderDiv" class="panel-heading"></div>
        <form class="form-horizontal">
            <input type="text" class="form-control hide" id="dictId" name="dictId"/>
            <div class="form-group">
                <div class="col-sm-2 control-label">字典名称</div>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="dictName" name="dictName" placeholder="字典名称"/>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-2 control-label">备注</div>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="remark" name="remark" placeholder="备注"/>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-2 control-label">授权</div>
                <div class="col-sm-10">
                    <ul id="menuTree" class="ztree"></ul>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-2 control-label"></div>
                <input type="button" class="btn btn-primary" onclick="saveOrUpdate()" value="确定"/>
                &nbsp;&nbsp;<input type="button" class="btn btn-warning" onclick="reload()" value="返回"/>
            </div>
        </form>
    </div>
</div>

<script src="/static/js/dict.js"></script>
</body>
</html>