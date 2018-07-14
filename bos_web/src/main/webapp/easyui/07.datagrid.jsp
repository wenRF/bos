<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>EasyUI-menubutton-菜单按钮</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<!-- 配置ztree资源文件 -->
<link rel="stylesheet" href="${pageContext.request.contextPath }/js/ztree/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/ztree/jquery.ztree.all-3.5.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"></script>
</head>
<body>
	<H3>如何将html的table渲染成datagrid</H3>
	<table class="easyui-datagrid">
		<thead>
			<tr>
				<th data-options="field:'id'">编号</th>
				<th data-options="field:'name'">姓名</th>
				<th data-options="field:'telephone'">电话</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>1</td>
				<td>老马容</td>
				<td>13838384384</td>
			</tr>
			<tr>
				<td>2</td>
				<td>老宋吉吉</td>
				<td>13838384385</td>
			</tr>
		</tbody>
	</table>
	<h3>基于ajax动态加载datagrid数据</h3>
	<table class="easyui-datagrid" data-options="url:'${pageContext.request.contextPath}/json/staff.json'">
		<thead>
			<tr>
				<th data-options="field:'id'">编号</th>
				<th data-options="field:'name'">姓名</th>
				<th data-options="field:'telephone'">电话</th>
			</tr>
		</thead>
	</table>
	<h3>基于datagrid的API动态构建一个datagrid表格</h3>
	<table id="grid"></table>
	<script type="text/javascript">
	$(function(){
		$('#grid').datagrid({
			url:"${pageContext.request.contextPath}/json/staff.json",//发送加载数据的请求
			columns:[[
			          {title:'编号',field:'id',checkbox:true},//checkbox设置是否显示成复选框，true-复选框，false-不是复选框，默认值是false
			          {title:'姓名',field:'name'},
			          {title:'电话',field:'telephone'}
			          ]],//设置表格的列名，是一个二维数组，title指定列名，field指定列的取值
			toolbar:[
			         {text:'查询',iconCls:'icon-search',handler:function(){
			        	 alert(1);
			         }},
			         {text:'增加',iconCls:'icon-add'},
			         {text:'修改',iconCls:'icon-edit'},
			         {text:'还原',iconCls:'icon-remove'}
			         ],//设置工具条，一维数组
			 pagination:true,//开启分页查询功能，默认是false-关闭
			 pageList:[30,50,100]//设置每页显示条数选项的变量
		});
	});
	</script>
</body>
</html>