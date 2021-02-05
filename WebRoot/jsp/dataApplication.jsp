<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
contentType="text/html; charset=UTF-8" %>
<!-- widget grid -->
<div class="row" style="height:600px;overflow: scroll">
    <input type="hidden" id="um1" value="${username }"/>
    <input type="hidden" id="realname" value="${realname}"/>

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
									<th>车辆颜色</th>
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
<div id="vehicle_application_dialog" title="">
    <form id="checkout-form" name="application" class="smart-form" novalidate="novalidate">
        <fieldset>
            <%--<input name="authenticity_token" type="hidden">--%>
            <%--<input name="authenticity_token" type="hidden">--%>
            <table style="width:100%;border-collapse:separate; border-spacing:10px;">
                <tr style="margin-top: 3px;">
                    <td>
                        <label style="width: 60px;">车牌号码</label><input type="text" id="vehicleNo">
                    </td>
                    <td>
                        <label style="width: 60px;">公司</label><input type="text"  id="companyName">
                    </td>
                </tr>
                <tr>
                    <td>
                        <label style="width: 60px;">车型</label>
                        <select style="width:160px;" id="vehicleType"></select>
                    </td>
                    <td>
                        <label style="width: 60px;">燃料类型</label>
                        <select style="width:160px;" id="fuelType"></select>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label style="width: 60px;">终端类型</label>
                        <select style="width:160px;" id="terminalType"></select>
                    </td>
                    <td>
                        <label style="width: 60px;">终端型号</label><input type="text" id="terminalModel">
                    </td>
                </tr>
                <tr>
                    <td>
                        <label style="width: 60px;">车主姓名</label><input type="text" id="ownerName">
                    </td>
                    <td>
                        <label style="width: 60px;">电话号码</label><input type="text" id="ownerPhone">
                    </td>

                </tr>
                <tr>
                    <td>
                        <label style="width: 60px;">白班姓名</label><input type="text" id="dayPerson">
                    </td>
                    <td>
                        <label style="width: 60px;">夜班姓名</label><input type="text" id="nightPerson">
                    </td>

                </tr>
                <tr>
                    <td>
                        <label style="width: 60px;">白班电话</label><input type="text" id="dayPhone">
                    </td>
                    <td>
                        <label style="width: 60px;">夜班电话</label><input type="text" id="nightPhone">
                    </td>
                </tr>
                <tr>
                    <td>
                        <label style="width: 60px;">车辆颜色</label><input type="text" id="vehicleColor">
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
		width : 600,
		resizable : false,
		modal : true,
	});
	//增加弹框
	function addVehicle(){
		$("#vehicleNo").val("");
		$("#companyName").val($("#realname").val());
		$("#vehicleType").val("");
		$("#fuelType").val("");
		$("#terminalType").val("");
		$("#terminalModel").val("");
		$("#ownerName").val("");
		$("#ownerPhone").val("");
		$("#dayPerson").val("");
		$("#nightPerson").val("");
		$("#dayPhone").val("");
		$("#nightPhone").val("");
		$("#vehicleColor").val("");
		$('#vehicle_application_dialog').dialog({
			title : "添加",
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
			if(dataapplication[i].id==id){
				$("#vehicleNo").val(dataapplication[i].vehicleNo);
				$("#companyName").val(dataapplication[i].companyName);
				$("#vehicleType").val(dataapplication[i].vehicleType);
				$("#fuelType").val(dataapplication[i].fuelType);
				$("#terminalType").val(dataapplication[i].terminalType);
				$("#terminalModel").val(dataapplication[i].terminalModel);
				$("#ownerName").val(dataapplication[i].ownerName);
				$("#ownerPhone").val(dataapplication[i].ownerPhone);
				$("#dayPerson").val(dataapplication[i].dayPerson);
				$("#nightPerson").val(dataapplication[i].nightPerson);
				$("#dayPhone").val(dataapplication[i].dayPhone);
				$("#nightPhone").val(dataapplication[i].nightPhone);
				$("#vehicleColor").val(dataapplication[i].vehicleColor);
			}
		}
		$('#vehicle_application_dialog').dialog({
			title : "修改",
			buttons : [{
				html : "修改",
				"class" : "btn btn-danger",
				click : function() {
					editdataapplication(id);
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
		if($("#vehicleNo").val()==""){
			alert("请填写车牌号码！！");
			return ;
		}
		if($("#companyName").val()==""){
			alert("请填写公司！！");
			return ;
		}
		if($("#ownerName").val()==""){
			alert("请填写车主姓名！！");
			return ;
		}
		if($("#ownerPhone").val()==""){
			alert("请填写车主电话！！");
			return ;
		}
		$.ajax({
			url : 'adddataapplication.action',
			type : 'post',
			data:{
				"vehicleNo" : $("#vehicleNo").val(),
                "companyName" : $("#companyName").val(),
				"vehicleType" : $("#vehicleType").val(),
				"fuelType" : $("#fuelType").val(),
				"terminalType" : $("#terminalType").val(),
				"terminalModel" : $("#terminalModel").val(),
				"ownerName" : $("#ownerName").val(),
				"ownerPhone" : $("#ownerPhone").val(),
				"dayPerson" : $("#dayPerson").val(),
				"nightPerson" : $("#nightPerson").val(),
				"dayPhone" : $("#dayPhone").val(),
				"nightPhone" : $("#nightPhone").val(),
                "vehicleColor" : $("#vehicleColor").val(),
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
        if($("#vehicleNo").val()==""){
            alert("请填写车牌号码！！");
            return ;
        }
        if($("#companyName").val()==""){
            alert("请填写公司！！");
            return ;
        }
        if($("#ownerName").val()==""){
            alert("请填写车主姓名！！");
            return ;
        }
        if($("#ownerPhone").val()==""){
            alert("请填写车主电话！！");
            return ;
        }
		$.ajax({
			url : 'editdataapplication.action',
			type : 'post',
			data:{
                "vehicleNo" : $("#vehicleNo").val(),
                "companyName" : $("#companyName").val(),
                "vehicleType" : $("#vehicleType").val(),
                "fuelType" : $("#fuelType").val(),
                "terminalType" : $("#terminalType").val(),
                "terminalModel" : $("#terminalModel").val(),
                "ownerName" : $("#ownerName").val(),
                "ownerPhone" : $("#ownerPhone").val(),
                "dayPerson" : $("#dayPerson").val(),
                "nightPerson" : $("#nightPerson").val(),
                "dayPhone" : $("#dayPhone").val(),
                "nightPhone" : $("#nightPhone").val(),
                "vehicleColor" : $("#vehicleColor").val(),
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
        findfueltype();
        findterminaltype();
        findvehicletype();
		setTimeout("finddataapplication()", 500);
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
				"vehicleNo" : $("#hiscar").val(),
				"companyName" : $("#app_comp").val(),
			},
			dataType: 'json',
			timeout : 180000,
			success:function(json){
				dataapplication = json.list12;
				console.log("chaxun=",dataapplication)
				var tab="";
				var i=0;
				if(dataapplication!=null){
					for(i=0;i<dataapplication.length;i++){
                        var obj = '"'+dataapplication[i].id+'"';
						tab=tab+"<tr><td nowrap='nowrap'>"+(i+1)+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+dataapplication[i].vehicleNo+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+dataapplication[i].companyName+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+dataapplication[i].vehicleType+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+dataapplication[i].vehicleColor+"</td>";
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
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+dataapplication[i].auditStatus+"</td>";
						tab=tab+"<td><input type='button' value='改' onclick='editVehicle("+obj+")'>&nbsp;<input type='button' value='删' onclick='deletedataapplication("+obj+")'></td></tr>";
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
    //燃料下拉栏
    function findfueltype(){
        $.ajax( {
            url : 'findfueltype.action',
            type : 'post',
            data : {},
            dataType : 'json',
            timeout : 180000,
            success : function(json) {
                var cust=json.list9;
                var tab="";
                for ( var i = 0; i < cust.length; i++) {
                    tab+="<option value='"+cust[i].fuelType+"'>"+cust[i].fuelType+"</option>";
                }
                $("#fuelType").append(tab);
            },
            error : function() {

            }
        });
    }

    //终端类型下拉栏
    function findterminaltype(){
        $.ajax( {
            url : 'findterminaltype.action',
            type : 'post',
            data : {},
            dataType : 'json',
            timeout : 180000,
            success : function(json) {
                var cust=json.list10;
                var tab="";
                var tab2="";
                for ( var i = 0; i < cust.length; i++) {
                    tab+="<option value='"+cust[i].terminalModel+"'>"+cust[i].terminalModel+"</option>";
                }
                $("#terminalType").append(tab);
            },
            error : function() {

            }
        });
    }
    //车型下拉栏
    function findvehicletype(){
        $.ajax( {
            url : 'findvehicletype.action',
            type : 'post',
            data : {},
            dataType : 'json',
            timeout : 180000,
            success : function(json) {
                var cust=json.list11;
                var tab="";
                for ( var i = 0; i < cust.length; i++) {
                    tab+="<option value='"+cust[i].vehicleType+"'>"+cust[i].vehicleType+"</option>";
                }
                $("#vehicleType").append(tab);
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
