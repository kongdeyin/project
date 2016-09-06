<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("basePath",basePath);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="${basePath}jquery-easyui-1.3.3/themes/black/easyui.css">
	<link rel="stylesheet" type="text/css" href="${basePath}jquery-easyui-1.3.3/themes/icon.css">
	<script type="text/javascript" src="${basePath}js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="${basePath}jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${basePath}jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript">
		$(function($){
 			$('#dg').datagrid({    
   		    url:'showUsers.do?methodName=showUsers&pageNo=1&pageSize=10', 
   		    frozenColumns:[[{field:'ahnds',checkbox:true}]], 
   		    rownumbers:true, 
   		    loadMsg:'数据加载中,请稍后……',
   		    striped:true, 
   		    pagination:true,
	    	columns:[[    
		        {field:'uid',title:'编号',width:80,align:'center',hidden:true},    
		        {field:'uNo',title:'员工工号',width:70,align:'center'},    
		        {field:'userName',title:'用户登录名',width:70,align:'center'},  
		        {field:'userPassWord',title:'用户密码',width:70,align:'center'},
		        {field:'realName',title:'真实姓名',width:70,align:'center'},   
		        {field:'phone',title:'手机号',width:80,align:'center'},
		        {field:'email',title:'邮箱',width:140,align:'center'},
		        {field:'QQ',title:'QQ',width:80,align:'center'},
		        {field:'weChatNo',title:'微信',width:90,align:'center'},
		        {field:'emergencyContactPerson',title:'紧急联系人',width:80,align:'center'},
		        {field:'emergencyContactPhone',title:'紧急联系人电话',width:100,align:'center'},
		        {field:'dname',title:'所属部门',width:63,align:'center'},
		        {field:'entryTime',title:'入职时间',width:73,align:'center'},
		        {field:'iseffective',title:'是否有效',width:60,align:'center'}
		    ]],
			toolbar: [{
				text:'添加用户',
				iconCls: 'icon-editadd',
				handler: function(){
					$('#win').window('open');
				}
			},'-',{
				text:'删除用户',
				iconCls: 'icon-delete',
				handler: function(){
					var selec = $('#dg').datagrid('getSelections');
					if(selec.length <= 0){
						alert("没有选中行");
						return;
					}
					//定义一个数组 存放选中的id
					var array = new Array();
					for(var i in selec){
						array.push(selec[i].uid);
					}
					//将数组对象转换成字符串
					var str = array.join(',');
					//ajax向后台提交数据
					$.get('deleteUserByUid.do?methodName=deleteUserByUid',{uid:str},
						function(data){
							$('#dg').datagrid('reload');
						},
					'text')	
				}
			},'-',{
				text:'修改用户',
				iconCls: 'icon-modify',
				handler: function(){alert('修改用户')}
			},'-',{
				text:'刷新',
				iconCls: 'icon-refresh',
				handler: function(data){
					$('#dg').datagrid('reload');
				}
			}]
		});
		
		var pager = $('#dg').datagrid("getPager");
		pager.pagination({
			onSelectPage:function(pageNo,pageSize){
				$('#dg').datagrid('loading');
				$.post("showUsers.do",{
					methodName:'showUsers',
					pageNo:pageNo,
					pageSize:pageSize
				},function(data){
					$("#dg").datagrid("loadData",{
						rows:data.rows,
						total:data.total
					});	
				},"json");
				$('#dg').datagrid('loaded');
			}
		});
 
 		//添加弹出窗口
		$('#win').window({    
	        width:600,    
	        height:400,    
	        modal:true,
	        title:'添加用户' ,
	        closed:true,
	        top:20, 
	    }); 
	    
	    
		//下拉列表动态获取部门名称
		$('.dept').combobox({    
		    url:'showdepartment.do?methodName=showdepartment',    
		    valueField:'did',    
		    textField:'dname'   
		});
		});
		
		//组合查询
		function queryuser(){
			var realName = $('#realname').val();
			var phone = $('#Phone').val();
			var did = $('#dname').combo('getValue');
			var sql = "";
			if(realName!=''){
				sql+=" and realName LIKE '%"+realName+"%'" ;
			}
			 if(phone!=''){
				 sql+=" and phone  LIKE '%"+phone+"%'" ;
			}
			 if(did!='' && did > 0){
				 sql+=" and u.did  LIKE '%"+did+"%'" ;
			}
			 $("#dg").datagrid("load",{
					sql:sql,
				});
		}
		
		//添加用户
	    function addUser(){
			$.post('addUser.do?methodName=addUser',{
				uNo:$("#uNo").val(),
				userName:$("#userName").val(),
				userPassWord:$("#userPassWord").val(),
				realName:$("#realName").val(),
				phone:$("#phone").val(),
				email:$("#email").val(),
				QQ:$("#QQ").val(),
				weChatNo:$("#weChatNo").val(),
				emergencyContactPerson:$("#emergencyContactPerson").val(),
				emergencyContactPhone:$("#emergencyContactPhone").val(),
				did:$("#did").combo('getValue'),
				EntryTime:$("#EntryTime").combo('getValue'),
				iseffective:$("#iseffective").combo('getValue')
			},function(data){
				$('#win').window('close');
				$('dg').gatagrid('reload');
				alert("添加成功");
				$('addUser').form('reset');
			},"text");
		};
		
	</script>
  </head>
  <body>
  	  <div id="select">
  	    	员工姓名： <input id="realname" type="text" />&nbsp;&nbsp;&nbsp;
  	    	手机号：<input id="Phone" type="text">&nbsp;&nbsp;&nbsp;
  	    	部门：<select id="dname" class="dept" class="easyui-combox" style="width:80px"></select>&nbsp;&nbsp;&nbsp;
  	  		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="queryuser();" data-options="iconCls:'icon-search'" style="margin-left: 40px;">搜索</a>
  	  </div>
  	  <table id="dg"> </table>
  	  <div id="win">
  	  	<form  id="addUser" method="post">
	    	<table>
	    		<tr>
	    			<td style="width:130px">员工工号：</td>
	    			<td><input id="uNo" class="easyui-validatebox" data-options="required:true" /></td>
	    		</tr>
	    		<tr>
	    			<td>用户登录名：</td>
	    			<td><input id="userName" name="userName" class="easyui-validatebox" data-options="required:true" /></td>
	    		</tr>
	    		<tr>
	    			<td>用户密码：</td>
	    			<td><input  id="userPassWord" type="password" class="easyui-validatebox" data-options="required:true" /></td>
	    		</tr>
	    		<tr>
	    			<td>真实姓名：</td>
	    			<td><input id="realName" class="easyui-validatebox" data-options="required:false" /></td>
	    		</tr>
	    		<tr>
	    			<td>手机号：</td>
	    			<td><input id="phone" class="easyui-validatebox" data-options="required:false" /></td>
	    		</tr>
	    		<tr>
	    			<td>邮箱：</td>
	    			<td><input id="email" class="easyui-textbox" data-options="validType:'email'" ></td>
	    		</tr>
	    		<tr>
	    			<td>QQ：</td>
	    			<td><input type="text" id="QQ"/></td>
	    		</tr>
	    		<tr>
	    			<td>微信：</td>
	    			<td><input type="text" id="weChatNo"/></td>
	    		</tr>
	    		<tr>
	    			<td>紧急联系人：</td>
	    			<td><input type="text" id="emergencyContactPerson"/></td>
	    		</tr>
	    		<tr>
	    			<td>紧急联系人电话：</td>
	    			<td><input type="text" id="emergencyContactPhone"/></td>
	    		</tr>
	    		<tr>
	    			<td>所属部门：</td>
	    			<td>
	    				<input class="dept" id="did" style="width:150px;" />  
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>入职时间：</td>
	    			<td><input id="EntryTime"  type= "text" class= "easyui-datetimebox" style="width:150px;"></td>
	    		</tr>
	    		<tr>
	    			<td>是否有效：</td>
	    			<td>
	    				<select id="iseffective" class="easyui-combobox" name="iseffective" style="width:150px;">   
							<option value="1">是</option>
							<option value="0">否</option>
						</select> 
	    			</td>
	    		</tr>
	    		<tr>
	    			<td ><a href="javascript:void(0);" onclick="addUser();" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="margin-left: 40px;">提交</a> </td>
	    			<td ><input type="reset" value="重置"/></td>
	    		</tr>
	    	</table>
    	</form>
  	  </div>  
  </body>
</html>
