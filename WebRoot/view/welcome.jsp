<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("basePath",basePath);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>欢迎您</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="${basePath}jquery-easyui-1.3.3/themes/black/easyui.css">
	<script type="text/javascript" src="${basePath}js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="${basePath}jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
	<script type="text/javascript">
		function addTab(title,url){
			$('#tabs').tabs('add',{
				title   :title,
				selected:true,
				closable:true,
				content :"<iframe style='height:100%;width:100%' scrolling='auto' frameborder='0' src='"+ url +"'></iframe>"
			});
		};
	</script>
  </head>
  
  <body class="easyui-layout">   
    <div data-options="region:'north',title:'铜雀台整形美容医院客户关系管理系统',split:true" style="height:159px;">
    	
    	<img src="${basePath}imgs/title.png" style="float:left;">
    	<h2 style="float:right;margin-right: 25px;">欢迎您，${loginUser.userName}&nbsp;&nbsp;&nbsp;<a  href="logout.do?methodName=logout">退出系统</a></h2>
    </div>   
    <div data-options="region:'west',title:'系统菜单',split:true" style="width:208px;">
    	<div id="aa" class="easyui-accordion" style="width: 200px;" data-options="fit:true">
			<c:forEach items="${menulist}" var="m2">
				<c:if test="${m2.level == 2}">
					<div title="${m2.mname }" data-options="iconCls:'icon-save'" style="overflow: auto; padding: 10px;">
						<ul id="tt">
							<c:forEach items="${menulist}" var="m3">
								<c:if test="${m3.parentid==m2.mid}">
									<li class="m3">
										<a href="javascript:void(0);" onclick="addTab('${m3.mname}','${basePath}${m3.url}');"">${m3.mname}</a>
									</li>
								</c:if>
							</c:forEach>
						</ul>
					</div>
				</c:if>
			</c:forEach>
			<div title="系统维护" data-options="iconCls:'icon-reload',selected:true" style="padding: 10px;">
			</div>
		</div>
	</div>
	<div data-options="region:'center'" style="padding:3px;background-color: #eee;">
		<div id="tabs" class="easyui-tabs" data-options="fit:true">
			<div title="欢迎">
			</div>
		</div>
	</div>   
  </body>  
</html>
