<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@page import="iolayer.SQLRead"%>
<%@include file="include/header.jsp"%>
<%@include file="include/nav_no.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

   <div class="divider col-sm-12 col-xs-12 col-md-12">
        <div class="header-text"> Comments <span>游客评价</span></div>
    </div>

    <div id="exTab2" class="col-md-10 col-lg-10 col-lg-offset-1 col-md-offset-1">
        <div class="tab-content ">
          <div class="tab-pane active" id="1">
          
	<c:forEach items="${comments}" var="comment" varStatus="st">
            <div class="blog-event">
              <div class="featured-img">
                <img src="${comment.cmUserimg }" width="150" alt="暂无图片">
              </div>
              <div class="featured-blog">
                  <h3>${comment.cmFrom }</h3>
                  <p> ${comment.cmContent } </p>

               
         
                <span class="p">觉得有用${comment.cmUseful}</span>
                 <span class="j">${comment.cmPoint } </span>
              </div>
            </div>
         </c:forEach>

          </div>


              </div>
              <nav aria-label="Page navigation" style="margin-left: 40%;margin-left: 40%">
                <ul class="pagination">
                <li><a href="?start=0">首  页</a></li>
            <li><a href="?start=${pre}">上一页</a></li>
            <li><a href="?start=${next}">下一页</a></li>
            <li><a href="?start=${last}">末  页</a></li>
                  
                </ul>
              </nav>
            </div>

  <%@include file="include/footer.jsp"%>  