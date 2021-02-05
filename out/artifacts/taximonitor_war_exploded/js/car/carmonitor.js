pageSetUp();
mapcarmonitor();
$('#monitortabsmany1').tabs();
	function switchSysBar1many1(){
		if ($("#switchpoing2many").val()=="3"){
			$("#switchpoing2many").val("4");
			$("#monitortabsmany1").css('display','none'); 
			 $("#mapdivmany1").height("100%"); 
			$("#utodmany1").attr("class", "fa fa-chevron-up");
		}
		else{
			$("#switchpoing2many").val("3");
			$("#monitortabsmany1").css('display','block');
			 $("#mapdivmany1").height("65%"); 
			$("#utodmany1").attr("class", "fa fa-chevron-down");
		}
	}

	function switchSysBarmany1(){
		if ($("#switchPointmany1").val()=="3"){
			$("#switchPointmany1").val("4");
			$("#monirightmany1").css('display','none'); 
			$("#lefttdmany1").width("98%"); 
			$("#ltormany1").attr("class", "fa fa-chevron-left");
		}
		else{
			$("#switchPointmany1").val("3");
			$("#monirightmany1").css('display','block');
			$("#lefttdmany1").width("74%"); 
			$("#ltormany1").attr("class", "fa fa-chevron-right");
		}
	}
	var vhicmonitor;
	var marker="";
	var list = [];
	function mapcarmonitor() {
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
		vhicmonitor = new AMap.Map("carmonitor", {
			center : position,
			level : 15,
			resizeEnable : true
		});//创建地图实例
		vhicmonitor.plugin( [ "AMap.ToolBar", "AMap.OverView", "AMap.Scale" ],
				function() {
					//加载工具条
				tool = new AMap.ToolBar( {
					direction : false,//隐藏方向导航
					ruler : false,//隐藏视野级别控制尺
					autoPosition : false
				//禁止自动定位
						});
				vhicmonitor.addControl(tool);
				//加载鹰眼
				view = new AMap.OverView();
				vhicmonitor.addControl(view);
				//加载比例尺
				scale = new AMap.Scale();
				vhicmonitor.addControl(scale);
				vhicmonitor.plugin( [ "AMap.MapType" ], function() {
					var type = new AMap.MapType( {
						defaultType : 0
					});//初始状态使用2D地图
						vhicmonitor.addControl(type);
					});
			});
	}
	
	$(document).ready(
			function() {
				$.ajax( {
					url : 'findvhic1.action',
					type : 'post',
					data : {},
					dataType : 'json',
					timeout : 180000,
					success : function(json) {
						var cust=json.list;
						var tab="";
						for ( var i = 0; i < cust.length; i++) {
							tab+="<option value='"+cust[i].vehi_no+"'></option>";
						}
						$("#manycar").html(tab);
					},
					error : function() {

					}
				});
				
			})
	var manyvehis = new Array(); 
var manysms = new Array(); 
var manyems = new Array(); 
function addmany(){
	manyvehis = new Array();
	manysms = new Array();
	manyems = new Array();
	$("[name='mcarname']").each(function () {
         var val = $(this).text(); //获取单个value
         manyvehis.push(val);
     });
	$("[name='mcartime']").each(function () {
        var val = $(this).text(); //获取单个value
        manyems.push(val);
        var et = getDate(val);
        var st = new Date(et.getTime() - 1000 * 60 * 10);
        manysms.push(setformatmany(st));
        
    });
	for(var s in manyvehis){
		if(manyvehis[s]==$("#carno").val()){
			alert("该车辆已存在");
			return;
		}else if($("#carno").val()=="0"){
			return;
		}
	}
	var mydate = new Date();
	var smydate = new Date(mydate.getTime() - 1000 * 60 * 10);
	manyvehis.push($("#carno").val());
	manysms.push(setformatmany(smydate));
	manyems.push(setformatmany(mydate));
	addmanytable();
	$("#carnumidmany").html(manyvehis.length);
	var mcs="";
	for(var s in manyvehis){
		mcs+=manyvehis[s]+",";
	}
	vhicdwxx(mcs);//将车号放入定位信息中
	querymanyhis($("#carno").val(),setformatmany(smydate));//传入时间，车号查询出的数据放入地图
}

