<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8"/>
    <title>简易群发办理会员页面</title>
    <#include "header.ftl"/>
</head>
<body>
<div class="charge_form">
    <form class="layui-form" action="#" method="post">
        <div class="layui-form-item">
            <label class="layui-form-label">用户名</label>
            <div class="layui-input-block">
                <span id="userName_area" style="line-height: 38px;"></span>
            </div>
        </div><div class="layui-form-item">
            <label class="layui-form-label">资费</label>
            <div class="layui-input-block">
                <span style="line-height: 38px;">10元/月</span>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">卡号</label>
            <div class="layui-input-block">
                <input type="text" id="cardNo" name="cardNo" required  lay-verify="required" autocomplete="off" placeholder="复制购买好的卡号到此即可" class="layui-input input_width300">
                <div style="margin-top: 10px;"><a href="#" style="color: #009688;">-->点击此处购买官方卡号</a></div>
            </div>
        </div>
    </form>
    <div class="button_area">
        <button id="submitCharge" type="button" class="layui-btn layui-btn">确 定</button>
        <#--        <button type="button" class="layui-btn layui-btn-primary layui-btn">取消</button>-->
    </div>
</div>
<script src="../static/layui/layui.js"></script>
<script>
    (function () {
        let ginfo = JSON.parse(localStorage.getItem("ginfo"))
        if(!ginfo){
            closeThis()
        }
        let userName = ginfo.userName
        document.getElementById("userName_area").innerText = userName
    })()

    let uuid = ""
    let loadingLayer

    layui.use("layer", function () {
        loadingLayer = layui.layer
    })

    $("#submitCharge").click(function () {
        let cardNo = $("#cardNo").val()
        if(!cardNo){
            error("卡号不能为空!")
            return
        }

        let loading = loadingLayer.load(2, {shade: [0.6, '#ccc']})
        $.ajax({
            url: "${requestPrefix}" + "/charge/" + cardNo,
            type:"Get",
            headers: {"groupT": JSON.parse(localStorage.getItem("ginfo")).groupT},
            contentType: "application/json;charset=utf-8",
            dataType:"json",
            success: async function(data){
                if(data.code === 0){
                    // 更新页面的会员到期时间
                    let ginfo = JSON.parse(localStorage.getItem("ginfo"))
                    ginfo.vipEndTime = data.data.vipEndTime
                    localStorage.setItem("ginfo", JSON.stringify(ginfo))

                    layer.close(loading)
                    success("办理成功!")
                    await sleep(1000)
                    let index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                    parent.layer.close(index); //再执行关闭
                    parent.location = "/index"
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

    function closeThis() {
        let index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
        parent.layer.close(index); //再执行关闭
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
                time: 1500,
                content: msg
            })
        })
    }

    function sleep (time) {
        return new Promise((resolve) => setTimeout(resolve, time));
    }


</script>
</body>
<style lang="scss">
    .charge_form {
        height: 380px;
        padding-top: 38px;
    }
    .input_width300 {
        width: 300px;
    }
    .button_area {
        text-align: center;
        padding-top: 38px;
    }
</style>
</html>