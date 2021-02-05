<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
contentType="text/html; charset=UTF-8" %>
<!-- widget grid -->
<div class="row" style="height:600px;overflow: scroll">
<input type="hidden" id="um10" value="${username }"/>
	<!-- row -->
<table border="1" style="width: 102%;">
	<tr style="height: ;overflow: auto;">
		<td>
			<div class="jarviswidget jarviswidget-color-darken" id="wid-id-2" data-widget-editbutton="false">
			<header style = "text-align:left;">
					<span class="widget-icon"> <i class="fa fa-table"></i> </span>
					<h2 id="gdcx">工单查询</h2>
				</header>
				<!-- widget div-->
				<div>
					<div class="jarviswidget-editbox">
					</div>
					<div class="widget-body no-padding">
					<form action="" id="msg-form" class="smart-form" novalidate="novalidate" >
								<section style="width:180px;float:left;">
										<label class="input"> <i class="icon-append fa fa-calendar"></i>
											<input id="repairorderstime" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})()" placeholder="--请选择开始时间--"/>
										</label>
								</section>
								<section style="width:180px;float:left;">
										<label class="input"> <i class="icon-append fa fa-calendar"></i>
											<input id="repairorderetime" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})()" placeholder="--请选择结束时间--"/>
										</label>
								</section>
								<section style=" width:130px;float:left">
									<label class="input">
										<input id="repaircar" type="text" list="repairordercar" placeholder="--车牌号码--" >
										<datalist id="repairordercar" >
										
										</datalist> 
									</label>
								</section>
								<a href="javascript:void(0);"  class="btn btn-primary"  onclick="findrepairorder();">查   询</a>
								<a href="javascript:void(1);"  class="btn btn-primary"  onclick="findrepairorderexcle();">导   出</a>								
								<span id="repairorderdaochu"></span>
							<section style="width:90px;text-align: center;height:32px;line-height:32px;">
										<label class="span"> 
											 <span id="repairorderts"></span>
										</label>
									</section>
						</form>
						<table id="repairordertable" style="float:left;"  class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
								   <th>序号</th>
			                       <th>公司名称</th>
			                       <th>车牌号码</th>
			                       <th>区域</th>
			                       <th>故障类型</th>
			                       <th>故障时间</th>
			                       <th>第一次催告时间</th>
			                       <th>第二次催告时间</th>
			                       <th>第三次催告时间</th>
			                       <th>反馈时间</th>
			                       <th>是否维修</th>
			                       <th>维修时间</th>
			                       <th>维修结果</th>
			                       <th>备注</th>
								</tr>
							</thead>
							<tbody id="repairordertbody">
							</tbody>
						</table>

					</div>
				</div>
			</div>
		</td>
	</tr>
</table>
</div>
<script type="text/javascript">
	
	// DO NOT REMOVE : GLOBAL FUNCTIONS!
	pageSetUp();
	$(document).ready(function(){
		var mydate = new Date();
		$("#repairorderstime").val(setformatnewlc(mydate));
		$("#repairorderetime").val(setformatnewlc(mydate));
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
	//页面刷新加载
	find1();
	function find1(){
		findvehicle();
		setTimeout("findrepairorder()", 500);
	}

	var repairorder=null;	
	function findrepairorder(){
		$.blockUI({message:"<div style='padding:5px;'><h3><font color='blue'>数据查询中,请稍候...</font></h3><img src='img/ajax-loader.gif' border='0'/><br/><br/></div>"});
		$.ajax({
			url : 'findrepairorder.action',
			type : 'post',
			data:{
				"stime" : $("#repairorderstime").val(),
				"etime" : $("#repairorderetime").val(),
				"vehicle_no" : $("#repaircar").val()
			},
			dataType: 'json',
			timeout : 180000,
			success:function(json){
				repairorder = json.list5;
				console.log("chaxun=",repairorder)
				var tab="";
				var i=0;
				if(repairorder!=null){
					for(i=0;i<repairorder.length;i++){		
						tab=tab+"<tr><td nowrap='nowrap'>"+(i+1)+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+repairorder[i].comp_name+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+repairorder[i].vehicle_no+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+repairorder[i].area_name+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+repairorder[i].fault_type+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+repairorder[i].fault_time+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+repairorder[i].once_time+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+repairorder[i].twice_time+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+repairorder[i].third_time+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+repairorder[i].feedback_time+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+repairorder[i].is_repair+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+repairorder[i].repair_time+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+repairorder[i].repair_result+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+repairorder[i].description+"</td>";
					}
						$("#repairorderts").html("总条数:"+i+"条");
						$("#repairordertbody").html(tab);
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
				for ( var i = 0; i < cust.length; i++) {
					tab+="<option value='"+cust[i].vehi_no+"'></option>";
				}
				$("#repairordercar").html(tab);
			},
			error : function() {
	
			}
		});
	}
	//导出excle
	function findrepairorderexcle(){
		$.blockUI({message:"<div style='padding:5px;'><h3><font color='blue'>数据查询中,请稍候...</font></h3><img src='img/ajax-loader.gif' border='0'/><br/><br/></div>"});
		if(window.confirm("确定按上述条件把数据集导成Excel？")){
			$.ajax({
			url : 'findrepairorderexcle.action',
			type : 'post',
			data:{
				"stime" : $("#repairorderstime").val(),
				"etime" : $("#repairorderetime").val(),
				"vehicle_no" : $("#repaircar").val()
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
	$("#gdcx").html("工单查询");
</script>
<style>
</style>
<script src="js/jquery.blockUI.js"></script>
<link rel="stylesheet" type="text/css" media="screen" href="css/bootstrap.min.css">
