pageSetUp();
initializemon();
findfencevhic();

function switchSysBarmon1mon(){
	if ($("#switchPointmon1mon").val()=="3"){
		$("#switchPointmon1mon").val("4");
		$("#monitortabsmon").css('display','none'); 
		 $("#mapdivmon").height("100%"); 
		$("#utodmon").attr("class", "fa fa-chevron-up");
	}
	else{
		$("#switchPointmon1mon").val("3");
		$("#monitortabsmon").css('display','block');
		 $("#mapdivmon").height("65%"); 
		$("#utodmon").attr("class", "fa fa-chevron-down");
	}
}

function switchSysBarmon(){
	if ($("#switchPointmon").val()=="3"){
		$("#switchPointmon").val("4");
		$("#monirightmon").css('display','none'); 
		$("#lefttdmon").width("98%"); 
		$("#ltormon").attr("class", "fa fa-chevron-left");
	}
	else{
		$("#switchPointmon").val("3");
		$("#monirightmon").css('display','block');
		$("#lefttdmon").width("74%"); 
		$("#ltormon").attr("class", "fa fa-chevron-right");
	}
}
var type=0;
var marker="";
var markerlist = new Array();
var mapObjfencemon;
function initializemon() {
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
	 mapObjfencemon=new AMap.Map("fancemon-data-div",{center:position,level:15,resizeEnable:true});//创建地图实例
	 mapObjfencemon.plugin(["AMap.ToolBar","AMap.OverView","AMap.Scale"],function(){
		  //加载工具条
		  tool=new AMap.ToolBar({
		    direction:false,//隐藏方向导航
		    ruler:false,//隐藏视野级别控制尺
		    autoPosition:false//禁止自动定位
		  });
		  mapObjfencemon.addControl(tool);
		  //加载鹰眼
		  view=new AMap.OverView();
		  mapObjfencemon.addControl(view);
		  //加载比例尺
		  scale=new AMap.Scale();
		  mapObjfencemon.addControl(scale);
		  mapObjfencemon.plugin(["AMap.MapType"], function() {
				var type = new AMap.MapType({defaultType:0});//初始状态使用2D地图
				mapObjfencemon.addControl(type);
			});
		});
	 AMap.event.addListener(mapObjfencemon,'click',function(e) {
			if(inforWindowone!=""){
				inforWindowone.close();
			}
		});
}

