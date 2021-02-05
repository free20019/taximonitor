initializemany();
findvhic();
findnum();
//tbtc();
$('#monitortabsmany').tabs();
// PAGE RELATED SCRIPTS
/*
 *  加载地图
 */
var mapmonitor;
var marker="";
function initializemany() {
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
	mapmonitor = new AMap.Map("many-data-div", {
		center : position,
		level : 15,
		resizeEnable : true
	});//创建地图实例
	mapmonitor.plugin( [ "AMap.ToolBar", "AMap.OverView", "AMap.Scale" ],
			function() {
				//加载工具条
			tool = new AMap.ToolBar( {
				direction : false,//隐藏方向导航
				ruler : false,//隐藏视野级别控制尺
				autoPosition : false
			//禁止自动定位
					});
			mapmonitor.addControl(tool);
			//加载鹰眼
			view = new AMap.OverView();
			mapmonitor.addControl(view);
			//加载比例尺
			scale = new AMap.Scale();
			mapmonitor.addControl(scale);
			mapmonitor.plugin( [ "AMap.MapType" ], function() {
				var type = new AMap.MapType( {
					defaultType : 0
				});//初始状态使用2D地图
					mapmonitor.addControl(type);
				});
		});
}
//复选框全选
function qxqbx(){
	if($("#quanxuan").prop("checked")){
		$("[name='vehicheckbox']").prop("checked",true);
		findvhicinfo();
	}else{
		$("[name='vehicheckbox']").prop("checked",false);
		findvhicinfo();
	}
}
//点击按钮缩进页面
function switchSysBar1many(){
	if ($("#switchPoint1many").val()=="3"){
		$("#switchPoint1many").val("4");
		$("#monitortabsmany").css('display','none'); 
		 $("#mapdivmany").height("100%"); 
		$("#utodmany").attr("class", "fa fa-chevron-up");
	}else{
		$("#switchPoint1many").val("3");
		$("#monitortabsmany").css('display','block');
		 $("#mapdivmany").height("65%"); 
		$("#utodmany").attr("class", "fa fa-chevron-down");
	}
}

function switchSysBarmany(){
	if ($("#switchPointmany").val()=="3"){
		$("#switchPointmany").val("4");
		$("#monirightmany").css('display','none'); 
		$("#lefttdmany").width("98%"); 
		$("#ltormany").attr("class", "fa fa-chevron-left");
	}else{
		$("#switchPointmany").val("3");
		$("#monirightmany").css('display','block');
		$("#lefttdmany").width("74%"); 
		$("#ltormany").attr("class", "fa fa-chevron-right");
	}
}



