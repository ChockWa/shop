<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8"/>
    <title>简易群发登录页面</title>
    <#include "header.ftl"/>
</head>
<body>
<div class="register_form">
    <form class="layui-form" action="#" method="post">
        <div class="layui-form-item">
            <label class="layui-form-label">用户名</label>
            <div class="layui-input-block">
                <input type="text" id="userName" name="userName" required  lay-verify="required" autocomplete="off" class="layui-input input_width300">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">密码</label>
            <div class="layui-input-block">
                <input type="password" id="password" name="password" required  lay-verify="required" autocomplete="off" class="layui-input input_width300">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">验证码</label>
            <div class="layui-input-block" style="display: flex">
                <input type="text" id="verifyCode" name="verifyCode" required lay-verify="required" autocomplete="off" class="layui-input input_width130">
                <img id="verifyCodeImg" class="verify_area" src="">
                <a href="javascript:void(0);" onclick="genVerifyCode()" class="change_verify">换一张</a>
            </div>
        </div>
    </form>
    <div class="button_area">
        <button id="submitReg" type="button" lay-filter="submitReg" class="layui-btn layui-btn" lay-submit>登 录</button>
        <#--        <button type="button" class="layui-btn layui-btn-primary layui-btn">取消</button>-->
    </div>
</div>
<script src="../static/layui/layui.js"></script>
<script>
    let uuid = ""
    let layer

    layui.use("layer", function () {
        layer = layui.layer
    })

    genVerifyCode()

    $("#submitReg").click(function () {
        let userName = $("#userName").val()
        let password = $("#password").val()
        let verifyCode = $("#verifyCode").val()
        if(!valid(userName, password, verifyCode)){
            return
        }
        let loading = layer.load(2, {shade: [0.6, '#ccc']})
        $.ajax({
            url:"/login",
            data:JSON.stringify({'userName':userName, "password":password,"verifyCode":verifyCode,"uuid":uuid}),
            type:"Post",
            contentType: "application/json;charset=utf-8",
            dataType:"json",
            success: async function(data){
                console.log(data);
                if(data.code === 0){
                    layer.close(loading)
                    success("登录成功!")
                    await sleep(1000)
                    let token = data.data.token
                    let userName = data.data.userName
                    let vipEndTime = data.data.vipEndTime
                    localStorage.setItem("ginfo", JSON.stringify({"groupT": token, "userName":userName, "vipEndTime": vipEndTime}))
                    let index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                    parent.layer.close(index); //再执行关闭
                    parent.location = "/"
                }else{
                    layer.close(loading)
                    error(data.msg)
                }
            },
            error:function(data){
                layer.close(loading)
                error(data.msg);
            }
        });
    })

    function valid (userName, password, verifyCode){
        if(!userName){
            error("用户名不能为空！")
            return false
        }
        if(!password || password.length < 6){
            error("密码不能少于6位！")
            return false
        }
        if(!verifyCode){
            error("验证码不能为空！")
            return false
        }
        return true
    }

    function error(msg){
        layui.use(["layer"], function () {
            let layer = layui.layer;
            layer.open({
                type: 0,
                title:"错误",
                btn: ["确定"],
                content: msg
            })
        })
    }

    function success(msg){
        layui.use(["layer"], function () {
            let layer = layui.layer;
            layer.open({
                type: 0,
                title:"成功",
                time: 1000,
                content: msg
            })
        })
    }

    function sleep (time) {
        return new Promise((resolve) => setTimeout(resolve, time));
    }


    function genVerifyCode() {
        uuid = guid()
        $("#verifyCodeImg").attr("src","/auth/genVerifyCode?uuid="+uuid)
    }
    function guid() {
        return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
            var r = Math.random() * 16 | 0,
                v = c == 'x' ? r : (r & 0x3 | 0x8);
            return v.toString(16);
        });
    }
</script>
</body>
<style lang="scss">
    .register_form {
        height: 430px;
        padding-top: 38px;
    }
    .input_width300 {
        width: 300px;
    }
    .input_width130 {
        width: 130px;
    }
    .button_area {
        text-align: center;
        padding-top: 38px;
    }
    .verify_area {
        width: 80px;
        height: 38px;
        padding-left: 8px;
    }
    .change_verify {
        padding-top: 10px;
        padding-left: 8px;
        color: #009688;
        cursor: pointer;
    }
</style>
</html>