<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!-- /templates/header.jsp import -->
<jsp:include page="/WEB-INF/views/templates/header.jsp">
    <jsp:param value="게시글 목록" name="title"/>
</jsp:include>
      <div class="grid list">
        <h1>게시글 목록</h1>
        <div>총 ${searchCount}개의 게시글이 검색되었습니다.</div>
        <table class="grid">
            <thead>
                <tr>
                  <th>번호</th>
                  <th>제목</th>
                  <th>이름</th>
                  <th>조회수</th>
                  <th>등록일</th>
                  <th>수정일</th>
                </tr>
            </thead>
            <tbody>
              <c:choose>
                  <c:when test="${not empty searchResult}">
                  <!-- searchResult가 존재하면, 반복하여 데이터를 보여주고 -->
                    <c:forEach items="${searchResult}" var="board">
                      <tr>
                        <td>${board.id}</td>
                        <td><a href="/view/${board.id}">${board.subject}</a></td>
                        <td>${board.membersVO.name}</td>
                        <td>${board.viewCnt}</td>
                        <td>${board.crtDt}</td>
                        <td>${board.mdfyDt}</td>
                      </tr>
                    </c:forEach>
                </c:when>
                  <c:otherwise>
                  <!-- searchResult가 존재하지 않으면, "검색된 데이터가 없습니다"를 보여주고 -->
                    <tr>
                        <td colspan="6">검색된 데이터가 없습니다.</td>
                    </tr>
                  </c:otherwise>
              </c:choose>
            </tbody>
        </table>
        <c:if test="${not empty sessionScope.__LOGIN_DATA__}">
          <div class="btn-group">
            <div class="right-align">
              <a href="/write">새로운 게시글 작성</a>
            </div>
          </div>
        </c:if>
      </div>
<jsp:include page="/WEB-INF/views/templates/footer.jsp"></jsp:include>
