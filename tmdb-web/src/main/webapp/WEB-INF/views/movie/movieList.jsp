<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>영화 목록</title>
  </head>
  <body>
    <h1>영화 목록</h1>
    <div>
      <div>
        등록된 영화 수 : ${count}
      </div>
      <table>
        <thead>
          <tr>
            <th>아이디</th>
            <th>포스터</th>
            <th>제목</th>
            <th>관람등급</th>
            <th>개봉일</th>
            <th>개봉국가</th>
            <th>상영시간</th>
            <th>소개글</th>
            <th>개요</th>
            <th>원제</th>
            <th>상태</th>
            <th>원어</th>
            <th>제작비</th>
            <th>수익</th>
        </tr>
        </thead>
        <tbody>
          <c:forEach items="${movieList}" var="movie">
          <tr>
            <td><a href="/view/${movie.movieId}">${movie.movieId}</a></td>
            <td>${movie.posterUrl}</td>
            <td>${movie.title}</td>
            <td>${movie.movieRating}</td>
            <td>${movie.openDate}</td>
            <td>${movie.openCountry}</td>
            <td>${movie.runningTime}</td>
            <td>${movie.introduce}</td>
            <td>${movie.sysnopsis}</td>
            <td>${movie.originalTitle}</td>
            <td>${movie.state}</td>
            <td>${movie.language}</td>
            <td>${movie.budget}</td>
            <td>${movie.profit}</td>
          </tr>
        </c:forEach>
        </tbody>

      </table>
        
    </div>
  </body>
</html>
