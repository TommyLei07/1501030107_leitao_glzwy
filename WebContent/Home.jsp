
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html class="noIE" lang="en-US">
<!--<![endif]-->

<head>
	<title>桂林园林植物园</title>

	<!-- meta -->
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale = 1.0, maximum-scale=1.0, user-scalable=no" />

	<!-- google fonts -->
	<link rel='stylesheet' href='http://fonts.googleapis.com/css?family=PT+Sans'>
	<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Droid+Serif:regular,bold" />
	<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Alegreya+Sans:regular,italic,bold,bolditalic" />
	<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Nixie+One:regular,italic,bold,bolditalic" />
	<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Alegreya+SC:regular,italic,bold,bolditalic" />

	<!-- css -->
	<link rel="stylesheet" href="assets/css/bootstrap.min.css">
	<link rel="stylesheet" href="assets/css/font-awesome.min.css">
	<link rel="stylesheet" href="assets/css/style.css" media="screen" />


</head>

<body>
	<div id="drawer-right">
		<div class="cross text-right">
			<a class="toggleDrawer" href="#">
				<i class="fa fa-times-circle fa-2x"></i>
			</a>
		</div>
		<h2>Navigate</h2>
		<nav>
			<ul class="nav nav-pills nav-stacked">
				<li>
					<a href="#wrapper">
						<i class="fa fa-home"></i> 主页</a>
				</li>
				<li>
					<a href="#Introductions">
						<i class="fa fa-bookmark"></i>景点简介</a>
				</li>
				<li>
					<a href="#hotel">
						<i class="fa fa-tasks"></i> 酒店住宿</a>
				</li>
				<li>
					<a href="#Restaurant">
						<i class="fa fa-wordpress"></i> 特色美食</a>
				</li>
				<li>
					<a href="#external">
						<i class="fa fa-humbs-up"></i>当地天气</a>
				</li>
				<li>
					<a href="#external">
						<i class="fa fa-phone-square"></i>交通路线</a>
				</li>
				<li>
					<a href="#external">
						<i class="fa fa-phone-square"></i>数据统计</a>
				</li>
				<li>
					<a href="#Recommendations">
						<i class="fa fa-phone-square"></i>其他推荐</a>
				</li>
				<li>
					<a href="#shop">
						<i class="fa fa-phone-square"></i>当地特产</a>
				</li>
				<li>
					<a href="#Comment">
						<i class="fa fa-phone-square"></i>游客评价</a>
				</li>
			</ul>
		</nav>

	</div>
	<!-- #drawer-right -->

	<div id="wrapper">

		<div id="header" class="content-block header-wrapper">
			<div class="header-wrapper-inner">
				<section class="top clearfix">
					<div class="pull-left">
						<h1>
							<a class="logo" href="index.html">Welcome</a>
						</h1>
					</div>
					<div class="pull-right">
						<a class="toggleDrawer" href="#">
							<i class="fa fa-bars fa-2x"></i>
						</a>
					</div>
				</section>
				<section class="center">
					<div class="slogan">
						桂&nbsp;林&nbsp;植&nbsp;物&nbsp;园
					</div>
					<div class="secondary-slogan">
						多源爬虫系统
					</div>
				</section>
				<section class="bottom">
					<a id="scrollToContent" href="#">
						<img src="assets/images/arrow_down.png">
					</a>
				</section>
			</div>
		</div>
		<!-- header -->

		<div class="content-block parallax" id="Introductions">
			<div class="container text-center">
				<h1>Introductions</h1>
				<a href="Introduction.jsp" class="btn btn-o-white btn-lg">查看详细介绍</a>
			</div>
		</div>
		<!-- #parallax -->


		<div class="content-block" id="hotel">
			<div class="container">
				<header class="block-heading cleafix">
					<a href="/1501030107_leitao_glzwy_2/Hotel" class="btn btn-o btn-lg pull-right">查看更多</a>
					<h1>周边住宿</h1>
					<p>Take a look at some of our recent products</p>
				</header>
				<section class="block-body">
					<div class="row">
				<c:forEach items="${hotels}" var="hotel" varStatus="st">
							<div class="col-sm-4">

							<a href="" class="recent-work" style="background-image:url(https:${hotel.hotelImg})">

								<span class="btn btn-o-white">${hotel.hotelName}</span>
							</a>

						</div>
				</c:forEach>
				
					</div>

				</section>
			</div>
		</div>
		<!-- #portfolio -->


		<!-- ForTest -->
		<div class="content-block" id="Restaurant">
			<div class="container">
				<header class="block-heading cleafix">
					<a href="/1501030107_leitao_glzwy_2/Restaurant" class="btn btn-o btn-lg pull-right">查看更多</a>
					<h1>特色美食</h1> 

				</header>
				<section class="block-body">
					<div class="row">
					<c:forEach items="${rests}" var="rest" varStatus="st">
						<div class="col-sm-4">
							<a href="#" class="recent-work" style="background-image:url(${rest.restImg })">
								<span class="btn btn-o-white">${rest.restName }</span>
							</a>
						</div>
					</c:forEach>
			
					</div>

				</section>
			</div>
		</div>

		<div class="content-block parallax" id="external">
			<div class="container text-center">
				<header class="block-heading cleafix">
					<h1>Our Services</h1>
					<p>A little about what we do</p>
				</header>
				<section class="block-body">
					<div class="row">
						<div class="col-md-4">
							<div class="service">
								<i class="fa fa-cloud"></i>
								<a href="Weather.jsp">
									<h2 class="ser_h">当地天气</h2>
								</a>
								<p></p>
							</div>
						</div>
						<div class="col-md-4">
							<div class="service">
								<i class="fa fa-bus"></i>
								<a href="Map.jsp">
									<h2 class="ser_h">交通路线</h2>
								</a>
								<p></p>
							</div>
						</div>
						<div class="col-md-4">
							<div class="service">
								<i class="fa fa-bar-chart"></i>
								<a href="tongji.jsp">
									<h2 class="ser_h">数据统计</h2>
								</a>
								<p></p>
							</div>
						</div>
					</div>
				</section>
			</div>
		</div>
		<!-- #services -->


		<div class="content-block" id="blog">
			<div class="container">
				<header class="block-heading cleafix">
					<a href="Travel.jsp" class="btn btn-o btn-lg pull-right">View All</a>
					<h1>其他推荐</h1>
					<p>Recommendations</p>
				</header>
				<section class="block-body">
					<div class="row">
					
						<c:forEach items="${travels}" var="travel" varStatus="st">
						<div class="col-sm-4 blog-post">
							<img src="${travel.travel_img}">
							<a href="${travel.travel_url }">
								<h2>${travel.travel_name }</h2>
							</a>
							<div class="date">${travel.travel_point }</div>
							<p>${travel.travel_comment }</p>
							<a href="${travel.travel_url }">了解更多</a>
						</div>
						</c:forEach>
					</div>
				</section>
			</div>
		</div>
		<!-- #blog -->
		<div class="content-block" id="shop">
			<div class="container">
				<header class="block-heading cleafix">
					<a href="/1501030107_leitao_glzwy_2/Shop" class="btn btn-o btn-lg pull-right">查看更多</a>
					<h1>当地特产</h1>
					<p>Shopping</p>
				</header>
				<section class="block-body">
					<div class="row">
				<c:forEach items="${shops}" var="shop" varStatus="st">
							<div class="col-sm-4">

							<a href="" class="recent-work" style="background-image:url(${ shop.shop_img})">

								<span class="btn btn-o-white">${shop.shop_name }</span>
							</a>

						</div>
				</c:forEach>
				
					</div>

				</section>
			</div>
		</div>





		<div class="content-block" id="Comment">
			<div class="container">
				<header class="block-heading cleafix">
					<a href="/1501030107_leitao_glzwy_2/Comment" class="btn btn-o btn-lg pull-right">查看更多</a>
					<h1>游客评价</h1>
					<p>Comment</p>
				</header>
				<section class="block-body">
					<div class="row">
					<c:forEach items="${comments}" var="comment" varStatus="st">
						<div class="col-md-4">
							<div class="testimonial">
								<img src="${comment.cmUserimg }">
								<p>${comment.cmContent }</p>
								<strong>${comment.cmFrom }</strong>
								<br/>

							</div>
						</div>
					</c:forEach>
			
					</div>
				</section>
			</div>
		</div>
		<!-- /#testimonials -->




		<div class="content-block" id="footer">
			<div class="container">
				<div class="row">
					<div class="col-xs-6"></div>
					<div class="col-xs-6 text-right">桂林园林植物园多源爬虫系统
						<a href="" target="_blank" title=""></a>
						<a href=""></a>
						<a href=""></a>
						- 雷涛 1501030107
						<a href=""></a>
					</div>
				</div>
			</div>
		</div>
		<!-- #footer -->


	</div>
	<!--/#wrapper-->




	<script src="assets/js/jquery-2.1.3.min.js"></script>
	<script src="assets/js/jquery-migrate-1.2.1.min.js"></script>
	<script src="assets/js/bootstrap.min.js"></script>
	<script src="assets/js/jquery.actual.min.js"></script>
	<script src="assets/js/jquery.scrollTo.min.js"></script>
	<script src="assets/js/script.js"></script>

</body>

</html>