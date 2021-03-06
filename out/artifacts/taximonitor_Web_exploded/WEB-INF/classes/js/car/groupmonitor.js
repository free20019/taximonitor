//pageSetUp();
initialize();
findgroupnum();
var mapObjmm;
function initialize() {
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
	 mapObjmm=new AMap.Map("data-div",{center:position,level:15,resizeEnable:true});//创建地图实例
	 mapObjmm.plugin(["AMap.ToolBar","AMap.OverView","AMap.Scale"],function(){
		  //加载工具条
		  tool=new AMap.ToolBar({ 
		    direction:false,//隐藏方向导航
		    ruler:false,//隐藏视野级别控制尺
		    autoPosition:false//禁止自动定位
		  });
		  mapObjmm.addControl(tool);
		  //加载鹰眼
		  view=new AMap.OverView();
		  mapObjmm.addControl(view);
		  //加载比例尺
		  scale=new AMap.Scale();
		  mapObjmm.addControl(scale);
		  mapObjmm.plugin(["AMap.MapType"], function() {
				var type = new AMap.MapType({defaultType:0});//初始状态使用2D地图
				mapObjmm.addControl(type);
			});
		});
	 AMap.event.addListener(mapObjmm,'click',function(e) {
			if(inforWindowone!=""){
				inforWindowone.close();
			}
		});
}


function switchSysBar1many(){
	if ($("#switchPoint1manyvhic").val()=="3"){
		$("#switchPoint1manyvhic").val("4");
		$("#monitortabsmanyvhicvhic").css('display','none'); 
		 $("#mapdivmanyvhic").height("100%"); 
		$("#utodmanyvhic").attr("class", "fa fa-chevron-up");
	}else{
		$("#switchPoint1manyvhic").val("3");
		$("#monitortabsmanyvhic").css('display','block');
		 $("#mapdivmanyvhic").height("65%"); 
		$("#utodmanyvhic").attr("class", "fa fa-chevron-down");
	}
}

function switchSysBarmany(){
	if ($("#switchPointmanyvhic").val()=="3"){
		$("#switchPointmanyvhic").val("4");
		$("#monirightmanyvhic").css('display','none'); 
		$("#lefttdmanyvhic").width("98%"); 
		$("#ltormanyvhic").attr("class", "fa fa-chevron-left");
	}else{
		$("#switchPointmanyvhic").val("3");
		$("#monirightmanyvhic").css('display','block');
		$("#lefttdmanyvhic").width("74%"); 
		$("#ltormanyvhic").attr("class", "fa fa-chevron-right");
	}
}
var clz;
function findgroupnum(){
	if(clz!=null&&clzjk==1){
		clearTimeout(clz);
		return;
		}
	$.ajax({
		 type: "POST",
	        url:"findgroupnum.action",
	        data:{
	        },
	       dataType: 'json',
			timeout : 180000,
		success:function(json){
	        	var cust=json.group;
	        	var tab="";
	        	for(var i=0;i<cust.length;i++){
	        		tab+="<tr id='"+cust[i].id+"' ondblclick='findnum(this.id)'><td>&nbsp;&nbsp;"+cust[i].groupname+"</td>";
	        		tab+="<td>&nbsp;&nbsp;"+cust[i].groupvhic+"</td></tr>";
	        	}
	        	$("#monitorcartbody").html(tab);
	        	if(clzjk==0){
	        		clz =setTimeout("findgroupnum()",30000);
	    		}else if(clzjk==1){
	    		//	alert("实时监控刷新暂停");
	    		}
		},
		error:function(){
			
		}		
	});
}
var ids;
var clzs;
function findnum(obj){
	if(clzs!=null&&clzjk==1){
		clearTimeout(clzs);
		return;
		}
	ids=obj;
	$.ajax({
		 type: "POST",
	        url:"findgroupinfo.action",
	        data:{
				id : obj
	        },
	       dataType: 'json',
			timeout : 180000,
		success:function(json){
	        	mapObjmm.clearMap();
	        	var cust=json.list;
	        	var tab="";
	        	for(var i=0;i<cust.length;i++){
	        		addmksvhic(cust[i]);
						tab +="<tr ondblclick='vhicmarker1(this.id);' class='demo12' id='"+cust[i].vehi_no+"'><td nowrap='nowrap'>"+cust[i].vehi_no+"</td><td nowrap='nowrap'>"+cust[i].color+"</td><td nowrap='nowrap'>"+cust[i].sim_num+"</td><td nowrap='nowrap'>"+cust[i].speed+"</td>";
						if(cust[i].status=="0"){
							if(cust[i].gspstatus=="1"){
								tab +="<td nowrap='nowrap'>重车</td>";
							}else{
								tab +="<td nowrap='nowrap'>空车</td>";
							}
						}else{
							tab +="<td nowrap='nowrap'>未上线</td>";
						}
						tab+="<td nowrap='nowrap'>"+cust[i].stime+"</td><td nowrap='nowrap'>"+cust[i].address+"</td><td nowrap='nowrap'>"+cust[i].mt_name+"</td><td nowrap='nowrap'>"+cust[i].cartype+"</td></tr>";
	        	}
	        	$("#vehinonow").html(tab);
	        	if(clzjk==0){
	        		clzs =setTimeout("findnum('"+ids+"')",30000);
	    		}else if(clzjk==1){
	    		//	alert("实时监控刷新暂停");
	    		}
	        	addRightMenu();
		},
		error:function(){
			
		}		
	});
}

