$(function () {
    $("#jqGrid").jqGrid({
        url: '/sys/config/list',
        datatype: "json",
        colModel: [
            { label: 'ID', name: 'id', width: 80, key: true, hidden: true },
            { label: '参数名', name: 'key', width: 60 },
            { label: '参数值', name: 'value', width: 100 },
            { label: '备注', name: 'remark', width: 80 },
            { label: '状态', name: 'status', width: 80, formatter: function(value, options, row){
                return value === 0 ?
                    '<span class="label label-danger">禁用</span>' :
                    '<span class="label label-success">正常</span>';
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
    $("#id").val("");
    $("#key").val("");
    $("#value").val("");
    $("#remark").val("");
    $("input[name='status']").prop("checked",false);
}

//刷新用户页面
function reload() {
    //重置
    reset();
    $("#configDiv").removeClass("hide");
    $("#editConfigDiv").addClass("hide");
    var qKey = $("#qKey").val();
    var page = $("#jqGrid").jqGrid('getGridParam','page');
    $("#jqGrid").jqGrid('setGridParam',{
        postData:{'key': qKey},
        page:page
    }).trigger("reloadGrid");
}

function query() {
    reload();
}

function addView(){
    //重置
    reset();
    $("#editHeaderDiv").text("新增");
    $("#editConfigDiv").removeClass("hide");
    $("#configDiv").addClass("hide");
}

//修改按钮跳转页面
function updateView() {
    //重置
    reset();
    $("#editHeaderDiv").text("修改");
    var id = getSelectedRow();
    if(id == null){
        return ;
    }
    $("#editConfigDiv").removeClass("hide");
    $("#configDiv").addClass("hide");

    $.get("/sys/config/info/"+id, function(r){
        $("#id").val(id);
        $("#key").val(r.config.key);
        $("#value").val(r.config.value);
        $("#remark").val(r.config.remark);
        if(r.config.status == "1"){
            $("input:radio[value='1']").prop('checked',true);//设置value=1的项目为当前选中项
        }else{
            $("input:radio[value='0']").prop('checked',true);
        }
    });
}

//新增、修改用户
function saveOrUpdate() {
    var id = $("#id").val();
    var key = $("#key").val();
    var value = $("#value").val();
    var remark = $("#remark").val();
    var status = $("input[name='status']:checked").val();

    var url = (id == null || id == "") ? "/sys/config/save" : "/sys/config/update";
    $.ajax({
        type: "POST",
        url: url,
        contentType: "application/json",
        data: JSON.stringify({
            id: id,
            key: key,
            value: value,
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

function del() {
    var ids = getSelectedRows();
    if(ids == null){
        return ;
    }
    confirm('确定要删除选中的记录？', function(){
        $.ajax({
            type: "POST",
            url: "/sys/config/delete",
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