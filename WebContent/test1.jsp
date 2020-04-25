<%@page import="iolayer.SQLRead"%>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>

<%
	String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'Test.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
 <body>
 　<%
   	SQLRead test= new SQLRead();
       List<Map<String, Object>> list = (List<Map<String, Object>>) test.getInfo("select*from book");//对servlet中的数据的接收
       if (list.size() < 1) {//判断数据库中有没有数据
           out.print("数据库中没有数据");
       } else {
   %>
   <table border="1" align="center">
    <tr>
        <th>id</th>
        <th>name</th>
        <th>author</th>
        <th>publishing</th>
        <th>price</th>
        <th>data</th>
    </tr>
<%
        for (int i = 0; i < list.size(); i++) {//对接收的数据进行遍历打印
    %>
        <tr>
            <td><%=list.get(2).get("id")%></td>
        <td><%=list.get(2).get("bookname")%></td>
        <td><%=list.get(2).get("writer")%></td>
        <td><%=list.get(2).get("publishing")%></td>
        <td><%=list.get(2).get("price")%></td>
        <td><%=list.get(2).get("date")%></td>
        </tr>
       <tr>
        <td colspan="6" align="right">yes</td>
      </tr>
             <%} }%>
        
</table>
  </body>
</html>
