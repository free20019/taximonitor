<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html; charset=UTF-8"%>
	<script language="javascript" type="text/javascript"
	src="js/jquery.contextmenu.r2.js"></script>
<div class="row" style="height: 98%">
<input type="hidden" id="job_number" value="${job}">
	<div style="height: 100%; float: left; width: 74%" id="lefttdmanyvhic">
		<div style="height: 65%; width: 100%;" id="mapdivmanyvhic">
			<table border="0" style="height: 100%; width: 100%">
				<tr style="width: 98%;">
					<td style="width:100%;height:100%">
						<div id="data-div" class="google_maps"
							style="height: 100%; width: 100%">
							&nbsp;
						</div>
					</td>
				</tr>
			</table>
		</div>
		<TABLE border=0 cellPadding=0 cellSpacing=0 height="5px" width="100%"
			style="background-color: #D0DBD7; padding: -5px;">
			<TBODY>
				<TR>
					<TD onclick=switchSysBar1many(); style="HEIGHT: 9px; cursor: pointer;" align="center">
						<i class="fa fa-chevron-down" id="utodmanyvhic"><input
								type="hidden" id="switchPoint1manyvhic" value="3">
						</i>
					</TD>
				</TR>
			</TBODY>
		</TABLE>
		<!-- Widget ID (each widget will need unique ID)-->
		<div id="monitortabsmanyvhic" style="height: 35%; width: 100%;overflow: auto;">
					<ul id="internal-tab-1" class="">
									<li class="active">
										<a href="#is1" data-toggle="tab">车辆信息</a>
									</li>
								</ul>
									<div id="is1" style="width: 100%; overflow: auto;height: 80%;">
										<div id="tabs-a" >
										<table id="dw_table1" class="table table-striped table-bordered table-hover" style="width: 100%;padding-top: 0px;">
												<thead>
													<tr>
														<th nowrap='nowrap' data-type="string">
															车牌
														</th>
														<th nowrap='nowrap' data-type="string">
															车辆颜色
														</th>
														<th nowrap='nowrap' data-type="num">
															 Sim卡号
														</th>
														<th nowrap='nowrap' data-type="num">
															车速(km/h)
														</th>
														<th nowrap='nowrap' data-type="string">
															状态
														</th>
														<th nowrap='nowrap'>
															汇报时间
														</th>
														<th nowrap='nowrap' data-type="string">
															当前定位
														</th>
														<th nowrap='nowrap' data-type="string">
															终端类型
														</th>
														<th nowrap='nowrap' data-type="string">
															车辆类型
														</th>
													</tr>
												</thead>
												<tbody id="vehinonow">
												</tbody>
											</table>
									</div>
			</div>
			
		</div>
	</div>
	<TABLE border=0 cellPadding=0 cellSpacing=0 height="102%"
		style="background-color: #D0DBD7; float: left">
		<TBODY>
			<TR>
				<TD onclick=switchSysBarmany(); style="HEIGHT: 100%; cursor: pointer">
					<i class="fa fa-chevron-right" id="ltormanyvhic"><input
							type="hidden" id="switchPointmanyvhic" value="3">
					</i>
				</TD>
			</TR>
		</TBODY>
	</TABLE>
	<div class="jarviswidget jarviswidget-color-darken" id="wid-id-1"
		data-widget-editbutton="false" data-widget-custombutton="false"
		style="overflow: auto; width: 24%; float: left; height: 102%;">

		<!-- widget div-->

		<!-- widget content -->
		<div class="widget-body" style="overflow: auto; height: 100%"
			id="monirightmanyvhic" >
			<table id="table1" class="table table-striped table-bordered table-hover" style="overflow: auto;">
							<thead>
								<tr>
									<th nowrap='nowrap'> &nbsp;&nbsp;车辆组名</th>
									<th nowrap='nowrap'> &nbsp;&nbsp;车辆数量</th>
								</tr>
							</thead>
							<tbody id="monitorcartbody">
							</tbody>
						</table>
		</div>
	</div>
	</div>
	 <div class="contextMenu" id="myMenu1">
      <ul>
        <li id="open1"><img src="img/car/db.jpg" /> 打开</li>
        <li id="email1"><img src="img/car/db.jpg" /> 邮件</li>
        <li id="save1"><img src="img/car/db.jpg" /> 保存</li>
        <li id="delete1"><img src="img/car/db.jpg" /> 关闭</li>
      </ul>
    </div>
<script type="text/javascript">
	pageSetUp();
	
</script>


<script language="javascript" type="text/javascript"
	src="js/jquery.ztree.core-3.5.js"></script>
<script language="javascript" type="text/javascript"
	src="js/car/groupmonitor.js"></script>
<link type="text/css" rel="stylesheet"
	href="css/zTreeStyle/zTreeStyle.css" />
<script language="javascript" type="text/javascript"
	src="js/jquery.ztree.excheck-3.5.js"></script>
<link rel="stylesheet" type="text/css" media="screen"
	href="css/bootstrap.min.css">