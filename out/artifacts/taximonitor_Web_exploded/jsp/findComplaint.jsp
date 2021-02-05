<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
contentType="text/html; charset=UTF-8" %>
<!-- widget grid -->
<div class="row" style="height:100%;overflow: scroll">
<input type="hidden" id="um1" value="${username }"/>
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
											<input id="complaintstime" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})()" placeholder="--请选择开始时间--"/>
										</label>
								</section>
								<section style="width:180px;float:left;">
										<label class="input"> <i class="icon-append fa fa-calendar"></i>
											<input id="complaintetime" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})()" placeholder="--请选择结束时间--"/>
										</label>
								</section>
								<section class="col col-5" style="width: 200px;float:left;" >
									<label class="select">
										<select id="complainttype" class="select" >
											<option value="" >--选择投诉类型--</option>
										</select> <i></i> </label>
								</section>
								<a href="javascript:void(0);"  class="btn btn-primary"  onclick="findcomplaint(1);">查   询</a>
								<a href="javascript:void(1);"  class="btn btn-primary"  onclick="findcomplaintexcle();">导   出</a><span id="nolinedaochu"></span>
						</form>
						<table id="complainttable" style="float:left;"  class="table table-striped table-bordered table-hover">
							<thead>
								<tr>							
									<th>序号</th>
									<th>投诉人</th>
									<th>投诉类型</th>
									<th>联系方式</th>
									<th>投诉车辆</th>
									<th>处理详情</th>
									<th>投诉时间</th>
									<th>处理时间</th>
								</tr>
							</thead>
							<tbody id="complainttbody">
							</tbody>
						</table>

					</div>
					<div class="pagination">
							<span class="getData3" data-url="" ></span>
							&nbsp;&nbsp;&nbsp;&nbsp;共有 <strong><a id="num3">0</a></strong> 条记录
							&nbsp;&nbsp;&nbsp;&nbsp;第 <strong><a id="currentPage3">0</a></strong> / <strong><a id="pageCount3">0</a></strong> 页
							
							&nbsp;&nbsp;&nbsp;&nbsp;
							<a class="STYLE19" href="javascript:getCurrentPage4(1);">首页</a>
							<a class="STYLE19" href="javascript:getCurrentPage4(currentPage-1);">上一页</a> | <a class="STYLE19" href="javascript:getCurrentPage4(currentPage+1);">下一页</a>
							<a class="STYLE19" href="javascript:getCurrentPage4(pageCount);">末页</a>
							&nbsp;&nbsp;&nbsp;&nbsp;转到第<input  style="width:30px;font-size:12px; border:solid 1px #7aaebd;" type="text" id="jumpPage4" >页 <a class="STYLE19" href="javascript:getOnePage4();">go</a>
							
					</div>
				</div>
			</div>
		</td>
	</tr>
</table>
</div>
<script type="text/javascript">
	//DO NOT REMOVE : GLOBAL FUNCTIONS!
	pageSetUp();
	$(document).ready(function(){
		var mydate = new Date();
		$("#complaintstime").val(setformatnewlc(mydate));
		$("#complaintetime").val(setformatnewlc(mydate));
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
	// 获取上一页，下一页，末页
	function getCurrentPage4(pageCount){
		if(pageCount<=0||pageCount>$('#pageCount3').html()){
			return ;
		}
		findcomplaint(pageCount);
	}
	// 获取跳转页码
	function getOnePage4(){
		var onePage = $('#jumpPage4').val();
		findcomplaint(onePage);
	}
	find1();
	function find1(){
		findtype();
		setTimeout("findcomplaint(1)", 500);
	}
	
	
function findcomplaint(cp){
$.blockUI({message:"<div style='padding:5px;'><h3><font color='blue'>数据查询中,请稍候...</font></h3><img src='img/ajax-loader.gif' border='0'/><br/><br/></div>"});
	$.ajax({
			url : 'findcomplaint.action',
			type : 'post',
			data:{
				"stime" : $("#complaintstime").val(),
				"etime" : $("#complaintetime").val(),
				"type" : $("#complainttype").val(),
				"page2.currentPage":cp
			},
			dataType: 'json',
			timeout : 180000,
			success:function(json){
				var noline = json.list2;
				var p = json.page2;		
				console.log(noline)
				var tab="";
				var i=0;
				//$('#complainttable').dataTable().fnDestroy(); 
				if(noline!=null){
					for(i=0;i<noline.length;i++){
						tab=tab+"<tr><td nowrap='nowrap'>"+((p.currentPage-1)*p.pageSize+i+1)+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+noline[i].CALL_NAME+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+noline[i].BUSINESS_ITEMTYPE_NAME+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+noline[i].CALLER_ID+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+noline[i].VEHICLE_PLATE_NUMBER+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+noline[i].BUSINESS_STATUS_NAME+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+noline[i].ARCHIVE_TIME+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+noline[i].ACCEPT_TIME+"</td>";
					}					
					$("#complainttbody").html(tab);					
					num =p.pageCount;
					currentPage=p.currentPage;
					pageCount=parseInt((num-1)/p.pageSize+1);
					$('#num3').html(num);
					$('#pageCount3').html(pageCount);
					$('#currentPage3').html(p.currentPage);	
					$.unblockUI();
				}
			},
			error:function(){
				
			}		
	});
}
	function findtype(){
		var area=["绕道","中途抛客","扰乱场站秩序","服务质量","拒载","拼载","表扬","其他"];
		$("#complainttype").append("<option value=''>全部</option>");
		for(var i=0;i<area.length;i++){
			$("#complainttype").append("<option value='"+area[i]+"'>"+area[i]+"</option>");
		}	
	}


//导出excle
	function findcomplaintexcle(){
	$.blockUI({message:"<div style='padding:5px;'><h3><font color='blue'>数据查询中,请稍候...</font></h3><img src='img/ajax-loader.gif' border='0'/><br/><br/></div>"});
	if(window.confirm("确定按上述条件把数据集导成Excel？")){
		$.ajax({
		url : 'findcomplaintexcle.action',
		type : 'post',
		data:{
			"stime" : $("#complaintstime").val(),
			"etime" : $("#complaintetime").val(),
			"compname" : $("#complainttype").val()
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
