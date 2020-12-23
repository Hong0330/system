<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="/WEB-INF/jsp/layout/top.jsp" %>

							<div class="row justify-content-center">
								<div class="card border-width-3 border-radius-0 border-color-hover-dark mb-4 col-lg-6">
									<div class="card-body">
										<h4 class="font-weight-bold text-uppercase text-4">직원 정보</h4>
										<table class="shop_table cart-totals mb-5">
											<tbody>
												<tr>
													<td>
														<strong class="d-block text-color-dark line-height-1 font-weight-semibold">사번<span class="product-qty"></span></strong>
													</td>
													<td class="text-right align-top">
														<span class="d-block text-color-dark line-height-1 font-weight-semibold"><c:out value="${row.memberNo}" /></span>
													</td>
												</tr>
												<tr>
													<td>
														<strong class="d-block text-color-dark line-height-1 font-weight-semibold">학과 명<span class="product-qty"></span></strong>
													</td>
													<td class="text-right align-top">
														<span class="d-block text-color-dark line-height-1 font-weight-semibold"><c:out value="${row.deptName}" /></span>
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
														<strong class="d-block text-color-dark line-height-1 font-weight-semibold">카드번호<span class="product-qty"></span></strong>
													</td>
													<td class="text-right align-top">
														<span class="d-block text-color-dark line-height-1 font-weight-semibold"><c:out value="${row.cardNo}" /></span>
													</td>
												</tr>
											</tbody>
										</table>
										<c:if test="${\"M\".equals(type)}">
											<form action="${request.getContextPath()}/emp/${row.memberNo}" method="post">
												<input type="hidden" name="_method" value="delete" />
												<input type="submit" value="삭제" class="btn btn-primary btn-modern float-right" />
											</form>
										</c:if>
										<div class="col-lg-10">
											<a href="${request.getContextPath()}/emp/${row.memberNo}/form"><input type="button" value="수정" class="btn btn-primary btn-modern float-right" /></a>
										</div>
									</div>
								</div>
							</div>
						
<%@ include file="/WEB-INF/jsp/layout/bottom.jsp" %>