var vehilist= null;
var aid;
var aaid="";
var anid=0;
var vehidwgps=null;
var dz;
function findfencevhic(){
	$.ajax({
		url : 'findfence.action',
		type : 'post',
		data:{
		},
		dataType: 'json',
		timeout : 180000,
		success:function(json){
			mapObjfencemon.clearMap();
			markers= [];
			var arealist = json.area;
			areadata="";
			 amap.isEmpty();
			var tab="";
				for(var i=0;i<arealist.length;i++){
					tab+="<tr ondblclick=addPolygon(\""+arealist[i].id+"\",\""+arealist[i].area_coordinates+"\",\""+arealist[i].area_name+"\",\""+arealist[i].arae_describe+"\",\""+arealist[i].area_size+"\")><td>"+arealist[i].area_name+"</td>";
					tab+="<td>"+arealist[i].user_id+"</td>";
					tab+="<td>"+arealist[i].alarmnum+"</td></tr>";
				}
				$("#areanumsmon").html(tab);
		},
		error:function(){
		}		
	});
}
//添加多边形覆盖物
var areadata="";
var xhid;
var o;
var n;
var m;
var s;
var amap = new Map();  
function addPolygon(obja,obj,name,ms,sz){ 
	mapObjfencemon.clearMap();
	xhid=obja;
	o=obj;
	n=name;
	m=ms;
	s=sz;
	if(dz!=null&&dzwl==1){
		clearTimeout(dz);
		return;
		}
	findvhicmon(obja);
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
	for(j=0;j<vehidwgps.length;j++){
		var po = new AMap.LngLat(vehidwgps[j].px,vehidwgps[j].py);
		if(!polygon.contains(po)){
			$("#wzvhic").append("<option value='"+vehidwgps[j].vehi_no+"' >"+vehidwgps[j].vehi_no+"  "+vehidwgps[j].stime+"</option>");
		}
	}
	amap.put(parseInt(obja),areavehis);
		areadata+="<tr style='color:#009933' ondblclick='gotoareamoni("+zbs[0].split(",")[0]+","+zbs[0].split(",")[1]+","+obja+","+cars+")'><td>"+name+"</td><td>"+cars+"</td></tr>";
	polygon.setMap(mapObjfencemon);  

	 	var markerContent = document.createElement("div");
	    markerContent.className = "txtstyle";
		var markerSpan = document.createElement("span");
		markerSpan.innerHTML = name;
		markerContent.appendChild(markerSpan);
		ms = zpp(ms);
		markerContent.onclick=function() { addTab(100,name,"find.action?time="+ntime+"&id="+id);};
		markerContent.onmouseover=function() {markerSpan.innerHTML =name+"<br/><br/>区域描述:</br>"+ms+"<br/><br/>区域面积:"+sz};
		markerContent.onmouseout=function() {markerSpan.innerHTML = name;};
	    var marker = new AMap.Marker({
		    map:mapObjfencemon,
		     zIndex:10001, 
		    position:new AMap.LngLat( zbs[0].split(",")[0],zbs[0].split(",")[1]),     
		    offset:new AMap.Pixel(-14,7), //相对于基点的偏移位置
		    draggable:false,  //是否可拖动
		    content:markerContent  //自定义点标记覆盖物内容
		});
	    if(dzwl==0){
			dz =setTimeout("addPolygon('"+xhid+"','"+o+"','"+n+"','"+m+"','"+s+"')",30000);
		}else if(dzwl==1){
		}
}


