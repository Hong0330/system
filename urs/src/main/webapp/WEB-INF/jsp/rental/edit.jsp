<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="/WEB-INF/jsp/layout/top.jsp" %>

<div class="row justify-content-center">
	<div class="card border-width-3 border-radius-0 border-color-hover-dark mb-4 col-lg-6">
		<div class="card-body">
		<h4 class="font-weight-bold text-uppercase text-4 mb-3">대여 정보 수정</h4>
			<form action="${request.getContextPath()}/rental" method="post" class="needs-validation" >
				<input type="hidden" name="_method" value="put" />
				<input type="hidden" name="no" value="${row.no}" />
				<div class="form-group row">
					<label class="col-lg-3 font-weight-bold text-dark col-form-label form-control-label text-2">학번</label>
					<div class="col-lg-9">
						<input class="form-control" type="text" name="studentNo" value="${row.studentNo}" maxlength="20" readonly>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-lg-3 font-weight-bold text-dark col-form-label form-control-label text-2">잠금 장치 일련번호</label>
					<div class="col-lg-9">
						<input class="form-control" type="text" name="lockDeviceNo" value="${row.lockDeviceNo}" maxlength="33" readonly>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-lg-3 font-weight-bold text-dark col-form-label form-control-label text-2">대여 일자</label>
					<div class="col-lg-9">
						<input class="form-control" type="date" name="rentalDate" value="<fmt:formatDate value="${row.rentalDate}" pattern="yyyy-MM-dd" />" maxlength="33" readonly>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-lg-3 font-weight-bold text-dark col-form-label form-control-label text-2">반납 일자</label>
					<div class="col-lg-9">
						<input class="form-control" type="date" name="returnDate" value="<fmt:formatDate value="${row.returnDate}" pattern="yyyy-MM-dd" />" max="9999-12-31" readonly>
					</div>
				</div>
				<div class="form-group row mb-5">
					<label class="col-lg-3 font-weight-bold text-dark col-form-label form-control-label text-2 required">상태</label>
					<div class="col-lg-9">
						<select name="status" class="form-control">
							<c:if test="${\"Y\".equals(row.status)}">
								<option value="Y" selected="selected">반납</option>
								<option value="N">대여 중</option>
								<option value="B">파손</option>
							</c:if>
							<c:if test="${\"N\".equals(row.status)}">
								<option value="Y">반납</option>
								<option value="N" selected="selected">대여 중</option>
								<option value="B">파손</option>
							</c:if>
							<c:if test="${\"B\".equals(row.status)}">
								<option value="Y">반납</option>
								<option value="N">대여 중</option>
								<option value="B" selected="selected">파손</option>
							</c:if>
						</select>
					</div>
				</div>
				<input type="submit" value="수정" class="btn btn-primary btn-modern float-right" />
				<div class="form-group col-lg-10">
					<a href="/rental/${row.no}"><input type="button" value="취소" class="btn btn-primary btn-modern float-right" /></a>
				</div>
			</form>
		</div>
	</div>
</div>

<%@ include file="/WEB-INF/jsp/layout/bottom.jsp" %>