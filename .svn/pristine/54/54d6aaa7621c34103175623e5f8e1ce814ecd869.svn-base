initializeudfance();
findfence();
var udpolygonOption = {
	    strokeColor:"#000033",	
	    strokeOpacity:1,
	    strokeWeight:2
	};
function switchSysBar2udfance(){
	if ($("#switchPoint2udfance").val()=="3"){
		$("#switchPoint2udfance").val("4");
		$("#downdiv2udfance").css('display','none'); 
		 $("#updiv2udfance").height("95%"); 
		$("#utod2udfance").attr("class", "fa fa-chevron-up");
	}
	else{
		$("#switchPoint2udfance").val("3");
		$("#downdiv2udfance").css('display','block');
		 $("#updiv2udfance").height("60%"); 
		$("#utod2udfance").attr("class", "fa fa-chevron-down");
	}
}
var udfencemap;
function initializeudfance() {
	var position ;
	if($("#index_dlm").val().indexOf("富阳") >= 0 ){
		position = new AMap.LngLat(119.93790, 30.05095);//创建中心点坐标
	}else if($("#index_dlm").val().indexOf("余杭") >= 0 ){
		position = new AMap.LngLat(120.29858, 30.42368);//创建中心点坐标
	}else if($("#index_dlm").val().indexOf("建德") >= 0 ){
		position = new AMap.LngLat(119.281213, 29.474884);//创建中心点坐标
	}else{
		position = new AMap.LngLat(120.16378, 30.25840);//创建中心点坐标
	}
	udfencemap=new AMap.Map("fance-div-udarea",{center:position,level:14,resizeEnable:true});//创建地图实例
	udfencemap.plugin(["AMap.ToolBar","AMap.OverView","AMap.Scale"],function(){
		  //加载工具条
		  tool=new AMap.ToolBar({
		    direction:false,//隐藏方向导航
		    ruler:false,//隐藏视野级别控制尺
		    autoPosition:false//禁止自动定位
		  });
		  udfencemap.addControl(tool);
		  //加载鹰眼
		  view=new AMap.OverView();
		  udfencemap.addControl(view);
		  //加载比例尺
		  scale=new AMap.Scale();
		  udfencemap.addControl(scale);
		});

	udfencemap.plugin(["AMap.MapType"], function() {
			var type = new AMap.MapType({defaultType:0});//初始状态使用2D地图
			udfencemap.addControl(type);
		});

}
//查询
var udarealist;
function findfence(){
	$.ajax({
		url : 'findfence.action',
		type : 'post',
		data:{
		},
		dataType: 'json',
		timeout : 180000,
		success:function(json){
			udarealist = json.area;
			var trdata="";
			for(var i=0;i<udarealist.length;i++){
				udaddPolygonfence(udarealist[i].area_coordinates,udarealist[i].area_name,udarealist[i].arae_describe,udarealist[i].area_size);
				var zbs = udarealist[i].area_coordinates.split(";");
				var lo = zbs[0].split(",")[0];
				var la = zbs[0].split(",")[1];
				trdata+="<tr ondblclick='gotoudfence("+lo+","+la+")'><td>"+(i+1)+"</td><td nowrap='nowrap'>"+udarealist[i].area_name+"</td><td nowrap='nowrap'>"+udarealist[i].arae_describe+"</td><td nowrap='nowrap'>"+udarealist[i].user_id+"</td><td nowrap='nowrap'>"+udarealist[i].area_size+"</td><TD><input type='button' value='改' onclick='editudfence("+udarealist[i].id+")'>&nbsp;<input type='button' value='删' onclick='deleteudfence("+udarealist[i].id+")'></td></tr>";
			}
			$("#fencetbody").html(trdata);
		},
		error:function(){
		}		
	});
}
//查询车辆组
function findvehigroup(obj){
	$.ajax({
		 type: "POST",
	        url:"findcargroup.action",
	        data:{
	        },
	       dataType: 'json',
			timeout : 180000,
		success:function(json){
	        	$("#fencevhic").empty();
	        	var cust=json.group;
	        	var tab="";
	        	$("#fencevhic").append("<option value='0' >--选择群组--</option>");
	        	for(var i=0;i<cust.length;i++){
	        		$("#fencevhic").append("<option value='"+cust[i].id+"' >"+cust[i].groupname+"</option>");
	        		if(cust[i].id!=obj){
	        			$("#fencevhicedit").append("<option value='"+cust[i].id+"' >"+cust[i].groupname+"</option>");
	        		}
	        	}
		},
		error:function(){
			
		}		
	});
}
function udaddPolygonfence(obj,name,ms,sz){  
	var polygonArr=new Array();//多边形覆盖物节点坐标数组   
	var zbs = obj.split(";");
	for(var i=0;i<zbs.length;i++){
		var zb = zbs[i].split(",");
		polygonArr.push(new AMap.LngLat(zb[0],zb[1]));   
	}
	var polygon=new AMap.Polygon({     
		path:polygonArr,//设置多边形边界路径  
		strokeColor:"black", //线颜色  
		// strokeOpacity:0.2, //线透明度   
		strokeWeight:3,    //线宽   
		fillColor: "#f5deb3", //填充色  
		fillOpacity: 0//填充透明度  
	});   
	polygon.setMap(udfencemap);  

	 	var markerContent = document.createElement("div");
	    markerContent.className = "txtstyle";
		var markerSpan = document.createElement("span");
		markerSpan.innerHTML = name;
		markerContent.appendChild(markerSpan);
		ms = ppfence(ms);
		markerContent.onmouseover=function() {markerSpan.innerHTML =name+" : <br/>区域描述:<br/>"+ms+"<br/>区域面积:"+sz};
		markerContent.onmouseout=function() {markerSpan.innerHTML = name;};
	    var marker = new AMap.Marker({
		    map:udfencemap,
		     zIndex:10001, 
		    position:new AMap.LngLat( zbs[0].split(",")[0],zbs[0].split(",")[1]),     
		    offset:new AMap.Pixel(-14,7), //相对于基点的偏移位置
		    draggable:false,  //是否可拖动
		    content:markerContent  //自定义点标记覆盖物内容
		});
}

