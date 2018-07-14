<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%-- <%@ taglib prefix="shiro"  uri="http://shiro.apache.org/tags"%> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
<script type="text/javascript">


	function doAdd() {
		//alert("增加...");
		$('#addStaffWindow').window("open");
	}

	function doView() {
		//alert("增加...");
		$('#searchWindow').window("open");
	}
	//作废
	function doDelete() {
		//获取选中条数
		var rows = $("#grid").datagrid("getSelections");
		if (rows.length <= 0) {
			//如果未选中，弹出提示
			$.messager.alert('提示信息', '请您选择要删除的数据！', 'warning');
		} else {
			//如果选中，获取选中的id，发送更新请求
			var staffIDArr = new Array();
			for(var i=0; i<rows.length; i++){ 
				staffIDArr.push(rows[i].id); //循环将选中对象的条数id放到数组中
			}
			var ids = staffIDArr.join(','); //将多个id用“，”拼接成字符串
			//发送请求
			location.href="${pageContext.request.contextPath}/staffAction_delete.action?ids="+ids;
		}
	}

	function doRestore() {
			//获取选中条数
			var rows = $("#grid").datagrid("getSelections");
			if (rows.length <= 0) {
				//如果未选中，弹出提示
				$.messager.alert('提示信息', '请您选择要删除的数据！', 'warning');
			} else {
				//如果选中，获取选中的id，发送更新请求
				var staffIDArr = new Array();
				for(var i=0; i<rows.length; i++){
					staffIDArr.push(rows[i].id);
				}
				var ids = staffIDArr.join(',');
				//发送请求
				location.href="${pageContext.request.contextPath}/staffAction_restore.action?ids="+ids;
			}
	}
	//工具栏
	var toolbar = [ {
		id : 'button-view',
		text : '查询',
		iconCls : 'icon-search',
		handler : doView
	}, 
	
	
	//权限设置	
/* 	<shiro:hasPermission name="abc"> */
	{
		id : 'button-add',
		text : '增加',
		iconCls : 'icon-add',
		handler : doAdd
	},
	/* </shiro:hasPermission> */
	
	
	{
		id : 'button-delete',
		text : '作废',
		iconCls : 'icon-cancel',
		handler : doDelete
	}, {
		id : 'button-save',
		text : '还原',
		iconCls : 'icon-save',
		handler : doRestore
	} ];
	// 定义列
	var columns = [ [ {
		field : 'id',
		checkbox : true,
	}, {
		field : 'name',
		title : '姓名',
		width : 120,
		align : 'center'
	}, {
		field : 'telephone',
		title : '手机号',
		width : 120,
		align : 'center'
	}, {
		field : 'haspda',
		title : '是否有PDA',
		width : 120,
		align : 'center',
		formatter : function(data, row, index) {
			if (data == "1") {
				return "有";
			} else {
				return "无";
			}
		}
	}, {
		field : 'deltag',
		title : '是否作废',
		width : 120,
		align : 'center',
		formatter : function(data, row, index) {
			if (data == "0") {
				return "正常使用"
			} else {
				return "已作废";
			}
		}
	}, {
		field : 'standard',
		title : '取派标准',
		width : 120,
		align : 'center'
	}, {
		field : 'station',
		title : '所谓单位',
		width : 200,
		align : 'center'
	} ] ];

	$(function() {
		// 先将body隐藏，再显示，不会出现页面刷新效果
		$("body").css({
			visibility : "visible"
		});

		// 取派员信息表格
		$('#grid').datagrid({
			iconCls : 'icon-forward',
			fit : true,
			border : true,
			rownumbers : true,
			striped : true,
			pageList : [ 3, 5, 10 ],
			pagination : true,
			toolbar : toolbar,
			url : "${pageContext.request.contextPath}/staffAction_pageQuery.action",
			idField : 'id',
			columns : columns,
			onDblClickRow : doDblClickRow
		});

		// 添加取派员窗口
		$('#addStaffWindow').window({
			title : '添加取派员',
			width : 400,
			modal : true,//设置是否是模态窗口,true打开窗口后，其他不能编辑，false，打开后可以编辑
			shadow : true,
			closed : true,//设置窗口默认是打开还是关闭，true-默认关闭，false-默认打开，默认值是false
			height : 400,
			resizable : false
		});
		
		// 编辑取派员窗口
		$('#editStaffWindow').window({
			title : '编辑取派员',
			width : 400,
			modal : true,//设置是否是模态窗口,true打开窗口后，其他不能编辑，false，打开后可以编辑
			shadow : true,
			closed : true,//设置窗口默认是打开还是关闭，true-默认关闭，false-默认打开，默认值是false
			height : 400,
			resizable : false
		});
		
		//查询取配员
		$('#searchWindow').window({
	        title: '查询取配员',
	        width: 400,
	        modal: true,
	        shadow: true,
	        closed: true,
	        height: 400,
	        resizable:false
	    });
		
		

	});

	function doDblClickRow(rowIndex, rowData) {
		//rowIndex-行索引值，表示是第几行
		//rowData-双击行的行对象
		//打开一个编辑窗口
		$('#editStaffWindow').window('open');
		//将数据回显到form中
		$('#editStaffForm').form("load", rowData);
	}
