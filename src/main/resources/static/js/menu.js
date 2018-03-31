var Menu = {
    id: "menuTable",
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Menu.initColumn = function () {
    var columns = [
        {field: 'selectItem', radio: true},
        {title: '菜单ID', field: 'menuId', visible: false, align: 'center', valign: 'middle', width: '80px'},
        {title: '菜单名称', field: 'name', align: 'center', valign: 'middle', sortable: true, width: '180px'},
        {title: '上级菜单', field: 'parentName', align: 'center', valign: 'middle', sortable: true, width: '100px'},
        {title: '图标', field: 'icon', align: 'center', valign: 'middle', sortable: true, width: '80px', formatter: function(item, index){
            return item.icon == null ? '' : '<i class="'+item.icon+' fa-lg"></i>';
        }},
        {title: '类型', field: 'type', align: 'center', valign: 'middle', sortable: true, width: '100px', formatter: function(item, index){
            if(item.type === 0){
                return '<span class="label label-primary">目录</span>';
            }
            if(item.type === 1){
                return '<span class="label label-success">菜单</span>';
            }
            if(item.type === 2){
                return '<span class="label label-warning">按钮</span>';
            }
        }},
        {title: '排序号', field: 'orderNum', align: 'center', valign: 'middle', sortable: true, width: '100px'},
        {title: '菜单URL', field: 'url', align: 'center', valign: 'middle', sortable: true, width: '160px'},
        {title: '授权标识', field: 'perms', align: 'center', valign: 'middle', sortable: true}]
    return columns;
};

function getMenuId () {
    var selected = $('#menuTable').bootstrapTreeTable('getSelections');
    if (selected.length == 0) {
        alert("请选择一条记录");
        return;
    } else {
        return selected[0].id;
    }
}


$(function () {
    var columns = Menu.initColumn();
    var table = new TreeTable(Menu.id, "/sys/menu/list", columns);
    table.setExpandColumn(2);
    table.setIdField("menuId");
    table.setCodeField("menuId");
    table.setParentCodeField("parentId");
    table.setExpandAll(false);
    table.init();
    Menu.table = table;
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
    }
};

//单选按钮切换显示
$('input:radio[name="menuType"]').change(function(){
    if($(this).val() == "0"){
        $("#type_0or1").removeClass("hide");
        $("#type_1").addClass("hide");
        $("#type_1or2").addClass("hide");
    }
    if($(this).val() == "1"){
        $("#type_0or1").addClass("hide");
        $("#type_1").removeClass("hide");
        $("#type_1or2").removeClass("hide");
    }
    if($(this).val() == "2"){
        $("#type_0or1").addClass("hide");
        $("#type_1").addClass("hide");
        $("#type_1or2").removeClass("hide");
    }
});

//刷新菜单页面
function reload() {
    $("#menuDiv").removeClass("hide");
    $("#editMenuDiv").addClass("hide");
    Menu.table.refresh();
}

//新增菜单跳转页面
function addView(){
    $("#editHeaderDiv").text("新增");
    $("#editMenuDiv").removeClass("hide");
    $("#menuDiv").addClass("hide");
    $("#menuId").val(null);
    // 初始化checkbox，使之全部未选中
    $("input[name='menuType']").each(function(){
        $(this).prop("checked",false);
    });
    $("#name").val(null);
    $("#url").val(null);
    $("#perms").val(null);
    $("#icon").val(null);
    $("#parentName").val(null);
    $("#parentId").val("0");
    $("#type").val(1);
    $("#orderNum").val(0);
    //默认选中目录 start
    $("input:radio[value='0']").prop('checked',true);
    $("#type_0or1").removeClass("hide");
    $("#type_1").addClass("hide");
    $("#type_1or2").addClass("hide");
    //默认选中目录 end
    getMenu();
}


//修改菜单跳转页面
function updateView() {
    var menuId = getMenuId();
    if(menuId == null){
        return ;
    }
    $("#editHeaderDiv").text("修改");
    $.get("/sys/menu/info/"+menuId, function(r){
        $("#editMenuDiv").removeClass("hide");
        $("#menuDiv").addClass("hide");
        $("#menuId").val(r.menu.menuId);
        if(r.menu.type == "0"){
            $("input:radio[value='0']").prop('checked',true);//设置value=0的项目为当前选中项
            $("#type_0or1").removeClass("hide");
            $("#type_1").addClass("hide");
            $("#type_1or2").addClass("hide");
        }
        if(r.menu.type == "1"){
            $("input:radio[value='1']").prop('checked',true);
            $("#type_0or1").addClass("hide");
            $("#type_1").removeClass("hide");
            $("#type_1or2").removeClass("hide");
        }
        if(r.menu.type == "2"){
            $("input:radio[value='2']").prop('checked',true);
            $("#type_0or1").addClass("hide");
            $("#type_1").addClass("hide");
            $("#type_1or2").removeClass("hide");
        }
        $("#name").val(r.menu.name);
        $("#parentId").val(r.menu.parentId);
        $("#parentName").val(r.menu.parentName);
        $("#url").val(r.menu.url);
        $("#perms").val(r.menu.perms);
        $("#orderNum").val(r.menu.orderNum);
        $("#icon").val(r.menu.icon);
        getMenu();
    });
}

//新增、修改菜单
function saveOrUpdate() {
    var menuId = $("#menuId").val();
    var menuType = $("input[name='menuType']:checked").val();
    var menuName = $("#name").val();
    var parentId = $("#parentId").val();
    var parentName = $("#parentName").val();
    var menuUrl = $("#url").val();
    var perms = $("#perms").val();
    var orderNum = $("#orderNum").val();
    var icon = $("#icon").val();
    var url = (menuId == null || menuId == "") ? "/sys/menu/save" : "/sys/menu/update";
    $.ajax({
        type: "POST",
        url: url,
        contentType: "application/json",
        data: JSON.stringify({
            menuId: menuId,
            type: menuType,
            name: menuName,
            parentId: parentId,
            parentName: parentName,
            url: menuUrl,
            perms: perms,
            orderNum: orderNum,
            icon: icon
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

//删除菜单
function del() {
    var menuId = getMenuId();
    if(menuId == null){
        return ;
    }
    confirm('确定要删除选中的记录？', function(){
        $.ajax({
            type: "POST",
            url: "/sys/menu/delete",
            data: "menuId=" + menuId,
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

//获取菜单
function getMenu(menuId){
    var parentId = $("#parentId").val();
    //加载菜单树
    $.get("/sys/menu/select", function(r){
        ztree = $.fn.zTree.init($("#menuTree"), setting, r.menuList);
        var node = ztree.getNodeByParam("menuId", parentId);
        ztree.selectNode(node);

        $("#parentName").val(node.name);
    })
}

function menuTree(){
    layer.open({
        type: 1,
        offset: '50px',
        skin: 'layui-layer-molv',
        title: "选择菜单",
        area: ['300px', '450px'],
        shade: 0,
        shadeClose: false,
        content: jQuery("#menuLayer"),
        btn: ['确定', '取消'],
        btn1: function (index) {
            var node = ztree.getSelectedNodes();
            //选择上级菜单
            $("#parentId").val(node[0].menuId);
            $("#parentName").val(node[0].name);

            layer.close(index);
        }
    });
}