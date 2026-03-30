<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Insert title here</title>
    <link rel="stylesheet" type="text/css" href="/css/tmdb-web.css" />
  </head>
  <body>
    <h1>영화 수정</h1>
    <form action="/update/${movie.movieId}" method="post">
      <div class="grid update">
        <label for="posterUrl">포스터 URL : </label>
        <input
          type="url"
          name="posterUrl"
          id="posterUrl"
          value="${movie.posterUrl}"
        />
        <label for="title">제목 : </label>
        <input type="text" name="title" id="title" value="${movie.title}" />
        <label for="movieRating">관람등급 : </label>
        <input
          type="text"
          name="movieRating"
          id="movieRating"
          value="${movie.movieRating}"
        />
        <label for="openDate">개봉일 : </label>
        <input
          type="date"
          name="openDate"
          id="openDate"
          value="${movie.openDate}"
        />
        <label for="openCountry">개봉국가 : </label>
        <input
          type="text"
          name="openCountry"
          id="openCountry"
          value="${movie.openCountry}"
        />
        <label for="runningTime">상영시간 : </label>
        <input
          type="number"
          name="runningTime"
          id="runningTime"
          value="${movie.runningTime}"
        />
        <label for="originalTitle">원제 : </label>
        <input
          type="text"
          name="originalTitle"
          id="originalTitle"
          value="${movie.originalTitle}"
        />
        <label for="state">상태 : </label>
        <select name="state" id="state" value="${movie.state}">
          <option value="개봉됨">개봉됨</option>
          <option value="제작예정">제작예정</option>
          <option value="후반제작">후반제작</option>
        </select>
        <label for="language">원어 : </label>
        <input
          type="text"
          name="language"
          id="language"
          value="${movie.language}"
        />
        <label for="budget">제작비 : </label>
        <input
          type="number"
          name="budget"
          id="budget"
          value="${movie.budget}"
        />
        <label for="profit">수익 : </label>
        <input
          type="number"
          name="profit"
          id="profit"
          value="${movie.profit}"
        />
        <label for="introduce">소개글 : </label>
        <textarea name="introduce" id="introduce">${movie.introduce}</textarea>
        <label for="sysnopsis">개요 : </label>
        <textarea name="sysnopsis" id="sysnopsis">${movie.sysnopsis}</textarea>
        <input type="submit" value="수정" />
      </div>
    </form>
  </body>
</html>
