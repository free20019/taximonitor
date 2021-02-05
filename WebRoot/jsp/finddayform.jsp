﻿ <%@ page language="java" import="java.util.*" pageEncoding="utf-8"
contentType="text/html; charset=UTF-8" %>
<section id="widget-grid" class="">
<input type="hidden" id="rbbum" value="${username }"/>
<!-- widget grid -->
<div class="row" style="height:600px;overflow: scroll">
<input type="hidden" id="date1" value="${date}">
	<!-- row -->
<table border="1" style="width: 102%;">
	<tr style="height: 150px;overflow: auto;">
		<td>
			<div class="jarviswidget jarviswidget-color-darken" id="wid-id-2" data-widget-editbutton="false">
				<header style = "text-align:right;">
					<span class="widget-icon"> <i class="fa fa-table"></i> </span>
					<h2 id="yyrbb">日报表</h2>
				</header>
				<!-- widget div-->
				<div>
					<div class="jarviswidget-editbox">
					</div>
					<div class="widget-body no-padding">
					<form action="" id="msg-form" class="smart-form" novalidate="novalidate" >
								<section style="width:180px;float:left;">
										<label class="input"> <i class="icon-append fa fa-calendar"></i>
											 <input id="dayformtime" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})()"></input>
										</label>
								</section>
								<section style="width:160px;float:left;">
										<label class="select">
											<select name="name" id="name">
											</select> <i></i> </label>
								</section>
								<a href="javascript:void(0);"  class="btn btn-primary" onclick="finddayform1();">查   询</a>
								<a href="javascript:void(1);" class="btn btn-primary"  onclick="finddayformexcle();">导   出</a><span id="dayformdaochu"></span>
						</form>
						
						<table id="fuel_dayform1" class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th>序号</th>
									<th>日期</th>
									<th>总车辆数</th>
									<th>营运车辆数</th>
									<th>出车率</th>
									<th>平均周转次数</th>
									<th>平均营收金额</th>
									<th>平均实载率</th>
									<th>平均重车时间(分)</th>
									<th>平均等候时间(分)</th>
									<th>平均实载里程(公里)</th>
									<th>平均空驶里程(公里)</th>
								</tr>
							</thead>
							<tbody id="dayformtbody">
							</tbody>
						</table>

					</div>
				</div>
			</div>
		</td>
	</tr>
