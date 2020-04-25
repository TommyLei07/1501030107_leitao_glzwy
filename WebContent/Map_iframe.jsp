<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>

	 <title>桂林园林植物园多源爬虫系统</title>

    <!-- meta -->
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">    
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link href="css/styles.css" rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=Poiret+One' rel='stylesheet' type='text/css'>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="css/zero.css" />
<script language="javascript" src="http://webapi.amap.com/maps?v=1.2&key=caaa086bdf5666322fba3baf5a6a2c03"></script>
</head>
<body onLoad="mapInit()">
    </div>
    <div id="iCenter"></div>
    <div id="iControlbox">
        <p>坐标：<span id="lnglats">&nbsp;</span></p>
        <ul>
            <li>
                <button onclick="javascript:driving_route();">驾车路线规划</button>
            </li>
            <li>
                <button onclick="javascript:transfer_route();">公交路线规划</button>
            </li>
             <li>
                <button onclick="javascript:walking_route();">步行路线规划</button>
            </li>
            <li>
            <button onclick="javascript:lineSearch();">10路公交车</button>
            </li>
            <li>
             <button onclick="javascript:stationSearch();">桂林站站点</button>
            </li>
           
            <li>
                <button onclick="javascript:clearMap();">清空地图</button>
            </li>
        </ul>
    </div>
    <div id="result"></div>
<!-- tongji begin-->
<!-- <script type="text/javascript">
var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");
document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3Faeff88f19045b513af7681b011cea3bd' type='text/javascript'%3E%3C/script%3E"));
</script> -->
<!-- tongji end -->
</body>
<script language="javascript">
function setLi(id1,id2){
    document.getElementById("box1").style.display = "none";
    document.getElementById("box2").style.display = "none";
    document.getElementById("box3").style.display = "none";
    document.getElementById(id1).style.display = "block";

    document.getElementById("iLi1").style.background = "#eee";
    document.getElementById("iLi2").style.background = "#eee";
    document.getElementById("iLi3").style.background = "#eee";
    document.getElementById(id2).style.background = "#fff";
}

