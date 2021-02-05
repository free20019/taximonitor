<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!-- <link rel="stylesheet" type="text/css" media="screen" href="/../css/bootstrap.min.css"> -->
<!-- <link rel="stylesheet" type="text/css" media="screen" href="/../css/map.css"> -->
<script type="text/javascript" src="/taximonitor/js/car/util.js"></script>
<!-- widget grid -->
<div class="row" style="height:600px;overflow: scroll">
<input type="hidden" id="actionidwxtj" value="${actionid }"/>
<input type="hidden" id="datawxtj" value="${data }"/>
	<!-- row -->
<table border="0"  style="width: 102%;">
	<tr>
		<td>
			<div class="jarviswidget jarviswidget-color-darken" id="wid-id-2" data-widget-editbutton="false">
				<header style = "text-align:right;">
					<span class="widget-icon"> <i class="fa fa-table"></i> </span>
					<h2 id="wxtj">维修统计</h2>
				</header>
				<!-- widget div-->
				<div>
					<div class="jarviswidget-editbox">
					</div>
					<div class="widget-body no-padding">
						<form action="" id="msg-form" class="smart-form" novalidate="novalidate" >
								<section style="width:85px;float:left;">
										<label class="font" >
											<span style="font-size: 18px">开始时间:</span>
										</label>
								</section>
								<section style="width:180px;float:left;">
										<label class="input"> <i class="icon-append fa fa-calendar"></i>
											<input id="wxtjstime" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})()" placeholder="--请选择开始时间--"/>
										</label>
								</section>
								<section style="width:85px;float:left;">
										<label class="font" > 
											<span style="font-size: 18px">结束时间:</span>
										</label>
								</section>
								<section style="width:180px;float:left;">
										<label class="input"> <i class="icon-append fa fa-calendar"></i>
											<input id="wxtjetime" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})()" placeholder="--请选择结束时间--"/>
										</label>
								</section>
								<section class="col col-5" style="width: 130px;float:left;" >
									<label class="select">
										<select  id="wxtjgs" class="select">
											<option value="0" >--选择公司--</option>
										</select> <i></i> 
									</label>
									</section>
								<section class="col col-5" style="width: 130px;float:left;" >
								<label class="select">
									<select  id="wxtjfgs" class="select">
										<option value="0" >--选择分公司--</option>
									</select> <i></i> 
								</label>
								</section>
								<section style="width: 130px;float:left;">
									<label class="input">
										<input id="wxtjcphm" type="text" list="wxtjcphm1" placeholder="--车牌号码--" >
										<datalist id="wxtjcphm1" >
										
										</datalist> 
									</label>
								</section>
								<section class="col col-5" style="width: 130px;float:left;" >
								<label class="select">
									<select  id="wxtjwxlx" class="select">
										<option value="0" >--所有维修类型--</option>
										<option value="一般维修" >--一般维修--</option>
										<option value="不能现场处理" >--不能现场处理--</option>
										<option value="简单维修" >--简单维修--</option>
										<option value="自查维修" >--自查维修--</option>
										<option value="通知维修" >--通知维修--</option>
									</select> <i></i> 
								</label>
								</section>
								<a href="javascript:void(0);" style="float:left;" id ="getwxlx" class="btn btn-primary" data-msg="wxtj">查   询</a>
								<a href="bexcel.action" class="btn btn-primary" id ="bus" >导出</a>
						</form></br>
						<table id="dt_basicc6" class="table table-striped table-bordered table-hover">
							<thead>
								<tr >
									<td nowrap="nowrap">序号</td>	
									<td nowrap="nowrap">公司</td>	
									<td nowrap="nowrap">车牌号码</td>
									<td nowrap="nowrap">终端类型</td>
									<td nowrap="nowrap">终端子类型</td>
									<td nowrap="nowrap">维修次数</td>
								</tr>
							</thead>
							<tbody id="wxtjtbody">
								
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
$("#wxtj").html("维修统计");
$('#getwxlx').on('click', function(){
	$.blockUI({message:"<div style='padding:5px;'><h3><font color='blue'>数据查询中,请稍候...</font></h3><img src='img/ajax-loader.gif' border='0'/><br/><br/></div>"});
	$.ajax({
		url:"findwxtj.action",
		data:{
			"stime":$("#wxtjstime").val(),
			"etime":$("#wxtjetime").val(),
			"compid":$("#wxtjgs").val(),
			"compname":$("#wxtjfgs").val(),
			"quyu":$("#wxtjcphm").val(),
			"areaid":$("#wxtjwxlx").val()
		},
		dataType:"JSON",
		type:"POST",
		async:true,
		success:function(data){
			$.unblockUI();
			var vehicle = data.vehicle;
			$('#wxtjtbody').html("");
			var tab = "";
			for(var i=0; i<vehicle.length; i++){
				tab += '<tr><td>'+(i+1)+'</td><td>'+vehicle[i].ba_name+'</td><td>'+vehicle[i].vehi_no+'</td><td>'+vehicle[i].mt_name+'</td><td>'+vehicle[i].mdtno+'</td><td>'+vehicle[i].address+'</td></tr>';
			}
			$('#wxtjtbody').html(tab);
		}
	});
})
</script>