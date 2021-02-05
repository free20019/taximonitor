<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
contentType="text/html; charset=UTF-8" %>
<!-- widget grid -->
<div class="row" style="height:600px;overflow: scroll">
<input type="hidden" id="um1" value="${username }"/>
	<!-- row -->
<table border="1" style="width: 102%;">
	<tr style="height: 100%;overflow: auto;">
		<td>
			<div class="jarviswidget jarviswidget-color-darken" id="wid-id-2" data-widget-editbutton="false">
			<header style = "text-align:left;">
					<span class="widget-icon"> <i class="fa fa-table"></i> </span>
					<h2 id="clzrsq">数据接入申请</h2>
				</header>
				<!-- widget div-->
				<div>
					<div class="jarviswidget-editbox">
					</div>
					<div class="widget-body no-padding">
					<form action="" id="msg-form" class="smart-form" novalidate="novalidate" >
								<section style="width:180px;float:left;">
										<label class="input"> <i class="icon-append fa fa-calendar"></i>
											<input id="dataapplicationstime" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})()" placeholder="--请选择开始时间--"/>
										</label>
								</section>
								<section style="width:180px;float:left;">
										<label class="input"> <i class="icon-append fa fa-calendar"></i>
											<input id="dataapplicationetime" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})()" placeholder="--请选择结束时间--"/>
										</label>
								</section>
								<section style=" width:130px;float:left">
									<label class="input">
										<input id="hiscar" type="text" list="applicationcar" placeholder="--车牌号码--" >
										<datalist id="applicationcar" >

										</datalist>
									</label>
								</section>
								<section style=" width:180px;float:left">
									<label class="input">
										<input id="app_comp" type="text" list="applicationcomp" placeholder="--公司--" >
										<datalist id="applicationcomp" >

										</datalist>
									</label>
								</section>
								<a href="javascript:void(0);"  class="btn btn-primary"  onclick="finddataapplication();">查   询</a>
								<a href="javascript:void(1);"  class="btn btn-primary"  onclick="finddataapplicationexcle();">导   出</a>
								<a href="javascript:void(2);" id="vehicle_add" class="btn btn-primary" onclick="addVehicle();">添加</a>

								<span id="dataapplicationdaochu"></span>
							<section style="width:90px;text-align: center;height:32px;line-height:32px;">
										<label class="span">
											 <span id="dataapplicationts"></span>
										</label>
									</section>
						</form>
						<table id="dataapplicationtable" style="float:left;"  class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
								   <th>序号</th>
			                       <th>车号</th>
			                       <th>公司</th>
			                       <th>车型</th>
			                       <th>燃料类型</th>
			                       <th>终端类型</th>
			                       <th>终端型号</th>
			                       <th>车主姓名</th>
			                       <th>电话号码</th>
			                       <th>白班姓名</th>
			                       <th>夜班姓名</th>
			                       <th>白班电话</th>
			                       <th>夜班电话</th>
			                       <th>申请时间</th>
			                       <th>审核时间</th>
			                       <th>审核人员</th>
			                       <th>审核原因</th>
			                       <th>车辆颜色</th>
			                       <th>审核状态</th>
			                       <th>操作</th>
								</tr>
							</thead>
							<tbody id="dataapplicationtbody">
							</tbody>
						</table>

					</div>
				</div>
			</div>
		</td>
	</tr>
