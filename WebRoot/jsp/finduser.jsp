<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
contentType="text/html; charset=UTF-8" %>
<script src="js/jquery.blockUI.js"></script>
<section id="widget-grid" class="">
<!-- widget grid -->
<div class="row" >
<input type="hidden" id="date" value="${date}">
<input type="hidden" id="user_glqx" value="${glqx}"/>
	<!-- row -->
<table border="1" style="width: 102%;height: 100%;">
	<tr style="height: 150px;overflow: auto;">
		<td>
			<div class="jarviswidget jarviswidget-color-darken" id="wid-id-2" data-widget-editbutton="false">
				<header style = "text-align:right;">
					<span class="widget-icon"> <i class="fa fa-table"></i> </span>
					<h2 id="yhglym">用户管理</h2>
				</header>
				<!-- widget div-->
				<div>
					<div class="jarviswidget-editbox">
					</div>
					<div class="widget-body no-padding">
						<form action="" id="msg-form" class="smart-form" novalidate="novalidate" >
								<a href="javascript:void(0);" id="uck" style = "display:none" class="btn btn-primary" onclick="finduser();">查   看</a>
								<a href="javascript:void(0);" id="uxz" style = "display:none" class="btn btn-primary" onclick="setuser();">新   增</a>
								<a href="javascript:void(0);" id="uxg" style = "display:none" class="btn btn-primary" onclick="edituser();">修   改</a>
								<a href="javascript:void(0);" id="usc" style = "display:none" class="btn btn-primary" onclick="deluser();">删   除</a>
						</form>
						<div class="widget-body-toolbar">
						</div>
						
						<table id="fuel_driver" class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th></th>
									<th>序号</th>
									<th>姓名</th>
									<th>页面权限</th>
									<th>用户名</th>
									<th>数据权限</th>
								</tr>
							</thead>
							<tbody id="drivertbody">
							</tbody>
						</table>

					</div>
				</div>
			</div>
		</td>
	</tr>
</table>
<div id="finduser" title="查看">
	<form>
		<fieldset>
			<input name="authenticity_token" type="hidden">
			<div class="form-group">
			<table>
					<tr><td width="100">
				<label>姓名:</label></td><td>
				<input class="form-control" id="compnamefind" disabled   type="text"></td></tr><tr><td>
				<label>用户名:</label></td><td>
				<input class="form-control" id="usernamefind" disabled  type="text"></td></tr><tr><td>
				<label>密码:</label></td><td>
				<input class="form-control" id="passwordfind"  disabled type="text"></td></tr><tr><td>
				<label>岗位:</label></td><td>
				<input class="form-control" id="stationfind"  disabled type="text"></td></tr><tr><td>
				</table>
				</div>
				<div class="form-group">
					<label  width="100">数据权限:</label>
					<textarea rows="10" cols="35" disabled id = "datafind"></textarea>
				</div>
		</fieldset>
	</form>
</div>
<div id="adddriver1" title="新增">
	<form>
		<fieldset>
			<input name="authenticity_token" type="hidden">
			<div class="form-group">
				<label>姓名:</label>
				<input class="form-control" id="compnameadd"  placeholder="--输入姓名--" type="text">
			</div>
			<div class="form-group">
				<label>用户名:</label>
				<input class="form-control" id="usernameadd"  placeholder="--输入用户名--" type="text">
			</div>
			<div class="form-group">
				<label>密码:</label>
				<input class="form-control" id="passwordadd"  placeholder="--输入密码--" type="text">
			</div>
			<div class="form-group">
				<table>
					<tr><td width="100">
				<label>请选择岗位:</label>
					</td>
						<td ><select id="station_add">
							</select> 
						</td>
					</tr>
				</table>
				</div>
						<div class="form-group">
							<label>数据权限:</label>
							<table style="float:left;">
								<tr><td><label>公司:</label></td><td>按钮</td><td>所属公司</td></tr>
								<tr><td>
								<section>
									<label class="select select-multiple">
										<select multiple="" class="custom-scroll" style="height: 300px;width: 200px" id="group-add_qx">
										</select> </label>
								</section>
								</td>
								<td>
									&nbsp;&nbsp;<input name="" type="button" id="addall_qx" style=" width:30px; height:30px;  border:0; background:url(img/anniu/yy.png)" />&nbsp;&nbsp;<br/><br/>
									&nbsp;&nbsp;<input id="addone_qx" type="button"  style=" width:30px; height:30px;  border:0; background:url(img/anniu/y.png)" />&nbsp;&nbsp;<br/><br/>
									&nbsp;&nbsp;<input id="delone_qx" type="button"  style=" width:30px; height:30px;  border:0; background:url(img/anniu/z.png)" />&nbsp;&nbsp;<br/><br/>
									&nbsp;&nbsp;<input id="delall_qx" type="button"  style=" width:30px; height:30px;  border:0; background:url(img/anniu/zz.png)" />
								</td>
								<td>
								<section>
									<label class="select select-multiple">
										<select multiple="" class="custom-scroll" style="height: 300px;width: 200px" id="sel-add_qx">
										</select> </label>
								</section>
								</td>
								</tr>
							</table>
						</div>
		</fieldset>
	</form>
