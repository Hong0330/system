<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<!-- Basic -->
		<meta charset="utf-8">

		<title>URS</title>	

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

		<!-- Current Page CSS -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/vendor/circle-flip-slideshow/css/component.css">
		<!-- Skin CSS -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/skins/default.css"> 

		<!-- Theme Custom CSS -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/custom.css">

		<!-- Head Libs -->
		<script src="${pageContext.request.contextPath}/vendor/modernizr/modernizr.min.js"></script>

	</head>
	<body>
		<div class="body">
			<header id="header" class="header-effect-shrink" data-plugin-options="{'stickyEnabled': true, 'stickyEffect': 'shrink', 'stickyEnableOnBoxed': true, 'stickyEnableOnMobile': true, 'stickyChangeLogo': true, 'stickyStartAt': 30, 'stickyHeaderContainerHeight': 70}">
				<div class="header-body">
					<div class="header-container container">
						<div class="header-row">
							<div class="header-column">
								<div class="header-row">
									<div class="header-logo">
										<c:choose>
											<c:when test="${\"M\".equals(type)}">
												<a href="${request.getContextPath()}/dept">
													<img alt="Logo" width="150" height="100" data-sticky-width="82" data-sticky-height="40" src="${pageContext.request.contextPath}/img/icon-logo.png">
												</a>
											</c:when>
											<c:otherwise>
												<a href="${request.getContextPath()}/lockdevice">
													<img alt="Logo" width="150" height="100" data-sticky-width="82" data-sticky-height="40" src="${pageContext.request.contextPath}/img/icon-logo.png">
												</a>
											</c:otherwise>
										</c:choose>
									</div>
								</div>
							</div>
							<div class="header-column justify-content-end">
								<div class="header-row pt-3">
									<nav class="header-nav-top">
										<ul class="nav nav-pills">
											<c:if test="${\"E\".equals(type)}">
												<li class="nav-item nav-item-left-border nav-item-left-border-remove nav-item-left-border-md-show">
													<span class="ws-nowrap">${empName}님</span>
												</li>
												<li class="nav-item nav-item-anim-icon d-none d-md-block">
													<a class="nav-link pl-0" href="${request.getContextPath()}/emp/${memberNo}"><i class="fas fa-angle-right"></i>내 정보 보기</a>
												</li>
											</c:if>
											<li class="nav-item nav-item-anim-icon d-none d-md-block">
												<a class="nav-link" href="${request.getContextPath()}/common/logout"><i class="fas fa-angle-right"></i>로그아웃</a>
											</li>
										</ul>
									</nav>
								</div>
								<div class="header-row">
									<div class="header-nav pt-1">
										<div class="header-nav-main header-nav-main-effect-1 header-nav-main-sub-effect-1">
											<nav class="collapse">
												<ul class="nav nav-pills" id="mainNav">
													<c:choose>
														<c:when test="${\"M\".equals(type)}">
															<li class="dropdown">
																<a class="dropdown-item dropdown-toggle" href="${request.getContextPath()}/dept">학과 관리</a>
															</li>
															<li class="dropdown">
																<a class="dropdown-item dropdown-toggle" href="${request.getContextPath()}/student">학생 관리</a>
															</li>
															<li class="dropdown">
																<a class="dropdown-item dropdown-toggle" href="${request.getContextPath()}/emp">직원 관리</a>
															</li>
														</c:when>
														<c:otherwise>
															<li class="dropdown">
																<a class="dropdown-item dropdown-toggle" href="${request.getContextPath()}/lockdevice">잠금 장치 관리</a>
															</li>
															<li class="dropdown">
																<a class="dropdown-item dropdown-toggle" href="${request.getContextPath()}/rental">대여 관리</a>
															</li>
															<li class="dropdown">
																<a class="dropdown-item dropdown-toggle" href="${request.getContextPath()}/student">학생 조회</a>
															</li>
														</c:otherwise>
													</c:choose>
												</ul>
											</nav>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</header>
			<div class="container py-2">
				<div class="row">
					<div class="col">
						<div class="row">
							<div class="col pb-3">