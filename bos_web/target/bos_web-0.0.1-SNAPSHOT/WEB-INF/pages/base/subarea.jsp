<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理分区</title>
<!-- 导入jquery核心类库 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<!-- 导入easyui类库 -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/ext/portal.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/default.css">	
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/ext/jquery.portal.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/ext/jquery.cookie.js"></script>
<script
	src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath }/js/jquery.ocupload-1.1.2.js"
	type="text/javascript"></script>
	
<script
	src="${pageContext.request.contextPath }/js/highcharts/highcharts.js"
	type="text/javascript"></script>
<script type="text/javascript">
	function doAdd(){
		$('#addSubareaWindow').window("open");
	}
	
	function doEdit(){
		alert("修改...");
	}
	
	function doDelete(){
		alert("删除...");
	}
	
	function doSearch(){
		$('#searchWindow').window("open");
	}
	
	function doExport(){
		location.href="${pageContext.request.contextPath}/subareaAction_exportXls.action"
	}
	
	function doImport(){
		alert("导入");
	}
	
	//工具栏
	var toolbar = [ {
		id : 'button-search',	
		text : '查询',
		iconCls : 'icon-search',
		handler : doSearch
	}, {
		id : 'button-add',
		text : '增加',
		iconCls : 'icon-add',
		handler : doAdd
	}, {
		id : 'button-edit',	
		text : '修改',
		iconCls : 'icon-edit',
		handler : doEdit
	},{
		id : 'button-delete',
		text : '删除',
		iconCls : 'icon-cancel',
		handler : doDelete
	},{
		id : 'button-import',
		text : '导入',
		iconCls : 'icon-redo',
		handler : doImport
	},{
		id : 'button-export',
		text : '导出',
		iconCls : 'icon-undo',
		handler : doExport
	},{
		id : 'button-subareaRegion',
		text : '区域分区分布图',
		iconCls : 'icon-tip',
		handler : doSubareaPie
	}];
	
	// 定义列
	var columns = [ [ {
		field : 'id',
		checkbox : true,
	}, {
		field : 'showid',
		title : '分区编号',
		width : 120,
		align : 'center',
		formatter : function(data,row ,index){
			return row.id;
		}
	},{
		field : 'province',
		title : '省',
		width : 120,
		align : 'center',
		formatter : function(data,row ,index){
			return row.region.province;
		}
	}, {
		field : 'city',
		title : '市',
		width : 120,
		align : 'center',
		formatter : function(data,row ,index){
			return row.region.city;
		}
	}, {
		field : 'district',
		title : '区',
		width : 120,
		align : 'center',
		formatter : function(data,row ,index){
			return row.region.district;
		}
	}, {
		field : 'addresskey',
		title : '关键字',
		width : 120,
		align : 'center'
	}, {
		field : 'startnum',
		title : '起始号',
		width : 100,
		align : 'center'
	}, {
		field : 'endnum',
		title : '终止号',
		width : 100,
		align : 'center'
	} , {
		field : 'single',
		title : '单双号',
		width : 100,
		align : 'center'
	} , {
		field : 'position',
		title : '位置',
		width : 200,
		align : 'center'
	} ] ];
	
	$(function(){
		// 先将body隐藏，再显示，不会出现页面刷新效果
		$("body").css({visibility:"visible"});
		
		// 收派标准数据表格
		$('#grid').datagrid( {
			iconCls : 'icon-forward',
			fit : true,
			border : true,
			rownumbers : true,
			striped : true,
			pageList: [30,50,100],
			pagination : true,
			toolbar : toolbar,
			url : "${pageContext.request.contextPath}/subareaAction_pageQuery.action",
			idField : 'id',
			columns : columns,
			onDblClickRow : doDblClickRow
		});
		
		// 添加、修改分区
		$('#addSubareaWindow').window({
	        title: '添加修改分区',
	        width: 600,
	        modal: true,
	        shadow: true,
	        closed: true,
	        height: 400,
	        resizable:false
	    });
		
		// 查询分区
		$('#searchWindow').window({
	        title: '查询分区',
	        width: 400,
	        modal: true,
	        shadow: true,
	        closed: true,
	        height: 400,
	        resizable:false
	    });
		
		$('#subareaPie').window({
	        title: '区域分区分布图',
	        width: 400,
	        modal: true,
	        shadow: true,
	        closed: true,
	        height: 400,
	        resizable:false
	    });
		
		// 修改分区
		$('#editSubareaWindow').window({
	        title: '修改分区',
	        width: 400,
	        modal: true,
	        shadow: true,
	        closed: true,
	        height: 400,
	        resizable:false
	    });
		

		//给导入按钮绑定事件
		$('#button-import').upload({
			action:'${pageContext.request.contextPath}/subareaAction_importXls.action',
			name:'subareaFile',
			onComplete:function(data){
				if("1" == data){
					$.messager.alert('提示信息','导入成功！','info');
				} else {
					$.messager.alert('提示信息','导入失败！','error');
				}
			}
		});
		
	});

	function doDblClickRow(rowIndex, rowData){
		//rowIndex-行索引值，表示是第几行
		//rowData-双击行的行对象
		//打开一个编辑窗口
		$('#editSubareaWindow').window('open');
		//将数据回显到form中
		$('#editSubareaWindow').form("load", rowData);
	}
	

	function doSubareaPie(){
		//打开一个窗口
		$('#subareaPie').window('open');
		//使用ajax查询区域分区的分部数据，返回json数据
		var url = "${pageContext.request.contextPath}/subareaAction_findGroupSubareas.action";
		$.post(url,{},function(data){
			//初始化饼图
			$('#container').highcharts({
				chart:{
					type:'pie'
				},
				series:[
				        {
				        	data:data
				        }
				        ]
				 
			});
		},'json');
	}