</script>
</head>
<body class="easyui-layout" style="visibility: hidden;">
	<div region="center" border="false">
		<table id="grid"></table>
	</div>
	<div class="easyui-window" title="对收派员进行添加或者修改" id="addStaffWindow"
		collapsible="false" minimizable="false" maximizable="false"
		style="top: 20px; left: 200px">
		<div region="north" style="height: 31px; overflow: hidden;"
			split="false" border="false">
			<div class="datagrid-toolbar">
				<a id="save" icon="icon-save" href="#" class="easyui-linkbutton"
					plain="true">保存</a>
				<script type="text/javascript">
				$(function(){
					$('#save').click(function(){
						var v = $('#addStaffForm').form('validate');
						if(v){
							//校验通过，提交表单
							$('#addStaffForm').submit();
						}
					});
				});
				</script>
			</div>
		</div>

		<div region="center" style="overflow: auto; padding: 5px;"
			border="false">
			<form id="addStaffForm" method="post"
				action="${pageContext.request.contextPath }/staffAction_add.action">
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">收派员信息</td>
					</tr>
					<!-- TODO 这里完善收派员添加 table -->
					<tr>
						<td>姓名</td>
						<td><input type="text" name="name" class="easyui-validatebox"
							required="true" /></td>
					</tr>
					<tr>
						<td>手机</td>
						<td><input type="text" name="telephone"
							class="easyui-validatebox" required="true" data-options="validType:'telephone'"/> <script
								type="text/javascript">
								$(function() {
									$.extend($.fn.validatebox.defaults.rules, {
										telephone : {
											validator : function(value, param) {
												var reg = /^1[3,5,7,8,9][0-9]{9}$/;
												return reg.test(value);
											},
											message : '手机号输入错误！'
										}
									});
								});
							</script></td>
					</tr>
					<tr>
						<td>单位</td>
						<td><input type="text" name="station"
							class="easyui-validatebox" required="true" /></td>
					</tr>
					<tr>
						<td>邮箱</td>
						<td><input type="text" name="email"
							class="easyui-validatebox" required="true" /></td>
					</tr>
					<tr>
						<td colspan="2"><input type="checkbox" name="haspda"
							value="1" /> 是否有PDA</td>
					</tr>
					<tr>
						<td>取派标准</td>
						<td><input type="text" name="standard"
							class="easyui-validatebox" required="true" /></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	
	
	
	
	
	
		<div class="easyui-window" title="查询取派员窗口" id="searchWindow" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
		<div style="overflow:auto;padding:5px;" border="false">
			<form id="searchForm">
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">查询条件</td>
					</tr>
					<tr>
						<td>姓名</td>
						<td><input type="text" name="name"/></td>
					</tr>
					<tr>
						<td>手机</td>
						<td><input type="text" name="telephone"
							class="easyui-validatebox" required="true" data-options="validType:'telephone'"/> <script
								type="text/javascript">
								/* $(function() {
									$.extend($.fn.validatebox.defaults.rules, {
										telephone : {
											validator : function(value, param) {
												var reg = /^1[3,5,7,8,9][0-9]{9}$/;
												return reg.test(value);
											},
											message : '手机号输入错误！'
										}
									});
								}); */
							</script></td>
					</tr> 
					<tr>
						<td>单位</td>
						<td><input type="text" name="station"
							 /></td>
					</tr>
					<!-- <tr>
						<td>邮箱</td>
						<td><input type="text" name="email"
							 /></td>
					</tr>
					<tr>
						<td colspan="2"><input type="checkbox" name="haspda"
							value="1" /> 是否有PDA</td>
					</tr> -->
					<tr>
						<td>取派标准</td>
						<td><input type="text" name="standard"
							 /></td>
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
	
	
	
	
	
	
	
	
	<div class="easyui-window" title="对收派员进行添加或者修改" id="editStaffWindow"
		collapsible="false" minimizable="false" maximizable="false"
		style="top: 20px; left: 200px">
		<div region="north" style="height: 31px; overflow: hidden;"
			split="false" border="false">
			<div class="datagrid-toolbar">
				<a id="edit" icon="icon-save" href="#" class="easyui-linkbutton"
					plain="true">保存</a>
				<script type="text/javascript">
				$(function(){
					$('#edit').click(function(){
						var v = $('#editStaffForm').form('validate');
						if(v){
							//校验通过，提交表单
							$('#editStaffForm').submit();
						}
					});
				});
				</script>
			</div>
		</div>

		<div region="center" style="overflow: auto; padding: 5px;"
			border="false">
			<form id="editStaffForm" method="post"
				action="${pageContext.request.contextPath }/staffAction_edit.action">
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">收派员信息</td>
					</tr>
					<!-- TODO 这里完善收派员添加 table -->
					<tr>
						<td>姓名
							<!-- 添加主键的隐藏域 -->
							<input type="hidden" name="id"/>
						</td>
						<td><input type="text" name="name" class="easyui-validatebox"
							required="true" /></td>
					</tr>
					<tr>
						<td>手机</td>
						<td><input type="text" name="telephone"
							class="easyui-validatebox" required="true" data-options="validType:'telephone'"/> <script
								type="text/javascript">
								$(function() {
									$.extend($.fn.validatebox.defaults.rules, {
										telephone : {
											validator : function(value, param) {
												var reg = /^1[3,5,7,8,9][0-9]{9}$/;
												return reg.test(value);
											},
											message : '手机号输入错误！'
										}
									});
								});
							</script></td>
					</tr>
					<tr>
						<td>单位</td>
						<td><input type="text" name="station"
							class="easyui-validatebox" required="true" /></td>
					</tr>
					<tr>
						<td>邮箱</td>
						<td><input type="text" name="email"
							class="easyui-validatebox" required="true" /></td>
					</tr>
					<tr>
						<td colspan="2"><input type="checkbox" name="haspda"
							value="1" /> 是否有PDA</td>
					</tr>
					<tr>
						<td>取派标准</td>
						<td><input type="text" name="standard"
							class="easyui-validatebox" required="true" /></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>
