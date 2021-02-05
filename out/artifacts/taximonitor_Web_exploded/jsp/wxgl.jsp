<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!-- <link rel="stylesheet" type="text/css" media="screen" href="/../css/bootstrap.min.css"> -->
<!-- <link rel="stylesheet" type="text/css" media="screen" href="/../css/map.css"> -->
<!-- widget grid -->
<div class="row" style="height:600px;overflow: scroll">
<input type="hidden" id="actionidwxgl" value="${actionid }"/>
<input type="hidden" id="datawxgl" value="${data }"/>
	<!-- row -->
<table border="0"  style="width: 102%;">
	<tr>
		<td>
			<div class="jarviswidget jarviswidget-color-darken" id="wid-id-2" data-widget-editbutton="false">
				<header style = "text-align:right;">
					<span class="widget-icon"> <i class="fa fa-table"></i> </span>
					<h2 id="wxgl">维修统计</h2>
				</header>
				<!-- widget div-->
				<div>
					<div class="jarviswidget-editbox">
					</div>
					<div class="widget-body no-padding">
						<form action="" id="msg-form" class="smart-form" novalidate="novalidate" >
							<section style="width: 130px;float:left;">
								<label class="input">
									<input id="wxglcphm" type="text" list="wxglcphm1" placeholder="--车牌号码--" >
									<datalist id="wxglcphm1" >
									
									</datalist> 
								</label>
							</section>
							<a href="javascript:void(0);" style="float:left;" id ="getwxgl" class="btn btn-primary" data-msg="wxgl">查   询</a>
						</form></br>
						<div class="row">
							<div  style="width: 100%;  position: relative;">
								<article class="col-xs-12 col-sm-4 col-md-4 col-lg-4" style="width: 98%;left:12px">
									<div class="jarviswidget" id="wid-id-3" data-widget-editbutton="false">
										<header>
											<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i> </span>
											<h2>车辆信息</h2>				
										</header>
											<div style="border-style:none;">
												<section style="width:85px;float:left;">
														<label class="font" >
															<span>所属公司:</span>
														</label>
												</section>
												<section style="width:260px;float:left;">
														<label class="input">
															<span id="wxglssgs"></span>
														</label>
												</section>
												<section style="width:85px;float:left;">
														<label class="font" >
															<span >车主姓名:</span>
														</label>
												</section>
												<section style="width:200px;float:left;">
														<label class="input"> 
															<span id="wxglczxm"></span>
														</label>
												</section>
												<section style="width:85px;float:left;">
														<label class="font" >
															<span >车主电话:</span>
														</label>
												</section>
												<section style="width:200px;float:left;">
														<label class="input">
															<span id="wxglczdh"></span>
														</label>
												</section>
											</div>
											<div style="border-style:none;">
												<section style="width:85px;float:left;">
														<label class="font" >
															<span >终端类型:</span>
														</label>
												</section>
												<section style="width:260px;float:left;">
														<label class="input">
															<span id="wxglzdlx"></span>
														</label>
												</section>
												<section style="width:85px;float:left;">
														<label class="font" >
															<span >终端子类型:</span>
														</label>
												</section>
												<section style="width:200px;float:left;">
														<label class="input"> 
															<span id="wxglzdzlx"></span>
														</label>
												</section>
												<section style="width:85px;float:left;">
														<label class="font" >
															<span >虚拟网:</span>
														</label>
												</section>
												<section style="width:200px;float:left;">
														<label class="input">
															<span id="wxglxnw"></span>
														</label>
												</section>
											</div>
											<div style="border-style:none;">
												<section style="width:85px;float:left;">
														<label class="font" >
															<span >SIM卡号:</span>
														</label>
												</section>
												<section style="width:260px;float:left;">
														<label class="input">
															<span id="wxglsimkh"></span>
														</label>
												</section>
												<section style="width:85px;float:left;">
														<label class="font" >
															<span >终端编号:</span>
														</label>
												</section>
												<section style="width:200px;float:left;">
														<label class="input"> 
															<span id="wxglzdbh"></span>
														</label>
												</section>
												<section style="width:85px;float:left;">
														<label class="font" >
															<span >初装时间:</span>
														</label>
												</section>
												<section style="width:200px;float:left;">
														<label class="input">
															<span id="wxglczsj"></span>
														</label>
												</section>
											</div>
											<div style="border-style:none;">
												<section style="width:85px;float:left;">
														<label class="font" >
															<span >备注:</span>
														</label>
												</section>
												<section style="float:left;">
														<label class="input">
															<span id="wxglbz"></span>
														</label>
												</section>
											</div>
										</div>
								</article>
							</div>
						</div>
						<a href="javascript:void(0);" style="float:left;margin-left: 10px;" id ="getwxgladd" onclick="setwxgl();" class="btn btn-primary" data-msg="wxgl">添    加</a>
						<a href="javascript:void(0);" style="float:left;margin-left: 10px;" id ="getwxgledit" onclick="editwxgl();" class="btn btn-primary" data-msg="wxgl">修    改</a>
						<a href="javascript:void(0);" style="float:left;margin-left: 10px;" id ="getwxgldel" onclick="delwxgl();" class="btn btn-primary" data-msg="wxgl">删    除</a>
						<table id="dt_basicc6" class="table table-striped table-bordered table-hover">
							<thead>
								<tr >
									<td nowrap="nowrap"></td>	
									<td nowrap="nowrap">序号</td>	
									<td nowrap="nowrap">车牌号码</td>	
									<td nowrap="nowrap">送修时间</td>
									<td nowrap="nowrap">完修时间</td>
									<td nowrap="nowrap">故障类型</td>
									<td nowrap="nowrap">故障现象</td>
									<td nowrap="nowrap">维修地点</td>
									<td nowrap="nowrap">维修类型</td>
									<td nowrap="nowrap">维修内容</td>
									<td nowrap="nowrap">维修费用</td>
								</tr>
							</thead>
							<tbody id="wxgltbody">
								
							</tbody>
							
						</table>
							
					</div>
				 
				  </div>  
			  </div>
		</td>
	</tr>
