$(function () {
    $("#form").on("submit", function () {
        if (this.checkValidity()) {
            $.ajax({
                url: "/login",
                type: "post",
                dataType: "json",
                data: {
                    username: $("#username").val(),
                    password: $("#password").val()
                },
                success: function (json) {
                    if (json.success) {
                        window.location.href = "/notoeat";
                    }else{
                        $("#prompt").text(json.msg).css("color","red");
                    }
                }
            })
        }
        return false;
    })
})