</div>
<div id="edituser" title="修改">
	<form>
		<fieldset>
			<input name="authenticity_token" type="hidden">
			<input type = "hidden" id = "useridedit">
			<div class="form-group">
				<label>姓名:</label>
				<input class="form-control" id="compnameedit"  placeholder="--输入姓名--" type="text">
			</div>
			<div class="form-group">
				<label>用户名:</label>
				<input class="form-control" id="usernameedit"  placeholder="--输入用户名--" type="text">
			</div>
			<div class="form-group">
				<label>密码:</label>
				<input class="form-control" id="passwordedit"  placeholder="--输入密码--" type="text">
			</div>
			<div class="form-group">
				<table>
					<tr><td width="100">
				<label>请选择岗位:</label>
					</td>
						<td ><select id="station_edit">
							</select> 
						</td>
					</tr>
				</table>
				</div>
						<div class="form-group">
							<label>数据权限:</label>
							<table style="float:left;">
								<tr><td><label>公司:</label></td><td>按钮</td><td>所属公司</td></tr>
								<tr><td>
								<section>
									<label class="select select-multiple">
										<select multiple="" class="custom-scroll" style="height: 300px;width: 200px" id="group-edit_qx">
										</select> </label>
								</section>
								</td>
								<td>
									&nbsp;&nbsp;<input name="" type="button" id="editall_qx" style=" width:30px; height:30px;  border:0; background:url(img/anniu/yy.png)" />&nbsp;&nbsp;<br/><br/>
									&nbsp;&nbsp;<input id="editone_qx" type="button"  style=" width:30px; height:30px;  border:0; background:url(img/anniu/y.png)" />&nbsp;&nbsp;<br/><br/>
									&nbsp;&nbsp;<input id="editdelone_qx" type="button"  style=" width:30px; height:30px;  border:0; background:url(img/anniu/z.png)" />&nbsp;&nbsp;<br/><br/>
									&nbsp;&nbsp;<input id="editdelall_qx" type="button"  style=" width:30px; height:30px;  border:0; background:url(img/anniu/zz.png)" />
								</td>
								<td>
								<section>
									<label class="select select-multiple">
										<select multiple="" class="custom-scroll" style="height: 300px;width: 200px" id="sel-edit_qx">
										</select> </label>
								</section>
								</td>
								</tr>
							</table>
						</div>
		</fieldset>
	</form>
</div>
</div>
<script type="text/javascript">
	var area_glqx = $("#user_glqx").val();
	if(area_glqx!=null&&area_glqx.length>0){
		var a = area_glqx.split(',');
		for(var i=0;i<a.length;i++){
			if(a[i]=='用户管理查看'){
				$("#uck").css('display','');
			}
			if(a[i]=='用户管理增加'){
				$("#uxz").css('display','');
			}
			if(a[i]=='用户管理修改'){
				$("#uxg").css('display','');
			}
			if(a[i]=='用户管理删除'){
				$("#usc").css('display','');
			}
		}
	}
	// DO NOT REMOVE : GLOBAL FUNCTIONS!
	pageSetUp();
	//loadDataTableScripts_driver();
	finddriver();
	var card;
	var cards;
