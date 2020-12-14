<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="/WEB-INF/jsp/layout/top.jsp" %>

						<div class="row justify-content-center">
							<div class="card border-width-3 border-radius-0 border-color-hover-dark mb-4 col-lg-6">
								<div class="card-body">
									<h4 class="font-weight-bold text-uppercase text-4 mb-3">대여 정보</h4>
									<table class="shop_table cart-totals mb-0">
										<tbody>
											<tr>
												<td>
													<strong class="d-block text-color-dark line-height-1 font-weight-semibold">잠금 장치 일련번호<span class="product-qty"></span></strong>
												</td>
												<td class="text-right align-top">
													<span class="d-block text-color-dark line-height-1 font-weight-semibold"><c:out value="${row.lockDeviceNo}" /></span>
												</td>
											</tr>
											<tr>
												<td>
													<strong class="d-block text-color-dark line-height-1 font-weight-semibold">위치<span class="product-qty"></span></strong>
												</td>
												<td class="text-right align-top">
													<span class="d-block text-color-dark line-height-1 font-weight-semibold"><c:out value="${row.location}" /></span>
												</td>
											</tr>
											<tr>
												<td>
													<strong class="d-block text-color-dark line-height-1 font-weight-semibold">학번<span class="product-qty"></span></strong>
												</td>
												<td class="text-right align-top">
													<span class="d-block text-color-dark line-height-1 font-weight-semibold"><c:out value="${row.studentNo}" /></span>
												</td>
											</tr>
											<tr>
												<td>
													<strong class="d-block text-color-dark line-height-1 font-weight-semibold">이름<span class="product-qty"></span></strong>
												</td>
												<td class="text-right align-top">
													<span class="d-block text-color-dark line-height-1 font-weight-semibold"><c:out value="${row.name}" /></span>
												</td>
											</tr>
											<tr>
												<td>
													<strong class="d-block text-color-dark line-height-1 font-weight-semibold">연락처<span class="product-qty"></span></strong>
												</td>
												<td class="text-right align-top">
													<span class="d-block text-color-dark line-height-1 font-weight-semibold"><c:out value="${row.phone}" /></span>
												</td>
											</tr>
											<tr>
												<td>
													<strong class="d-block text-color-dark line-height-1 font-weight-semibold">상태<span class="product-qty"></span></strong>
												</td>
												<td class="text-right align-top">
													<span class="d-block text-color-dark line-height-1 font-weight-semibold"><div id="status"></div></span>
												</td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
						</div>
						<div class="row justify-content-left">
							<div class="col-lg-3 float-left">
							</div>
								<div class="card border-width-3 border-radius-0 border-color-hover-dark mb-4 col-lg-3 float-left">
									<h4 class="font-weight-bold text-uppercase text-4 mb-3">대여 정보</h4>
									<table class="shop_table cart-totals mb-0">
										<tbody>
											<tr>
												<td>
													<strong class="d-block text-color-dark line-height-1 font-weight-semibold">대여 일자<span class="product-qty"></span></strong>
												</td>
												<td class="text-right align-top">
													<span id="rentalDate" class="d-block text-color-dark line-height-1 font-weight-semibold"><fmt:formatDate value="${row.rentalDate}" pattern="yyyy-MM-dd" /></span>
												</td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
							<c:if test="${row.returnDate != null}">
								<div class="row justify-content-right">	
									<div class="col-lg-6 float-left">
									</div>
									<div class="card border-width-3 border-radius-0 border-color-hover-dark mb-4 col-lg-3 float-right">
										<h4 class="font-weight-bold text-uppercase text-4 mb-3">반납 정보</h4>
										<table class="shop_table cart-totals mb-0">
											<tbody>
												<tr>
													<td>
														<strong class="d-block text-color-dark line-height-1 font-weight-semibold">반납 일자<span class="product-qty"></span></strong>
													</td>
													<td class="text-right align-top">
														<span class="d-block text-color-dark line-height-1 font-weight-semibold"><fmt:formatDate value="${row.returnDate}" pattern="yyyy-MM-dd" /></span>
													</td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
							</c:if>
						<div class="row justify-content-center">
							<div id="error"></div>
							<br>
						</div>
						<div class="row justify-content-center">
							<div class="col-lg-5">
								<a href="${request.getContextPath()}/rental/${row.no}/form"><input type="button" value="수정" class="btn btn-primary btn-modern float-right" /></a>
							</div>
							<form action="${request.getContextPath()}/rental/${row.no}" method="post" onsubmit="return check(this)">
								<input type="hidden" name="_method" value="delete" />
								<input type="submit" value="삭제" class="btn btn-primary btn-modern float-right" />
							</form>
						</div>
<script>
	var status = "${row.status}";
	if (status === "Y") {
		document.getElementById("status").innerHTML = "반납";
	} else if (status === "N") {
		var rentalDate = new Date(document.getElementById("rentalDate").innerHTML);
		if (dateDiff(rentalDate, new Date()) > 2) {
			document.getElementById("status").innerHTML = "연체";
		} else {
			document.getElementById("status").innerHTML = "대여 중";
		}
	} else {
		document.getElementById("status").innerHTML = "파손";
	};
	
	function dateDiff(_date1, _date2) {
	    var diffDate_1 = new Date(_date1);
	    var diffDate_2 = new Date(_date2);
	 
	    diffDate_1 =new Date(diffDate_1.getFullYear(), diffDate_1.getMonth()+1, diffDate_1.getDate());
	    diffDate_2 =new Date(diffDate_2.getFullYear(), diffDate_2.getMonth()+1, diffDate_2.getDate());
	 
	    var diff = Math.abs(diffDate_2.getTime() - diffDate_1.getTime());
	    diff = Math.ceil(diff / (1000 * 3600 * 24));
	 
	    return diff;
	};
	
	function check(form) {
		var xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function() {
			console.log(xhr.readyState);

			if (xhr.readyState === xhr.DONE) {
				if (xhr.status === 200 || xhr.status === 201) {
					var row = JSON.parse(xhr.responseText);
					if (row === null || row.status === "Y") {
						form.submit();
					} else {
						document.getElementById("error").innerHTML = "<font size=\"2em\" color=\"red\">미반납 혹은 파손상태의 대여 정보가 존재합니다.</font>";
					}
				} else {
					console.error(xhr.responseText);
				}
			}
		};
		
		var no = "${row.no}";
		if (!no) {
			no = 0;
		}
		xhr.open("GET", "http://localhost/rental/check/" + no, true);
		xhr.send();
		
		return false;
	};
</script>

<%@ include file="/WEB-INF/jsp/layout/bottom.jsp" %>