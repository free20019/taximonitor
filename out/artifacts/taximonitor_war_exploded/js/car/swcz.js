	var qd_jwd="",zj_jwd="",zd_jwd="";
	var mydate = new Date();
	var smydate = new Date(mydate.getTime() - 1000 * 60 * 60*1);
	$("#qd_stime").val(setformatnewlc(smydate));
	$("#qd_etime").val(setformatnewlc(mydate));
	$("#zj_stime").val(setformatnewlc(smydate));
	$("#zj_etime").val(setformatnewlc(mydate));
	$("#zd_stime").val(setformatnewlc(smydate));
	$("#zd_etime").val(setformatnewlc(mydate));
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
	
	 var carinfo = [
	                {'id': 1, 'plateNumber': '浙A12345','position': [{'x': '120.135637', 'y': '30.283086', 'datetime': '2016-2-3 13:20:12', 'speed': '60'},{'x': '120.135594', 'y': '30.283225', 'datetime': '2016-2-3 13:20:13', 'speed': '60'},{'x': '120.135616', 'y': '30.283346', 'datetime': '2016-2-3 13:20:15', 'speed': '60'},{'x': '120.135552', 'y': '30.284179', 'datetime': '2016-2-3 13:20:19', 'speed': '60'},{'x': '120.135476', 'y': '30.284939', 'datetime': '2016-2-3 13:20:23', 'speed': '60'},{'x': '120.135444', 'y': '30.285662', 'datetime': '2016-2-3 13:20:26', 'speed': '60'},{'x': '120.135369', 'y': '30.286357', 'datetime': '2016-2-3 13:20:29', 'speed': '60'},{'x': '120.135273', 'y': '30.287867', 'datetime': '2016-2-3 13:20:36', 'speed': '60'},{'x': '120.135198', 'y': '30.288367', 'datetime': '2016-2-3 13:20:40', 'speed': '60'},{'x': '120.136067', 'y': '30.288385', 'datetime': '2016-2-3 13:20:45', 'speed': '60'},{'x': '120.138684', 'y': '30.288506', 'datetime': '2016-2-3 13:20:49', 'speed': '60'},{'x': '120.140321', 'y': '30.288603', 'datetime': '2016-2-3 13:20:58', 'speed': '60'}]},
	                {'id': 2, 'plateNumber': '浙A65842','position': [{'x': '120.169118', 'y': '30.281702', 'datetime': '2016-2-6 17:39:52', 'speed': '60'},{'x': '120.169230', 'y': '30.281382', 'datetime': '2016-2-6 17:40:03', 'speed': '60'},{'x': '120.169434', 'y': '30.281382', 'datetime': '2016-2-6 17:40:25', 'speed': '60'},{'x': '120.169504', 'y': '30.280428', 'datetime': '2016-2-6 17:41:09', 'speed': '60'},{'x': '120.169595', 'y': '30.280025', 'datetime': '2016-2-6 17:41:25', 'speed': '60'},{'x': '120.169692', 'y': '30.279557', 'datetime': '2016-2-6 17:41:56', 'speed': '60'},{'x': '120.170072', 'y': '30.277695', 'datetime': '2016-2-6 17:41:59', 'speed': '60'}]},
	                {'id': 3, 'plateNumber': '浙A65843','position': [{'x': '120.160679', 'y': '30.270432', 'datetime': '2016-2-3 13:20:12', 'speed': '60'},{'x': '120.159574', 'y': '30.270538', 'datetime': '2016-2-3 13:20:13', 'speed': '60'},{'x': '120.158550', 'y': '30.270594', 'datetime': '2016-2-3 13:20:15', 'speed': '60'},{'x': '120.158217', 'y': '30.271025', 'datetime': '2016-2-3 13:20:19', 'speed': '60'},{'x': '120.157928', 'y': '30.271474', 'datetime': '2016-2-3 13:20:23', 'speed': '60'},{'x': '120.157547', 'y': '30.272206', 'datetime': '2016-2-3 13:20:26', 'speed': '60'},{'x': '120.157257', 'y': '30.272716', 'datetime': '2016-2-3 13:20:29', 'speed': '60'}]},
	                {'id': 4, 'plateNumber': '浙A65844','position': [{'x': '120.171861', 'y': '30.275722', 'datetime': '2016-2-3 13:20:12', 'speed': '60'},{'x': '120.171770', 'y': '30.275866', 'datetime': '2016-2-3 13:20:13', 'speed': '60'},{'x': '120.171427', 'y': '30.275861', 'datetime': '2016-2-3 13:20:15', 'speed': '60'},{'x': '120.171159', 'y': '30.275912', 'datetime': '2016-2-3 13:20:19', 'speed': '60'},{'x': '120.170842', 'y': '30.275926', 'datetime': '2016-2-3 13:20:23', 'speed': '60'},{'x': '120.170617', 'y': '30.276116', 'datetime': '2016-2-3 13:20:26', 'speed': '60'},{'x': '120.170553', 'y': '30.276417', 'datetime': '2016-2-3 13:20:29', 'speed': '60'}]},
	                {'id': 5, 'plateNumber': '浙A65845','position': [{'x': '120.171273', 'y': '30.264710', 'datetime': '2016-2-3 13:20:12', 'speed': '60'}, {'x': '120.171391', 'y': '30.263514', 'datetime': '2016-2-3 13:20:13', 'speed': '60'}, {'x': '120.171512', 'y': '30.260614', 'datetime': '2016-2-3 13:20:15', 'speed': '60'}, {'x': '120.171639', 'y': '30.257074', 'datetime': '2016-2-3 13:20:19', 'speed': '60'}, {'x': '120.171767', 'y': '30.253461', 'datetime': '2016-2-3 13:20:23', 'speed': '60'}, {'x': '120.172004', 'y': '30.250791', 'datetime': '2016-2-3 13:20:26', 'speed': '60'}, {'x': '120.172325', 'y': '30.243617', 'datetime': '2016-2-3 13:20:29', 'speed': '60'}]},
	                {'id': 6, 'plateNumber': '浙A65846','position': [{'x': '120.169118', 'y': '30.281702', 'datetime': '2016-2-3 13:20:12', 'speed': '60'}, {'x': '120.169230', 'y': '30.281382', 'datetime': '2016-2-3 13:20:13', 'speed': '60'}, {'x': '120.169434', 'y': '30.281382', 'datetime': '2016-2-3 13:20:15', 'speed': '60'}, {'x': '120.169504', 'y': '30.280428', 'datetime': '2016-2-3 13:20:19', 'speed': '60'}, {'x': '120.169595', 'y': '30.280025', 'datetime': '2016-2-3 13:20:23', 'speed': '60'}, {'x': '120.169692', 'y': '30.279557', 'datetime': '2016-2-3 13:20:26', 'speed': '60'}, {'x': '120.170072', 'y': '30.277695', 'datetime': '2016-2-3 13:20:29', 'speed': '60'}]}
	            ];
	            var fxudmouseTool=null;
	            var polygonfk = null;
	            var polygonfks = [null,null,null];
	            var udpolygonOption = {
	                strokeColor:"",
	                strokeOpacity:0,
	                strokeWeight:1
	            };
	            var isStart = false, isEnd = false, isMiddle = false;
	            var map = null, mouseTool = null;
	            $(function() {
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
	                map = new AMap.Map('AMap', {
	                	center : position,
	                    resizeEnable:true,
	                    zoom:15
	                });
	                $('.clearMap').hide().click(function() {
	                    if(polygonfk!=null) {map.clearMap();}
	                    qd_jwd="";zj_jwd="";zd_jwd="";
	                    isStart = false; isEnd = false; isMiddle = false; polygonfks = [null,null,null];
	                    $(this).hide();
	                });
	                $('.dwqdfw').click(function() {
	                    if (!isStart) isStart = arawAreaFun(map, isStart, 1);
	                });
	                $('.dwzjdfw').click(function() {
	                    if (!isMiddle) isMiddle = arawAreaFun(map, isMiddle, 3);
	                });
	                $('.dwzdfw').click(function(){
	                    if (!isEnd) isEnd = arawAreaFun(map, isEnd, 2);
	                });
	                $('.tabFanweiTitle li').click(function() {
	                    $('.tabFanweiTitle li').removeAttr('data-checked');
	                    $(this).attr('data-checked', '');
	                    $('[id$=Panel]').css({'display':'none'});
	                    let dataId = $(this).attr('data-id');
	                    $('#' + dataId).css({'display':'block'});
	                });
//	                $('.btn_query').click(function() {
//	                    console.info(polygonfks);
//	                    $('#qdfwPanel table tbody').html('');
//	                    $('#zdfwPanel table tbody').html('');
//	                    $('#clxxPanel table tbody').html('');
//	                    if (polygonfks[0] != null || polygonfks[1] != null) tableDisposeInfo();
//	                    //polygon.contains([116.368904, 39.923423]);
//	                });
	            });
	            function arawAreaFun(mapObj, isArea, num) {
	                console.info(num);
	                if(isArea) return false;
	                mapObj.plugin(["AMap.MouseTool"],function(){
	                    fxudmouseTool = new AMap.MouseTool(mapObj);
	                    fxudmouseTool.polygon(udpolygonOption);   //使用鼠标工具绘制多边形
	                    AMap.event.addListener(fxudmouseTool, "draw", function(e){
	                        var drawObj = e.obj;
	                        var pointsCount = e.obj.getPath().length;
	                        var a =  e.obj.getPath();
	                        var zbs = "";
	                        var polygonArr1 = [];//多边形覆盖物节点坐标数组
	                        for(var i=0;i<pointsCount;i++){
	                            polygonArr1.push([a[i].lng,a[i].lat]);
	                        }
	                        polygonfk = fanwei(polygonArr1, num);
	                        polygonfk.setMap(mapObj);
	                        if (num == 1) polygonfks[0] = polygonfk;
	                        else polygonfks[1] = polygonfk;
	                        fxudmouseTool.close(true);
	                        fxudmouseTool=null;
	                        $('.clearMap').show();
	                    });
	                });
	                return true
	            }
	            function fanwei(polygonArr1, num) {
	                if (1 == num) {
	                	qd_jwd="";
	                	for(var i =0;i<polygonArr1.length;i++){
	                		qd_jwd+=polygonArr1[i]+";";
	                	}
	                    return new AMap.Polygon({
	                        path: polygonArr1,//设置多边形边界路径
	                        strokeColor: "#1791fc", //线颜色
	                        strokeOpacity: 1, //线透明度
	                        strokeWeight: 2,    //线宽
	                        fillColor: "#1791fc", //填充色
	                        fillOpacity:.5//填充透明度
	                    });
	                }
	                if (2 == num) {
	                	zd_jwd="";
	                	for(var i =0;i<polygonArr1.length;i++){
	                		zd_jwd+=polygonArr1[i]+";";
	                	}
	                    return new AMap.Polygon({
	                        path: polygonArr1,//设置多边形边界路径
	                        strokeColor: "#ff91fc", //线颜色
	                        strokeOpacity: 1, //线透明度
	                        strokeWeight: 2,    //线宽
	                        fillColor: "#1791fc", //填充色
	                        fillOpacity:.5//填充透明度
	                    });
	                }
	                zj_jwd="";
	                for(var i =0;i<polygonArr1.length;i++){
                		zj_jwd+=polygonArr1[i]+";";
                	}
	                return new AMap.Polygon({
	                    path: polygonArr1,//设置多边形边界路径
	                    strokeColor: "#ffff00", //线颜色
	                    strokeOpacity: 1, //线透明度
	                    strokeWeight: 2,    //线宽
	                    fillColor: "#1791fc", //填充色
	                    fillOpacity:.5//填充透明度
	                });
	            }
