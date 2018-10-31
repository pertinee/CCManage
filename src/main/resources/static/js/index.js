//初始化用户菜单
$(function () {
    menuInit();
    websocketInit();
    nowTimeInit();
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
    var rePassword = $("#rePassword").val();
    if("" == password){
        layer.msg('原密码不能为空');
        return;
    }
    if("" == newPassword){
        layer.msg('新密码不能为空');
        return;
    }
    if("" == rePassword){
        layer.msg('确认密码不能为空');
        return;
    }
    if(newPassword != rePassword){
        layer.msg('新密码与确认密码不一致');
        return;
    }
    var data = "password="+password+"&newPassword="+newPassword;
    $.ajax({
        type: "POST",
        url: "/sys/user/password",
        data: data,
        dataType: "json",
        success: function(result){
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

//当前时间显示
function nowTimeInit() {
    setInterval("nowTime.innerHTML=new Date().toLocaleString()+' 星期'+'日一二三四五六'.charAt(new Date().getDay());",1000);
}

//在线人数统计、消息群发
function websocketInit() {
    var socket;
    if(typeof(WebSocket) == "undefined") {
        console.log("您的浏览器不支持WebSocket");
    }else{
        console.log("您的浏览器支持WebSocket");
        //实现化WebSocket对象，指定要连接的服务器地址与端口  建立连接
        socket = new WebSocket("ws://localhost:8080/websocket");
        //打开事件
        socket.onopen = function() {
            console.log("Socket 已打开");
        };
        //获得消息事件
        socket.onmessage = function(msg) {
            console.log(msg.data);
            //发现消息进入    调后台获取
            // 在线人数
            var onlineUserNum = "<i class='fa fa-user-circle' title='在线人数'></i> &nbsp;" + msg.data;
            $("#onlineUserNum").html(onlineUserNum);
            //getCallsingList();
        };
        //关闭事件
        socket.onclose = function() {
            console.log("Socket已关闭");
        };
        //发生了错误事件
        socket.onerror = function() {
            alert("Socket发生了错误");
        };
        $(window).unload(function(){
            socket.close();
        });

        // $("#btnSend").click(function() {
        //     socket.send("这是来自客户端的消息" + location.href + new Date());
        // });
        //
        // $("#btnClose").click(function() {
        //     socket.close();
        // });
    }
}