var mapObj;
var route_text
var steps;
var polyline;
var MDrive;
var btContent = new Array(); //结果表格数组
var resultStr;//结果拼接string
var resLine  = ''; //结果表格对象
//初始化地图对象，加载地图
function mapInit(){
    mapObj = new AMap.Map("iCenter",{
    center:new AMap.LngLat(110.2825600000,25.2610700000), //地图中心点
    level:12  //地图显示的比例尺级别
    });
    AMap.event.addListener(mapObj,'click',getLnglat); //点击事件
}
//鼠标点击，获取经纬度坐标
function getLnglat(e){
    var x = e.lnglat.getLng();
    var y = e.lnglat.getLat();
    document.getElementById("lnglats").innerHTML = x + "," + y;
}
//清空地图
function clearMap(){
    mapObj.clearMap();
    document.getElementById("result").innerHTML = '&nbsp;';
}
//起、终点
var start_xy = new AMap.LngLat(110.2825600000,25.2610700000);
var end_xy = new AMap.LngLat(110.2761100000,25.2535900000);
//驾车导航
function driving_route() {
    clearMap();
    mapObj.plugin(["AMap.Driving"], function() {
        var DrivingOption = {
            //驾车策略，包括 LEAST_TIME，LEAST_FEE, LEAST_DISTANCE,REAL_TRAFFIC
            policy: AMap.DrivingPolicy.LEAST_TIME
        };
        MDrive = new AMap.Driving(DrivingOption); //构造驾车导航类
        AMap.event.addListener(MDrive, "complete", driving_routeCallBack); //返回导航查询结果
        MDrive.search(start_xy, end_xy); //根据起终点坐标规划驾车路线
    });
}
//可拖拽的驾车导航
function driving_route_drag(){
    clearMap();
    var path = [start_xy,end_xy];
    mapObj.plugin("AMap.DragRoute",function(){
        MDrive = new AMap.DragRoute(mapObj, path, AMap.DrivingPolicy.LEAST_FEE); //构造拖拽导航类
        MDrive.search(); //查询导航路径并开启拖拽导航
    });
}
//导航结果展示
function driving_routeCallBack(data) {
    var routeS = data.routes;
    if (routeS.length <= 0) {
        document.getElementById("result").innerHTML = "未查找到任何结果!<br />建议：<br />1.请确保所有字词拼写正确。<br />2.尝试不同的关键字。<br />3.尝试更宽泛的关键字。";
    }
    else{
        route_text="";
        for(var v =0; v< routeS.length;v++){
            //驾车步骤数
            steps = routeS[v].steps
            var route_count = steps.length;
            //行车距离（米）
            var distance = routeS[v].distance;
            //拼接输出html
            for(var i=0 ;i< steps.length;i++){
                route_text += "<tr><td align=\"left\" onMouseover=\"driveDrawFoldline('" + i + "')\">" + i +"." +steps[i].instruction  + "</td></tr>";
            }
        }
        //输出行车路线指示
        route_text = "<table cellspacing=\"5px\"><tr><td style=\"background:#e1e1e1;\">路线</td></tr><tr><td>&nbsp;&nbsp;桂林站</td></tr>" + route_text + "<tr><td>&nbsp;&nbsp;桂林园林植物园</td></tr></table>";
        document.getElementById("result").innerHTML = route_text;
        drivingDrawLine();
    }
}
//绘制驾车导航路线
function drivingDrawLine(s) {
    //起点、终点图标
    var sicon = new AMap.Icon({
        image: "http://api.amap.com/Public/images/js/poi.png",
        size:new AMap.Size(44,44),
        imageOffset: new AMap.Pixel(-334, -180)
    });
    var startmarker = new AMap.Marker({
        icon : sicon, //复杂图标
        visible : true,
        position : start_xy,
        map:mapObj,
        offset : {
            x : -16,
            y : -40
        }
    });
    var eicon = new AMap.Icon({
        image: "http://api.amap.com/Public/images/js/poi.png",
        size:new AMap.Size(44,44),
        imageOffset: new AMap.Pixel(-334, -134)
    });
    var endmarker = new AMap.Marker({
        icon : eicon, //复杂图标
        visible : true,
        position : end_xy,
        map:mapObj,
        offset : {
            x : -16,
            y : -40
        }
    });
    //起点到路线的起点 路线的终点到终点 绘制无道路部分
    var extra_path1 = new Array();
    extra_path1.push(start_xy);
    extra_path1.push(steps[0].path[0]);
    var extra_line1 = new AMap.Polyline({
        map: mapObj,
        path: extra_path1,
        strokeColor: "#9400D3",
        strokeOpacity: 0.7,
        strokeWeight: 4,
        strokeStyle: "dashed",   //虚线
        strokeDasharray: [10, 5]
    });

    var extra_path2 = new Array();
    var path_xy = steps[(steps.length-1)].path;
    extra_path2.push(end_xy);
    extra_path2.push(path_xy[(path_xy.length-1)]);
    var extra_line2 = new AMap.Polyline({
        map: mapObj,
        path: extra_path2,
        strokeColor: "#9400D3",
        strokeOpacity: 0.7,
        strokeWeight: 4,
        strokeStyle: "dashed",  //虚线
        strokeDasharray: [10, 5]
    });

    var drawpath = new Array();
    for(var s=0; s<steps.length; s++){
        drawpath = steps[s].path;
        var polyline = new AMap.Polyline({
            map: mapObj,
            path: drawpath,
            strokeColor: "#9400D3",
            strokeOpacity: 0.7,
            strokeWeight: 4,
            strokeDasharray: [10, 5]
        });
    }
    mapObj.setFitView();
}
//绘制驾车导航路段
function driveDrawFoldline(num){
    var drawpath1 = new Array();
    drawpath1 = steps[num].path;
    if(polyline != null){
        polyline.setMap(null);
    }
    polyline = new AMap.Polyline({
            map: mapObj,
            path: drawpath1,
            strokeColor: "#FF3030",
            strokeOpacity: 0.9,
            strokeWeight: 4,
            strokeDasharray: [10, 5]
        });

    mapObj.setFitView(polyline);
}
//公交导航
function transfer_route() {
    clearMap();
    var trans;
    //加载公交换乘插件
    mapObj.plugin(["AMap.Transfer"], function() {
        transOptions = {
            city: '桂林市',                            //公交城市
            policy: AMap.TransferPolicy.LEAST_TIME //乘车策略
        };
        //构造公交换乘类
        trans = new AMap.Transfer (transOptions);
        //返回导航查询结果
        AMap.event.addListener(trans, "complete", transCallBack);
        //显示错误信息
        AMap.event.addListener(trans, "error", function(e) {alert(e.info);});
        //根据起、终点坐标查询公交换乘路线
        trans.search(start_xy,end_xy);
    });
}
function transCallBack(data) {
    var btCount       = data.count;
    var btPlans       = data.plans;
    var btOrigin      = data.origin;
    var btDestination = data.destination;
    var btTaxiCost    = data.taxi_cost;
    var startName     = "桂林站"; //可以使用地理编码解析起点和终点坐标
    var endName       = "桂林园林植物园";
    var BusArr        = [];
    var WalkArr       = [];
    var onbus         = new Array();
    var onwalk        = new Array();
    //结果输出用表格展现，输出表格头
    var resTableHeader = "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tr><td width=\"80\" style=\" border-left:1px solid #fff; background:#e1e1e1;\">　乘车方案</td><td width=\"80\" style=\" border-left:1px solid #fff; background:#e1e1e1;\">　导航信息</td></tr>";
    btContent.push(resTableHeader);
    //遍历每种换乘方案
    for (var i = 0; i < btPlans.length; i++) {
        var btDistance  = btPlans[i].distance;
        var btseg       = btPlans[i].segments;
        var lineNameArr = new Array();
        var resLine     = "";
        var naviInfo    = '';
        var lineName;
        for(var j = 0; j < btseg.length; j++) {
            naviInfo += btseg[j].instruction + "<br/>";
            if(btseg[j].transit_mode =="WALK") {
                if(i===0) {
                    WalkArr.push(btseg[j].transit.path);
                }
            }
            else {
                lineName = btseg[j].transit.lines[0].name;
                lineNameArr.push(lineName);
                if(i===0) {
                    BusArr.push(btseg[j].transit.path);
                }
            }
        }
        lineName = lineNameArr.join("-->");
        drawBuschangeLine(btOrigin,btDestination,BusArr,WalkArr);
        //结果输出用表格展现，输出表格内容
        resLine = "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">"
        + "<tr><td width=\"80\" class=\"change_blue\">"+ lineName +"</td><td width=\"80\" class=\"change_blue\" >"
        + Getdistance(btDistance) + "</td></tr>" + "<tr><td width=\"80\" class=\"change_blue\" ></td><td width=\"80\"><img src=\"http://webapi.amap.com/images/start.gif\" /> <b>起点</b> " + startName + "</td></tr>" + "<tr><td width=\"80\" class=\"change_blue\"></td><td width=\"80\" class=\"change_blue\">"+ naviInfo +"</td></tr>"+ "<tr><td width=\"80\" class=\"change_blue\" ></td><td width=\"80\"><img src=\"http://webapi.amap.com/images/end.gif\" /> <b>终点</b> " + endName + "</td></tr>";
        btContent.push(resLine);
     }
    resultStr = btContent.join("");
     //写到result这个div
    document.getElementById("result").innerHTML = resultStr;
    //取出需要加换乘、步行图标的位置，这里仅画出第一个换乘方案
    var sinseg = btPlans[0].segments;
    for(var a in sinseg) {
        if(sinseg[a].transit_mode =="WALK") {
            onwalk.push(sinseg[a].transit.origin);
        }
        else {
            onbus.push(sinseg[a].transit.on_station.location);
        }
    }
    addMarker(onbus);
    mapObj.setFitView();
}
//距离，米换算为千米
function Getdistance(len) {
    if (len <= 1000) {
        var s = len;
        return s + "米";
    } else {
        var s = Math.round(len / 1000);
        return "约" + s + "公里";
    }
}
//绘制路线，仅第一条
function drawBuschangeLine(startPot,endPot,BusArr,WalkArr) {
    //自定义起点，终点图标
     var sicon = new AMap.Icon({
        image: "http://api.amap.com/Public/images/js/poi.png",
        size: new AMap.Size(44,44),
        imageOffset: new AMap.Pixel(-334, -180)
    });
     var eicon = new AMap.Icon({
        image: "http://api.amap.com/Public/images/js/poi.png",
        size: new AMap.Size(44,44),
        imageOffset: new AMap.Pixel(-334, -134)
    });
    //绘制起点，终点
    var stmarker = new AMap.Marker({
        map:mapObj,
        position:new AMap.LngLat(startPot.lng,startPot.lat), //基点位置
        icon:sicon, //复杂图标
        offset:{x:-16,y:-34} //相对于基点的位置
    });
    var endmarker = new AMap.Marker({
        map:mapObj,
        position:new AMap.LngLat(endPot.lng,endPot.lat), //基点位置
        icon:eicon, //复杂图标
        offset:{x:-16,y:-34} //相对于基点的位置
    });
    //绘制乘车的路线
    for(var j in BusArr) {
        busPolyline = new AMap.Polyline({
            map:mapObj,
            path:BusArr[j],
            strokeColor:"#005cb5",//线颜色
            strokeOpacity:0.8,//线透明度
            strokeWeight:6//线宽
        });
    }
    //绘制步行的路线
    for (var i in WalkArr) {
        walkPolyline = new AMap.Polyline({
            map:mapObj,
            path:WalkArr[i],
            strokeColor : "#6EB034", //线颜色
            strokeOpacity : 0.6, //线透明度
            strokeWeight : 6//线宽
        });
    }

}
function addMarker(busmar) {
    for (var i = 0; i < busmar.length; i++) {
        var busmarker = new AMap.Marker({
            icon : new AMap.Icon({
                image: "http://api.amap.com/Public/images/js/busroute.png",
                size: new AMap.Size(20, 20),
                imageOffset: new AMap.Pixel(-33, -3)
            }),
            position : busmar[i],
            offset : {
                x : -25,
                y : -25
            },
            map:mapObj
        });
    }
}
//公交线路查询
function lineSearch() {
    clearMap();
    //加载公交线路查询插件
    //实例化公交线路查询类，只取回一条路线
    mapObj.plugin(["AMap.LineSearch"], function() {
       var linesearch = new AMap.LineSearch({
            pageIndex:1,
            city:'桂林',
            pageSize:1,
            extensions:'all'
        });
        //搜索“518”相关公交线路
        linesearch.search('10');
        AMap.event.addListener(linesearch, "complete", lineSearch_Callback);
        AMap.event.addListener(linesearch, "error", function(e){alert(e.info);
        });
    });
}
function lineSearch_Callback(data) {
    var lineArr  = data.lineInfo;
    var lineNum  = data.lineInfo.length;
    if(lineNum == 0) {
        resLine = data.info;
    }
    else {
        resLine += "<div id=\"divid" + i + "\"><table>";
        for(var i = 0; i < lineNum; i++) {
            var lineName = lineArr[i].name;
            var lineCity = lineArr[i].city;
            var distance = lineArr[i].distance;
            var company  = lineArr[i].company;
            var stime    = lineArr[i].stime;
            var etime    = lineArr[i].etime;
            var pathArr  = lineArr[i].path;
            var stops    = lineArr[i].via_stops;
            var startPot = stops[0].location;
            var endPot   = stops[stops.length-1].location;
            //结果输出用DIV展现，输出内容
            resLine += "<tr><td><h3><font color=\"#00a6ac\">" + lineName + "</font></h3></td></tr>";
            resLine += "<tr><td>首末车时间：" + stime.substring(0, 2) + ":" + stime.substring(2, 4) + '-' + etime.substring(0, 2) + ":" + etime.substring(2, 4) + "；" + "全长：" + distance + "公里;" + "所属公司：" + company+"</td></tr>";
           //绘制第一条路线
            if(i==0) drawbusLine(startPot,endPot,pathArr);
        }
        resLine += "</table></div>"
        document.getElementById('result').innerHTML = resLine;
    }
}
//绘制路线
function drawbusLine(startPot,endPot,BusArr) {
    //自定义起点，终点图标
     var sicon = new AMap.Icon({
        image: "http://api.amap.com/Public/images/js/poi.png",
        size: new AMap.Size(44,44),
        imageOffset: new AMap.Pixel(-334, -180)
    });
     var eicon = new AMap.Icon({
        image: "http://api.amap.com/Public/images/js/poi.png",
        size: new AMap.Size(44,44),
        imageOffset: new AMap.Pixel(-334, -134)
    });
    //绘制起点，终点
    var stmarker = new AMap.Marker({
        map:mapObj,
        position:new AMap.LngLat(startPot.lng,startPot.lat), //基点位置
        icon:sicon, //复杂图标
        offset:{x:-16,y:-34}, //相对于基点的位置
        zIndex:10
    });
    var endmarker = new AMap.Marker({
        map:mapObj,
        position:new AMap.LngLat(endPot.lng,endPot.lat), //基点位置
        icon:eicon, //复杂图标
        offset:{x:-16,y:-34}, //相对于基点的位置
        zIndex:10
    });
    //绘制乘车的路线
    busPolyline = new AMap.Polyline({
        map:mapObj,
        path:BusArr,
        strokeColor:"#005cb5",//线颜色
        strokeOpacity:0.8,//线透明度
        strokeWeight:6//线宽
    });
    mapObj.setFitView();
}
//公交站点查询
function stationSearch() {
    clearMap();
    //加载公交站点查询插件
    mapObj.plugin(["AMap.StationSearch"], function() {
        //实例化公交站点查询类
        var station = new AMap.StationSearch({
            pageIndex: 1, //页码
            pageSize: 10, //单页显示结果条数
            city:'桂林'    //确定搜索城市
        });
        station.search('桂林站'); //查询
        AMap.event.addListener(station, 'complete', stationSearch_CallBack);
        AMap.event.addListener(station, 'error', function(e) {alert(e.info);});
    });
}
/*
 *公交站点查询返回数据解析
 */
