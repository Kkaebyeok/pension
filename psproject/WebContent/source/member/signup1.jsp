<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../header.jsp"/>
<div class="wrap">
	<div class="content">
		<div class="join-wrapper">
			<div>
				<h1>회원가입</h1>
				<ul class="join_step">
					<li class="join_on">
						<p>01</p>
						<p>약관동의</p>
					</li>
					<li>
						<p>02</p>
						<p>회원유형</p>
					</li>
					<li>
						<p>03</p>
						<p>정보입력</p>
					</li>
					<li>
						<p>04</p>
						<p>가입완료</p>
					</li>
				</ul>
				<div class="terms_main">
					<h3>회원정보이용약관</h3>
					<textarea readonly="readonly">개인정보 처리목적

놀다가는 다음의 목적을 위하여 개인정보를 처리합니다. 처리하고 있는 개인정보는 다음의 목적 이외의 용도로는 이용되지 않으며, 이용 목적이 변경되는 경우에는 개인정보 보호법 제18조에 따라 별도의 동의를 받는 등 필요한 조치를 이행할 것입니다.

1. 홈페이지 회원 가입 및 관리
회원 가입의사 확인, 서비스 부정이용 방지을 위한 기초자료 등을 목적으로 개인정보를 처리합니다.

2. 홍보메일 발송
세미나, 뉴스레터 등의 메일 발송의 목적을 위해 개인정보를 처리합니다

3. 개인정보 항목
(일반회원) 비밀번호, 이름, 이메일, 휴대전화,거주지
(기업회원) 비밀번호, 이름, 이메일, 휴대전화, 기업명, 기업위치, 대표자명, 설립연도, 대표연락처, 대표 이메일, 업종, 년도별 매출액, 인력현황, 자본금, 기업 홈페이지, 사업자 등록증

4. 보유 및 이용기간
놀다가는 법령에 따른 개인정보 보유ㆍ이용기간 또는 정보주체로부터 개인정보를 수집 시에 동의 받은 개인정보 보유·이용기간을 3년내에서 개인정보를 처리·보유합니다.

5. 동의거부 권리 및 불이익 내용
개인정보보호법 제 15조에 따라서 개인정보 수집에 동의를 거부할 수 있으며, 필수항목을 입력하지 않을 경우 회원가입 및 민원사무처리가 불가능합니다.
				</textarea>
					<div class="t_input">
						<label><input type="radio" name="c"> 동의합니다</label>
						<label><input type="radio" name="c"> 동의하지 않습니다</label>
					</div>
					<div class="t_buttons">
						<a href="javascript:history.back()"><button>취소</button></a>
						<a href="signup2"><button>확인</button></a>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="../footer.jsp"/>
      
      