</table>
</div>
<div id="addwxgl" title="新增">
	<form>
		<fieldset>
			<input name="authenticity_token" type="hidden">
			<input name="authenticity_token" type="hidden">
				<table style="width:100%;border-collapse:separate; border-spacing:10px;">
					<tr style="margin-top: 3px;">
						<td>
							<label>车牌号码</label><input type="text" name="cphm" id="wxglcphms" class="wxglclear">
						</td>
						<td>
							<label>联系人&nbsp;&nbsp;&nbsp;&nbsp;</label><input type="text" name="lxr" id="wxgllxr" class="wxglclear">
						</td>
					</tr>
					<tr>
						<td>
							<label>联系电话</label><input type="text" name="lxdh" id="wxgllxdh" class="wxglclear">
						</td>
						<td>
							<label>故障现象</label>
							<select style="width:160px;" id="wxglgzxx" name="gzxx"></select>
						</td>
					</tr>
					<tr>
						<td>
							<label>故障描述</label>
							<select style="width:160px;" id="wxglgzms" name="gzms"></select>
						</td>
						<td>
							<label>维修类型</label>
							<select style="width:160px;" id="wxglwxlx" name="wxlx"></select>
<!-- 							<select> -->
<!-- 								<option value="一般维修">一般维修</option> -->
<!-- 								<option value="不能现场处理">不能现场处理</option> -->
<!-- 								<option value="简单维修">简单维修</option> -->
<!-- 								<option value="自查维修">自查维修</option> -->
<!-- 								<option value="通知维修">通知维修</option> -->
<!-- 							</select> -->
						</td>
					</tr>
					<tr>
						<td>
							<label>当前故障</label><input type="text" name="dqgz" id="wxgldqgz" class="wxglclear">
						</td>
						<td>
							<label>维修费用</label><input type="text" name="wxfy" id="wxglwxfy" class="wxglclear">
						</td>
					</tr>
					<tr>
						<td>
							<label>维修人员</label><input type="text" name="wxry" id="wxglwxry" class="wxglclear">
						</td>
						<td>
							<label>维修地点</label>
							<select style="width:160px;" id="wxglwxdd" name="wxdd"></select>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<label>维修内容</label><input type="text" name="wxnr" class="wxglclear" style="width:75%" id="wxglwxnr">
						</td>
					</tr>
					<tr>
						<td>
							<label>送修时间</label><input type="text" name="sxsj" class="wxglclear" id="wxglsxsj" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss', minDate: '%y-%M-%d 00:00:00',maxDate:'%y-%M-%d 23:59:59'});">
						</td>
						<td>
							<label>完修时间</label><input type="text" class="wxsj" name="yemian" id="wxglwxsj" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss', minDate: '%y-%M-%d 00:00:00',maxDate:'%y-%M-%d 23:59:59'});">
						</td>
					</tr>
					<tr>
						<td>
							<label>客户满意度</label>
							<select style="width:160px;" id="wxglmyd" name="khmyd">
								<option value="满意">满意</option>
								<option value="不满意">不满意</option>
								<option value="投诉">投诉</option>
								<option value="表扬">表扬</option>
							</select>
						</td>
					</tr>
				</table>
		</fieldset>
	</form>
