<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>회원가입</title>
    <script type="text/javascript" src="/js/jquery-4.0.0.slim.min.js"></script>
    <script type="text/javascript" src="/js/members.js"></script>
    <link rel="stylesheet" type="text/css" href="/css/tmdb-web.css" />
  </head>
  <body>
    <form:form modelAttribute="registVO" action="/regist" method="post">
      <div class="grid regist">
        <label for="email" class="hide">이메일</label>
        <input type="text" name="email" id="email" placeholder="이메일" />

        <label for="name" class="hide">이름</label>
        <input type="text" name="name" id="name" placeholder="이름" />

        <label for="password" class="hide">비밀번호</label>
        <input
          type="password"
          name="password"
          id="password"
          placeholder="비밀번호"
        />

        <label for="password" class="hide">비밀번호 확인</label>
        <input
          type="password"
          name="password-confirm"
          id="password-confirm"
          placeholder="비밀번호 확인"
        />

        <input type="submit" value="회원가입" />
      </div>
    </form:form>
  </body>
</html>
