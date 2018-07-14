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

<!-- ztree -->
<link rel="stylesheet" href="${pageContext.request.contextPath }/js/ztree/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/ztree/jquery.ztree.all-3.5.js"></script>
</head>
<body class="easyui-layout">
	<!-- <div title="管理系统" data-options="region:'north'" style="height:100px">北部区域</div> -->
	<div title="功能菜单" data-options="region:'west'" style="width:200px">
			<!-- 折叠 -->
			<div  class="easyui-accordion" fit="true"   >   <!-- fit自适应属性 自动填满 父容器 -->
				<div title="王朝" >
					<a onclick="doAdd()" class="easyui-linkbutton">动态选项卡</a>
					<script type="text/javascript">
					function doAdd(){
						//动态添加选项卡
						$('#tt').tabs('add',{
							title:'动态选项卡',
							content:'这是动态添加的选项卡',
							closable:true
						});
					}
					</script>
				
				
				</div>
				<div title="马汉">
						<!-- 基于标准json构建ztree树 -->
						<ul id="ztree1" class="ztree"></ul>     <!-- 容器 -->
							<script type="text/javascript">
							$(function(){
								//定义setting全局变量 标准的jason可以为空
								var setting={};
								//树节点
								var nodes=[
								          {name:"系统管理"},
								          {name:"用户管理",children:[
								                                 {name:"用户添加"},
								                                 {name:"用户修改"},
								                                 {name:"用户删除"},
								                                 
								                                 	]},
								          {name:"权限管理"}
								          ];
								
								//初始化
							 $.fn.zTree.init($("#ztree1"), setting, nodes);
							});
							</script>		
				</div>
				<div title="张龙">
					<!-- 简单的Jason构建ztree -->
					<ul id="ztree2" class="ztree"></ul>
					<script type="text/javascript">
					$(function(){
						//定义setting全局变量，
						var setting = {
								data:{
									simpleData:{
										enable:true//开启简单json数据功能，默认是关闭
									}
								}
						};
						//定义树节点
						var nodes = [
						             {id:'1', pId:'0', name:"系统管理"},
						             {id:'2', pId:'0', name:"用户管理"},
						             {id:'21', pId:'2', name:"用户添加"},
						             {id:'22', pId:'2', name:"用户修改"},
						             {id:'23', pId:'2', name:"用户删除"},
						             {id:'3', pId:'0', name:"权限管理"}
						             ];
						//初始化树
						$.fn.zTree.init($('#ztree2'), setting, nodes);
					});
					</script>
				</div>
				<div title="赵虎">
					<!-- ajax动态构建ztree-->
					<ul id="ztree3" class="ztree"></ul>
					<script type="text/javascript">
					$(function(){
						//定义setting全局变量，
						var setting = {
								data:{
									simpleData:{
										enable:true//开启简单json数据功能，默认是关闭
									}
								},
								callback:{
									onClick:function(event, treeId, treeNode){
										var page = treeNode.page;
										if(undefined != page){
											//有page属性，子节点
											//判断选项卡是否存在
											var b = $('#tt').tabs('exists',treeNode.name);
											if(b){
												//已存在
												$('#tt').tabs('select',treeNode.name);
											} else {
												//不存在
												//动态添加选项卡
												$('#tt').tabs('add',{
													title:treeNode.name,
												  //<iframe/> 自动发请求
													content:'<iframe src="${pageContext.request.contextPath}/'+page+'" height="100%" width="100%" frameborder="0"></iframe>',
													closable:true
												});
											}
										}
									}
								}
						};
						//发送ajax请求加载树节点
						var url = "${pageContext.request.contextPath}/json/menu.json";
						$.post(url,{},function(data){
							//初始化树
							$.fn.zTree.init($('#ztree3'), setting, data);
						},"json");
					});
					</script>
				</div>
			</div>	
	</div>
	<div data-options="region:'center'">

			<!-- 折叠 -->
			<div class="easyui-tabs" fit="true"  id="tt" >   <!-- fit自适应属性 自动填满 父容器 -->
				<div title="王朝"  data-options="closable:true,iconCls:'icon-help',closable:true">1</div>
				<div title="马汉" data-options="closable:true">1</div>
				<div title="张龙" data-options="closable:true">1</div>
				<div title="赵虎" data-options="closable:true">1</div>
			</div>	

	</div>
	<!-- <div data-options="region:'east'" style="width:100px">东部区域</div>
	<div data-options="region:'south'" style="height:100px">南部区域</div> -->
</body>
</html>