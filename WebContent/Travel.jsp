<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@page import="iolayer.SQLRead"%>
<%@include file="include/header.jsp"%>
<%@include file="include/nav_no.jsp"%>

<%
	SQLRead test= new SQLRead();
    List<Map<String, Object>> list_travel = (List<Map<String, Object>>) test.getInfo("select*from travel_");//对servlet中的数据的接收
   
    if (list_travel.size() < 1) {//判断数据库中有没有数据
        out.print("数据库中没有数据");
    } else {
%>

        <div class="divider col-sm-12 col-xs-12 col-md-12">
            <div class="header-text">Others <span>其他推荐</span></div>
        </div>

        <section id="blog-isotope" class="subpage bg-white">
        <div class="overlay-light">
            <div id="content" class="container">
                <!-- posts -->
                <div class="posts">
                    <!-- post 1 -->
                    
                        <%
	 for (int i = 0; i < list_travel.size(); i++){
	 String comm=list_travel.get(i).get("travel_comment").toString().substring(0,100);
	 
	 
	 
%>   
                    
                   <div class="post item col-md-4 col-xs-12 col-sm-4">
                           <div class="panel">
                                  <div class="panel-header">
                                    <a href="<%=list_travel.get(i).get("travel_url")%>"> <img src="<%=list_travel.get(i).get("travel_img") %>" class="img-responsive"  alt="image"/></a>
                                  </div>
                                  <div class="panel-body">
                                       <h3 class="post-title"><a href="<%=list_travel.get(i).get("travel_url")%>"><%=list_travel.get(i).get("travel_name") %></a></h3>

                <p><%=comm %></p>

  				<button style="margin-right:50px" type="button" class="btn btn-default" data-toggle="tooltip" data-placement="right" title="<%=list_travel.get(i).get("travel_comment")%>">查看详细</button>
                                  </div>
                                  <div class="panel-footer">
                                    <div class="pull-left">
                                      <a class="btn btn-black btn-xs" href="<%=list_travel.get(i).get("travel_url")%>"><span class="read-more"><%=list_travel.get(i).get("travel_price") %></span></a>
                                </div>
                                    <div class="pull-right">
                                        <i class="fa fa-envelope"></i>
                                        <small><%=list_travel.get(i).get("travel_point") %></small>
                                    </div>
                                    <div class="clearfix"></div>
                                  </div>
                            </div>
                    </div>
                    <%}} %>
                  </div>
            </div>
         </div><!-- /end overlay -->
      </section><!--=== / END blog ===-->



  <%@include file="include/footer.jsp"%>  