<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://www.springframework.org/tags/form"
prefix="form" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>회원가입</title>
    <script type="text/javascript" src="/js/jquery-4.0.0.slim.min.js"></script>
    <script type="text/javascript" src="/js/members.js"></script>
    <link rel="stylesheet" type="text/css" href="/css/hello-spring.css" />
  </head>
  <body>
    <h1>회원가입</h1>
    <form:form modelAttribute="registVO" action="/regist" method="post">
      <div class="grid regist">
        <label for="email">이메일: </label>
        <div class="input-div">
          <input
            type="email"
            name="email"
            id="email"
            placeholder="이메일을 입력하세요"
            value="${inputData.email}"
          />
          <form:errors path="email" element="div" cssClass="validation-error" />
        </div>

        <label for="name">이름: </label>
        <div>
          <input
            type="text"
            name="name"
            id="name"
            placeholder="이름을 입력하세요"
            value="${inputData.name}"
          />
          <form:errors path="name" element="div" cssClass="validation-error" />
        </div>

        <label for="password">비밀번호: </label>
        <div class="input-div">
          <input
            type="password"
            name="password"
            id="password"
            placeholder="비밀번호를 입력하세요"
          />
          <form:errors
            path="password"
            element="div"
            cssClass="validation-error"
          />
        </div>
        <!-- 비밀번호 두 번 입력하기 ==> 두 비밀번호가 일치할 때만 회원가입 -->
        <label for="confirm-password">비밀번호 확인</label>
        <div class="input-div">
          <input type="password" name="confirm-password" id="confirm-password">
        </div>
        <!-- 비밀번호 한 번 입력하기 ==> 비밀번호를 확인하는 기능 -->
        <label for="show-password">비밀번호 확인하기</label>
        <input type="checkbox" id="show-password" />
        <!-- name 없음 => 서버로 보내지 않겠다. -->

        <div class="btn-group">
          <div class="right-align">
            <input type="submit" value="저장" />
          </div>
        </div>
      </div>
    </form:form>
  </body>
</html>
