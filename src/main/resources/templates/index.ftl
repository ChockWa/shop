<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8"/>
    <title>推广页面</title>
    <#include "header.ftl"/>
</head>

<body>
<div>
<div class="index">
    <div>
        <img class="logo_area" src="../static/images/logo.png">
    </div>
    <hr>
    <div class="nav_button">
        <button type="button" class="layui-btn layui-btn-sm">登录</button>
        <button id="register_action" type="button" class="layui-btn layui-btn-primary layui-btn-sm">注册</button>
        <button type="button" class="layui-btn layui-btn-sm layui-btn-danger">办理会员</button>
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
        <button type="button" class="layui-btn layui-btn-sm">查看使用图文教程</button>
        <button type="button" class="layui-btn layui-btn-sm">下载使用视频教程</button>
        <button type="button" class="layui-btn layui-btn-sm layui-btn-danger">下载工具</button>
    </div>
    <hr>
</div>
    <#include "footer.ftl"/>
</div>

<script src="../static/layui/layui.js"></script>
<script>
$("#register_action").click(function() {
    layui.use('layer', function(){
        var layer = layui.layer;
        layer.open({
            type: 2,
            title:"注册",
            area:['500px',],
            btn: ['确定', '取消'],
            content: "regP",
            yes:function(){
            }
        })
    });
})
</script>
</body>
</html>
<style lang="scss">
    .index {
        width: 800px;
        margin: 0 auto;
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
</style>