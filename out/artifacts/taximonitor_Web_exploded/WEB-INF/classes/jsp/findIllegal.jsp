<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
contentType="text/html; charset=UTF-8" %>
<!-- widget grid -->
<div class="row" style="height:100%;overflow: scroll">
<input type="hidden" id="um1" value="${username }"/>
	<!-- row -->
<table border="1" style="width: 102%;">
	<tr style="height: ;overflow: auto;">
		<td>
			<div class="jarviswidget jarviswidget-color-darken" id="wid-id-2" data-widget-editbutton="false">
			<header style = "text-align:left;">
					<span class="widget-icon"> <i class="fa fa-table"></i> </span>
					<h2 id="wzcx">违章查询</h2>
				</header>
				<!-- widget div-->
				<div>
					<div class="jarviswidget-editbox">
					</div>
					<div class="widget-body no-padding">
					<form action="" id="msg-form" class="smart-form" novalidate="novalidate" >
								<section class="col col-5" style="width: 200px;float:left;" >
									<label class="select">
										<select id="illegalcomp" class="select" >
											<option value="" >--选择公司--</option>
										</select> <i></i> </label>
								</section>
								<a href="javascript:void(0);"  class="btn btn-primary"  onclick="findillegal(1);">查   询</a>
								<a href="javascript:void(1);"  class="btn btn-primary"  onclick="findillegalexcle();">导   出</a><span id="nolinedaochu"></span>
						</form>
						<table id="illegaltable" style="float:left;"  class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th>序号</th>
									<th>公司</th>
									<th>车号</th>
									<th>违章次数</th>
									<th>IC卡分值</th>
								</tr>
							</thead>
							<tbody id="illegaltbody">
							</tbody>
						</table>

					</div>
					<div class="pagination">
							<span class="getData3" data-url="" ></span>
							&nbsp;&nbsp;&nbsp;&nbsp;共有 <strong><a id="num3">0</a></strong> 条记录
							&nbsp;&nbsp;&nbsp;&nbsp;第 <strong><a id="currentPage3">0</a></strong> / <strong><a id="pageCount3">0</a></strong> 页
							
							&nbsp;&nbsp;&nbsp;&nbsp;
							<a class="STYLE19" href="javascript:getCurrentPage4(1);">首页</a>
							<a class="STYLE19" href="javascript:getCurrentPage4(currentPage-1);">上一页</a> | <a class="STYLE19" href="javascript:getCurrentPage4(currentPage+1);">下一页</a>
							<a class="STYLE19" href="javascript:getCurrentPage4(pageCount);">末页</a>
							&nbsp;&nbsp;&nbsp;&nbsp;转到第<input  style="width:30px;font-size:12px; border:solid 1px #7aaebd;" type="text" id="jumpPage4" >页 <a class="STYLE19" href="javascript:getOnePage4();">go</a>
							
					</div>
				</div>
			</div>
		</td>
	</tr>
</table>
</div>
<script type="text/javascript">
	// 获取上一页，下一页，末页
	function getCurrentPage4(pageCount){
		if(pageCount<=0||pageCount>$('#pageCount3').html()){
			return ;
		}
		findillegal(pageCount);
	}
	// 获取跳转页码
	function getOnePage4(){
		var onePage = $('#jumpPage4').val();
		findillegal(onePage);
	}
	find1();
	function find1(){
		findba();
		setTimeout("findillegal(1)", 500);
	}
	
	
function findillegal(cp){
$.blockUI({message:"<div style='padding:5px;'><h3><font color='blue'>数据查询中,请稍候...</font></h3><img src='img/ajax-loader.gif' border='0'/><br/><br/></div>"});
	$.ajax({
			url : 'findillegal.action',
			type : 'post',
			data:{
				"compname" : $("#illegalcomp").val(),
				"page1.currentPage":cp
			},
			dataType: 'json',
			timeout : 180000,
			success:function(json){
				var noline = json.list1;
				var p = json.page1;		
				console.log(noline)
				var tab="";
				var i=0;
				//$('#illegaltable').dataTable().fnDestroy(); 
				if(noline!=null){
					for(i=0;i<noline.length;i++){
						tab=tab+"<tr><td nowrap='nowrap'>"+((p.currentPage-1)*p.pageSize+i+1)+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+noline[i].com_name+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+noline[i].auto_num+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+noline[i].count+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+noline[i].ic_score+"</td>";
					}					
					$("#illegaltbody").html(tab);					
					num =p.pageCount;
					currentPage=p.currentPage;
					pageCount=parseInt((num-1)/p.pageSize+1);
					$('#num3').html(num);
					$('#pageCount3').html(pageCount);
					$('#currentPage3').html(p.currentPage);	
					$.unblockUI();
				}
			},
			error:function(){
				
			}		
	});
}
function findba(){
		$.ajax({
			url : 'findallcomp.action',
			type : 'post',
			data:{
			},
			dataType: 'json',
			timeout : 180000,
			success:function(json){
					var area=json.list5;
					for(var i=0;i<area.length;i++){
						$("#illegalcomp").append("<option value='"+area[i].name+"'>"+area[i].name+"</option>");
					}
			},
			error:function(){
				
			}		
		});
	}


//导出excle
	function findillegalexcle(){
	$.blockUI({message:"<div style='padding:5px;'><h3><font color='blue'>数据查询中,请稍候...</font></h3><img src='img/ajax-loader.gif' border='0'/><br/><br/></div>"});
	if(window.confirm("确定按上述条件把数据集导成Excel？")){
		$.ajax({
		url : 'findillegalexcle.action',
		type : 'post',
		data:{
			"compname" : $("#illegalcomp").val()
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
$("#wzcx").html("违章查询");
</script>
<style>
</style>
<script src="js/jquery.blockUI.js"></script>
<link rel="stylesheet" type="text/css" media="screen" href="css/bootstrap.min.css">
