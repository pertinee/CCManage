//初始化用户菜单
$(function () {
    menuInit();
});

//初始化用户菜单
function menuInit() {
    $.get("/sys/menu/user?_"+$.now(), function(r){
        //window.permissions = r.permissions;

        var result = "";
        result += "<li class='header'>菜单</li>";
        if(r.menuList.length > 0){
            for(var i = 0 ; i < r.menuList.length ; i ++){
                result += "<li class='treeview'>";
                if(r.menuList[i].type == "0"){
                    result += "<a href='#'>";
                }
                if(r.menuList[i].icon != null || r.menuList[i].icon != ""){
                    result += "<i class='"+r.menuList[i].icon+"'></i>";
                }
                result += "<span>"+r.menuList[i].name+"</span>"
                result += "<i class='fa fa-angle-left pull-right'></i>";
                result += "</a>";
                var childList = r.menuList[i].list;
                if(childList.length > 0){
                    result += "<ul class='treeview-menu'>";
                    for(var j = 0 ; j < childList.length ; j ++){
                        var tmpUrl = childList[j].url;
                        var tmpIcon = childList[j].icon;
                        var tmpName = childList[j].name;
                        result += "<li><a href='javascript:void(0);' onclick=showMenuContent("+"'"+tmpUrl+"'"+","+"'"+tmpName+"'"+")>";
                        result += "<i class='"+tmpIcon+"'></i> "+tmpName+"</a></li>";
                    }
                    result += "</ul>";
                }
                result += "</li>";
            }
            $("#menuList").html(result);
        }else{

        }

    });
}

//iframe自适应
$(window).on('resize', function() {
    var $content = $('.content');
    $content.height($(this).height() - 190);
    $content.find('iframe').each(function() {
        $(this).height($content.height());
    });
}).resize();

//点击菜单，在iframe显示内容
function showMenuContent(menuUrl, desc) {
    $(".content iframe").attr('src',menuUrl);
    $("#targetBread").text(desc);
};

//修改密码
function updatePassword(){
    var password = $("#password").val();
    var newPassword = $("#newPassword").val();
    if("" == password){
        layer.msg('原密码不能为空');
        return;
    }
    if("" == newPassword){
        layer.msg('新密码不能为空');
        return;
    }
    var data = "password="+password+"&newPassword="+newPassword;
    $.ajax({
        type: "POST",
        url: "/sys/user/password",
        data: data,
        dataType: "json",
        success: function(result){
            debugger;
            if(result.code == 0){
                layer.msg('更新成功');
                location.reload();
            }else{
                layer.msg(result.msg);
            }
        }
    });
}

//退出
function logoutConfirm() {
    layer.confirm('确定要退出吗？', {
        btn : [ '确定', '取消' ]//按钮
    }, function(index) {
        layer.close(index);
        //退出
        parent.location.href ='/logout';
    });
}