<%--
  Created by IntelliJ IDEA.
  User: zhangyunpeng
  Date: 2021/8/2
  Time: 10:02 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>新增日志行为</title>
    <%@include file="../../basepath.jsp"%>
    <link href="layui/css/layui.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="css/common.css?v=<%=Math.random()%>">
</head>
<body>
<span class="layui-breadcrumb">
<%--  <a href="">首页</a>--%>
  <a href="log-map/list">日志行为管理</a>
  <a >日志行为新增</a>
</span>
<form class="layui-form" action="log-map/add" lay-filter="form" method="post">
    <div class="layui-form-item">
        <label class="layui-form-label">url地址</label>
        <div class="layui-input-block">
            <input type="text" name="url"
                   required
                   lay-verify="required"
                   lay-reqText="url不可以为空"
                   lay-verType="tips"
                   placeholder="请输入url地址"
                   autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">行为名称</label>
        <div class="layui-input-block">
            <input type="text" name="action"
                   required
                   lay-verify="required"
                   lay-reqText="行为名称不可以为空"
                   lay-verType="tips"
                   placeholder="请输入行为名称" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="layui/layui.js"></script>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript">
    layui.use('form',function(){
        var form = layui.form
        form.on('submit(form)',function(res){
            console.log(res)
            // return false

        })
    })
</script>
</body>
</html>
