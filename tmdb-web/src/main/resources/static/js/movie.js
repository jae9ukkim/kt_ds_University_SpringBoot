/**
 *
 */
$().ready(function () {
  $("#writeVO").on("submit", function (event) {
    $(this).find(".validation-error").remove();
    // submit callback event 막기
    event.preventDefault();

    var budget = $("#budget");
    numberCheck(budget);
    var profit = $("#profit");
    numberCheck(profit);
    var runningTime = $("#runningTime");
    numberCheck(runningTime);
    
    
    var posterUrl = $("#posterUrl");
    validationCheck(posterUrl, "포스터 URL을 입력해 주세요.");
    var title = $("#title");
    validationCheck(title, "제목을 입력해 주세요.");
    var state = $("#state");
    validationCheck(state, "상태를 선택해 주세요.");
    var language = $("#language");
    validationCheck(language, "원어를 입력해 주세요.");
    var sysnopsis = $("#sysnopsis");
    validationCheck(sysnopsis, "개요를 입력해 주세요.");

    if ($(".validation-error").length === 0) {
      this.submit();
    }
  });

  function validationCheck(selector, errMsg) {
    if (!selector.val()) {
      var errorMessage = $("<div>");
      errorMessage.addClass("validation-error");
      errorMessage.text(errMsg);
      selector.after(errorMessage);
    }
  }
  
  function numberCheck(selector) {
    if(!selector.val()) {
        selector.val(0);
    }
  }
});