// 	findba();
	// PAGE RELATED SCRIPTS
	function finddriver(){
	$.blockUI({message:"<div style='padding:5px;'><h3><font color='blue'>数据查询中,请稍候...</font></h3><img src='img/ajax-loader.gif' border='0'/><br/><br/></div>"});
		$.ajax({
			url : 'finduser.action',
			type : 'post',
			data:{
			},
			dataType: 'json',
			timeout : 180000,
			success:function(json){
					var quanxian=json.list;
					var tab="";
					if(quanxian!=null){
					for(var i=0;i<quanxian.length;i++){
						tab=tab+"<tr><td><input type='checkbox' name='userid' value='"+quanxian[i].id+"' /></td>";
						tab=tab+"<td nowrap='nowrap'>"+(i+1)+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+quanxian[i].real_name+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+quanxian[i].station_id+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+quanxian[i].username+"</td>";
						if(quanxian[i].date_view_type.length>25){
							tab=tab+"<td nowrap='nowrap'>&nbsp;"+quanxian[i].date_view_type.substr(0,30)+"...</td></tr>";
						}else{
							tab=tab+"<td nowrap='nowrap'>&nbsp;"+quanxian[i].date_view_type+"</td></tr>";
						}
					}
					$("#drivertbody").html(tab);
					loadDataTableScripts_driver();
					}
			},
			error:function(){
				
			}
		});
		$.unblockUI();
	}
	
 
	//增加
	 function adduser(){
			var select = document.getElementById("sel-add_qx").options;
			var a = "";
   			for(var i=0; i<select.length; i++) {
          			 a += select[i].value +",";
   			}
		a=a.substr(0,a.length-1);
		$.ajax({
            type: "POST",
            url:"adduser.action",
            data:{
            	"username" : $("#usernameadd").val(),
            	"password" : $("#passwordadd").val(),
            	"date_view_type" : a,
            	"station_id" : $("#station_add").val(),
            	"realname" : $("#compnameadd").val()
            },
           dataType: 'json',
		timeout : 180000,
            error: function(json) {
                alert(json.info);
            },
            success: function(json) {
               alert(json.info);
               finddriver();
            }
        });		
     }
     //增加弹出窗口
	function setuser(){
			$("#sel-add_qx").empty();
	        $("#sel-edit_qx").empty();
			$("#compnameadd").val("");
            $("#usernameadd").val("");
            $("#passwordadd").val("");
            findcompany();
            findstat();
			$('#adddriver1').dialog('open');
		}
		$('#adddriver1').dialog({
		autoOpen : false,
		width : 500,
		resizable : false,
		modal : true,
		buttons : [{
				html : "<i class='fa fa-times'></i>&nbsp; 取消",
				"class" : "btn btn-default",
				click : function() {
					$(this).dialog("close");
	
				}
			}, {
	
				html : "<i class='fa fa-plus'></i>&nbsp; 增加",
				"class" : "btn btn-danger",
				click : function() {
					adduser();
					$(this).dialog("close");
				}
			}]
	});
	//将数据存入数组
	function getArray(list){
		var str = new Array();
		var str2 = new Array();
		$('#sel-add_qx option').each(function(){
			str.push($(this).val());
		});
		
		for(var i =0;i<list.length;i++){
			var flag = true;
			for(var j =0;j<str.length;j++){
				if(list[i]== str[j])
					flag = false;
			}
			if(flag)
				str2.push(list[i]);
		}
		$("#group-add_qx").empty();
			for(var i=0;i<str2.length;i++){
				$("#group-add_qx").append("<option value='"+str2[i]+"'>"+str2[i]+"</option>");
			}
			return str2;
	}
	//添加所有车
	$('#addall_qx').unbind('click').click(function(){
		var lists = getArray(card);
		for(var i =0;i< lists.length;i++){
			$("#sel-add_qx").append("<option value='"+lists[i].name+"'>"+lists[i].name+"</option>");
		}
			$("#group-add_qx").empty();
		
	});
	//添加一辆车
	$('#addone_qx').unbind('click').click(function(){
		var vehicle = $("#group-add_qx option:selected").text();
		if(vehicle !=""){
			$("#sel-add_qx").append("<option value='"+vehicle+"'>"+vehicle+"</option>");
			 var i = $("#group-add_qx").get(0).selectedIndex;
				$("#group-add_qx").get(0).remove(i);
		}else{
			alert("请选择要添加的车号");
		}
		
	});
	//删除一辆车
	$('#delone_qx').unbind('click').click(function(){
		var i = $("#sel-add_qx").get(0).selectedIndex;
		var vehicle = $("#sel-add_qx").text();
		if(i != -1){
			$("#sel-add_qx").get(0).remove(i);
			$("#group-add_qx").append("<option value='"+vehicle+"'>"+vehicle+"</option>");
		}else{
			alert("请选择要移除的车号");
		}
	});
	//删除所有车
	$('#delall_qx').unbind('click').click(function(){
		$("#sel-add_qx").empty();
		findcompany();
	});
	//删除
	function deluser() {
		var s="";
		$('input[name="userid"]:checked').each(function(){
    	s+=$(this).val()+' ';
	});
	var checkbox=s.split(' ');
	if(checkbox.length<2){
		alert("请选择数据进行删除!");
	}else{
    	var r = window.confirm("您确定要删除该条数据吗？");
    	if(r) {
    	 $.blockUI({message:"<div style='padding:5px;'><h3><font color='blue'>数据查询中,请稍候...</font></h3><img src='img/ajax-loader.gif' border='0'/><br/><br/></div>"});
     	$.ajax({
			url : 'deluser.action',
			type : 'post',
			data:{
				"id":s
			},
			dataType: 'json',
			timeout : 180000,
			success:function(json){
			alert(json.info);
			  finddriver();
			},
			error:function(){
				alert(json.info);
			}		
	});
     	}
     	$.unblockUI();
	 }
	 }
	 //修改弹出窗口
	function edituser(){
	var s="";
		$('input[name="userid"]:checked').each(function(){
    	s+=$(this).val()+' ';
	});
	var checkbox=s.split(' ');
	if(checkbox.length<2){
		alert("请选择数据进行修改!");
	}else if(checkbox.length>2){
		alert("每次只能修改一条数据!");
	}else{
	 findcompany();
             findstat();
     	$.ajax({
             type: "POST",
             url:"iduser.action",
             data:{
             	"id" : s
             },
            dataType: 'json',
			timeout : 180000,
             success: function(json) {
             $("#sel-add_qx").empty();
	        $("#sel-edit_qx").empty();
            
                $("#useridedit").val(json.user.id);
                $("#compnameedit").val(json.user.real_name);
                $("#usernameedit").val(json.user.username);
                $("#passwordedit").val(json.user.password);
                var shuju=json.user.date_view_type.split(',');
                for(var i=0;i<shuju.length;i++){
                	$("#sel-edit_qx").append("<option value='"+shuju[i]+"'>"+shuju[i]+"</option>");
                }
                var select = document.getElementById("station_edit").options;
      			for(var i=0; i<select.length; i++) {
           		 	if(select[i].value == json.user.job_number) {
                    	select[i].selected = true;
                    	break;
            		}
      			}
             }
         });
		$('#edituser').dialog('open');
	}
	}
	$('#edituser').dialog({
		autoOpen : false,
		width : 500,
		resizable : false,
		modal : true,
		buttons : [{
				html : "<i class='fa fa-times'></i>&nbsp; 取消",
				"class" : "btn btn-default",
				click : function() {
					$(this).dialog("close");
	
				}
			}, {
	
				html : "<i class='fa fa-plus'></i>&nbsp; 修改",
				"class" : "btn btn-danger",
				click : function() {
					edituser1();
					$(this).dialog("close");
				}
			}]
	});
	
	//将数据存入数组
	function getArrays(list){
		var str = new Array();
		var str2 = new Array();
		$('#sel-edit_qx option').each(function(){
			str.push($(this).val());
		});
		
		for(var i =0;i<list.length;i++){
			var flag = true;
			for(var j =0;j<str.length;j++){
				if(list[i]== str[j])
					flag = false;
			}
			if(flag)
				str2.push(list[i]);
		}
		$("#group-edit_qx").empty();	
			for(var i=0;i<str2.length;i++){
				$("#group-edit_qx").append("<option value='"+str2[i]+"'>"+str2[i]+"</option>");
			}
			return str2;
	}
	//添加所有车
	$('#editall_qx').unbind('click').click(function(){
		var lists = getArrays(cards);
		for(var i =0;i< lists.length;i++){
			$("#sel-edit_qx").append("<option value='"+lists[i].name+"'>"+lists[i].name+"</option>");
		}
			$("#group-edit_qx").empty();
		
	});
	//添加一辆车
	$('#editone_qx').unbind('click').click(function(){
		var vehicle = $("#group-edit_qx option:selected").text();
		if(vehicle !=""){
			$("#sel-edit_qx").append("<option value='"+vehicle+"'>"+vehicle+"</option>");
			 var i = $("#group-edit_qx").get(0).selectedIndex;
				$("#group-edit_qx").get(0).remove(i);
		}else{
			alert("请选择要添加的车号");
		}
		
	});
	//删除一辆车
	$('#editdelone_qx').unbind('click').click(function(){
		var i = $("#sel-edit_qx").get(0).selectedIndex;
		var vehicle = $("#sel-edit_qx").val();
		if(i != -1){
			$("#sel-edit_qx").get(0).remove(i);
			$("#group-edit_qx").append("<option value='"+vehicle+"'>"+vehicle+"</option>");
		}else{
			alert("请选择要移除的车号");
		}
	});
	//删除所有车
	$('#editdelall_qx').unbind('click').click(function(){
		$("#sel-edit_qx").empty();
		findcompany();
	});
	//修改
	 function edituser1(){
	 var select = document.getElementById("sel-edit_qx").options;
			var a = "";
   			for(var i=0; i<select.length; i++) {
          			 a += select[i].value +",";
   			}
		a=a.substr(0,a.length-1);
     $.blockUI({message:"<div style='padding:5px;'><h3><font color='blue'>数据查询中,请稍候...</font></h3><img src='img/ajax-loader.gif' border='0'/><br/><br/></div>"});
     	$.ajax({
             type: "POST",
             url:"edituser.action",
             data:{
             	"id" : $("#useridedit").val(),
             	"realname" : $("#compnameedit").val(),
             	"username" : $("#usernameedit").val(),
             	"password" : $("#passwordedit").val(),
             	"station_id" : $("#station_edit").val(),
             	"date_view_type" : a,
             },
            dataType: 'json',
			timeout : 180000,
             error: function(json) {
                 alert(json.info);
             },
             success: function(json) {
                alert(json.info);
                finddriver();
             }
         });
     	$.unblockUI();
     }
     //查看
     function finduser(){
	var s="";
		$('input[name="userid"]:checked').each(function(){
    	s+=$(this).val()+' ';
	});
	var checkbox=s.split(' ');
	if(checkbox.length<2){
		alert("请选择数据进行才查看!");
	}else if(checkbox.length>2){
		alert("每次只能修改一条查看!");
	}else{
     	$.ajax({
             type: "POST",
             url:"iduser.action",
             data:{
             	"id" : s
             },
            dataType: 'json',
			timeout : 180000,
             success: function(json) {
                $("#compnamefind").val(json.user.real_name);
                $("#usernamefind").val(json.user.username);
                $("#passwordfind").val(json.user.password);
                $("#stationfind").val(json.user.station_id);
                var shuju=json.user.date_view_type.split(',');
                var data = "";
                for(var i=0;i<shuju.length;i++){
                	data+=shuju[i]+'\r\n';
                }
                $("#datafind").val(data);
//             		$("#findba").val(shuju[0]);
//             		$("#findcomp").val(shuju[1]);
//             		$("#findvehi").val(shuju[2]);
             }
         });
		$('#finduser').dialog('open');
	}
	}
	$('#finduser').dialog({
		autoOpen : false,
		width : 350,
		resizable : false,
		modal : true,
	});
	//下拉框
	function findcompany(){
	$.ajax({
		 type: "POST",
	        url:"findcompany.action",
	        data:{
	        },
	       dataType: 'json',
			timeout : 180000,
		success:function(json){
	        	var cust=json.company;
	        	$("#group-add_qx").empty();
	        	$("#group-edit_qx").empty();
	        	card=cust;
	        	cards=cust;
	        	for(var i=0;i<cust.length;i++){
	        		$("#group-add_qx").append("<option value='"+cust[i].baid+"'>"+cust[i].name+"</option>");
	        		$("#group-edit_qx").append("<option value='"+cust[i].baid+"'>"+cust[i].name+"</option>");
	        	}
		},
		error:function(){
		}		
	});
}
	//岗位下拉框
	//下拉框
	function findstat(){
	$.ajax({
		 type: "POST",
	        url:"findstation.action",
	        data:{
	        },
	       dataType: 'json',
			timeout : 180000,
		success:function(json){
	        	var cust=json.station;
	        	$("#station_add").empty();
	        	$("#station_edit").empty();
	        	for(var i=0;i<cust.length;i++){
	        		$("#station_add").append("<option value='"+cust[i].id+"'>"+cust[i].station_name+"</option>");
	        		$("#station_edit").append("<option value='"+cust[i].id+"'>"+cust[i].station_name+"</option>");
	        	}
		},
		error:function(){
		}		
	});
}
	$("#yhglym").html("用户管理");