</div>

<div id="editwxgl" title="修改">
	<form>
		<fieldset>
			<input name="authenticity_token" type="hidden">
			<input name="authenticity_token" type="hidden">
				<table style="width:100%;border-collapse:separate; border-spacing:10px;">
					<tr style="margin-top: 3px;">
						<td>
							<label>车牌号码</label><input type="text" name="cphm" id="wxglcphmse" class="wxglclear">
						</td>
						<td>
							<label>联系人&nbsp;&nbsp;&nbsp;&nbsp;</label><input type="text" name="lxr" id="wxgllxre" class="wxglclear">
						</td>
					</tr>
					<tr>
						<td>
							<label>联系电话</label><input type="text" name="lxdh" id="wxgllxdhe" class="wxglclear">
						</td>
						<td>
							<label>故障现象</label>
							<select style="width:160px;" id="wxglgzxxe" name="gzxx"></select>
						</td>
					</tr>
					<tr>
						<td>
							<label>故障描述</label>
							<select style="width:160px;" id="wxglgzmse" name="gzms"></select>
						</td>
						<td>
							<label>维修类型</label>
							<select style="width:160px;" id="wxglwxlxe" name="wxlx"></select>
<!-- 							<select> -->
<!-- 								<option value="一般维修">一般维修</option> -->
<!-- 								<option value="不能现场处理">不能现场处理</option> -->
<!-- 								<option value="简单维修">简单维修</option> -->
<!-- 								<option value="自查维修">自查维修</option> -->
<!-- 								<option value="通知维修">通知维修</option> -->
<!-- 							</select> -->
						</td>
					</tr>
					<tr>
						<td>
							<label>当前故障</label><input type="text" name="dqgz" id="wxgldqgze" class="wxglclear">
						</td>
						<td>
							<label>维修费用</label><input type="text" name="wxfy" id="wxglwxfye" class="wxglclear">
						</td>
					</tr>
					<tr>
						<td>
							<label>维修人员</label><input type="text" name="wxry" id="wxglwxrye" class="wxglclear">
						</td>
						<td>
							<label>维修地点</label>
							<select style="width:160px;" id="wxglwxdde" name="wxdd"></select>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<label>维修内容</label><input type="text" name="wxnr" class="wxglclear" style="width:75%" id="wxglwxnre">
						</td>
					</tr>
					<tr>
						<td>
							<label>送修时间</label><input type="text" name="sxsj" class="wxglclear" id="wxglsxsje" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss', minDate: '%y-%M-%d 00:00:00',maxDate:'%y-%M-%d 23:59:59'});">
						</td>
						<td>
							<label>完修时间</label><input type="text" class="wxsj" name="yemian" id="wxglwxsje" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss', minDate: '%y-%M-%d 00:00:00',maxDate:'%y-%M-%d 23:59:59'});">
						</td>
					</tr>
					<tr>
						<td>
							<label>客户满意度</label>
							<select style="width:160px;" id="wxglmyde" name="khmyd">
								<option value="满意">满意</option>
								<option value="不满意">不满意</option>
								<option value="投诉">投诉</option>
								<option value="表扬">表扬</option>
							</select>
						</td>
					</tr>
				</table>
		</fieldset>
	</form>
