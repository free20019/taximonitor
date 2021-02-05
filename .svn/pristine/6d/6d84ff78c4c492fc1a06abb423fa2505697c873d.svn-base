<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
contentType="text/html; charset=UTF-8" %>
<div class="row" style="height: 100%" >
<!-- widget grid -->
<input type="hidden" id="compid" value="${date}">
<input type="hidden" id="userid" value="${userid}">
<input type="hidden" id="qyid" value="${id}"/>
<input type="hidden" id="area_glqx" value="${glqx}"/>
	<!-- row -->
		<div style="width: 100%;height: 60%" id="updiv2ud"><div id="data-div-udarea" class="google_maps" style="height: 100%;width: 100%">&nbsp;</div></div>
		<!-- 
		<div id="tip"> 
		<a href="javascript:void(0);" class="btn btn-primary" onclick="addarea();">添加区域</a>&nbsp;&nbsp;
		</div>
		<div id="tip2" style="display: none"> 
		<a href="javascript:void(0);" class="btn btn-primary" onclick="addarea();">完成编辑区域</a>&nbsp;&nbsp;
		</div>
		 -->
	<TABLE border=0 cellPadding=0 cellSpacing=0 height="9px" width="100%" style="background-color: #D0DBD7;padding: -5px;" >
          <TBODY>
            <TR> 
              <TD onclick=switchSysBar2ud() style="HEIGHT: 9px;cursor:pointer;" align="center"><i class="fa fa-chevron-down" id="utod2ud"><input type="hidden" id="switchPoint2ud" value="3"></i></TD>
            </TR>
          </TBODY>
        </TABLE>	
		
	<div style="width: 100%;height: 35%;overflow: auto;" id="downdiv2ud">
			<div style="overflow: auto;" class="jarviswidget jarviswidget-color-blueDark" id="wid-id-0" data-widget-colorbutton="false" data-widget-editbutton="false" data-widget-custombutton="false">
				<header>
					<span class="widget-icon"> <i class="fa fa-edit"></i> </span>
					<h2>区域详细 &nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);" id="area_add" class="btn btn-primary" style = "display:none" onclick="addudarea();">添加区域</a></h2>
				</header>
				<div>
					<div class="jarviswidget-editbox">
					</div>
					<div class="widget-body">
						<form id="udareaform">
						<table id="udareatable" class="table table-striped table-bordered table-hover" style="height: 100%">
							<thead>
								<tr>
									<th nowrap="nowrap">序号</th>
									<th nowrap="nowrap">区域名称</th>
									<th nowrap="nowrap">区域描述</th>
									<th nowrap="nowrap">区域面积</th>
									<th nowrap="nowrap">操作</th>
								</tr>
								
							</thead>
							<tbody id="udareadata">
							</tbody>
						</table>
					</form>
					</div>
				</div>
			</div>
			</div>
</div>
<div id="dialog_udarea" title="区域详情">
	<p>
	<form action="" id="checkout-form" class="smart-form" novalidate="novalidate" >
	<table border="0">
	<tr>
		<td valign="top">区域名称:&nbsp;&nbsp;&nbsp;</td>
		<td>
		<label class="input"  > 
			<input type="text" name=""  id="udareaname">
		</label>
		</td>
	</tr>
	<tr>
		<td valign="top">区域描述:&nbsp;&nbsp;&nbsp;</td>
		<td>
		<textarea cols="40" rows="5" id="udareams"></textarea>
		</td>
	</tr>
	<tr>
		<td valign="top">区域面积:&nbsp;&nbsp;&nbsp;</td>
		<td>
		<label class="input"  > 
			<input type="text" name=""  id="udareasize" disabled="disabled">
		</label>
		</td>
	</tr>
	<tr>
		<td valign="top">区域坐标:&nbsp;&nbsp;&nbsp;</td>
		<td>
		<textarea cols="40" rows="5" id="udareazbs" disabled="disabled"></textarea>
		</td>
	</tr>
	</table>
	</form>
	</p>
</div>
<div id="udarea_edit" title="区域修改">
	<p>
	<input type="hidden" id="udareaidedit" value="">
	<form action="" id="checkout-form" class="smart-form" novalidate="novalidate" >
	<table border="0">
	<tr>
		<td valign="top">区域名称:&nbsp;&nbsp;&nbsp;</td>
		<td >
		<label class="input"  > 
			<input type="text" name=""  id="udareanameedit">
		</label>
		</td>
	</tr>
	<!-- 
	<tr>
		<td valign="top">预报警数:&nbsp;&nbsp;&nbsp;</td>
		<td>
		<label class="input"  > 
			<input type="text" name=""  id="areabjsedit">
		</label>
		</td>
	</tr>
	 -->
	<tr>
		<td valign="top">区域描述:&nbsp;&nbsp;&nbsp;</td>
		<td>
		<textarea cols="40" rows="5" id="udareamsedit"></textarea>
		</td>
	</tr>
	<tr>
		<td valign="top">区域面积:&nbsp;&nbsp;&nbsp;</td>
		<td>
		<label class="input"  > 
			<input type="text" name=""  id="udareasizeedit" disabled="disabled">
		</label>
		</td>
	</tr>
	<tr>
		<td valign="top">区域坐标:&nbsp;&nbsp;&nbsp;</td>
		<td>
		<textarea cols="40" rows="5" id="udareazbsedit" disabled="disabled"></textarea>
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
<script language="javascript" type="text/javascript" src="js/car/area_management.js"></script>
<link rel="stylesheet" type="text/css" media="screen" href="css/bootstrap.min.css">
<script type="text/javascript">
	var area_glqx = $("#area_glqx").val();
	if(area_glqx!=null&&area_glqx.length>0){
		var a = area_glqx.split(',');
		for(var i=0;i<a.length;i++){
			if(a[i]=='区域管理增加'){
				$("#area_add").css('display','');
			}
		}
	}
</script>