</table>
</div>
<%--<div id="vehicle_application_dialog" title="">
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
</div>--%>
<div id="vehicle_application_dialog" title="">
    <form id="checkout-form" name="application" class="smart-form" novalidate="novalidate" >
        <fieldset>
            <input name="authenticity_token" type="hidden">
            <input name="authenticity_token" type="hidden">
            <table style="width:100%;border-collapse:separate; border-spacing:10px;">
                <tr style="margin-top: 3px;">
                    <td>
                        <label>车牌号码</label><input type="text" name="cphm" id="wxglcphms" class="wxglclear">
                    </td>
                    <td>
                        <label>联系人&nbsp;&nbsp;&nbsp;&nbsp;</label><input type="text" name="lxr" id="wxgllxr" class="wxglclear">
                    </td>
                </tr>
                <tr>
                    <td>
                        <label>联系电话</label><input type="text" name="lxdh" id="wxgllxdh" class="wxglclear">
                    </td>
                    <td>
                        <label>故障现象</label>
                        <select style="width:160px;" id="wxglgzxx" name="gzxx"></select>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label>故障描述</label>
                        <select style="width:160px;" id="wxglgzms" name="gzms"></select>
                    </td>
                    <td>
                        <label>维修类型</label>
                        <select style="width:160px;" id="wxglwxlx" name="wxlx"></select>
                        <!-- 							<select> -->
                        <!-- 								<option value="一般维修">一般维修</option> -->
                        <!-- 								<option value="不能现场处理">不能现场处理</option> -->
                        <!-- 								<option value="简单维修">简单维修</option> -->
                        <!-- 								<option value="自查维修">自查维修</option> -->
                        <!-- 								<option value="通知维修">通知维修</option> -->
                        <!-- 							</select> -->
                    </td>
                </tr>
                <tr>
                    <td>
                        <label>当前故障</label><input type="text" name="dqgz" id="wxgldqgz" class="wxglclear">
                    </td>
                    <td>
                        <label>维修费用</label><input type="text" name="wxfy" id="wxglwxfy" class="wxglclear">
                    </td>
                </tr>
                <tr>
                    <td>
                        <label>维修人员</label><input type="text" name="wxry" id="wxglwxry" class="wxglclear">
                    </td>
                    <td>
                        <label>维修地点</label>
                        <select style="width:160px;" id="wxglwxdd" name="wxdd"></select>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <label>维修内容</label><input type="text" name="wxnr" class="wxglclear" style="width:75%" id="wxglwxnr">
                    </td>
                </tr>
                <tr>
                    <td>
                        <label>送修时间</label><input type="text" name="sxsj" class="wxglclear" id="wxglsxsj" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss', minDate: '%y-%M-%d 00:00:00',maxDate:'%y-%M-%d 23:59:59'});">
                    </td>
                    <td>
                        <label>完修时间</label><input type="text" class="wxsj" name="yemian" id="wxglwxsj" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss', minDate: '%y-%M-%d 00:00:00',maxDate:'%y-%M-%d 23:59:59'});">
                    </td>
                </tr>
                <tr>
                    <td>
                        <label>客户满意度</label>
                        <select style="width:160px;" id="wxglmyd" name="khmyd">
                            <option value="满意">满意</option>
                            <option value="不满意">不满意</option>
                            <option value="投诉">投诉</option>
                            <option value="表扬">表扬</option>
                        </select>
                    </td>
                </tr>
            </table>
        </fieldset>
    </form>
