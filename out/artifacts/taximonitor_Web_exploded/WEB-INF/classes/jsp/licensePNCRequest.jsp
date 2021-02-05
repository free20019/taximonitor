<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
contentType="text/html; charset=UTF-8" %>
<!-- widget grid -->
<div class="row" style="height:600px;overflow: scroll">
<input type="hidden" id="um1" value="${username }"/>
	<!-- row -->
<table border="1" style="width: 102%;">
	<tr style="height: ;overflow: auto;">
		<td>
			<div class="jarviswidget jarviswidget-color-darken" id="wid-id-2" data-widget-editbutton="false">
			<header style = "text-align:left;">
					<span class="widget-icon"> <i class="fa fa-table"></i> </span>
					<h2 id="czqcwsxcl">车牌号变更申请</h2>
				</header>
				<!-- widget div-->
				<div>
					<div class="jarviswidget-editbox">
					</div>
					<div class="widget-body no-padding">
					<form action="" id="msg-form" class="smart-form" novalidate="novalidate" >
								<section style="width:180px;float:left;">
										<label class="input"> <i class="icon-append fa fa-calendar"></i>
											<input id="vehiclechangestime" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})()" placeholder="--请选择开始时间--"/>
										</label>
								</section>
								<section style="width:180px;float:left;">
										<label class="input"> <i class="icon-append fa fa-calendar"></i>
											<input id="vehiclechangeetime" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})()" placeholder="--请选择结束时间--"/>
										</label>
								</section>
								<section style=" width:130px;float:left">
									<label class="input">
										<input id="change_car" type="text" list="changecar" placeholder="--原车牌号码--" >
										<datalist id="changecar" >
										
										</datalist> 
									</label>
								</section>
								<section style=" width:130px;float:left">
									<label class="input">
										<input id="change_to_car" type="text" list="changetocar" placeholder="--新车牌号码--" >
										<datalist id="changetocar" >
										
										</datalist> 
									</label>
								</section>
								<a href="javascript:void(0);"  class="btn btn-primary"  onclick="findvehiclechange();">查   询</a>
								<a href="javascript:void(1);"  class="btn btn-primary"  onclick="findvehiclechangeexcle();">导   出</a>
								<a href="javascript:void(2);" id="vehicle_add" class="btn btn-primary" onclick="addVehicle_change();">添加</a>
								
								<span id="vehiclechangedaochu"></span>
							<section style="width:90px;text-align: center;height:32px;line-height:32px;">
										<label class="span"> 
											 <span id="vehiclechangets"></span>
										</label>
									</section>
						</form>
						<table id="vehiclechangetable" style="float:left;"  class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
								    <th>序号</th>
					             	<th>原车牌号码</th>
			                        <th>现车牌号码</th>
			                        <th>申请时间</th>
			                        <th>审核状态</th>
			                        <th>审核时间</th>
			                        <th>审核原因</th>
			                        <th>操作</th>
								</tr>
							</thead>
							<tbody id="vehiclechangetbody">
							</tbody>
						</table>

					</div>
				</div>
			</div>
		</td>
	</tr>
</table>
</div>
<div id="vehicle_change_dialog" title="">
	<p>
	<form action="" id="checkout-form" class="smart-form" novalidate="novalidate" >
	<table border="0">
	<tr>
		<td valign="top">车牌号码:&nbsp;&nbsp;&nbsp;</td>
		<td >
			<section style=" width:250px;float:left">
				<label class="input">
					<input id="change_vehicle" type="text" list="changevehicle" placeholder="--车牌号码--" >
					<datalist id="changevehicle" >
					
					</datalist> 
				</label>
			</section>
		</td>
	</tr>
	<tr>
		<td valign="top">变更车牌号:&nbsp;&nbsp;&nbsp;</td>
		<td>
		<label class="input"  > 
			<input style="width:250px;float:left" id="change_to_vehicle" type="text"  placeholder="--变更为车牌号码--" >
		</label>
		</td>
	</tr>
	</table>
	</form>
	</p>
