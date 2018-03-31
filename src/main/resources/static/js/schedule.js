/**
 * Created by luchunzhou on 2018/1/21.
 */
$(function () {
    $("#jqGrid").jqGrid({
        url: '/sys/schedule/list',
        datatype: "json",
        colModel: [
            { label: '任务ID', name: 'jobId', width: 60, key: true },
            { label: 'bean名称', name: 'beanName', width: 100 },
            { label: '方法名称', name: 'methodName', width: 100 },
            { label: '参数', name: 'params', width: 100 },
            { label: 'cron表达式 ', name: 'cronExpression', width: 100 },
            { label: '备注 ', name: 'remark', width: 100 },
            { label: '状态', name: 'status', width: 60, formatter: function(value, options, row){
                return value === 0 ?
                    '<span class="label label-success">正常</span>' :
                    '<span class="label label-danger">暂停</span>';
            }}
        ],
        viewrecords: true,
        height: 385,
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

//重置
function reset() {
    $("#jobId").val("");
    $("#beanName").val("");
    $("#methodName").val("");
    $("#params").val("");
    $("#cronExpression").val("");
    $("#remark").val("");
    $("#status").val("");
}

//刷新
function reload() {
    //重置
    reset();
    $("#logDiv").removeClass("hide");
    $("#editLogDiv").addClass("hide");
    var qBeanName = $("#qBeanName").val();
    var page = $("#jqGrid").jqGrid('getGridParam','page');
    $("#jqGrid").jqGrid('setGridParam',{
        postData:{'beanName': qBeanName},
        page:page
    }).trigger("reloadGrid");
}

//查询
function query() {
    reload();
}

//添加显示
function addView(){
    //重置
    reset();
    $("#editHeaderDiv").text("新增");
    $("#editLogDiv").removeClass("hide");
    $("#logDiv").addClass("hide");
}

//修改显示
function updateView() {
    //重置
    reset();
    var jobId = getSelectedRow();
    if(jobId == null){
        return ;
    }
    $.get("/sys/schedule/info/"+jobId, function(r){
        $("#editHeaderDiv").text("修改");
        $("#editLogDiv").removeClass("hide");
        $("#logDiv").addClass("hide");
        $("#jobId").val(jobId);
        $("#beanName").val(r.schedule.beanName);
        $("#methodName").val(r.schedule.methodName);
        $("#params").val(r.schedule.params);
        $("#cronExpression").val(r.schedule.cronExpression);
        $("#remark").val(r.schedule.remark);
        $("#status").val(r.schedule.status);
    });
}

//新增或修改
function saveOrUpdate() {
    var jobId = $("#jobId").val();
    var beanName = $("#beanName").val();
    var methodName = $("#methodName").val();
    var params = $("#params").val();
    var cronExpression = $("#cronExpression").val();
    var remark = $("#remark").val();
    var status = $("#status").val();
    var url = (jobId == null || jobId == "") ? "/sys/schedule/save" : "/sys/schedule/update";
    $.ajax({
        type: "POST",
        url: url,
        contentType: "application/json",
        data: JSON.stringify({
            jobId: jobId,
            beanName: beanName,
            methodName: methodName,
            params: params,
            cronExpression: cronExpression,
            remark: remark,
            status: status
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

//删除
function del() {
    var jobIds = getSelectedRows();
    if(jobIds == null){
        return ;
    }
    confirm('确定要删除选中的记录？', function(){
        $.ajax({
            type: "POST",
            url: "/sys/schedule/delete",
            contentType: "application/json",
            data: JSON.stringify(jobIds),
            success: function(r){
                if(r.code == 0){
                    alert('操作成功', function(index){
                        reload();
                    });
                }else{
                    alert(r.msg);
                }
            }
        });
    });
}

//暂停
function pause() {
    var jobIds = getSelectedRows();
    if(jobIds == null){
        return ;
    }
    confirm('确定要暂停选中的记录？', function(){
        $.ajax({
            type: "POST",
            url: "/sys/schedule/pause",
            contentType: "application/json",
            data: JSON.stringify(jobIds),
            success: function(r){
                if(r.code == 0){
                    alert('操作成功', function(index){
                        reload();
                    });
                }else{
                    alert(r.msg);
                }
            }
        });
    });
}

//恢复
function resume() {
    var jobIds = getSelectedRows();
    if(jobIds == null){
        return ;
    }
    confirm('确定要恢复选中的记录？', function(){
        $.ajax({
            type: "POST",
            url: "/sys/schedule/resume",
            contentType: "application/json",
            data: JSON.stringify(jobIds),
            success: function(r){
                if(r.code == 0){
                    alert('操作成功', function(index){
                        reload();
                    });
                }else{
                    alert(r.msg);
                }
            }
        });
    });
}

//立即执行
function runOnce() {
    var jobIds = getSelectedRows();
    if(jobIds == null){
        return ;
    }
    confirm('确定要立即执行选中的记录？', function(){
        $.ajax({
            type: "POST",
            url: "/sys/schedule/run",
            contentType: "application/json",
            data: JSON.stringify(jobIds),
            success: function(r){
                if(r.code == 0){
                    alert('操作成功', function(index){
                        reload();
                    });
                }else{
                    alert(r.msg);
                }
            }
        });
    });
}