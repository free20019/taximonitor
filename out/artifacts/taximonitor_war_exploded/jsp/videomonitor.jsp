<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML >
<html lang="zh-cn" style="height:100%">
<head>
<link rel="stylesheet" type="text/css" href="css/zTreeStyle/zTreeStyle.css"/>
<script src="js/libs/jquery-2.1.1.min.js"></script>
<script src="js/browser.js"></script>
<script type="text/javascript" src="js/jquery.ztree.core-3.4.min.js"></script>
</head>
<body style="height: 95%;">
<div style="height: 100%;">
<p>
<input type="hidden" name="IP" value="192.168.0.82">&nbsp;&nbsp;
<input type="hidden" name="PORT" value="5850">&nbsp;&nbsp;
</p>
<p>
<input type="hidden" name="USER" value="qqzm">&nbsp;&nbsp;&nbsp;
<input type="hidden" name="USERPASSWORD" value="123456">&nbsp;&nbsp;
</p>
<p>
<input type="hidden" name="ID" value="B52006064">&nbsp;&nbsp;
<input type="hidden" name="DEVPASSWORD" value="123456">
</p>
<br>
	<div style="width:20%;height: 100%;float: left;overflow: auto">
		<input type="text"  placeholder="车牌号码" id="vediokeyword" onkeyup="">
		<ul id="databanzu" class="ztree" >
			<li>浙AT2687
				<ul>
					<li><input type="button" name="LOGIN" onclick='v1.c_LoginDVR(IP.value, PORT.value, USER.value, USERPASSWORD.value, ID.value, DEVPASSWORD.value,1, 1);'value='通道一'></li>
					<li><input type="button" name="LOGIN" onclick='v2.c_LoginDVR(IP.value, PORT.value, USER.value, USERPASSWORD.value, ID.value, DEVPASSWORD.value,1, 2);'value='通道二'></li>
					<li><input type="button" name="LOGIN" onclick='v3.c_LoginDVR(IP.value, PORT.value, USER.value, USERPASSWORD.value, ID.value, DEVPASSWORD.value,1, 3);'value='通道三'></li>
					<li><input type="button" name="LOGIN" onclick='v4.c_LoginDVR(IP.value, PORT.value, USER.value, USERPASSWORD.value, ID.value, DEVPASSWORD.value,1, 4);'value='通道四'></li>
				</ul>
			</li>
			
		</ul>
	
	</div>
	<div style='width: 0%;height: 630;float: left' id="hksp">
		<table cellpadding='0' cellspacing='0'  border='1' bordercolor='#ffffff' width='100%' height="100%" align='center'>
			<tr>
				<td><object id='v1' classid='CLSID:9F1ED631-24A3-4894-B726-4A3346EE3333' width='386' height='314' style='margin: 0;'></object></td>
				<td><object id='v2' classid='CLSID:9F1ED631-24A3-4894-B726-4A3346EE3333' width='386' height='314' style='margin: 0;'></object></td>
			</tr>
			<tr>
				<td><object id='v3' classid='CLSID:9F1ED631-24A3-4894-B726-4A3346EE3333' width='386' height='314' style='margin: 0;'></object></td>
				<td><object id='v4' classid='CLSID:9F1ED631-24A3-4894-B726-4A3346EE3333' width='386' height='314' style='margin: 0;'></object></td>
			</tr>
		</table>
	</div>
</div>
<script language='javascript' for='v1' event='c_LoginReturn(Message)'> 
 if(Message ==1)
 {
   //alert("登录成功");
   v1.c_UserOperation(1,'','');
   v1.c_UserOperation(2,'','');
 }else if(Message ==0)
 {
    //alert("登录失败");
 }
</script>
<script language='javascript' for='v2' event='c_LoginReturn(Message)'> 
 if(Message ==1)
 {
   //alert("登录成功");
   v2.c_UserOperation(1,'','');
   v2.c_UserOperation(2,'','');
 }else if(Message ==0)
 {
    //alert("登录失败");
 }
</script>
<script language='javascript' for='v3' event='c_LoginReturn(Message)'> 
 if(Message ==1)
 {
   //alert("登录成功");
   v3.c_UserOperation(1,'','');
   v3.c_UserOperation(2,'','');
 }else if(Message ==0)
 {
    //alert("登录失败");
 }
</script>
<script language='javascript' for='v4' event='c_LoginReturn(Message)'> 
 if(Message ==1)
 {
   //alert("登录成功");
   v4.c_UserOperation(1,'','');
   v4.c_UserOperation(2,'','');
 }else if(Message ==0)
 {
    //alert("登录失败");
 }
</script>