<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<%@ page import = "vo.Customer" %>    
<%@ page import = "vo.Employee" %>  
      
<%

	if(session.getAttribute("user") == null) { // 로긴한 애가 아니면
		response.sendRedirect(request.getContextPath()+"/loginForm.jsp");
			
		return;
	} 

	
		
%>       
    
    
    
    
    
    
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>uiCookies:Haus &mdash; Free Bootstrap Theme, Free Responsive Bootstrap Website Template</title>
    <meta name="description" content="Free Bootstrap Theme by uicookies.com">
    <meta name="keywords" content="free website templates, free bootstrap themes, free template, free bootstrap, free website template">
    
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400" rel="stylesheet">
    <link rel="stylesheet" href="css/styles-merged.css">
    <link rel="stylesheet" href="css/style.min.css">
    <link rel="stylesheet" href="css/custom.css">

    <!--[if lt IE 9]>
      <script src="js/vendor/html5shiv.min.js"></script>
      <script src="js/vendor/respond.min.js"></script>
    <![endif]-->
  </head>
  <body>

  <!-- START: header -->
  
  <div class="probootstrap-loader"></div>

  <header role="banner" class="probootstrap-header">
    <div class="container">
        <a href="index.html" class="probootstrap-logo">GDJ50SHOP<span>.</span></a>
        
        <a href="#" class="probootstrap-burger-menu visible-xs" ><i>Menu</i></a>
        <div class="mobile-menu-overlay"></div>

        <nav role="navigation" class="probootstrap-nav hidden-xs">
          <ul class="probootstrap-main-nav">
            <li class="active"><a href="index.html">Home</a></li>
            <li><a href="properties.html">Properties</a></li>
            <li><a href="agents.html">Agents</a></li>
            <li><a href="about.html">About</a></li>
            <li><a href="contact.jsp">Contact</a></li>
            <li><a href="loginForm.jsp">Login</a></li>
            <li><a href="ourindex.jsp">Info</a></li>
          </ul>
          <div class="extra-text visible-xs"> 
            <a href="#" class="probootstrap-burger-menu"><i>Menu</i></a>
            <h5>Address</h5>
            <p>198 West 21th Street, Suite 721 New York NY 10016</p>
            <h5>Connect</h5>
            <ul class="social-buttons">
              <li><a href="#"><i class="icon-twitter"></i></a></li>
              <li><a href="#"><i class="icon-facebook2"></i></a></li>
              <li><a href="#"><i class="icon-instagram2"></i></a></li>
            </ul>
          </div>
        </nav>
    </div>
  </header>
  <!-- END: header -->
  <section class="probootstrap-slider flexslider2 page-inner">
    <div class="overlay"></div>
    <div class="probootstrap-wrap-banner">
      <div class="container">
        <div class="row">
          <div class="col-md-8">

            <div class="page-title probootstrap-animate">
              <div class="probootstrap-breadcrumbs">
                <a href="#">Home</a><span>Info</span>
              </div>
              <h1>Info</h1>
            </div>

          </div>
        </div>
      </div>
    </div>

  </section>
  <!-- END: slider  -->

  <section class="probootstrap-section">
    <div class="container">
      <div class="row heading">
        <div class="col-md-12"><h2 class="mt0 mb50 text-center">Info</h2></div>
      </div>
   
      <div class="row">
        <div class="col-md-6">
          <h2><%=session.getAttribute("user") %> / <%=session.getAttribute("id") %></h2>
          <p><%=session.getAttribute("name") %>님 환영합니다!</p>
		<br>
		<a href="<%=request.getContextPath()%>/haus/haus/logout.jsp"> 로그아웃 </a>/
		<a href="<%=request.getContextPath()%>/haus/haus/remove<%=session.getAttribute("user")%>Form.jsp"> 탈퇴 </a>
      </div>
    </div>
    </div>
  </section>


  

  

  <footer class="probootstrap-footer probootstrap-bg" style="background-image: url(img/slider_3.jpg)">
    <div class="container">
      <div class="row mb60">
        <div class="col-md-3">
          <div class="probootstrap-footer-widget">
            <h4 class="heading">About GDJ50SHOP.</h4>
            <p>Our second project which webpage type is "E-Commerce". </p>
            <p><a href="#">Read more...</a></p>
          </div> 
        </div>
        <div class="col-md-3">
          <div class="probootstrap-footer-widget probootstrap-link-wrap">
            <h4 class="heading">Quick Links</h4>
            <ul class="stack-link">
              <li><a href="#">Property Listing</a></li>
              <li><a href="#">Rent Properties</a></li>
              <li><a href="#">Sell Properties</a></li>
              <li><a href="#">Agents</a></li>
              <li><a href="#">Testimonial</a></li>
            </ul>
          </div>
        </div>
  
      </div>
      <div class="row copyright">
        <div class="col-md-6">
          <div class="probootstrap-footer-widget">
            <p>&copy; 2017 <a href="https://uicookies.com/">uiCookies:Haus</a>. Designed by <a href="https://uicookies.com/">uicookies.com</a> <br> Demo Photos from <a href="https://pixabay.com/">Pixabay</a> &amp; <a href="https://unsplash.com/">Unsplash</a></p>
          </div>
        </div>
        <div class="col-md-6">
          <div class="probootstrap-footer-widget right">
            <ul class="probootstrap-footer-social">
              <li><a href="#"><i class="icon-twitter"></i></a></li>
              <li><a href="#"><i class="icon-facebook"></i></a></li>
              <li><a href="#"><i class="icon-instagram2"></i></a></li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  </footer>

  <div class="gototop js-top">
    <a href="#" class="js-gotop"><i class="icon-chevron-thin-up"></i></a>
  </div>
  

  <script src="js/scripts.min.js"></script>
  <script src="js/main.min.js"></script>

  <!-- Google Map -->
  <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"></script>
  <script src="js/google-map.js"></script>

  <script src="js/custom.js"></script>

  </body>
</html>