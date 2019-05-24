<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:include page="header.jsp"/>
      <!-- END header -->
      <section class="pt-5">
        <div class="subcontainer">
          <div class="row rollpic">
            <div class="col-md-12 rollpic">
              <div class="owl-carousel owl-theme home-slider">
                <div>
                  <a href="list?type=2" class="a-block d-flex align-items-center height-lg event phone1" style="background-image: url('images/spa.png');">
                  </a>
                </div>
                <div>
                  <a href="list?type=4" class="a-block d-flex align-items-center height-lg event phone2" style="background-image: url('images/workshop.png');">
                  </a>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>
	<div class="wrap">
	<!-- END section -->
	<section class="site-section py-sm">
        <div class="container">
          <div class="row">
            <div class="col-md-6">
              <h2 class="mb-4">추천펜션</h2>
            </div>
          </div>
          <div class="row blog-entries">
            <div class="col-md-12 col-lg-12 main-content">
              <div class="row">
              <c:forEach items="${list}" var="i">
                <div class="col-md-4">
                <fmt:formatDate var="now" pattern="yyyy-MM-dd" value="<%=new Date()%>"/>
                  <a href="detail.do?psidx=${i.psidx}&startdate=${now}" class="blog-entry element-animate" data-animate-effect="fadeIn">
                    <img src="${web_path}/${i.oridx}/thumb.jpg" alt="Image placeholder">
                    <div class="blog-content-body">
                      <div class="post-meta">
                        <span class="mr-2">${i.pstitle}</span>
                      </div>
                      <h2 class="price">최저가 : ${i.lowPrice}원</h2>
                    </div>
                  </a>
                </div>
              </c:forEach>
              </div>
            <!-- END main-content -->
        </div>
        </div>
        </div>
      </section>
      </div>
<jsp:include page="footer.jsp"/>
