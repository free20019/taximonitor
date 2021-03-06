pageSetUp();
initializehistory();
$('#downdiv0').tabs();
function switchSysBar0(){
	if ($("#switchPoint0").val()=="3"){
		$("#switchPoint0").val("4");
		$("#downdiv0").css('display','none'); 
		 $("#updiv0").height("100%"); 
		$("#utod0").attr("class", "fa fa-chevron-up");
	}
	else{
		$("#switchPoint0").val("3");
		$("#downdiv0").css('display','block');
		 $("#updiv0").height("60%"); 
		$("#utod0").attr("class", "fa fa-chevron-down");
	}
}

var mapObjhistory;
function initializehistory() {
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
	mapObjhistory=new AMap.Map("data-div-history",{center:position,level:14,resizeEnable:true});//创建地图实例
	mapObjhistory.plugin(["AMap.ToolBar","AMap.OverView","AMap.Scale"],function(){
		  //加载工具条
		  tool=new AMap.ToolBar({
		    direction:false,//隐藏方向导航
		    ruler:false,//隐藏视野级别控制尺
		    autoPosition:false//禁止自动定位
		  });
		  mapObjhistory.addControl(tool);
		  //加载鹰眼
		  view=new AMap.OverView();
		  mapObjhistory.addControl(view);
		  //加载比例尺
		  scale=new AMap.Scale();
		  mapObjhistory.addControl(scale);

		  mapObjhistory.plugin(["AMap.MapType"], function() {
				var type = new AMap.MapType({defaultType:0});//初始状态使用2D地图
				mapObjhistory.addControl(type);
			});
		});

}

