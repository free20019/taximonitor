initializeudarea();
findarea();
var udpolygonOption = {
	    strokeColor:"#000033",	
	    strokeOpacity:1,
	    strokeWeight:2
	};
function switchSysBar2ud(){
	if ($("#switchPoint2ud").val()=="3"){
		$("#switchPoint2ud").val("4");
		$("#downdiv2ud").css('display','none'); 
		 $("#updiv2ud").height("95%"); 
		$("#utod2ud").attr("class", "fa fa-chevron-up");
	}
	else{
		$("#switchPoint2ud").val("3");
		$("#downdiv2ud").css('display','block');
		 $("#updiv2ud").height("60%"); 
		$("#utod2ud").attr("class", "fa fa-chevron-down");
	}
}
var udareamap;
function initializeudarea() {
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
	udareamap=new AMap.Map("data-div-udarea",{center:position,level:14,resizeEnable:true});//创建地图实例
	udareamap.plugin(["AMap.ToolBar","AMap.OverView","AMap.Scale"],function(){
		  //加载工具条
		  tool=new AMap.ToolBar({
		    direction:false,//隐藏方向导航
		    ruler:false,//隐藏视野级别控制尺
		    autoPosition:false//禁止自动定位
		  });
		  udareamap.addControl(tool);
		  //加载鹰眼
		  view=new AMap.OverView();
		  udareamap.addControl(view);
		  //加载比例尺
		  scale=new AMap.Scale();
		  udareamap.addControl(scale);
		});

	udareamap.plugin(["AMap.MapType"], function() {
			var type = new AMap.MapType({defaultType:0});//初始状态使用2D地图
			udareamap.addControl(type);
		});

}
//查询
var udarealist;
function findarea(){
	$.ajax({
		url : 'findarea.action',
		type : 'post',
		data:{
		},
		dataType: 'json',
		timeout : 180000,
		success:function(json){
			udarealist = json.area;
			var trdata="";
			for(var i=0;i<udarealist.length;i++){
				udaddPolygon1(udarealist[i].area_coordinates,udarealist[i].area_name,udarealist[i].arae_describe,udarealist[i].area_size);
				var zbs = udarealist[i].area_coordinates.split(";");
				var lo = zbs[0].split(",")[0];
				var la = zbs[0].split(",")[1];
				trdata+="<tr ondblclick='gotoudarea("+lo+","+la+")'><td>"+(i+1)+"</td><td nowrap='nowrap'>"+udarealist[i].area_name+"</td><td nowrap='nowrap'>"+udarealist[i].arae_describe+"</td><td nowrap='nowrap'>"+udarealist[i].area_size+"</td><TD><input type='button' value='改' onclick='editudarea("+udarealist[i].id+")'>&nbsp;<input type='button' value='删' onclick='deleteudarea("+udarealist[i].id+")'></td></tr>";
			}
			$("#udareadata").html(trdata);
		},
		error:function(){
		}		
	});
}
function udaddPolygon1(obj,name,ms,sz){  
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
	polygon.setMap(udareamap);  

	 	var markerContent = document.createElement("div");
	    markerContent.className = "txtstyle";
		var markerSpan = document.createElement("span");
		markerSpan.innerHTML = name;
		markerContent.appendChild(markerSpan);
		ms = pp(ms);
		markerContent.onmouseover=function() {markerSpan.innerHTML =name+" : <br/>区域描述:<br/>"+ms+"<br/>区域面积:"+sz};
		markerContent.onmouseout=function() {markerSpan.innerHTML = name;};
	    var marker = new AMap.Marker({
		    map:udareamap,
		     zIndex:10001, 
		    position:new AMap.LngLat( zbs[0].split(",")[0],zbs[0].split(",")[1]),     
		    offset:new AMap.Pixel(-14,7), //相对于基点的偏移位置
		    draggable:false,  //是否可拖动
		    content:markerContent  //自定义点标记覆盖物内容
		});
}

function pp(obj){
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
function gotoudarea(lo,la){
	var po = new AMap.LngLat(lo,la);
	udareamap.setZoomAndCenter(14,po);
}
//增加
function addudarea(){
	if(udmouseTool!=null){
		alert("鼠标在地图上点击绘制多边形，单击右键或者双击左键结束绘制");
	}else{
		udareamap.plugin(["AMap.MouseTool"],function(){ 
		udmouseTool = new AMap.MouseTool(udareamap); 
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
			$("#udareazbs").val(zbs);
			$("#udareasize").val(drawObj.getArea()+"平方米");
			$('#dialog_udarea').dialog('open');
			//alert("多边形节点数：" + pointsCount + "<br>节点坐标："+e.obj.getPath());
		});
		});
	}
}
var udmouseTool=null;
$('#dialog_udarea').dialog({
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
			addarea();
			$(this).dialog("close");
			resetudtip();
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
			resetudtip();
		}
	}]
});



function addarea(){
	if($("#udareaname").val()==""){
		alert("请输入区域名称");
	}else{
		//if($("#udareabjs").val()==""){
		//	alert("请输入区域预报警数量");
		//}else{
			if($("#udareams").val()==""){
				alert("请输入区域描述");
			}else{
				$.ajax({
					url : 'addarea.action',
					type : 'post',
					data:{
						"area_name" : $("#udareaname").val(),
						"area_describe" : $("#udareams").val(),
						"area_coordinates" : $("#udareazbs").val(),
						"area_size" : $("#udareasize").val(),
						"id" : $("#qyid").val()
					},
					dataType: 'json',
					timeout : 180000,
					success:function(json){
							alert(json.info);
							udareamap.clearMap();
							findarea();
					},
					error:function(){
					}		
				});
			}
		//}
	}
}
function resetudtip(){
	$("#udareaname").val("");
	$("#udareams").val("");
	$("#udareasize").val("");
	$("#udareazbs").val("");
}
//修改
$('#udarea_edit').dialog({
	autoOpen : false,
	width : 600,
	resizable : false,
	modal : true,
	title : "",
	buttons : [{
		html : "修改",
		"class" : "btn btn-danger",
		click : function() {
			editarea();
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

function editudarea(obj){
	$.ajax({
		url : 'findarea.action',
		type : 'post',
		data:{
			"id" : obj
		},
		dataType: 'json',
		timeout : 180000,
		success:function(json){
			var area = json.area;
			$("#udareaidedit").val(area[0].id);
			$("#udareanameedit").val(area[0].area_name);
			$("#udareamsedit").val(area[0].arae_describe);
			$("#udareasizeedit").val(area[0].area_size);
			$("#udareazbsedit").val(area[0].area_coordinates);
			$('#udarea_edit').dialog('open');
		},
		error:function(){
		}		
	});
}
function editarea(){
	$.ajax({
		url : 'editarea.action',
		type : 'post',
		data:{
			"id" : $("#udareaidedit").val(),
			"area_name" : $("#udareanameedit").val(),
			"area_describe" : $("#udareamsedit").val()
		},
		dataType: 'json',
		timeout : 180000,
		success:function(json){
				alert(json.info);
				udareamap.clearMap();
				findarea();
		},
		error:function(){
		}		
	});
}
function deleteudarea(obj){
	if(window.confirm("确定删除该数据？")){
	$.ajax({
		url : 'delarea.action', 
		type : 'post',
		data:{
			"id" : obj
		},
		dataType: 'json',
		timeout : 180000,
		success:function(json){
				alert(json.info);
				udareamap.clearMap();
				findarea();
		},
		error:function(){
			alert("请求失败");
		}		
	});
	}
}