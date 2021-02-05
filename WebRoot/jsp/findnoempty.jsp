<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
contentType="text/html; charset=UTF-8" %>
<!-- widget grid -->
<div class="row" style="height:600px;overflow: scroll">
<input type="hidden" id="um1" value="${username }"/>
	<!-- row -->
<table border="1" style="width: 102%;">
	<tr style="height: ;overflow: auto;">
		<td>
			<div class="jarviswidget jarviswidget-color-darken" id="wid-id-2" data-widget-editbutton="false">
			<header style = "text-align:left;">
					<span class="widget-icon"> <i class="fa fa-table"></i> </span>
					<h2 id="wkcbhcx">无空车变化查询</h2>
				</header>
				<!-- widget div-->
				<div>
					<div class="jarviswidget-editbox">
					</div>
					<div class="widget-body no-padding">
					<form action="" id="msg-form" class="smart-form" novalidate="novalidate" >
					<section style="width:180px;float:left;">
										<label class="input"> <i class="icon-append fa fa-calendar"></i>
											<input id="noemptystime" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})()" placeholder="--请选择开始时间--"/>
										</label>
								</section>
								<section style="width:180px;float:left;">
										<label class="input"> <i class="icon-append fa fa-calendar"></i>
											<input id="noemptyetime" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:noemptystime.value})()" placeholder="--请选择结束时间--"/>
										</label>
								</section>
								<label class="select" style="width:180px;float:left;">
											<select  id="noareaselect" class="select">
												<option value="0" >--选择公司--</option>
											</select> <i></i> </label>
									</section>
									<section class="col col-5" style="width: 200px;float:left;" >
										<label class="select">
											<select id="nocompselect" class="select" >
												<option value="0" selected=""  disabled="">--选择分公司--</option>
											</select> <i></i> </label>
									</section>
								<a href="javascript:void(0);"  class="btn btn-primary"  onclick="findnoempty();">查   询</a>
								<a href="javascript:void(1);"  class="btn btn-primary"  onclick="findnoemptyexcle();">导   出</a><span id="noemptydaochu"></span>
							<section style="width:90px;text-align: center;height:32px;line-height:32px;">
										<label class="span"> 
											 <span id="noemptyts"></span>
										</label>
									</section>
						</form>
						<table id="noemptytable" style="float:left;"  class="table table-striped table-bordered table-hover">
							<thead>
								<tr id="noemptythead">
<!-- 									<th>序号</th> -->
<!-- 									<th>公司</th> -->
<!-- 									<th>车号</th> -->
								</tr>
							</thead>
							<tbody id="noemptytbody">
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
	$(document).ready(function(){
		var mydate = new Date();
		var smydate = new Date(mydate.getTime() - 1000 * 60 * 60*24*10);
		$("#noemptystime").val(setformatnewlc(smydate));
		$("#noemptyetime").val(setformatnewlc(mydate));
//		$("#START_TIME2").val(y+"-01-01");
	});
	function setformatnewlc(obj){
		var y=(obj.getFullYear()).toString();
		var m=(obj.getMonth()+1).toString();
		if(m.length==1){
			m="0"+m;
		}
		var d=obj.getDate().toString();
		if(d.length==1){
			d="0"+d;
		}
// 		var h = obj.getHours().toString();
// 		if(h.length==1){
// 			h="0"+h;
// 		}
// 		var mi = obj.getMinutes().toString();
// 		if(mi.length==1){
// 			mi="0"+mi;
// 		}
// 		var s = obj.getSeconds().toString();
// 		if(s.length==1){
// 			s="0"+s;
// 		}
		var time=y+"-"+m+"-"+d; 
		return time;
	}
	//loadDataTableScripts_noemptytable();
	//loadDataTableScripts_area();
	// PAGE RELATED SCRIPTS
	find1();
	function find1(){
		findba();
		setTimeout("findnoempty()", 500);
	}
	Date.prototype.format = function() {
　　　　　var s = '';
	        　　　　　var mouth = (this.getMonth() + 1)>=10?(this.getMonth() + 1):('0'+(this.getMonth() + 1));
	        　　　　　var day = this.getDate()>=10?this.getDate():('0'+this.getDate());
	        　　　　　s += this.getFullYear() + '-'; // 获取年份。
	        　　　　　s += mouth + "-"; // 获取月份。
	        　　　　　s += day; // 获取日。
	        　　　　　return (s); // 返回日期。
	};
	 
    　　function getAll(begin, end) {
    	　　　　var arr = [];
        　　　　var ab = begin.split("-");
        　　　　var ae = end.split("-");
        　　　　var db = new Date();
        　　　　db.setUTCFullYear(ab[0], ab[1] - 1, ab[2]);
        　　　　var de = new Date();
        　　　　de.setUTCFullYear(ae[0], ae[1] - 1, ae[2]);
        　　　　var unixDb = db.getTime() - 24 * 60 * 60 * 1000;
        　　　　var unixDe = de.getTime() - 24 * 60 * 60 * 1000;
        　　　　for (var k = unixDb; k <= unixDe;) {
            　　　　　　//console.log((new Date(parseInt(k))).format());
            　　　　　　k = k + 24 * 60 * 60 * 1000;
            　　　　　　arr.push((new Date(parseInt(k))).format());
        　　　　}
        　　　　return arr;
    }
    
