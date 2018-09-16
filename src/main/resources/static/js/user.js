$(function () {
    //初始化用户列表
    $("#jqGrid").jqGrid({
        url: '/sys/user/list',
        datatype: "json",
        colModel: [
            { label: '用户ID', name: 'userId', index: "user_id", width: 80, key: true, hidden:true },
            { label: '用户名', name: 'username', width: 75 },
            { label: '所属部门', name: 'deptName', width: 75 },
            { label: '邮箱', name: 'email', width: 90 },
            { label: '手机号', name: 'mobile', width: 100 },
            { label: '状态', name: 'status', width: 75, hidden:true},
            { label: '状态', name: 'statusCn', width: 75, formatter: function(value, options, row){
                if(row.status == '1'){
                    return '<span class="label label-success">'+ row.statusCn +'</span>';
                }
                if(row.status == '0'){
                    return '<span class="label label-danger">'+ row.statusCn +'</span>';
                }
            }},
            { label: '创建时间', name: 'createDate', index: "create_date", width: 80}
        ],
        viewrecords: true,
        // height: 340,
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
            idKey: "id",
            pIdKey: "parentId",
            rootPId: -1
        },
        key: {
            url:"nourl"
        }
    }
};
var ztree;

//重置
function reset() {

    $("#userId").val("");
    $("#deptId").val("");
    $("#deptName").val("");
    $("#createUserId").val("");
    $("#username").val("");
    $("#password").val("");
    $("#email").val("");
    $("#mobile").val("");
    // 初始化checkbox，使之全部未选中
    $("input[name='roleIdList']").each(function(){
        $(this).prop("checked",false);
    });
    $("input[name='status']").prop("checked",false);
}

//查询用户
function query() {
    reload();
}

//刷新用户页面
function reload() {
    //重置
    reset();
    $("#userDiv").removeClass("hide");
    $("#editUserDiv").addClass("hide");
    var qUsername = $("#qUsername").val();
    var page = $("#jqGrid").jqGrid('getGridParam','page');
    $("#jqGrid").jqGrid('setGridParam',{
        postData:{'username': qUsername},
        page:page
    }).trigger("reloadGrid");
}

//添加按钮跳转页面
function addView() {
    //重置
    reset();
    $("#editHeaderDiv").text("新增");
    $("#editUserDiv").removeClass("hide");
    $("#userDiv").addClass("hide");
    getDept();
}

//修改按钮跳转页面
function updateView() {
    //重置
    reset();
    $("#editHeaderDiv").text("修改");

    var userId = getSelectedRow();
    if(userId == null){
        return;
    }
    $("#editUserDiv").removeClass("hide");
    $("#userDiv").addClass("hide");

    $.get("/sys/user/info/"+userId, function(r){

        $("#userId").val(userId);
        $("#username").val(r.user.username);
        $("#deptId").val(r.user.deptId);
        $("#deptName").val(r.user.deptName);
        //$("#password").val(r.user.password);
        $("#email").val(r.user.email);
        $("#mobile").val(r.user.mobile);
        if(r.user.status == "1"){
            $("input:radio[value='1']").prop('checked',true);//设置value=1的项目为当前选中项
        }else{
            $("input:radio[value='0']").prop('checked',true);
        }
        $("#createUserId").val(r.user.createUserId);
        $("input[name='roleIdList']").each(function(){
            for(var i = 0 ; i < (r.user.roleIdList).length ; i ++){
                if($(this).val() == (r.user.roleIdList)[i]){
                    $(this).prop("checked",true);
                }
            }
        });
    });
    getDept();
}

//新增、修改用户
function saveOrUpdate() {
    var userId = $("#userId").val();
    var deptId = $("#deptId").val();
    var deptName = $("#deptName").val();
    var username = $("#username").val();
    var password = $("#password").val();
    var email = $("#email").val();
    var mobile = $("#mobile").val();
    var status = $("input[name='status']:checked").val();
    var createUserId = $("#createUserId").val();
    var roleIdList = new Array();
    $("input[name='roleIdList']:checked").each(function(i){
        roleIdList[i] = $(this).val();
    });
    var url = (userId == null || userId == "") ? "/sys/user/save" : "/sys/user/update";
    $.ajax({
        type: "POST",
        url: url,
        contentType: "application/json",
        data: JSON.stringify({
            userId: userId,
            deptId: deptId,
            deptName: deptName,
            username: username,
            password: password,
            email: email,
            mobile: mobile,
            status: status,
            createUserId: createUserId,
            roleIdList: roleIdList
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

//删除用户
function del() {
    var userIds = getSelectedRows();
    if(userIds == null){
        return;
    }
    confirm('确定要删除选中的记录？', function(){
        $.ajax({
            type: "POST",
            url: "/sys/user/delete",
            contentType: "application/json",
            data: JSON.stringify(userIds),
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

function getDept(){
    var deptId = $("#deptId").val();
    //加载部门树
    $.get("/sys/dept/list", function(r){
        ztree = $.fn.zTree.init($("#deptTree"), setting, r);
        var node = ztree.getNodeByParam("id", deptId);
        if(node != null){
            ztree.selectNode(node);

            $("#deptName").val(node.name);
        }
    })
}

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
            $("#deptId").val(node[0].id);
            $("#deptName").val(node[0].name);

            layer.close(index);
        }
    });
}
