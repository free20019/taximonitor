pageSetUp();
initializearea();
findareavhic();

function switchSysBar1(){
	if ($("#switchPoint1").val()=="3"){
		$("#switchPoint1").val("4");
		$("#monitortabs").css('display','none'); 
		 $("#mapdiv").height("100%"); 
		$("#utod").attr("class", "fa fa-chevron-up");
	}
	else{
		$("#switchPoint1").val("3");
		$("#monitortabs").css('display','block');
		 $("#mapdiv").height("65%"); 
		$("#utod").attr("class", "fa fa-chevron-down");
	}
}

function switchSysBar(){
	if ($("#switchPoint").val()=="3"){
		$("#switchPoint").val("4");
		$("#moniright").css('display','none'); 
		$("#lefttd").width("98%"); 
		$("#ltor").attr("class", "fa fa-chevron-left");
	}
	else{
		$("#switchPoint").val("3");
		$("#moniright").css('display','block');
		$("#lefttd").width("74%"); 
		$("#ltor").attr("class", "fa fa-chevron-right");
	}
}
var type=0;
var marker="";
var markerlist = new Array();
var mapObjarea;
function initializearea() {
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
	 mapObjarea=new AMap.Map("area-data-div",{center:position,level:15,resizeEnable:true});//创建地图实例
	 mapObjarea.plugin(["AMap.ToolBar","AMap.OverView","AMap.Scale"],function(){
		  //加载工具条
		  tool=new AMap.ToolBar({
		    direction:false,//隐藏方向导航
		    ruler:false,//隐藏视野级别控制尺
		    autoPosition:false//禁止自动定位
		  });
		  mapObjarea.addControl(tool);
		  //加载鹰眼
		  view=new AMap.OverView();
		  mapObjarea.addControl(view);
		  //加载比例尺
		  scale=new AMap.Scale();
		  mapObjarea.addControl(scale);
		  mapObjarea.plugin(["AMap.MapType"], function() {
				var type = new AMap.MapType({defaultType:0});//初始状态使用2D地图
				mapObjarea.addControl(type);
			});
		});
	 AMap.event.addListener(mapObjarea,'click',function(e) {
			if(inforWindowone!=""){
				inforWindowone.close();
			}
		});
}

var vehilist= null;
var aid;
var aaid="";
var anid=0;
var qy;
function findareavhic(){
//	$.blockUI({message:"<div style='padding:5px;'><h3><font color='blue'>数据查询中,请稍候...</font></h3><img src='img/ajax-loader.gif' border='0'/><br/><br/></div>"});
	//$("#area_data").html("<tr><td align='center' colspan='3'><img src='img/select2-spinner.gif' /></td></tr>");
	if(qy!=null&&qyjk==1){
		clearTimeout(qy);
		return;
		}
	$.ajax({
		url : 'findareavhic.action',
		type : 'post',
		data:{
		},
		dataType: 'json',
		timeout : 180000,
		success:function(json){
			vehilist = json.list;
			mapObjarea.clearMap();
			markers= [];
			var arealist = json.area;
			for(j=0;j<vehilist.length;j++){
					addmks1(vehilist[j]);
			}
			areadata="";
			 amap.isEmpty();
			
			if(j==vehilist.length){
				addCluster(1);
				for(var i=0;i<arealist.length;i++){
					addPolygon(arealist[i].id,arealist[i].area_coordinates,arealist[i].area_name,arealist[i].arae_describe,arealist[i].area_size);
					var zbs = arealist[i].area_coordinates.split(";");
					var lo = zbs[0].split(",")[0];
					var la = zbs[0].split(",")[1];
				}
				$("#areanums").html(areadata);
//				if(aaid!=""&&anid==1){
//					getvehidwmsg(amap.get(parseInt(aaid)));
//				}
			}
		if(qyjk==0){
			qy = setTimeout("findareavhic()",30000);
		}else if(qyjk==1){
		}
		},
		error:function(){
		}		
	});
}
//添加多边形覆盖物
var areadata="";
var amap = new Map();  
function addPolygon(obja,obj,name,ms,sz){  
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
	var cars=0;  
	var areavehis=""
	for(j=0;j<vehilist.length;j++){
		var po = new AMap.LngLat(vehilist[j].px,vehilist[j].py);
		if(polygon.contains(po)){
			cars++;
			areavehis+=vehilist[j].vehi_no+";";
		}
	}
	amap.put(parseInt(obja),areavehis);
		areadata+="<tr style='color:#009933' ondblclick='gotoareamoni("+zbs[0].split(",")[0]+","+zbs[0].split(",")[1]+","+obja+","+cars+")'><td>"+name+"</td><td>"+cars+"</td></tr>";
	polygon.setMap(mapObjarea);  

		name = name+": "+cars;
	 	var markerContent = document.createElement("div");
	    markerContent.className = "txtstyle";
		var markerSpan = document.createElement("span");
		markerSpan.innerHTML = name;
		markerContent.appendChild(markerSpan);
		ms = zpp(ms);
//		markerContent.onclick=function() { addTab(100,name,"find.action?time="+ntime+"&id="+id);};//点击区域事件
		markerContent.onmouseover=function() {markerSpan.innerHTML =name+"<br/><br/>区域描述:</br>"+ms+"<br/><br/>区域面积:"+sz};
		markerContent.onmouseout=function() {markerSpan.innerHTML = name;};
	    var marker = new AMap.Marker({
		    map:mapObjarea,
		     zIndex:10001, 
		    position:new AMap.LngLat( zbs[0].split(",")[0],zbs[0].split(",")[1]),     
		    offset:new AMap.Pixel(-14,7), //相对于基点的偏移位置
		    draggable:false,  //是否可拖动
		    content:markerContent  //自定义点标记覆盖物内容
		});
}


