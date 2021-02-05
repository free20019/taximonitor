<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML >
<html lang="zh-cn">
	<head>
		<meta charset="utf-8">
		<title>  </title>
		<meta name="description" content="">
		<meta name="author" content="">
		<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=edge; chrome=1">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
		<!-- Basic Styles -->
		<link rel="stylesheet" type="text/css" media="screen" href="css/smartadmin-production_unminified.css">
		<link rel="stylesheet" type="text/css" media="screen" href="css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" media="screen" href="css/font-awesome.min.css">
		<link rel="stylesheet" type="text/css" media="screen" href="css/smartadmin-skins.css">
		<link rel="stylesheet" type="text/css" href="css/gddiv.css" />
		<link rel="stylesheet" type="text/css" href="css/image.css" />
		<link rel="stylesheet" type="text/css" media="screen" href="css/demo.css">
		<!-- FAVICONS -->
		
		<link rel="shortcut icon" href="img/favicon/favicon.ico" type="image/x-icon">
		<link rel="icon" href="img/favicon/favicon.ico" type="image/x-icon">
		<!-- Startup image for web apps -->
		<link rel="apple-touch-startup-image" href="img/splash/ipad-landscape.png" media="screen and (min-device-width: 481px) and (max-device-width: 1024px) and (orientation:landscape)">
		<link rel="apple-touch-startup-image" href="img/splash/ipad-portrait.png" media="screen and (min-device-width: 481px) and (max-device-width: 1024px) and (orientation:portrait)">
    <link rel="stylesheet" href="js/stylesheets/jquery.tooltip/jquery.tooltip.css" type="text/css" />
    <script type="text/javascript" src="js/javascripts/jquery.min.js"></script>
    <script type="text/javascript" src="js/javascripts/jquery.tooltip.js"></script>
    <script type="text/javascript" src="js/map.js"></script>
    <script type="text/javascript">
      $j = jQuery.noConflict();
      $j(document).ready(function(){
        $j("div.item").tooltip();
      });
    </script>
	      <script src="js/html5shiv.min.js"></script>
	      <script src="js/respond.min.js"></script>
		<script src="js/libs/jquery-2.1.4.min.js"></script>
		<script src="js/libs/jquery-ui.min.js"></script>
		<script src="js/jQueryRotate.2.2.js"></script>
		<script src="js/map.js"></script>
		<!-- 
		<script src="js/jquery.blockUI.js"></script>
		 -->
		 <!-- 
		  <script src="http://webapi.amap.com/maps?v=1.2&key=0a54a59bdc431189d9405b3f2937921a" type="text/javascript"></script>
		  -->
		<script src="http://webapi.amap.com/maps?v=1.3&key=20be63c6a78db94a95f7393077b90dca&plugin=AMap.Geocoder" type="text/javascript"></script>
		<style type="text/css">
  			#tabs li .ui-icon-close {	float: left; margin: 0.4em 0.2em 0 0; cursor: pointer; }
		</style>

		<script type="text/javascript">
		$(document).ready(function(){
			 $(".menu li").hover(function() {
		 	 $(this).find("em").animate({opacity: "show", top: "-75"}, "slow");
		 	}, function() {
		  	$(this).find("em").animate({opacity: "hide", top: "-85"}, "fast");
		 	});
		 	});
		var mapObj;
		var view;
		var scale;
		var ssjk=0;
		var qyjk=0;
		var clzjk=0;
		var dzwl=0;
		var cljk=0;
			$(function() {
				var tabs=$( "#tabs" ).tabs();
			    $("#tabs-0").load("jsp/car.jsp");
			    tabs.delegate( "span.ui-icon-close", "click", function() {
			      var panelId = $( this ).closest( "li" ).remove().attr( "aria-controls" );
			      $( "#" + panelId ).remove();
			      tabs.tabs( "refresh" );
			    });
			    tabs.bind( "keyup", function( event ) {
			      if ( event.altKey && event.keyCode === $.ui.keyCode.BACKSPACE ) {
			        var panelId = tabs.find( ".ui-tabs-active" ).remove().attr( "aria-controls" );
			        $( "#" + panelId ).remove();
			        tabs.tabs( "refresh" );
			      }
			    });
			
			});
				var tabid;
				var title;
				var url;
				        var Li="";
				function addTab(tabid,title,url) {
					if(tabid=="0"){
						ssjk=0;
					}else{
						ssjk=1;
					}
					if(tabid=="1"){
						cljk=0;
					}else{
						cljk=1;
					}
					if(tabid=="3"){
						dzwl=0;
					}else{
						dzwl=1;
					}
					if(tabid=="4"){
						qyjk=0;
					}else{
						qyjk=1;
					}
					if(tabid=="5"){
						clzjk=0;
					}else{
						clzjk=1;
					}
					var tabs=$( "#tabs" ).tabs();
					var existing = tabs.find("[id='tabs-" + tabid + "']");
					if(existing.length==0){
					    var TabName = title /* TabName 和 TabCont 可从其他地方动态加载 */
				        TabId = "tabs-" + tabid,
				       	Li = "<li><a href='#" + TabId + "' >" + TabName + "</a><span class='ui-icon ui-icon-close' role='presentation' id='tabs"+tabid+"' onclick='tabclose(this.id)'>删除标签页</span></li>";
					    tabs.find( "ul:eq(0)" ).append(Li);
					    tabs.append( "<div id='" + TabId + "' style='height: 100%; display: none;'></div>" ); 
					    tabs.tabs( "refresh" );    
					    $("#"+TabId).load(url);
					   var index = $("#"+TabId).parent().find("#"+TabId).index()-1;
					    $("#tabs").tabs({ active: index });       
					}else{
					
						var index = $("#tabs-"+tabid).parent().find("#tabs-"+tabid).index()-1;
						$("#tabs").tabs({ active: index });       
					}
				}

			
			  
		  </script>
	</head>
	<body class="smart-style-3">
		<!-- POSSIBLE CLASSES: minified, fixed-ribbon, fixed-header, fixed-width
			 You can also add different skin classes such as "smart-skin-1", "smart-skin-2" etc...-->
		
		<!-- HEADER -->
		<header id="header">
			<div id="logo-group" style="width: 70%;" >
				<!-- PLACE YOUR LOGO HERE -->
				<span id="logo"><img src="img/logo1.png" alt="SmartAdmin" style="height:55px;width:400px;"> </span> 
				<!-- END LOGO PLACEHOLDER -->
			</div>
			<!-- pulled right: nav area -->
			<div class="pull-right">

				<!-- 提交问题 -->
				<!-- 提交问题 -->	
				<div id="" class="btn-header pull-right">
					<span> <a>${realname }<i class=""></i></a> </span>
				</div>
				<!-- collapse menu button -->
				<div id="hide-menu" class="btn-header pull-right">
					<span> <a href="javascript:void(0);" title="收缩菜单栏"><i class="fa fa-reorder"></i></a> </span>
				</div>
				<!-- end collapse menu -->

				<!-- logout button -->
				<div id="logout" class="btn-header transparent pull-right">
					<span> <a href="login1.html" title="退出" data-logout-msg="确定退出？"><i class="fa fa-sign-out"></i></a> </span>
				</div>
				<!-- end logout button -->



				<!-- fullscreen button -->
				<div id="fullscreen" class="btn-header transparent pull-right">
					<span> <a href="javascript:void(0);" onclick="launchFullscreen(document.documentElement);" title="全屏"><i class="fa fa-fullscreen"></i></a> </span>
				</div>
				<!-- end fullscreen button -->


			</div>
			<!-- end pulled right: nav area -->

		</header>
		<!-- END HEADER -->

		<!-- Left panel : Navigation area -->
		<!-- Note: This width of the aside area can be adjusted through LESS variables -->
		<aside id="left-panel">

			<!-- User info -->
			<div class="login-info">
				<span> <!-- User image size is adjusted inside CSS, it should stay as it --> 
					
						<a href="javascript:void(0);" id="show-shortcut">
						<span>
							<p>导航菜单</p>
							<input type="hidden" id="index_glqx" value="${glqx}"/>
							<input type="hidden" id="index_ymqx" value="${ymqx}"/>
							<input type="hidden" id="index_dlm" value="${realname}"/>
							<input type="hidden" id="yhid" value="${id}"/>
						</span>
					</a> 
						
				</span>
			</div>
			<!-- end user info -->

			<embed id="plugin0" type="application/x-hello"  width="0" height="0"></embed>


			<!-- NAVIGATION : This navigation is also responsive

			To make this navigation dynamic please make sure to link the node
			(the reference to the nav > ul) after page load. Or the navigation
			will not initialize.
			-->
			<nav>
				<!-- NOTE: Notice the gaps after each icon usage <i></i>..
				Please note that these links work a bit different than
				traditional href="" links. See documentation for details.
				-->

				<ul>
					<li><a href="javascript:addTab('0','实时监控','jsp/car.jsp');"><i class="fa fa-lg fa-fw fa-map-marker"></i><span class="menu-item-parent">实时监控</span></a></li>
					
					<li id='index_cljk' style="display: none;">
					<a href="#"><i class="fa fa-lg fa-fw fa-random"></i><span class="menu-item-parent">车辆监控</span></a>
					<ul>
						<li id = 'index_clgz' style="display: none;">
							<a href="javascript:addTab('1','多车监控','jsp/carmonitor.jsp');">
								<i class="fa fa-lg fa-fw"></i><span class="menu-item-parent">多车监控</span>
							</a>
						</li>
						<li id = 'index_fpjk' style="display: none;">
							<a href="javascript:addTab('02','分屏监控','jsp/splitmonitor.jsp');">
								<i class="fa fa-lg fa-fw"></i><span class="menu-item-parent">分屏监控</span>
							</a>
						</li>
					</ul>
					</li>
					
					<li id = 'index_lsgj' style="display: none;"><a href="javascript:addTab('2','历史轨迹','jsp/cartrack.jsp');"><i class="fa fa-lg fa-fw fa-picture-o"></i><span class="menu-item-parent">历史轨迹</span></a></li>
					<li id = 'index_dzwl' style="display: none;"><a href="javascript:addTab('3','电子围栏','jsp/fencemonitor.jsp');"><i class="fa fa-fw fa-folder-open"></i><span class="menu-item-parent">电子围栏</span></a></li>
					<li id = 'index_qyjk' style="display: none;"><a href="javascript:addTab('4','区域监控','jsp/areamonitor.jsp');"><i class="fa fa-lg fa-fw fa-file"></i><span class="menu-item-parent">区域监控</span></a></li>
					<li id = 'index_clzjk' style="display: none;"><a href="javascript:addTab('5','车辆组监控','jsp/groupmonitor.jsp');"><i class="fa fa-lg fa-fw fa-windows"></i><span class="menu-item-parent">车辆组监控</span></a></li>
					<li id = 'index_hkspjk' style="display: none;">
