<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://www.springframework.org/tags/form"
prefix="form" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>영화 등록</title>
    <script type="text/javascript" src="/js/jquery-4.0.0.slim.min.js"></script>
    <script type="text/javascript" src="/js/movie.js"></script>
    <link rel="stylesheet" type="text/css" href="/css/tmdb-web.css" />
  </head>
  <body>
    <h1>영화 등록</h1>
    <form:form
      modelAttribute="writeVO"
      action="/write"
      method="post"
      enctype="multipart/form-data"
    >
      <div class="grid write">
        <label for="posterUrl">포스터 URL : </label>
        <div>
          <input
            type="url"
            name="posterUrl"
            id="posterUrl"
            value="${inputData.posterUrl}"
          />
          <form:errors
            path="posterUrl"
            element="div"
            cssClass="validation-error"
          />
        </div>

        <label for="title">제목 : </label>
        <div>
          <input
            type="text"
            name="title"
            id="title"
            value="${inputData.title}"
          />
          <form:errors path="title" element="div" cssClass="validation-error" />
        </div>

        <label for="posterFiles">포스터 파일 : </label>
        <input type="file" name="posterFiles" id="posterFiles" />

        <label for="movieRating">관람등급 : </label>
        <input
          type="text"
          name="movieRating"
          id="movieRating"
          value="${inputData.movieRating}"
        />

        <label for="openDate">개봉일 : </label>
        <input
          type="date"
          name="openDate"
          id="openDate"
          value="${inputData.openDate}"
        />

        <label for="openCountry">개봉국가 : </label>
        <input
          type="text"
          name="openCountry"
          id="openCountry"
          value="${inputData.openCountry}"
        />

        <label for="runningTime">상영시간 : </label>
        <input
          type="number"
          name="runningTime"
          id="runningTime"
          value="${inputData.runningTime}"
        />

        <label for="originalTitle">원제 : </label>
        <input
          type="text"
          name="originalTitle"
          id="originalTitle"
          value="${inputData.originalTitle}"
        />

        <label for="state">상태 : </label>
        <div>
          <select name="state" id="state">
            <option value="개봉됨">개봉됨</option>
            <option value="제작예정">제작예정</option>
            <option value="후반제작">후반제작</option>
          </select>
          <form:errors path="state" element="div" cssClass="validation-error" />
        </div>

        <label for="language">원어 : </label>
        <div>
          <input
            type="text"
            name="language"
            id="language"
            value="${inputData.language}"
          />
          <form:errors
            path="language"
            element="div"
            cssClass="validation-error"
          />
        </div>

        <label for="budget">제작비 : </label>
        <input
          type="number"
          name="budget"
          id="budget"
          value="${inputData.budget}"
        />

        <label for="profit">수익 : </label>
        <input
          type="number"
          name="profit"
          id="profit"
          value="${inputData.profit}"
        />

        <label for="introduce">소개글 : </label>
        <textarea name="introduce" id="introduce">
${inputData.introduce}</textarea
        >

        <label for="sysnopsis">개요 : </label>
        <div>
          <textarea name="sysnopsis" id="sysnopsis">
${inputData.sysnopsis}</textarea
          >
          <form:errors
            path="sysnopsis"
            element="div"
            cssClass="validation-error"
          />
        </div>

        <input type="submit" value="등록" />
      </div>
    </form:form>
  </body>
</html>
