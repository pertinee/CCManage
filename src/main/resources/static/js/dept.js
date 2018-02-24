var setting = {
    data: {
        simpleData: {
            enable: true,
            idKey: "deptId",
            pIdKey: "parentId",
            rootPId: -1
        },
        key: {
            url:"nourl"
        }
    }
};

var Dept = {
    id: "deptTable",
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Dept.initColumn = function () {
    var columns = [
        {field: 'selectItem', radio: true},
        {title: '部门ID', field: 'deptId', visible: false, align: 'center', valign: 'middle', width: '80px'},
        {title: '部门名称', field: 'name', align: 'center', valign: 'middle', sortable: true, width: '180px'},
        {title: '上级部门', field: 'parentName', align: 'center', valign: 'middle', sortable: true, width: '100px'},
        {title: '排序号', field: 'orderNum', align: 'center', valign: 'middle', sortable: true, width: '100px'}]
    return columns;
};

function getDeptId () {
    var selected = $('#deptTable').bootstrapTreeTable('getSelections');
    if (selected.length == 0) {
        alert("请选择一条记录");
        return;
    } else {
        return selected[0].id;
    }
}


$(function () {
    $.get("/sys/dept/info", function(r){
        var colunms = Dept.initColumn();
        var table = new TreeTable(Dept.id, "/sys/dept/list", colunms);
        table.setRootCodeValue(r.deptId);
        table.setExpandColumn(2);
        table.setIdField("deptId");
        table.setCodeField("deptId");
        table.setParentCodeField("parentId");
        table.setExpandAll(false);
        table.init();
        Dept.table = table;
    });
});

//重置
function reset() {
    $("#deptId").val("");
    $("#name").val("");
    $("#parentId").val("");
    $("#parentName").val("");
    $("#orderNum").val("");
}

//刷新部门页面
function reload() {
    //重置
    reset();
    $("#deptDiv").removeClass("hide");
    $("#editDeptDiv").addClass("hide");
    Dept.table.refresh();
}

//新增部门跳转页面
function addView(){
    //重置
    reset();
    //初始化赋值
    $("#parentId").val("0");
    $("#parentName").val(null);
    $("#orderNum").val(0);
    $("#editHeaderDiv").text("新增");
    $("#editDeptDiv").removeClass("hide");
    $("#deptDiv").addClass("hide");
    getDept();
}


//修改部门跳转页面
function updateView() {
    var deptId = getDeptId();
    if(deptId == null){
        return ;
    }
    $("#editHeaderDiv").text("修改");
    $("#editDeptDiv").removeClass("hide");
    $("#deptDiv").addClass("hide");
    $.get("/sys/dept/info/"+deptId, function(r){
        debugger
        $("#deptId").val(r.dept.deptId);
        $("#name").val(r.dept.name);
        $("#parentId").val(r.dept.parentId);
        //这里不需要取父级部门名字，在getDept()方法中获取
        //$("#parentName").val(r.dept.parentName);
        $("#orderNum").val(r.dept.orderNum);
        getDept();
    });
}

//新增、修改部门
function saveOrUpdate() {
    var deptId = $("#deptId").val();
    var name = $("#name").val();
    var parentId = $("#parentId").val();
    var parentName = $("#parentName").val();
    var orderNum = $("#orderNum").val();
    var url = (deptId == null || deptId == "") ? "/sys/dept/save" : "/sys/dept/update";
    debugger;
    $.ajax({
        type: "POST",
        url: url,
        contentType: "application/json",
        data: JSON.stringify({
            deptId: deptId,
            name: name,
            parentId: parentId,
            parentName: parentName,
            orderNum: orderNum
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

//删除部门
function del() {
    var deptId = getDeptId();
    if(deptId == null){
        return ;
    }
    confirm('确定要删除选中的记录？', function(){
        $.ajax({
            type: "POST",
            url: "/sys/dept/delete",
            data: "deptId=" + deptId,
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

    });
}

//获取部门
function getDept(){
    var parentId = $("#parentId").val();
    //加载部门树
    $.get("/sys/dept/select", function(r){
        ztree = $.fn.zTree.init($("#deptTree"), setting, r.deptList);
        var node = ztree.getNodeByParam("deptId", parentId);
        ztree.selectNode(node);
        $("#parentName").val(node.name);
    })
}

//部门树
function deptTree(){
    layer.open({
        type: 1,
        offset: '50px',
        skin: 'layui-layer-molv',
        title: "选择部门",
        area: ['300px', '450px'],
        shade: 0,
        shadeClose: false,
        content: jQuery("#deptLayer"),
        btn: ['确定', '取消'],
        btn1: function (index) {
            var node = ztree.getSelectedNodes();
            //选择上级部门
            $("#parentId").val(node[0].deptId);
            $("#parentName").val(node[0].name);

            layer.close(index);
        }
    });
}