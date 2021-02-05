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
					<h2 id="czqcwsxcl">车辆报停申请</h2>
				</header>
				<!-- widget div-->
				<div>
					<div class="jarviswidget-editbox">
					</div>
					<div class="widget-body no-padding">
					<form action="" id="msg-form" class="smart-form" novalidate="novalidate" >
								<section style="width:180px;float:left;">
										<label class="input"> <i class="icon-append fa fa-calendar"></i>
											<input id="vehiclestopstime" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})()" placeholder="--请选择开始时间--"/>
										</label>
								</section>
								<section style="width:180px;float:left;">
										<label class="input"> <i class="icon-append fa fa-calendar"></i>
											<input id="vehiclestopetime" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})()" placeholder="--请选择结束时间--"/>
										</label>
								</section>
								<section style=" width:130px;float:left">
									<label class="input">
										<input id="stop_car" type="text" list="stopcar" placeholder="--车牌号码--" >
										<datalist id="stopcar" >
										
										</datalist> 
									</label>
								</section>
								<a href="javascript:void(0);"  class="btn btn-primary"  onclick="findvehiclestop();">查   询</a>
								<a href="javascript:void(1);"  class="btn btn-primary"  onclick="findvehiclestopexcle();">导   出</a>
								<a href="javascript:void(2);" id="vehicle_add" class="btn btn-primary" onclick="addVehicle_stop();">添加</a>
								
								<span id="vehiclestopdaochu"></span>
							<section style="width:90px;text-align: center;height:32px;line-height:32px;">
										<label class="span"> 
											 <span id="vehiclestopts"></span>
										</label>
									</section>
						</form>
						<table id="vehiclestoptable" style="float:left;"  class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
								   <th>序号</th>
					             	<th>公司名称</th>
			                       <th>车牌号码</th>
			                       <th>停报时间</th>
			                       <th>停报原因</th>
			                       <th>上报日期</th>
			                       <th>审核状态</th>
			                       <th>审核时间</th>
			                       <th>审核原因</th>
			                       <th>操作</th>
								</tr>
							</thead>
							<tbody id="vehiclestoptbody">
							</tbody>
						</table>

					</div>
				</div>
			</div>
		</td>
	</tr>
