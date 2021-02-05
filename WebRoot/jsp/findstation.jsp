﻿<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
contentType="text/html; charset=UTF-8" %>
<script src="js/jquery.blockUI.js"></script>
<section id="widget-grid" class="">
<!-- widget grid -->
<div class="row" >
<input type="hidden" id="stationadmin" value="${stationadmin}">
<input type="hidden" id="station_glqx" value="${glqx}"/>
	<!-- row -->
<table border="1" style="width: 102%;height: 100%;">
	<tr style="height: 150px;overflow: auto;">
		<td>
			<div class="jarviswidget jarviswidget-color-darken" id="wid-id-2" data-widget-editbutton="false">
				<header style = "text-align:right;">
					<span class="widget-icon"> <i class="fa fa-table"></i> </span>
					<h2 id="gwgl">岗位管理</h2>
				</header>
				<!-- widget div-->
				<div>
					<div class="jarviswidget-editbox">
					</div>
					<div class="widget-body no-padding">
						<form action="" id="msg-form" class="smart-form" novalidate="novalidate" >
								<a href="javascript:void(0);" id="sck"  class="btn btn-primary" onclick="stationfind();">查   看</a>
								<a href="javascript:void(0);" id="sxz"  class="btn btn-primary" onclick="setstation();">新   增</a>
								<a href="javascript:void(0);" id="sxg"  class="btn btn-primary" onclick="stationedit();">修   改</a>
								<a href="javascript:void(0);" id="ssc"  class="btn btn-primary" onclick="delstation();">删   除</a>
						</form>
						<div class="widget-body-toolbar">
						</div>
						
						<table id="station_table" class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th></th>
									<th>序号</th>
									<th>岗位名</th>
									<th>岗位权限</th>
									<th>管理权限</th>
								</tr>
							</thead>
							<tbody id="stationtbody">
							</tbody>
						</table>

					</div>
				</div>
			</div>
		</td>
	</tr>
</table>
<div id="addstation" title="新增">
	<form>
		<fieldset>
			<input name="authenticity_token" type="hidden">
			<div class="form-group">
				<label>岗位名:</label>
				<input class="form-control" id="addstationname" placeholder="--请输入岗位名--"   type="text">
			</div>
			<input name="authenticity_token" type="hidden">
					<label class="label"><font color="black" size="2px">页面权限:</font></label>
						<table>
							<tr>
								<td width="149"><div class="col col-4">
