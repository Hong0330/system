<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/jsp/layout/top.jsp" %>

<div class="row justify-content-center">
	<div class="card border-width-3 border-radius-0 border-color-hover-dark mb-4 col-lg-6">
		<div class="card-body">
		<h4 class="font-weight-bold text-uppercase text-4 mb-3">학과 정보 등록</h4>
			<form action="${request.getContextPath()}/dept" method="post" class="needs-validation" onsubmit="return check(this)" >
				<div class="form-group row">
					<label class="col-lg-3 font-weight-bold text-dark col-form-label form-control-label text-2 required">학과명</label>
					<div class="col-lg-9">
						<input id="name" class="form-control" type="text" name="name" maxlength="33" required>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-lg-3 font-weight-bold text-dark col-form-label form-control-label text-2 required">전화번호</label>
					<div class="col-lg-9">
						<input class="form-control" type="text" name="tel" maxlength="12" onkeyup="inputPhoneNumber(this)" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" required>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-lg-3 font-weight-bold text-dark col-form-label form-control-label text-2 required">위치</label>
					<div class="col-lg-9">
						<input class="form-control" type="text" name="location" maxlength="33" required>
					</div>
				</div>
				<input type="submit" value="등록" class="btn btn-primary btn-modern float-right" />
				<div class="col-lg-10">
					<a href="/dept"><input type="button" value="취소" class="btn btn-primary btn-modern float-right" /></a>
				</div>
			</form>
			<div class="col-lg-9" id="error"></div>
		</div>
	</div>
</div>
<script>
	function inputPhoneNumber(obj) {
	    var number = obj.value.replace(/[^0-9]/g, "");
	    var phone = "";
	
	    if(number.length < 4) {
	        return number;
	    } else if(number.length < 7) {
	        phone += number.substr(0, 3);
	        phone += "-";
	        phone += number.substr(3);
	    } else if(number.length < 11) {
	        phone += number.substr(0, 3);
	        phone += "-";
	        phone += number.substr(3, 3);
	        phone += "-";
	        phone += number.substr(6);
	    } else {
	        phone += number.substr(0, 3);
	        phone += "-";
	        phone += number.substr(3, 4);
	        phone += "-";
	        phone += number.substr(7);
	    }
	    obj.value = phone;
	};

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
						document.getElementById("error").innerHTML = "<font size=\"2em\" color=\"red\">이미 존재하는 학과명입니다.</font>";
					}
				} else {
					console.error(xhr.responseText);
				}
			}
		};
		
		var name = document.getElementById("name").value;
		if (!name) {
			name = "!";
		}
		xhr.open("GET", "http://localhost/dept/check/" + name, true);
		xhr.send();
		
		return false;
	};
</script>
<%@ include file="/WEB-INF/jsp/layout/bottom.jsp" %>