function ppfence(obj){
	var num = parseInt(obj.length/10);
	if(num<1){
		return obj;
	}else{
		var rs="";
		for(var i=0;i<num;i++){
			rs+=obj.substr(i*10,10)+"<br/>";
		}
		rs+=obj.substr(num*10,obj.length);
		return rs;
	}
}
function gotoudfence(lo,la){
	var po = new AMap.LngLat(lo,la);
	udfencemap.setZoomAndCenter(14,po);
}
//增加
function addudfence(){
	if(udmouseTool!=null){
		alert("鼠标在地图上点击绘制多边形，单击右键或者双击左键结束绘制");
	}else{
		udfencemap.plugin(["AMap.MouseTool"],function(){ 
		udmouseTool = new AMap.MouseTool(udfencemap); 
		udmouseTool.polygon(udpolygonOption);   //使用鼠标工具绘制多边形
		AMap.event.addListener(udmouseTool, "draw", function(e){
			var drawObj = e.obj;  
			var pointsCount = e.obj.getPath().length; 
			var a =  e.obj.getPath();
			var zbs = "";
			for(var i=0;i<pointsCount;i++){
				if(i==pointsCount-1){
					zbs+=a[i]
					}else{
				zbs+=a[i]+";";}
			}
			$("#fencezb").val(zbs);
			$("#fencesize").val(drawObj.getArea()+"平方米");
			$('#fendfence').dialog('open');
			findvehigroup();
			//alert("多边形节点数：" + pointsCount + "<br>节点坐标："+e.obj.getPath());
		});
		});
	}
}
var udmouseTool=null;
$('#fendfence').dialog({
	autoOpen : false,
	width : 600,
	resizable : false,
	modal : true,
	title : "",
	buttons : [{
		html : "保存区域",
		"class" : "btn btn-danger",
		click : function() {
			if(udmouseTool!=null){
				udmouseTool.close(true);
			}
			udmouseTool=null;
			addfemce();
			$(this).dialog("close");
			resetudtipfence();
		}
	}, {
		html : "取消添加",
		"class" : "btn btn-default",
		click : function() {
			if(udmouseTool!=null){
				udmouseTool.close(true);
			}
			udmouseTool=null;
			$(this).dialog("close");
			resetudtipfence();
		}
	}]
});



