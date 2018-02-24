<!DOCTYPE html>
<html>
<head>
	<title>参数管理</title>
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
	<div id="configDiv">
		<div class="grid-btn">
			<div class="form-group col-sm-2">
                <input type="text" class="form-control" id="qKey" placeholder="参数名">
			</div>
			<a class="btn btn-default" onclick="query()">查询</a>
			
			<a class="btn btn-primary" onclick="addView()"><i class="fa fa-plus"></i>&nbsp;新增</a>
			<a class="btn btn-primary" onclick="updateView()"><i class="fa fa-pencil-square-o"></i>&nbsp;修改</a>
			<a class="btn btn-primary" onclick="del()"><i class="fa fa-trash-o"></i>&nbsp;删除</a>
		</div>
	    <table id="jqGrid"></table>
	    <div id="jqGridPager"></div>
    </div>

   	<div id="editConfigDiv" class="panel panel-default hide">
		<div id="editHeaderDiv" class="panel-heading"></div>
		<form class="form-horizontal">
            <input type="text" class="form-control hide" id="id" name="id"/>
			<div class="form-group">
			   	<div class="col-sm-2 control-label">参数名</div>
			   	<div class="col-sm-10">
			      <input type="text" class="form-control" id="key" name="key" placeholder="参数名"/>
			    </div>
			</div>
			<div class="form-group">
			   	<div class="col-sm-2 control-label">参数值</div>
			   	<div class="col-sm-10">
			      <input type="text" class="form-control" id="value" name="value" placeholder="参数值"/>
			    </div>
			</div>
			<div class="form-group">
			   	<div class="col-sm-2 control-label">备注</div>
			   	<div class="col-sm-10">
			      <input type="text" class="form-control" id="remark" name="remark" placeholder="备注"/>
			    </div>
			</div>
            <div class="form-group">
                <div class="col-sm-2 control-label">状态</div>
                <label class="radio-inline">
                    <input type="radio" name="status" value="1"/> 正常
                </label>
                <label class="radio-inline">
                    <input type="radio" name="status" value="0"/> 禁用
                </label>
            </div>
			<div class="form-group">
				<div class="col-sm-2 control-label"></div> 
				<input type="button" class="btn btn-primary" onclick="saveOrUpdate()" value="确定"/>
				&nbsp;&nbsp;<input type="button" class="btn btn-warning" onclick="reload()" value="返回"/>
			</div>
		</form>
	</div>

<script src="/static/js/config.js"></script>
</body>
</html>