function stationSearch_CallBack(searchResult) {
    clearMap();
    var resultStr    = ""; //结果拼接String
    var resultString = searchResult.info;
    var stationArr   = searchResult.stationInfo;
    var searchNum    = stationArr.length;
    var iMarker    = searchResult.location;

    if(searchNum > 0) {
        resultStr += "<div id=\"divid" + i + "\"><table>";
        for(var i=0; i<searchNum; i++) {
            //结果输出用表格展现
            resultStr += "<tr><td><h3><font color=\"#00a6ac\">" + stationArr[i].name + "</font></h3></td></tr>";

            resultStr += "<tr><td>途径该站点的公交线路:</td></tr><tr><td>";
            var stationBusArr = stationArr[i].buslines;
            for(var j =0; j<stationBusArr.length; j++) {
                resultStr += stationBusArr[j].name + "<br/>";
            }
        }
        resultStr += "</td></tr></table></div>"
        document.getElementById("result").innerHTML = resultStr;

        var stmarker = new AMap.Marker({
            map:mapObj,
            position:iMarker
        });
    }
    else {
        document.getElementById("result").innerHTML = resultString;
    }
}
//步行导航
function walking_route() {
    var MWalk;
    mapObj.plugin(["AMap.Walking"], function() {
        MWalk = new AMap.Walking(); //构造路线导航类
        AMap.event.addListener(MWalk, "complete", walk_routeCallBack); //返回导航查询结果
        MWalk.search(start_xy, end_xy); //根据起终点坐标规划步行路线
    });
}
//导航结果展示
function walk_routeCallBack(data) {
    var routeS = data.routes;
        if (routeS.length <= 0) {
            document.getElementById("result").innerHTML = "未查找到任何结果!<br />建议：<br />1.请确保所有字词拼写正确。<br />2.尝试不同的关键字。<br />3.尝试更宽泛的关键字。";
        }
        else {
            route_text="";
            for(var v =0; v< routeS.length;v++){
                //步行导航路段数
                steps = routeS[v].steps;
                var route_count = steps.length;
                //步行距离（米）
                var distance = routeS[v].distance;
                //拼接输出html
                for(var i=0 ;i< steps.length;i++) {
                    route_text += "<tr><td align=\"left\" onMouseover=\"walkingDrawSeg('" + i + "')\">" + i +"." +steps[i].instruction  + "</td></tr>";
                }
            }
            //输出步行路线指示
            route_text = "<table cellspacing=\"5 px\" ><tr><td style=\"background:#e1e1e1;\">路线</td></tr><tr><td><img src=\"http://code.mapabc.com/images/start.gif\" />&nbsp;&nbsp;北京南站</td></tr>" + route_text + "<tr><td><img src=\"http://code.mapabc.com/images/end.gif\" />&nbsp;&nbsp;北京站</td></tr></table>";
            document.getElementById("result").innerHTML = route_text;
            walkingDrawLine();
        }
}
//绘制步行导航路线
function walkingDrawLine() {
    //起点、终点图标
    var sicon = new AMap.Icon({
        image: "http://api.amap.com/Public/images/js/poi.png",
        size:new AMap.Size(44,44),
        imageOffset: new AMap.Pixel(-334, -180)
    });
    var startmarker = new AMap.Marker({
        icon : sicon, //复杂图标
        visible : true,
        position : start_xy,
        map:mapObj,
        offset : {
            x : -16,
            y : -40
        }
    });
    var eicon = new AMap.Icon({
        image: "http://api.amap.com/Public/images/js/poi.png",
        size:new AMap.Size(44,44),
        imageOffset: new AMap.Pixel(-334, -134)
    });
    var endmarker = new AMap.Marker({
        icon : eicon, //复杂图标
        visible : true,
        position : end_xy,
        map:mapObj,
        offset : {
            x : -16,
            y : -40
        }
    });
    //起点到路线的起点 路线的终点到终点 绘制无道路部分
    var extra_path1 = new Array();
    extra_path1.push(start_xy);
    extra_path1.push(steps[0].path[0]);
    var extra_line1 = new AMap.Polyline({
        map: mapObj,
        path: extra_path1,
        strokeColor: "#9400D3",
        strokeOpacity: 0.7,
        strokeWeight: 4,
        strokeStyle: "dashed",
        strokeDasharray: [10, 5]
    });

    var extra_path2 = new Array();
    var path_xy = steps[(steps.length-1)].path;
    extra_path2.push(end_xy);
    extra_path2.push(path_xy[(path_xy.length-1)]);
    var extra_line2 = new AMap.Polyline({
        map: mapObj,
        path: extra_path2,
        strokeColor: "#9400D3",
        strokeOpacity: 0.7,
        strokeWeight: 4,
        strokeStyle: "dashed",
        strokeDasharray: [10, 5]
    });

    for(var s=0; s<steps.length; s++) {
        var drawpath = steps[s].path;
        var polyline = new AMap.Polyline({
            map: mapObj,
            path: drawpath,
            strokeColor: "#9400D3",
            strokeOpacity: 0.7,
            strokeWeight: 4
        });
    }
    mapObj.setFitView();
}
</script>
</html>