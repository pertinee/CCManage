//初始化验证码
$(document).ready(function(){
    refreshCode();
    websocketInit();
});

//获取验证码
function refreshCode(){
    var kaptcha = "/captcha?t=" + $.now();
    $("#code").attr('src',kaptcha);
}

//登录
function login() {
    var username = $("#username").val();
    var password = $("#password").val();
    var captcha = $("#captcha").val();
    var data = "username="+username+"&password="+password+"&captcha="+captcha;
    $.ajax({
        type: "POST",
        url: "/sys/login",
        data: data,
        dataType: "json",
        success: function(result){
            if(result.code == 0){
                //登录成功,跳转
                parent.location.href ='/sys';
            }else{
                layer.msg(result.msg);
                refreshCode();
            }
        }
    });
}

// 绑定键盘按下事件
$(document).keypress(function(e) {
    // 回车键事件，登录
    if(e.which == 13) {
        login();
    }
});


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
            //getCallingList();
        };
        //关闭事件
        socket.onclose = function() {
            console.log("Socket已关闭");
        };
        //发生了错误事件
        socket.onerror = function() {
            alert("Socket发生了错误");
        }
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