//	            function tableDisposeInfo() {
//	                var j = 0;
//	                for (var i = 0; i < carinfo.length; i++) {
//	                    var position = carinfo[i].position;
//	                    if (polygonfks[0] != null) {
//	                        for (j = 0; j < position.length; j++) {
//	                            let flag = polygonfks[0].contains([position[j].x, position[j].y]);
//	                            if (flag) {
//	                                let html = '<tr><td>' + carinfo[i].plateNumber + '</td><td>' + position[j].x + '</td><td>' + position[j].y + '</td><td>' + position[j].speed + '</td></tr>';
//	                                $('#qdfwPanel table tbody').append(html);
//	                                $('#clxxPanel table tbody').append(html);
//	                            }
//	                        }
//	                    }
//	                    if (polygonfks[1] != null) {
//	                        for (j = 0; j < position.length; j++) {
//	                            let flag = polygonfks[1].contains([position[j].x, position[j].y]);
//	                            if (flag) {
//	                                let html = '<tr><td>' + carinfo[i].plateNumber + '</td><td>' + position[j].x + '</td><td>' + position[j].y + '</td><td>' + position[j].speed + '</td></tr>';
//	                                $('#zdfwPanel table tbody').append(html);
//	                                $('#clxxPanel table tbody').append(html);
//	                            }
//	                        }
//	                    }
//	                }
//	            }
	            
	            
	            function findswcz(){
	            	layer.load(2);
	            	$("#swcz_cx").attr('disabled',"true");
	            	$.ajax({
	            		 type: "POST",
	            	        url:"findswcz.action",
	            	        data:{
	            				"qd_stime" : $("#qd_stime").val(),
	            				"qd_etime" : $("#qd_etime").val(),
	            				"zj_stime" : $("#zj_stime").val(),
	            				"zj_etime" : $("#zj_etime").val(),
	            				"zd_stime" : $("#zd_stime").val(),
	            				"zd_etime" : $("#zd_etime").val(),
	            				"qd_jwd" : qd_jwd,
	            				"zd_jwd" : zd_jwd,
	            				"zj_jwd" : zj_jwd
	            	        },
	            	        dataType: 'json',
	            			timeout : 3600000,
	            		success:function(json){
	            			console.log(json)
	            			$('#qdfwPanel table tbody').empty();
                            $('#clxxPanel table tbody').empty();
                            $('#zdfwPanel table tbody').empty();
                            console.log(json.list[0].length+"   "+json.list[1].length+"   "+json.list[2].length)
                            if(json.list[0].length>0||json.list[1].length>0||json.list[2].length>0){
                            	console.log(json)
    	            			var qd = json.list[0];
    	            			var zd = json.list[1];
    	            			var zj = json.list[2];
    	            			var html = "";
    	            			var html1="";
    	            			var html2="";
    	            			for(var i =0;i<qd.length;i++){
    	            				html+="<tr><td style='width:20%'>"+qd[i].vehi_no+"</td><td style='width:40%'>"
    	            				+qd[i].px.substr(0,qd[i].px.length-1)+"</td><td style='width:40%'>"+qd[i].stime.substr(0,qd[i].stime.length-1)+"</td></tr>";
    	            			}
    	            			for(var i =0;i<zd.length;i++){
    	            				html1+="<tr><td style='width:20%'>"+zd[i].vehi_no+"</td><td style='width:40%'>"
    	            				+zd[i].px.substr(0,zd[i].px.length-1)+"</td><td style='width:40%'>"+zd[i].stime.substr(0,zd[i].stime.length-1)+"</td></tr>";
    	            			}
    	            			for(var i =0;i<zj.length;i++){
    	            				html2+="<tr><td style='width:20%'>"+zj[i].vehi_no+"</td><td style='width:40%'>"
    	            				+zj[i].px.substr(0,zj[i].px.length-1)+"</td><td style='width:40%'>"+zj[i].stime.substr(0,zj[i].stime.length-1)+"</td></tr>";
    	            			}
    	            			$('#qdfwPanel table tbody').append(html);
                                $('#clxxPanel table tbody').append(html2);
                                $('#zdfwPanel table tbody').append(html1);
                                $('#swcz_cx').removeAttr("disabled");
                                layer.closeAll('loading');
                            }else{
                            	$('#swcz_cx').removeAttr("disabled");
                            	layer.closeAll('loading');
                            	alert("该时间内起点与终点区域内没有符合条件的车辆");
                            }
	            		},
	            		error:function(){
	            			layer.closeAll('loading');
	            		}		
	            	});
	            }
function findswczexcle(){
	if(window.confirm("确定按上述条件把数据集导成Excel？")){
		layer.load(2);
		$.ajax({
		url : 'findswczexcle.action',
		type : 'post',
		data:{
			"qd_stime" : $("#qd_stime").val(),
			"qd_etime" : $("#qd_etime").val(),
			"zj_stime" : $("#zj_stime").val(),
			"zj_etime" : $("#zj_etime").val(),
			"zd_stime" : $("#zd_stime").val(),
			"zd_etime" : $("#zd_etime").val(),
			"qd_jwd" : qd_jwd,
			"zd_jwd" : zd_jwd,
			"zj_jwd" : zj_jwd
		},
		dataType: 'json',
		timeout : 180000,
		success:function(json){
			layer.closeAll('loading');
			window.location.href=json.action;
			$.unblockUI();
		},
		error:function(){
			layer.closeAll('loading');
		}
		});
	}
}