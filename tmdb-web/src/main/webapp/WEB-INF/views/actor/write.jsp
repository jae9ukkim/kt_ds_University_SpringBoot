<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="/js/jquery-4.0.0.slim.min.js"></script>
<link rel="stylesheet" type="text/css" href="/css/tmdb-web.css">
<title>배우 등록</title>
</head>
<body>
    <h1>배우 등록</h1>
    <form:form modelAttribute="writeVO" action="/actor/write" method="post">
        <div class="grid write">
            <label for="actorName">배우 이름</label>
            <input type="text" name="actorName" id="actorName" value="${inputData.actorName}">
            <label for="actorProfileUrl">프로필 URL</label>
            <input type="text" name="actorProfileUrl" id="actorProfileUrl" value="${inputData.actorProfileUrl}">
            <input type="submit" value="등록">
        </div>
    </form:form>
</body>
</html>