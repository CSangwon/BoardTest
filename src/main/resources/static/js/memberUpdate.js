
let index = {
    init: function () {
        $('btn-update').on('click', () => {
            let form = document.querySelector("needs-validation");
            if (form.checkValidity() == false) {
                console.log("회원수정 x")
            } else {
                this.update();
            }
        })
    },
    update: function (){
        let data = {
            id: $('#id').val(),
            password: $('#password').val(),
            nickname: $('#nickname').val()
        }

        $.ajax({
            type: "PUT",
            url: "/auth/api/v1/member",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function (res) {
            alert("회원수정이 완료되었습니다.");
            location.href = "/";
        }).fail(function (err) {
            alert(JSON.stringify(err));
        });
    }

}