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
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"></script>
</head>
<body class="easyui-layout">
<script type="text/javascript">
	//show
	/* $.messager.show({
		title:'消息提示框',
		msg:'欢迎登陆',
		timeout:'5000',
		showType:'slide',
	}); */
	
	//alert
	//$.messager.alert("提示框","你好","info");
	
	//confirm
	
	$.messager.confirm('确认信息','确定要删除？',function(r){ 
			if (r){ 
				alert('ok'); 
				} 
			});
	
	window.setTimeout(function(){
		$.messager.show({
			title:'消息提示框',
			msg:'欢迎登陆',
			timeout:'5000',
			showType:'slide',
		});
	},2000);		//延迟执行操作

</script>
</body>
</html>