function getDate(strDate) {
    var date = eval('new Date(' + strDate.replace(/\d+(?=-[^-]+$)/,
     function (a) { return parseInt(a, 10) - 1; }).match(/\d+/g) + ')');
    return date;
}
function addmanytable(){
	var trdaa="";
	for(var s in manyvehis){
		trdaa+="<tr ><td name='mcarname'>"+manyvehis[s]+"</td><td name='mcartime'>"+manyems[s]+"</td><td><img src='img/delete.png' onclick='deletemanyone(this.id)' id='"+manyvehis[s]+"'></td></tr>";
	}
	$("#datamany").html(trdaa);
}
//删除按钮，删除车辆
function deletemanyone(obj){
	for(var s in manyvehis){
		if(obj==manyvehis[s]){
			manyvehis.splice(s,1);
			manysms.splice(s,1);
			manyems.splice(s,1);
			break;
		}
	}
	$("#carnumidmany").html(manyvehis.length);
	var mcs="";
	for(var s in manyvehis){
		mcs+=manyvehis[s]+",";
	}
	if(mcs==""){
		mcs="2,";
	}
	vhicdwxx(mcs);
	addmanytable();
}
function setformatmany(obj){
	var y=(obj.getFullYear()).toString();
	var m=(obj.getMonth()+1).toString();
	if(m.length==1){
		m="0"+m;
	}
	var d=obj.getDate().toString();
	if(d.length==1){
		d="0"+d;
	}
	var h = obj.getHours().toString();
	if(h.length==1){
		h="0"+h;
	}
	var mi = obj.getMinutes().toString();
	if(mi.length==1){
		mi="0"+mi;
	}
	var s = obj.getSeconds().toString();
	if(s.length==1){
		s="0"+s;
	}
	var time=y+"-"+m+"-"+d+" "+h+":"+mi+":"+s;
	return time;
}
//查询车辆放入定位信息中
function vhicdwxx(obj){
	$.ajax({
		 type: "POST",
	        url:"findvhic.action",
	        data:{
				vehi_nolist : obj
	        },
	       dataType: 'json',
			timeout : 180000,
		success:function(json){
	        	var cust=json.list;
	        	var tab="";
	        	for(var i=0;i<cust.length;i++){
						tab +="<tr id='"+cust[i].vehi_no+"'><td nowrap='nowrap'>"+cust[i].vehi_no+"</td><td nowrap='nowrap'>"+cust[i].color+"</td><td nowrap='nowrap'>"+cust[i].sim_num+"</td><td nowrap='nowrap'>"+cust[i].speed+"</td>";
						if(cust[i].gpsstatus=="1"){
							tab +="<td nowrap='nowrap'>运动</td>";
						}else{
							tab +="<td nowrap='nowrap'>静止</td>";
						}
						tab+="<td nowrap='nowrap'>"+cust[i].stime+"</td><td nowrap='nowrap'>"+cust[i].address+"</td><td nowrap='nowrap'>"+cust[i].mt_name+"</td><td nowrap='nowrap'>"+cust[i].cartype+"</td></tr>";
	        	}
	        	$("#dw_datamany").html(tab);
		},
		error:function(){
			
		}		
	});
}
//查询车辆轨迹放入地图显示
function querymanyhis(vhic,time){
	var mydate =new Date();
	$.ajax({
		url : 'vhichistory.action',
		type : 'post',
		data:{
			"vehi_no" : vhic,
			"stime" : time,
			"etime" : setformatmany(mydate)
		},
		dataType: 'json',
		timeout : 180000,
		success:function(json){
			vehigpsmany=null;
			vehigpsmany = json.list;
			
			if(vehigpsmany.length!=0){
				for(var i=0;i<vehigpsmany.length;i++){
					if(i==0){
						addmksmanyhisstart(vehigpsmany[i]);
					}else{
						addmksmanyhis(vehigpsmany[i]);
					}
				}
				completeEventHandlermany(vehigpsmany);
			}else{
			}
		},
		error:function(){
		}		
	});
}
//将车辆起点放入地图
function addmksmanyhisstart(obj){
	var markerContent = document.createElement("div");
    markerContent.className = "markerContentStyle";
    //点标记中的图标
    var markerImg= document.createElement("img");
	markerImg.className="markerlnglat";
	markerImg.src="img/start.png";   
	markerImg.id="img"+obj.messageid
	markerContent.appendChild(markerImg);
  var txt = "<b style='color:#3399FF'>"+obj.vehi_no+"(起点)</b><br/><br/><b>[GPS时间]</b>："+obj.stime
  				  +"<br/><b>[车辆状态]</b>："+obj.status
				  +"<br/><b>[GPS速度]</b>："+obj.speed
				  +"<br/><b>[行驶方向]</b>："+dlwzmany(obj.distance)
				  +"<br/><b>[经纬度]</b>："+obj.px+","+obj.py
				  +"<br/><b>[位置描述]</b>："+obj.address;
	var marker1 = new AMap.Marker({
	    position:new AMap.LngLat(obj.px,obj.py), 
	    zIndex:102,  
	    offset:new AMap.Pixel(-20,-40), //相对于基点的偏移位置
	    draggable:false,  //是否可拖动
	    content:markerContent   //自定义点标记覆盖物内容
	});
	//$("#img"+obj.messageid).rotate(obj.direction-90);
	marker1.setMap(vhicmonitor);  //在地图上添加点
	var a=new AMap.LngLat(obj.px,obj.py);
	vhicmonitor.setCenter(a);
	//添加文本标记
	var info = [];
	info.push(txt);
	               
	var inforWindow = new AMap.InfoWindow({
	  offset:new AMap.Pixel(0,0),
	  content:info.join("<br>")
	});                 
	  AMap.event.addListener(marker1,"click",function(e){
		  inforWindow.open(vhicmonitor,marker1.getPosition());
		});
}
//将车辆除起点外的所有点放入地图
function addmksmanyhis(obj){
	var markerContent = document.createElement("div");
    markerContent.className = "markerContentStyle";
    //点标记中的图标
    var markerImg= document.createElement("img");
	markerImg.className="markerlnglat";
	markerImg.src="img/fx.jpg";   
	markerImg.id="img"+obj.messageid;
	markerContent.appendChild(markerImg);
	 var txt = "<b style='color:#3399FF'>"+obj.vehi_no+"</b><br/><br/><b>[GPS时间]</b>："+obj.stime
		  +"<br/><b>[车辆状态]</b>："+obj.status
	  +"<br/><b>[GPS速度]</b>："+obj.speed
	  +"<br/><b>[行驶方向]</b>："+dlwzmany(obj.distance)
	  +"<br/><b>[经纬度]</b>："+obj.px+","+obj.py
	  +"<br/><b>[位置描述]</b>："+obj.address;
				  var marker1 =null;
if(obj.status=="空车"){
	var marker1 = new AMap.Marker({
	    //map:mapObjhistory,
	    position:new AMap.LngLat(obj.px,obj.py),
	    offset:new AMap.Pixel(-14,-7), //相对于基点的偏移位置
	    draggable:false,  //是否可拖动
	    icon:"img/fx.jpg",
	  //  content:markerContent,   //自定义点标记覆盖物内容
	    angle:obj.distance-90
	});
	}else if(obj.status=="重车"){
		var marker1 = new AMap.Marker({
		    //map:mapObjhistory,
		    position:new AMap.LngLat(obj.px,obj.py),
		    offset:new AMap.Pixel(-14,-7), //相对于基点的偏移位置
		    draggable:false,  //是否可拖动
		    icon:"img/fx2.png",
		  //  content:markerContent,   //自定义点标记覆盖物内容
		    angle:obj.distance-90
		});
		}
	
	marker1.setMap(vhicmonitor);  //在地图上添加点
	//添加文本标记
	var info = [];
	info.push(txt);
	               
	var inforWindow = new AMap.InfoWindow({
	  offset:new AMap.Pixel(0,0),                 
	  content:info.join("<br>")                 
	});                 
	  AMap.event.addListener(marker1,"click",function(e){
		  inforWindow.open(vhicmonitor,marker1.getPosition());
		});

	//添加角度
	//markerlist.push(marker1);
}
//添加车辆路径线
function completeEventHandlermany(vehigpsmany){
	var markerhistorym=null;
	var markerContent = document.createElement("div");
    markerContent.className = "markerContentStyle";
    var markerImg= document.createElement("img");
	markerImg.className="markerlnglat";
	if(vehigpsmany[vehigpsmany.length-1].status=="空车"){
		markerImg.src="img/car2.png";
	}else if(vehigpsmany[vehigpsmany.length-1].status=="重车"){
		markerImg.src="img/car.png";
	}
	markerContent.appendChild(markerImg);
	var markerSpan = document.createElement("span");
	markerSpan.innerHTML = vehigpsmany[0].vehi_no;
	markerContent.appendChild(markerSpan);
	
		markerhistorym = new AMap.Marker({
	        map:vhicmonitor,
	        position:new AMap.LngLat(vehigpsmany[vehigpsmany.length-1].px,vehigpsmany[vehigpsmany.length-1].py),//基点位置
	        zIndex:101,
	        offset:new AMap.Pixel(-26,-14), //相对于基点的位置
	        autoRotation:true,
	        content:markerContent
	    });
	var manylineArr = new Array();
    for (var i = 0; i <vehigpsmany.length; i++){
    	var lngX=vehigpsmany[i].px;
    	var lngY=vehigpsmany[i].py;
    	if(i>0){
	    	var longi0 = vehigpsmany[i-1].px;
	    	var lati0 = vehigpsmany[i-1].py;
	    	if(lngX!=longi0||lngY!=lati0){
	    		manylineArr.push(new AMap.LngLat(lngX,lngY));
		    }else{
			  //  alert(lngY+"   "+(parseFloat(vehigps[i].lati)+0.00001));
		    	lngY=parseFloat(vehigpsmany[i].py)+0.00001*i;
		    	manylineArr.push(new AMap.LngLat(lngX,lngY));
			}
	    }else{
	    	manylineArr.push(new AMap.LngLat(lngX,lngY));
		}
    }
    //绘制轨迹
    var polylinem = new AMap.Polyline({
        map:vhicmonitor,
        path:manylineArr,
        strokeColor:"#000000",//线颜色
        strokeOpacity:1,//线透明度
        strokeWeight:3,//线宽
        strokeStyle:"solid"//线样式
    });
    if(vehigpsmany[0].vehinum==manyvehis[manyvehis.length-1]){
    	vhicmonitor.setFitView();
	}
  }
