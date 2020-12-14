<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="/WEB-INF/jsp/layout/top.jsp" %>

	<h4 class="font-weight-bold text-uppercase text-4 mb-3 col-lg-4">학과 정보</h4>
	<div class="float-left col-lg-4">
		<input id="name" type="text" name="name" placeholder="검색할 학과명을 입력해주세요." class="form-control" />
	</div>
	<div class="float-left">
		<a><input id="search" type="button" value="검색" class="btn btn-outline btn-primary btn-sm mb-2 form-control" /></a>
	</div>
	<div class="float-right">
		<a href="${request.getContextPath()}/dept/form"><input type="button" value="등록" class="btn btn-outline btn-primary btn-sm mb-2 form-control" /></a>
	</div>
	<div class="divider divider-primary">
		<hr class="divider divider-primary divider-small">
	</div>
	<p id="list"></p>

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
					    html += "    <thead>";
						html += "        <tr>";
						html += "            <th>번호</th>";
						html += "            <th>학과 명</th>";
						html += "            <th>전화번호</th>";
						html += "            <th>위치</th>";
						html += "        </tr>";
						html += "    </thead>";
						
						for (var i = 0; i < rows.length; i++) {
							html += "    <tbody>";
							html += "        <tr>";
							html += "            <td>" + (i + 1) + "</td>";
							html += "	         <td>" + "<a href=\"/dept/" + rows[i].no + "\">" + rows[i].name + "</a>" + "</td>";
							html += "            <td>" +  rows[i].tel + "</td>";
							html += "            <td>" +  rows[i].location + "</td>";
							html += "        <tr>";
							html += "    </tbody>";
						}
						html += "</table>";

						document.getElementById("list").innerHTML = html;
					}
				} else {
					console.error(xhr.responseText);
				}
			}
		};

		var name = document.getElementById("name").value;
		if (!name) {
			name = "%25";
		}
		xhr.open("GET", "http://localhost/dept/search/" + name, true);
		xhr.send();
	};
</script>

<%@ include file="/WEB-INF/jsp/layout/bottom.jsp" %>