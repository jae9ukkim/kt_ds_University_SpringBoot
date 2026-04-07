<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/templates/header.jsp">
    <jsp:param value="사용자 조회" name="title"/>    
</jsp:include>
<h1>사용자 내용 조회</h1>
    <div class="grid view">
      <span>이메일</span>
      <div>${member.email}</div>

      <span>이름</span>
      <div>${member.name}</div>

      <div class="btn-group">
        <div class="right-align">
            <a href="/member/update/${member.email}">수정</a>
            <a href="/member/delete?email=${member.email}">삭제</a>
            <a href="/member/delete-me">회원탈퇴</a>
        </div>
      </div>
    </div>
<jsp:include page="/WEB-INF/views/templates/footer.jsp"></jsp:include>