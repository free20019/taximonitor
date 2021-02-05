<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
contentType="text/html; charset=UTF-8" %>
<script src="js/jquery.blockUI.js"></script>
<div class="row" style="height: 98%" >
<div style="height: 100%;float: left;width: 74%" id="lefttdmon">
		<input type="hidden" id="compid" value="${date}"> 
		<input type="hidden" id="userid" value="${userid}">
		<div style="height: 65%;width: 100%" id="mapdivmon">
			<table border="0" style="height: 100%;width: 100%">
			</tr>
			<tr style="width: 98%;">
				<td style="width: 100%;height: 100%"><div id="fancemon-data-div" class="google_maps" style="height: 100%;width: 100%">&nbsp;</div></td>
			</tr>
		</table>
		
		</div>
		<!-- 
		<div id="data-div" class="google_maps" style="height: 65%;width: 100%">&nbsp;</div>
		 -->
			<TABLE border=0 cellPadding=0 cellSpacing=0 height="9px" width="100%" style="background-color: #D0DBD7;padding: -5px;" >
          <TBODY>
            <TR> 
              <TD onclick=switchSysBarmon1mon() style="HEIGHT: 9px;cursor:pointer;" align="center"><i class="fa fa-chevron-down" id="utodmon"><input type="hidden" id="switchPointmon1mon" value="3"></i></TD>
            </TR>
          </TBODY>
        </TABLE>
			<!-- Widget ID (each widget will need unique ID)-->
			<div id="monitortabsmon"  style="height: 35%;width: 100%;">
				<ul>
					<li>
						<a href="#tabs-a" onclick="dwtab()" >定位信息(<font id="carnumidmon">0</font>)</a>
					</li>
				</ul>
				<div id="tabs-a" style="width: 100%;overflow: auto;height: 80%">
				<form id="ssjkform">
					<table id="dw_table11" class="table table-striped table-bordered table-hover" style="width: 100%;padding-top: 0px;">
							<thead>
								<tr>
										<th nowrap='nowrap'>
											序号
										</th>
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
							<tbody id="areatbodymon">
							</tbody>
						</table>
						</form>
				</div>
			</div>
</div>
<TABLE border=0 cellPadding=0 cellSpacing=0 height="102%" style="background-color: #D0DBD7;float: left">
          <TBODY>
            <TR> 
              <TD onclick=switchSysBarmon() style="HEIGHT: 100%;cursor:pointer"><i class="fa fa-chevron-right" id="ltormon"><input type="hidden" id="switchPointmon" value="3"></i></TD>
            </TR>
          </TBODY>
        </TABLE>
	<div class="jarviswidget jarviswidget-color-darken" id="wid-id-1" data-widget-editbutton="false" data-widget-custombutton="false"  style="overflow: auto;width: 24%;float: left;height: 102%;">
				<!-- 
				<header>
					<span class="widget-icon"> <i class="fa fa-edit"></i> </span>
					<h2>车辆列表</h2>				
					
				</header>
				 -->

				<!-- widget div-->
					
					<!-- widget content -->
					<div class="widget-body" style="overflow: auto;height: 100%" id="monirightmon">
						<table  style="height:100%;width:100%">
							<tr style="height:50%;width:100%">
								<td style="vertical-align:top;">
									<table id="area_table" class="table table-striped table-bordered table-hover" style="overflow: auto;">
										<thead>
											<tr>
												<th nowrap='nowrap'><font id="areanummon"></font>区域</th>
												<th nowrap='nowrap'></font>车辆组</th>
												<th nowrap='nowrap'>车辆数</th>
											</tr>
										</thead>
										<tbody id="areanumsmon">
										</tbody>
									</table>
								</td>
							</tr>
							<tr style="height:50%;width:100%">
								<td>
									<div class="widget-body-toolbar" align="left" style="height: 100%;">
										<form action="" id="checkout-form" class="smart-form"
											novalidate="novalidate">
											违规车辆
											<table><tr>
													<label class="select select-multiple">
														<select multiple="" class="custom-scroll"
															style="height:300px; width: 100%" id="wzvhic">
														</select>
													</label>
												</tr></table>
										</form>
									</div>
								</td>
							</tr>
						</table>
					</div>
				</div>

</div>
<script language="javascript" type="text/javascript" src="js/car/fencemonitor.js"></script>
<style>
.txtstyle{position:relative;background: rgba(255,204,51, 0.5);}
.txtstyle span{
white-space:nowrap;
	display:block;
	text-align:left;
	background-color: #FFCC66;
	color:black;
	width:auto;
	border:1px solid #ffffff;
	FONT-FAMILY:微软雅黑;
	position:absolute;
	top:-10px;left:25px;
	white-space:nowrap webkit-border-radius:5px;
	border-radius:5px;
}
td,th{ nowrap:nowrap;}
</style>
<script language="javascript" type="text/javascript" src="js/jquery.ztree.core-3.5.js"></script>  
	<link type="text/css" rel="stylesheet" href="css/zTreeStyle/zTreeStyle.css" />  
	<script language="javascript" type="text/javascript" src="js/jquery.ztree.excheck-3.5.js"></script> 
<link rel="stylesheet" type="text/css" media="screen" href="css/bootstrap.min.css">