<!-- 									<label class="checkbox"><input type="checkbox" name="yemian" value="车辆跟踪"><i></i>车辆跟踪</label> -->
									<label class="checkbox"><input type="checkbox" name="yemian" value="历史轨迹"><i></i>历史轨迹</label>
									<label class="checkbox"><input type="checkbox" name="yemian" value="电子围栏"><i></i>电子围栏</label>
									<label class="checkbox"><input type="checkbox" name="yemian" value="区域监控"><i></i>区域监控</label>
									<label class="checkbox"><input type="checkbox" name="yemian" value="车辆组监控"><i></i>车辆组监控</label>
									<label class="checkbox"><input type="checkbox" name="yemian" value="视频监控"><i></i>视频监控</label>
									<label class="checkbox"><input type="checkbox" name="yemian" value="失物查找"><i></i>失物查找</label>
									</div>
								</td>
								<td width="200"><div class="col col-4">
									<label class="checkbox"><input type="checkbox" name="yemian" value="营运记录查询"><i></i>营运记录查询</label>
									<label class="checkbox"><input type="checkbox" name="yemian" value="车辆营运数据统计"><i></i>车辆营运数据统计</label>
									<label class="checkbox"><input type="checkbox" name="yemian" value="在线营运率分析"><i></i>在线营运率分析</label>
									<label class="checkbox"><input type="checkbox" name="yemian" value="里程利用率分析"><i></i>里程利用率分析</label>
									<label class="checkbox"><input type="checkbox" name="yemian" value="单车平均载客里程分析"><i></i>单车平均载客里程分析</label>
									<label class="checkbox"><input type="checkbox" name="yemian" value="上线率分析"><i></i>上线率分析</label>
									
									</div>
								</td>
								<td width="250"><div class="col col-4">
									<label class="checkbox"><input type="checkbox" name="yemian" value="重车率分析"><i></i>重车率分析</label>
									<label class="checkbox"><input type="checkbox" name="yemian" value="单车平均营运收益分析"><i></i>单车平均营运收益分析</label>
									<label class="checkbox"><input type="checkbox" name="yemian" value="无营运数据车辆查询"><i></i>无营运数据车辆查询</label>
									<label class="checkbox"><input type="checkbox" name="yemian" value="未上线车辆查询"><i></i>未上线车辆查询</label>
									<label class="checkbox"><input type="checkbox" name="yemian" value="营运日报"><i></i>营运日报</label>
									<label class="checkbox"><input type="checkbox" name="yemian" value="营运月报"><i></i>营运月报</label>
									</div>
								</td>
							</tr>
							<tr>
								<td width="250"><div class="col col-4">

									<label class="checkbox"><input type="checkbox" name="yemian" value="上线无营运车辆查询"><i></i>上线无营运车辆查询</label>
									<label class="checkbox"><input type="checkbox" name="yemian" value="未上线营运车辆查询"><i></i>未上线营运车辆查询</label>
									<label class="checkbox"><input type="checkbox" name="yemian" value="未上线未营运车辆查询"><i></i>未上线未营运车辆查询</label>
									<label class="checkbox"><input type="checkbox" name="yemian" value="无空车变化查询"><i></i>无空车变化查询</label>
									<label class="checkbox"><input type="checkbox" name="yemian" value="多车监控"><i></i>多车监控</label>
									<label class="checkbox"><input type="checkbox" name="yemian" value="分屏监控"><i></i>分屏监控</label>					
									</div>
								</td>
								<td width="250"><div class="col col-4">
									<label class="checkbox"><input type="checkbox" name="yemian" value="平台使用情况"><i></i>平台使用情况</label>
									<label class="checkbox"><input type="checkbox" name="yemian" value="车辆转入申请"><i></i>车辆转入申请</label>
									<label class="checkbox"><input type="checkbox" name="yemian" value="车辆停报申请"><i></i>车辆停报申请</label>
									<label class="checkbox"><input type="checkbox" name="yemian" value="车辆号变更申请"><i></i>车辆号变更申请</label>
									<label class="checkbox"><input type="checkbox" name="yemian" value="车辆转出申请"><i></i>车辆转出申请</label>
									<label class="checkbox"><input type="checkbox" name="yemian" value="工单查询"><i></i>工单查询</label>
									
									</div>
								</td>
								<td width="250"><div class="col col-4">
									<label class="checkbox"><input type="checkbox" name="yemian" value="维修管理"><i></i>维修管理</label>
									<label class="checkbox"><input type="checkbox" name="yemian" value="维修统计"><i></i>维修统计</label>
									<label class="checkbox"><input type="checkbox" name="yemian" value="违章查询"><i></i>违章查询</label>
									<label class="checkbox"><input type="checkbox" name="yemian" value="服务质量查询"><i></i>服务质量查询</label>
									<label class="checkbox"><input type="checkbox" name="yemian" value="投诉查询"><i></i>投诉查询</label>
									</div>
								</td>
							</tr>
						</table>
						<input name="authenticity_token" type="hidden">
					<label class="label"><font color="black" size="2px">管理权限:</font></label>
						<table>
							<tr>
								<td width="150"><div class="col col-4">
									<span>用户管理</span>
								</td>
								<td width="95">
									<label class="checkbox"><input type="checkbox" name="guanli" value="用户管理增加"><i></i>增加</label>
									</td><td width="95">
									<label class="checkbox"><input type="checkbox" name="guanli" value="用户管理删除"><i></i>删除</label>
									</td><td width="95">
									<label class="checkbox"><input type="checkbox" name="guanli" value="用户管理修改"><i></i>修改</label>
									</td><td width="95">
									<label class="checkbox"><input type="checkbox" name="guanli" value="用户管理查看"><i></i>查看</label>
								</td>
							</tr>
							<tr>
								<td width="150"><div class="col col-4">
									<span>岗位管理</span>
								</td>
								<td width="95">
									<label class="checkbox"><input type="checkbox" name="guanli" value="岗位管理增加"><i></i>增加</label>
									</td><td width="95">
									<label class="checkbox"><input type="checkbox" name="guanli" value="岗位管理删除"><i></i>删除</label>
									</td><td width="95">
									<label class="checkbox"><input type="checkbox" name="guanli" value="岗位管理修改"><i></i>修改</label>
									</td><td width="95">
									<label class="checkbox"><input type="checkbox" name="guanli" value="岗位管理查看"><i></i>查看</label>
								</td>
							</tr>
							<tr>
								<td width="150"><div class="col col-4">
									<span>区域管理</span>
								</td>
								<td width="95">
									<label class="checkbox"><input type="checkbox" name="guanli" value="区域管理增加"><i></i>增加</label>
									</td><td width="95">
									<label class="checkbox"><input type="checkbox" name="guanli" value="区域管理删除"><i></i>删除</label>
									</td><td width="95">
									<label class="checkbox"><input type="checkbox" name="guanli" value="区域管理修改"><i></i>修改</label>
									</td><td width="95">
									<label class="checkbox"><input type="checkbox" name="guanli" value="区域管理查看"><i></i>查看</label>
								</td>
							</tr>
							<tr>
								<td width="150"><div class="col col-4">
									<span>车辆组管理</span>
								</td>
								<td width="95">
									<label class="checkbox"><input type="checkbox" name="guanli" value="车辆组管理增加"><i></i>增加</label>
									</td><td width="95">
									<label class="checkbox"><input type="checkbox" name="guanli" value="车辆组管理删除"><i></i>删除</label>
									</td><td width="95">
									<label class="checkbox"><input type="checkbox" name="guanli" value="车辆组管理修改"><i></i>修改</label>
									</td><td width="95">
									<label class="checkbox"><input type="checkbox" name="guanli" value="车辆组管理查看"><i></i>查看</label>
								</td>
							</tr>
							<tr>
								<td width="150"><div class="col col-4">
									<span>电子围栏管理</span>
								</td>
								<td width="95">
									<label class="checkbox"><input type="checkbox" name="guanli" value="电子围栏管理增加"><i></i>增加</label>
									</td><td width="95">
									<label class="checkbox"><input type="checkbox" name="guanli" value="电子围栏管理删除"><i></i>删除</label>
									</td><td width="95">
									<label class="checkbox"><input type="checkbox" name="guanli" value="电子围栏管理修改"><i></i>修改</label>
									</td><td width="95">
									<label class="checkbox"><input type="checkbox" name="guanli" value="电子围栏管理查看"><i></i>查看</label>
								</td>
							</tr>
						</table>
		</fieldset>
	</form>
