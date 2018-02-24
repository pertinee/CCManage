$(function () {
    $("#jqGrid").jqGrid({
        url: '/sys/role/list',
        datatype: "json",
        colModel: [
            { label: '角色ID', name: 'roleId', index: "role_id", width: 45, key: true, hidden:true },
            { label: '角色名称', name: 'roleName', index: "role_name", width: 75 },
            { label: '备注', name: 'remark', width: 100 },
            { label: '创建时间', name: 'createDate', index: "create_date", width: 80}
        ],
        viewrecords: true,
        height: 340,
        rowNum: 10,
        rowList : [10,30,50],
        rownumbers: true,
        rownumWidth: 25,
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page",
            rows:"limit",
            order: "order"
        },
        gridComplete:function(){
            //隐藏grid底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" });
        }
    });

    //jqGrid自适应
    $("#jqGrid").setGridHeight($(window).height() - 130);
});

var setting = {
    data: {
        simpleData: {
            enable: true,
            idKey: "menuId",
            pIdKey: "parentId",
            rootPId: -1
        },
        key: {
            url:"nourl"
        }
    },
    check:{
        enable:true,
        nocheckInherit:true
    }
};

//重置
function reset() {
    $("#roleId").val("");
    $("#roleName").val("");
    $("#remark").val("");
    getMenuTree(null);
}

//查询角色
function query() {
    reload();
}

//刷新页面
function reload() {
    //重置
    reset();
    $("#roleDiv").removeClass("hide");
    $("#editRoleDiv").addClass("hide");
    var qRoleName = $("#qRoleName").val();
    var page = $("#jqGrid").jqGrid('getGridParam','page');
    $("#jqGrid").jqGrid('setGridParam',{
        postData:{'roleName': qRoleName},
        page:page
    }).trigger("reloadGrid");
}

//添加跳转页面
function addView(){
    //重置
    reset();
    $("#editRoleDiv").removeClass("hide");
    $("#roleDiv").addClass("hide");
    $("#editHeaderDiv").text("新增");
}

//修改按钮跳转页面
function updateView() {
    //重置
    reset();
    $("#editHeaderDiv").text("修改");

    var roleId = getSelectedRow();
    if(roleId == null){
        return;
    }
    $("#roleId").val(roleId);
    $("#editRoleDiv").removeClass("hide");
    $("#roleDiv").addClass("hide");
    getMenuTree(roleId);
}

//新增、修改角色
function saveOrUpdate() {
    var roleId = $("#roleId").val();
    var roleName = $("#roleName").val();
    var remark = $("#remark").val();
    //获取选择的菜单
    var nodes = ztree.getCheckedNodes(true);
    var menuIdList = new Array();
    for(var i=0; i<nodes.length; i++) {
        menuIdList.push(nodes[i].menuId);
    }
    // vm.role.menuIdList = menuIdList;
    var url = (roleId == null || roleId == "") ? "/sys/role/save" : "/sys/role/update";
    $.ajax({
        type: "POST",
        url: url,
        contentType: "application/json",
        data: JSON.stringify({
            roleId: roleId,
            roleName: roleName,
            remark: remark,
            menuIdList : menuIdList
        }),
        success: function(r){
            if(r.code === 0){
                alert('操作成功', function(index) {
                    reload();
                });
            }else{
                alert(r.msg);
            }
        }
    });
}

//删除角色
function del() {
    var roleIds = getSelectedRows();
    if(roleIds == null){
        return ;
    }
    confirm('确定要删除选中的记录？', function(){
        $.ajax({
            type: "POST",
            url: "/sys/role/delete",
            contentType: "application/json",
            data: JSON.stringify(roleIds),
            success: function(r){
                if(r.code == 0){
                    alert('操作成功', function(index) {
                        reload();
                    });
                }else{
                    alert(r.msg);
                }
            }
        });
    });
}

//获取菜单树
function getMenuTree(roleId) {
    //加载菜单树
    $.get("/sys/menu/perms", function(r){
        ztree = $.fn.zTree.init($("#menuTree"), setting, r.menuList);
        //展开所有节点
        ztree.expandAll(true);

        if(roleId != null){
            getRole(roleId);
        }
    });
}

//获取角色信息
function getRole(roleId){
    $.get("/sys/role/info/"+roleId, function(r){
        $("#roleName").val(r.role.roleName);
        $("#remark").val(r.role.remark);
        //勾选角色所拥有的菜单
        var menuIds = r.role.menuIdList;
        for(var i=0; i<menuIds.length; i++) {
            var node = ztree.getNodeByParam("menuId", menuIds[i]);
            ztree.checkNode(node, true, false);
        }
    });
}