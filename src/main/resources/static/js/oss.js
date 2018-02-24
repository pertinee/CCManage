$(function () {
    $("#jqGrid").jqGrid({
        url: '/sys/oss/list',
        datatype: "json",
        colModel: [
            { label: 'id', name: 'id', width: 80, key: true, hidden: true },
            { label: 'URL地址', name: 'url', width: 160, formatter:'link' },
            { label: '创建时间', name: 'createDate', width: 40 }
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

    new AjaxUpload('#upload', {
        action: '/sys/oss/upload',
        name: 'file',
        autoSubmit:true,
        responseType:"json",
        onSubmit:function(file, extension){
            var tp = $("input[name='type']:checked").val();
            if(tp == null || tp == ""){
                alert("云存储配置未配置");
                return false;
            }
            if (!(extension && /^(jpg|jpeg|png|gif)$/.test(extension.toLowerCase()))){
                alert('只支持jpg、png、gif格式的图片！');
                return false;
            }
        },
        onComplete : function(file, r){
            if(r.code == 0){
                alert(r.url);
                reload();
            }else{
                alert(r.msg);
            }
        }
    });

    getConfig();
});

//单选按钮切换显示
$('input:radio[name="type"]').change(function(){
    if($(this).val() == "1"){
        $("#type_1").removeClass("hide");
        $("#type_2").addClass("hide");
        $("#type_3").addClass("hide");
    }
    if($(this).val() == "2"){
        $("#type_1").addClass("hide");
        $("#type_2").removeClass("hide");
        $("#type_3").addClass("hide");
    }
    if($(this).val() == "3"){
        $("#type_1").addClass("hide");
        $("#type_2").addClass("hide");
        $("#type_3").removeClass("hide");
    }
});


//刷新页面
function reload() {
    $("#ossDiv").removeClass("hide");
    $("#editOssDiv").addClass("hide");
    var page = $("#jqGrid").jqGrid('getGridParam','page');
    $("#jqGrid").jqGrid('setGridParam',{
        page:page
    }).trigger("reloadGrid");
}

//查询
function query() {
    reload();
}


function getConfig() {
    $.getJSON("/sys/oss/config", function(r){
        if(r.config.type == "1"){
            $("#type_1").removeClass("hide");
            $("#type_2").addClass("hide");
            $("#type_3").addClass("hide");
            $("input:radio[value='1']").prop('checked',true);
            $("input:radio[value='2']").prop('checked',false);
            $("input:radio[value='3']").prop('checked',false);

            $("#qiniuDomain").val(r.config.qiniuDomain);
            $("#qiniuPrefix").val(r.config.qiniuPrefix);
            $("#qiniuAccessKey").val(r.config.qiniuAccessKey);
            $("#qiniuSecretKey").val(r.config.qiniuSecretKey);
            $("#qiniuBucketName").val(r.config.qiniuBucketName);
        }else if(r.config.type == "2"){
            $("#type_1").addClass("hide");
            $("#type_2").removeClass("hide");
            $("#type_3").addClass("hide");

            $("input:radio[value='2']").prop('checked',true);
            $("#aliyunDomain").val(r.config.aliyunDomain);
            $("#aliyunPrefix").val(r.config.aliyunPrefix);
            $("#aliyunEndPoint").val(r.config.aliyunEndPoint);
            $("#aliyunAccessKeyId").val(r.config.aliyunAccessKeyId);
            $("#aliyunAccessKeySecret").val(r.config.aliyunAccessKeySecret);
            $("#aliyunBucketName").val(r.config.aliyunBucketName);
        }else if(r.config.type == "3"){
            $("#type_1").addClass("hide");
            $("#type_2").addClass("hide");
            $("#type_3").removeClass("hide");

            $("input:radio[value='3']").prop('checked',true);
            $("#qcloudDomain").val(r.config.qcloudDomain);
            $("#qcloudPrefix").val(r.config.qcloudPrefix);
            $("#qcloudAppId").val(r.config.qcloudAppId);
            $("#qcloudSecretId").val(r.config.qcloudSecretId);
            $("#qcloudSecretKey").val(r.config.qcloudSecretKey);
            $("#qcloudBucketName").val(r.config.qcloudBucketName);
            $("#qcloudRegion").val(r.config.qcloudRegion);
        }else{
            alert("云存储配置未配置");
            return false;
        }


    });
}

function addConfig(){
    $("#editHeaderDiv").text("云存储配置");
    $("#editOssDiv").removeClass("hide");
    $("#ossDiv").addClass("hide");

    // 初始化checkbox，使之全部未选中
    $("input[name='type']").each(function(){
        $(this).prop("checked",false);
    });
    getConfig();
}


function saveOrUpdate() {
    // 获取每个字段的值
    var type = $("input[name='type']:checked").val();
    var qiniuDomain = $("#qiniuDomain").val();
    var qiniuPrefix = $("#qiniuPrefix").val();
    var qiniuAccessKey = $("#qiniuAccessKey").val();
    var qiniuSecretKey = $("#qiniuSecretKey").val();
    var qiniuBucketName = $("#qiniuBucketName").val();
    var aliyunDomain = $("#aliyunDomain").val();
    var aliyunPrefix = $("#aliyunPrefix").val();
    var aliyunEndPoint = $("#aliyunEndPoint").val();
    var aliyunAccessKeyId = $("#aliyunAccessKeyId").val();
    var aliyunAccessKeySecret = $("#aliyunAccessKeySecret").val();
    var aliyunBucketName = $("#aliyunBucketName").val();
    var qcloudDomain = $("#qcloudDomain").val();
    var qcloudPrefix = $("#qcloudPrefix").val();
    var qcloudAppId = $("#qcloudAppId").val();
    var qcloudSecretId = $("#qcloudSecretId").val();
    var qcloudSecretKey = $("#qcloudSecretKey").val();
    var qcloudBucketName = $("#qcloudBucketName").val();
    var qcloudRegion = $("#qcloudRegion").val();

    var url = "/sys/oss/saveConfig";
    $.ajax({
        type: "POST",
        url: url,
        contentType: "application/json",
        data: JSON.stringify({
            type: type,
            qiniuDomain: qiniuDomain,
            qiniuPrefix: qiniuPrefix,
            qiniuAccessKey: qiniuAccessKey,
            qiniuSecretKey: qiniuSecretKey,
            qiniuBucketName: qiniuBucketName,
            aliyunDomain: aliyunDomain,
            aliyunPrefix: aliyunPrefix,
            aliyunEndPoint: aliyunEndPoint,
            aliyunAccessKeyId: aliyunAccessKeyId,
            aliyunAccessKeySecret: aliyunAccessKeySecret,
            aliyunBucketName: aliyunBucketName,
            qcloudDomain: qcloudDomain,
            qcloudPrefix: qcloudPrefix,
            qcloudAppId: qcloudAppId,
            qcloudSecretId: qcloudSecretId,
            qcloudSecretKey: qcloudSecretKey,
            qcloudBucketName: qcloudBucketName,
            qcloudRegion: qcloudRegion
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
    var ossIds = getSelectedRows();
    if(ossIds == null){
        return ;
    }
    confirm('确定要删除选中的记录？', function(){
        $.ajax({
            type: "POST",
            url: "/sys/oss/delete",
            contentType: "application/json",
            data: JSON.stringify(ossIds),
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
