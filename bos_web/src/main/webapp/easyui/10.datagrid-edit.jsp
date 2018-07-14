<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>EasyUI-datagrid-数据表格编辑功能</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<!-- 配置ztree资源文件 -->
<link rel="stylesheet" href="${pageContext.request.contextPath }/js/ztree/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/ztree/jquery.ztree.all-3.5.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.ocupload-1.1.2.js"></script>
</head>
<body>
	<table id="grid"></table>
	<script type="text/javascript">
		$(function(){
			var rowIndex;
			$('#grid').datagrid({
				url:'${pageContext.request.contextPath}/json/staff.json',
				columns:[[
				          {field:'id',title:'编号',checkbox:true},
				          {field:'name',title:'姓名',editor:{
				        	  type:'validatebox',//指定编辑框的类型
				        	  options:{
				        		 required:true 
				        	  }//定制编辑框的内容
				          }},
				          {field:'telephone',title:'电话',editor:{
				        	  type:'validatebox',//指定编辑框的类型
				        	  options:{
				        		  required:true
				        	  }//定制编辑框的内容
				          }}
				          ]],
				toolbar:[
				         	{text:'新增',iconCls:'icon-add',handler:function(){
				         		if(undefined == rowIndex){
				         			//没有在编辑状态的行，可以插入一行
					         		//新增一行
					         		$('#grid').datagrid('insertRow',{
					         			index:0,//设置从第几行插入，从0开始
					         			row: {
					         				
					         			}
					         		});
					         		//开启编辑状态：
					         		//参数1：开启编辑状态的方法
					         		//参数2：行索引值
					         		$('#grid').datagrid('beginEdit', 0);
					         		rowIndex = 0;
				         		}
				         	}},
				         	{text:'删除',iconCls:'icon-remove',handler:function(){
				         		//获取选中的数据
				         		var rows = $('#grid').datagrid('getSelections');
				         		if(rows.length < 1){
				         			//位选中，提示
				         			$.messager.alert('提示信息','请选择要删除的数据！','warning');
				         		} else {
				         			//循环删除数据
				         			for(var i=0; i<rows.length; i++){
				         				//获取到行号
				         				var index = $('#grid').datagrid('getRowIndex', rows[i]);
				         				$('#grid').datagrid('deleteRow', index);
				         			}
				         		}
				         	}},
				         	{text:'修改',iconCls:'icon-edit',handler:function(){
				         		//获取选中的数据
				         		var rows = $('#grid').datagrid('getSelections');
				         		if(rows.length < 1){
				         			//位选中，提示
				         			$.messager.alert('提示信息','请选择要修改的数据！','warning');
				         		} else if(rows.length > 1){
				         			$.messager.alert('提示信息','只能选择一条编辑数据!','warning');
				         		} else {
				         			//获取到行号
				         			var index = $('#grid').datagrid('getRowIndex', rows[0]);
				         			$('#grid').datagrid('beginEdit', index);
				         		}
				         	}},
				         	{text:'保存',iconCls:'icon-save',handler:function(){
				         		//获取选中的数据
				         		var rows = $('#grid').datagrid('getSelections');
				         		if(rows.length < 1){
				         			//位选中，提示
				         			$.messager.alert('提示信息','请选择要保存的数据！','warning');
				         		} else if(rows.length > 1){
				         			$.messager.alert('提示信息','只能选择一条保存数据!','warning');
				         		} else {
				         			//获取到行号
				         			var index = $('#grid').datagrid('getRowIndex', rows[0]);
				         			$('#grid').datagrid('endEdit', index);
				         		}
				         	}},
				         	{text:'查询',iconCls:'icon-search'}
				         ],
				pagination:true,
				onAfterEdit:function(rowIndex, rowData, changes){
					//rowIndex:行号
					//rowData：整条行数据对象
					//changes：修改后的数据键值对
					/*
						{
							name:'张三',
							telephone:'13838389438'
						}
					*/
					alert(rowIndex+"-"+rowData.name+"-"+changes.name);
				}
			});
		});
	</script>
</body>
</html>