<!-- 						<a href="javascript:void(0);" onclick="openUrl()"> -->
							<a href="javascript:void(0);" onclick="openUrlhk()">
					<i class="fa fa-lg fa-fw fa-inbox"></i><span class="menu-item-parent">海康视频监控</span></a></li>
					<li id = 'index_tyspjk' style="display: none;">
						<a href="javascript:void(0);" onclick="openUrl()">
					<i class="fa fa-lg fa-fw fa-inbox"></i><span class="menu-item-parent">通用视频监控</span></a></li>
					<li id = 'index_swcz' style="display: none;"><a href="javascript:addTab('8','失物查找','jsp/find/swcz.jsp');"><i class="fa fa-lg fa-fw fa-windows"></i><span class="menu-item-parent">失物查找</span></a></li>
					<li id='index_wxgl' style="display: none;">
						<a href="#"><i class="fa fa-lg fa-fw fa-pencil-square-o"></i><span class="menu-item-parent">维修管理</span></a>
						<ul>
							<li id = 'index_wxgl1' style="display: none;">
								<a href="javascript:addTab('91','维修管理','jsp/wxgl.jsp');">
									<i class="fa fa-lg fa-fw"></i><span class="menu-item-parent">维修管理</span>
								</a>
							</li>
							<li id = 'index_wxtj' style="display: none;">
								<a href="javascript:addTab('92','维修统计','jsp/wxtj.jsp');">
									<i class="fa fa-lg fa-fw"></i><span class="menu-item-parent">维修统计</span>
								</a>
							</li>
						</ul>
					</li>
					<li id='index_yysj' style="display: none;">
						<a href="#"><i class="fa fa-lg fa-fw fa-list-alt"></i><span class="menu-item-parent">统计报表</span></a>
						<ul>
							<div id="" class="item">
							<li id="index_s1" style="display: none;">
								<a href="javascript:addTab('100','营运记录查询','jsp/find/bus.jsp');">营运记录查询</a>
							</li>
							 <div class="tooltip_description" style="display:none" title="">
					       <img id ="image" src="img/Formula/15.jpg"></img>
					        </div>
					        </div>
							 <div id="" class="item">
							  <li id="index_s2" style="display: none;">
								<a href="javascript:addTab('101','车辆营运数据统计','jsp/find/vehicle.jsp');">车辆营运数据统计</a>
							</li>	
							 <div class="tooltip_description" style="display:none" title="">
					       <img id ="image" src="img/Formula/17.jpg"></img>
					        </div>
					        </div>
							<div id="" class="item">
        					<li id="index_s3" style="display: none;">
								<a href="javascript:addTab('102','在线营运率分析','jsp/findyingyun.jsp');">在线营运率分析</a>
							</li>
					        <div class="tooltip_description" style="display:none" title="">
					      <img id ="image" src="img/Formula/3.jpg"></img>
					        </div>
					      </div>
							<div id="" class="item">
        					<li id="index_s4" style="display: none;">
								<a href="javascript:addTab('103','里程利用率分析','jsp/findshizai.jsp');">里程利用率分析</a>
							</li>
					        <div class="tooltip_description" style="display:none" title="">
					       	<img  id ="image"  src="img/Formula/4.jpg"></img>
					        </div>
					      </div>
							<div id="" class="item">
        					<li id="index_s5" style="display: none;">
								<a href="javascript:addTab('111','单车平均载客里程分析','jsp/findsslicheng.jsp');">单车平均载客里程分析</a>
							</li>
					        <div class="tooltip_description" style="display:none" title="">
					          <img id ="image" src="img/Formula/8.jpg"></img>
					        </div>
					      </div>
							<div id="" class="item">
        					<li id="index_s6" style="display: none;">
								<a href="javascript:addTab('104','上线率分析','jsp/findallonline.jsp');">上线率分析</a>
							</li>
					        <div class="tooltip_description" style="display:none" title="">
								<img  id ="image" src="img/Formula/5.jpg"></img>
					        </div>
					      </div>
							<div id="" class="item">
        					<li id="index_s7" style="display: none;">
								<a href="javascript:addTab('105','重车率分析','jsp/findallload.jsp');">重车率分析</a>
							</li>
					        <div class="tooltip_description" style="display:none" title="">
					          <img id ="image" src="img/Formula/6.jpg"></img>
					        </div>
					      </div>
							<div id="" class="item">
        					<li id="index_s8" style="display: none;">
								<a href="javascript:addTab('106','单车平均营运收益分析','jsp/findyyshouyi.jsp');">单车平均营运收益分析</a>
							</li>
					        <div class="tooltip_description" style="display:none" title="">
					          <img id ="image" src="img/Formula/7.jpg"></img>
					        </div>
					      </div>
							
							
							<div id="" class="item">
							<li id="index_s11" style="display: none;">
								<a href="javascript:addTab('109','营运日报','jsp/finddayform.jsp');">营运日报</a>
							</li>
							 <div class="tooltip_description" style="display:none" title="">
					       <img id ="image" src="img/Formula/32.jpg"></img>
					        </div>
							</div>
							
							<div id="" class="item">
							<li id="index_s12" style="display: none;">
								<a href="javascript:addTab('110','营运月报','jsp/findmonthform.jsp');">营运月报</a>
							</li>
							 <div class="tooltip_description" style="display:none" title="">
					       <img id ="image" src="img/Formula/24.jpg"></img>
					        </div>
							</div>
							
							<div id="" class="item">
							<li id="index_s9" style="display: none;">
								<a href="javascript:addTab('107','无营运数据车辆查询','jsp/findoffline.jsp');">无营运数据车辆查询</a>
							</li>
							 <div class="tooltip_description" style="display:none" title="">
					       <img id ="image" src="img/Formula/21.jpg"></img>
					        </div>
					        </div>
							
							 <div id="" class="item">
							<li id="index_s10" style="display: none;">
								<a href="javascript:addTab('108','未上线车辆查询','jsp/findnoline.jsp');">未上线车辆查询</a>
							</li>
							 <div class="tooltip_description" style="display:none" title="">
					       <img id ="image" src="img/Formula/22.jpg"></img>
					        </div>
					        </div>
							
							<div id="" class="item">
							<li id="index_s13" style="display: none;">
								<a href="javascript:addTab('112','上线无营运车辆查询','jsp/findonlineandoffbus.jsp');">上线无营运车辆查询</a>
							</li>
							 <div class="tooltip_description" style="display:none" title="">
					       <img id ="image" src="img/Formula/33.jpg"></img>
					        </div>
							</div>
							
							<div id="" class="item">
							<li id="index_s14" style="display: none;">
								<a href="javascript:addTab('113','未上线营运车辆查询','jsp/findnolineandbus.jsp');">未上线营运车辆查询</a>
							</li>
							 <div class="tooltip_description" style="display:none" title="">
					       <img id ="image" src="img/Formula/34.jpg"></img>
					        </div>
							</div>
							
							<div id="" class="item">
							<li id="index_s15" style="display: none;">
								<a href="javascript:addTab('114','未上线未营运车辆查询','jsp/findnolineandoffbus.jsp');">未上线未营运车辆查询</a>
							</li>
							 <div class="tooltip_description" style="display:none" title="">
					      	 <img id ="image" src="img/Formula/35.jpg"></img>
					        </div>
							</div>
							
							<div id="" class="item">
							<li id="index_s16" style="display: none;">
								<a href="javascript:addTab('115','无空车变化查询','jsp/findnoempty.jsp');">无空车变化查询</a>
							</li>
							 <div class="tooltip_description" style="display:none" title="">
					      	 <img id ="image" src="img/Formula/35.jpg"></img>
					        </div>
							</div>
						</ul>
					</li>
					<li id ='index_jcsj' style="display: none;">
						<a href="#"><i class="fa fa-lg fa-fw fa-desktop"></i><span class="menu-item-parent">基础数据</span></a>
						<ul>
							<li id="index_j1" style="display: none;">
								<a href="javascript:addTab('200','车辆组管理','jsp/group_management.jsp');">车辆组管理</a>
							</li>
							<li id="index_j2" style="display: none;">
								<a href="javascript:addTab('201','区域管理','jsp/area_management.jsp');">区域管理</a>
							</li>
							<li id="index_j3" style="display: none;">
								<a href="javascript:addTab('202','电子围栏管理','jsp/fence_management.jsp');">电子围栏管理</a>
							</li>
							<li id="index_j4" style="display: none;">
								<a href="javascript:addTab('203','用户管理','jsp/finduser.jsp');">用户管理</a>
							</li>
							<li id="index_j5" style="display: none;">
								<a href="javascript:addTab('204','岗位管理','jsp/findstation.jsp');">岗位管理</a>
							</li>
							<li id="index_j6" style="display: none;">
								<a href="javascript:addTab('205','平台使用情况','jsp/login_history.jsp');">平台使用情况</a>
							</li>
							<li id="index_j7" style="display: none;">
								<a href="javascript:addTab('206','车辆转入申请','jsp/vehicleTApplication.jsp');">车辆转入申请</a>
							</li>
							<li id="index_j8" style="display: none;">
								<a href="javascript:addTab('207','车辆停报申请','jsp/vehicleAFStop.jsp');">车辆停报申请</a>
							</li>
							<li id="index_j9" style="display: none;">
								<a href="javascript:addTab('208','车辆号变更申请','jsp/licensePNCRequest.jsp');">车辆号变更申请</a>
							</li>
							<li id="index_j10" style="display: none;">
								<a href="javascript:addTab('209','车辆转出申请','jsp/vehicleOutApplication.jsp');">车辆转出申请</a>
							</li>
							<li id="index_j11" style="display: none;">
								<a href="javascript:addTab('210','工单查询','jsp/repairOrder.jsp');">工单查询</a>
							</li>
							<li id="index_j12">
								<a href="javascript:addTab('211','数据接入申请','jsp/dataApplication.jsp');">数据接入申请</a>
							</li>
						</ul>
					</li>
					<li id ='index_yzsjcx' style="display: none;">
						<a href="#"><i class="fa fa-lg fa-fw fa-desktop"></i><span class="menu-item-parent">运政数据查询</span></a>
						<ul>
							<li id="index_y1" style="display: none;">
								<a href="javascript:addTab('300','违章查询','jsp/findIllegal.jsp');">违章查询</a>
							</li>
							<li id="index_y2" style="display: none;">
								<a href="javascript:addTab('301','服务质量查询','jsp/findServiceQuality.jsp');">服务质量查询</a>
							</li>
							<li id="index_y3" style="display: none;">
								<a href="javascript:addTab('302','投诉查询','jsp/findComplaint.jsp');">投诉查询</a>
							</li>
						</ul>
					</li>
				</ul>
			</nav>
			
