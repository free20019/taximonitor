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
					<h2 id="clzrsq">车辆转入申请</h2>
				</header>
				<!-- widget div-->
				<div>
					<div class="jarviswidget-editbox">
					</div>
					<div class="widget-body no-padding">
					<form action="" id="msg-form" class="smart-form" novalidate="novalidate" >
								<section style="width:180px;float:left;">
										<label class="input"> <i class="icon-append fa fa-calendar"></i>
											<input id="vehicleapplicationstime" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})()" placeholder="--请选择开始时间--"/>
										</label>
								</section>
								<section style="width:180px;float:left;">
										<label class="input"> <i class="icon-append fa fa-calendar"></i>
											<input id="vehicleapplicationetime" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})()" placeholder="--请选择结束时间--"/>
										</label>
								</section>
								<section style=" width:130px;float:left">
									<label class="input">
										<input id="hiscar" type="text" list="applicationcar" placeholder="--车牌号码--" >
										<datalist id="applicationcar" >
										
										</datalist> 
									</label>
								</section>
								<a href="javascript:void(0);"  class="btn btn-primary"  onclick="findvehicleapplication();">查   询</a>
								<a href="javascript:void(1);"  class="btn btn-primary"  onclick="findvehicleapplicationexcle();">导   出</a>
								<a href="javascript:void(2);" id="vehicle_add" class="btn btn-primary" onclick="addVehicle();">添加</a>
								
								<span id="vehicleapplicationdaochu"></span>
							<section style="width:90px;text-align: center;height:32px;line-height:32px;">
										<label class="span"> 
											 <span id="vehicleapplicationts"></span>
										</label>
									</section>
						</form>
						<table id="vehicleapplicationtable" style="float:left;"  class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
								   <th>序号</th>
			                       <th>车牌号码</th>
			                       <th>原公司名称</th>
			                       <th>现公司名称</th>
			                       <th>转入时间</th>
			                       <th>申请时间</th>
			                       <th>审核状态</th>
			                       <th>审核时间</th>
			                       <th>审核原因</th>
			                       <th>操作</th>
								</tr>
							</thead>
							<tbody id="vehicleapplicationtbody">
							</tbody>
						</table>

					</div>
				</div>
			</div>
		</td>
	</tr>