</div>
<script type="text/javascript">
$("#wxgl").html("维修统计");
var wxgldata = [];
var wgglid = '';
$('#getwxgl').on('click', function(){
	$.blockUI({message:"<div style='padding:5px;'><h3><font color='blue'>数据查询中,请稍候...</font></h3><img src='img/ajax-loader.gif' border='0'/><br/><br/></div>"});
	$.ajax({
		url:"findwxgl.action",
		data:{
			"quyu":$("#wxglcphm").val()
		},
		dataType:"JSON",
		type:"POST",
		async:true,
		success:function(data){
			$.unblockUI();
			$('#wxgltbody').html('');
			console.log(data.l[0])
			wxgldata = [];
			if(data.l[0].info.length ==0)
				return "无车辆信息";
			var vehicle = data.l[0].info[0]
			$("#wxglssgs").html(vehicle.COMP_NAME);
			$("#wxglczxm").html(vehicle.OWN_NAME);
			$("#wxglczdh").html(vehicle.OWN_TEL);
			$("#wxglzdlx").html(vehicle.MT_NAME);
			$("#wxglzdzlx").html(vehicle.MDT_SUB_TYPE);
			$("#wxglxnw").html(vehicle.VSIM_NUM);
			$("#wxglsimkh").html(vehicle.VEHI_SIM);
			$("#wxglzdbh").html(vehicle.MDT_NO);
			$("#wxglczsj").html(vehicle.VEHI_DATE);
			$("#wxglbz").html(vehicle.NOTE);
			var tab = "";
			var wxxx = data.l[0].wxxx;
			wxgldata = wxxx;
			for(var i=0; i<wxxx.length; i++){
				tab += '<tr><td><input type="checkbox"  name="wxglhhh" value="'+wxxx[i].RR_ID+'" /></td>' +
				'<td>' + (i + 1) + '</td>' +
				'<td>' + wxxx[i].VEHI_NO + '</td>' +
				'<td>' + wxxx[i].RR_TIME + '</td>' +
				'<td>' + wxxx[i].RR_TIME_END + '</td>' +
				'<td>' + (wxxx[i].RM_MALFUNCTION1==null?'':wxxx[i].RM_MALFUNCTION1) + '</td>' +
				'<td>' + wxxx[i].RM_MALFUNCTION + '</td>' +
				'<td>' + wxxx[i].RA_ADDR + '</td>' +
				'<td>' + wxxx[i].RT_TYPE + '</td>' +
				'<td>' + wxxx[i].RC_CONTENT + '</td>' +
				'<td>' + wxxx[i].RR_COST + '</td>';
			}
			$('#wxgltbody').html(tab);
		}
	});
})

