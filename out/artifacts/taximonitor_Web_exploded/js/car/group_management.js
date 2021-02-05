findcargroup();
var card;
var cards;
//查询
function findcargroup(){
	$.ajax({
		 type: "POST",
	        url:"findcargroup.action",
	        data:{
	        },
	       dataType: 'json',
			timeout : 180000,
		success:function(json){
	        	$("#groupf").empty();
	        	var cust=json.group;
	        	var tab="";
	        	$("#groupf").append("<option value='0' >--选择群组--</option>");
	        	for(var i=0;i<cust.length;i++){
	        		$("#groupf").append("<option value='"+cust[i].id+"' >"+cust[i].groupname+"</option>");
	        	}
		},
		error:function(){
			
		}		
	});
}
function findcargroupvhic(){
	$.blockUI({message:"<div style='padding:5px;'><h3><font color='blue'>数据查询中,请稍候...</font></h3><img src='img/ajax-loader.gif' border='0'/><br/><br/></div>"});
	$.ajax({
		 type: "POST",
	        url:"findcargroupvhic.action",
	        data:{
				id : $("#groupf").val()
	        },
	       dataType: 'json',
			timeout : 180000,
		success:function(json){
	        	var cust=json.list;
	        	var tab="";
	        	for(var i=0;i<cust.length;i++){
	        		tab=tab+"<tr><td nowrap='nowrap'>"+(i+1)+"</td>";
					tab=tab+"<td nowrap='nowrap'>&nbsp;"+cust[i].vehi_no+"</td>";
					tab=tab+"<td nowrap='nowrap'>&nbsp;"+cust[i].sim_num+"</td>";
					tab=tab+"<td nowrap='nowrap'>&nbsp;"+cust[i].own_name+"</td>";
					tab=tab+"<td nowrap='nowrap'>&nbsp;"+cust[i].own_tel+"</td>";
					tab=tab+"<td nowrap='nowrap'>&nbsp;"+cust[i].color+"</td>";
					tab=tab+"<td nowrap='nowrap'>&nbsp;"+cust[i].mt_name+"</td>";
					tab=tab+"<td nowrap='nowrap'>&nbsp;"+cust[i].cartype+"</td></tr>";
	        	}
	        	$("#tbf").html(tab);
	        	$.unblockUI();
		},
		error:function(){
			$.unblockUI();
		}		
	});
}
function findvhic12(){
	$.ajax({
		 type: "POST",
	        url:"findvhic1.action",
	        data:{
	        },
	       dataType: 'json',
			timeout : 180000,
		success:function(json){
	        	var cust=json.list;
	        	card=cust;
	        	cards=cust;
	        	var tab="";
	        	for(var i=0;i<cust.length;i++){
	        		$("#group-add").append("<option value='"+cust[i].vehi_no+"'>"+cust[i].vehi_no+"</option>");
	        		$("#group-edit").append("<option value='"+cust[i].vehi_no+"'>"+cust[i].vehi_no+"</option>");
	        	}
		},
		error:function(){
		}		
	});
}
			