$(document).ready(function(){
	var mydate = new Date();
	var smydate = new Date(mydate.getTime() - 1000 * 60 * 60*2);
	$("#hisstime").val(setformatnewlc(smydate));
	$("#hisetime").val(setformatnewlc(mydate));
//	$("#START_TIME2").val(y+"-01-01");
});
function setformatnewlc(obj){
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
					$("#historycar").html(tab);
				},
				error : function() {

				}
			});
			
		})
		
	//导出excle
	function exportgj(){
	$.blockUI({message:"<div style='padding:5px;'><h3><font color='blue'>数据查询中,请稍候...</font></h3><img src='img/ajax-loader.gif' border='0'/><br/><br/></div>"});
	if(window.confirm("确定按上述条件把数据集导成Excel？")){
		$.ajax({
		url : 'exporthistory.action',
		type : 'post',
		data:{
			"vehi_no" : $("#hiscar").val(),
			"stime" : $("#hisstime").val(),
			"etime" : $("#hisetime").val()
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
		//根据车号查询时间段内的数据
		var vehigps;
		function querymanyhis(vhic,time){
			if($("#hiscar").val().length==0){
				alert("请选择车号");
			}else{
				$.blockUI({message:"<div style='padding:5px;'><h3><font color='blue'>数据查询中,请稍候...</font></h3><img src='img/ajax-loader.gif' border='0'/><br/><br/></div>"});
				var mydate =new Date();
				$.ajax({
					url : 'vhichistory.action',
					type : 'post',
					data:{
						"vehi_no" : $("#hiscar").val(),
						"stime" : $("#hisstime").val(),
						"etime" : $("#hisetime").val()
					},
					dataType: 'json',
					timeout : 180000,
					success:function(json){
						//图标
						var noline = json.list;
						lsgj(noline);
//						setTimeout('querymanyhis( )', f); 
						
						vehigps = json.list;
						mapObjhistory.clearMap();
						if(vehigps.length==0){
							alert("该车辆在该时段 没有GPS数据");
							$("#vehigpsdata").html("");
							$.unblockUI();
						}else{
							var trdata="";
							var lc=0;
							for(var i=0;i<vehigps.length;i++){
								if(i==0){
									addmkshisstart(vehigps[i]);
									trdata +="<tr><td nowrap='nowrap'>"+(i+1)+"</td><td nowrap='nowrap'>"+vehigps[i].stime+"</td><td nowrap='nowrap'>"+vehigps[i].status+"</td><td nowrap='nowrap'>"+vehigps[i].px+"</td><td nowrap='nowrap'>"+vehigps[i].py+"</td><td nowrap='nowrap'>"+dlwz(vehigps[i].distance)+"</td><td nowrap='nowrap'>"+vehigps[i].speed+"</td><td nowrap='nowrap'>0</td><td nowrap='nowrap'>"+vehigps[i].address+"</td></tr>";
								}else{
									addmkshis(vehigps[i]);
									var l1 = new AMap.LngLat(vehigps[i].px,vehigps[i].py);
									var l2 = new AMap.LngLat(vehigps[i-1].px,vehigps[i-1].py);
									lc += l1.distance(l2);
									trdata +="<tr><td nowrap='nowrap'>"+(i+1)+"</td><td nowrap='nowrap'>"+vehigps[i].stime+"</td><td nowrap='nowrap'>"+vehigps[i].status+"</td><td nowrap='nowrap'>"+vehigps[i].px+"</td><td nowrap='nowrap'>"+vehigps[i].py+"</td><td nowrap='nowrap'>"+dlwz(vehigps[i].distance)+"</td><td nowrap='nowrap'>"+vehigps[i].speed+"</td><td nowrap='nowrap'>"+parseFloat((lc/1000).toFixed(2))+"</td><td nowrap='nowrap'>"+vehigps[i].address+"</td></tr>";
								}
								if(i==vehigps.length-1){
									addmkshisend(vehigps[i]);
								}
							}
							completeEventHandler(vehigps);
							$("#zlc").val(parseFloat((lc/1000).toFixed(2))+"km");
							$("#hisvehigpsdata").html(trdata);
						}
						gjstime=null;
						gjetime=null;
						gjcl=null;
						markerMovingControl = new MarkerMovingControl(mapObjhistory, markerhistory, lineArr);
						$.unblockUI();
					},
					error:function(){
						$.unblockUI();
					}		
				});
			}
	}
		function dlwz(obj){
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
		
		//添加起点
		function addmkshisstart(obj){
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
						  +"<br/><b>[行驶方向]</b>："+dlwz(obj.distance)
						  +"<br/><b>[经纬度]</b>："+obj.px+","+obj.py
						  +"<br/><b>[位置描述]</b>："+obj.address;
			var marker1 = new AMap.Marker({
			    map:mapObjhistory,
			    position:new AMap.LngLat(obj.px,obj.py),
			    zIndex:102,
			    offset:new AMap.Pixel(-24,-28), //相对于基点的偏移位置
			    draggable:false,  //是否可拖动
			    content:markerContent   //自定义点标记覆盖物内容
			});
			//$("#img"+obj.messageid).rotate(obj.direction-90);
			marker1.setMap(mapObjhistory);  //在地图上添加点

			//添加文本标记
			var info = [];
			info.push(txt);
			               
			var inforWindow = new AMap.InfoWindow({                 
			  offset:new AMap.Pixel(0,0),                 
			  content:info.join("<br>")                 
			});                 
			  AMap.event.addListener(marker1,"click",function(e){                 
				  inforWindow.open(mapObjhistory,marker1.getPosition());                 
				});
		}
		//添加终点
		function addmkshisend(obj){
			var markerContent = document.createElement("div");
		    markerContent.className = "markerContentStyle";
		    //点标记中的图标
		    var markerImg= document.createElement("img");
			markerImg.className="markerlnglat";
			markerImg.src="img/end.png";   
			markerImg.id="img"+obj.messageid
			markerContent.appendChild(markerImg);
		  var txt = "<b style='color:#3399FF'>"+obj.vehi_no+"(终点)</b><br/><br/><b>[GPS时间]</b>："+obj.stime
		  				  +"<br/><b>[车辆状态]</b>："+obj.status
						  +"<br/><b>[GPS速度]</b>："+obj.speed
						  +"<br/><b>[行驶方向]</b>："+dlwz(obj.direction)
						  +"<br/><b>[经纬度]</b>："+obj.px+","+obj.py
						  +"<br/><b>[位置描述]</b>："+obj.address;
			var marker1 = new AMap.Marker({
			    map:mapObjhistory,
			    position:new AMap.LngLat(obj.px,obj.py),     
			    zIndex:102,  
			    offset:new AMap.Pixel(-24,-28), //相对于基点的偏移位置
			    draggable:false,  //是否可拖动
			    content:markerContent   //自定义点标记覆盖物内容
			});
			//$("#img"+obj.messageid).rotate(obj.direction-90);
			marker1.setMap(mapObjhistory);  //在地图上添加点

			//添加文本标记
			var info = [];                 
			info.push(txt);                 
			               
			var inforWindow = new AMap.InfoWindow({                 
			  offset:new AMap.Pixel(0,0),                 
			  content:info.join("<br>")                 
			});                 
			  AMap.event.addListener(marker1,"click",function(e){                 
				  inforWindow.open(mapObjhistory,marker1.getPosition());                 
				});

			//添加角度		
			//markerlist.push(marker1);
		}
		//添加所有点
		function addmkshis(obj){
			var markerContent = document.createElement("div");
		    markerContent.className = "markerContentStyle";
		    //点标记中的图标
		    var markerImg= document.createElement("img");
			markerImg.className="markerlnglat";
			markerImg.src="img/fx.jpg";   
			markerImg.id="img"+obj.messageid
			markerContent.appendChild(markerImg);
		  var txt = "<b style='color:#3399FF'>"+obj.vehi_no+"</b><br/><br/><b>[GPS时间]</b>："+obj.stime
		  				  +"<br/><b>[车辆状态]</b>："+obj.status
						  +"<br/><b>[GPS速度]</b>："+obj.speed
						  +"<br/><b>[行驶方向]</b>："+dlwz(obj.direction)
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
			//$("#img"+obj.messageid).rotate(obj.direction-90);
			marker1.setMap(mapObjhistory);  //在地图上添加点

			//添加文本标记
			var info = [];                 
			info.push(txt);                 
			               
			var inforWindow = new AMap.InfoWindow({                 
			  offset:new AMap.Pixel(0,0),                 
			  content:info.join("<br>")                 
			});                 
			  AMap.event.addListener(marker1,"click",function(e){                 
				  inforWindow.open(mapObjhistory,marker1.getPosition());                 
				});

			//添加角度		
			//markerlist.push(marker1);
		}
		
		
		var polyline="";
		var markerhm="";
		var lineArr=null;
		var subArr=null;
	var markerhistory;
	//添加轨迹
		function completeEventHandler(vehigps){
			if(vehigps[0].status=="空车"){
				markerhistory = new AMap.Marker({
			        map:mapObjhistory,
			        //draggable:true, //是否可拖动
			        position:new AMap.LngLat(vehigps[0].px,vehigps[0].py),//基点位置
			        icon:"img/car2.png", //marker图标，直接传递地址url
			        zIndex:101,
			        offset:new AMap.Pixel(-26,-14), //相对于基点的位置
			        angle:vehigps[0].direction-90,
			        autoRotation:true
			    });
			}else if(vehigps[0].status=="重车"){
				markerhistory = new AMap.Marker({
			        map:mapObjhistory,
			        //draggable:true, //是否可拖动
			        position:new AMap.LngLat(vehigps[0].px,vehigps[0].py),//基点位置
			        icon:"img/car.png", //marker图标，直接传递地址url
			        zIndex:101,
			        offset:new AMap.Pixel(-26,-14), //相对于基点的位置
			        angle:vehigps[0].direction-90,
			        autoRotation:true
			    });
			}
		    lineArr = new Array();
		    for (var i = 0; i <vehigps.length; i++){
		    	var lngX=vehigps[i].px;
		    	var lngY=vehigps[i].py;
		    	if(i>0){
			    	var longi0 = vehigps[i-1].px;
			    	var lati0 = vehigps[i-1].py;
			    	if(lngX!=longi0||lngY!=lati0){
		        		lineArr.push(new AMap.LngLat(lngX,lngY));
				    }else{
					  //  alert(lngY+"   "+(parseFloat(vehigps[i].lati)+0.00001));
				    	lngY=parseFloat(vehigps[i].py)+0.00001*i;
				    	lineArr.push(new AMap.LngLat(lngX,lngY));
					}
			    }else{
			    	lineArr.push(new AMap.LngLat(lngX,lngY));
				}
		    }
		    //绘制轨迹
		    polyline = new AMap.Polyline({
		        map:mapObjhistory,
		        path:lineArr,
		        strokeColor:"#00A",//线颜色
		        strokeOpacity:1,//线透明度
		        strokeWeight:3,//线宽
		        strokeStyle:"dashed"//线样式
		    });
		    mapObjhistory.setFitView();
		    //alert(lineArr.length+"   "+vehigps.length);
		   // var linea=0;
		    AMap.event.addListener(markerhistory,'moving',function(e) {
				    for(var i=0;i<lineArr.length;i++){
				        var l = lineArr[i];
				        if(markerhistory.getPosition().distance(l)<=2){
					        
				        	$('#vehigpsdata tr').each(function() {
				       	 		$(this).css('color','black');
				    		});
				           $("#his"+vehigps[i].messageid).css('color','red');
				           if(vehigps[i].status=="空车"){
				        	   markerhistory.setIcon("img/car2.png");
						    }else if(vehigps[i].status=="重车"){
				        	   markerhistory.setIcon("img/car.png");
						    }
						   // linea = i+1;
							break;
				        }else{
				        	       
					    }
				    }
				});
		  }
		
		
		
		  //轨迹回放
		var MarkerMovingControl = function(map, marker, path) {
		          this._map = map;
		          this._marker = marker;
		          this._path = path;
		          this._currentIndex = 0;
		          marker.setMap(map);
		          marker.setPosition(path[0]);
		      }
		/**
		 * 移动marker，会从当前位置开始向前移动
		 */
		MarkerMovingControl.prototype.move = function() {
		    if (!this._listenToStepend) {
		        this._listenToStepend = AMap.event.addListener(this, 'stepend', function() {
		            this.step();
		        }, this);
		    }
		    this.step();
		};

		/**
		 * 停止移动marker，由于控件会记录当前位置，所以相当于暂停
		 */
		MarkerMovingControl.prototype.stop = function() {
		    this._marker.stopMove();
		};

		/**
		 * 重新开始，会把marker移动到路径的起点然后开始移动
		 */
		MarkerMovingControl.prototype.restart = function() {
		    this.stop();
		    this._marker.setPosition(this._path[0]);
		    this._currentIndex = 0;
		    this.move();
		};

		/**
		 * 向前移动一步
		 */
		 var spc=500;
		function quick(){
			spc=spc+200;
			 shezspc();
		}
		function slow(){
			if(spc-200<=0){
				alert("已到最小速度");
			}else{
				spc=spc-200;
			}
			shezspc();
		}
		function shezspc(){
			MarkerMovingControl.prototype.step = function(){
			    var nextIndex = this._currentIndex + 1;
			    if (nextIndex < this._path.length) {
			        if (!this._listenToMoveend) {
			            this._listenToMoveend = AMap.event.addListener(this._marker, 'moveend', function(){
			                this._currentIndex++;
			                AMap.event.trigger(this, 'stepend');
			            }, this);
			        }
			        // console.log(nextIndex);
			        this._marker.moveTo(this._path[nextIndex], spc);
			    }
			};
		}
		 
		MarkerMovingControl.prototype.step = function(){
		    var nextIndex = this._currentIndex + 1;
		    if (nextIndex < this._path.length) {
		        if (!this._listenToMoveend) {
		            this._listenToMoveend = AMap.event.addListener(this._marker, 'moveend', function(){
		                this._currentIndex++;
		                AMap.event.trigger(this, 'stepend');
		            }, this);
		        }
		        // console.log(nextIndex);
		        this._marker.moveTo(this._path[nextIndex], 500);
		    }
		};
		var markerMovingControl = null;

		// 编写事件响应函数
		function startAnimation() {
			spc=500;
			shezspc();
		    markerMovingControl.restart();
//			markerhistory.moveAlong(lineArr,500);
		}

		function pauseAnimation() {
		    markerMovingControl.stop();
		}

		function continueAnimation() {
		    markerMovingControl.move();
		}

//历史轨迹图
function lsgj(obj){
	var yingyuntime=[];
	for(var i=0;i<obj.length;i++){
		yingyuntime.push(obj[i].stime);
	}
	var speed=[];
	for(var i=0;i<obj.length;i++){
		speed.push(obj[i].speed);
	}
    require(
    	[
            'echarts',
            'echarts/chart/line', // 
			'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
        ],
    function (ec) {
        // 基于准备好的dom，初始化echarts图表          
		var myChart2 = ec.init(document.getElementById('lsgj')); 
		option2 = {
		grid:{
			left: '3%',
	        right: '4%',
	        bottom: '3%',
	        containLabel: true
		},
	    title : {
	        text: '',
	        subtext: ''
	    },
	    tooltip : {
	        trigger: 'axis'
	    },
	    legend: {
	        data:['车辆速度(km/h)']
	    },
	    toolbox: {
	        show : false,//隐藏右上角工具按钮
	        feature : {
	            mark : {show: true},
	            dataView : {show: true, readOnly: false},
	            magicType: {show: true, type: ['line', 'bar']},
	            restore : {show: true},
	            saveAsImage : {show: true},	           
	        }
	    },
	    calculable : true,
	    xAxis : [
	        {
	            type : 'category',
	            boundaryGap : false,
	            data :yingyuntime,
	        }
	    ],
	    yAxis : [
	        {
	            type : 'value',  
	        }
	    ],
	    series : [
	        {
	            name:'车辆速度(km/h)',
	            type:'line',
	            data:speed,
	        }
	    ]
	};                    
	myChart2.setOption(option2); 
    }
    ); 
}