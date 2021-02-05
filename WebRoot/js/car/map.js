//vehicle.jsp
$('#search').unbind("click").click(function(){
	$.blockUI({message:"<div style='padding:5px;'><h3><font color='blue'>数据查询中,请稍候...</font></h3><img src='img/ajax-loader.gif' border='0'/><br/><br/></div>"});
	var startTime = $('#startTime').val();
	var endTime = $('#endTime').val();
	var cardNo = $('#cardNo').val();
	var company = $('#company option:selected').text();
	var branch = $('#branch option:selected').text();
	var url = $('#search').data("url");
		var cur= 1;
		$.ajax({
				url:url,			
				data:{
					"con.startTime":startTime,
					"con.endTime":endTime,
					"con.cardNo":cardNo,
					"con.company":company,
					"con.branch":branch,
					"page.currentPage":cur
				},
				dataType:"JSON",
				type:"POST",
				async:true,
				success:function(data){				
					var businesslist = data.list;
					var p = data.page;				
					num =p.pageCount;
					pageCount=parseInt((num-1)/p.pageSize+1);
					getList(businesslist,p);
					$('#num').html(num);
					$('#pageCount').html(pageCount);
					$('#vehi').show();
					$.unblockUI();
				}
		});
	
});
// 添加到页面
	function getList(businesslist,p){
		page= p.currentPage;
		var tab="";
		if(businesslist != null ){	
				for(var i =0;i<businesslist.length;i++){				
					var empty = businesslist[i].empty;
					var distance = businesslist[i].distance;
					var total = (empty+distance).toFixed(2);	
					var percent =((distance/total)*100).toFixed(2)+"%"
					tab=tab+"<tr ><td nowrap='nowrap'>"+businesslist[i].number+"</td>";
					tab=tab+"<td nowrap='nowrap'>"+businesslist[i].company+"</td>";
					tab=tab+"<td nowrap='nowrap'>"+businesslist[i].branch+"</td>";	
					tab=tab+"<td nowrap='nowrap'>"+businesslist[i].vhic+"</td>";
					tab=tab+"<td nowrap='nowrap'>"+businesslist[i].money+"</td>";
					tab=tab+"<td nowrap='nowrap'>"+businesslist[i].times+"</td>";
					tab=tab+"<td nowrap='nowrap'>"+businesslist[i].distance.toFixed(2)+"</td>";
					tab=tab+"<td nowrap='nowrap'>"+businesslist[i].empty.toFixed(2)+"</td>";				
					tab=tab+"<td nowrap='nowrap'>"+total+"</td>";
					tab=tab+"<td nowrap='nowrap'>"+percent+"</td>";
					tab=tab+"<td nowrap='nowrap'>"+(businesslist[i].timeOut/3600*1.0).toFixed(2)+"</td>";
					tab=tab+"<td nowrap='nowrap'>"+(businesslist[i].waitTime/3600*1.0).toFixed(2)+"</td>";
					tab=tab+"</tr>";
				}			
			
		}
		$('#tb').html(tab);
		$('#currentPage').html(page);
	}
// 获取末页
function getEndPage(pageCount){
	getCurrentPage(pageCount);
}
// 获取跳转页码
function getOnePage(){
	var onePage = $('#jumpPage').val();
	getCurrentPage(onePage);
}
//获取当前页
function getCurrentPage(cp){
	$.blockUI({message:"<div style='padding:5px;'><h3><font color='blue'>数据查询中,请稍候...</font></h3><img src='img/ajax-loader.gif' border='0'/><br/><br/></div>"});
	var startTime = $('#startTime').val();
	var endTime = $('#endTime').val();
	var cardNo = $('#cardNo').val();
	var company = $('#company option:selected').text();
	var branch = $('#branch option:selected').text();
	var url=$('.getData').data('url');
	if(cp<=0){
		cp = 1;
	}else if(cp>=pageCount){
		cp = pageCount;
	}
	$.ajax({
		url:url,			
		data:{		
			"con.startTime":startTime,
			"con.endTime":endTime,
			"con.cardNo":cardNo,
			"con.company":company,
			"con.branch":branch,
			"page.currentPage":cp
		},
		dataType:"JSON",
		type:"POST",
		async:true,
		success:function(data){				
			var businesslist = data.list;
			var p = data.page;
			getList(businesslist,p);
			$.unblockUI();
		}
	});
}