</table>
</div>
<script type="text/javascript">
	
	// DO NOT REMOVE : GLOBAL FUNCTIONS!
	pageSetUp();
	var myDate = new Date();
	var a=myDate.getFullYear();
	var b=myDate.getMonth()+1+"";
	if(b.length<2){
		b="0"+b;
	}
	var day = myDate.getDate();
	if(day.length<2){
		day="0"+day;
	}
	$("#dayformtime").val(a+"-"+b+"-"+day);
	//loadDataTableScripts_dayform1();
	
	// PAGE RELATED SCRIPTS
		var guanli=$("#date1").val().split(',');
		$("#name").empty();
		$("#name").append("<option value='0'>所有公司</option>");
		for(var i=0;i<guanli.length;i++){
			$("#name").append("<option value='"+guanli[i]+"'>"+guanli[i]+"</option>");
		}
	finddayform1();
	function finddayform1(){
	$.blockUI({message:"<div style='padding:5px;'><h3><font color='blue'>数据查询中,请稍候...</font></h3><img src='img/ajax-loader.gif' border='0'/><br/><br/></div>"});
		$.ajax({
			url : 'finddayform.action',
			type : 'post',
			data:{
				'time' : $("#dayformtime").val(),
				'ba_name':$("#name").val()
			},
			dataType: 'json',
			timeout : 180000,
			success:function(json){
				//$('#fuel_dayform1').dataTable().fnDestroy(); 
					var quanxian=json.list;
					var tab="";
					if(quanxian!=null){
					for(var i=0;i<quanxian.length;i++){
						tab=tab+"<tr><td nowrap='nowrap'>"+(i+1)+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+quanxian[i].report_time+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+quanxian[i].repore_vhic+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+quanxian[i].repore_vhicno+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+parseInt((parseInt(quanxian[i].repore_vhicno)/quanxian[i].repore_vhic)*100)+"%</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+quanxian[i].repore_no+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+quanxian[i].repore_amount_revenue+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+quanxian[i].repore_actual_loading+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+quanxian[i].reproe_revenue_time+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+quanxian[i].repore_wait_time+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+quanxian[i].repore_actual_mileage+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+quanxian[i].repore_empty_mileage+"</td></tr>";
					}
					$("#dayformtbody").html(tab);
					//loadDataTableScripts_dayform1();
					}
			},
			error:function(){
				
			}		
		});
		$.unblockUI();
	}
	//导出excle
		function finddayformexcle(){
	$.blockUI({message:"<div style='padding:5px;'><h3><font color='blue'>数据查询中,请稍候...</font></h3><img src='img/ajax-loader.gif' border='0'/><br/><br/></div>"});
	if(window.confirm("确定按上述条件把数据集导成Excel？")){
		$.ajax({
		url : 'finddayformexcle.action',
		type : 'post',
		data:{
				'time' : $("#dayformtime").val(),
				'ba_name':$("#name").val()
		},
		dataType: 'json',
		timeout : 180000,
		success:function(json){
			window.location.href=json.action;
			$.unblockUI();
		},
		error:function(){
			
		}
		});
	}
}
$("#yyrbb").html("日报表");
//树
	$('.tree > ul').attr('role', 'tree').find('ul').attr('role', 'group');
	$('.tree').find('li:has(ul)').addClass('parent_li').attr('role', 'treeitem').find(' > span').attr('title', 'Collapse this branch').on('click', function(e) {
		var children = $(this).parent('li.parent_li').find(' > ul > li');
		if (children.is(':visible')) {
			children.hide('fast');
			$(this).attr('title', 'Expand this branch').find(' > i').removeClass().addClass('fa fa-lg fa-plus-circle');
		} else {
			children.show('fast');
			$(this).attr('title', 'Collapse this branch').find(' > i').removeClass().addClass('fa fa-lg fa-minus-circle');
		}
		e.stopPropagation();
	});
		
	if($('.DTTT_dropdown.dropdown-menu').length){
		$('.DTTT_dropdown.dropdown-menu').remove();
	}

	var fuel_dayform1="";
	function loadDataTableScripts_dayform1() {

		loadScript("js/plugin/datatables/jquery.dataTables-cust.min.js", dt_2);

		function dt_2() {
			loadScript("js/plugin/datatables/ColReorder.min.js", dt_3);
		}

		function dt_3() {
			loadScript("js/plugin/datatables/FixedColumns.min.js", dt_4);
		}

		function dt_4() {
			loadScript("js/plugin/datatables/ColVis.min.js", dt_5);
		}

		function dt_5() {
			loadScript("js/plugin/datatables/ZeroClipboard.js", dt_6);
		}

		function dt_6() {
			loadScript("js/plugin/datatables/media/js/TableTools.min.js", dt_7);
		}

		function dt_7() {
			loadScript("js/plugin/datatables/DT_bootstrap.js", runDataTables_dayform1);
		}

	}

	function runDataTables_dayform1() {

		/*
		 * BASIC
		 */
		fuel_dayform1=$('#fuel_dayform1').dataTable({
			"sPaginationType" : "bootstrap_full",
			"bRetrieve": true
		});

		/* END BASIC */

		/* Add the events etc before DataTables hides a column */
		$("#datatable_fixed_column thead input").keyup(function() {
			oTable.fnFilter(this.value, oTable.oApi._fnVisibleToColumnIndex(oTable.fnSettings(), $("thead input").index(this)));
		});

		$("#datatable_fixed_column thead input").each(function(i) {
			this.initVal = this.value;
		});
		$("#datatable_fixed_column thead input").focus(function() {
			if (this.className == "search_init") {
				this.className = "";
				this.value = "";
			}
		});
		$("#datatable_fixed_column thead input").blur(function(i) {
			if (this.value == "") {
				this.className = "search_init";
				this.value = this.initVal;
			}
		});		
		

		var oTable = $('#datatable_fixed_column').dataTable({
			"sDom" : "<'dt-top-row'><'dt-wrapper't><'dt-row dt-bottom-row'<'row'<'col-sm-6'i><'col-sm-6 text-right'p>>",
			//"sDom" : "t<'row dt-wrapper'<'col-sm-6'i><'dt-row dt-bottom-row'<'row'<'col-sm-6'i><'col-sm-6 text-right'>>",
			"oLanguage" : {
				"sSearch" : "Search all columns:"
			},
			"bSortCellsTop" : true
		});		
		


		/*
		 * COL ORDER
		 */
		$('#datatable_col_reorder').dataTable({
			"sPaginationType" : "bootstrap",
			"sDom" : "R<'dt-top-row'Clf>r<'dt-wrapper't><'dt-row dt-bottom-row'<'row'<'col-sm-6'i><'col-sm-6 text-right'p>>",
			"fnInitComplete" : function(oSettings, json) {
				$('.ColVis_Button').addClass('btn btn-default btn-sm').html('Columns <i class="icon-arrow-down"></i>');
			}
		});
		
		/* END COL ORDER */

		/* TABLE TOOLS */
		$('#datatable_tabletools').dataTable({
			"sDom" : "<'dt-top-row'Tlf>r<'dt-wrapper't><'dt-row dt-bottom-row'<'row'<'col-sm-6'i><'col-sm-6 text-right'p>>",
			"oTableTools" : {
				"aButtons" : ["copy", "print", {
					"sExtends" : "collection",
					"sButtonText" : 'Save <span class="caret" />',
					"aButtons" : ["csv", "xls", "pdf"]
				}],
				"sSwfPath" : "js/plugin/datatables/media/swf/copy_csv_xls_pdf.swf"
			},
			"fnInitComplete" : function(oSettings, json) {
				$(this).closest('#dt_table_tools_wrapper').find('.DTTT.btn-group').addClass('table_tools_group').children('a.btn').each(function() {
					$(this).addClass('btn-sm btn-default');
				});
			}
		});
		
		/* END TABLE TOOLS */
	}
</script>
<link rel="stylesheet" type="text/css" media="screen" href="css/bootstrap.min.css">
