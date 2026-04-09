$().ready(function () {
  $("#writeVO").on("submit", function (event) {
    event.preventDefault();
    $(".validation-error").remove();

    var actorName = $("#actorName").val();

    if (!actorName) {
      var emptyDiv = $("<div>");   
      emptyDiv.addClass("validation-error");   
      var errorMessage = $("<div>");
      errorMessage.addClass("validation-error");
      errorMessage.text("배우 이름을 작성해주세요.");
      $("#actorName").after(errorMessage).after(emptyDiv);
    }
    if ($(".validation-error").length === 0) {
      this.submit();
    }
  });
});