</div>
<script type="text/javascript">

	// DO NOT REMOVE : GLOBAL FUNCTIONS!
	pageSetUp();
	$(document).ready(function(){
		var mydate = new Date();
		$("#dataapplicationstime").val(setformatnewlc(mydate));
		$("#dataapplicationetime").val(setformatnewlc(mydate));
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
					adddataapplication();
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
		for(i=0;i<dataapplication.length;i++){
			if(dataapplication[i].comp_id==id){
				$("#app_vehicle").val(dataapplication[i].vehi_no+"("+dataapplication[i].comp_name+")");
				$("#noareaselect").val(dataapplication[i].ba_name);
				$("#applicationtime").val(dataapplication[i].time);
			}
		}
		$('#vehicle_application_dialog').dialog({
			title : "车辆申请修改",
			buttons : [{
				html : "修改",
				"class" : "btn btn-danger",
				click : function() {
					editdataapplication(id);
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
	function adddataapplication(){
		if($("#app_vehicle").val()==""){
			alert("请输入车牌号码");
			return ;
		}
		if($("#noareaselect").val()==""){
			alert("请输入公司名称");
			return ;
		}
		$.ajax({
			url : 'adddataapplication.action',
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
					finddataapplication();
				}
			},
			error:function(){
			}
		});
	}
	//修改请求
	function editdataapplication(id){
		if($("#app_vehicle").val()==""){
			alert("请输入车牌号码");
			return ;
		}
		if($("#noareaselect").val()==""){
			alert("请输入公司名称");
			return ;
		}
		$.ajax({
			url : 'editdataapplication.action',
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
					finddataapplication();
				}
			},
			error:function(){
			}
		});
	}
	//删除请求
	function deletedataapplication(id){
		if(window.confirm("确定删除该数据？")){
			$.ajax({
				url : 'deletedataapplication.action',
				type : 'post',
				data:{
					"id" : id
				},
				dataType: 'json',
				timeout : 180000,
				success:function(json){
					alert(json.info);
					finddataapplication();
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
		// setTimeout("finddataapplication()", 500);
	}

	var dataapplication=null;
	function finddataapplication(){
		$.blockUI({message:"<div style='padding:5px;'><h3><font color='blue'>数据查询中,请稍候...</font></h3><img src='img/ajax-loader.gif' border='0'/><br/><br/></div>"});
		$.ajax({
			url : 'finddataapplication.action',
			type : 'post',
			data:{
				"stime" : $("#dataapplicationstime").val(),
				"etime" : $("#dataapplicationetime").val(),
				"vehicle_no" : $("#hiscar").val(),
				"type" : "0"
			},
			dataType: 'json',
			timeout : 180000,
			success:function(json){
				dataapplication = json.list;
				console.log("chaxun=",dataapplication)
				var tab="";
				var i=0;
				if(dataapplication!=null){
					for(i=0;i<dataapplication.length;i++){
						tab=tab+"<tr><td nowrap='nowrap'>"+(i+1)+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+dataapplication[i].vehicleNo+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+dataapplication[i].companyName+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+dataapplication[i].vehicleType+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+dataapplication[i].fuelType+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+dataapplication[i].terminalType+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+dataapplication[i].terminalModel+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+dataapplication[i].ownerName+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+dataapplication[i].ownerPhone+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+dataapplication[i].dayPerson+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+dataapplication[i].nightPerson+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+dataapplication[i].dayPhone+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+dataapplication[i].nightPhone+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+dataapplication[i].applicationDate+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+dataapplication[i].auditDate+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+dataapplication[i].auditPerson+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+dataapplication[i].auditReason+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+dataapplication[i].vehicleColor+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+dataapplication[i].auditStatus+"</td>";
						tab=tab+"<td><input type='button' value='改' onclick='editVehicle("+dataapplication[i].id+")'>&nbsp;<input type='button' value='删' onclick='deletedataapplication("+dataapplication[i].id+")'></td></tr>";
					}
						$("#dataapplicationts").html("总条数:"+i+"条");
						$("#dataapplicationtbody").html(tab);
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
					$("#applicationcomp").html(tab);
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
				$("#applicationcar").html(tab);
			},
			error : function() {

			}
		});
	}
	//导出excle
	function finddataapplicationexcle(){
		$.blockUI({message:"<div style='padding:5px;'><h3><font color='blue'>数据查询中,请稍候...</font></h3><img src='img/ajax-loader.gif' border='0'/><br/><br/></div>"});
		if(window.confirm("确定按上述条件把数据集导成Excel？")){
			$.ajax({
			url : 'finddataapplicationexcle.action',
			type : 'post',
			data:{
				"stime" : $("#dataapplicationstime").val(),
				"etime" : $("#dataapplicationetime").val(),
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
	$("#clzrsq").html("数据接入申请");
</script>
<style>
</style>
<script src="js/jquery.blockUI.js"></script>
<link rel="stylesheet" type="text/css" media="screen" href="css/bootstrap.min.css">
