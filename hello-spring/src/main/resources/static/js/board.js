$().ready(function () {
  // ".add-file"을 클릭하면
  $(".attach-files").on("click", ".add-file", function () {
    //   $(".add-file").on("click", function () {
    // 새로운 파일이 추가될 때 마다 기존의 ".add-file"을 "del-file로 변경하고"
    // 텍스트는 "+" 에서 "-" 로 변경한다.
    $(this)
      .closest(".attach-files")
      .children(".add-file")
      .removeClass("add-file")
      .addClass("del-file")
      .text("-")
      .off("click") // 할당되어 있던 이벤트를 제거한다.
      .on("click", function () {
        // 버튼 왼쪽에 있는 input tag 삭제
        $(this).prev().remove();
        // 버튼도 삭제
        $(this).remove();
      }); // 새로운 이벤트를 추가한다.

    // 새로운 파일 input과 button을
    var fileInput = $("<input />");
    fileInput.attr({
      type: "file",
      name: "attachFile",
    });

    var addButton = $("<button />");
    addButton.attr("type", "button").addClass("add-file").text("+");

    // ".attach-files" 아래에 추가
    $(".attach-files").append(fileInput).append(addButton);
  });

  $("#writeVO").on("submit", function (event) {
    // 에러메시지 element 초기화
    $(this).find(".validation-error").remove();

    // submit callback event 제거
    event.preventDefault();

    // 게시글의 제목은 3글자 이상으로 작성
    var subject = $("#subject").val();
    if (subject.length < 3) {
      var subjectErrorMessage = $("<div>");
      subjectErrorMessage.addClass("validation-error");
      subjectErrorMessage.text(
        "게시글의 제목은 3글자 이상으로 작성해야 합니다.",
      );
      $("#subject").after(subjectErrorMessage);
    }

    if ($(".validation-error").length === 0) {
      this.submit();
    }
  });
});
