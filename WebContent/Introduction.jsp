<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@page import="iolayer.SQLRead"%>
<%@include file="include/header.jsp"%>
<%@include file="include/nav_no.jsp"%>

<%
	SQLRead test= new SQLRead();
    List<Map<String, Object>> list_intros = (List<Map<String, Object>>) test.getInfo("select*from intros_");//对servlet中的数据的接收
    if (list_intros.size() < 1) {//判断数据库中有没有数据
        out.print("数据库中没有数据");
    } else {
%>

    <div class="divider col-sm-12 col-xs-12 col-md-12">
        <div class="header-text"> Introduction <span>景点简介</span></div>
    </div>
    <%
	 for (int i = 0; i < list_intros.size(); i++){
%>    
       　
   <div class="">
	<table class="table table-hover" >
  <thead>
     <th >景点属性</th>
     <th >内容</th>


  </thead>
  <tbody>
     <tr>
        <td>地址</td>
        <td><%=list_intros.get(i).get("location")%></td>

     </tr>
		 <tr>
 			 <td>景区类型</td>
 			 <td><%=list_intros.get(i).get("type")%></td>

 		</tr>
		<tr>
	      <td>游玩时间</td>
		 <td><%=list_intros.get(i).get("funtime")%></td>

	  </tr>
	 
	  
	  	<tr>
	      <td>开放时间</td>
		 <td><%=list_intros.get(i).get("opentime")%></td>

	  </tr>
	  </tr>
	  	<tr>
	      <td>门票价格</td>
		 <td><%=list_intros.get(i).get("ticket")%></td>

	  </tr>
  </tbody>
</table>
<div class="introl">

<p><%=list_intros.get(i).get("introl") %><p>

</div>




<%}} %>
</div>



  <%@include file="include/footer.jsp"%>  
