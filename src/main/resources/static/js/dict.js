$(function () {
    $("#jqGrid").jqGrid({
        url: '/sys/dict/list',
        datatype: "json",
        colModel: [
            { label: '字典唯一ID', name: 'id', index: "id", width: 45, key: true, hidden:true },
            { label: '字典ID', name: 'dictId', index: "dict_id", width: 45, hidden:true },
            { label: '字典名称', name: 'dictName', index: "dict_name", width: 75 },
            { label: '字典数值', name: 'dictValue', index: "dict_value", width: 75 },
            { label: '字典提示', name: 'dictPrompt', index: "dict_prompt", width: 75 },
            { label: '权限', name: 'accessLevel', index: "access_level", width: 75, hidden:true },
            { label: '权限', name: 'accessLevelCn', index: "access_level", width: 75 , formatter: function(item, index){
                if(item == '可修改'){
                    return '<span class="label label-success">'+ item +'</span>';
                }else{
                    return '<span class="label label-warning">'+ item +'</span>';
                }
            }},
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
    $("#jqGrid").setGridHeight($(window).height() - 150); // 因为有新增提示信息，所以这边底部路边要减去更大一点
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
    $("#id").val("");
    $("#dictId").val("");
    $("#dictName").val("");
    $("#dictValue").val("");
    $("#dictPrompt").val("");
    // 初始化checkbox，使之全部未选中
    $("input[name='accessLevel']").each(function(){
        $(this).prop("checked",false);
    });
    $("#orderId").val("");
    $("#remark").val("");
}

//查询字典
function query() {
    reload();
}

//刷新页面
function reload() {
    reset();
    $("#dictDiv").removeClass("hide");
    $("#editDictDiv").addClass("hide");
    $("#dictName").removeAttr("readonly","readonly");
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
    var data = getSelectedRowData();
    if(data == null){
        // 新增数据字典和数据字典详情
        $("#editHeaderDiv").text("新增");
    }else{
        // 扩展数据字典详情
        $("#editHeaderDiv").text("扩展");
        $("#dictName").attr("readonly","readonly");
        $("#dictId").val(data.dictId);
        $("#dictName").val(data.dictName);
    }
}

//修改按钮跳转页面
function updateView() {
    //重置
    reset();
    var data = getSelectedRowData();
    if(data == null){
        return;
    }
    if(data.accessLevel != '2'){
        alert("数据字典不可修改");
        return;
    }
    $("#editHeaderDiv").text("修改");
    $("#editDictDiv").removeClass("hide");
    $("#dictDiv").addClass("hide");
    $("#dictName").attr("readonly","readonly");

    $("#id").val(data.id);
    $("#dictId").val(data.dictId);
    $("#dictName").val(data.dictName);
    $("#dictValue").val(data.dictValue);
    $("#dictPrompt").val(data.dictPrompt);
    $("input:radio[value= "+ data.accessLevel +"]").prop('checked',true);//设置当前选中项
    $("#orderId").val(data.orderId);
    $("#remark").val(data.remark);
}

//新增、修改字典
function saveOrUpdate() {
    var id = $("#id").val();
    var dictId = $("#dictId").val();
    var dictName = $("#dictName").val();
    var dictValue = $("#dictValue").val();
    var dictPrompt = $("#dictPrompt").val();
    var accessLevel = $("input[name='accessLevel']:checked").val();
    var orderId = $("#orderId").val();
    var remark = $("#remark").val();
    var url = (id == null || id == "") ? "/sys/dict/save" : "/sys/dict/update";
    $.ajax({
        type: "POST",
        url: url,
        contentType: "application/json",
        data: JSON.stringify({
            id: id,
            dictId: dictId,
            dictName: dictName,
            dictValue: dictValue,
            dictPrompt: dictPrompt,
            accessLevel: accessLevel,
            orderId: orderId,
            remark: remark
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