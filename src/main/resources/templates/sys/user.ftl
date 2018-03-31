<!DOCTYPE html>
<html>
<head>
    <title>管理员列表</title>
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
    <#--<script src="/static/plugins/layer/layer.js"></script>-->
    <#--<script src="/static/plugins/jqgrid/grid.locale-cn.js"></script>-->
    <#--<script src="/static/plugins/jqgrid/jquery.jqGrid.min.js"></script>-->
    <#--<script src="/static/js/common.js"></script>-->
</head>
<body>
    <div id="userDiv">
        <div class="grid-btn">
            <div class="form-group col-sm-2">
                <input type="text" class="form-control" id="qUsername" placeholder="用户名">
            </div>
            <a class="btn btn-default" onclick="query()"><i class="fa fa-search"></i>&nbsp;查询</a>
            <a class="btn btn-primary" onclick="addView()"><i class="fa fa-plus"></i>&nbsp;新增</a>
            <a class="btn btn-primary" onclick="updateView()"><i class="fa fa-pencil-square-o"></i>&nbsp;修改</a>
            <a class="btn btn-danger" onclick="del()"><i class="fa fa-trash-o"></i>&nbsp;删除</a>
        </div>
        <table id="jqGrid"></table>
        <div id="jqGridPager"></div>
    </div>
    <div id="editUserDiv" class="panel panel-default hide">
        <div id="editHeaderDiv" class="panel-heading"></div>
        <form id="userForm" class="form-horizontal">
            <input type="text" class="form-control hide" id="userId" name="userId"/>
            <input type="text" class="form-control hide" id="deptId" name="deptId"/>
            <input type="text" class="form-control hide" id="createUserId" name="createUserId"/>
            <div class="form-group">
                <div class="col-sm-2 control-label">用户名</div>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="username" name="username" placeholder="登录账号"/>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-2 control-label">所属部门</div>
                <div class="col-sm-10">
                    <input type="text" class="form-control" style="cursor:pointer;" id="deptName" onclick="deptTree()" readonly="readonly" placeholder="所属部门"/>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-2 control-label">密码</div>
                <div class="col-sm-10">
                    <input type="password" class="form-control" id="password" name="password" placeholder="密码"/>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-2 control-label">邮箱</div>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="email" name="email" placeholder="邮箱"/>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-2 control-label">手机号</div>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="mobile" name="mobile" placeholder="手机号"/>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-2 control-label">角色</div>
                <div class="col-sm-10">
                    <#list sysRoleList as role>
                        <labe class="checkbox-inline">
                            <input type="checkbox" value="${role.roleId}" name="roleIdList">${role.roleName}
                        </labe>
                    </#list>
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
    <!-- 选择部门 -->
    <div id="deptLayer" style="display: none;padding:10px;">
        <ul id="deptTree" class="ztree"></ul>
    </div>

<script src="/static/js/user.js"></script>
</body>
</html>