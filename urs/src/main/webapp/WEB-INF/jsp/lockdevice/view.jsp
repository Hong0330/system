<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="/WEB-INF/jsp/layout/top.jsp" %>

							<div class="row justify-content-center">
								<div class="card border-width-3 border-radius-0 border-color-hover-dark mb-4 col-lg-6">
									<div class="card-body">
										<h4 class="font-weight-bold text-uppercase text-4 mb-3">잠금장치 정보</h4>
										<table class="shop_table cart-totals mb-5">
											<tbody>
												<tr>
													<td>
														<strong class="d-block text-color-dark line-height-1 font-weight-semibold">잠금장치 번호<span class="product-qty"></span></strong>
													</td>
													<td class="text-right align-top">
														<span class="d-block text-color-dark line-height-1 font-weight-semibold"><c:out value="${row.no}" /></span>
													</td>
												</tr>
												<tr>
													<td>
														<strong class="d-block text-color-dark line-height-1 font-weight-semibold">직원 명<span class="product-qty"></span></strong>
													</td>
													<td class="text-right align-top">
														<span class="d-block text-color-dark line-height-1 font-weight-semibold"><c:out value="${row.empName}" /></span>
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
														<strong class="d-block text-color-dark line-height-1 font-weight-semibold">상태<span class="product-qty"></span></strong>
													</td>
													<c:choose>
														<c:when test="${\"O\".equals(row.status)}">
															<td class="text-right align-top">
																<span class="d-block text-color-dark line-height-1 font-weight-semibold">열림</span>
															</td>
														</c:when>
														<c:otherwise>
															<td class="text-right align-top">
																<span class="d-block text-color-dark line-height-1 font-weight-semibold">닫힘</span>
															</td>
														</c:otherwise>
													</c:choose>
												</tr>
											</tbody>
										</table>
										<form action="${request.getContextPath()}/lockdevice/${row.no}" method="post">
											<input type="hidden" name="_method" value="delete" />
											<input type="submit" value="삭제" class="btn btn-primary btn-modern float-right" />
										</form>
									</div>
								</div>
							</div>

<%@ include file="/WEB-INF/jsp/layout/bottom.jsp" %>