<span class="minifyme"> <i class="fa fa-arrow-circle-left hit"></i> </span>
		</aside>
		<!-- END NAVIGATION -->

		<!-- MAIN PANEL -->
		<div id="main" role="main" style="height: 90%;">


			<!-- MAIN CONTENT -->
			<div id="tabs" style="height: 100%;border: 0 none">
					<ul id="tabul">
						<li><a href="#tabs-0"  onclick="stop('0')">实时监控</a><span class='ui-icon ui-icon-close' role='presentation' id='tabs0' onclick='tabclose(this.id)'>删除标签页</span></li>
					</ul>
					<div id="tabs-0" style="height: 100%;">
					</div>
			</div>
			<!-- END MAIN CONTENT -->

		</div>
		<!-- END MAIN PANEL -->

		<!--================================================== -->
<div id="tbtc" title="车辆概况" >
	<form>
		<fieldset>
			<input name="authenticity_token" type="hidden">
			<article class="col-xs-12 col-sm-4 col-md-4 col-lg-4">
			<table>
				<tr><td>
			<div class="jarviswidget" id="wid-id-3" data-widget-editbutton="false"  style="width: 350px">
				<header>
					<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i> </span>
					<h2>车辆概况分析</h2>				
				</header>
				<div>
					<div><span id="clzs"></span></div>
					<div class="jarviswidget-editbox">
					</div>
					<div class="widget-body no-padding">
						<div id="xxf1" class="chart" style="width: 300px"></div>
					</div>
				</div>
			</div>
				</td><td>
				<div class="jarviswidget" id="wid-id-3" data-widget-editbutton="false"  style="width: 350px">
				<header>
					<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i> </span>
					<h2>车辆概况分析</h2>				
				</header>
				<div>
					<div><span id="clzx"></span></div>
					<div class="jarviswidget-editbox">
					</div>
					<div class="widget-body no-padding">
						<div id="xxf2" class="chart" style="width: 300px"></div>
					</div>
				</div>
			</div>
				</td></tr>
			</table>
		</article>
		</fieldset>
	</form>
