<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8"/>
    <title>简易群发工具</title>
    <#include "header.ftl"/>
</head>
<body>
<div>
<div class="index">
    <div class="index_head">
        <div>
            <img class="logo_area" src="../static/images/logo.png">
        </div>
        <div>
            <div class="user_info_area">
                <div id="userName_area"></div>
                <div id="time_area"></div>
            </div>
        </div>
    </div>
    <hr>
    <div class="nav_button">
        <button id="login_action" type="button" class="layui-btn layui-btn-sm">登录</button>
        <button id="register_action" type="button" class="layui-btn layui-btn-primary layui-btn-sm">注册</button>
        <button id="charge_action" type="button" class="layui-btn layui-btn-sm layui-btn-danger">办理会员</button>
    </div>
    <div class="simple_desc">
        <div class="product_logo">
            <img src="../static/images/icon.png">
        </div>
        <div class="product_s_desc">
            <div>
                <div>
                    <div>名称：</div>
                    <div>简易QQ群发工具v1.0</div>
                </div>
                <div>
                    <div>资费：</div>
                    <div>18元/月</div>
                </div>
                <div>
                    <div>描述：</div>
                    <div>
                        本工具支持两种方式的QQ群发：<br>
                        1.针对某一个QQ群的所有成员进行群发<br>
                        2.针对所有QQ群进行群发
                    </div>
                </div>
                <div>
                    <div>客服：</div>
                    <div>
                        qq:2395287915<br>
                        email:chockwa888@gmail.com
                    </div>
                </div>
            </div>
        </div>
    </div>
    <hr>
    <div class="detail_desc">
        <h3>详情描述</h3>
        <hr>
        本工具支持两种方式的群发(可发送图片)，1.对指定QQ群内的群成员进行群发消息。2.对QQ所有群进行群发消息。如下图：
        <img class="detail_desc_img" src="../static/images/groupsend1.png">
    </div>
    <hr>
    <div>
        <button id="downloadUseBook" type="button" class="layui-btn layui-btn-sm">下载使用图文教程</button>
        <button type="button" class="layui-btn layui-btn-sm">下载使用视频教程</button>
        <button type="button" class="layui-btn layui-btn-sm layui-btn-danger">下载工具</button>
    </div>
    <hr>
</div>
    <#include "footer.ftl"/>
</div>

<script src="../static/layui/layui.js"></script>
<script>
    init()
    function init() {
        let ginfo = JSON.parse(localStorage.getItem("ginfo"))
        // console.log(ginfo)
        if(!ginfo){
            return
        }
        let userName = ginfo.userName
        let vipEndTime = ginfo.vipEndTime
        document.getElementById("time_area").innerText = "会员到期时间：" + (vipEndTime != null ? formartDate(vipEndTime) : "非会员")
        document.getElementById("userName_area").innerText = userName
    }

    let loadingLayer
    layui.use("layer", function () {
        loadingLayer = layui.layer
    })

    $("#register_action").click(function() {
        layui.use('layer', function(){
            var layer = layui.layer;
            layer.open({
                type: 2,
                title:"注册",
                area:["500px","480px"],
                shadeClose: true,
                content: ["regP", "no"],
                yes:function(){
                }
            })
        });
    })

    $("#login_action").click(function() {
        layui.use('layer', function(){
            var layer = layui.layer;
            layer.open({
                type: 2,
                title:"登录",
                area:["500px","430px"],
                shadeClose: true,
                content: ["loginP", "no"],
                yes:function(){
                }
            })
        });
    })

    $("#charge_action").click(function() {
        let ginfo = localStorage.getItem("ginfo")
        if(!ginfo){
            error("请先进行登录!")
            return
        }
        layui.use('layer', function(){
            var layer = layui.layer;
            layer.open({
                type: 2,
                title:"办理会员",
                area:["500px","380px"],
                shadeClose: true,
                content: ["chargeP", "no"],
                yes:function(){
                }
            })
        });
    })

    $("#downloadUseBook").click(function () {
        checkLogin(() => {window.open("/downloadUB")})
    })

    function checkLogin(callback, params) {
        let loading = loadingLayer.load(2, {shade: [0.6, '#ccc']})
        let ginfo = localStorage.getItem("ginfo")
        console.log(ginfo)
        $.ajax({
            url: "${requestPrefix}" + "/checkL",
            type:"Get",
            headers: {"groupT": ginfo==null?"":JSON.parse(ginfo).groupT},
            contentType: "application/json;charset=utf-8",
            dataType:"json",
            success: async function(data){
                if(data.code === 0){
                    layer.close(loading)
                    if(callback){
                        callback(params)
                    }
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

    function formartDate (param) {
        let date = new Date(param);
        let Y = date.getFullYear() + '-';
        let M = date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) + '-' : date.getMonth() + 1 + '-';
        let D = date.getDate() < 10 ? '0' + date.getDate() + ' ' : date.getDate() + ' ';
        let h = date.getHours() < 10 ? '0' + date.getHours() + ':' : date.getHours() + ':';
        let m = date.getMinutes() < 10 ? '0' + date.getMinutes() + ':' : date.getMinutes() + ':';
        let s = date.getSeconds() < 10 ? '0' + date.getSeconds() : date.getSeconds();
        return Y + M + D + h + m + s;
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
</script>
</body>
</html>
<style lang="scss">
    .index {
        width: 800px;
        margin: 0 auto;
    }
    .index_head {
        display: flex;
        justify-content: space-between;
    }
    .logo_area {
        width: 238px;
    }
    .nav_button {
        display: flex;
        justify-content: flex-end;
    }
    .simple_desc {
        margin-top: 40px;
        display: flex;
        justify-content: flex-start;
    }
    body > div > div > div.simple_desc > div.product_s_desc > div > div {
        display: flex;
    }
    .product_logo {
        padding-left: 50px;
    }
    .product_s_desc {
        padding-left: 50px;
        line-height: 30px;
    }
    .detail_desc {
    }
    .detail_desc_img {
        padding-top: 13px;
    }
    .user_info_area {
        padding-top: 20px;
        text-align: right;
        line-height: 23px;
    }
</style>