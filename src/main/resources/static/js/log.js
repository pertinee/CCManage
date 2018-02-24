$(function () {
    //初始化日志列表
    $("#jqGrid").jqGrid({
        url: '/sys/log/list',
        datatype: "json",
        colModel: [
            { label: 'id', name: 'id', width: 120, key: true, hidden:true },
            { label: '用户名', name: 'username', width: 50 },
            { label: '用户操作', name: 'operation', width: 70 },
            { label: '请求方法', name: 'method', width: 150 },
            { label: '请求参数', name: 'params', width: 80 },
            { label: 'IP地址', name: 'ip', width: 70 },
            { label: '创建时间', name: 'createDate',index: "create_date", width: 90 }
        ],
        viewrecords: true,
        height: 340,
        rowNum: 10,
        rowList : [10,30,50],
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

    //jqGrid自适应
    $("#jqGrid").setGridHeight($(window).height() - 130);
});

//查询跳转
function query() {
    reload();
}

//查询
function reload() {
    var qKey = $("#qKey").val();
    var page = $("#jqGrid").jqGrid('getGridParam','page');
    $("#jqGrid").jqGrid('setGridParam',{
        postData:{'key': qKey},
        page:page
    }).trigger("reloadGrid");
}