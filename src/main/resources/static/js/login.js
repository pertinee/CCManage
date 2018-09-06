//初始化验证码
$(document).ready(function(){
    refreshCode();
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