$.ajax({
	url : 'findvhic1.action',
	type : 'post',
	data : {},
	dataType : 'json',
	timeout : 180000,
	success : function(json) {
		var cust=json.list;
		$("#wxglcphm1").empty();
		var tab="";
		for ( var i = 0; i < cust.length; i++) {
			tab+="<option value='"+cust[i].vehi_no+"'></option>";
		}
		$("#wxglcphm1").html(tab);
	},
	error : function() {

	}
});
function setwxgl(){
	$('#addwxgl').dialog('open');
	$.ajax({
		url : 'findwxinfolist.action',
		type : 'post',
		data : {
			"quyu" : "TB_REPAIR_MALFUNCTION"
		},
		dataType : 'json',
		timeout : 180000,
		success : function(json) {
			var cust=json.wx;
			$("#wxglgzxx").empty();
			for ( var i = 0; i < cust.length; i++) {
				$("#wxglgzxx").append("<option value='"+cust[i].id+"'>"+cust[i].name+"</option>");
			}
		},
		error : function() {

		}
	});
	$.ajax({
		url : 'findwxinfolist.action',
		type : 'post',
		data : {
			"quyu" : "TB_REPAIR_CONTENT"
		},
		dataType : 'json',
		timeout : 180000,
		success : function(json) {
			var cust=json.wx;
			$("#wxglgzms").empty();
			for ( var i = 0; i < cust.length; i++) {
				$("#wxglgzms").append("<option value='"+cust[i].id+"'>"+cust[i].name+"</option>");
			}
		},
		error : function() {

		}
	});
	$.ajax({
		url : 'findwxinfolist.action',
		type : 'post',
		data : {
			"quyu" : "TB_REPAIR_TYPE"
		},
		dataType : 'json',
		timeout : 180000,
		success : function(json) {
			var cust=json.wx;
			$("#wxglwxlx").empty();
			for ( var i = 0; i < cust.length; i++) {
				$("#wxglwxlx").append("<option value='"+cust[i].id+"'>"+cust[i].name+"</option>");
			}
		},
		error : function() {

		}
	});
	$.ajax({
		url : 'findwxinfolist.action',
		type : 'post',
		data : {
			"quyu" : "TB_REPAIR_ADDR"
		},
		dataType : 'json',
		timeout : 180000,
		success : function(json) {
			var cust=json.wx;
			$("#wxglwxdd").empty();
			for ( var i = 0; i < cust.length; i++) {
				$("#wxglwxdd").append("<option value='"+cust[i].id+"'>"+cust[i].name+"</option>");
			}
		},
		error : function() {

		}
	});
	$('.wxglclear').val('');
}
$('#addwxgl').dialog({
	autoOpen : false,
	width : 650,
	resizable : false,
	modal : true,
	buttons : [{
			html : "<i class='fa fa-times'></i>&nbsp; 取消",
			"class" : "btn btn-default",
			click : function() {
				$(this).dialog("close");
			}
		}, {

			html : "<i class='fa fa-plus'></i>&nbsp; 增加",
			"class" : "btn btn-danger",
			click : function() {
				addwxgl();
// 				$(this).dialog("close");
			}
		}]
});
function addwxgl(){
	if($('#wxglcphms').val() == ''){
		alert('车牌号码不能为空！');
		return;
	}
	if($('#wxglsxsj').val() == ''){
		alert('送修时间不能为空！');
		return;
	}
	if($('#wxglwxsj').val() == ''){
		alert('完修时间不能为空！');
		return;
	}
	$.ajax({
		url : 'addwxgl.action',
		type : 'post',
		data : {
			"cphm" : $('#wxglcphms').val(),
			"lxr" : $('#wxgllxr').val(),
			"lxdh" : $('#wxgllxdh').val(),
			"gzxx" : $('#wxglgzxx').val(),
			"gzms" : $('#wxglgzms').val(),
			"wxlx" : $('#wxglwxlx').val(),
			"dqgz" : $('#wxgldqgz').val(),
			"wxfy" : $('#wxglwxfy').val(),
			"wxry" : $('#wxglwxry').val(),
			"wxdd" : $('#wxglwxdd').val(),
			"wxnr" : $('#wxglwxnr').val(),
			"sxsj" : $('#wxglsxsj').val(),
			"wxsj" : $('#wxglwxsj').val(),
			"myd" : $('#wxglmyd').val()
		},
		dataType : 'json',
		timeout : 180000,
		success : function(json) {
			if(json.compname == '添加成功'){
				$('#addwxgl').dialog("close");
				alert(json.compname);
				$('#getwxgl').click();
			}else{
				alert(json.compname);
			}
		},
		error : function() {

		}
	});
}

