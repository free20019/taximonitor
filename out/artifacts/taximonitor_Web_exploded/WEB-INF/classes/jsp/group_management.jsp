<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
contentType="text/html; charset=UTF-8" %>
<script type="text/javascript" src="js/car/group_management.js"></script>
<script src="js/jquery.blockUI.js"></script>
 <div class="row"  style=" height:100%;overflow: scroll">
 <table border="0"  style="width: 102%;">
	<tr>
	<input type="hidden" id="clzum" value="${id}"/>
	<input type="hidden" id="groupgl_glqx" value="${glqx}"/>
		<td height="50px;">
			<div class="jarviswidget jarviswidget-color-darken" id="wid-id-2" data-widget-editbutton="false">
				<header style = "text-align:right;">
					<span class="widget-icon"> <i class="fa fa-table"></i> </span>
					<h2 id="clzgl">车辆组管理</h2>
				</header>
				<!-- widget div-->
				<div>
					<div class="jarviswidget-editbox">
					</div>
					<div class="widget-body no-padding">
						<form action="" id="msg-form" class="smart-form" novalidate="novalidate" >
								<section class="col col-5" style="width: 200px;float:left;" >
									<label class="select">
										<select  id="groupf" class="select">
											<option value="0" >--选择群组--</option>
										</select> <i></i> 
									</label>
									</section>
								<a href="javascript:void(0);" id ="searchf" style = "display:none" class="btn btn-primary" onclick="findcargroupvhic();" data-msg="searchf">查   询</a>
								<a href="javascript:void(0);"  id="add-groupf" style = "display:none" class="btn btn-primary "><strong onclick="addcargroup();">添加车辆组</strong></a>
								<a href="javascript:void(0);"  id="update-groupf" style = "display:none" class="btn btn-primary "><strong onclick="editcargroup();">修改车辆组</strong></a>
								<a href="javascript:void(0);"  id="delete-groupf" style = "display:none" class="btn btn-primary "><strong onclick="delcargroup();">删除车辆组</strong></a>
						</form>		
		<table id="dt_basicc6" class="table table-striped table-bordered table-hover">
							<thead>
								<tr >
									<td nowrap="nowrap">序号</td>	
									<td nowrap="nowrap">车号</td>
									<td nowrap="nowrap">SIM卡号</td>
									<td nowrap="nowrap">司机姓名</td>
									<td nowrap="nowrap">联系电话</td>
									<td nowrap="nowrap">车辆颜色</td>	
									<td nowrap="nowrap">终端类型</td>	
									<td nowrap="nowrap">车辆类型</td>	
								</tr>
							</thead>
							<tbody id="tbf">
								
							</tbody>
							
						</table>
	</div>
</div>
							
</div></td></tr></table></div>
<div id="addgroupf" title="新增">
		<fieldset>
		<table>
			<tr><td style="width: 260px;">
			<input name="authenticity_token" type="hidden">
			<div class="form-group">
				<label>车辆组名:</label>
				<input class="form-control" id="vhicgroupadd"  placeholder="--输入车辆组名--" type="text">
			</div>
			
			</td>
			</tr>
		</table>
			
			<table style="float:left;">
				<tr><td><label>车号:</label></td><td>按钮</td><td>车辆组车号</td></tr>
				<tr><td>
				<section>
					<label class="select select-multiple">
						<select multiple="" class="custom-scroll" style="height: 300px;width: 200px" id="group-add">
						</select> </label>
				</section>
				</td>
				<td>
					&nbsp;&nbsp;<input name="" type="button" id="addall" style=" width:30px; height:30px;  border:0; background:url(img/anniu/yy.png)" />&nbsp;&nbsp;<br/><br/>
					&nbsp;&nbsp;<input id="addone" type="button"  style=" width:30px; height:30px;  border:0; background:url(img/anniu/y.png)" />&nbsp;&nbsp;<br/><br/>
					&nbsp;&nbsp;<input id="delone" type="button"  style=" width:30px; height:30px;  border:0; background:url(img/anniu/z.png)" />&nbsp;&nbsp;<br/><br/>
					&nbsp;&nbsp;<input id="delall" type="button"  style=" width:30px; height:30px;  border:0; background:url(img/anniu/zz.png)" />
				</td>
				<td>
				<section>
					<label class="select select-multiple">
						<select multiple="" class="custom-scroll" style="height: 300px;width: 200px" id="sel-add">
						</select> </label>
				</section>
				</td>
				</tr>
			</table>
		</fieldset>
</div>
<div id="editgroupf" title="修改">
	<form>
		<fieldset>
			<input name="authenticity_token" type="hidden">
			<div class="form-group">
				<label>车辆组名:</label>
				<input type="hidden" id="vhicgroupidedit">
				<input class="form-control" id="vhicgroupedit"  placeholder="--输入车辆组名--" type="text">
			</div>
			<table style="float:left;">
				<tr><td><label>车号:</label></td><td>按钮</td><td>车辆组车号</td></tr>
				<tr><td>
				<section>
					<label class="select select-multiple">
						<select multiple="" class="custom-scroll" style="height: 300px;width: 200px" id="group-edit">
						</select> </label>
				</section>
				</td>
				<td>
					&nbsp;&nbsp;<input id="addalls" type="button"  style=" width:30px; height:30px;  border:0; background:url(img/anniu/yy.png)" />&nbsp;&nbsp;<br/><br/>
					&nbsp;&nbsp;<input id="addones" type="button"  style=" width:30px; height:30px;  border:0; background:url(img/anniu/y.png)" />&nbsp;&nbsp;<br/><br/>
					&nbsp;&nbsp;<input id="delones" type="button"  style=" width:30px; height:30px;  border:0; background:url(img/anniu/z.png)" />&nbsp;&nbsp;<br/><br/>
					&nbsp;&nbsp;<input id="delalls" type="button"  style=" width:30px; height:30px;  border:0; background:url(img/anniu/zz.png)" />
				</td>
				<td>
				<section>
					<label class="select select-multiple">
						<select multiple="" class="custom-scroll" style="height: 300px;width: 200px" id="sel-edit">
						</select> </label>
				</section>
				</td>
				</tr>
			</table>
		</fieldset>
	</form>
</div>
<script type="text/javascript">
	$("#clzgl").html("车辆组管理");
	var area_glqx = $("#groupgl_glqx").val();
	if(area_glqx!=null&&area_glqx.length>0){
		var a = area_glqx.split(',');
		for(var i=0;i<a.length;i++){
			if(a[i]=='车辆组管理查看'){
				$("#searchf").css('display','');
			}
			if(a[i]=='车辆组管理增加'){
				$("#add-groupf").css('display','');
			}
			if(a[i]=='车辆组管理修改'){
				$("#update-groupf").css('display','');
			}
			if(a[i]=='车辆组管理删除'){
				$("#delete-groupf").css('display','');
			}
		}
	}
</script>