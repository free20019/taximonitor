<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
contentType="text/html; charset=UTF-8" %>
<HTML>
 <HEAD>
  <TITLE> JQuery右键菜单 </TITLE>
  <script language="javascript" type="text/javascript" src="js/jquery.contextmenu.r2.js"></script>
 </HEAD>
 <BODY>
 <table>
 	<tr class="demo1"><td>11</td><td>222</td></tr></br>
 	<tr class="demo1"><td>33</td><td>444</td></tr>
 </table>
<hr />
<hr />
    <!--右键菜单的源-->
     <div class="contextMenu" id="myMenu12">
      <ul>
        <li id="open"><img src="img/car/db.jpg" /> 打开</li>
        <li id="email"><img src="img/car/db.jpg" /> 邮件</li>
        <li id="save"><img src="img/car/db.jpg" /> 保存</li>
        <li id="delete"><img src="img/car/db.jpg" /> 关闭</li>
      </ul>
    </div>
 </BODY>
 <script>
    //所有class为demo1的span标签都会绑定此右键菜单
     $('.demo1').contextMenu('myMenu12', 
     {
          bindings: 
          {
            'open': function(t) {
              alert('Trigger was '+t.id+'\nAction was Open');
            },
            'email': function(t) {
              alert('Trigger was '+t.id+'\nAction was Email');
            },
            'save': function(t) {
              alert('Trigger was '+t.id+'\nAction was Save');
            },
            'delete': function(t) {
              alert('Trigger was '+t.id+'\nAction was Delete');
            }
          }
    });
 </script>
</HTML>