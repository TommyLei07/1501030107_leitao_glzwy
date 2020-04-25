<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="iolayer.SQLRead"%>

<%@page import="iolayer.SQLRead"%>
<%@include file="include/header.jsp"%>
<%@include file="include/nav_no.jsp"%>



<%
	SQLRead test= new SQLRead();
    List<Map<String, Object>> list_weather = (List<Map<String, Object>>) test.getInfo("select*from weather_");//对servlet中的数据的接收
    if (list_weather.size() < 1) {//判断数据库中有没有数据
        out.print("数据库中没有数据");
    } else {
%>


    <div class="divider col-sm-12 col-xs-12 col-md-12">
        <div class="header-text"> Weather <span>最近天气</span></div>
    </div>
    
       　
    <div class="">
    	<table class="table table-hover" >
      <thead>
        <th></th>
         <th >日期</th>
         <th >天气</th>
         <th >温度</th>
         <th >风力</th>
      </thead>
      <tbody>
      <%
	 for (int i = 0; i < list_weather.size(); i++){
%>
         <tr>
           <td><%=i+1 %></td>
           <td><%=list_weather.get(i).get("Wdate") %></td>
           <td><%=list_weather.get(i).get("Wwea") %></td>
           <td><%=list_weather.get(i).get("Wtem") %></td>
           <td><%=list_weather.get(i).get("Wwin") %></td>
      
         </tr>
         <%}} %>
      </tbody>
    </table>
    </div>

  <%@include file="include/footer.jsp"%>  