//business.jsp
$('#search3').unbind("click").click(function(){
	$.blockUI({message:"<div style='padding:5px;'><h3><font color='blue'>数据查询中,请稍候...</font></h3><img src='img/ajax-loader.gif' border='0'/><br/><br/></div>"});
	var startTime = $('#startTime3').val();
	var endTime = $('#endTime3').val();
	var cardNo = $('#cardNo3').val();
	var company = $('#company3 option:selected').text();
	var branch = $('#branch3 option:selected').text();
	var url = $('#search3').data("url");
		var cur= 1;
		$.ajax({
				url:url,			
				data:{
					"con.startTime":startTime,
					"con.endTime":endTime,
					"con.cardNo":cardNo,
					"con.company":company,
					"con.branch":branch,
					"page.currentPage":cur
				},
				dataType:"JSON",
				type:"POST",
				async:true,
				success:function(data){				
					var businesslist = data.list;
					var p = data.page;				
					num =p.pageCount;
					pageCount=parseInt((num-1)/p.pageSize+1);
					getList3(businesslist,p);
					$('#num3').html(num);
					$('#pageCount3').html(pageCount);
					$('#bus').show();
					$.unblockUI();
				}
		});
	
});
function findbus(vhic){
	$.blockUI({message:"<div style='padding:5px;'><h3><font color='blue'>数据查询中,请稍候...</font></h3><img src='img/ajax-loader.gif' border='0'/><br/><br/></div>"});
	var startTime = $('#startTime3').val();
	var endTime = $('#endTime3').val();
	var cardNo = $('#cardNo3').val();
	var company = $('#company3 option:selected').text();
	var branch = $('#branch3 option:selected').text();
	var url = $('#search3').data("url");
		var cur= 1;
		$.ajax({
				url:url,			
				data:{
					"con.startTime":startTime,
					"con.endTime":endTime,
					"con.cardNo":vhic,
					"con.company":company,
					"con.branch":branch,
					"page.currentPage":cur
				},
				dataType:"JSON",
				type:"POST",
				async:true,
				success:function(data){				
					var businesslist = data.list;
					var p = data.page;				
					num =p.pageCount;
					pageCount=parseInt((num-1)/p.pageSize+1);
					getList3(businesslist,p);
					$('#num3').html(num);
					$('#pageCount3').html(pageCount);
					$('#bus').show();
					$.unblockUI();
				}
		});
}
// 添加到页面
	function getList3(businesslist,p){
		page= p.currentPage;
		var tab="";
		if(businesslist != null ){	
				for(var i =0;i<businesslist.length;i++){				
					tab=tab+"<tr ><td nowrap='nowrap'>"+businesslist[i].number+"</td>";
					tab=tab+"<td nowrap='nowrap'>"+businesslist[i].vhic+"</td>";
					tab=tab+"<td nowrap='nowrap'>"+businesslist[i].certNo+"</td>";
					tab=tab+"<td nowrap='nowrap'>"+businesslist[i].upTaxi.substr(0,businesslist[i].upTaxi.length-2)+"</td>";
					tab=tab+"<td nowrap='nowrap'>"+businesslist[i].downTaxi+"</td>";
					tab=tab+"<td nowrap='nowrap'>"+businesslist[i].money+"</td>";					
					tab=tab+"<td nowrap='nowrap'>"+businesslist[i].distance.toFixed(2)+"</td>";
					tab=tab+"<td nowrap='nowrap'>"+businesslist[i].empty.toFixed(2)+"</td>";				
					tab=tab+"<td nowrap='nowrap'>"+businesslist[i].waitTime+"</td>";
					tab=tab+"<td nowrap='nowrap'>"+businesslist[i].type+"</td>";
					tab=tab+"<td nowrap='nowrap'>"+businesslist[i].time+"</td>";
					tab=tab+"</tr>";
				}			
			
		}
		$('#tb3').html(tab);
		$('#currentPage3').html(page);
	}
// 获取末页
function getEndPage3(pageCount){
	getCurrentPage3(pageCount);
}
// 获取跳转页码
function getOnePage3(){
	var onePage = $('#jumpPage3').val();
	getCurrentPage3(onePage);
}

function getCurrentPage3(cp){
	$.blockUI({message:"<div style='padding:5px;'><h3><font color='blue'>数据查询中,请稍候...</font></h3><img src='img/ajax-loader.gif' border='0'/><br/><br/></div>"});
	var startTime = $('#startTime3').val();
	var endTime = $('#endTime3').val();
	var cardNo = $('#cardNo3').val();
	var company = $('#company3 option:selected').text();
	var branch = $('#branch3 option:selected').text();
	var url=$('.getData3').data('url');
	if(cp<=0){
		cp = 1;
	}else if(cp>=pageCount){
		cp = pageCount;
	}
	$.ajax({
		url:url,			
		data:{		
			"con.startTime":startTime,
			"con.endTime":endTime,
			"con.cardNo":cardNo,
			"con.company":company,
			"con.branch":branch,
			"page.currentPage":cp
		},
		dataType:"JSON",
		type:"POST",
		async:true,
		success:function(data){				
			var businesslist = data.list;
			var p = data.page;
			getList3(businesslist,p);
			$.unblockUI();
		}
	});
}