</table>
</div>
<div id="vehicle_application_dialog" title="">
	<p>
	<form id="checkout-form" name="application" class="smart-form" novalidate="novalidate" >
	<table border="0">
	<tr>
		<td valign="top">车牌号码:&nbsp;&nbsp;&nbsp;</td>
		<td >
			<section style=" width:250px;float:left">
				<label class="input">
					<input id="app_vehicle" type="text" list="applicationvehicle" placeholder="--车牌号码(原公司)--" >
					<datalist id="applicationvehicle" >
					
					</datalist> 
				</label>
			</section>
		</td>
	</tr>
	<tr>
		<td valign="top">现公司名称:&nbsp;&nbsp;&nbsp;</td>
		<td>
			<section style="width:250px;float:left">
				<label class="input">
					<input id="noareaselect" type="text" list="noareaselectlist" placeholder="--选择公司--" >
					<datalist id="noareaselectlist" >
					
					</datalist> 
				</label>
			</section>
		</td>
	</tr>
	<tr>
		<td valign="top">转入时间:&nbsp;&nbsp;&nbsp;</td>
		<td>
			<section style="width:250px;float:left;">
					<label class="input"> <i class="icon-append fa fa-calendar"></i>
						<input id="applicationtime" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})()" placeholder="--请选择时间--"/>
					</label>
			</section>
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
		$("#vehicleapplicationstime").val(setformatnewlc(mydate));
		$("#vehicleapplicationetime").val(setformatnewlc(mydate));
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
	$('#vehicle_application_dialog').dialog({
		autoOpen : false,
		width : 400,
		resizable : false,
		modal : true,
	});
	//增加弹框
	function addVehicle(){
		$("#app_vehicle").val("");
		$("#noareaselect").val("");
		$("#applicationtime").val("");
		$('#vehicle_application_dialog').dialog({
			title : "车辆申请添加",
			buttons : [{
				html : "添加",
				"class" : "btn btn-danger",
				click : function() {
					addvehicleapplication();
				}
			}, {
				html : "取消",
				"class" : "btn btn-default",
				click : function() {
					$(this).dialog("close");
				}
			}]
		});
		$('#vehicle_application_dialog').dialog('open');
	}
	//修改弹框
	function editVehicle(id){
		for(i=0;i<vehicleapplication.length;i++){
			if(vehicleapplication[i].comp_id==id){
				$("#app_vehicle").val(vehicleapplication[i].vehi_no+"("+vehicleapplication[i].comp_name+")");
				$("#noareaselect").val(vehicleapplication[i].ba_name);
				$("#applicationtime").val(vehicleapplication[i].time);
			}
		}
		$('#vehicle_application_dialog').dialog({
			title : "车辆申请修改",
			buttons : [{
				html : "修改",
				"class" : "btn btn-danger",
				click : function() {
					editvehicleapplication(id);
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
		$('#vehicle_application_dialog').dialog('open');
	}
	//添加请求
	function addvehicleapplication(){
		if($("#app_vehicle").val()==""){
			alert("请输入车牌号码");
			return ;
		}
		if($("#noareaselect").val()==""){
			alert("请输入公司名称");
			return ;
		}
		$.ajax({
			url : 'addvehicleapplication.action',
			type : 'post',
			data:{
				"vehicle_no" : $("#app_vehicle").val(),
				"compname" : $("#noareaselect").val(),
				"time" : $("#applicationtime").val(),
				"type" : "0"
			},
			dataType: 'json',
			timeout : 180000,
			success:function(json){
				alert(json.info);
				if(json.info=="添加成功"){					
					$("#vehicle_application_dialog").dialog("close");
					findvehicleapplication();
				}
			},
			error:function(){
			}		
		});
	}
	//修改请求
	function editvehicleapplication(id){
		if($("#app_vehicle").val()==""){
			alert("请输入车牌号码");
			return ;
		}
		if($("#noareaselect").val()==""){
			alert("请输入公司名称");
			return ;
		}
		$.ajax({
			url : 'editvehicleapplication.action',
			type : 'post',
			data:{
				"vehicle_no" : $("#app_vehicle").val(),
				"compname" : $("#noareaselect").val(),
				"time" : $("#applicationtime").val(),
				"type" : "0",
				"id" : id
			},
			dataType: 'json',
			timeout : 180000,
			success:function(json){
				alert(json.info);
				if(json.info=="修改成功"){					
					$("#vehicle_application_dialog").dialog("close");
					findvehicleapplication();
				}
			},
			error:function(){
			}		
		});
	}
	//删除请求
	function deletevehicleapplication(id){
		if(window.confirm("确定删除该数据？")){
			$.ajax({
				url : 'deletevehicleapplication.action', 
				type : 'post',
				data:{
					"id" : id
				},
				dataType: 'json',
				timeout : 180000,
				success:function(json){
					alert(json.info);
					findvehicleapplication();
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
		findba();
		setTimeout("findvehicleapplication()", 500);
	}

	var vehicleapplication=null;	
	function findvehicleapplication(){
		$.blockUI({message:"<div style='padding:5px;'><h3><font color='blue'>数据查询中,请稍候...</font></h3><img src='img/ajax-loader.gif' border='0'/><br/><br/></div>"});
		$.ajax({
			url : 'findvehicleapplication.action',
			type : 'post',
			data:{
				"stime" : $("#vehicleapplicationstime").val(),
				"etime" : $("#vehicleapplicationetime").val(),
				"vehicle_no" : $("#hiscar").val(),
				"type" : "0"
			},
			dataType: 'json',
			timeout : 180000,
			success:function(json){
				vehicleapplication = json.list;
				console.log("chaxun=",vehicleapplication)
				var tab="";
				var i=0;
				if(vehicleapplication!=null){
					for(i=0;i<vehicleapplication.length;i++){
						tab=tab+"<tr><td nowrap='nowrap'>"+(i+1)+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+vehicleapplication[i].vehi_no+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+vehicleapplication[i].comp_name+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+vehicleapplication[i].ba_name+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+vehicleapplication[i].time+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+vehicleapplication[i].own_name+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+vehicleapplication[i].vehi_sim+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+vehicleapplication[i].own_tel+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+vehicleapplication[i].run_times+"</td>";
						tab=tab+"<td><input type='button' value='改' onclick='editVehicle("+vehicleapplication[i].comp_id+")'>&nbsp;<input type='button' value='删' onclick='deletevehicleapplication("+vehicleapplication[i].comp_id+")'></td></tr>";
					}
						$("#vehicleapplicationts").html("总条数:"+i+"条");
						$("#vehicleapplicationtbody").html(tab);
						$.unblockUI();
				}
			},
			error:function(){
				
			}		
		});
	}
	//公司下拉栏
	function findba(){
		$.ajax({
			url : 'findallcomp.action',
			type : 'post',
			data:{
			},
			dataType: 'json',
			timeout : 180000,
			success:function(json){
					var comp=json.list5;
					var tab="";
					for ( var i = 0; i < comp.length; i++) {
						tab+="<option value='"+comp[i].name+"'></option>";
					}
					$("#noareaselectlist").html(tab);
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
					tab2+="<option value='"+cust[i].vehi_no+"("+cust[i].comp_name+")'></option>";
				}
				$("#applicationcar").html(tab);
				$("#applicationvehicle").html(tab2);
			},
			error : function() {
	
			}
		});
	}
	//导出excle
	function findvehicleapplicationexcle(){
		$.blockUI({message:"<div style='padding:5px;'><h3><font color='blue'>数据查询中,请稍候...</font></h3><img src='img/ajax-loader.gif' border='0'/><br/><br/></div>"});
		if(window.confirm("确定按上述条件把数据集导成Excel？")){
			$.ajax({
			url : 'findvehicleapplicationexcle.action',
			type : 'post',
			data:{
				"stime" : $("#vehicleapplicationstime").val(),
				"etime" : $("#vehicleapplicationetime").val(),
				"vehicle_no" : $("#hiscar").val(),
				"type" : "0"
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
	$("#clzrsq").html("车辆转入申请");
</script>
<style>
</style>
<script src="js/jquery.blockUI.js"></script>
<link rel="stylesheet" type="text/css" media="screen" href="css/bootstrap.min.css">