var markers= [];
function addmks1(obj){
	var markerContent = document.createElement("div");
    markerContent.className = "markerContentStyle";
    //点标记中的图标
    var markerImg= document.createElement("img");
	markerImg.className="markerlnglat";
	if(obj.status=="0"){
		markerImg.src="img/car/h.png";
	}else{
		markerImg.src="img/car/c.png";
	}
	
	markerContent.appendChild(markerImg);
	var marker1 = new AMap.Marker({
	 //   map:mapObjhistory,
	    position:new AMap.LngLat(obj.px,obj.py),     
	    offset:new AMap.Pixel(-0,-0), //相对于基点的偏移位置
	    draggable:false,  //是否可拖动
	    content:markerContent   //自定义点标记覆盖物内容
	});
	marker1.setMap(mapObjarea);  //在地图上添加点
	markers.push(marker1);
	var txt = "<table><tr><td><b style='color:#3399FF'>"+obj.vehi_no+"</b></td><td></td></tr><tr><td><b>[所属公司]</b>："+obj.comp_name+"</tr></td><tr><td><b>[车辆类型]</b>："+obj.cartype+"</tr></td><tr><td><b>[车辆颜色]</b>："+obj.color+"</tr></td><tr><td><b>[SIM卡]</b>："+obj.sim_num+"</tr></td><tr><td><b>[车辆所属人]</b>："+obj.own_name
	  +"</tr></td><tr><td><b>[联系电话]</b>："+obj.own_tel+"</tr></td><tr><td><b>[经度]</b>："+obj.px+"</tr></td><tr><td><b>[纬度]</b>："+obj.py;
	var info = [];
	info.push(txt);
	var inforWindow = new AMap.InfoWindow({
	  offset:new AMap.Pixel(0,0),
	  content:info.join("</tr></td></table>")
	});                 
	  AMap.event.addListener(marker1,"click",function(e){                 
		  inforWindow.open(mapObjarea,marker1.getPosition());                 
		});
	  AMap.event.addListener(mapObjarea,"click",function(e){                 
		  inforWindow.close();              
		});
}
var cluster;
function addCluster(tag)
{
	if(cluster) {	
		cluster.setMap(null);
	}
	if(tag==1) {
		var sts=[{url:"img/car/12.png", size:new AMap.Size(32,32),offset:new AMap.Pixel(-16,-30)},
			{url:"img/car/11.png", size:new AMap.Size(32,32),offset:new AMap.Pixel(-16,-30)},
			{url:"img/car/13.jpg", size:new AMap.Size(48,48),offset:new AMap.Pixel(-24,-45),textColor:'#CC0066'}];
		mapObjarea.plugin(["AMap.MarkerClusterer"],function(){
			cluster = new AMap.MarkerClusterer(mapObjarea,markers,{minClusterSize:10,styles:sts});
		});
	}
	else {
		mapObjarea.plugin(["AMap.MarkerClusterer"],function(){
			cluster = new AMap.MarkerClusterer(mapObjarea,markers);
		});
	}
}
function gotoareamoni(lom,lam,obja,cs){
	anid=1;
	var po = new AMap.LngLat(lom,lam);
	mapObjarea.setZoomAndCenter(16,po);
	aaid=parseInt(obja);
	 getvehidwmsg(amap.get(parseInt(aaid)));
//	$("#carnumid").html(cs);
}
function getvehidwmsg(carids){
	$("#areatbody").html("<tr><td align='center' colspan='12'><img src='img/select2-spinner.gif' /></td></tr>");
	$.ajax({
		url : 'findvhic.action',
		type : 'post',
		data:{
			"vhic" : carids,
		},
		dataType: 'json',
		timeout : 180000,
		success:function(json){
			var vehidwgps = json.list;
			var trdata="";
			for(var i=0;i<vehidwgps.length;i++){
				var kzc ="";
				if(vehidwgps[i].status=="0"){
					if(vehidwgps[i].gspstatus=="1"){
						kzc ="重车";
					}else{
						kzc ="空车";
					}
				}else{
					kzc ="未上线";
				}
				trdata +="<tr ondblclick='carmarker(this.id)' id='"+vehidwgps[i].vehi_no+"'><td>"+(i+1)+"</td><td nowrap='nowrap'>"+vehidwgps[i].vehi_no+"</td><td nowrap='nowrap'>"+vehidwgps[i].color+"</td><td nowrap='nowrap'>"+vehidwgps[i].sim_num+"</td><td nowrap='nowrap'>"+vehidwgps[i].speed+"</td>";
				trdata+="<td nowrap='nowrap'>"+kzc+"</td><td nowrap='nowrap'>"+vehidwgps[i].stime+"</td><td nowrap='nowrap'>"+vehidwgps[i].address+"</td><td nowrap='nowrap'>"+vehidwgps[i].mt_name+"</td><td nowrap='nowrap'>"+vehidwgps[i].cartype+"</td></tr>";
			}
			$("#carnumid").html(vehidwgps.length);
			$("#areatbody").html(trdata);
		},
		error:function(){
			
		}		
	});
}
function carmarker(obj){
	$.ajax({
		 type: "POST",
	        url:"findvhic.action",
	        data:{
				vehi_no : obj
	        },
	       dataType: 'json',
			timeout : 180000,
		success:function(json){
	        	var cust=json.list;
	        	for(var i=0;i<cust.length;i++){
	        		addMarke2(cust[i]);
	        	}
		},
		error:function(){
			
		}		
	});
}
function addMarke2(vehicle){
	if(marker!=""){
		marker.setMap(null);
	}
	var markerContent = document.createElement("div");
    markerContent.className = "txtstyle";
    //点标记中的图标
    var markerImg= document.createElement("img");
	markerImg.className="markerlnglat";
	if(vehicle.status=="1"){
			markerImg.src="img/car/h.png";   
	}else{
		markerImg.src="img/car/c.png";   
	}
	markerContent.appendChild(markerImg);
	marker = new AMap.Marker({
	    map:mapObjarea,
	    position:new AMap.LngLat(vehicle.px,vehicle.py),     
	    offset:new AMap.Pixel(-0,-0), //相对于基点的偏移位置
	    draggable:false,  //是否可拖动
	    content:markerContent   //自定义点标记覆盖物内容
	});
	mapObjarea.setCenter(new AMap.LngLat(vehicle.px,vehicle.py));
	var txt = "<table><tr><td><b style='color:#3399FF'>"+vehicle.vehi_no+"</b></td></tr><tr><td><b>[所属公司]</b>："+vehicle.comp_name+"</tr></td><tr><td><b>[车辆类型]</b>："+vehicle.cartype+"</tr></td><tr><td><b>[车辆颜色]</b>："+vehicle.color+"</tr></td><tr><td><b>[SIM卡]</b>："+vehicle.sim_num+"</tr></td><tr><td><b>[车辆所属人]</b>："+vehicle.own_name
	  +"</tr></td><tr><td><b>[联系电话]</b>："+vehicle.own_tel+"</tr></td><tr><td><b>[经度]</b>："+vehicle.px+"</tr></td><tr><td><b>[纬度]</b>："+vehicle.py;
	var info = [];
	info.push(txt);
	               
	var inforWindowone = new AMap.InfoWindow({                 
	  offset:new AMap.Pixel(5,10),                 
	  content:info.join("</tr></td></table>")                 
	});           
	  inforWindowone.open(mapObjarea,marker.getPosition());                 
	  AMap.event.addListener(marker,"click",function(e){                 
		  inforWindowone.open(mapObjarea,marker.getPosition());                 
		});
	  
}
function zpp(obj){
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

(function(){
	var tbody = document.querySelector('#dw_table3').tBodies[0];
	var th = document.querySelector('#dw_table3').tHead.rows[0].cells;
	var td = tbody.rows;
	for(var i = 0;i < th.length;i++){
	    th[i].flag = 1;
	    th[i].onclick = function(){
	        sort(this.getAttribute('data-type'),this.flag,this.cellIndex);
	        this.flag = -this.flag;
	    };
	};
	function sort(str,flag,n){
	    var arr = [];
	    for(var i = 0;i < td.length;i++){
	        arr.push(td[i]);
	    };
	    arr.sort(function(a,b){
	        return method(str,a.cells[n].innerHTML,b.cells[n].innerHTML) * flag;
	    });
	    for(var i = 0;i < arr.length;i++){
	        tbody.appendChild(arr[i]);
	    };
	};
	function method(str,a,b){
	    switch(str){
	    case 'num': 
	        return a-b;
	        break;
	    case 'string': 
	        return a.localeCompare(b);
	        break;
	    default:
	        return new Date(a.split('-').join('/')).getTime()-new Date(b.split('-').join('/')).getTime();
	    };
	};
})();