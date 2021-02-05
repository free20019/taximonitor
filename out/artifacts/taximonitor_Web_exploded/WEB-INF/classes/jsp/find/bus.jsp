<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!-- <link rel="stylesheet" type="text/css" media="screen" href="/../css/bootstrap.min.css"> -->
<!-- <link rel="stylesheet" type="text/css" media="screen" href="/../css/map.css"> -->
<script type="text/javascript" src="/taximonitor/js/car/util.js"></script>
<!-- widget grid -->
<div class="row" style="height:600px;overflow: scroll">
<input type="hidden" id="actionidoper" value="${actionid }"/>
<input type="hidden" id="dataoper" value="${data }"/>
	<!-- row -->
<table border="0"  style="width: 102%;">
	<tr>
		<td>
			<div class="jarviswidget jarviswidget-color-darken" id="wid-id-2" data-widget-editbutton="false">
				<header style = "text-align:right;">
					<span class="widget-icon"> <i class="fa fa-table"></i> </span>
					<h2 id="yyjycx">营运交易查询</h2>
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
											<input id="startTime3" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})()" placeholder="--请选择开始时间--"/>
										</label>
								</section>
								<section style="width:85px;float:left;">
										<label class="font" > 
											<span style="font-size: 18px">结束时间:</span>
										</label>
								</section>
								<section style="width:180px;float:left;">
										<label class="input"> <i class="icon-append fa fa-calendar"></i>
											<input id="endTime3" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})()" placeholder="--请选择结束时间--"/>
										</label>
								</section>
								<section class="col col-5" style="width: 130px;float:left;" >
									<label class="select">
										<select  id="company3" class="select">
											<option value="0" >--选择公司--</option>
										</select> <i></i> 
									</label>
									</section>
								<section class="col col-5" style="width: 130px;float:left;" >
								<label class="select">
									<select  id="branch3" class="select">
										<option value="0" >--选择分公司--</option>
									</select> <i></i> 
								</label>
								</section>
								<section style="width: 130px;float:left;">
									<label class="input">
										<input id="cardNo3" type="text" list="cardNo33" placeholder="--车牌号码--" >
										<datalist id="cardNo33" >
										
										</datalist> 
									</label>
								</section>
								<a href="javascript:void(0);" style="float:left;" id ="search3" class="btn btn-primary" data-url="business.action" data-msg="business">查   询</a>
								<a href="bexcel.action" class="btn btn-primary" id ="bus" >导出</a>
						</form></br>
						<table id="dt_basicc6" class="table table-striped table-bordered table-hover">
							<thead>
								<tr >
									<td nowrap="nowrap">序号</td>	
									<td nowrap="nowrap">车号</td>								
									<td nowrap="nowrap">资格证号</td>	
									<td nowrap="nowrap">上车时间</td>
									<td nowrap="nowrap">下车时间</td>
									<td nowrap="nowrap">营运金额(元)</td>
									<td nowrap="nowrap">计程(公里)</td>
									<td nowrap="nowrap">空驶(公里)</td>									
									<td nowrap="nowrap">等候(秒)</td>
									<td nowrap="nowrap">交易类型</td>		
									<td nowrap="nowrap">接收时间</td>		
								</tr>
							</thead>
							<tbody id="tb3">
								
							</tbody>
							
						</table>
							
					</div>
				 
					<div class="pagination">
							<span class="getData3" data-url="getBusiness.action" ></span>
							&nbsp;&nbsp;&nbsp;&nbsp;共有 <strong><a id="num3">0</a></strong> 条记录
							&nbsp;&nbsp;&nbsp;&nbsp;第 <strong><a id="currentPage3">0</a></strong> / <strong><a id="pageCount3">0</a></strong> 页
							
							&nbsp;&nbsp;&nbsp;&nbsp;
							<a class="STYLE19" href="javascript:getCurrentPage3(1);">首页</a>
							<a class="STYLE19" href="javascript:getCurrentPage3(this.page-1);">上一页</a> | <a class="STYLE19" href="javascript:getCurrentPage3(this.page+1);">下一页</a>
							<a class="STYLE19" href="javascript:getEndPage3(pageCount);">末页</a>
							&nbsp;&nbsp;&nbsp;&nbsp;转到第<input  style="width:30px;font-size:12px; border:solid 1px #7aaebd;" type="text" id="jumpPage3" >页 <a class="STYLE19" href="javascript:getOnePage3();">go</a>
							
					</div>
				  </div>  
				  </div>
			
		</td>
	</tr>
</table>
</div>
<script type="text/javascript" src="/taximonitor/js/car/map.js"></script>
<script type="text/javascript">
$("#yyjycx").html("营运交易查询");
</script>