var sms3;
monitorallmany1();
function monitorallmany1(){
	if(sms3!=null&&cljk==1){
		clearTimeout(sms3);
		return;
		}
	if(manyvehis.length==0){
		vhicmonitor.clearMap();
	}else{
		var mcs="";
		vhicmonitor.clearMap();
		for(var s in manyvehis){
			mcs+=manyvehis[s]+",";
			querymanyhis(manyvehis[s],manysms[s]);
		}
		vhicdwxx(mcs);
	}
	if(cljk==0){
		sms3 = setTimeout("monitorallmany1()",30000);
	}else if(cljk==1){
		//alert("暂停");
	}
}

function dlwzmany(obj){
	if(obj==0||obj==360){
		return "正北";
	}else if(obj==90){
		return "正东";
	}else if(obj==180){
		return "正南";
	}else if(obj==270){
		return "正西";
	}else if(obj>0&&obj<90){
		return "东北";
	}else if(obj>90&&obj<180){
		return "东南";
	}else if(obj>180&&obj<270){
		return "西南";
	}else if(obj>270&&obj<360){
		return "西北";
	}
}
(function(){
	var tbody = document.querySelector('#dw_tablemany').tBodies[0];
	var th = document.querySelector('#dw_tablemany').tHead.rows[0].cells;
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