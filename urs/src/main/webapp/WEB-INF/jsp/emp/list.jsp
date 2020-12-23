<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/jsp/layout/top.jsp" %>

	<h4 class="font-weight-bold text-uppercase text-4 mb-3 col-lg-4">직원 정보</h4>
	<div class="float-left col-lg-4">
		<input id="memberNo" type="text" name="memberNo" placeholder="검색할 사번을 입력해주세요." class="form-control"  />
	</div>
	<div class="float-left">
		<a><input id="search" type="button" value="검색" class="btn btn-outline btn-primary btn-sm mb-2 form-control" /></a>
	</div>
	<div class="float-right">
		<a href="${request.getContextPath()}/emp/form"><input type="button" value="등록" class="btn btn-outline btn-primary btn-sm mb-2 form-control" /></a>
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
					html += "<tr>";
					html += "   <th>번호</th>";
					html += "	<th>사번</th>";
					html += "	<th>학과 명</th>";
					html += "	<th>이름</th>";
					html += "<tr>";
					
					for (var i = 0; i < rows.length; i++) {
						html += "<tr>";
						html += "	<td>" + (i + 1) + "</td>";
						html += "	<td><a href=\"emp/" + rows[i].memberNo + "\">" + rows[i].memberNo + "</a></td>";
						html += "	<td>" + rows[i].deptName + "</td>";
						html += "	<td>" + rows[i].name + "</td>";
						html += "</tr>";
					}
					
					html += "</table>";
					
					document.getElementById("list").innerHTML = html;
				}
			} else {
				console.error(xhr.responseText);
			}
		}
	};
	
	var memberNo = document.getElementById("memberNo").value;
	if (!memberNo) {
		memberNo = 0;
	}
	
	xhr.open("GET", "http://localhost/emp/search/" + memberNo , true);
	xhr.send();
};
</script>

<%@ include file="/WEB-INF/jsp/layout/bottom.jsp" %>