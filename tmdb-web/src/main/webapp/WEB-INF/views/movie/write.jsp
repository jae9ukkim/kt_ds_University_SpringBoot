<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>영화 등록</title>
    <link rel="stylesheet" type="text/css" href="/css/tmdb-web.css" />
  </head>
  <body>
    <h1>영화 등록</h1>
    <form action="/write" method="post" enctype="multipart/form-data">
      <div class="grid write">
        <label for="posterUrl">포스터 URL : </label>
        <input type="url" name="posterUrl" id="posterUrl" />
        <label for="title">제목 : </label>
        <input type="text" name="title" id="title" />
        <label for="posterFiles">포스터 파일 : </label>
        <input type="file" name="posterFiles" id="posterFiles" />
        <label for="movieRating">관람등급 : </label>
        <input type="text" name="movieRating" id="movieRating" />
        <label for="openDate">개봉일 : </label>
        <input type="date" name="openDate" id="openDate" />
        <label for="openCountry">개봉국가 : </label>
        <input type="text" name="openCountry" id="openCountry" />
        <label for="runningTime">상영시간 : </label>
        <input type="number" name="runningTime" id="runningTime" />
        <label for="originalTitle">원제 : </label>
        <input type="text" name="originalTitle" id="originalTitle" />
        <label for="state">상태 : </label>
        <select name="state" id="state">
          <option value="개봉됨">개봉됨</option>
          <option value="제작예정">제작예정</option>
          <option value="후반제작">후반제작</option>
        </select>
        <label for="language">원어 : </label>
        <input type="text" name="language" id="language" />
        <label for="budget">제작비 : </label>
        <input type="number" name="budget" id="budget" />
        <label for="profit">수익 : </label>
        <input type="number" name="profit" id="profit" />
        <label for="introduce">소개글 : </label>
        <textarea name="introduce" id="introduce"></textarea>
        <label for="sysnopsis">개요 : </label>
        <textarea name="sysnopsis" id="sysnopsis"></textarea>
        <input type="submit" value="등록" />
      </div>
    </form>
  </body>
</html>
