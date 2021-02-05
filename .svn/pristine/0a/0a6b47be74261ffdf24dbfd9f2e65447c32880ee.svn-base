<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!-- http://127.0.0.1:8080/taximonitor/jsp/hkvideo.action?yh=1 -->
<!DOCTYPE HTML >
<html lang="zh-cn" style="height:100%">
<head>
<link rel="stylesheet" type="text/css"
	href="../css/zTreeStyle/zTreeStyle.css" />
<script src="../js/libs/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="../js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="../js/jquery.resizableColumns.js"></script>
</head>
<body style="height: 95%;">
<div style="height: 20px;">
	<div style="width:20%;height: 100%;float: left;">1</div>
	<div style="width:60%;height: 100%;float: left;">2</div>
	<div style="width:20%;height: 100%;float: left;">3</div>
</div>
	<div style="height: 98%;">
		<input type="hidden" id="RegIp" value="192.168.0.151">&nbsp;&nbsp;
		<!-- 注册服务器ip 端口port -->
		<input type="hidden" id="RegPort" value="7660">&nbsp;&nbsp; <input
			type="hidden" id="StreamIp" value="192.168.0.151">&nbsp;&nbsp;
		<!-- 流媒体服务器ip 端口port -->
		<input type="hidden" id="StreamPort" value="7554">&nbsp;&nbsp;
		<input type="hidden" id="AlarmIp" value="192.168.0.151">&nbsp;&nbsp;
		<!-- 报警服务器ip 端口port -->
		<input type="hidden" id="AlarmPort" value="7332">&nbsp;&nbsp;
		<input type="hidden" id="RegisterNo" value="2">&nbsp;&nbsp;
		<!-- 设备注册线路编号 -->
		<input type="hidden" id="StreamType" value="1">&nbsp;&nbsp;
		<!-- 流码类型 -->
		<div style="width:20%;height: 100%;float: left;overflow-y: auto;overflow-x: hidden;" title="车辆列表">
			<input type="text" placeholder="车牌号码" id="vediokeyword"
				onchange="getteam();">
			<ul id="databanzu" class="ztree">
			</ul>

		</div>
		<div style='width: 60%;height: 100%;float: left;' id="hksp">
			<table cellpadding='0' cellspacing='0' border='1'
				bordercolor='#ffffff' width='100%' height="100%" align='center'>
				<tr>
					<td><object id='v1'
							classid='clsid:EE4EA829-B8DA-4229-AC72-585AF45AD778' width='100%'
							height='100%' style='margin: 0;'></object></td>
					<td style="height: 99%;width:3px"></td>
					<td><object id='v2'
							classid='clsid:EE4EA829-B8DA-4229-AC72-585AF45AD778' width='100%'
							height='100%' style='margin: 0;'></object></td>
				</tr>
				<tr>
					<td><object id='v3'
							classid='clsid:EE4EA829-B8DA-4229-AC72-585AF45AD778' width='100%'
							height='100%' style='margin: 0;'></object></td>
					<td style="height: 100%;width:3px"></td>
					<td><object id='v4'
							classid='clsid:EE4EA829-B8DA-4229-AC72-585AF45AD778' width='100%'
							height='100%' style='margin: 0;'></object></td>
				</tr>
			</table>
		</div>
		<div style='width: 80%;height: 100%;float: left;display: none'
			id="tysp">
			<OBJECT codebase=".\TLMDVRAx.CAB#version=1,0,4,5" ID="TLMDvrAx"
				name="TLMDvrAx" CLASSID="CLSID:15AB9520-D6EF-499B-AB7F-906AB0BA04AD"
				events="true" HEIGHT=581 WIDTH=709 align="top"></OBJECT>
		</div>
		<div style='width: 20%;height: 100%;float: left;' id="caozuo">
			<table width="100%" class="table listext" data-resizable-columns-id="demo-table">
		<thead>
			<tr>
				<th data-resizable-column-id="#">#</th>
				<th data-resizable-column-id="first_name">First Name</th>
				<th data-resizable-column-id="last_name">Last Name</th>
				<th data-resizable-column-id="username" id="username-column" data-noresize>Username</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>1</td>
				<td>Mark</td>
				<td>Otto</td>
				<td>@mdo</td>
			</tr>
			<tr>
				<td>2</td>
				<td>Jacob</td>
				<td>Thornton</td>
				<td>@fat</td>
			</tr>
			<tr>
				<td>3</td>
				<td colspan="2">Larry the Bird</td>
				<td>@twitter</td>
			</tr>
		</tbody>
	</table>
		
		</div>
	</div>
	<script type="text/javascript">
		window.onload = function() {
			$("table").resizableColumns({});
			getteam();
			try {
				//根据控件的PorgID来判断是否注册，未注册则抛出异常
				obj = new ActiveXObject("PPVSGUARD.PpvsguardCtrl.1");

				//设置窗口模式
				var PlayOCX = document.getElementById("v1");

				if (typeof PlayOCX == "undefined" || PlayOCX == null) {
					alert("海康视频控件已注册但页面对象不存在！");
					return;
				}
				PlayOCX.SetActiveXShowMode(0, 0);//实时预览模式，需要修改模式或者窗口UI可参考SetActiveXShowMode接口说明
			} catch (e) {
				alert("海康视频控件未注册或未安装！");
			}
		}

		function do_login() {
			document.getElementById("TLMDvrAx").LoginStyle = 0;
			var szServerIP = "192.168.0.82";
			var nSeverPort = "10000";
			var szUserName = "admin";
			var szPassWord = "njtynjty";
			var nRet = document.getElementById("TLMDvrAx").LoginServer(
					szServerIP, nSeverPort, szUserName, szPassWord);
			if (nRet) {
				document.getElementById("TLMDvrAx").ShowWndStyle = 4;
			} else {
				alert("登陆失败！");
			}
		}

		var treeNodes;
		var zTree;
		function getteam() {
			$.ajax({
				async : false,
				cache : false,
				url : 'queryvediovhic.action',
				type : 'post',
				data : {
					"keyword" : $("#vediokeyword").val()
				},
				dataType : 'json',
				timeout : 120000,
				success : function(json) {
					treeNodes = eval('(' + json.cars + ')');
					zTree = $.fn.zTree
							.init($("#databanzu"), setting, treeNodes);
				},
				error : function() {
					alert("提交失败!");
				}
			});
		}
		var playid1 = "0";
		var playid2 = "0";
		var playid3 = "0";
		var playid4 = "0";

		var oc=0;

		var setting = {
			async : {
				enable : true,
				url : "queryvediovhic.action",
				dataType : "text",
				type : "post"
			},
			data : {
				simpleData : {
					enable : true,
					idKey : "id",
					pIdKey : "pId",
					rootPId : 0
				}
			},
			callback : {
				onClick : function(event, treeId, treeNode) {
					if (treeNode.lx == "hksp") {
						$("#hksp").css('display', '');
						$("#tysp").css('display', 'none');
						if (treeNode.id == "td1") {
							if (playid1 == "0") {
								treeNode.icon = "../img/video/mdton.png";
								zTree.updateNode(treeNode);
								playid1 = "1";
								Play1(treeNode.camera);
							} else {
								treeNode.icon = "../img/video/mdt.png";
								zTree.updateNode(treeNode);
								var obj = document.getElementById("v1");
								obj.StreamPlayStopByTCP();
								playid1 = "0";
							}
						} else if (treeNode.id == "td2") {
							if (playid2 == "0") {
								treeNode.icon = "../img/video/mdton.png";
								zTree.updateNode(treeNode);
								playid2 = "1";
								Play2(treeNode.camera);
							} else {
								treeNode.icon = "../img/video/mdt.png";
								zTree.updateNode(treeNode);
								var obj = document.getElementById("v2");
								obj.StreamPlayStopByTCP();
								playid2 = "0";
							}
						} else if (treeNode.id == "td3") {
							if (playid3 == "0") {
								treeNode.icon = "../img/video/mdton.png";
								zTree.updateNode(treeNode);
								playid3 = "1";
								Play3(treeNode.camera);
							} else {
								treeNode.icon = "../img/video/mdt.png";
								zTree.updateNode(treeNode);
								var obj = document.getElementById("v3");
								obj.StreamPlayStopByTCP();
								playid3 = "0";
							}
						} else if (treeNode.id == "td4") {
							if (playid4 == "0") {
								treeNode.icon = "../img/video/mdton.png";
								zTree.updateNode(treeNode);
								playid4 = "1";
								Play4(treeNode.camera);
							} else {
								treeNode.icon = "../img/video/mdt.png";
								zTree.updateNode(treeNode);
								var obj = document.getElementById("v4");
								obj.StreamPlayStopByTCP();
								playid4 = "0";
							}
						}
					} else if (treeNode.lx == "tysp") {
						$("#tysp").css('display', '');
						$("#hksp").css('display', 'none');
						do_login();
						if(oc==0){
							start_chn(treeNode.vehinum.substr(1),parseInt(treeNode.td));
							treeNode.icon = "../img/video/mdton.png";
							zTree.updateNode(treeNode);
							oc=1;
						}else if(oc==1){
							stop_chn();
							treeNode.icon = "../img/video/mdt.png";
							zTree.updateNode(treeNode);
							oc=0;
						}
					}
					//alert("dns:"+treeNode.camera);
				}
			}
		}
		var iPlayHandle1;
		var iPlayHandle2;
		var iPlayHandle3;
		var iPlayHandle4;
		function Play1(obj) {

			var szAccount = obj;
			//注册服务IP、端口
			var szRegIP = document.getElementById("RegIp").value;
			var iRegPort = document.getElementById("RegPort").value;
			var PlayOCX = document.getElementById("v1");

			var iConnHandle = PlayOCX.ConnectDeviceByACS(szAccount, szRegIP,
					iRegPort, "admin", "12345");

			if (iConnHandle < 0) {
				alert("连接设备失败！");
				return;
			}
			var iChannel = "1";
			var iRegisterNo = document.getElementById("RegisterNo").value;
			//流媒体服务器信息
			var szStreamServerIP = document.getElementById("StreamIp").value;
			var iStreamServerPort = document.getElementById("StreamPort").value;

			//有视频播放，则先停止
			if (iPlayHandle1 > 0) {
				PlayOCX.StreamPlayStopByTCP();
			}
			//设置设备注册线路编号，以iVMS-7200添加设备选择注册线路匹配
			PlayOCX.SetDeviceNetLine(iRegisterNo);
			//取流类型
			var iStreamType = document.getElementById("StreamType").value;

			//客户端到流媒体采用TCP，流媒体到设备采用TCP取流
			iPlayHandle1 = PlayOCX.StreamPlayStartByTCP(szStreamServerIP,
					iStreamServerPort, szAccount, iChannel, iStreamType, 1);

			//设置窗口的设备信息显示
			var szMode = "TCP";
			PlayOCX.SetDeviceInfoForShow(">>> 转发取流(" + szMode + ") <<<");
		}

		function Play2(obj) {

			var szAccount = obj;
			//注册服务IP、端口
			var szRegIP = document.getElementById("RegIp").value;
			var iRegPort = document.getElementById("RegPort").value;
			var PlayOCX = document.getElementById("v2");

			var iConnHandle = PlayOCX.ConnectDeviceByACS(szAccount, szRegIP,
					iRegPort, "admin", "12345");

			if (iConnHandle < 0) {
				alert("连接设备失败！");
				return;
			}
			var iChannel = "2";
			var iRegisterNo = document.getElementById("RegisterNo").value;
			//流媒体服务器信息
			var szStreamServerIP = document.getElementById("StreamIp").value;
			var iStreamServerPort = document.getElementById("StreamPort").value;

			//有视频播放，则先停止
			if (iPlayHandle2 > 0) {
				PlayOCX.StreamPlayStopByTCP();
			}
			//设置设备注册线路编号，以iVMS-7200添加设备选择注册线路匹配
			PlayOCX.SetDeviceNetLine(iRegisterNo);
			//取流类型
			var iStreamType = document.getElementById("StreamType").value;

			//客户端到流媒体采用TCP，流媒体到设备采用TCP取流
			iPlayHandle2 = PlayOCX.StreamPlayStartByTCP(szStreamServerIP,
					iStreamServerPort, szAccount, iChannel, iStreamType, 1);

			//设置窗口的设备信息显示
			var szMode = "TCP";
			PlayOCX.SetDeviceInfoForShow(">>> 转发取流(" + szMode + ") <<<");
		}
		function Play3(obj) {

			var szAccount = obj;
			//注册服务IP、端口
			var szRegIP = document.getElementById("RegIp").value;
			var iRegPort = document.getElementById("RegPort").value;
			var PlayOCX = document.getElementById("v3");

			var iConnHandle = PlayOCX.ConnectDeviceByACS(szAccount, szRegIP,
					iRegPort, "admin", "12345");

			if (iConnHandle < 0) {
				alert("连接设备失败！");
				return;
			}
			var iChannel = "3";
			var iRegisterNo = document.getElementById("RegisterNo").value;
			//流媒体服务器信息
			var szStreamServerIP = document.getElementById("StreamIp").value;
			var iStreamServerPort = document.getElementById("StreamPort").value;

			//有视频播放，则先停止
			if (iPlayHandle3 > 0) {
				PlayOCX.StreamPlayStopByTCP();
			}
			//设置设备注册线路编号，以iVMS-7200添加设备选择注册线路匹配
			PlayOCX.SetDeviceNetLine(iRegisterNo);
			//取流类型
			var iStreamType = document.getElementById("StreamType").value;

			//客户端到流媒体采用TCP，流媒体到设备采用TCP取流
			iPlayHandle3 = PlayOCX.StreamPlayStartByTCP(szStreamServerIP,
					iStreamServerPort, szAccount, iChannel, iStreamType, 1);

			//设置窗口的设备信息显示
			var szMode = "TCP";
			PlayOCX.SetDeviceInfoForShow(">>> 转发取流(" + szMode + ") <<<");
		}
		function Play4(obj) {

			var szAccount = obj;
			//注册服务IP、端口
			var szRegIP = document.getElementById("RegIp").value;
			var iRegPort = document.getElementById("RegPort").value;
			var PlayOCX = document.getElementById("v4");

			var iConnHandle = PlayOCX.ConnectDeviceByACS(szAccount, szRegIP,
					iRegPort, "admin", "12345");

			if (iConnHandle < 0) {
				alert("连接设备失败！");
				return;
			}
			var iChannel = "4";
			var iRegisterNo = document.getElementById("RegisterNo").value;
			//流媒体服务器信息
			var szStreamServerIP = document.getElementById("StreamIp").value;
			var iStreamServerPort = document.getElementById("StreamPort").value;

			//有视频播放，则先停止
			if (iPlayHandle4 > 0) {
				PlayOCX.StreamPlayStopByTCP();
			}
			//设置设备注册线路编号，以iVMS-7200添加设备选择注册线路匹配
			PlayOCX.SetDeviceNetLine(iRegisterNo);
			//取流类型
			var iStreamType = document.getElementById("StreamType").value;

			//客户端到流媒体采用TCP，流媒体到设备采用TCP取流
			iPlayHandle4 = PlayOCX.StreamPlayStartByTCP(szStreamServerIP,
					iStreamServerPort, szAccount, iChannel, iStreamType, 1);

			//设置窗口的设备信息显示
			var szMode = "TCP";
			PlayOCX.SetDeviceInfoForShow(">>> 转发取流(" + szMode + ") <<<");
		}

		function start_chn(vhic,td)
		{
			document.getElementById("TLMDvrAx").PlayVideo(vhic, td);
		}

		function stop_chn()
		{
			document.getElementById("TLMDvrAx").StopVideo();
		}
	</script>
	
	<style type="text/css">
*{margin:0;padding:0;list-style-type:none;}
a,img{border:0;}
table{empty-cells:show;border-collapse:collapse;border-spacing:0;}
body{font:12px/180% Arial, Helvetica, sans-serif, "新宋体";}

.demo{width:760px;margin:40px auto;}
.demo h2{font-size:18px;height:52px;color:#3366cc;text-align:center;}
.listext th{background:#eee;color:#3366cc;}
.listext th,.listext td{border:solid 1px #ddd;text-align:left;padding:10px;font-size:14px;}

.rc-handle-container{position:relative;}
.rc-handle{position:absolute;width:7px;cursor:ew-resize;*cursor:pointer;margin-left:-3px;}
</style>
</body>
</html>