function addfemce(){
	if($("#fencename").val()==""){
		alert("请输入区域名称");
	}else{
			if($("#fencems").val()==""){
				alert("请输入区域描述");
			}else{
				if($("#fencevhic").val()=="0"){
					alert("请选择车辆组");
				}else{
				$.ajax({
					url : 'addfence.action',
					type : 'post',
					data:{
						"area_name" : $("#fencename").val(),
						"area_describe" : $("#fencems").val(),
						"area_coordinates" : $("#fencezb").val(),
						"area_size" : $("#fencesize").val(),
						"group_id" : $("#fencevhic").val(),
						"id" : $("#dzwsum").val()
					},
					dataType: 'json',
					timeout : 180000,
					success:function(json){
							alert(json.info);
							udfencemap.clearMap();
							findfence();
					},
					error:function(){
					}		
				});
				}
			}
	}
}
function resetudtipfence(){
	$("#fencename").val("");
	$("#fencems").val("");
	$("#fencesize").val("");
	$("#fencezb").val("");
}
//修改
$('#fenceedit').dialog({
	autoOpen : false,
	width : 600,
	resizable : false,
	modal : true,
	title : "",
	buttons : [{
		html : "修改",
		"class" : "btn btn-danger",
		click : function() {
			editfence();
			$(this).dialog("close");
		}
	}, {
		html : "取消",
		"class" : "btn btn-default",
		click : function() {
			$(this).dialog("close");
		}
	}]
});
function editudfence(obj){
	$.ajax({
		url : 'findfence.action',
		type : 'post',
		data:{
			"id" : obj
		},
		dataType: 'json',
		timeout : 180000,
		success:function(json){
			var area = json.area;
			$("#fencevhicedit").empty();
			$("#udfenceidedit").val(area[0].id);
			$("#udfencenameedit").val(area[0].area_name);
			$("#udfencemsedit").val(area[0].arae_describe);
			$("#udfencesizeedit").val(area[0].area_size);
			$("#udfencezbsedit").val(area[0].area_coordinates);
			$("#fencevhicedit").append("<option value='"+area[0].orderid+"' >"+area[0].user_id+"</option>");
			findvehigroup(area[0].orderid);
			$('#fenceedit').dialog('open');
		},
		error:function(){
		}		
	});
}
function editfence(){
	$.ajax({
		url : 'editfence.action',
		type : 'post',
		data:{
			"id" : $("#udfenceidedit").val(),
			"area_name" : $("#udfencenameedit").val(),
			"area_describe" : $("#udfencemsedit").val(),
			"group_id" :$("#fencevhicedit").val()
		},
		dataType: 'json',
		timeout : 180000,
		success:function(json){
				alert(json.info);
				udfencemap.clearMap();
				findfence();
		},
		error:function(){
		}		
	});
}
function deleteudfence(obj){
	if(window.confirm("确定删除该数据？")){
	$.ajax({
		url : 'delfence.action', 
		type : 'post',
		data:{
			"id" : obj
		},
		dataType: 'json',
		timeout : 180000,
		success:function(json){
				alert(json.info);
				udfencemap.clearMap();
				findfence();
		},
		error:function(){
			alert("请求失败");
		}		
	});
	}
}