//查询所有车辆将车辆放入地图
var jk;
function findvhic(){
	if(jk!=null&&ssjk==1){
		clearTimeout(jk);
		return;
		}
	$.ajax({
		 type: "POST",
	        url:"findvhic1.action",
	        data:{
				vehi_no : $("#bzdvhic").val()
	        },
	       dataType: 'json',
			timeout : 180000,
			success:function(json){
	        	mapmonitor.clearMap(null);
	        	var cust=json.list;
	        	var tab="";
	        	markers=[];
	        	for(var i=0;i<cust.length;i++){
	        		addmks(cust[i]);
	        		if(i==cust.length-1){
	        			mapmonitor.clearMap();
	        			addCluster(1);
	        		}
	        		if(cust[i].status=="0"){
						if(cust[i].gspstatus=="1"){
							tab +="<tr ><td nowrap='nowrap'><input type='checkbox'  name='vehicheckbox' value='"+cust[i].vehi_no+"' id='"+cust[i].vehi_no+"' onclick='findvhicinfo()'>&nbsp;&nbsp;&nbsp;<img src='img/car/3.png'>"+cust[i].vehi_no+"</td></tr>";
						}else{
							tab +="<tr ><td nowrap='nowrap'><input type='checkbox'  name='vehicheckbox' value='"+cust[i].vehi_no+"' id='"+cust[i].vehi_no+"' onclick='findvhicinfo()'>&nbsp;&nbsp;&nbsp;<img src='img/car/2.png'>"+cust[i].vehi_no+"</td></tr>";
						}
					}else{
						tab +="<tr ><td nowrap='nowrap'><input type='checkbox'  name='vehicheckbox' value='"+cust[i].vehi_no+"' id='"+cust[i].vehi_no+"' onclick='findvhicinfo()'>&nbsp;&nbsp;&nbsp;<img src='img/car/1.png'>"+cust[i].vehi_no+"</td></tr>";
					}
	        	}
	        	$("#vehi_data").html(tab);
	        	if(ssjk==0){
	    			jk =setTimeout("findvhic()",30000);
	    		}else if(ssjk==1){
	    		//	alert("实时监控刷新暂停");
	    		}
		},
		error:function(){
			
		}		
	});
}
//var wdz;
function findvhic1(){
	$.ajax({
		 type: "POST",
	        url:"findvhic1.action",
	        data:{
				vehi_no : $("#bzdvhic").val()
	        },
	       dataType: 'json',
			timeout : 180000,
		success:function(json){
	        	var cust=json.list;
	        	var tab="";
	        	for(var i=0;i<cust.length;i++){
	        		if(cust[i].status=="0"){
						if(cust[i].gspstatus=="1"){
							tab +="<tr ><td nowrap='nowrap'><input type='checkbox'  name='vehicheckbox' value='"+cust[i].vehi_no+"' id='"+cust[i].vehi_no+"' onclick='findvhicinfo()'>&nbsp;&nbsp;&nbsp;<img src='img/car/3.png'>"+cust[i].vehi_no+"</td></tr>";
						}else{
							tab +="<tr ><td nowrap='nowrap'><input type='checkbox'  name='vehicheckbox' value='"+cust[i].vehi_no+"' id='"+cust[i].vehi_no+"' onclick='findvhicinfo()'>&nbsp;&nbsp;&nbsp;<img src='img/car/2.png'>"+cust[i].vehi_no+"</td></tr>";
						}
					}else{
						tab +="<tr ><td nowrap='nowrap'><input type='checkbox'  name='vehicheckbox' value='"+cust[i].vehi_no+"' id='"+cust[i].vehi_no+"' onclick='findvhicinfo()'>&nbsp;&nbsp;&nbsp;<img src='img/car/1.png'>"+cust[i].vehi_no+"</td></tr>";
					}
	        	}
	        	$("#vehi_data").html(tab);
		},
		error:function(){
			
		}		
	});
}
//将查询出的车辆放入地图
var markers= [];
function addmks(obj){
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
//	marker1.setMap(mapmonitor);  //在地图上添加点
	
	markers.push(marker1);
	var txt = "<table><tr><td><b style='color:#3399FF'>"+obj.vehi_no+"</b></td><td></td></tr><tr><td><b>[所属公司]</b>："+obj.comp_name+"</tr></td><tr><td><b>[车辆类型]</b>："+obj.cartype+"</tr></td><tr><td><b>[车辆颜色]</b>："+obj.color+"</tr></td><tr><td><b>[SIM卡]</b>："+obj.sim_num+"</tr></td><tr><td><b>[车辆所属人]</b>："+obj.own_name
	  +"</tr></td><tr><td><b>[联系电话]</b>："+obj.own_tel+"</tr></td><tr><td><b>[经度]</b>："+obj.px+"</tr></td><tr><td><b>[纬度]</b>："+obj.py+"</tr></td><tr><td><b>[所在位置]</b>："+obj.address+"<tr><td><a href='javascript:void(0);' onclick='openLSGJ(\""+obj.vehi_no+"\");'>历史轨迹</a>";
	if($("#dlqxcar").val()!='hzsf'){
		txt+="&nbsp;&nbsp;&nbsp;&nbsp;<a href='javascript:void(0);' onclick='openYYGJ(\""+obj.vehi_no+"\");'>营运数据</a>";
	}
	txt+="&nbsp;&nbsp;&nbsp;&nbsp;<a href='javascript:void(0);' onclick='setmessage_send(\""+obj.vehi_no+"\");'>消息发送</a>";
	var info = [];
	info.push(txt);
	var inforWindow = new AMap.InfoWindow({
	  offset:new AMap.Pixel(0,0),
	  content:info.join("</tr></td></table>")
	});
	  AMap.event.addListener(marker1,"click",function(e){                 
//		  inforWindow.open(mapmonitor,marker1.getPosition());  
		  vhicmarker(obj.vehi_no);
		  //addMarker(obj);
		});
	  AMap.event.addListener(mapmonitor,"click",function(e){
		  inforWindow.close();              
		});
}
var cluster;
 
function openLSGJ(vhic){
	addTab(2,'历史轨迹','jsp/cartrack.jsp');
	setTimeout(function (){$('#hiscar').val(vhic);querymanyhis();}, 1000);
}
function openYYGJ(vhic){
	addTab(100,'营运记录查询','jsp/find/bus.jsp');
	setTimeout(function (){findbus(vhic)}, 1000);
}