</script>	
</head>
<body class="easyui-layout" style="visibility:hidden;">
	<div region="center" border="false">
    	<table id="grid"></table>
	</div>
	<!-- 添加 修改分区 -->
	<div class="easyui-window" title="分区添加修改" id="addSubareaWindow" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
		<div style="height:31px;overflow:hidden;" split="false" border="false" >
			<div class="datagrid-toolbar">
				<a id="save" icon="icon-save" href="#" class="easyui-linkbutton" plain="true" >保存</a>
				<script type="text/javascript">
				$(function(){
					$('#save').click(function(){
						var v = $('#addSubareaForm').form('validate');
						if(v){
							$('#addSubareaForm').submit();
						}
					});
				});
				</script>
			</div>
		</div>
		
		<div style="overflow:auto;padding:5px;" border="false">
			<form id="addSubareaForm" method="post"
				action="${pageContext.request.contextPath }/subareaAction_add.action">
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">分区信息</td>
					</tr>
					<tr>
						<td>分区编码</td>
						<td><input type="text" name="id" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>选择区域</td>
						<td>
							<input class="easyui-combobox" name="region.id"  
    							data-options="mode:'remote',valueField:'id',textField:'name',url:'${pageContext.request.contextPath }/regionAction_listajax.action'" />  
						</td>
					</tr>
					
					
					<tr>
						<td>关键字</td>
						<td><input type="text" name="addresskey" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>起始号</td>
						<td><input type="text" name="startnum" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>终止号</td>
						<td><input type="text" name="endnum" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>单双号</td>
						<td>
							<select class="easyui-combobox" name="single" style="width:150px;">  
							    <option value="0">单双号</option>  
							    <option value="1">单号</option>  
							    <option value="2">双号</option>  
							</select> 
						</td>
					</tr>
					<tr>
						<td>位置信息</td>
						<td><input type="text" name="position" class="easyui-validatebox" required="true" style="width:250px;"/></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	
	<!-- 编辑分区 -->
	<div class="easyui-window" title="分区编辑修改" id="editSubareaWindow" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
		<div style="height:31px;overflow:hidden;" split="false" border="false" >
			<div class="datagrid-toolbar">
				<a id="save2" icon="icon-save" href="#" class="easyui-linkbutton" plain="true" >保存</a>
				<script type="text/javascript">
				$(function(){
					$('#save2').click(function(){
						var v = $('#editSubareaForm').form('validate');
						if(v){
							$('#editSubareaForm').submit();
						}
					});
				});
				</script>
			</div>
		</div>
		
		<div style="overflow:auto;padding:5px;" border="false">
			<form id="editSubareaForm" method="post"
				action="${pageContext.request.contextPath }/subareaAction_edit.action">
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">分区信息</td>
					</tr>
					<tr>
						<td>分区编码</td>
						<td><input type="text" name="id" class="easyui-validatebox" required="true"/></td>
					</tr>
					
					<tr>
						<td>关键字</td>
						<td><input type="text" name="addresskey" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>省</td>
						<td><input type="text" name="rowData.region.province" class="easyui-validatebox" required="true"/></td>
					</tr>
					
					<tr>
						<td>起始号</td>
						<td><input type="text" name="startnum" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>终止号</td>
						<td><input type="text" name="endnum" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>单双号</td>
						<td>
							<select class="easyui-combobox" name="single" style="width:150px;">  
							    <option value="0">单双号</option>  
							    <option value="1">单号</option>  
							    <option value="2">双号</option>  
							</select> 
						</td>
					</tr>
					<tr>
						<td>位置信息</td>
						<td><input type="text" name="position" class="easyui-validatebox" required="true" style="width:250px;"/></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	
	<!-- 查询分区 -->
	<div class="easyui-window" title="查询分区窗口" id="searchWindow" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
		<div style="overflow:auto;padding:5px;" border="false">
			<form id="searchForm">
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">查询条件</td>
					</tr>
					<tr>
						<td>省</td>
						<td><input type="text" name="region.province"/></td>
					</tr>
					<tr>
						<td>市</td>
						<td><input type="text" name="region.city"/></td>
					</tr>
					<tr>
						<td>区（县）</td>
						<td><input type="text" name="region.district"/></td>
					</tr>
					<tr>
						<td>关键字</td>
						<td><input type="text" name="addresskey"/></td>
					</tr>
					<tr>
						<td colspan="2">
						<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
						<script type="text/javascript">
						$(function(){
							$("#btn").click(function(){
								//1.将查询条件表单中的数据转换成json对象
								//给表单绑定一个方法，可以通过该方法将表单数据转换成json对象
								$.fn.serializeJson=function(){  
						            var serializeObj={};  
						            var array=this.serializeArray();
						            $(array).each(function(){  
						                if(serializeObj[this.name]){  
						                    if($.isArray(serializeObj[this.name])){  
						                        serializeObj[this.name].push(this.value);  
						                    }else{  
						                        serializeObj[this.name]=[serializeObj[this.name],this.value];  
						                    }  
						                }else{  
						                    serializeObj[this.name]=this.value;   
						                }  
						            });  
						            return serializeObj;  
						        }; 
								//2.使用datagrid的load方法实现带条件的分页查询
								//调用表单的刚刚绑定的方法，获取json对象
								var data = $('#searchForm').serializeJson();
								$("#grid").datagrid('load',data);
								//load方法执行的操作：
								//1）发送url属性指定的请求
								//2）发送data的json对象中的数据
								//   data中的数据如:
								//{
								//	'region.province':'北京',
								//	'region.city':'北京',
								//	'region.district':'北京',
								//	 addresskey:'东城'
								//}
								//3.关闭查询窗口
								$('#searchWindow').window("close");
							});
						});
						</script>
						
						
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<!-- 展示区域分区分布图窗口 -->
	<div class="easyui-window" title="分区添加修改" id="subareaPie" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
		<div id="container" style="min-width: 310px; height: 400px; max-width: 600px; margin: 0 auto"></div>
	</div>
</body>
</html>