<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="/WEB-INF/jsp/layout/top.jsp" %>

	<h4 class="font-weight-bold text-uppercase text-4 mb-3 col-lg-4">대여 정보</h4>
	<div class="float-left col-lg-4">
		<input id="studentNo" type="text" name="studentNo" placeholder="검색할 학번을 입력해주세요." class="form-control"  />
	</div>
	<div class="float-left">
		<a><input id="search" type="button" value="검색" class="btn btn-outline btn-primary btn-sm mb-2 form-control" /></a>
	</div>
	<div class="divider divider-primary">
		<hr class="divider divider-primary divider-small">
	</div>
	<div id="list"></div>
	
<script>
	document.getElementById("search").addEventListener("click", search);
	
	function search() {
		var xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function() {
			console.log(xhr.readyState);
			
			if (xhr.readyState === xhr.DONE) {
				if (xhr.status === 200 || xhr.status === 201) {	
					var rows = JSON.parse(xhr.responseText);
					if (rows.length === 0) {
						document.getElementById("list").innerHTML = "검색결과가 없습니다";
					} else {
						var html = 
								"<table class=\"table table-bordered\">";
						html += "    <tr>";
						html += "        <th>번호</th>";
						html += "        <th>학번</th>";
						html += "        <th>대여 일자</th>";
						html += "        <th>반납 일자</th>";
						html += "        <th>상태</th>";
						html += "    </tr>";
						for (var i = 0; i < rows.length; i++) {
							var rentalDate = new Date(rows[i].rentalDate);
							var returnDate = new Date(rows[i].returnDate);
							html += "    <tr>";
							html += "        <td>" + (i + 1) + "</td>";
							html += "        <td><a href=\"/rental/" + rows[i].no + "\">" + rows[i].studentNo + "</a></td>";
							html += "        <td>" + rentalDate.getFullYear() + "-" + (rentalDate.getMonth() + 1) + "-" + rentalDate.getDate() + "</td>";
							html += "        <td>";
							if (null < returnDate) {
								html += returnDate.getFullYear() + "-" + (returnDate.getMonth() + 1) + "-" + returnDate.getDate();
							}
							html += "        </td>";
							html += "        <td>";
							if (rows[i].status === "Y") {
								html += "반납";
							} else if (rows[i].status === "N") {
								if (dateDiff(rentalDate, new Date()) > 2) {
									html += "연체";
								} else {
									html += "대여 중";
								}
							} else {
								html += "파손";
							}
							html += "        </td>";	
							html += "    <tr>";
						}
						html += "</table>";
						
						document.getElementById("list").innerHTML = html;
					}
				} else {
					console.error(xhr.responseText);
				}
			}
		};
		
		var studentNo = document.getElementById("studentNo").value;
		if (!studentNo) {
			studentNo = 0;
		}
		xhr.open("GET", "http://localhost/rental/search/" + studentNo , true);
		xhr.send();
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
</script>

<%@ include file="/WEB-INF/jsp/layout/bottom.jsp" %>