</div>
<div id="cxstation" title="查看">
	<form>
		<fieldset>
			<input name="authenticity_token" type="hidden">
			<div class="form-group">
				<label>岗位名:</label>
				<input class="form-control" id="cxstationname" placeholder="--请输入岗位名--"   type="text">
			</div>
			<input name="authenticity_token" type="hidden">
					<label class="label"><font color="black" size="2px">页面权限:</font></label>
						<table>
							<tr>
								<td width="149"><div class="col col-4">
<!-- 									<label class="checkbox"><input type="checkbox" name="cxyemian" value="车辆跟踪"><i></i>车辆跟踪</label> -->
									<label class="checkbox"><input type="checkbox" name="cxyemian" value="历史轨迹"><i></i>历史轨迹</label>
									<label class="checkbox"><input type="checkbox" name="cxyemian" value="电子围栏"><i></i>电子围栏</label>
									<label class="checkbox"><input type="checkbox" name="cxyemian" value="区域监控"><i></i>区域监控</label>
									<label class="checkbox"><input type="checkbox" name="cxyemian" value="车辆组监控"><i></i>车辆组监控</label>
									<label class="checkbox"><input type="checkbox" name="cxyemian" value="视频监控"><i></i>视频监控</label>
									<label class="checkbox"><input type="checkbox" name="cxyemian" value="失物查找"><i></i>失物查找</label>
									</div>
								</td>
								<td width="200"><div class="col col-4">
									<label class="checkbox"><input type="checkbox" name="cxyemian" value="营运记录查询"><i></i>营运记录查询</label>
									<label class="checkbox"><input type="checkbox" name="cxyemian" value="车辆营运数据统计"><i></i>车辆营运数据统计</label>
									<label class="checkbox"><input type="checkbox" name="cxyemian" value="在线营运率分析"><i></i>在线营运率分析</label>
									<label class="checkbox"><input type="checkbox" name="cxyemian" value="里程利用率分析"><i></i>里程利用率分析</label>
									<label class="checkbox"><input type="checkbox" name="cxyemian" value="单车平均载客里程分析"><i></i>单车平均载客里程分析</label>
									<label class="checkbox"><input type="checkbox" name="cxyemian" value="上线率分析"><i></i>上线率分析</label>
									</div>
								</td>
								<td width="250"><div class="col col-4">
									<label class="checkbox"><input type="checkbox" name="cxyemian" value="重车率分析"><i></i>重车率分析</label>
									<label class="checkbox"><input type="checkbox" name="cxyemian" value="单车平均营运收益分析"><i></i>单车平均营运收益分析</label>
									<label class="checkbox"><input type="checkbox" name="cxyemian" value="无营运数据车辆查询"><i></i>无营运数据车辆查询</label>
									<label class="checkbox"><input type="checkbox" name="cxyemian" value="未上线车辆查询"><i></i>未上线车辆查询</label>
									<label class="checkbox"><input type="checkbox" name="cxyemian" value="营运日报"><i></i>营运日报</label>
									<label class="checkbox"><input type="checkbox" name="cxyemian" value="营运月报"><i></i>营运月报</label>
									</div>
								</td>
							</tr>
							<tr>
								<td width="250"><div class="col col-4">

									<label class="checkbox"><input type="checkbox" name="cxyemian" value="上线无营运车辆查询"><i></i>上线无营运车辆查询</label>
									<label class="checkbox"><input type="checkbox" name="cxyemian" value="未上线营运车辆查询"><i></i>未上线营运车辆查询</label>
									<label class="checkbox"><input type="checkbox" name="cxyemian" value="未上线未营运车辆查询"><i></i>未上线未营运车辆查询</label>
									<label class="checkbox"><input type="checkbox" name="cxyemian" value="无空车变化查询"><i></i>无空车变化查询</label>
									<label class="checkbox"><input type="checkbox" name="cxyemian" value="多车监控"><i></i>多车监控</label>
									<label class="checkbox"><input type="checkbox" name="cxyemian" value="分屏监控"><i></i>分屏监控</label>					
									</div>                                              
								</td>                                                   
								<td width="250"><div class="col col-4">                 
									<label class="checkbox"><input type="checkbox" name="cxyemian" value="平台使用情况"><i></i>平台使用情况</label>
									<label class="checkbox"><input type="checkbox" name="cxyemian" value="车辆转入申请"><i></i>车辆转入申请</label>
									<label class="checkbox"><input type="checkbox" name="cxyemian" value="车辆停报申请"><i></i>车辆停报申请</label>
									<label class="checkbox"><input type="checkbox" name="cxyemian" value="车辆号变更申请"><i></i>车辆号变更申请</label>
									<label class="checkbox"><input type="checkbox" name="cxyemian" value="车辆转出申请"><i></i>车辆转出申请</label>
									<label class="checkbox"><input type="checkbox" name="cxyemian" value="工单查询"><i></i>工单查询</label>
									                                                    
									</div>                                              
								</td>                                                   
								<td width="250"><div class="col col-4">                 
									<label class="checkbox"><input type="checkbox" name="cxyemian" value="维修管理"><i></i>维修管理</label>
									<label class="checkbox"><input type="checkbox" name="cxyemian" value="维修统计"><i></i>维修统计</label>
									<label class="checkbox"><input type="checkbox" name="cxyemian" value="违章查询"><i></i>违章查询</label>
									<label class="checkbox"><input type="checkbox" name="cxyemian" value="服务质量查询"><i></i>服务质量查询</label>
									<label class="checkbox"><input type="checkbox" name="cxyemian" value="投诉查询"><i></i>投诉查询</label>
									</div>
								</td>
							</tr>
						</table>
						<input name="authenticity_token" type="hidden">
					<label class="label"><font color="black" size="2px">管理权限:</font></label>
						<table>
							<tr>
								<td width="150"><div class="col col-4">
									<span>用户管理</span>
								</td>
								<td width="95">
									<label class="checkbox"><input type="checkbox" name="cxguanli" value="用户管理增加"><i></i>增加</label>
									</td><td width="95">
									<label class="checkbox"><input type="checkbox" name="cxguanli" value="用户管理删除"><i></i>删除</label>
									</td><td width="95">
									<label class="checkbox"><input type="checkbox" name="cxguanli" value="用户管理修改"><i></i>修改</label>
									</td><td width="95">
									<label class="checkbox"><input type="checkbox" name="cxguanli" value="用户管理查看"><i></i>查看</label>
								</td>
							</tr>
							<tr>
								<td width="150"><div class="col col-4">
									<span>岗位管理</span>
								</td>
								<td width="95">
									<label class="checkbox"><input type="checkbox" name="cxguanli" value="岗位管理增加"><i></i>增加</label>
									</td><td width="95">
									<label class="checkbox"><input type="checkbox" name="cxguanli" value="岗位管理删除"><i></i>删除</label>
									</td><td width="95">
									<label class="checkbox"><input type="checkbox" name="cxguanli" value="岗位管理修改"><i></i>修改</label>
									</td><td width="95">
									<label class="checkbox"><input type="checkbox" name="cxguanli" value="岗位管理查看"><i></i>查看</label>
								</td>
							</tr>
							<tr>
								<td width="150"><div class="col col-4">
									<span>区域管理</span>
								</td>
								<td width="95">
									<label class="checkbox"><input type="checkbox" name="cxguanli" value="区域管理增加"><i></i>增加</label>
									</td><td width="95">
									<label class="checkbox"><input type="checkbox" name="cxguanli" value="区域管理删除"><i></i>删除</label>
									</td><td width="95">
									<label class="checkbox"><input type="checkbox" name="cxguanli" value="区域管理修改"><i></i>修改</label>
									</td><td width="95">
									<label class="checkbox"><input type="checkbox" name="cxguanli" value="区域管理查看"><i></i>查看</label>
								</td>
							</tr>
							<tr>
								<td width="150"><div class="col col-4">
									<span>车辆组管理</span>
								</td>
								<td width="95">
									<label class="checkbox"><input type="checkbox" name="cxguanli" value="车辆组管理增加"><i></i>增加</label>
									</td><td width="95">
									<label class="checkbox"><input type="checkbox" name="cxguanli" value="车辆组管理删除"><i></i>删除</label>
									</td><td width="95">
									<label class="checkbox"><input type="checkbox" name="cxguanli" value="车辆组管理修改"><i></i>修改</label>
									</td><td width="95">
									<label class="checkbox"><input type="checkbox" name="cxguanli" value="车辆组管理查看"><i></i>查看</label>
								</td>
							</tr>
							<tr>
								<td width="150"><div class="col col-4">
									<span>电子围栏管理</span>
								</td>
								<td width="95">
									<label class="checkbox"><input type="checkbox" name="cxguanli" value="电子围栏管理增加"><i></i>增加</label>
									</td><td width="95">
									<label class="checkbox"><input type="checkbox" name="cxguanli" value="电子围栏管理删除"><i></i>删除</label>
									</td><td width="95">
									<label class="checkbox"><input type="checkbox" name="cxguanli" value="电子围栏管理修改"><i></i>修改</label>
									</td><td width="95">
									<label class="checkbox"><input type="checkbox" name="cxguanli" value="电子围栏管理查看"><i></i>查看</label>
								</td>
							</tr>
						</table>
		</fieldset>
	</form>