</div>


		<!-- JS TOUCH : include this plugin for mobile drag / drop touch events
		<script src="js/plugin/jquery-touch/jquery.ui.touch-punch.min.js"></script> -->

		<!-- BOOTSTRAP JS -->
		<script src="js/bootstrap/bootstrap.min.js"></script>
		<script type="text/javascript" src="My97DatePicker/WdatePicker.js"></script> 
		<!-- CUSTOM NOTIFICATION -->
		<script src="js/notification/SmartNotification.min.js"></script>

		<!-- JARVIS WIDGETS -->
		<script src="js/smartwidgets/jarvis.widget.min.js"></script>

		<!-- EASY PIE CHARTS -->
		<script src="js/plugin/easy-pie-chart/jquery.easy-pie-chart.min.js"></script>

		<!-- SPARKLINES -->
		<script src="js/plugin/sparkline/jquery.sparkline.min.js"></script>

		<!-- JQUERY VALIDATE -->
		<script src="js/plugin/jquery-validate/jquery.validate.min.js"></script>

		<!-- JQUERY MASKED INPUT -->
		<script src="js/plugin/masked-input/jquery.maskedinput.min.js"></script>

		<!-- JQUERY SELECT2 INPUT -->
		<script src="js/plugin/select2/select2.min.js"></script>

		<!-- JQUERY UI + Bootstrap Slider -->
		<script src="js/plugin/bootstrap-slider/bootstrap-slider.min.js"></script>

		<!-- browser msie issue fix -->
		<script src="js/plugin/msie-fix/jquery.mb.browser.min.js"></script>

		<!-- FastClick: For mobile devices: you can disable this in app.js -->
		<script src="js/plugin/fastclick/fastclick.js"></script>

		<!--[if IE 7]>
			<h1>Your browser is out of date, please update your browser by going to www.microsoft.com/download</h1>
		<![endif]-->

		<!-- Demo purpose only -->
		<script src="js/demo.js"></script>

		<!-- MAIN APP JS FILE -->
		<script src="js/appsosuo.js"></script>
		<script type="text/javascript">
			
		</script>

		<!-- Your GOOGLE ANALYTICS CODE Below -->
		<script type="text/javascript">
		var index_glqx = $("#index_glqx").val();
		var index_ymqx = $("#index_ymqx").val();
		var ymqx = index_ymqx.split(',');
		var glqx = index_glqx.split(',');
		console.log(ymqx)
		for(var i=0;i<ymqx.length;i++){
// 			if(ymqx[i]=='车辆跟踪'){
// 				$("#index_clgz").css('display','');
// 			}
			if(ymqx[i]=='历史轨迹'){
				$("#index_lsgj").css('display','');
			}
			if(ymqx[i]=='电子围栏'){
				$("#index_dzwl").css('display','');
			}
			if(ymqx[i]=='区域监控'){
				$("#index_qyjk").css('display','');
			}
			if(ymqx[i]=='车辆组监控'){
				$("#index_clzjk").css('display','');
			}
			if(ymqx[i]=='视频监控'){
				$("#index_hkspjk").css('display','');
				$("#index_tyspjk").css('display','');
			}
			if(ymqx[i]=='失物查找'){
				$("#index_swcz").css('display','');
			}
			if(ymqx[i]=='多车监控'||ymqx[i]=='分屏监控'){
				$("#index_cljk").css('display','');
				if(ymqx[i]=='多车监控'){
					$("#index_clgz").css('display','');
				}
				if(ymqx[i]=='分屏监控'){
					$("#index_fpjk").css('display','');
				}
			}			
			if(ymqx[i]=='维修管理'||ymqx[i]=='维修统计'){
				$("#index_wxgl").css('display','');
				if(ymqx[i]=='维修管理'){
					$("#index_wxgl1").css('display','');
				}
				if(ymqx[i]=='维修统计'){
					$("#index_wxtj").css('display','');
				}
			}
			if(ymqx[i]=='违章查询'||ymqx[i]=='服务质量查询'||ymqx[i]=='投诉查询'){
				$("#index_yzsjcx").css('display','');
				if(ymqx[i]=='违章查询'){
					$("#index_y1").css('display','');
				}
				if(ymqx[i]=='服务质量查询'){
					$("#index_y2").css('display','');
				}
				if(ymqx[i]=='投诉查询'){
					$("#index_y3").css('display','');
				}
			}
			if(ymqx[i]=='平台使用情况'||ymqx[i]=='车辆转入申请'||ymqx[i]=='车辆停报申请'||ymqx[i]=='车辆号变更申请'||ymqx[i]=='车辆转出申请'||ymqx[i]=='工单查询'){
				$("#index_jcsj").css('display','');
				if(ymqx[i]=='平台使用情况'){
					$("#index_j6").css('display','');
				}
				if(ymqx[i]=='车辆转入申请'){
					$("#index_j7").css('display','');
				}
				if(ymqx[i]=='车辆停报申请'){
					$("#index_j8").css('display','');
				}
				if(ymqx[i]=='车辆号变更申请'){
					$("#index_j9").css('display','');
				}
				if(ymqx[i]=='车辆转出申请'){
					$("#index_j10").css('display','');
				}
				if(ymqx[i]=='工单查询'){
					$("#index_j11").css('display','');
				}
			}
			if(ymqx[i]=='营运记录查询'||ymqx[i]=='车辆营运数据统计'||ymqx[i]=='在线营运率分析'||ymqx[i]=='里程利用率分析'||ymqx[i]=='单车平均载客里程分析'||ymqx[i]=='上线率分析'||ymqx[i]=='重车率分析'||ymqx[i]=='单车平均营运收益分析'||ymqx[i]=='无营运数据车辆查询'||ymqx[i]=='未上线车辆查询'||ymqx[i]=='营运日报'||ymqx[i]=='营运月报'||ymqx[i]=='上线无营运车辆查询'||ymqx[i]=='未上线营运车辆查询'||ymqx[i]=='未上线未营运车辆查询'||ymqx[i]=='无空车变化查询'){
				$("#index_yysj").css('display','');
				if(ymqx[i]=='营运记录查询'){
					$("#index_s1").css('display','');
				}
				if(ymqx[i]=='车辆营运数据统计'){
					$("#index_s2").css('display','');
				}
				if(ymqx[i]=='在线营运率分析'){
					$("#index_s3").css('display','');
				}
				if(ymqx[i]=='里程利用率分析'){
					$("#index_s4").css('display','');
				}
				if(ymqx[i]=='单车平均载客里程分析'){
					$("#index_s5").css('display','');
				}
				if(ymqx[i]=='上线率分析'){
					$("#index_s6").css('display','');
				}
				if(ymqx[i]=='重车率分析'){
					$("#index_s7").css('display','');
				}
				if(ymqx[i]=='单车平均营运收益分析'){
					$("#index_s8").css('display','');
				}
				if(ymqx[i]=='无营运数据车辆查询'){
					$("#index_s9").css('display','');
				}
				if(ymqx[i]=='未上线车辆查询'){
					$("#index_s10").css('display','');
				}
				if(ymqx[i]=='营运日报'){
					$("#index_s11").css('display','');
				}
				if(ymqx[i]=='营运月报'){
					$("#index_s12").css('display','');
				}
				if(ymqx[i]=='上线无营运车辆查询'){
					$("#index_s13").css('display','');
				}
				if(ymqx[i]=='未上线营运车辆查询'){
					$("#index_s14").css('display','');
				}
				if(ymqx[i]=='未上线未营运车辆查询'){
					$("#index_s15").css('display','');
				}
				if(ymqx[i]=='无空车变化查询'){
					$("#index_s16").css('display','');
				}
			}
		}
		console.log(glqx)
		for(var i=0;i<glqx.length;i++){
			if(glqx[i]=='车辆组管理查看'||glqx[i]=='区域管理查看'||glqx[i]=='电子围栏管理查看'||glqx[i]=='用户管理查看'||glqx[i]=='岗位管理查看'){
					$("#index_jcsj").css('display','');
			}
			if(glqx[i]=='车辆组管理查看'||glqx[i]=='车辆组管理增加'||glqx[i]=='车辆组管理修改'||glqx[i]=='车辆组管理删除'){
					$("#index_j1").css('display','');
			}
			if(glqx[i]=='区域管理查看'||glqx[i]=='区域管理增加'||glqx[i]=='区域管理修改'||glqx[i]=='区域管理删除'){
					$("#index_j2").css('display','');
			}
			if(glqx[i]=='电子围栏管理查看'||glqx[i]=='电子围栏管理增加'||glqx[i]=='电子围栏管理修改'||glqx[i]=='电子围栏管理删除'){
					$("#index_j3").css('display','');
			}
			if(glqx[i]=='用户管理查看'||glqx[i]=='用户管理增加'||glqx[i]=='用户管理修改'||glqx[i]=='用户管理删除'){
					$("#index_j4").css('display','');
			}
			if(glqx[i]=='岗位管理查看'||glqx[i]=='岗位管理增加'||glqx[i]=='岗位管理修改'||glqx[i]=='岗位管理删除'){
					$("#index_j5").css('display','');
			}
		}
		  var _gaq = _gaq || [];
		  _gaq.push(['_setAccount', 'UA-XXXXXXXX-X']);
		  _gaq.push(['_trackPageview']);
		
		  (function() {
		    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
		    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
		    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
		  })();
			function tabclose(obj){
 				if(obj=="tabs0"){
 						ssjk=1;
 				}
 				if(obj=="tabs1"){
 						cljk=1;
 				}
 				if(obj=="tabs3"){
 						dzwl=1;
 				}
 				if(obj=="tabs4"){
 						qyjk=1;
 				}
 				if(obj=="tabs5"){
 						clzjk=1;
 				}
 			}
 			loadScript("js/plugin/flot/jquery.flot.cust.js", loadFlotResize);
	
	function loadFlotResize() {
		loadScript("js/plugin/flot/jquery.flot.resize.js", loadFlotFillbetween);
	}
	function loadFlotFillbetween() {
		loadScript("js/plugin/flot/jquery.flot.fillbetween.js", loadFlotOrderBar);
	}
	function loadFlotOrderBar() {
		loadScript("js/plugin/flot/jquery.flot.orderBar.js", loadFlotPie);
	}	
	function loadFlotPie() {
		loadScript("js/plugin/flot/jquery.flot.pie.js", loadFlotToolTip);
	}
	function loadFlotToolTip() {
		loadScript("js/plugin/flot/jquery.flot.tooltip.js", generateAllFlottb);
	}
	
	var abcdddd = false;