function addCluster(tag){
	if(cluster) {
		cluster.setMap(null);
	}
	mapmonitor.clearMap();
	if(tag==1) {
		var sts=[{url:"img/car/12.png", size:new AMap.Size(32,32),offset:new AMap.Pixel(-16,-30)},
			{url:"img/car/11.png", size:new AMap.Size(32,32),offset:new AMap.Pixel(-16,-30)},
			{url:"img/car/13.jpg", size:new AMap.Size(48,48),offset:new AMap.Pixel(-24,-45),textColor:'#CC0066'}];
		mapmonitor.plugin(["AMap.MarkerClusterer"],function(){
			cluster = new AMap.MarkerClusterer(mapmonitor,markers,{minClusterSize:10,styles:sts});
		});
	}
	else {
		mapmonitor.plugin(["AMap.MarkerClusterer"],function(){
			cluster = new AMap.MarkerClusterer(mapmonitor,markers);
		});
	}
}
//将车辆树选中的车辆显示到车辆信息中
var ss="";
var t;
function findvhicinfo(){
	clearTimeout(t);
	var s="";
	$('input[name="vehicheckbox"]:checked').each(function(){
		s+=$(this).val()+',';
	});
	if(s==""){
		s="2,";
	}

	$.ajax({
		 type: "POST",
	        url:"findvhic.action",
	        data:{
				vehi_nolist : s
	        },
	       dataType: 'json',
			timeout : 180000,
		success:function(json){
	        	var cust=json.list;
	        	var tab="";
	        	var tab2="";
	        	for(var i=0;i<cust.length;i++){
						tab +="<tr ondblclick='vhicmarker(this.id)' id='"+cust[i].vehi_no+"'><td nowrap='nowrap'>"+cust[i].vehi_no+"</td><td nowrap='nowrap'>"+cust[i].color+"</td><td nowrap='nowrap'>"+cust[i].sim_num+"</td><td nowrap='nowrap'>"+cust[i].speed+"</td>";
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
						if(cust[i].ba_id!='&nbsp'){
							var num=parseInt(cust[i].ba_id,16).toString(2).split('').reverse().join('');
//							alert(cust[i].ba_id+":"+num+":"+num.substr(16,1)+":"+num.substr(1,1)+":"+num.substr(16,1)+":"+num.substr(17,1));
							if(num.substr(0,1)=="1"||num.substr(16,1)=="1"||num.substr(17,1)=="1"){
								var alarm="";
								if(num.substr(0,1)=="1") alarm="紧急报警";
								if(num.substr(16,1)=="1") alarm="超速报警";
								if(num.substr(17,1)=="1") alarm="连续驾驶超时";
								tab2 +="<tr ondblclick='vhicmarker(this.id)' id='"+cust[i].vehi_no+"'><td nowrap='nowrap'>"+cust[i].vehi_no+"</td><td nowrap='nowrap'>"+alarm+"</td><td nowrap='nowrap'>"+cust[i].stime+"</td><td nowrap='nowrap'>"+cust[i].sim_num+"</td>";
								tab2+="<td nowrap='nowrap'>"+cust[i].mt_name+"</td><td nowrap='nowrap'>"+cust[i].cartype+"</td></tr>";
							}
						}
	        	}
	        	if(ss!=s){	        		
	        		$("#vhicnow").html(tab);
	        		ss=s;
	        	}
	        	$("#vhicnow2").html(tab2);
	        	if(s!='2,'&&ssjk==0){
	    			console.log("5s更新一次")
	    			t=setTimeout("findvhicinfo()",5000);

	    			
	    			
	    		}else {
	    		}
		},
		error:function(){
			
		}		
	});
}

