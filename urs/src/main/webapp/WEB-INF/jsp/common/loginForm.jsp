<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		
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
										<a href="${pageContext.request.contextPath}/common/login/form">
											<img alt="Logo" width="150" height="100" data-sticky-width="82" data-sticky-height="40" src="${pageContext.request.contextPath}/img/icon-logo.png">
										</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</header>
		</div>
		
		<form action="/common/login" method="POST" onsubmit="return check(this)">
		<div class="container py-4">

					<div class="row justify-content-center">
						<div class="col-md-6 col-lg-5 mb-5 mb-lg-0">
							<h2 class="font-weight-bold text-5 mb-0">Login</h2> <br />
							<form action="/" id="frmSignIn" method="post" class="needs-validation">
								<div class="form-row">
									<div class="form-group col">
										<label class="text-color-dark text-3">사번<span class="text-color-danger">*</span></label>
											<c:choose>										
												<c:when test="${member.memberNo != 0}">
													<input id="memberNo" type="text" name="memberNo" value="${member.memberNo}" class="form-control form-control-lg text-4" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" required>
												</c:when>
												<c:otherwise>
													<input id="memberNo" type="text" name="memberNo" class="form-control form-control-lg text-4" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" required>
												</c:otherwise>												
											</c:choose>
									</div>
								</div>
								<div class="form-row">
									<div class="form-group col">
										<label class="text-color-dark text-3">비밀번호 <span class="text-color-danger">*</span></label>
										<input id="password" type="password" name="password" class="form-control form-control-lg text-4" required>
									</div>
								</div>
								<div class="form-row justify-content-between">
									<div class="form-group col-md-auto">
										<div class="custom-control custom-checkbox">
											<input type="checkbox" class="custom-control-input" id="remembermeMemberNo" name="rememberMemberNo" />
											<label class="custom-control-label cur-pointer text-2" for="remembermeMemberNo">사번 기억하기</label>
										</div>
									</div>
								</div>
								<div id="error"></div>
								<div class="form-row">
									<div class="form-group col">
										<input type="submit" value="로그인" class="btn btn-dark btn-modern btn-block text-uppercase rounded-0 font-weight-bold text-3 py-3" data-loading-text="Loading..." />
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</form>
			<script>
				function check(form) {
				var xhr = new XMLHttpRequest();
				xhr.onreadystatechange = function() {
					console.log(xhr.readyState);
		
					if (xhr.readyState === xhr.DONE) {
						if (xhr.status === 200 || xhr.status === 201) {
							var row = JSON.parse(xhr.responseText);
							if (row === null) {
								form.submit();
							} else {
								document.getElementById("error").innerHTML = "<font size=\"2em\" color=\"red\">잘못된 로그인 정보입니다.</font>";
							}
						} else {
							console.error(xhr.responseText);
						}
					}
				};
				
				var memberNo = document.getElementById("memberNo").value;
				var password = document.getElementById("password").value;
				
				if (!memberNo) {
					memberNo = "1";
				}
				
				if (!password) {
					password = "!";
				}
				
				xhr.open("GET", "http://localhost/common/check/" + memberNo + "/" + password, true);
				xhr.send();
				
				return false;
			};
		</script>
	</body>