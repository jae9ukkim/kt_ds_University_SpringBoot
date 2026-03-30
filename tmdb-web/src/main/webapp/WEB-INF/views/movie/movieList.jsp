<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>영화 목록</title>
    <link rel="stylesheet" href="/css/tmdb-web.css">
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
            <th class="hide">아이디</th>
            <th>포스터</th>
            <th>제목</th>
            <th>관람등급</th>
            <th>개봉일</th>
            <th>개봉국가</th>
            <th>상영시간(분)</th>
            <th>소개글</th>
            <th class="hide">개요</th>
            <th>원제</th>
            <th>상태</th>
            <th>원어</th>
            <th>제작비($)</th>
            <th>수익($)</th>
        </tr>
        </thead>
        <tbody>
          <c:forEach items="${movieList}" var="movie">
            <tr>
              <td class="hide">${movie.movieId}</td>
              <td><img src="${movie.posterUrl}" ></td>
              <td><a href="/view/${movie.movieId}">${movie.title}</a></td>
              <td class="align-center">${movie.movieRating}</td>
              <td>${movie.openDate}</td>
              <td class="align-center">${movie.openCountry}</td>
              <td class="align-center">${movie.runningTime}</td>
              <td>${movie.introduce}</td>
              <td class="hide">${movie.sysnopsis}</td>
              <td>${movie.originalTitle}</td>
              <td class="align-center">${movie.state}</td>
              <td>${movie.language}</td>
              <td class="align-right">${movie.budget}</td>
              <td class="align-right">${movie.profit}</td>
            </tr>
          </c:forEach>
        </tbody>

      </table>
        <div class="align-right">
          <a href="/write">새 영화</a>
        </div>
    </div>
  </body>
</html>