</div>
<div id="editstation" title="修改">
	<form>
		<fieldset>
			<input name="authenticity_token" type="hidden">
			<div class="form-group">
				<label>岗位名:</label>
				<input class="form-control" id="xgstationname" placeholder="--请输入岗位名--"   type="text">
				<input type="hidden" id="stationeditid"/>
			</div>
			<input name="authenticity_token" type="hidden">
					<label class="label"><font color="black" size="2px">页面权限:</font></label>
						<table>
							<tr>
								<td width="149"><div class="col col-4">
<!-- 									<label class="checkbox"><input type="checkbox" name="xgyemian" value="车辆跟踪"><i></i>车辆跟踪</label> -->
									<label class="checkbox"><input type="checkbox" name="xgyemian" value="历史轨迹"><i></i>历史轨迹</label>
									<label class="checkbox"><input type="checkbox" name="xgyemian" value="电子围栏"><i></i>电子围栏</label>
									<label class="checkbox"><input type="checkbox" name="xgyemian" value="区域监控"><i></i>区域监控</label>
									<label class="checkbox"><input type="checkbox" name="xgyemian" value="车辆组监控"><i></i>车辆组监控</label>
									<label class="checkbox"><input type="checkbox" name="xgyemian" value="视频监控"><i></i>视频监控</label>
									<label class="checkbox"><input type="checkbox" name="xgyemian" value="失物查找"><i></i>失物查找</label>
									</div>
								</td>
								<td width="200"><div class="col col-4">
									<label class="checkbox"><input type="checkbox" name="xgyemian" value="营运记录查询"><i></i>营运记录查询</label>
									<label class="checkbox"><input type="checkbox" name="xgyemian" value="车辆营运数据统计"><i></i>车辆营运数据统计</label>
									<label class="checkbox"><input type="checkbox" name="xgyemian" value="在线营运率分析"><i></i>在线营运率分析</label>
									<label class="checkbox"><input type="checkbox" name="xgyemian" value="里程利用率分析"><i></i>里程利用率分析</label>
									<label class="checkbox"><input type="checkbox" name="xgyemian" value="单车平均载客里程分析"><i></i>单车平均载客里程分析</label>
									<label class="checkbox"><input type="checkbox" name="xgyemian" value="上线率分析"><i></i>上线率分析</label>
									</div>
								</td>
								<td width="250"><div class="col col-4">
									<label class="checkbox"><input type="checkbox" name="xgyemian" value="重车率分析"><i></i>重车率分析</label>
									<label class="checkbox"><input type="checkbox" name="xgyemian" value="单车平均营运收益分析"><i></i>单车平均营运收益分析</label>
									<label class="checkbox"><input type="checkbox" name="xgyemian" value="无营运数据车辆查询"><i></i>无营运数据车辆查询</label>
									<label class="checkbox"><input type="checkbox" name="xgyemian" value="未上线车辆查询"><i></i>未上线车辆查询</label>
									<label class="checkbox"><input type="checkbox" name="xgyemian" value="营运日报"><i></i>营运日报</label>
									<label class="checkbox"><input type="checkbox" name="xgyemian" value="营运月报"><i></i>营运月报</label>
									</div>
								</td>
							</tr>
							<tr>
								<td width="250"><div class="col col-4">

									<label class="checkbox"><input type="checkbox" name="xgyemian" value="上线无营运车辆查询"><i></i>上线无营运车辆查询</label>
									<label class="checkbox"><input type="checkbox" name="xgyemian" value="未上线营运车辆查询"><i></i>未上线营运车辆查询</label>
									<label class="checkbox"><input type="checkbox" name="xgyemian" value="未上线未营运车辆查询"><i></i>未上线未营运车辆查询</label>
									<label class="checkbox"><input type="checkbox" name="xgyemian" value="无空车变化查询"><i></i>无空车变化查询</label>
									<label class="checkbox"><input type="checkbox" name="xgyemian" value="多车监控"><i></i>多车监控</label>
									<label class="checkbox"><input type="checkbox" name="xgyemian" value="分屏监控"><i></i>分屏监控</label>					
									</div>                                              
								</td>                                                   
								<td width="250"><div class="col col-4">                 
									<label class="checkbox"><input type="checkbox" name="xgyemian" value="平台使用情况"><i></i>平台使用情况</label>
									<label class="checkbox"><input type="checkbox" name="xgyemian" value="车辆转入申请"><i></i>车辆转入申请</label>
									<label class="checkbox"><input type="checkbox" name="xgyemian" value="车辆停报申请"><i></i>车辆停报申请</label>
									<label class="checkbox"><input type="checkbox" name="xgyemian" value="车辆号变更申请"><i></i>车辆号变更申请</label>
									<label class="checkbox"><input type="checkbox" name="xgyemian" value="车辆转出申请"><i></i>车辆转出申请</label>
									<label class="checkbox"><input type="checkbox" name="xgyemian" value="工单查询"><i></i>工单查询</label>
									                                                    
									</div>                                              
								</td>                                                   
								<td width="250"><div class="col col-4">                 
									<label class="checkbox"><input type="checkbox" name="xgyemian" value="维修管理"><i></i>维修管理</label>
									<label class="checkbox"><input type="checkbox" name="xgyemian" value="维修统计"><i></i>维修统计</label>
									<label class="checkbox"><input type="checkbox" name="xgyemian" value="违章查询"><i></i>违章查询</label>
									<label class="checkbox"><input type="checkbox" name="xgyemian" value="服务质量查询"><i></i>服务质量查询</label>
									<label class="checkbox"><input type="checkbox" name="xgyemian" value="投诉查询"><i></i>投诉查询</label>
									</div>                                              
								</td>
							</tr>
						</table>
						<input name="authenticity_token" type="hidden">
					<label class="label"><font color="black" size="2px">管理权限:</font></label>
						<table>
							<tr>
								<td width="150"><div class="col col-4">
									<span>用户管理</span>
								</td>
								<td width="95">
									<label class="checkbox"><input type="checkbox" name="xgguanli" value="用户管理增加"><i></i>增加</label>
									</td><td width="95">
									<label class="checkbox"><input type="checkbox" name="xgguanli" value="用户管理删除"><i></i>删除</label>
									</td><td width="95">
									<label class="checkbox"><input type="checkbox" name="xgguanli" value="用户管理修改"><i></i>修改</label>
									</td><td width="95">
									<label class="checkbox"><input type="checkbox" name="xgguanli" value="用户管理查看"><i></i>查看</label>
								</td>
							</tr>
							<tr>
								<td width="150"><div class="col col-4">
									<span>岗位管理</span>
								</td>
								<td width="95">
									<label class="checkbox"><input type="checkbox" name="xgguanli" value="岗位管理增加"><i></i>增加</label>
									</td><td width="95">
									<label class="checkbox"><input type="checkbox" name="xgguanli" value="岗位管理删除"><i></i>删除</label>
									</td><td width="95">
									<label class="checkbox"><input type="checkbox" name="xgguanli" value="岗位管理修改"><i></i>修改</label>
									</td><td width="95">
									<label class="checkbox"><input type="checkbox" name="xgguanli" value="岗位管理查看"><i></i>查看</label>
								</td>
							</tr>
							<tr>
								<td width="150"><div class="col col-4">
									<span>区域管理</span>
								</td>
								<td width="95">
									<label class="checkbox"><input type="checkbox" name="xgguanli" value="区域管理增加"><i></i>增加</label>
									</td><td width="95">
									<label class="checkbox"><input type="checkbox" name="xgguanli" value="区域管理删除"><i></i>删除</label>
									</td><td width="95">
									<label class="checkbox"><input type="checkbox" name="xgguanli" value="区域管理修改"><i></i>修改</label>
									</td><td width="95">
									<label class="checkbox"><input type="checkbox" name="xgguanli" value="区域管理查看"><i></i>查看</label>
								</td>
							</tr>
							<tr>
								<td width="150"><div class="col col-4">
									<span>车辆组管理</span>
								</td>
								<td width="95">
									<label class="checkbox"><input type="checkbox" name="xgguanli" value="车辆组管理增加"><i></i>增加</label>
									</td><td width="95">
									<label class="checkbox"><input type="checkbox" name="xgguanli" value="车辆组管理删除"><i></i>删除</label>
									</td><td width="95">
									<label class="checkbox"><input type="checkbox" name="xgguanli" value="车辆组管理修改"><i></i>修改</label>
									</td><td width="95">
									<label class="checkbox"><input type="checkbox" name="xgguanli" value="车辆组管理查看"><i></i>查看</label>
								</td>
							</tr>
							<tr>
								<td width="150"><div class="col col-4">
									<span>电子围栏管理</span>
								</td>
								<td width="95">
									<label class="checkbox"><input type="checkbox" name="xgguanli" value="电子围栏管理增加"><i></i>增加</label>
									</td><td width="95">
									<label class="checkbox"><input type="checkbox" name="xgguanli" value="电子围栏管理删除"><i></i>删除</label>
									</td><td width="95">
									<label class="checkbox"><input type="checkbox" name="xgguanli" value="电子围栏管理修改"><i></i>修改</label>
									</td><td width="95">
									<label class="checkbox"><input type="checkbox" name="xgguanli" value="电子围栏管理查看"><i></i>查看</label>
								</td>
							</tr>
						</table>
		</fieldset>
	</form>
