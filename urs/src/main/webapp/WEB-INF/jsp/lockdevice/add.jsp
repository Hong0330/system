<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="/WEB-INF/jsp/layout/top.jsp" %>

<div class="row justify-content-center">
	<div class="card border-width-3 border-radius-0 border-color-hover-dark mb-4 col-lg-6">
		<div class="card-body">
		<h4 class="font-weight-bold text-uppercase text-4 mb-3">잠금 장치 정보 등록</h4>
			<form action="${request.getContextPath()}/lockdevice" method="post" class="needs-validation" onsubmit="return check(this)">
				<div class="form-group row">
					<label class="col-lg-3 font-weight-bold text-dark col-form-label form-control-label text-2 required">잠금 장치 번호</label>
					<div class="col-lg-9">
						<input class="form-control" id="no" type="text" name="no" maxlength="20" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" required>
					</div>
					<div class="col-lg-9" id="error"></div>
				</div>
				<input type="submit" value="등록" class="btn btn-primary btn-modern float-right" />
				<div class="col-lg-10">
					<a href="/lockdevice"><input type="button" value="취소" class="btn btn-primary btn-modern float-right" /></a>
				</div>
			</form>
		</div>
	</div>
</div>
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
						document.getElementById("error").innerHTML = "<font size=\"2em\" color=\"red\">이미 존재하는 번호입니다.</font>";
					}
				} else {
					console.error(xhr.responseText);
				}
			}
		};
		
		var no = document.getElementById("no").value;
		var status = null;
		if (no == 0) {
			no = "1";
		}
		
		xhr.open("GET", "http://localhost/lockdevice/check/" + no + "/" + status, true);
		xhr.send();
		
		return false;
	};
</script>
<%@ include file="/WEB-INF/jsp/layout/bottom.jsp" %>