//删除
function delcargroup(){
	var r = window.confirm("您确定要删除该条数据吗？");
	if(r) {
	$.ajax({
		 type: "POST",
	        url:"delcargroup.action",
	        data:{
				id : $("#groupf").val()
	        },
	       dataType: 'json',
			timeout : 180000,
		success:function(json){
	        	alert(json.info);
	        	findcargroup();
	        	findcargroupvhic();
		},
		error:function(){
		}		
	});
	}
}
//新增
function addcargroup(){
	$('#addgroupf').dialog('open');
	findvhic12();
}
$('#addgroupf').dialog({
	autoOpen : false,
	width : 500,
	resizable : false,
	modal : true,
	buttons : [{
		html : "<i class='fa fa-times'></i>&nbsp; 取消",
		"class" : "btn btn-default",
		click : function() {
		$(this).dialog("close");
	}
	}, {
		
		html : "<i class='fa fa-plus'></i>&nbsp; 确定",
		"class" : "btn btn-danger",
		click : function() {
		cargroupadd();
		findcargroup();
		$(this).dialog("close");
	}
	}]
});
//将数据存入数组
function getArray(list){
	var str = new Array();
	var str2 = new Array();
	$('#sel-add option').each(function(){
		str.push($(this).val());
	});
	
	for(var i =0;i<list.length;i++){
		var flag = true;
		for(var j =0;j<str.length;j++){
			if(list[i]== str[j])
				flag = false;
		}
		if(flag)
			str2.push(list[i]);
	}
	$("#group-add").empty();	
		for(var i=0;i<str2.length;i++){
			$("#group-add").append("<option value='"+str2[i]+"'>"+str2[i]+"</option>");
		}
		return str2;
}
//添加所有车
$('#addall').unbind('click').click(function(){
	var lists = getArray(card);
	for(var i =0;i< lists.length;i++){
		$("#sel-add").append("<option value='"+lists[i].vehi_no+"'>"+lists[i].vehi_no+"</option>");
	}
		$("#group-add").empty();
	
});
//添加一辆车
$('#addone').unbind('click').click(function(){
	var vehicle = $("#group-add option:selected").text();
	if(vehicle !=""){
		$("#sel-add").append("<option value='"+vehicle+"'>"+vehicle+"</option>");
		 var i = $("#group-add").get(0).selectedIndex;
			$("#group-add").get(0).remove(i);
	}else{
		alert("请选择要添加的车号");
	}
	
});
//删除一辆车
$('#delone').unbind('click').click(function(){
	var i = $("#sel-add").get(0).selectedIndex;
	var vehicle = $("#sel-add").val();
	if(i != -1){
		$("#sel-add").get(0).remove(i);
		$("#group-add").append("<option value='"+vehicle+"'>"+vehicle+"</option>");
	}else{
		alert("请选择要移除的车号");
	}
});
//删除所有车
$('#delall').unbind('click').click(function(){
	$("#sel-add").empty();
	findvhic12();
});
function cargroupadd(){
	var list ="";
	$('#sel-add option').each(function(){
		list+=$(this).val()+",";
	});
	$.ajax({
		type: "POST",
		url:"addcargroup.action",
		data:{
				"name":$("#vhicgroupadd").val(),
				"vhic":list,
				"id" :$("#clzum").val()
		},
	       dataType: 'json',
			timeout : 180000,
		success:function(json){
			alert(json.info);
			findcargroup();
		},
		error:function(){
			
		}		
	});
}
//修改
function editcargroup(){
	var sel =$('#groupf').val();
	var se = $('#groupf option:selected').text();
	if(sel != 0){
		$.ajax({
			url:"findcargroupid.action",
			data:{
				"id":sel,
			},
			dataType:"JSON",
			type:"POST",
			async:true,
			success:function(data){
				var g=data.g;
				var list = data.group;
				$('#vhicgroupedit').val(se);
				$("#sel-edit").empty();			
				if(list != null){
					for(var i=0;i<list.length;i++){
						$("#sel-edit").append("<option value='"+list[i].groupvhic+"'>"+list[i].groupvhic+"</option>");
					}
					
				}
			}
		});
		$('#editgroupf').dialog('open');
		findvhic12();
	}else{
		alert("请选择一项车辆组进行修改");
	}
}
$('#editgroupf').dialog({
	autoOpen : false,
	width : 500,
	resizable : false,
	modal : true,
	buttons : [{
		html : "<i class='fa fa-times'></i>&nbsp; 取消",
		"class" : "btn btn-default",
		click : function() {
		$(this).dialog("close");
	}
	}, {
		
		html : "<i class='fa fa-plus'></i>&nbsp; 确定",
		"class" : "btn btn-danger",
		click : function() {
		cargroupedit();
		findcargroup();
		$(this).dialog("close");
	}
	}]
});

//将数据存入数组
function getArrays(list){
	var str = new Array();
	var str2 = new Array();
	$('#sel-edit option').each(function(){
		str.push($(this).val());
	});
	
	for(var i =0;i<list.length;i++){
		var flag = true;
		for(var j =0;j<str.length;j++){
			if(list[i]== str[j])
				flag = false;
		}
		if(flag)
			str2.push(list[i]);
	}
	$("#group-edit").empty();	
		for(var i=0;i<str2.length;i++){
			$("#group-edit").append("<option value='"+str2[i]+"'>"+str2[i]+"</option>");
		}
		return str2;
}
//添加所有车
$('#addalls').unbind('click').click(function(){
	var lists = getArrays(cards);
	for(var i =0;i< lists.length;i++){
		$("#sel-edit").append("<option value='"+lists[i].vehi_no+"'>"+lists[i].vehi_no+"</option>");
	}
		$("#group-edit").empty();
	
});
//添加一辆车
$('#addones').unbind('click').click(function(){
	var vehicle = $("#group-edit option:selected").text();
	if(vehicle !=""){
		$("#sel-edit").append("<option value='"+vehicle+"'>"+vehicle+"</option>");
		 var i = $("#group-edit").get(0).selectedIndex;
			$("#group-edit").get(0).remove(i);
	}else{
		alert("请选择要添加的车号");
	}
	
});
//删除一辆车
$('#delones').unbind('click').click(function(){
	var i = $("#sel-edit").get(0).selectedIndex;
	var vehicle = $("#sel-edit").val();
	if(i != -1){
		$("#sel-edit").get(0).remove(i);
		$("#group-edit").append("<option value='"+vehicle+"'>"+vehicle+"</option>");
	}else{
		alert("请选择要移除的车号");
	}
});
//删除所有车
$('#delalls').unbind('click').click(function(){
	$("#sel-edit").empty();
	findvhic12();
});
function cargroupedit(){
	var list ="";
	$('#sel-edit option').each(function(){
		list+=$(this).val()+",";
	});
	$.ajax({
		type: "POST",
		url:"editcargroup.action",
		data:{
				"id" : $("#groupf").val(),
				"name":$("#vhicgroupedit").val(),
				"vhic":list
		},
	       dataType: 'json',
			timeout : 180000,
		success:function(json){
			alert(json.info);
			findcargroup();
		},
		error:function(){
			
		}		
	});
}