//双击车辆信息，将该车辆添加到地图上显示
function vhicmarker(obj){
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
	        		addMarker(cust[i]);
	        	}
		},
		error:function(){
			
		}		
	});
}
//将查询到车号打点到地图
function addMarker(vehicle){
	//每次加载将上次地图添加的点删除
	if(marker!=""){
		marker.setMap(null);
	}
	var markerContent = document.createElement("div");
    markerContent.className = "txtstyle";
    //点标记中的图标
    var markerImg= document.createElement("img");
	markerImg.className="markerlnglat";
	console.log(vehicle.status+'  '+vehicle.gspstatus)
	if(vehicle.status=="0"){
		if(vehicle.gspstatus=='1'){
			markerImg.src="img/car/h.png";   
		}else{
			markerImg.src="img/car/c.png"; 
		}
	}else{
		markerImg.src="img/car/c.png";   
	}
	markerContent.appendChild(markerImg);
	marker = new AMap.Marker({
	    map:mapmonitor,
	    position:new AMap.LngLat(vehicle.px,vehicle.py),     
	    offset:new AMap.Pixel(-0,-0), //相对于基点的偏移位置
	    draggable:false,  //是否可拖动
	    content:markerContent   //自定义点标记覆盖物内容
	});
	mapmonitor.setCenter(new AMap.LngLat(vehicle.px,vehicle.py));
	var txt = "<table><tr><td><b style='color:#3399FF'>"+vehicle.vehi_no+"</b></td></tr><tr><td><b>[所属公司]</b>："+vehicle.comp_name+"</tr></td><tr><td><b>[车辆类型]</b>："+vehicle.cartype+"</tr></td><tr><td><b>[车辆颜色]</b>："+vehicle.color+"</tr></td><tr><td><b>[SIM卡]</b>："+vehicle.sim_num+"</tr></td><tr><td><b>[车辆所属人]</b>："+vehicle.own_name
	  +"</tr></td><tr><td><b>[联系电话]</b>："+vehicle.own_tel+"</tr></td><tr><td><b>[经度]</b>："+vehicle.px+"</tr></td><tr><td><b>[纬度]</b>："+vehicle.py+"</tr></td><tr><td><b>[所在位置]</b>："+vehicle.address+"<tr><td><a href='javascript:void(0);' onclick='openLSGJ(\""+vehicle.vehi_no+"\");'>历史轨迹</a>";
	if($("#dlqxcar").val()!='hzsf'){
		txt+="&nbsp;&nbsp;&nbsp;&nbsp;<a href='javascript:void(0);' onclick='openYYGJ(\""+obj.vehi_no+"\");'>营运数据</a>";
	}
	txt+="&nbsp;&nbsp;&nbsp;&nbsp;<a href='javascript:void(0);' onclick='setmessage_send(\""+obj.vehi_no+"\");'>消息发送</a>";
	var info = [];
	info.push(txt);
	               
	var inforWindowone = new AMap.InfoWindow({                 
	  offset:new AMap.Pixel(5,10),                 
	  content:info.join("</tr></td></table>")                 
	});           
	  inforWindowone.open(mapmonitor,marker.getPosition());                 
	  AMap.event.addListener(marker,"click",function(e){                 
		  inforWindowone.open(mapmonitor,marker.getPosition());                 
		});
	  
}


function findnum(){
	if(jk!=null&&ssjk==1){
		clearTimeout(jk);
		return;
		}
	$.ajax({
		 type: "POST",
	        url:"findnum.action",
	        data:{
	        },
	       dataType: 'json',
			timeout : 180000,
		success:function(json){
	        	var cust=json.num;
	        	$("#total").html(cust[0]);
	        	$("#onnum").html(cust[1]);
	        	$("#offnum").html(cust[2]);
	        	$("#hnum").html(cust[3]);
	        	$("#nnum").html(cust[4]);
	        	if(ssjk==0){
	    			jk =setTimeout("findnum()",30000);
	    		}else if(ssjk==1){
//	    			alert("实时监控刷新暂停");
	    		}
	        	
		},
		error:function(){
			
		}		
	});
}

//消息发送弹出窗
	function setmessage_send(){
		$("#xxfs_xxk").val('');
		$('#message_send').dialog('open');
	}
	$('#message_send').dialog({
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

			html : "<i class='fa fa-plus'></i>&nbsp; 发送",
			"class" : "btn btn-danger",
			click : function() {
				send_message();
				$(this).dialog("close");
			}
		}]
});
	function send_message(obj){
		$.ajax({
			type: "POST",
	        url:"send_message.action",
	        data:{
	        	"vhic":obj,
	        	"info":$("#xxfs_xxk").val()
	        },
	        dataType: 'json',
			timeout : 180000,
			success:function(json){
				alert('发送成功');
			},
			error:function(){
				
			}		
		});
	};
(function(){
	var tbody = document.querySelector('#dw_table').tBodies[0];
	var th = document.querySelector('#dw_table').tHead.rows[0].cells;
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
(function(){
	var tbody = document.querySelector('#dw_table2').tBodies[0];
	var th = document.querySelector('#dw_table2').tHead.rows[0].cells;
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