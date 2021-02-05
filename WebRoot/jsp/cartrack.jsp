<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
contentType="text/html; charset=UTF-8" %>
<div class="row" style="height: 100%" >
<!-- widget grid -->
	<!-- row -->
		<div style="width: 100%;height: 60%" id="updiv0"><div id="data-div-history" class="google_maps" style="height: 100%;width: 100%">&nbsp;</div></div>
	<TABLE border=0 cellPadding=0 cellSpacing=0 height="9px" width="100%" style="background-color: #D0DBD7;padding: -5px;" >
          <TBODY>
            <TR> 
              <TD onclick=switchSysBar0() style="HEIGHT: 9px;cursor:pointer;" align="center"><i class="fa fa-chevron-down" id="utod0"><input type="hidden" id="switchPoint0" value="3"></i></TD>
            </TR>
          </TBODY>
    </TABLE>
	
	<div id="downdiv0" style="width: 100%;height: 40%;overflow: auto;" >
			<div style="overflow: auto;" class="jarviswidget" id="wid-id-0" data-widget-colorbutton="false" data-widget-editbutton="false" data-widget-custombutton="false">
				<header>
<!-- 					<span class="widget-icon"> <i class="fa fa-edit"></i> </span> -->
<!-- 					<h2>明细数据&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);" id="area_add" class="btn btn-primary" style = "display:none" onclick="addudarea();">添加区域</a></h2> -->
				<h2>
					<ul id="internal-tab-1" class="">
						<li class="active">
							<a href="#is1" data-toggle="tab">明细数据</a>
						</li>
						<li class="active">
							<a href="#is2" data-toggle="tab">轨迹图表</a>
						</li>
					</ul>
				</h2>
				</header>

				<!-- widget div-->
				<div id="is1">

					<!-- widget edit box -->
					<div class="jarviswidget-editbox">
						<!-- This area used as dropdown edit box -->

					</div>
					<!-- end widget edit box -->

					<!-- widget content -->
					<div class="widget-body">
						<div class="widget-body-toolbar">
								<form action="" id="checkout-form" class="smart-form" novalidate="novalidate" >
								<section style=" width:130px;float:left">
									<label class="input">
										<input id="hiscar" type="text" list="historycar" placeholder="--车牌号码--" >
										<datalist id="historycar" >
										
										</datalist> 
									</label>
								</section>
								<section style="width:175px;float:left;">
										<label class="input"> <i class="icon-append fa fa-calendar"></i>
											 <input id="hisstime" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})()" placeholder="--开始时间--"></input>
										</label>
									</section>
									<section style="width:175px;float:left;">
										<label class="input"> <i class="icon-append fa fa-calendar"></i>
											 <input id="hisetime" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})()" placeholder="--结束时间--"></input>
										</label>
									</section>
									<a href="javascript:void(0);" class="btn btn-primary" onclick="querymanyhis();">查   询</a>
							<div style="float:left;text-align: center;height:32px;line-height:32px;">总里程：</div>
							<section style="width:80px;float:left;">
										<label class="input"> 
											 <input id="zlc" type="text"  placeholder="0.00km" disabled></input>
										</label>
									</section>&nbsp;&nbsp;
									<a href="javascript:void(0);" class="btn btn-primary" onclick="startAnimation();">从头开始</a>
									<a href="javascript:void(0);" class="btn btn-primary" onclick="pauseAnimation();">暂   停</a>
									<a href="javascript:void(0);" class="btn btn-primary" onclick="continueAnimation();">继   续</a>
									<a href="javascript:void(0);" class="btn btn-primary" onclick="quick();">加   快</a>
									<a href="javascript:void(0);" class="btn btn-primary" onclick="slow();">减   慢</a>
									&nbsp;&nbsp;
									<a href="javascript:void(0);" class="btn btn-primary" onclick="exportgj();">导   出</a><span id="gjdaochu"></span>
								</form>
						</div>
						<!-- gps数据 -->
						<form id="gjbbform">
						<table id="dt_history_basic" class="table table-striped table-bordered table-hover" style="height: 100%">
							<thead>
								<tr>
									<th nowrap='nowrap'>序号</th>
									<th nowrap='nowrap'>上报时间</th>
									<th nowrap='nowrap'>车辆状态</th>
									<th nowrap='nowrap'>经度</th>
									<th nowrap='nowrap'>纬度</th>
									<th nowrap='nowrap'>方向</th>
									<th nowrap='nowrap'>GPS速度(km/h)</th>
									<th nowrap='nowrap'>总里程(km)</th>
									<th nowrap='nowrap'>位置描述</th>
								</tr>
							</thead>
							<tbody id="hisvehigpsdata">
							</tbody>
						</table>
					</form>

					</div>
					<!-- end widget content -->

				</div>
				<!-- end widget div -->
				<div id="is2">
					<div class="jarviswidget-editbox">
					</div>
					<div class="widget-body no-padding">
						<div id="lsgj" class="chart has-legend" style="width: 1100px;height: 220px;"></div>
					</div>
				</div>
				
			</div>
			</div>
</div>
<script src="js/car/cartrack.js"></script>
<script src="js/jquery.blockUI.js"></script>
<script src="js/echarts/echarts.js"></script>
<script src="js/echarts/bar.js"></script>
<script src="js/echarts/line.js"></script>
<link rel="stylesheet" type="text/css" media="screen" href="css/bootstrap.min.css">