var markers= [];
function addmks2(obj){
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
	marker1.setMap(mapObjfencemon);  //在地图上添加点
	markers.push(marker1);
	var txt = "<table><tr><td><b style='color:#3399FF'>"+obj.vehi_no+"</b></td><td></td></tr><tr><td><b>[所属公司]</b>："+obj.comp_name+"</tr></td><tr><td><b>[车辆类型]</b>："+obj.cartype+"</tr></td><tr><td><b>[车辆颜色]</b>："+obj.color+"</tr></td><tr><td><b>[SIM卡]</b>："+obj.sim_num+"</tr></td><tr><td><b>[车辆所属人]</b>："+obj.own_name
	  +"</tr></td><tr><td><b>[联系电话]</b>："+obj.own_tel+"</tr></td><tr><td><b>[经度]</b>："+obj.px+"</tr></td><tr><td><b>[纬度]</b>："+obj.py+"</tr></td><tr><td><b>[所在位置]</b>："+obj.address;
	var info = [];
	info.push(txt);
	var inforWindow = new AMap.InfoWindow({
	  offset:new AMap.Pixel(0,0),
	  content:info.join("</tr></td></table>")
	});                 
	  AMap.event.addListener(marker1,"click",function(e){                 
		  inforWindow.open(mapObjfencemon,marker1.getPosition());                 
		});
	  AMap.event.addListener(mapObjfencemon,"click",function(e){                 
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
		mapObjfencemon.plugin(["AMap.MarkerClusterer"],function(){
			cluster = new AMap.MarkerClusterer(mapObjfencemon,markers,{minClusterSize:10,styles:sts});
		});
	}
	else {
		mapObjfencemon.plugin(["AMap.MarkerClusterer"],function(){
			cluster = new AMap.MarkerClusterer(mapObjfencemon,markers);
		});
	}
}

function findvhicmon(carids){
	$("#areatbodymon").html("<tr><td align='center' colspan='12'><img src='img/select2-spinner.gif' /></td></tr>");
	$.ajax({
		url : 'findfencevhicinfo.action',
		type : 'post',
		data:{
			"id" : carids,
		},
		async:false,
		dataType: 'json',
		timeout : 180000,
		success:function(json){
			vehidwgps = json.list;
			var trdata="";
			for(var i=0;i<vehidwgps.length;i++){
				addmks2(vehidwgps[i]);
				var kzc ="";
				if(vehidwgps[i].status=="0"){
					if(vehidwgps[i].gspstatus=="1"){
						kzc +="<td nowrap='nowrap'>重车</td>";
					}else{
						kzc +="<td nowrap='nowrap'>空车</td>";
					}
				}else{
					kzc +="<td nowrap='nowrap'>未上线</td>";
				}
				trdata +="<tr ondblclick='carmarker1(this.id)' id='"+vehidwgps[i].vehi_no+"'><td>"+(i+1)+"</td><td nowrap='nowrap'>"+vehidwgps[i].vehi_no+"</td><td nowrap='nowrap'>"+vehidwgps[i].color+"</td><td nowrap='nowrap'>"+vehidwgps[i].sim_num+"</td><td nowrap='nowrap'>"+vehidwgps[i].speed+"</td>";
				trdata+="<td nowrap='nowrap'>"+kzc+"</td><td nowrap='nowrap'>"+vehidwgps[i].stime+"</td><td nowrap='nowrap'>"+vehidwgps[i].address+"</td><td nowrap='nowrap'>"+vehidwgps[i].mt_name+"</td><td nowrap='nowrap'>"+vehidwgps[i].cartype+"</td></tr>";
			}
			$("#carnumidmon").html(vehidwgps.length);
			$("#areatbodymon").html(trdata);
		},
		error:function(){
			
		}		
	});
}
function carmarker1(obj){
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
	        		addMarke3(cust[i]);
	        	}
		},
		error:function(){
			
		}		
	});
}
function addMarke3(vehicle){
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
	    map:mapObjfencemon,
	    position:new AMap.LngLat(vehicle.px,vehicle.py),     
	    offset:new AMap.Pixel(-0,-0), //相对于基点的偏移位置
	    draggable:false,  //是否可拖动
	    content:markerContent   //自定义点标记覆盖物内容
	});
	mapObjfencemon.setCenter(new AMap.LngLat(vehicle.px,vehicle.py));
	var txt = "<table><tr><td><b style='color:#3399FF'>"+vehicle.vehi_no+"</b></td></tr><tr><td><b>[所属公司]</b>："+vehicle.comp_name+"</tr></td><tr><td><b>[车辆类型]</b>："+vehicle.cartype+"</tr></td><tr><td><b>[车辆颜色]</b>："+vehicle.color+"</tr></td><tr><td><b>[SIM卡]</b>："+vehicle.sim_num+"</tr></td><tr><td><b>[车辆所属人]</b>："+vehicle.own_name
	  +"</tr></td><tr><td><b>[联系电话]</b>："+vehicle.own_tel+"</tr></td><tr><td><b>[经度]</b>："+vehicle.px+"</tr></td><tr><td><b>[纬度]</b>："+vehicle.py+"</tr></td><tr><td><b>[所在位置]</b>："+vehicle.address;
	var info = [];
	info.push(txt);
	               
	var inforWindowone = new AMap.InfoWindow({                 
	  offset:new AMap.Pixel(5,10),                 
	  content:info.join("</tr></td></table>")                 
	});           
	  inforWindowone.open(mapObjfencemon,marker.getPosition());                 
	  AMap.event.addListener(marker,"click",function(e){                 
		  inforWindowone.open(mapObjfencemon,marker.getPosition());                 
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
	var tbody = document.querySelector('#dw_table11').tBodies[0];
	var th = document.querySelector('#dw_table11').tHead.rows[0].cells;
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