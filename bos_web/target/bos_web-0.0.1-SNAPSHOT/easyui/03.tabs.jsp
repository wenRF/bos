<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>EasyUI-layout-布局管理器</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
</head>
<body class="easyui-layout">
	<div title="管理系统" data-options="region:'north'" style="height:100px">北部区域</div>
	<div title="功能菜单" data-options="region:'west'" style="width:200px">
			<!-- 折叠 -->
			<div class="easyui-accordion" fit="true">   <!-- fit自适应属性 自动填满 父容器 -->
				<div title="王朝">1</div>
				<div title="马汉">1</div>
				<div title="张龙">1</div>
				<div title="赵虎">1</div>
			</div>	
	</div>
	<div data-options="region:'center'">

			<!-- 折叠 -->
			<div class="easyui-tabs" fit="true" >   <!-- fit自适应属性 自动填满 父容器 -->
				<div title="王朝"  data-options="closable:true,iconCls:'icon-help',closable:true">1</div>
				<div title="马汉" data-options="closable:true">1</div>
				<div title="张龙" data-options="closable:true">1</div>
				<div title="赵虎" data-options="closable:true">1</div>
			</div>	

	</div>
	<div data-options="region:'east'" style="width:100px">东部区域</div>
	<div data-options="region:'south'" style="height:100px">南部区域</div>
</body>
</html>