<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@page import="iolayer.SQLRead"%>
<%@include file="include/header.jsp"%>
<%@include file="include/nav_no.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
    <div class="divider col-sm-12 col-xs-12 col-md-12">
        <div class="header-text"> Restaurant <span>特色美食</span></div>
    </div>

    <div id="exTab2" class="col-md-10 col-lg-10 col-lg-offset-1 col-md-offset-1">
        <div class="tab-content ">
          <div class="tab-pane active" id="1">
          
          
          <c:forEach items="${rests}" var="rest" varStatus="st">
            <div class="blog-event">
              <div class="featured-img">
                <img src="${rest.restImg }" 
	style="width: 200px;height: 170px;" alt="暂无图片">
                
              </div>
              <div class="featured-blog">
                  <h3>${rest.restName }</h3>
                  <p> ${rest.restLocation }</p>

                <span class="j">${ rest.restComment} </span>
                <span class="p">评分：${rest.restPoint }</span>
                <span class="p">价格：${rest.restPrice}</span>
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
