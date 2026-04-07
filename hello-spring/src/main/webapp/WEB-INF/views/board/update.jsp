<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="jakarta.tags.core" prefix="c" %>
<jsp:include page="/WEB-INF/views/templates/header.jsp">
    <jsp:param value="게시글 수정" name="title"/>
    <jsp:param value="<script type='text/javascript' src='/js/board.js'></script>" name="scripts"/>
</jsp:include>
    <h1>게시글 수정</h1>
    <!-- action : form 내부의 value를 전송할 엔드포인트 -->
    <form
      method="post"
      action="/update/${article.id}"
      enctype="multipart/form-data"
    >
      <input type="hidden" name="fileGroupId" value="${article.fileGroupId}">
      <div class="grid update">
        <label for="subject">제목</label>
        <input
          type="text"
          name="subject"
          id="subject"
          placeholder="제목을 입력하세요."
          value="${article.subject}"
        />
        <!-- <label for="email">이메일</label>
        <input
          type="email"
          name="email"
          id="email"
          placeholder="이메일을 입력하세요."
          value="${article.email}"
        /> -->

        <label for="attach-files">첨부파일</label>
        <div id="attach-files" class="attach-files">
          <ul class="vertical-list">
            <c:forEach items="${article.files}" var="file">
            <li>
              <input type="checkbox" name="deleteFileNum" value="${file.fileNum}" id="">
              <a href="/file/${file.fileGroupId}/${file.fileNum}">${file.displayName}</a>
            </li>
          </c:forEach>
          </ul>
          <input type="file" name="attachFile" id="" />
          <button type="button" class="add-file">+</button>
        </div>

        <label for="content">내용</label>
        <textarea name="content" id="content" placeholder="내용을 입력하세요.">
${article.content}</textarea
        >

        <div class="btn-group">
          <div class="right-align">
            <input type="submit" value="저장" />
          </div>
        </div>
      </div>
    </form>
<jsp:include page="/WEB-INF/views/templates/footer.jsp"></jsp:include>
