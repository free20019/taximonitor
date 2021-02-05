<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
contentType="text/html; charset=UTF-8" %>
<div class="row" style="height: 100%" >
<input type="hidden" id="dzwsum" value="${id}"/>
<input type="hidden" id="fance_glqx" value="${glqx}"/>
<!-- widget grid -->
	<!-- row -->
		<div style="width: 100%;height: 60%" id="updiv2udfance"><div id="fance-div-udarea" class="google_maps" style="height: 100%;width: 100%">&nbsp;</div></div>
	<TABLE border=0 cellPadding=0 cellSpacing=0 height="9px" width="100%" style="background-color: #D0DBD7;padding: -5px;" >
          <TBODY>
            <TR> 
              <TD onclick=switchSysBar2udfance() style="HEIGHT: 9px;cursor:pointer;" align="center"><i class="fa fa-chevron-down" id="utod2udfance"><input type="hidden" id="switchPoint2udfance" value="3"></i></TD>
            </TR>
          </TBODY>
        </TABLE>	
		
	<div style="width: 100%;height: 35%;overflow: auto;" id="downdiv2udfance">
			<div style="overflow: auto;" class="jarviswidget jarviswidget-color-blueDark" id="wid-id-0" data-widget-colorbutton="false" data-widget-editbutton="false" data-widget-custombutton="false">
				<header>
					<span class="widget-icon"> <i class="fa fa-edit"></i> </span>
					<h2>区域详细 &nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);" id="fancegl_add" style = "display:none" class="btn btn-primary" onclick="addudfence();">添加区域</a></h2>
				</header>
				<div>
					<div class="jarviswidget-editbox">
					</div>
					<div class="widget-body">
						<form id="udareaform">
						<table id="fencetable" class="table table-striped table-bordered table-hover" style="height: 100%">
							<thead>
								<tr>
									<th nowrap="nowrap">序号</th>
									<th nowrap="nowrap">区域名称</th>
									<th nowrap="nowrap">区域描述</th>
									<th nowrap="nowrap">区域车辆组</th>
									<th nowrap="nowrap">区域面积</th>
									<th nowrap="nowrap">操作</th>
								</tr>
								
							</thead>
							<tbody id="fencetbody">
							</tbody>
						</table>
					</form>
					</div>
				</div>
			</div>
			</div>
</div>
<div id="fendfence" title="区域详情">
	<p>
	<form action="" id="checkout-form" class="smart-form" novalidate="novalidate" >
	<table border="0">
	<tr>
		<td valign="top">区域名称:&nbsp;&nbsp;&nbsp;</td>
		<td>
		<label class="input"  > 
			<input type="text" name=""  id="fencename">
		</label>
		</td>
	</tr>
	<tr>
		<td valign="top">区域描述:&nbsp;&nbsp;&nbsp;</td>
		<td>
		<textarea cols="40" rows="5" id="fencems"></textarea>
		</td>
	</tr>
	<tr>
		<td valign="top">区域面积:&nbsp;&nbsp;&nbsp;</td>
		<td>
		<label class="input"  > 
			<input type="text" name=""  id="fencesize" disabled="disabled">
		</label>
		</td>
	</tr>
	<tr>
		<td valign="top">区域车辆组:&nbsp;&nbsp;&nbsp;</td>
		<td>
		<section class="col col-5" style="width: 200px;float:left;" >
									<label class="select">
										<select  id="fencevhic" class="select">
											<option value="0" >--选择群组--</option>
										</select> <i></i> 
									</label>
									</section>
		</td>
	</tr>
	<tr>
		<td valign="top">区域坐标:&nbsp;&nbsp;&nbsp;</td>
		<td>
		<textarea cols="40" rows="5" id="fencezb" disabled="disabled"></textarea>
		</td>
	</tr>
	</table>
	</form>
	</p>
</div>
<div id="fenceedit" title="区域修改">
	<p>
	<input type="hidden" id="udfenceidedit" value="">
	<form action="" id="checkout-form" class="smart-form" novalidate="novalidate" >
	<table border="0">
	<tr>
		<td valign="top">区域名称:&nbsp;&nbsp;&nbsp;</td>
		<td >
		<label class="input"  > 
			<input type="text" name=""  id="udfencenameedit">
		</label>
		</td>
	</tr>
	<tr>
		<td valign="top">区域描述:&nbsp;&nbsp;&nbsp;</td>
		<td>
		<textarea cols="40" rows="5" id="udfencemsedit"></textarea>
		</td>
	</tr>
	<tr>
		<td valign="top">区域面积:&nbsp;&nbsp;&nbsp;</td>
		<td>
		<label class="input"  > 
			<input type="text" name=""  id="udfencesizeedit" disabled="disabled">
		</label>
		</td>
	</tr>
	<tr>
		<td valign="top">区域车辆组:&nbsp;&nbsp;&nbsp;</td>
		<td>
		<section class="col col-5" style="width: 200px;float:left;" >
									<label class="select">
										<select  id="fencevhicedit" class="select">
										</select> <i></i> 
									</label>
									</section>
		</td>
	</tr>
	<tr>
		<td valign="top">区域坐标:&nbsp;&nbsp;&nbsp;</td>
		<td>
		<textarea cols="40" rows="5" id="udfencezbsedit" disabled="disabled"></textarea>
		</td>
	</tr>
	</table>
	</form>
	</p>
</div>
<style>
.txtstyle{position:relative;}
.txtstyle span{
white-space:nowrap;
	display:block;
	text-align:left;
	background-color: #3399CC;
	color:#FFFFFF;
	width:auto;
	border:1px solid #3399CC;
	FONT-FAMILY:微软雅黑;
	position:absolute;
	top:-10px;left:25px;
	white-space:nowrap webkit-border-radius:5px;
	border-radius:5px;
}
#tip{
			position:absolute;
			right:30px;
			top:40px;
			border-radius:3px;
			line-height:30px;
		}
</style>
<script language="javascript" type="text/javascript" src="js/car/fence_management.js"></script>
<link rel="stylesheet" type="text/css" media="screen" href="css/bootstrap.min.css">
<script type="text/javascript">
	var area_glqx = $("#fance_glqx").val();
	if(area_glqx!=null&&area_glqx.length>0){
		var a = area_glqx.split(',');
		for(var i=0;i<a.length;i++){
			if(a[i]=='电子围栏管理增加'){
				$("#fancegl_add").css('display','');
			}
		}
	}
</script>