</div>
</div>
<script type="text/javascript">
	
	// DO NOT REMOVE : GLOBAL FUNCTIONS!
	pageSetUp();
	//loadDataTableScripts_station();
	findstation();
	// PAGE RELATED SCRIPTS
	var area_glqx = $("#station_glqx").val();
	if(area_glqx!=null&&area_glqx.length>0){
		var a = area_glqx.split(',');
		for(var i=0;i<a.length;i++){
			if(a[i]=='岗位管理查看'){
				$("#sck").css('display','');
			}
			if(a[i]=='岗位管理增加'){
				$("#sxz").css('display','');
			}
			if(a[i]=='岗位管理修改'){
				$("#sxg").css('display','');
			}
			if(a[i]=='岗位管理删除'){
				$("#ssc").css('display','');
			}
		}
	}
	function findstation(){
	$.blockUI({message:"<div style='padding:5px;'><h3><font color='blue'>数据查询中,请稍候...</font></h3><img src='img/ajax-loader.gif' border='0'/><br/><br/></div>"});
		$.ajax({
			url : 'findstation.action',
			type : 'post',
			data:{
			},
			dataType: 'json',
			timeout : 180000,
			success:function(json){
			if(stations!=""){
				stations.fnDestroy();
				} 
					var quanxian=json.station;
					var tab="";
					if(quanxian!=null){
					for(var i=0;i<quanxian.length;i++){
						tab=tab+"<tr><td><input type='checkbox' name='idstation' value='"+quanxian[i].id+"' /></td>";
						tab=tab+"<td nowrap='nowrap'>"+(i+1)+"</td>";
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+quanxian[i].station_name+"</td>";
						if(quanxian[i].station_juri.length>30){
							tab=tab+"<td nowrap='nowrap'>&nbsp;"+quanxian[i].station_juri.substr(0,30)+"..."+"</td>";
						}else{
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+quanxian[i].station_juri+"</td>";
						}
						if(quanxian[i].station_admin.length>25){
							tab=tab+"<td nowrap='nowrap'>&nbsp;"+quanxian[i].station_admin.substr(0,25)+"..."+"</td>";
						}else{
						tab=tab+"<td nowrap='nowrap'>&nbsp;"+quanxian[i].station_admin+"</td>";
						}
					}
					$("#stationtbody").html(tab);
					loadDataTableScripts_station();
					}
			},
			error:function(){
				
			}		
		});
		$.unblockUI();
	}
	//获取每组复选框选中的值
	  function getCheckboxValues(name) {
            return $(":checkbox[name="+name+"]:checked").map(function(){  
                return $(this).val(); 
            }).get().join(",");
        }
 
	//增加
	 function addstation(){
            var yemian = getCheckboxValues("yemian");
            var guanli = getCheckboxValues("guanli");
				$.ajax({
             type: "POST",
             url:"addstation.action",
             data:{
             	"stationname" : $("#addstationname").val(),
             	"stationjuri" : yemian,
             	"stationadmin" :guanli
             },
            dataType: 'json',
			timeout : 180000,
             error: function(json) {
                 alert(json.info);
             },
             success: function(json) {
                alert(json.info);
                findstation();
             }
         });		
     }
     //增加弹出窗口
	function setstation(){
			$("#addstationname").val("");
            $("input[name='yemian']:checkbox").each(function(){
    			$(this).removeAttr("checked");
   			})
			$('#addstation').dialog('open');
		}
		$('#addstation').dialog({
		autoOpen : false,
		width : 650,
		resizable : false,
		modal : true,
		buttons : [{
				html : "<i class='fa fa-times'></i>&nbsp; 取消",
				"class" : "btn btn-default",
				click : function() {
					$(this).dialog("close");
	
				}
			}, {
	
				html : "<i class='fa fa-plus'></i>&nbsp; 增加",
				"class" : "btn btn-danger",
				click : function() {
					addstation();
					$(this).dialog("close");
				}
			}]
	});
	//删除
	function delstation() {
		var s="";
		$('input[name="idstation"]:checked').each(function(){
    	s+=$(this).val()+' ';
	});
	var checkbox=s.split(' ');
	if(checkbox.length<2){
		alert("请选择数据进行删除!");
	}else{
    	var r = window.confirm("您确定要删除该条数据吗？");
    	if(r) {
    	 $.blockUI({message:"<div style='padding:5px;'><h3><font color='blue'>数据查询中,请稍候...</font></h3><img src='img/ajax-loader.gif' border='0'/><br/><br/></div>"});
     	$.ajax({
			url : 'stationdel.action',
			type : 'post',
			data:{
				"action_id":s
			},
			dataType: 'json',
			timeout : 180000,
			success:function(json){
			alert(json.info);
			  findstation();
			},
			error:function(){
				alert(json.info);
			}		
	});
     	}
     	$.unblockUI();
	 }
	 }
	 //修改弹出窗口
	function stationedit(){
	 $("input[name='xgyemian']:checkbox").each(function(){
    	$(this).removeAttr("checked");
    })
     $("input[name='xgguanli']:checkbox").each(function(){
    	$(this).removeAttr("checked");
    })
	var s="";
		$('input[name="idstation"]:checked').each(function(){
    	s+=$(this).val()+' ';
	});
	var checkbox=s.split(' ');
	if(checkbox.length<2){
		alert("请选择数据进行修改!");
	}else if(checkbox.length>2){
		alert("每次只能修改一条数据!");
	}else{
     	$.ajax({
             type: "POST",
             url:"stationid.action",
             data:{
             	"action_id" : s
             },
            dataType: 'json',
			timeout : 180000,
             success: function(json) {
                $("#xgstationname").val(json.s.station_name);
                $("#stationeditid").val(json.s.id);
                var guanli=json.s.station_admin.split(',');
                var yemian=json.s.station_juri.split(',');
                var b = document.getElementsByName('xgyemian');
                var a = document.getElementsByName('xgguanli');
            	for (var i = 0; i < yemian.length; i++){
        			for (var j = 0; j < b.length; j++){
            			if (yemian[i] == b[j].value) {b[j].checked = true;break;}}}
            	for (var i = 0; i < guanli.length; i++){
        			for (var j = 0; j < a.length; j++){
            			if (guanli[i] == a[j].value) {a[j].checked = true;break;}}}
             }
         });
		$('#editstation').dialog('open');
	}
	}
	$('#editstation').dialog({
		autoOpen : false,
		width : 650,
		resizable : false,
		modal : true,
		buttons : [{
				html : "<i class='fa fa-times'></i>&nbsp; 取消",
				"class" : "btn btn-default",
				click : function() {
					$(this).dialog("close");
	
				}
			}, {
	
				html : "<i class='fa fa-plus'></i>&nbsp; 修改",
				"class" : "btn btn-danger",
				click : function() {
					edituser1();
					$(this).dialog("close");
				}
			}]
	});
	//修改
	 function edituser1(){
     $.blockUI({message:"<div style='padding:5px;'><h3><font color='blue'>数据查询中,请稍候...</font></h3><img src='img/ajax-loader.gif' border='0'/><br/><br/></div>"});
      		 var yemian = getCheckboxValues("xgyemian");
            var guanli = getCheckboxValues("xgguanli");
     	$.ajax({
             type: "POST",
             url:"stationedit.action",
             data:{
             	"action_id" : $("#stationeditid").val(),
             	"stationname" : $("#xgstationname").val(),
             	"stationjuri" : yemian,
             	"stationadmin" : guanli,
             },
            dataType: 'json',
			timeout : 180000,
             error: function(json) {
                 alert(json.info);
             },
             success: function(json) {
                alert(json.info);
                findstation();
             }
         });
     	$.unblockUI();
     }
     //查看
     function stationfind(){
    $("input[name='cxyemian']:checkbox").each(function(){
    	$(this).removeAttr("checked");
    })
     $("input[name='cxguanli']:checkbox").each(function(){
    	$(this).removeAttr("checked");
    })
	var s="";
		$('input[name="idstation"]:checked').each(function(){
    	s+=$(this).val()+' ';
	});
	var checkbox=s.split(' ');
	if(checkbox.length<2){
		alert("请选择数据进行才查看!");
	}else if(checkbox.length>2){
		alert("每次只能修改一条查看!");
	}else{
     	$.ajax({
             type: "POST",
             url:"stationid.action",
             data:{
             	"action_id" : s
             },
            dataType: 'json',
			timeout : 180000,
             success: function(json) {
                $("#cxstationname").val(json.s.station_name);
                var guanli=json.s.station_admin.split(',');
                var yemian=json.s.station_juri.split(',');
                var b = document.getElementsByName('cxyemian');
                var a = document.getElementsByName('cxguanli');
            	for (var i = 0; i < yemian.length; i++){
        			for (var j = 0; j < b.length; j++){
            			if (yemian[i] == b[j].value) {b[j].checked = true;break;}}}
            	for (var i = 0; i < guanli.length; i++){
        			for (var j = 0; j < a.length; j++){
            			if (guanli[i] == a[j].value) {a[j].checked = true;break;}}}
             }
         });
		$('#cxstation').dialog('open');
	}
	}
	$('#cxstation').dialog({
		autoOpen : false,
		width : 650,
		resizable : false,
		modal : true,
	});
	$("#gwgl").html("岗位管理");