//树
	$('.tree > ul').attr('role', 'tree').find('ul').attr('role', 'group');
	$('.tree').find('li:has(ul)').addClass('parent_li').attr('role', 'treeitem').find(' > span').attr('title', 'Collapse this branch').on('click', function(e) {
		var children = $(this).parent('li.parent_li').find(' > ul > li');
		if (children.is(':visible')) {
			children.hide('fast');
			$(this).attr('title', 'Expand this branch').find(' > i').removeClass().addClass('fa fa-lg fa-plus-circle');
		} else {
			children.show('fast');
			$(this).attr('title', 'Collapse this branch').find(' > i').removeClass().addClass('fa fa-lg fa-minus-circle');
		}
		e.stopPropagation();
	});
		
	if($('.DTTT_dropdown.dropdown-menu').length){
		$('.DTTT_dropdown.dropdown-menu').remove();
	}

	var drivers="";
	function loadDataTableScripts_driver() {

		loadScript("js/plugin/datatables/jquery.dataTables-cust.min.js", dt_2);

		function dt_2() {
			loadScript("js/plugin/datatables/ColReorder.min.js", dt_3);
		}

		function dt_3() {
			loadScript("js/plugin/datatables/FixedColumns.min.js", dt_4);
		}

		function dt_4() {
			loadScript("js/plugin/datatables/ColVis.min.js", dt_5);
		}

		function dt_5() {
			loadScript("js/plugin/datatables/ZeroClipboard.js", dt_6);
		}

		function dt_6() {
			loadScript("js/plugin/datatables/media/js/TableTools.min.js", dt_7);
		}

		function dt_7() {
			loadScript("js/plugin/datatables/DT_bootstrap.js", runDataTables_driver);
		}

	}

	function runDataTables_driver() {

		/*
		 * BASIC
		 */
		drivers=$('#fuel_driver').dataTable({
			"sPaginationType" : "bootstrap_full",
			"bRetrieve": true
		});

		/* END BASIC */

		/* Add the events etc before DataTables hides a column */
		$("#datatable_fixed_column thead input").keyup(function() {
			oTable.fnFilter(this.value, oTable.oApi._fnVisibleToColumnIndex(oTable.fnSettings(), $("thead input").index(this)));
		});

		$("#datatable_fixed_column thead input").each(function(i) {
			this.initVal = this.value;
		});
		$("#datatable_fixed_column thead input").focus(function() {
			if (this.className == "search_init") {
				this.className = "";
				this.value = "";
			}
		});
		$("#datatable_fixed_column thead input").blur(function(i) {
			if (this.value == "") {
				this.className = "search_init";
				this.value = this.initVal;
			}
		});		
		

		var oTable = $('#datatable_fixed_column').dataTable({
			"sDom" : "<'dt-top-row'><'dt-wrapper't><'dt-row dt-bottom-row'<'row'<'col-sm-6'i><'col-sm-6 text-right'p>>",
			//"sDom" : "t<'row dt-wrapper'<'col-sm-6'i><'dt-row dt-bottom-row'<'row'<'col-sm-6'i><'col-sm-6 text-right'>>",
			"oLanguage" : {
				"sSearch" : "Search all columns:"
			},
			"bSortCellsTop" : true
		});		
		


		/*
		 * COL ORDER
		 */
		$('#datatable_col_reorder').dataTable({
			"sPaginationType" : "bootstrap",
			"sDom" : "R<'dt-top-row'Clf>r<'dt-wrapper't><'dt-row dt-bottom-row'<'row'<'col-sm-6'i><'col-sm-6 text-right'p>>",
			"fnInitComplete" : function(oSettings, json) {
				$('.ColVis_Button').addClass('btn btn-default btn-sm').html('Columns <i class="icon-arrow-down"></i>');
			}
		});
		
		/* END COL ORDER */

		/* TABLE TOOLS */
		$('#datatable_tabletools').dataTable({
			"sDom" : "<'dt-top-row'Tlf>r<'dt-wrapper't><'dt-row dt-bottom-row'<'row'<'col-sm-6'i><'col-sm-6 text-right'p>>",
			"oTableTools" : {
				"aButtons" : ["copy", "print", {
					"sExtends" : "collection",
					"sButtonText" : 'Save <span class="caret" />',
					"aButtons" : ["csv", "xls", "pdf"]
				}],
				"sSwfPath" : "js/plugin/datatables/media/swf/copy_csv_xls_pdf.swf"
			},
			"fnInitComplete" : function(oSettings, json) {
				$(this).closest('#dt_table_tools_wrapper').find('.DTTT.btn-group').addClass('table_tools_group').children('a.btn').each(function() {
					$(this).addClass('btn-sm btn-default');
				});
			}
		});
		
		/* END TABLE TOOLS */
	}
</script>

<script src="js/jquery.blockUI.js"></script>

<link rel="stylesheet" type="text/css" media="screen" href="css/bootstrap.min.css">