function findnoempty(){
$.blockUI({message:"<div style='padding:5px;'><h3><font color='blue'>数据查询中,请稍候...</font></h3><img src='img/ajax-loader.gif' border='0'/><br/><br/></div>"});
	$.ajax({
			url : 'findnoempty.action',
			type : 'post',
			data:{
				"stime" : $("#noemptystime").val(),
				"etime" : $("#noemptyetime").val(),
				"areaid" : $("#noareaselect").val(),
				"compid" : $("#nocompselect").val()
			},
			dataType: 'json',
			timeout : 180000,
			success:function(json){
				var arr=getAll($("#noemptystime").val(),$("#noemptyetime").val());
				var thead="";			
				thead=thead+"<th>序号</th>";
				thead=thead+"<th>公司</th>";
				thead=thead+"<th>车号</th>";
				for(var j=0;j<arr.length;j++ ){
					thead=thead+"<th>"+arr[j]+"</th>";
				}
				$("#noemptythead").html(thead);
					
				var noempty = json.list8;
				var tab="";
				var i=0;
				console.log("list8:"+json.list8)
				//$('#noemptytable').dataTable().fnDestroy(); 
				if(noempty!=null){
					for(i=0;i<noempty.length;i++){
						tab=tab+"<tr><td nowrap='nowrap'>"+(i+1)+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+noempty[i].comp_id+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+noempty[i].vehi_no+"</td>";
						var arr2=noempty[i].ba_id.split(";");
						for(var z=0;z<arr2.length-1;z++){
							if(arr2[z]=="null"){
								tab=tab+"<td nowrap='nowrap'>&nbsp;</td>";
							}else{
								tab=tab+"<td nowrap='nowrap'>&nbsp;"+arr2[z]+"</td>";
							}
						}
					}
						$("#noemptyts").html("总条数:"+i+"条");
						$("#noemptytbody").html(tab);
						$.unblockUI();
				}
			},
			error:function(){
				
			}		
	});
}
function findba(){
		$.ajax({
			url : 'findba.action',
			type : 'post',
			data:{
			},
			dataType: 'json',
			timeout : 180000,
			success:function(json){
					var area=json.list1;
					for(var i=0;i<area.length;i++){
						$("#noareaselect").append("<option value='"+area[i].ba_name+"'onclick='findcomp(this.value);'>"+area[i].ba_name+"</option>");
					}
			},
			error:function(){
				
			}		
		});
	}
	function findcomp(obj){
		$.ajax({
			url : 'findcompgroup.action',
			type : 'post',
			data:{
				'ba_id': obj
			},
			dataType: 'json',
			timeout : 180000,
			success:function(json){
			$("#nocompselect").empty();
					var area=json.list;
					$("#nocompselect").append("<option value='0' >所有分公司</option>");
					for(var i=0;i<area.length;i++){
						$("#nocompselect").append("<option value='"+area[i].comp_name+"' >"+area[i].comp_name+"</option>");
					}
			},
			error:function(){
				
			}		
		});
	}


//导出excle
	function findnoemptyexcle(){
	$.blockUI({message:"<div style='padding:5px;'><h3><font color='blue'>数据查询中,请稍候...</font></h3><img src='img/ajax-loader.gif' border='0'/><br/><br/></div>"});
	if(window.confirm("确定按上述条件把数据集导成Excel？")){
		$.ajax({
		url : 'findnoemptyexcle.action',
		type : 'post',
		data:{
			"stime" : $("#noemptystime").val(),
				"etime" : $("#noemptyetime").val(),
				"areaid" : $("#noareaselect").val(),
				"compid" : $("#nocompselect").val()	
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
$("#wkcbhcx").html("无空车变化查询");
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

	var noemptytables="";
	function loadDataTableScripts_noemptytable() {

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
			loadScript("js/plugin/datatables/DT_bootstrap.js", runDataTables_noemptytable);
		}

	}

	function runDataTables_noemptytable() {

		/*
		 * BASIC
		 */
		noemptytables=$('#noemptytable').dataTable({
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
<style>
</style>
<script src="js/jquery.blockUI.js"></script>
<link rel="stylesheet" type="text/css" media="screen" href="css/bootstrap.min.css">