//树
	$('.tree > ul').attr('role', 'tree').find('ul').attr('role', 'group');
	$('.tree').find('li:has(ul)').addClass('parent_li').attr('role', 'treeitem').find(' > span').attr('title', 'Collapse this branch').on('click', function(e) {
		var children = $(this).parent('li.parent_li').find(' > ul > li');
		if (children.is(':visible')) {
			children.hide('fast');
			$(this).attr('title', 'Expand this branch').find(' > i').removeClass().addClass('fa fa-lg fa-plus-circle');
		} else {
			children.show('fast');
			$(this).attr('title', 'Collapse this branch').find(' > i').removeClass().addClass('fa fa-lg fa-minus-circle');
		}
		e.stopPropagation();
	});
		
	if($('.DTTT_dropdown.dropdown-menu').length){
		$('.DTTT_dropdown.dropdown-menu').remove();
	}

	var stations="";
	function loadDataTableScripts_station() {

		loadScript("js/plugin/datatables/jquery.dataTables-cust.min.js", dt_2);

		function dt_2() {
			loadScript("js/plugin/datatables/ColReorder.min.js", dt_3);
		}

		function dt_3() {
			loadScript("js/plugin/datatables/FixedColumns.min.js", dt_4);
		}

		function dt_4() {
			loadScript("js/plugin/datatables/ColVis.min.js", dt_5);
		}

		function dt_5() {
			loadScript("js/plugin/datatables/ZeroClipboard.js", dt_6);
		}

		function dt_6() {
			loadScript("js/plugin/datatables/media/js/TableTools.min.js", dt_7);
		}

		function dt_7() {
			loadScript("js/plugin/datatables/DT_bootstrap.js", runDataTables_station);
		}

	}

	function runDataTables_station() {

		/*
		 * BASIC
		 */
		stations=$('#station_table').dataTable({
			"sPaginationType" : "bootstrap_full",
			"bRetrieve": true
		});

		/* END BASIC */

		/* Add the events etc before DataTables hides a column */
		$("#datatable_fixed_column thead input").keyup(function() {
			oTable.fnFilter(this.value, oTable.oApi._fnVisibleToColumnIndex(oTable.fnSettings(), $("thead input").index(this)));
		});

		$("#datatable_fixed_column thead input").each(function(i) {
			this.initVal = this.value;
		});
		$("#datatable_fixed_column thead input").focus(function() {
			if (this.className == "search_init") {
				this.className = "";
				this.value = "";
			}
		});
		$("#datatable_fixed_column thead input").blur(function(i) {
			if (this.value == "") {
				this.className = "search_init";
				this.value = this.initVal;
			}
		});		
		

		var oTable = $('#datatable_fixed_column').dataTable({
			"sDom" : "<'dt-top-row'><'dt-wrapper't><'dt-row dt-bottom-row'<'row'<'col-sm-6'i><'col-sm-6 text-right'p>>",
			//"sDom" : "t<'row dt-wrapper'<'col-sm-6'i><'dt-row dt-bottom-row'<'row'<'col-sm-6'i><'col-sm-6 text-right'>>",
			"oLanguage" : {
				"sSearch" : "Search all columns:"
			},
			"bSortCellsTop" : true
		});		
		


		/*
		 * COL ORDER
		 */
		$('#datatable_col_reorder').dataTable({
			"sPaginationType" : "bootstrap",
			"sDom" : "R<'dt-top-row'Clf>r<'dt-wrapper't><'dt-row dt-bottom-row'<'row'<'col-sm-6'i><'col-sm-6 text-right'p>>",
			"fnInitComplete" : function(oSettings, json) {
				$('.ColVis_Button').addClass('btn btn-default btn-sm').html('Columns <i class="icon-arrow-down"></i>');
			}
		});
		
		/* END COL ORDER */

		/* TABLE TOOLS */
		$('#datatable_tabletools').dataTable({
			"sDom" : "<'dt-top-row'Tlf>r<'dt-wrapper't><'dt-row dt-bottom-row'<'row'<'col-sm-6'i><'col-sm-6 text-right'p>>",
			"oTableTools" : {
				"aButtons" : ["copy", "print", {
					"sExtends" : "collection",
					"sButtonText" : 'Save <span class="caret" />',
					"aButtons" : ["csv", "xls", "pdf"]
				}],
				"sSwfPath" : "js/plugin/datatables/media/swf/copy_csv_xls_pdf.swf"
			},
			"fnInitComplete" : function(oSettings, json) {
				$(this).closest('#dt_table_tools_wrapper').find('.DTTT.btn-group').addClass('table_tools_group').children('a.btn').each(function() {
					$(this).addClass('btn-sm btn-default');
				});
			}
		});
		
		/* END TABLE TOOLS */
	}
</script>

<script src="js/jquery.blockUI.js"></script>

<link rel="stylesheet" type="text/css" media="screen" href="css/bootstrap.min.css">
