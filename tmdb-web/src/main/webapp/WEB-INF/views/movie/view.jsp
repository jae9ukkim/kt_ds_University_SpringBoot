<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <div class="grid write">
        <div>포스터 URL : ${movie.posterUrl}</div>
        <div>제목 : ${movie.title}</div>
        <div>관람등급 : ${movie.movieRating}</div>
        <div>개봉일 : ${movie.openDate}</div>
        <div>개봉국가 : ${movie.openCountry}</div>
        <div>상영시간 : ${movie.runningTime}</div>
        <div>소개글 : ${movie.introduce}</div>
        <div>개요 : ${movie.sysnopsis}</div>
        <div>원제 : ${movie.originalTitle}</div>
        <div>상태 : ${movie.state}</div>
        <div>원어 : ${movie.language}</div>
        <div>제작비 : ${movie.budget}</div>
        <div>수익 : ${movie.profit}</div>
        <div>
            <a href="">수정</a>   
            <a href="">삭제</a>   
        </div>
      </div>
</body>
</html>