</table>
</div>
<div id="vehicle_stop_dialog" title="">
	<p>
	<form action="" id="checkout-form" class="smart-form" novalidate="novalidate" >
	<table border="0">
	<tr>
		<td valign="top">车牌号码:&nbsp;&nbsp;&nbsp;</td>
		<td >
			<section style=" width:250px;float:left">
				<label class="input">
					<input id="stop_vehicle" type="text" list="stopvehicle" placeholder="--车牌号码(公司)--" >
					<datalist id="stopvehicle" >
					
					</datalist> 
				</label>
			</section>
		</td>
	</tr>
	<tr>
		<td valign="top">停报原因:&nbsp;&nbsp;&nbsp;</td>
		<td>
			<textarea style="width:250px;float:left" rows="5" cols="40" id="stop_reason"></textarea>
		</td>
	</tr>
	<tr>
		<td valign="top">停报时间:&nbsp;&nbsp;&nbsp;</td>
		<td>
			<section style="width:250px;float:left;">
					<label class="input"> <i class="icon-append fa fa-calendar"></i>
						<input id="stop_time" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})()" placeholder="--请选择时间--"/>
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
		$("#vehiclestopstime").val(setformatnewlc(mydate));
		$("#vehiclestopetime").val(setformatnewlc(mydate));
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
	$('#vehicle_stop_dialog').dialog({
		autoOpen : false,
		width : 400,
		resizable : false,
		modal : true,
	});
	//增加弹框
	function addVehicle_stop(){
		$("#stop_vehicle").val("");
		$("#stop_reason").val("");
		$("#stop_time").val("");
		$('#vehicle_stop_dialog').dialog({
			title : "车辆停报添加",
			buttons : [{
				html : "添加",
				"class" : "btn btn-danger",
				click : function() {
					addvehiclestop();
				}
			}, {
				html : "取消",
				"class" : "btn btn-default",
				click : function() {
					$(this).dialog("close");
				}
			}]
		});
		$('#vehicle_stop_dialog').dialog('open');
	}
	//修改弹框
	function editVehicle_stop(id){
		for(i=0;i<vehiclestop.length;i++){
			if(vehiclestop[i].comp_id==id){
				$("#stop_vehicle").val(vehiclestop[i].vehi_no+"("+vehiclestop[i].comp_name+")");
				$("#stop_reason").val(vehiclestop[i].ba_name);
				$("#stop_time").val(vehiclestop[i].own_name);
			}
		}
		$('#vehicle_stop_dialog').dialog({
			title : "车辆停报修改",
			buttons : [{
				html : "修改",
				"class" : "btn btn-danger",
				click : function() {
					editvehiclestop(id);
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
		$('#vehicle_stop_dialog').dialog('open');
	}
	//添加请求
	function addvehiclestop(){
		if($("#stop_vehicle").val()==""){
			alert("请输入车牌号码");
			return ;
		}
		if($("#stop_reason").val()==""){
			alert("请输入停报原因");
			return ;
		}
		$.ajax({
			url : 'addvehiclestop.action',
			type : 'post',
			data:{
				"vehicle_no" : $("#stop_vehicle").val(),
				"reason" : $("#stop_reason").val(),
				"time" : $("#stop_time").val()
			},
			dataType: 'json',
			timeout : 180000,
			success:function(json){
				alert(json.info);
				if(json.info=="添加成功"){					
					$("#vehicle_stop_dialog").dialog("close");
					findvehiclestop();
				}
			},
			error:function(){
			}		
		});
	}
	//修改请求
	function editvehiclestop(id){
		if($("#stop_vehicle").val()==""){
			alert("请输入车牌号码");
			return ;
		}
		if($("#stop_reason").val()==""){
			alert("请输入停报原因");
			return ;
		}
		$.ajax({
			url : 'editvehiclestop.action',
			type : 'post',
			data:{
				"vehicle_no" : $("#stop_vehicle").val(),
				"reason" : $("#stop_reason").val(),
				"time" : $("#stop_time").val(),
				"id" : id
			},
			dataType: 'json',
			timeout : 180000,
			success:function(json){
				alert(json.info);
				if(json.info=="修改成功"){					
					$("#vehicle_stop_dialog").dialog("close");
					findvehiclestop();
				}
			},
			error:function(){
			}		
		});
	}
	//删除请求
	function deletevehiclestop(id){
		if(window.confirm("确定删除该数据？")){
			$.ajax({
				url : 'deletevehiclestop.action', 
				type : 'post',
				data:{
					"id" : id
				},
				dataType: 'json',
				timeout : 180000,
				success:function(json){
					alert(json.info);
					findvehiclestop();
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
		setTimeout("findvehiclestop()", 500);
	}

	var vehiclestop=null;	
	function findvehiclestop(){
		$.blockUI({message:"<div style='padding:5px;'><h3><font color='blue'>数据查询中,请稍候...</font></h3><img src='img/ajax-loader.gif' border='0'/><br/><br/></div>"});
		$.ajax({
			url : 'findvehiclestop.action',
			type : 'post',
			data:{
				"stime" : $("#vehiclestopstime").val(),
				"etime" : $("#vehiclestopetime").val(),
				"vehicle_no" : $("#stop_car").val()
			},
			dataType: 'json',
			timeout : 180000,
			success:function(json){
				vehiclestop = json.list2;
				console.log("chaxun=",vehiclestop)
				var tab="";
				var i=0;
				if(vehiclestop!=null){
					for(i=0;i<vehiclestop.length;i++){
						tab=tab+"<tr><td nowrap='nowrap'>"+(i+1)+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+vehiclestop[i].comp_name+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+vehiclestop[i].vehi_no+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+vehiclestop[i].own_name+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+vehiclestop[i].ba_name+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+vehiclestop[i].time+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+vehiclestop[i].vehi_sim+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+vehiclestop[i].own_tel+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+vehiclestop[i].run_times+"</td>";
						tab=tab+"<td><input type='button' value='改' onclick='editVehicle_stop("+vehiclestop[i].comp_id+")'>&nbsp;<input type='button' value='删' onclick='deletevehiclestop("+vehiclestop[i].comp_id+")'></td></tr>";
					}
						$("#vehiclestopts").html("总条数:"+i+"条");
						$("#vehiclestoptbody").html(tab);
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
					tab2+="<option value='"+cust[i].vehi_no+"("+cust[i].comp_name+")'></option>";
				}
				$("#stopcar").html(tab);
				$("#stopvehicle").html(tab2);
			},
			error : function() {
	
			}
		});
	}
	//导出excle
	function findvehiclestopexcle(){
		$.blockUI({message:"<div style='padding:5px;'><h3><font color='blue'>数据查询中,请稍候...</font></h3><img src='img/ajax-loader.gif' border='0'/><br/><br/></div>"});
		if(window.confirm("确定按上述条件把数据集导成Excel？")){
			$.ajax({
			url : 'findvehiclestopexcle.action',
			type : 'post',
			data:{
				"stime" : $("#vehiclestopstime").val(),
				"etime" : $("#vehiclestopetime").val(),
				"vehicle_no" : $("#stop_car").val()	
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
	$("#czqcwsxcl").html("车辆报停申请");
</script>
<style>
</style>
<script src="js/jquery.blockUI.js"></script>
<link rel="stylesheet" type="text/css" media="screen" href="css/bootstrap.min.css">