function addRightMenu(){
	$(".demo12").contextMenu('myMenu1', {
		          bindings: 
		          {
		            'open1': function(t) {
		              alert('Trigger was '+t.id+'\nAction was Open');
		            },
		            'email1': function(t) {
		              alert('Trigger was '+t.id+'\nAction was Email');
		            },
		            'save1': function(t) {
		              alert('Trigger was '+t.id+'\nAction was Save');
		            },
		            'delete1': function(t) {
		              alert('Trigger was '+t.id+'\nAction was Delete');
		            }
		          }
		    });
}

var markervhic= [];
function addmksvhic(obj){
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
	marker1.setMap(mapObjmm);  //在地图上添加点
	markervhic.push(marker1);
	var txt = "<table><tr><td><b style='color:#3399FF'>"+obj.vehi_no+"</b></td><td></td></tr><tr><td><b>[所属公司]</b>："+obj.comp_name+"</tr></td><tr><td><b>[车辆类型]</b>："+obj.cartype+"</tr></td><tr><td><b>[车辆颜色]</b>："+obj.color+"</tr></td><tr><td><b>[SIM卡]</b>："+obj.sim_num+"</tr></td><tr><td><b>[车辆所属人]</b>："+obj.own_name
	  +"</tr></td><tr><td><b>[联系电话]</b>："+obj.own_tel+"</tr></td><tr><td><b>[经度]</b>："+obj.px+"</tr></td><tr><td><b>[纬度]</b>："+obj.py+"</tr></td><tr><td><b>[所在位置]</b>："+obj.address;
	var info = [];
	info.push(txt);
	var inforWindow = new AMap.InfoWindow({
	  offset:new AMap.Pixel(0,0),
	  content:info.join("</tr></td></table>")
	});                 
	  AMap.event.addListener(marker1,"click",function(e){                 
		  inforWindow.open(mapObjmm,marker1.getPosition());                 
		});
	  AMap.event.addListener(mapObjmm,"click",function(e){                 
		  inforWindow.close();              
		});
}


//双击车辆信息，将该车辆添加到地图上显示
function vhicmarker1(obj){
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
	        		addMarker1(cust[i]);
	        	}
		},
		error:function(){
			
		}		
	});
}
//将查询到车号打点到地图
function addMarker1(vehicle){
	//每次加载将上次地图添加的点删除
	if(marker!=""){
		marker.setMap(null);
	}
	var markerContent = document.createElement("div");
    markerContent.className = "txtstyle";
    //点标记中的图标
    var markerImg= document.createElement("img");
	markerImg.className="markerlnglat";
	if(vehicle.status=="0"){
		markerImg.src="img/car/h.png";   
	}else{
		markerImg.src="img/car/c.png";   
	}
	markerContent.appendChild(markerImg);
	marker = new AMap.Marker({
	    map:mapObjmm,
	    position:new AMap.LngLat(vehicle.px,vehicle.py),     
	    offset:new AMap.Pixel(-0,-0), //相对于基点的偏移位置
	    draggable:false,  //是否可拖动
	    content:markerContent   //自定义点标记覆盖物内容
	});
	mapObjmm.setCenter(new AMap.LngLat(vehicle.px,vehicle.py));
	var txt = "<table><tr><td><b style='color:#3399FF'>"+vehicle.vehi_no+"</b></td></tr><tr><td><b>[所属公司]</b>："+vehicle.comp_name+"</tr></td><tr><td><b>[车辆类型]</b>："+vehicle.cartype+"</tr></td><tr><td><b>[车辆颜色]</b>："+vehicle.color+"</tr></td><tr><td><b>[SIM卡]</b>："+vehicle.sim_num+"</tr></td><tr><td><b>[车辆所属人]</b>："+vehicle.own_name
	  +"</tr></td><tr><td><b>[联系电话]</b>："+vehicle.own_tel+"</tr></td><tr><td><b>[经度]</b>："+vehicle.px+"</tr></td><tr><td><b>[纬度]</b>："+vehicle.py+"</tr></td><tr><td><b>[所在位置]</b>："+vehicle.address;
	var info = [];
	info.push(txt);
	               
	var inforWindowone = new AMap.InfoWindow({                 
	  offset:new AMap.Pixel(5,10),                 
	  content:info.join("</tr></td></table>")                 
	});           
	  inforWindowone.open(mapObjmm,marker.getPosition());                 
	  AMap.event.addListener(marker,"click",function(e){                 
		  inforWindowone.open(mapObjmm,marker.getPosition());                 
		});
	  
}

(function(){
	var tbody = document.querySelector('#dw_table1').tBodies[0];
	var th = document.querySelector('#dw_table1').tHead.rows[0].cells;
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
