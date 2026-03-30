<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> 
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>회원 목록</title>
    <link rel="stylesheet" type="text/css" href="/css/hello-spring.css" />
  </head>
  <body>
    <h1>회원 목록</h1>
    <div>회원 수 : ${count}</div>
    <div class="grid list">
      <table class="grid">
        <thead>
          <tr>
            <th>이메일</th>
            <th>이름</th>
          </tr>
        </thead>
        <tbody>
            <c:choose> 
              <c:when test="${not empty members}">
                    <c:forEach items="${members}" var="member">
                        <tr>
                            <td><a href="/member/view/${member.email}">${member.email}</a></td>
                            <td>${member.name}</td>
                        </tr>
                    </c:forEach>
              </c:when>
              <c:otherwise>
                <tr>
                  <td colspan="2">회원이 없습니다.</td>
                </tr>
              </c:otherwise>
            </c:choose>
        </tbody>
      </table>
      <div class="btn-group">
        <div class="right-align">
            <a href="/regist">새로운 회원 등록</a>
        </div>
      </div>
    </div>
  </body>
</html>
