$(function () {
    $("#form").on("submit", function () {
        if (this.checkValidity()) {
            var data = $(this).serialize();
            $.ajax({
                url: "/loginAction",
                type: "post",
                dataType: "json",
                data: data,
                success: function (json) {
                    if (json.success) {
                        window.location.href = json.result;
                    }else{
                        $("#prompt").text(json.msg).css("color","red");
                    }
                }
            })
        }
        return false;
    })
})