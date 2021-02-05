<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<HTML>
<HEAD>
<TITLE>AvtiveX</TITLE>
</HEAD>
<BODY>
<p>
服务器地址:&nbsp;<input type="text" name="IP" value="192.168.0.82">&nbsp;&nbsp;
服务器端口:&nbsp;<input type="text" name="PORT" value="5850">&nbsp;&nbsp;
</p>
<p>
用  户  名  :&nbsp;<input type="text" name="USER" value="qqzm">&nbsp;&nbsp;&nbsp;
用户密码:&nbsp;<input type="text" name="USERPASSWORD" value="123456">&nbsp;&nbsp;
</p>
<p>
设备ID号码:&nbsp;<input type="text" name="ID" value="B52006079">&nbsp;&nbsp;
设备密码:&nbsp;<input type="text" name="DEVPASSWORD" value="123456">
</p>
<p>
<input type="button" name="LOGIN" onclick='MYAvtiveX.c_LoginDVR(IP.value, PORT.value, USER.value, USERPASSWORD.value, ID.value, DEVPASSWORD.value,1, 1);'value='浏 览'>
</p>


<script language='javascript' for='MYAvtiveX' event='c_LoginReturn(Message)'> 
 if(Message ==1)
 {
   //alert("登录成功");
   MYAvtiveX.c_UserOperation(1,'','');
   MYAvtiveX.c_UserOperation(2,'','');
 }else if(Message ==0)
 {
    //alert("登录失败");
 }
</script>


<OBJECT WIDTH=386 HEIGHT=314 id="MYAvtiveX"
 classid="CLSID:9F1ED631-24A3-4894-B726-4A3346EE3333">
</OBJECT>

<p>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" name="PTZ_UP" onMouseDown="MYAvtiveX.c_PTZRotation(1,1,2,5,10,7,8);" value='  上  '>
</p>
<p>
<input type="button" name="PTZ_LEFT" onMouseDown="MYAvtiveX.c_PTZRotation(3,1,2,5,10,7,8);"  value='  左 '>
&nbsp;<input type="button" name="PTZ_STOP" onMouseDown="MYAvtiveX.c_PTZRotation(16,1,2,5,10,7,8);" value=' 停止 '>
&nbsp;<input type="button" name="PTZ_RIGHT" onMouseDown="MYAvtiveX.c_PTZRotation(4,1,2,5,10,7,8);" value='  右  '>
</p>
<p>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" name="PTZ_DOWN" onMouseDown="MYAvtiveX.c_PTZRotation(2,1,2,5,10,7,8);" value='  下  '>
</p>

</BODY>
</HTML>