function tbtc(){
	if(!abcdddd){
		$('#tbtc').dialog('open');
		tbsj();
		abcdddd = !abcdddd;
	}
	// alert(abcdddd);
	
}
$('#tbtc').dialog({
	autoOpen : false,
	width : 750,
	resizable : false,
	modal : false
});
function tbtcclose(){
	if(abcdddd){
		$('#tbtc').dialog('close');
		abcdddd = !abcdddd;
	}
}
function tbsj(){
		$.ajax({
			url : 'findnum.action',
			type : 'post',
			data:{
			},
			dataType: 'json',
			timeout : 180000,
			success:function(json){
			var cust=json.num;
				var zsl=cust[0];
				var zxs=cust[1];
				var wsx=cust[2];
				var zcs=cust[3];
				var kcs=cust[4];
				$("#clzs").html("车辆总数为:"+zsl);
				$("#clzx").html("在线车辆总数为:"+zxs);
				generateAllFlottb(wsx,zcs,kcs);
				generateAllFlottbsx(zcs,kcs);
			},
			error:function(){
			}		
		});
	}

	/* chart colors default */
	var $chrt_border_color = "#efefef";
	var $chrt_grid_color = "#DDD"
	var $chrt_main = "#E24913";			/* red       */
	var $chrt_second = "#6595b4";		/* blue      */
	var $chrt_third = "#FF9F01";		/* orange    */
	var $chrt_fourth = "#7e9d3a";		/* green     */
	var $chrt_fifth = "#BD362F";		/* dark red  */
	var $chrt_mono = "#000";
	
	function generateAllFlottb(wsx,zcs,kcs) {
		
		
		/* end pie chart */
		
		/* site stats chart */
	

	
		if ($('#xxf1').length) {

				var data_pie = [];
				var series = 3;
				data_pie[0] = {
						label : "下线数",
						data :wsx
					}
					data_pie[1] = {
						label : "重车数",
						data : zcs
					}
					data_pie[2] = {
						label : "空车数",
						data : kcs
					}
				$.plot($("#xxf1"), data_pie, {
					series : {
						pie : {
							show : true,
							innerRadius : 0,
							radius : 1,
							label : {
								show : true,
								radius : 2 / 3,
								formatter : function(label, series) {
									return '<div style="font-size:16px;color:white;">'  + '<br/>' + Math.round(series.percent*((wsx+zcs+kcs)/100)) + '</div>';
								}
								
							}
						}
					},
					legend : {
						show : true,
						noColumns : 1, // number of colums in legend table
						labelFormatter : function(label, series) {
									return  '<div style="width:80px;font-size:12px;text-align:center; ">' +  label + '</div>';
								}, // fn: string -> string
						labelBoxBorderColor : "#ffa", // border color for the little label boxes
						container : null, // container (as jQuery object) to put legend in, null means default on top of graph
						position : "ne", // position of default legend container within plot
						margin : [-25, 10], // distance from grid edge to default legend container within plot
						backgroundColor : "#efefef", // null means auto-detect
						backgroundOpacity : 1 // set to 0 to avoid background
					},
					grid : {
						hoverable : true,
						clickable : true
					},
				});
	
		}
	}
	function generateAllFlottbsx(zcs,kcs) {
		
		
		/* end pie chart */
		
		/* site stats chart */
	

	
		if ($('#xxf2').length) {

				var data_pie = [];
				var series = 2;
					data_pie[0] = {
						label : "重车数",
						data : zcs
					}
					data_pie[1] = {
						label : "空车数",
						data : kcs
					}
				$.plot($("#xxf2"), data_pie, {
					series : {
						pie : {
							show : true,
							innerRadius : 0,
							radius : 1,
							label : {
								show : true,
								radius : 2 / 3,
								formatter : function(label, series) {
									return '<div style="font-size:16px;color:white;">'  + '<br/>' + Math.round(series.percent*((zcs+kcs)/100)) + '</div>';
								}
								
							}
						}
					},
					legend : {
						show : true,
						noColumns : 1, // number of colums in legend table
						labelFormatter : function(label, series) {
									return  '<div style="width:80px;font-size:12px;text-align:center; ">' +  label + '</div>';
								}, // fn: string -> string
						labelBoxBorderColor : "#ffa", // border color for the little label boxes
						container : null, // container (as jQuery object) to put legend in, null means default on top of graph
						position : "ne", // position of default legend container within plot
						margin : [-25, 10], // distance from grid edge to default legend container within plot
						backgroundColor : "#efefef", // null means auto-detect
						backgroundOpacity : 1 // set to 0 to avoid background
					},
					grid : {
						hoverable : true,
						clickable : true
					},
				});
	
		}
	}
	function plugin0()
        {
            return document.getElementById('plugin0');
        }
        plugin = plugin0;
        function addEvent(obj, name, func)
        {
            if (obj.attachEvent) {
                obj.attachEvent("on"+name, func);
            } else {
                obj.addEventListener(name, func, false); 
            }
        }
        
        function loadre()
        {
            addEvent(plugin(), 'test', function(){
                alert("Received a test event from the plugin.")
            });
        }
        function pluginLoaded() {
            alert("Plugin loaded!");
        }
        
        function addTestEvent()
        {
            addEvent(plugin(), 'echo', function(txt,count){
                alert(txt+count);
            });
        }
        
        function testEvent()
        {
            plugin().testEvent();
        }
        
        function pluginValid()
        {
            if(plugin().valid){
            alert(plugin().echo("This plugin seems to be working!"));
            } else {
                alert("Plugin is not working :(");
            }
        }
        function openUrl()
        {
            if(plugin().valid){
            plugin().openUrl(window.location.origin+"/taximonitor/jsp/tongyongvideo.htm");
            } else {
                alert("请使用火狐浏览器并且安装插件");
            }
        }
        function openUrlhk()
        {
            if(plugin().valid){
            plugin().openUrl(window.location.origin+"/taximonitor/jsp/hkvideo.action?yh="+$('#yhid').val());
            } else {
                alert("请使用火狐浏览器并且安装插件");
            }
        }
 		loadre();
		</script>

	</body>

</html>