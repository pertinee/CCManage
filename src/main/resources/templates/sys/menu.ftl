<!DOCTYPE html>
<html>
<head>
    <title>菜单管理</title>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <#include "common_css.ftl">
    <#include "common_js.ftl">
    <#--<link rel="stylesheet" href="/static/dist/css/bootstrap.min.css">-->
    <#--<link rel="stylesheet" href="/static/dist/css/bootstrap-table.min.css">-->
    <#--<link rel="stylesheet" href="/static/dist/css/font-awesome.min.css">-->
    <#--<link rel="stylesheet" href="/static/plugins/jqgrid/ui.jqgrid-bootstrap.css">-->
    <#--<link rel="stylesheet" href="/static/plugins/ztree/css/metroStyle/metroStyle.css">-->
    <#--<link rel="stylesheet" href="/static/plugins/treegrid/jquery.treegrid.css">-->
    <#--<link rel="stylesheet" href="/static/css/main.css">-->
    <#--<script src="/static/dist/js/jquery.min.js"></script>-->
    <#--<script src="/static/plugins/layer/layer.js"></script>-->
    <#--<script src="/static/dist/js/bootstrap.min.js"></script>-->
    <#--<script src="/static/plugins/jqgrid/grid.locale-cn.js"></script>-->
    <#--<script src="/static/plugins/jqgrid/jquery.jqGrid.min.js"></script>-->
    <#--<script src="/static/plugins/ztree/jquery.ztree.all.min.js"></script>-->
    <#--<script src="/static/dist/js/bootstrap-table.min.js"></script>-->
    <#--<script src="/static/plugins/treegrid/jquery.treegrid.min.js"></script>-->
    <#--<script src="/static/plugins/treegrid/jquery.treegrid.bootstrap3.js"></script>-->
    <#--<script src="/static/plugins/treegrid/jquery.treegrid.extension.js"></script>-->
    <#--<script src="/static/plugins/treegrid/tree.table.js"></script>-->
    <#--<script src="/static/js/common.js"></script>-->
</head>
<body>
<div id="menuDiv">
    <div class="grid-btn">
        <a class="btn btn-primary" onclick="addView()"><i class="fa fa-plus"></i>&nbsp;新增</a>
        <a class="btn btn-primary" onclick="updateView()"><i class="fa fa-pencil-square-o"></i>&nbsp;修改</a>
        <a class="btn btn-primary" onclick="del()"><i class="fa fa-trash-o"></i>&nbsp;删除</a>
    </div>
    <table id="menuTable" data-mobile-responsive="true" data-click-to-select="true">
        <thead>
        <tr>
            <th data-field="selectItem" data-checkbox="true"></th>
        </tr>
        </thead>
    </table>
    <#--<table id="jqGrid"></table>-->
    <#--<div id="jqGridPager"></div>-->
</div>

<div id="editMenuDiv" class="panel panel-default hide">
    <div id="editHeaderDiv" class="panel-heading"></div>
    <form class="form-horizontal">
        <input type="text" class="form-control hide" id="menuId" name="menuId"/>
        <div class="form-group">
            <div class="col-sm-2 control-label">类型</div>
            <label class="radio-inline">
                <input type="radio" value="0" name="menuType"/> 目录
            </label>
            <label class="radio-inline">
                <input type="radio" value="1" name="menuType"/> 菜单
            </label>
            <label class="radio-inline">
                <input type="radio" value="2" name="menuType"/> 按钮
            </label>
        </div>
        <div class="form-group">
            <div class="col-sm-2 control-label">菜单名称</div>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="name" name="name" placeholder="菜单名称或按钮名称"/>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-2 control-label">上级菜单</div>
            <div class="col-sm-10">
                <input type="text" class="form-control hide" id="parentId" name="parentId" placeholder="上级菜单id"/>
                <input type="text" class="form-control" style="cursor:pointer;" id="parentName" name="parentName" onclick="menuTree()" readonly="readonly" placeholder="一级菜单"/>
            </div>
        </div>
        <div id="type_1" class="form-group hide">
            <div class="col-sm-2 control-label">菜单URL</div>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="url" name="url" placeholder="菜单URL"/>
            </div>
        </div>
        <div id="type_1or2" class="form-group hide">
            <div class="col-sm-2 control-label">授权标识</div>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="perms" name="perms" placeholder="多个用逗号分隔，如：user:list,user:create"/>
            </div>
        </div>
        <div id="type_0or1" class="form-group hide">
            <div class="col-sm-2 control-label">排序号</div>
            <div class="col-sm-10">
                <input type="number" class="form-control" id="orderNum" name="orderNum" placeholder="排序号"/>
            </div>
        </div>
        <div id="type_0or1" class="form-group hide">
            <div class="col-sm-2 control-label">图标</div>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="icon" name="icon" placeholder="菜单图标"/>
                <code style="margin-top:4px;display: block;">获取图标：http://fontawesome.io/icons/</code>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-2 control-label"></div>
            <input type="button" class="btn btn-primary" onclick="saveOrUpdate()" value="确定"/>
            &nbsp;&nbsp;<input type="button" class="btn btn-warning" onclick="reload()" value="返回"/>
        </div>
    </form>
</div>

<!-- 选择菜单 -->
<div id="menuLayer" style="display: none;padding:10px;">
    <ul id="menuTree" class="ztree"></ul>
</div>

<script src="/static/js/menu.js"></script>
</body>
</html>