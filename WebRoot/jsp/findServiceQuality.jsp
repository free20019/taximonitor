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
					<h2 id="tscx">投诉查询</h2>
				</header>
				<!-- widget div-->
				<div>
					<div class="jarviswidget-editbox">
					</div>
					<div class="widget-body no-padding">
					<form action="" id="msg-form" class="smart-form" novalidate="novalidate" >
								<section style="width:180px;float:left;">
										<label class="input"> <i class="icon-append fa fa-calendar"></i>
											<input id="servicequalityyear" type="text" onClick="WdatePicker({dateFmt:'yyyy'})()" placeholder="--请选择开始时间--"/>
										</label>
								</section>
								<section class="col col-5" style="width: 200px;float:left;" >
									<label class="select">
										<select id="servicequalitycomp" class="select" >
											<option value="" >--选择公司--</option>
										</select> <i></i> </label>
								</section>
								<section style=" width:130px;float:left">
									<label class="input">
										<input id="level" type="text" list="servicequalitylevel" placeholder="--荣誉等级--" >
										<datalist id="servicequalitylevel" >
										
										</datalist> 
									</label>
								</section>
								<a href="javascript:void(0);"  class="btn btn-primary"  onclick="findservicequality();">查   询</a>
								<a href="javascript:void(1);"  class="btn btn-primary"  onclick="findservicequalityexcle();">导   出</a>								
								<span id="servicequalitydaochu"></span>
								<section style="width:90px;text-align: center;height:32px;line-height:32px;">
									<label class="span"> 
										 <span id="servicequalityts"></span>
									</label>
								</section>
						</form>
						<table id="servicequalitytable" style="float:left;"  class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
								   <th>序号</th>
			                       <th>公司名称</th>
			                       <th>年度</th>
			                       <th>信誉等级</th>
								</tr>
							</thead>
							<tbody id="servicequalitytbody">
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
// 	pageSetUp();
// 	$(document).ready(function(){
// 		var mydate = new Date();
// 		$("#servicequalityyear").val(setformatnewlc(mydate));
// 	});
// 	function setformatnewlc(obj){
// 		var y=(obj.getFullYear()).toString();
// 		var time=y; 
// 		return time;
// 	}
	//页面刷新加载
	find1();
	function find1(){
		findlevel();
		findba();
		setTimeout("findservicequality()", 500);
	}

	var servicequality=null;	
	function findservicequality(){
		$.blockUI({message:"<div style='padding:5px;'><h3><font color='blue'>数据查询中,请稍候...</font></h3><img src='img/ajax-loader.gif' border='0'/><br/><br/></div>"});
		$.ajax({
			url : 'findservicequality.action',
			type : 'post',
			data:{
				"year" : $("#servicequalityyear").val(),
				"compname" : $("#servicequalitycomp").val(),
				"level" : $("#level").val()
			},
			dataType: 'json',
			timeout : 180000,
			success:function(json){
				servicequality = json.list3;
				console.log("chaxun=",servicequality)
				var tab="";
				var i=0;
				if(servicequality!=null){
					for(i=0;i<servicequality.length;i++){		
						tab=tab+"<tr><td nowrap='nowrap'>"+(i+1)+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+servicequality[i].YHMC+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+servicequality[i].SJ+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+servicequality[i].XYDJ+"</td>";
					}
						$("#servicequalityts").html("总条数:"+i+"条");
						$("#servicequalitytbody").html(tab);
						$.unblockUI();
				}
			},
			error:function(){
				
			}		
		});
	}
	//车辆下拉栏
	function findlevel(){
		
		var levelList=["AAA","AA","A","B"];
		$("#servicequalitylevel").append("<option value=''>全部</option>");
		for(var i=0;i<levelList.length;i++){
			$("#servicequalitylevel").append("<option value='"+levelList[i]+"'>"+levelList[i]+"</option>");
		}	
	}
	function findba(){
		$.ajax({
			url : 'findallcomp.action',
			type : 'post',
			data:{
			},
			dataType: 'json',
			timeout : 180000,
			success:function(json){
					var area=json.list5;
					for(var i=0;i<area.length;i++){
						$("#servicequalitycomp").append("<option value='"+area[i].name+"'>"+area[i].name+"</option>");
					}
			},
			error:function(){
				
			}		
		});
	}
	//导出excle
	function findservicequalityexcle(){
		$.blockUI({message:"<div style='padding:5px;'><h3><font color='blue'>数据查询中,请稍候...</font></h3><img src='img/ajax-loader.gif' border='0'/><br/><br/></div>"});
		if(window.confirm("确定按上述条件把数据集导成Excel？")){
			$.ajax({
			url : 'findservicequalityexcle.action',
			type : 'post',
			data:{
				"year" : $("#servicequalityyear").val(),
				"compname" : $("#servicequalitycomp").val(),
				"level" : $("#level").val()
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
	$("#tscx").html("投诉查询");
</script>
<style>
</style>
<script src="js/jquery.blockUI.js"></script>
<link rel="stylesheet" type="text/css" media="screen" href="css/bootstrap.min.css">