</div>
<script type="text/javascript">
	
	// DO NOT REMOVE : GLOBAL FUNCTIONS!
	pageSetUp();
	$(document).ready(function(){
		var mydate = new Date();
		$("#vehiclechangestime").val(setformatnewlc(mydate));
		$("#vehiclechangeetime").val(setformatnewlc(mydate));
	});
	function setformatnewlc(obj){
		var y=(obj.getFullYear()).toString();
		var m=(obj.getMonth()+1).toString();
		if(m.length==1){
			m="0"+m;
		}
		var d=obj.getDate().toString();
		if(d.length==1){
			d="0"+d;
		}
		var time=y+"-"+m+"-"+d; 
		return time;
	}
	//弹框
	$('#vehicle_change_dialog').dialog({
		autoOpen : false,
		width : 400,
		resizable : false,
		modal : true,
	});
	//增加弹框
	function addVehicle_change(){
		$("#change_vehicle").val("");
		$("#change_to_vehicle").val("");

		$('#vehicle_change_dialog').dialog({
			title : "车牌号变更添加",
			buttons : [{
				html : "添加",
				"class" : "btn btn-danger",
				click : function() {
					addvehiclechange();
				}
			}, {
				html : "取消",
				"class" : "btn btn-default",
				click : function() {
					$(this).dialog("close");
				}
			}]
		});
		$('#vehicle_change_dialog').dialog('open');
	}
	//修改弹框
	function editVehicle_change(id){
		for(i=0;i<vehiclechange.length;i++){
			if(vehiclechange[i].comp_id==id){
				$("#change_vehicle").val(vehiclechange[i].vehi_no);
				$("#change_to_vehicle").val(vehiclechange[i].comp_name);
			}
		}
		$('#vehicle_change_dialog').dialog({
			title : "车牌号变更修改",
			buttons : [{
				html : "修改",
				"class" : "btn btn-danger",
				click : function() {
					editvehiclechange(id);
					$(this).dialog("close");
				}
			}, {
				html : "取消",
				"class" : "btn btn-default",
				click : function() {
					$(this).dialog("close");
				}
			}]
		});
		$('#vehicle_change_dialog').dialog('open');
	}
	//添加请求
	function addvehiclechange(){
		if($("#change_vehicle").val()==""){
			alert("请输入原车牌号码");
			return ;
		}
		if($("#change_to_vehicle").val()==""){
			alert("请输入新车牌号码");
			return ;
		}
		$.ajax({
			url : 'addvehiclechange.action',
			type : 'post',
			data:{
				"vehicle_no" : $("#change_vehicle").val(),
				"new_vehicle" : $("#change_to_vehicle").val()
			},
			dataType: 'json',
			timeout : 180000,
			success:function(json){
				alert(json.info);
				if(json.info=="添加成功"){					
					$("#vehicle_change_dialog").dialog("close");
					findvehiclechange();
				}
			},
			error:function(){
			}		
		});
	}
	//修改请求
	function editvehiclechange(id){
		if($("#change_vehicle").val()==""){
			alert("请输入原车牌号码");
			return ;
		}
		if($("#change_to_vehicle").val()==""){
			alert("请输入新车牌号码");
			return ;
		}
		$.ajax({
			url : 'editvehiclechange.action',
			type : 'post',
			data:{
				"vehicle_no" : $("#change_vehicle").val(),
				"new_vehicle" : $("#change_to_vehicle").val(),
				"id" : id
			},
			dataType: 'json',
			timeout : 180000,
			success:function(json){
				alert(json.info);
				if(json.info=="修改成功"){					
					$("#vehicle_change_dialog").dialog("close");
					findvehiclechange();
				}
			},
			error:function(){
			}		
		});
	}
	//删除请求
	function deletevehiclechange(id){
		if(window.confirm("确定删除该数据？")){
			$.ajax({
				url : 'deletevehiclechange.action', 
				type : 'post',
				data:{
					"id" : id
				},
				dataType: 'json',
				timeout : 180000,
				success:function(json){
					alert(json.info);
					findvehiclechange();
				},
				error:function(){
					alert("请求失败");
				}		
			});
			}
	}
	//页面刷新加载
	find1();
	function find1(){
		findvehicle();
		findnewvehicle();
		setTimeout("findvehiclechange()", 500);
	}

	var vehiclechange=null;	
	function findvehiclechange(){
		$.blockUI({message:"<div style='padding:5px;'><h3><font color='blue'>数据查询中,请稍候...</font></h3><img src='img/ajax-loader.gif' border='0'/><br/><br/></div>"});
		$.ajax({
			url : 'findvehiclechange.action',
			type : 'post',
			data:{
				"stime" : $("#vehiclechangestime").val(),
				"etime" : $("#vehiclechangeetime").val(),
				"vehicle_no" : $("#change_car").val(),
				"new_vehicle" : $("#change_to_car").val()
			},
			dataType: 'json',
			timeout : 180000,
			success:function(json){
				vehiclechange = json.list3;
				console.log("chaxun=",vehiclechange)
				var tab="";
				var i=0;
				if(vehiclechange!=null){
					for(i=0;i<vehiclechange.length;i++){
						tab=tab+"<tr><td nowrap='nowrap'>"+(i+1)+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+vehiclechange[i].vehi_no+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+vehiclechange[i].comp_name+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+vehiclechange[i].own_name+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+vehiclechange[i].vehi_sim+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+vehiclechange[i].own_tel+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+vehiclechange[i].run_times+"</td>";
						tab=tab+"<td><input type='button' value='改' onclick='editVehicle_change("+vehiclechange[i].comp_id+")'>&nbsp;<input type='button' value='删' onclick='deletevehiclechange("+vehiclechange[i].comp_id+")'></td></tr>";
					}
						$("#vehiclechangets").html("总条数:"+i+"条");
						$("#vehiclechangetbody").html(tab);
						$.unblockUI();
				}
			},
			error:function(){
				
			}		
		});
	}
	//车辆下拉栏
	function findvehicle(){
		$.ajax( {
			url : 'findvhic1.action',
			type : 'post',
			data : {},
			dataType : 'json',
			timeout : 180000,
			success : function(json) {
				var cust=json.list;
				var tab="";
				var tab2="";
				for ( var i = 0; i < cust.length; i++) {
					tab+="<option value='"+cust[i].vehi_no+"'></option>";
				}
				$("#changecar").html(tab);
				$("#changevehicle").html(tab);
			},
			error : function() {
	
			}
		});
	}
	//new车辆下拉栏
	function findnewvehicle(){
		$.ajax( {
			url : 'findnewvehicle.action',
			type : 'post',
			data : {},
			dataType : 'json',
			timeout : 180000,
			success : function(json) {
				var cust=json.list4;
				var tab="";
				var tab2="";
				for ( var i = 0; i < cust.length; i++) {
					tab+="<option value='"+cust[i].vehi_no+"'></option>";
				}
				$("#changetocar").html(tab);
			},
			error : function() {
	
			}
		});
	}
	//导出excle
	function findvehiclechangeexcle(){
		$.blockUI({message:"<div style='padding:5px;'><h3><font color='blue'>数据查询中,请稍候...</font></h3><img src='img/ajax-loader.gif' border='0'/><br/><br/></div>"});
		if(window.confirm("确定按上述条件把数据集导成Excel？")){
			$.ajax({
			url : 'findvehiclechangeexcle.action',
			type : 'post',
			data:{
				"stime" : $("#vehiclechangestime").val(),
				"etime" : $("#vehiclechangeetime").val(),
				"vehicle_no" : $("#change_car").val()	
			},
			dataType: 'json',
			timeout : 180000,
			success:function(json){
				window.location.href=json.action;
				$.unblockUI();
			},
			error:function(){
				
			}
			});
		}
	}
	$("#czqcwsxcl").html("车牌号变更申请");
</script>
<style>
</style>
<script src="js/jquery.blockUI.js"></script>
<link rel="stylesheet" type="text/css" media="screen" href="css/bootstrap.min.css">
