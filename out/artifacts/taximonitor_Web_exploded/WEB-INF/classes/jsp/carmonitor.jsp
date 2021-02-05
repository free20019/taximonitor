<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
contentType="text/html; charset=UTF-8" %>
<script src="js/jquery.blockUI.js"></script>
<div class="row" style="height: 98%" >
<div style="height: 100%;float: left;width: 74%" id="lefttdmany1">
		<div style="height: 65%;width: 100%;" id="mapdivmany1" > 
			<table border="0" style="height: 100%;width: 100%">
			<tr style="width: 98%;">
				<td style="width: 100%;height: 100%"><div id="carmonitor" class="google_maps" style="height: 100%;width: 100%">&nbsp;</div></td>
			</tr>
		</table>
		
		</div>
		<!-- 
		<div id="data-div" class="google_maps" style="height: 65%;width: 100%">&nbsp;</div>
		 -->
			<TABLE border=0 cellPadding=0 cellSpacing=0 height="9px" width="100%" style="background-color: #D0DBD7;padding: -5px;" >
          <TBODY>
            <TR> 
              <TD onclick=switchSysBar1many1() style="HEIGHT: 9px;cursor:pointer;" align="center"><i class="fa fa-chevron-down" id="utodmany1"><input type="hidden" id="switchpoing2many" value="3"></i></TD>
            </TR>
          </TBODY>
        </TABLE>
			<!-- Widget ID (each widget will need unique ID)-->
			<div id="monitortabsmany1"  style="height: 35%;width: 100%;">
				<ul>
					<li>
						<a href="#tabs-a" >定位信息(<font id="carnumidmany">0</font>)</a>
					</li>
				</ul>
				<div id="tabs-a" style="width: 100%;overflow: auto;height: 80%">
					<table id="dw_tablemany" class="table table-striped table-bordered table-hover" style="width: 100%;padding-top: 0px;">
							<thead>
								<tr>
									<th nowrap='nowrap' data-type="string">车牌</th>
									<th nowrap='nowrap' data-type="string">车辆颜色</th>
									<th nowrap='nowrap' data-type="num">Sim卡号</th>
									<th nowrap='nowrap' data-type="num">车速(km/h)</th>
									<th nowrap='nowrap' data-type="string">状态</th>
									<th nowrap='nowrap'>汇报时间</th>
									<th nowrap='nowrap' data-type="string">当前定位</th>
									<th nowrap='nowrap' data-type="string">终端类型</th>
									<th nowrap='nowrap' data-type="string">车辆类型</th>
								</tr>
							</thead>
							<tbody id="dw_datamany">
							</tbody>
						</table>
				</div>
			</div>
</div>
<TABLE border=0 cellPadding=0 cellSpacing=0 height="102%" style="background-color: #D0DBD7;float: left">
          <TBODY>
            <TR> 
              <TD onclick=switchSysBarmany1() style="HEIGHT: 100%;cursor:pointer"><i class="fa fa-chevron-right" id="ltormany1"><input type="hidden" id="switchPointmany1" value="3"></i></TD>
            </TR>
          </TBODY>
        </TABLE>
	<div class="jarviswidget jarviswidget-color-darken" id="wid-id-1" data-widget-editbutton="false" data-widget-custombutton="false"  style="overflow: auto;width: 24%;float: left;height: 102%;">
					<!-- widget content -->
					<div class="widget-body" style="overflow: auto;height: 100%" id="monirightmany1" >
						<div class="widget-body-toolbar" align="left" >
							<form action="" id="checkout-form" class="smart-form" novalidate="novalidate" >
								<section style=" width:70%;float:left">
									<label class="input">
										<input type="hidden"/>
										<input  type="text" list="manycar" placeholder="--车牌号码--" id="carno">
										<datalist id="manycar" >
										
										</datalist> 
									</label>
								</section>&nbsp;&nbsp;
							<a href="javascript:void(0);" class="btn btn-primary" onclick="addmany();">添   加</a>
							</form>
						</div>
						<!-- gps数据 
						-->
						<table id="tablemany" class="table table-striped table-bordered table-hover" style="overflow: auto;">
							<thead>
								<tr>
									<th nowrap='nowrap'>车辆</th>
									<th nowrap='nowrap'>监控时间</th>
									<th nowrap='nowrap'>操作</th>
								</tr>
							</thead>
							<tbody id="datamany">
							</tbody>
						</table>
					

					</div>
				</div>

</div>
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
.markerContentStyle{position:relative;filter: Alpha(Opacity=0)}
.markerContentStyle span{white-space:nowrap;
	display:block;
	text-align:left;
	background-color: #3399CC;
	color:#FFFFFF;
	width:auto;
	border:1px solid #3399CC;
	FONT-FAMILY:微软雅黑;
	position:absolute;
	top:25px;left:-20px;
	white-space:nowrap webkit-border-radius:5px;
	border-radius:5px;}
td,th{ nowrap:nowrap;}
</style>
<script language="javascript" type="text/javascript" src="js/jquery.ztree.core-3.5.js"></script>
<script language="javascript" type="text/javascript" src="js/car/carmonitor.js"></script>
	<link type="text/css" rel="stylesheet" href="css/zTreeStyle/zTreeStyle.css" />  
	<script language="javascript" type="text/javascript" src="js/jquery.ztree.excheck-3.5.js"></script> 
<link rel="stylesheet" type="text/css" media="screen" href="css/bootstrap.min.css">