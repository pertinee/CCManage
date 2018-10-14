$(function () {
    $("#jqGrid").jqGrid({
        url: '/sys/dict/list',
        datatype: "json",
        colModel: [
            { label: '字典唯一ID', name: 'id', index: "id", width: 45, key: true, hidden:true },
            { label: '字典ID', name: 'dict_id', index: "dict_id", width: 45 },
            { label: '字典名称', name: 'dictName', index: "dict_name", width: 75 },
            { label: '字典数值', name: 'dictValue', index: "dict_value", width: 75 },
            { label: '字典提示', name: 'dictPrompt', index: "dict_prompt", width: 75 },
            { label: '权限', name: 'accessLevel', index: "access_level", width: 75, hidden:true },
            { label: '权限', name: 'accessLevelCn', index: "access_level", width: 75 },
            { label: '排序', name: 'orderId', index: "order_id", width: 75 },
            { label: '备注', name: 'remark', index: "remark", width: 100 }
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
    $("#dictId").val("");
    $("#dictName").val("");
    $("#dictValue").val("");
    $("#dictPrompt").val("");
    $("#accessLevel").val("");
    $("#orderId").val("");
    $("#remark").val("");
}

//查询字典
function query() {
    reload();
}

//刷新页面
function reload() {
    //重置
    reset();
    $("#dictDiv").removeClass("hide");
    $("#editDictDiv").addClass("hide");
    var qDictName = $("#qDictName").val();
    var page = $("#jqGrid").jqGrid('getGridParam','page');
    $("#jqGrid").jqGrid('setGridParam',{
        postData:{'dictName': qDictName},
        page:page
    }).trigger("reloadGrid");
}

//添加跳转页面
function addView(){
    //重置
    reset();
    $("#editDictDiv").removeClass("hide");
    $("#dictDiv").addClass("hide");
    $("#editHeaderDiv").text("新增");
}

//修改按钮跳转页面
function updateView() {
    //重置
    reset();
    $("#editHeaderDiv").text("修改");

    var id = getSelectedRow();
    if(id == null){
        return;
    }
    $("#dictId").val(id);
    $("#editDictDiv").removeClass("hide");
    $("#dictDiv").addClass("hide");
}

//新增、修改字典
function saveOrUpdate() {
    var id = $("#dictId").val();
    var dictName = $("#dictName").val();
    var dictValue = $("#dictValue").val();
    var dictPrompt = $("#dictPrompt").val();
    var accessLevel = $("#accessLevel").val();
    var orderId = $("#orderId").val();
    var remark = $("#remark").val();
    //获取选择的菜单
    var nodes = ztree.getCheckedNodes(true);
    var menuIdList = new Array();
    for(var i=0; i<nodes.length; i++) {
        menuIdList.push(nodes[i].menuId);
    }
    var url = (id == null || id == "") ? "/sys/dict/save" : "/sys/dict/update";
    $.ajax({
        type: "POST",
        url: url,
        contentType: "application/json",
        data: JSON.stringify({
            id: id,
            dictName: dictName,
            dictValue: dictValue,
            dictPrompt: dictPrompt,
            accessLevel: accessLevel,
            orderId: orderId,
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

//删除字典
function del() {
    var ids = getSelectedRows();
    if(ids == null){
        return ;
    }
    confirm('确定要删除选中的记录？', function(){
        $.ajax({
            type: "POST",
            url: "/sys/dict/delete",
            contentType: "application/json",
            data: JSON.stringify(ids),
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