<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
contentType="text/html; charset=UTF-8" %>
    <style>
        @import "css/initStyle.css";
        @import "js/layer-v2.4/layer/skin/layer.css";
        #AMap {
            width: 100%;
            height: 100%;
        }
        #AMapToolBox {
            width: 400px;
            height: calc(100% - 100px);
            min-height: 500px;
            position: absolute;
            top: 50px;
            right: 50px;
            box-shadow: 2px 2px 4px #aaa;
            background: #ffffff;
        }
        .clearMap {
            padding: 5px 10px;
            position: absolute;
            top: 20px;
            left: -72px;
            border: none;
            border-radius: 50px 0 0 50px/50px 0 0 50px;
            outline: none;
            background: #c6e9ff;
        }
        .one1, .zhong1, .two1 {
            width: 90px;
            width: calc(100% - 20px);
            htight: 85px;
            min-height: 85px;
            position: relative;
            margin-top: 20px;
            margin-left: auto;
            margin-right: auto;
            padding-bottom: 10px;
            border: 1px solid #989898;
            font-size:12px;
            font-weight:none;
        }
        .one1 > .dt,
        .zhong1 > .dt,
        .two1 > .dt {
            padding-left: .3em;
            padding-right: .3em;
            position: absolute;
            top: -.5em;
            left: 1em;
            background: #ffffff;
            font-weight: none;
        }
        .one1 > .dd label,
        .zhong1 > .dd label,
        .two1 > .dd label {
            width: 80px;
            margin-top: 15px;
            margin-left: 4px;
            text-indent: 25px;
            display: inline-block;
        }
        .one1 > .dd input,
        .zhong1 > .dd input,
        .two1 > .dd input {
            width: 110px;
        }
        .dd input.kssj, .dd input.jssj {
            width: 160px;
        }
        .dwqdfw, .dwzjdfw, .dwzdfw {
            width: 110px;
            height: 52px;
            position: absolute;
            top: 10px;
            left: 250px;
            display: block;
        }
        [class^=btn] {
            line-height: 1.3em;
            margin-top: 5px;
            margin-left: 10px;
        }
        .tabFanwei {
            position: absolute;
            top: 350px;
            left: 10px;
            right: 10px;
            bottom: 10px;
        }
        .tabFanweiTitle {
            position: absolute;
            z-index: 1;
        }
        .tabFanweiTitle li {
            width: 5em;
            height: 2em;
            line-height: 2em;
            margin-left: 1px;
            margin-right: 2px;
            text-align: center;
            float: left;
            border: 1px solid #9c9c9c;
            border-top-left-radius: .3em;
            border-top-right-radius: .3em;
            background: #eaeaea;
            z-index: 1;
        }
        .tabFanweiTitle li[data-checked] {
            border-bottom-color: #ffffff;
            background: #ffffff;
        }
        [id$=Panel] {
            position: absolute;
            top: 25px; left: 0; right: 0; bottom: 0;
            overflow: hidden;
            border: 1px solid #9c9c9c;
        }
        [id$=Panel] table {font-weight :none;font-size:12px}
        [id$=Panel] table thead tr {
            height: 25px;
            line-height: 25px;
            border-bottom: 1px solid #bbbbbb;
            z-index: 1;
        }
        [id$=Panel] table thead tr th:nth-child(1), [id$=Panel] .tbody table tbody tr td:nth-child(1) {width: 87px;}
        [id$=Panel] table thead tr th:nth-child(2), [id$=Panel] .tbody table tbody tr td:nth-child(2) {width: 30%;}
        [id$=Panel] table thead tr th:nth-child(3), [id$=Panel] .tbody table tbody tr td:nth-child(3) {width: 30%;}
        [id$=Panel] table thead tr th:nth-child(4), [id$=Panel] .tbody table tbody tr td:nth-child(4) {width: 17%;}
        [id$=Panel] .tbody {
            height: 150px;
            overflow: auto;
        }
        [id$=Panel] table tbody tr {
            height: 23px;
            line-height: 23px;
        }
        [id$=Panel] table tbody tr td {
            text-align: center;
        }
    </style>
<!-- 	<script type="text/javascript" src="js/jquery-1.12.1.min.js"></script>     -->
    <script src="js/layer-v2.4/layer/layer.js"></script>
    <script type="text/javascript" src="js/car/swcz.js"></script>
    <div class="row" style="height: 98%" >
<div id="AMap"></div>
<div id="AMapToolBox">
    <button type="button" class="clearMap">清理地图</button>
    <div class="one1">
        <div class="dt">起点范围</div>
        <div class="dd">
            <label>开始时间</label>
            <input type="text" id="qd_stime" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})()" style="width:140px"/><br>
            <label>结束时间</label>
            <input type="text" id="qd_etime" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})()" style="width:140px"/>
            <button type="button" class="dwqdfw">定位起点</button>
        </div>
    </div>
    <div class="zhong1 clear">
        <div class="dt">中间点范围</div>
        <div class="dd">
            <label>开始时间</label>
            <input type="text" id="zj_stime" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})()" style="width:140px"/><br>
            <label>结束时间</label>
            <input type="text" id="zj_etime" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})()" style="width:140px"/>
            <button type="button" class="dwzjdfw">定位中间</button>
        </div>
    </div>
    <div class="two1 clear">
        <div class="dt">终点范围</div>
        <div class="dd">
            <label>开始时间</label>
            <input type="text" id="zd_stime" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})()" style="width:140px"/><br>
            <label>结束时间</label>
            <input type="text" id="zd_etime" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})()" style="width:140px"/>
            <button type="button" class="dwzdfw">定位终点</button>
        </div>
    </div>
    <button type="button" class="btn_query clear" id="swcz_cx" onclick = "findswcz();">查询</button>
    <button type="button" class="btn_query clear" id="swcz_dc" onclick = "findswczexcle();">导出</button><span id="findswczexcle"></span>
<!--     <button type="button" class="btn_queryCarInfo">查看车辆信息</button> -->
    <div class="tabFanwei">
        <ul class="tabFanweiTitle">
            <li class="qdfwTab" data-id="qdfwPanel" data-checked="">起点范围</li>
            <li class="zdfwTab" data-id="zdfwPanel">终点范围</li>
            <li class="clxxTab" data-id="clxxPanel">车辆信息</li>
        </ul>
        <div id="qdfwPanel">
            <table width="100%" border="1">
                <thead>
                <tr>
                    <th style='width:20%'>车牌号码</th>
                    <th style='width:40%'>经纬度</th>
                    <th style='width:40%'>时间</th>
                </tr>
                </thead>
            </table>
            <div class="tbody">
                <table>
                    <tbody></tbody>
                </table>
            </div>
        </div>
        <div id="zdfwPanel"  style="display: none;">
            <table width="100%">
                <thead>
                <tr>
                   <th style='width:20%'>车牌号码</th>
                    <th style='width:40%'>经纬度</th>
                    <th style='width:40%'>时间</th>
                </tr>
                </thead>
            </table>
            <div class="tbody">
                <table>
                    <tbody></tbody>
                </table>
            </div>
        </div>
        <div id="clxxPanel"  style="display: none;">
            <table style="width: 100%;">
                <thead>
                <tr>
                    <th style='width:20%'>车牌号码</th>
                    <th style='width:40%'>经纬度</th>
                    <th style='width:40%'>时间</th>
                </tr>
                </thead>
            </table>
            <div class="tbody">
                <table>
                    <tbody></tbody>
                </table>
            </div>
        </div>
    </div>
</div>
</div>