function editwxgl(){
	var s="";
	$('input[name="wxglhhh"]:checked').each(function(){
		s+=$(this).val()+' ';
	});
	var checkbox=s.split(' ');
	if(checkbox.length<2){
		alert("请选择数据进行修改!");
	}else if(checkbox.length>2){
		alert("每次只能修改一条数据!");
	}else{
		$('#editwxgl').dialog('open');
		$.ajax({
			url : 'findwxinfolist.action',
			type : 'post',
			data : {
				"quyu" : "TB_REPAIR_MALFUNCTION"
			},
			dataType : 'json',
			timeout : 180000,
			success : function(json) {
				var cust=json.wx;
				$("#wxglgzxxe").empty();
				for ( var i = 0; i < cust.length; i++) {
					$("#wxglgzxxe").append("<option value='"+cust[i].id+"'>"+cust[i].name+"</option>");
				}
				for(var i=0; i<wxgldata.length; i++){
					if(wxgldata[i].RR_ID == s.trim()){
						$("#wxglgzxxe").val(wxgldata[i].RM_ID)
					}
				}
			},
			error : function() {
	
			}
		});
		$.ajax({
			url : 'findwxinfolist.action',
			type : 'post',
			data : {
				"quyu" : "TB_REPAIR_CONTENT"
			},
			dataType : 'json',
			timeout : 180000,
			success : function(json) {
				var cust=json.wx;
				$("#wxglgzmse").empty();
				for ( var i = 0; i < cust.length; i++) {
					$("#wxglgzmse").append("<option value='"+cust[i].name+"'>"+cust[i].name+"</option>");
				}
				for(var i=0; i<wxgldata.length; i++){
					if(wxgldata[i].RR_ID == s.trim()){
						$("#wxglgzmse").val(wxgldata[i].RM_MALFUNCTION)
					}
				}
			},
			error : function() {
	
			}
		});
		$.ajax({
			url : 'findwxinfolist.action',
			type : 'post',
			data : {
				"quyu" : "TB_REPAIR_TYPE"
			},
			dataType : 'json',
			timeout : 180000,
			success : function(json) {
				var cust=json.wx;
				$("#wxglwxlxe").empty();
				for ( var i = 0; i < cust.length; i++) {
					$("#wxglwxlxe").append("<option value='"+cust[i].id+"'>"+cust[i].name+"</option>");
				}
				for(var i=0; i<wxgldata.length; i++){
					if(wxgldata[i].RR_ID == s.trim()){
						$("#wxglwxlxe").val(wxgldata[i].RT_ID)
					}
				}
			},
			error : function() {
	
			}
		});
		$.ajax({
			url : 'findwxinfolist.action',
			type : 'post',
			data : {
				"quyu" : "TB_REPAIR_ADDR"
			},
			dataType : 'json',
			timeout : 180000,
			success : function(json) {
				var cust=json.wx;
				$("#wxglwxdde").empty();
				for ( var i = 0; i < cust.length; i++) {
					$("#wxglwxdde").append("<option value='"+cust[i].id+"'>"+cust[i].name+"</option>");
				}
				for(var i=0; i<wxgldata.length; i++){
					if(wxgldata[i].RR_ID == s.trim()){
						$("#wxglwxdde").val(wxgldata[i].RA_ID)
					}
				}
			},
			error : function() {
	
			}
		});
		$('.wxglclear').val('');
		for(var i=0; i<wxgldata.length; i++){
			if(wxgldata[i].RR_ID == s.trim()){
				console.log(wxgldata[i])
				$('#wxglcphmse').val(wxgldata[i].VEHI_NO),
				$('#wxgllxre').val(wxgldata[i].VEHI_NAME);
				$('#wxgllxdhe').val(wxgldata[i].VEHI_PHONE);
				$('#wxglwxfye').val(wxgldata[i].RR_COST);
				$('#wxglwxrye').val(wxgldata[i].USER_ID);
				$('#wxglwxnre').val(wxgldata[i].RC_CONTENT);
				$('#wxglsxsje').val(wxgldata[i].RR_TIME.length>10?wxgldata[i].RR_TIME.substr(0,wxgldata[i].RR_TIME.length-2):wxgldata[i].RR_TIME);
				$('#wxglwxsje').val(wxgldata[i].RR_TIME_END.length>10?wxgldata[i].RR_TIME_END.substr(0,wxgldata[i].RR_TIME_END.length-2):wxgldata[i].RR_TIME_END);
				$('#wxglmyde').val(wxgldata[i].TCSS);
				wgglid = wxgldata[i].RR_ID;
			}
		}
	}
}
$('#editwxgl').dialog({
	autoOpen : false,
	width : 650,
	resizable : false,
	modal : true,
	buttons : [{
			html : "<i class='fa fa-times'></i>&nbsp; 取消",
			"class" : "btn btn-default",
			click : function() {
				$(this).dialog("close");
			}
		}, {

			html : "<i class='fa fa-plus'></i>&nbsp; 修改",
			"class" : "btn btn-danger",
			click : function() {
				editwxgls();
// 				$(this).dialog("close");
			}
		}]
});
function editwxgls(){
	if($('#wxglcphmse').val() == ''){
		alert('车牌号码不能为空！');
		return;
	}
	if($('#wxglsxsje').val() == ''){
		alert('送修时间不能为空！');
		return;
	}
	if($('#wxglwxsje').val() == ''){
		alert('完修时间不能为空！');
		return;
	}
	$.ajax({
		url : 'editwxgl.action',
		type : 'post',
		data : {
			"cphm" : $('#wxglcphmse').val(),
			"lxr" : $('#wxgllxre').val(),
			"lxdh" : $('#wxgllxdhe').val(),
			"gzxx" : $('#wxglgzxxe').val(),
			"gzms" : $('#wxglgzmse').val(),
			"wxlx" : $('#wxglwxlxe').val(),
			"dqgz" : $('#wxgldqgze').val(),
			"wxfy" : $('#wxglwxfye').val(),
			"wxry" : $('#wxglwxrye').val(),
			"wxdd" : $('#wxglwxdde').val(),
			"wxnr" : $('#wxglwxnre').val(),
			"sxsj" : $('#wxglsxsje').val(),
			"wxsj" : $('#wxglwxsje').val(),
			"myd" : $('#wxglmyde').val(),
			"id" : wgglid
		},
		dataType : 'json',
		timeout : 180000,
		success : function(json) {
			console.log(json)
			if(json.compname == '修改成功'){
				$('#editwxgl').dialog("close");
				alert(json.compname);
				$('#getwxgl').click();
			}else{
				alert(json.compname);
			}
		},
		error : function() {

		}
	});
}
function delwxgl(){
	var s="";
	$('input[name="wxglhhh"]:checked').each(function(){
		s+=$(this).val()+' ';
	});
	var checkbox=s.split(' ');
	if(checkbox.length<2){
		alert("请选择数据进行修改!");
	}else if(checkbox.length>2){
		alert("每次只能修改一条数据!");
	}else{
		$.ajax({
			url : 'delwxgl.action',
			type : 'post',
			data : {
				"id" : s.trim()
			},
			dataType : 'json',
			timeout : 180000,
			success : function(json) {
				console.log(json)
				if(json.compname == '删除成功'){
					alert(json.compname);
					$('#getwxgl').click();
				}else{
					alert(json.compname);
				}
			},
			error : function() {

			}
		});
	}
}
</script>