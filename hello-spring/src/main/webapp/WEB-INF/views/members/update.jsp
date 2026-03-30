<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>사용자 정보 수정</title>
    <link rel="stylesheet" type="text/css" href="/css/hello-spring.css" />
  </head>
  <body>
    <h1>회원가입</h1>
    <form action="/member/update/${member.email}" method="post">
      <div class="grid regist">
        <!-- <label for="email">이메일: </label>
        <input
          type="email"
          name="email"
          id="email"
          placeholder="이메일을 입력하세요"
          value="${member.email}"
        /> -->
        <label for="name">이름: </label>
        <input
          type="text"
          name="name"
          id="name"
          placeholder="이름을 입력하세요"
          value="${member.name}"
        />
        <label for="password">비밀번호: </label>
        <input
          type="password"
          name="password"
          id="password"
          placeholder="비밀번호를 입력하세요"
          value="{member.password}"
        />
        <div class="btn-group">
          <div class="right-align">
            <input type="submit" value="저장" />
          </div>
        </div>
      </div>
    </form>
  </body>
</html>
