$().ready(function () {
  // 이메일 중복검사
  $("#email").on("blur", function () {
    $("#email + .duplicate-check").remove();

    var email = $(this).val();

    if (!email) {
      return;
    }

    fetch("/regist/check/duplicate/" + email)
      .then(function (data) {
        return data.json();
      })
      .then(function (json) {
        var emailDuplicationCheck = $("<div>");
        if (json.duplicate) {
          emailDuplicationCheck.text(email + "은 사용 불가능합니다.");
          emailDuplicationCheck.addClass("duplicate-check warning");
        } else {
          emailDuplicationCheck.text(email + "은 사용 가능합니다.");
          emailDuplicationCheck.addClass("duplicate-check pass");
        }

        $("#email").after(emailDuplicationCheck);
      });
  });

  // 비밀번호 일치 검사
  $("#password, #password-confirm").on("blur", function () {
    $(".passwordError").remove();

    var password = $("#password").val();
    var passwordConfirm = $("#password-confirm").val();

    if (password !== passwordConfirm) {
      var passwordErrMsg = $("<div>");
      passwordErrMsg.text("비밀번호가 일치하지 않습니다.");
      passwordErrMsg.addClass("passwordError warning");
      $("#password-confirm").after(passwordErrMsg);
    }
  });

  $("registVO").on("submit", function () {
    // submit callback prevent
    // validation check
    // submit
  });
});
