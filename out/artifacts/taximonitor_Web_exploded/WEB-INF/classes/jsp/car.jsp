<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html; charset=UTF-8"%>
<script src="js/jquery.blockUI.js"></script>
<div class="row" style="height: 98%">
<input type="hidden" id="job_number" value="${job}">
<input type="hidden" id="dlqxcar" value="${username }"/>
	<div style="height: 100%; float: left; width: 74%" id="lefttdmany">
		<div style="height: 65%; width: 100%;" id="mapdivmany">
			<table border="0" style="height: 100%; width: 100%">
				<tr style="width: 98%;">
					<td style="width:100%;height:100%">
						<table border="0" style="height: 100%;width: 100%">
							<tr style="width: 98%;" onMouseOver="tbtc();" onMouseOut="tbtcclose();">
								<td>
								<font size="4" color="red">总数：<strong id="total"></strong>&nbsp;上线：<strong id="onnum"></strong></font>&nbsp;<font size="4" color="#333333" ><img src="img/car/d.png"></img>下线：</font><font size="4" color="#333333"><strong id="offnum"></strong> </font>&nbsp;<font size="4" color="#ff1313" ><img src="img/car/h.png"></img>重车：</font><font size="4" color="red"><strong id="hnum"></strong></font>&nbsp;<font size="4" color="#009933" ><img src="img/car/c.png"></img>空车：</font><font size="4" color="#009933"><strong id="nnum"></strong></font>
								&nbsp;<font size="1"  style="float: right;padding-right: 20px;"><strong id="ntime"></strong></font>
								</td>
							</tr>
							<tr style="width: 98%;">
								<td style="width: 100%;height: 100%"><div id="many-data-div" class="google_maps" style="height: 100%;width: 100%">&nbsp;</div></td>
							</tr>
						</table>
						
					</td>
				</tr>
			</table>
		</div>
		<!-- 
		<div id="data-div" class="google_maps" style="height: 65%;width: 100%">&nbsp;</div>
		 -->
		<TABLE border=0 cellPadding=0 cellSpacing=0 height="5px" width="100%"
			style="background-color: #D0DBD7; padding: -5px;">
			<TBODY>
				<TR>
					<TD onclick=switchSysBar1many(); style="HEIGHT: 9px; cursor: pointer;" align="center">
						<i class="fa fa-chevron-down" id="utodmany"><input
								type="hidden" id="switchPoint1many" value="3">
						</i>
					</TD>
				</TR>
			</TBODY>
		</TABLE>
		<!-- Widget ID (each widget will need unique ID)-->
		<div id="monitortabsmany" style="height: 35%; width: 100%;overflow: auto;">
					<ul id="internal-tab-1" class="">
									<li class="active">
										<a href="#is1" data-toggle="tab">车辆信息</a>
									</li>
									<li class="active">
										<a href="#is2" data-toggle="tab">报警信息</a>
									</li>
					</ul>
					<div id="is1" style="width: 100%; overflow: auto;height: 80%;">
						<div id="tabs-a" >
						<table id="dw_table" class="table table-striped table-bordered table-hover" style="width: 100%;padding-top: 0px;">
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
								<tbody id="vhicnow">
								</tbody>
							</table>
					</div>
			</div>
			
				<div id="is2" style="width: 100%; overflow: auto;height: 80%;">
					<div id="tabs-a2" >
					<table id="dw_table2" class="table table-striped table-bordered table-hover" style="width: 100%;padding-top: 0px;">
							<thead>
								<tr>
									<th nowrap='nowrap' data-type="string">
										车牌
									</th>
									<th nowrap='nowrap' data-type="string">
										报警信息
									</th>
									<th nowrap='nowrap'>
										报警时间
									</th>
									<th nowrap='nowrap' data-type="num">
										 Sim卡号
									</th>
									<th nowrap='nowrap' data-type="string">
										终端类型
									</th>
									<th nowrap='nowrap' data-type="string">
										车辆类型
									</th>
								</tr>
							</thead>
							<tbody id="vhicnow2">
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
					<i class="fa fa-chevron-right" id="ltormany"><input
							type="hidden" id="switchPointmany" value="3">
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
			id="monirightmany" >
			<div class="widget-body-toolbar" align="left" style="height: 4%;">
				<form action="" id="checkout-form" class="smart-form" novalidate="novalidate" >
								<input type='text' style='display:none'/>
								<section style="width:100%;float:left">
										<label class="input"> <i class="icon-prepend fa fa-user"></i>
											<input type="text" name="fname" placeholder="车牌,终端类型,终端编号,SIM卡" id="bzdvhic" onkeyup="findvhic1();">
										</label>
								</section>
				</form>
			</div>
			<table id="vehi_table" class="table table-striped table-bordered table-hover" style="overflow: auto;">
							<thead>
								<tr>
									<th nowrap='nowrap'><input type="checkbox" id="quanxuan" onclick="qxqbx()"> &nbsp;&nbsp;车牌</th>
								</tr>
							</thead>
							<tbody id="vehi_data">
							</tbody>
						</table>
		</div>
	</div>
	
		
		
		<div id="message_send" title="修改">
	<form>
		<fieldset>
			<input name="authenticity_token" type="hidden">
			<div class="form-group">
				<textarea rows="5" cols="60" id="xxfs_xxk"></textarea>
			</div>
		</fieldset>
	</form>
</div>
		
	</div>
	

<script type="text/javascript">
	pageSetUp();
</script>

<script language="javascript" type="text/javascript"
	src="js/jquery.ztree.core-3.5.js"></script>
<script language="javascript" type="text/javascript"
	src="js/car/car.js"></script>
<link type="text/css" rel="stylesheet"
	href="css/zTreeStyle/zTreeStyle.css" />
<script language="javascript" type="text/javascript"
	src="js/jquery.ztree.excheck-3.5.js"></script>
<link rel="stylesheet" type="text/css" media="screen"
	href="css/bootstrap.min.css">