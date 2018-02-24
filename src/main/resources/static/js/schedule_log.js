/**
 * Created by luchunzhou on 2018/1/21.
 */
$(function () {
    $("#jqGrid").jqGrid({
        url: '/sys/scheduleLog/list',
        datatype: "json",
        colModel: [
            { label: '日志ID', name: 'logId', width: 50, key: true },
            { label: '任务ID', name: 'jobId', width: 50},
            { label: 'bean名称', name: 'beanName', width: 60 },
            { label: '方法名称', name: 'methodName', width: 60 },
            { label: '参数', name: 'params', width: 60 },
            { label: '状态', name: 'status', width: 50, formatter: function(value, options, row){
                return value === 0 ?
                    '<span class="label label-success">成功</span>' :
                    '<span class="label label-danger" style="cursor:pointer;" onclick="showError('+row.logId+')">失败</span>';
            }},
            { label: '耗时(单位：毫秒)', name: 'times', width: 70 },
            { label: '执行时间', name: 'createTime', width: 80 }
        ],
        viewrecords: true,
        height: 385,
        rowNum: 10,
        rowList : [10,30,50,100,200],
        rownumbers: true,
        rownumWidth: 25,
        autowidth:true,
        multiselect: false,
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
});

//查询
function query() {
    var qJobId = $("#qJobId").val();
    $("#jqGrid").jqGrid('setGridParam',{
        postData:{'jobId': qJobId},
        page:1
    }).trigger("reloadGrid");
}

function showError(logId) {
    $.get("/sys/scheduleLog/info/"+logId, function(r){
        parent.layer.open({
            title:'失败信息',
            closeBtn:0,
            content: r.log.error
        });
    });
}

//返回
function back() {
    history.go(-1);
}
