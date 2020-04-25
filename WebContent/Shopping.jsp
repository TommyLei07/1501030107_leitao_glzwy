<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="iolayer.SQLRead"%>

<%@page import="iolayer.SQLRead"%>
<%@include file="include/header.jsp"%>
<%@include file="include/nav_no.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

    
    
        <div class="divider col-sm-12 col-xs-12 col-md-12">
          <div class="header-text"><span>特产</span> Shopping</div>
        </div>


        <section id="clients">

    <!-- Team Inner -->
          <div class="inner team">

      <!-- Header -->


      <!-- Members -->
      <div class="team-members inner-details">

        <!-- Member -->
        <c:forEach items="${shops}" var="shop" varStatus="st">
        <div class="col-xs-4 member animated" data-animation="fadeInUp" data-animation-delay="500">
          <div class="member-inner">
            <!-- Team Member Image -->
            <a class="team-image">
              <!-- Img -->
              <img src="${ shop.shop_img}" alt="" />
            </a>
            <div class="member-details">
              <div class="member-details-inner">
                <!-- Name -->
                <h2 class="member-name light">${shop.shop_name } </h2>
                <!-- Description -->
                <p class="member-description">${shop.shop_location }</p>
				<p class="member-description">${shop.shop_recommend }</p>
                <!-- Socials -->
                <div class="socials">
             
                  <a href="${shop.shop_url }"><i class="fa fa-link"></i></a>
                </div><!-- End Socials -->
              </div> <!-- End Detail Inner -->
            </div><!-- End Details -->
          </div> <!-- End Member Inner -->
        </div><!-- End Member -->
        </c:forEach>


      </div><!-- End Members -->
    </div><!-- End Team Inner -->
      <nav aria-label="Page navigation" style="margin-left: 40%;margin-left: 40%">
                <ul class="pagination">
                <li><a href="?start=0">首  页</a></li>
            <li><a href="?start=${pre}">上一页</a></li>
            <li><a href="?start=${next}">下一页</a></li>
            <li><a href="?start=${last}">末  页</a></li>
                  
                </ul>
              </nav>
  </section><!-- End Team Section -->

  <%@include file="include/footer.jsp"%>  