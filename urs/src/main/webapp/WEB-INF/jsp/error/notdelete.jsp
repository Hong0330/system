<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<!-- Basic -->
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">	

		<title>500 - Unexpected Error</title>	

		<meta name="keywords" content="HTML5 Template" />
		<meta name="description" content="Porto - Responsive HTML5 Template">
		<meta name="author" content="okler.net">

		<!-- Favicon -->
		<link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icons/icons8-umbrella-100.png" type="image/x-icon" />
		<link rel="apple-touch-icon" href="${pageContext.request.contextPath}/img/apple-touch-icon.png">

		<!-- Mobile Metas -->
		<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1.0, shrink-to-fit=no">

		<!-- Web Fonts  -->
		<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700,800%7CShadows+Into+Light&display=swap" rel="stylesheet" type="text/css">

		<!-- Vendor CSS -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/vendor/bootstrap/css/bootstrap.min.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/vendor/fontawesome-free/css/all.min.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/vendor/animate/animate.compat.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/vendor/simple-line-icons/css/simple-line-icons.min.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/vendor/owl.carousel/assets/owl.carousel.min.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/vendor/owl.carousel/assets/owl.theme.default.min.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/vendor/magnific-popup/magnific-popup.min.css">

		<!-- Theme CSS -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/theme.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/theme-elements.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/theme-blog.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/theme-shop.css">


		<!-- Skin CSS -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/skins/default.css"> 

		<!-- Theme Custom CSS -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/custom.css">

		<!-- Head Libs -->
		<script src="${pageContext.request.contextPath}/vendor/modernizr/modernizr.min.js"></script>

	</head>
<body>

	<div class="body coming-soon">
		<div role="main" class="main" style="min-height: calc(100vh - 393px);">
			<div class="container">
				<div class="row mt-5">
					<div class="col text-center">
						<div class="header-logo">
							<c:choose>
								<c:when test="${\"M\".equals(type)}">
									<a href="${request.getContextPath()}/dept">
										<img alt="Logo" width="150" height="100" data-sticky-width="82" data-sticky-height="40" src="${pageContext.request.contextPath}/img/icons/icons8-lost-and-found-80.png">
									</a>
								</c:when>
								<c:otherwise>
									<a href="${request.getContextPath()}/lockdevice">
										<img alt="Logo" width="150" height="100" data-sticky-width="82" data-sticky-height="40" src="${pageContext.request.contextPath}/img/icons/icons8-lost-and-found-80.png">
									</a>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col">
						<hr class="solid my-5">
					</div>
				</div>
				<section class="http-error py-0">
					<div class="row justify-content-center py-3">
						<div class="col-6 text-center">
							<div class="http-error-main">
								<h2 class="mb-0">DELETE ERROR!</h2>
								<p class="text-3 my-4">삭제 도중 이상이 발생 하였습니다.</p>
							</div>
							<c:choose>
								<c:when test="${\"M\".equals(type)}">
									<a href="${request.getContextPath()}/dept" class="btn btn-primary btn-rounded btn-xl font-weight-semibold text-2 px-4 py-3 mt-1 mb-4">
										<i class="fas fa-angle-left pr-3"></i>메인화면으로 가기
									</a>
								</c:when>
								<c:otherwise>
									<a href="${request.getContextPath()}/lockdevice" class="btn btn-primary btn-rounded btn-xl font-weight-semibold text-2 px-4 py-3 mt-1 mb-4">
										<i class="fas fa-angle-left pr-3"></i>메인화면으로 가기
									</a>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
				</section>
			</div>
		</div>

		<footer id="footer">
			<div class="footer-copyright footer-copyright-style-2">
				<div class="container py-2">
					<div class="row py-4">
						<div class="col-md-4 d-flex align-items-center justify-content-center justify-content-md-start mb-2 mb-lg-0">
							<p>Hong, Sung, Goo</p>
						</div>
					</div>
				</div>
			</div>
		</footer>
	</div>

	<!-- Vendor -->
	<script src="${pageContext.request.contextPath}/vendor/jquery/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/vendor/jquery.appear/jquery.appear.min.js"></script>
	<script src="${pageContext.request.contextPath}/vendor/jquery.easing/jquery.easing.min.js"></script>
	<script src="${pageContext.request.contextPath}/vendor/jquery.cookie/jquery.cookie.min.js"></script>
	<script src="${pageContext.request.contextPath}/vendor/popper/umd/popper.min.js"></script>
	<script src="${pageContext.request.contextPath}/vendor/bootstrap/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/vendor/common/common.min.js"></script>
	<script src="${pageContext.request.contextPath}/vendor/jquery.validation/jquery.validate.min.js"></script>
	<script src="${pageContext.request.contextPath}/vendor/jquery.easy-pie-chart/jquery.easypiechart.min.js"></script>
	<script src="${pageContext.request.contextPath}/vendor/jquery.gmap/jquery.gmap.min.js"></script>
	<script src="${pageContext.request.contextPath}/vendor/jquery.lazyload/jquery.lazyload.min.js"></script>
	<script src="${pageContext.request.contextPath}/vendor/isotope/jquery.isotope.min.js"></script>
	<script src="${pageContext.request.contextPath}/vendor/owl.carousel/owl.carousel.min.js"></script>
	<script src="${pageContext.request.contextPath}/vendor/magnific-popup/jquery.magnific-popup.min.js"></script>
	<script src="${pageContext.request.contextPath}/vendor/vide/jquery.vide.min.js"></script>
	<script src="${pageContext.request.contextPath}/vendor/vivus/vivus.min.js"></script>

	<!-- Theme Base, Components and Settings -->
	<script src="${pageContext.request.contextPath}/js/theme.js"></script>

	<!-- Theme Custom -->
	<script src="${pageContext.request.contextPath}/js/custom.js"></script>


	<!-- Theme Initialization Files -->
	<script src="${pageContext.request.contextPath}/js/theme.init.js"></script>

</body>
</html>