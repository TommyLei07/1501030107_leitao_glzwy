<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="iolayer.SQLRead"%>
<%
	String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">
	<head>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
		<meta charset="utf-8">
  <title>桂林园林植物园多源爬虫系统</title>
		<meta name="generator" content="Bootply" />
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<!-- Latest compiled and minified CSS -->
		<link rel="stylesheet" href="css/bootstrap.min.css">
		<!--[if lt IE 9]>
			<script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script>
		<![endif]-->
		<link href="css/styles.css" rel="stylesheet">
    <link rel="stylesheet" href="css/demo.css" />
    <link rel="stylesheet" href="css/testimonial.css" />
		<link rel="stylesheet" href="css/font-awesome.min.css">
		<link href='http://fonts.googleapis.com/css?family=Poiret+One' rel='stylesheet' type='text/css'>
	</head>
	<body>
  	<div class="header container">

      </div>
  	</div>
		<div class="navbar" role="navigation">

			<div class="collapse navbar-collapse">
       <ul class="nav navbar-nav">
<li class="selected"><a href="index.jsp">主页</a></li>
          <li><a href="Introduction.jsp">景点简介</a></li>
          <li><a href="Restaurant.jsp">特色美食</a></li>
          <li><a href="shopping.jsp">周边购物</a></li>

          <li><a href="Hotel.jsp">酒店住宿</a></li>
          <li><a href="Comment.jsp">游客评价</a></li>
          <li><a href="Map.jsp">如何前往</a></li>
          <li><a href="Weather.jsp">天气一览</a></li>
           <li><a href="Travel.jsp">其他推荐</a></li>
           <li><a href="tongji.jsp">数据统计</a></li>
        </ul>
      </div>
		</div>

		<!-- 轮播 -->
		<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
		  <!-- Indicators -->
		  <ol class="carousel-indicators">
		    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
		    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
		    <li data-target="#carousel-example-generic" data-slide-to="2"></li>
		  </ol>

		  <!-- Wrapper for slides -->
			<!-- 图片轮播 -->
		  <div class="carousel-inner" role="listbox">
		    <div class="item active">
		      <img src="glzwyimgs/1.jpg" alt="" style="width:100%">
		      <div class="carousel-caption">
		        桂林园林植物园
		      </div>
		    </div>
		    <div class="item">
		      <img src="glzwyimgs/2.jpg" alt="" style="width: 100%;">
		      <div class="carousel-caption">
		     桂林植物园
		      </div>
		    </div>
		    <div class="item">
		      <img src="glzwyimgs/1.jpg" alt="" style="width: 100%;">
		      <div class="carousel-caption">
		        桂林园林植物园
		      </div>
		    </div>
		  </div>

		  <!-- 控制前后 -->
			<a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
	 <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>

 </a>
 <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
	 <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>

 </a>
		</div>

		<!-- 轮播结束 -->




		<!-- 控制距离填充 -->
		<div class="header container">

			</div>


    <div class="divider col-sm-12 col-xs-12 col-md-12">
			<!-- sytle.css 20 -->
      <div class="header-text">
				<span>介绍</span>
				Introduction</div>
    </div>
　<%
    	SQLRead test= new SQLRead();
        List<Map<String, Object>> list_comment = (List<Map<String, Object>>) test.getInfo("select*from comment_");//对servlet中的数据的接收
        List<Map<String, Object>> list_intros = (List<Map<String, Object>>) test.getInfo("select*from intros_");//对servlet中的数据的接收
        if (list_intros.size() < 1) {//判断数据库中有没有数据
            out.print("数据库中没有数据");
        } else {	 for (int i = 0; i < list_intros.size(); i++){
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


</div>
<%} %>

			     <div class="divider col-sm-12 col-xs-12 col-md-12">
              <div class="header-text">
								Commends
								<span>评价 </span>
							</div>

            </div>



       <section class="testimonial">
          <div class="col-md-7 testimonial-blog">
             <div id="wrapper">
            <!-- DEMO -->
                <div class="testimonials-slider">
                <%
        for (int i = 0; i < list_comment.size(); i++) {//对接收的数据进行遍历打印
    %>
									<div class="slide">
										<div class="testimonials-carousel-thumbnail"><img width="120" alt="" src="<%=list_comment.get(i).get("Cm_userimg")%>"></div>
											<div class="testimonials-carousel-context"style="height: 165px;">
												<div class="testimonials-name">
													<%=list_comment.get(i).get("Cm_from")%> <span>来自携程网</span>
												</div>
												<div class="testimonials-carousel-content">
													<p><%=list_comment.get(i).get("Cm_content")%></p>
												</div>
													<div class="testimonials-carousel-content" align="right">
													<span><%=list_comment.get(i).get("Cm_point")%></span>
												</div>
											</div>
										</div>		
										
										  <%} }%>			
                    </div>
                 <!-- END DEMO -->
                  <div class="clearfix"></div>
                </div>
              </div>
              <!-- <div class="col-md-4 subscribe-form">
                <div class="subscribe">
                  <h4>Subscribe to Newsletter</h4>
                    <div class="email-field">
                        <form action="http://demo.nrgthemes.com/">
                               <div class="email-input">
                                  <input type="email" placeholder="Enter your email..." required>
                                  <i class="fa fa-check col-green"></i>
                               </div>
                          <button type="submit" class="button-main bg-green">Submit</button>
                        </form>
                          <span>We never spam :)</span>
                    </div>
                  </div>
                </div> -->
              </section>

    <!-- ============FOOTER============= -->
   <footer id="footer">
         <div class="footer-content container">
             <div class="footer-adress text-center col-xs-12 col-sm-4 col-md-4">
                 <h4></h4>
                 <ul class="footer-menus">
						 <li ><a href="index.jsp">主页</a></li>
          <li><a href="Introduction.jsp">景点简介</a></li>
          <li><a href="Restaurant.jsp">特色美食</a></li>
          <li><a href="shopping.jsp">周边购物</a></li>

          <li><a href="Hotel.jsp">酒店住宿</a></li>
          <li><a href="Comment.jsp">游客评价</a></li>
          <li><a href="Map.jsp">如何前往</a></li>
          <li><a href="Weather.jsp">天气一览</a></li>
           <li><a href="Travel.jsp">其他推荐</a></li>
           <li><a href="tognji.jsp">数据统计</a></li>
                 </ul>
             </div>
             <div class="footer-second col-xs-12 col-sm-4 col-md-4">

                <p class="text-center footer-text1">桂林园林植物园</p>
                 <p class="text-center footer-text">Copyright @雷涛 1501030107</p> </div>
             <div class="footer-third col-xs-12 col-sm-4 col-md-4">

             </div>
         </div>
         <div class="move-top-page">
       </div>
     </footer>

	<!-- script references -->
		<script src="js/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
    <script src="js/nav-hover.min.js"></script>
    <script type="text/javascript" src="js/jquery.bxslider.min.js"></script>
    <script type="text/javascript" src="js/main.js"></script>
    <!-- Place in the <head>, after the three links -->
    <script>
     $('.testimonials-slider').bxSlider({
      slideWidth: 800,
      minSlides: 1,
      maxSlides: 1,
      slideMargin: 32,
      auto: true,
      autoControls: true
      });
    </script>
		<script type="text/javascript">
		</script>
	</body>
</html>
