'use strict';

let index = {
    init: function (){
        $("#btn-save").on("click", () => {
            let form = document.querySelector("#needs-validation");
            if (form.checkValidity() == false) {
                console.log("회원가입 안됨")
            } else{
                this.save();
            }
        });
    },
    save: function (){
        let data ={
            username: $('#username').val(),
            password: $('#password').val(),
            email: $('#email').val(),
            name: $('#name').val(),
            nickname: $('#nickname').val()
        }

        $.ajax({
            type: "POST",
            url: "/auth/api/v1/member",
            data: JSON.stringify(data),
            dataType: "json",
            contentType: "application/json; charset=utf-8"
        }).done(function (){
            alert("회원가입이 완료되었습니다.");
            window.location.href = "/";
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    },
    // login: function () {
    //     let data = {
    //         username: $("#username").val(),
    //         password: $("password").val()
    //     }
    //     $.ajax({
    //         type: "POST",
    //         url: "/auth/api/v1/login",
    //         data: JSON.stringify(data),
    //         dataType: "json",
    //         contentType: "application/json; charset=utf-8",
    //
    //     }).done(function (res){
    //         console.log(res);
    //         if(res.statusCode == 1){
    //             alert("로그인 되었습니다.");
    //             window.location.href = "/";
    //         }else{
    //             alert("아이디와 패스워드를 확인하세요");
    //         }
    //     }).fail(function (error){
    //         console.log("로그인 실패")
    //         alert(JSON.stringify(error));
    //     })
    // }
};
index.init();