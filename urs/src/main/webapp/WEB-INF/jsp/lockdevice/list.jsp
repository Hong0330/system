<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="/WEB-INF/jsp/layout/top.jsp" %>

	<h4 class="font-weight-bold text-uppercase text-4 mb-3 col-lg-4">잠금장치 정보</h4>
	<div class="float-left col-lg-4">
		<input id="deptName" type="text" name="deptName" placeholder="검색할 학과 명을 입력해주세요." class="form-control"  />
	</div>
	<div class="float-left">
	<a><input id="search" type="button" value="검색" class="btn btn-outline btn-primary btn-sm mb-2 form-control" /></a>
	</div>
	<div class="float-right">
	<a href="${request.getContextPath()}/lockdevice/form"><input type="button" value="등록" class="btn btn-outline btn-primary btn-sm mb-2 form-control" /></a>
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
						html += "        <th>잠금장치 번호</th>";
						html += "        <th>학과 명</th>";
						html += "        <th>상태</th>";
						html += "    </tr>";
						for (var i = 0; i < rows.length; i++) {
							html += "    <tr>";
							html += "        <td>" + (i + 1) + "</td>";
							html += "        <td><a href=\"/lockdevice/" + rows[i].no + "\">" + rows[i].no + "</a></td>";
							html += "        <td>" + rows[i].deptName + "</td>";
							if (rows[i].status == "O") {
								html += "        <td>열림</td>";
							} else if (rows[i].status == "C"){
								html += "        <td>닫힘</td>";
							}
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
		
		var deptName = document.getElementById("deptName").value;
		if (!deptName) {
			deptName = "%25";
		}
		xhr.open("GET", "http://localhost/lockdevice/search/" + deptName , true);
		xhr.send();
	};
</script>

<%@ include file="/WEB-INF/jsp/layout/bottom.jsp" %>