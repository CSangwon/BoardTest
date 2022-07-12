let isPossibleUsername = false;
let isPossibleNickname = false;
let checkPasswordCheck = false;
let checkedUsername;
let checkedNickname;

function checkUsername(username) {
    let regExp = /^([a-zA-Z0-9]){2,15}$/;
    return regExp.test(username);
}

function checkPassword(password) {
    let regExp = /^(?=.*\d)(?=.*[0-9a-zA-Z])(?=.*[~!@#$%^&*()=+])[0-9a-zA-Z\d~!@#$%^&*()=+]{8,16}$/;
    return regExp.test(password);
}

function checkEmail(email) {
    let regExp = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
    return regExp.test(email);
}

function checkName(name){
    let regExp = /^[가-힣]{2,4}$/;
    return regExp.test(name)
}

function checkNickname(nickname){
    let regExp = /^[가-힣|a-z|A-Z|0-9|]{4,10}$/
    return regExp.test(nickname)
}


let index = {
    init: function (){
        $("#btn-save").on("click", () => {
            this.save()
        });

        $("#username-valid").on("click", () => {
            this.usernameValid()
        });

        $("#nickname-valid").on("click", () => {
            this.nicknameValid()
        });

        $("#checkPasswordEqual").keyup(() => {
            this.checkPasswordEqual()
        })
    },
    /* 회원가입 */
    save: function (){
        let data ={
            username: $('#username').val(),
            password: $('#password').val(),
            email: $('#email').val(),
            name: $('#name').val(),
            nickname: $('#nickname').val()
        }

        if(!isPossibleUsername || checkedUsername != data.username){
            isPossibleUsername = false;
            return alert("아이디 중복을 확인해주세요")
        }

        if(!isPossibleNickname || checkedNickname != data.nickname){
            isPossibleNickname = false;
            return alert("닉네임 중복을 확인해주세요")
        }



        if (!data.username) {
            return alert("아이디를 입력해주세요.")
        } else if (!data.password) {
            return alert("비밀번호를 입력해주세요.")
        } else if (!data.email) {
            return alert("이메일을 입력해주세요.")
        } else if (!data.name) {
            return alert("이름을 입력해주세요.")
        } else if (!data.nickname) {
            return alert("닉네임을 입력하세요");
        }

        if (!checkUsername(data.username)) {
            return alert("아이디는 영문, 숫자만 가능하며 2 ~ 10자리까지 가능합니다.")
        }else if (!checkPassword(data.password)) {
            return alert("비밀번호는 영문과 숫자 조합으로 8 ~ 16자리까지 가능합니다.")
        } else if (!checkEmail(data.email)) {
            return alert("올바르지 않은 이메일 형식입니다.")
        } else if (!checkName(data.name)) {
            return alert("올바른 이름을 입력하세요")
        } else if (!checkNickname(data.nickname)) {
            return alert("닉네임은 한글, 영어, 숫자만 4 ~ 10자리로 입력 가능합니다")
        } else if (checkPasswordCheck == false){
            return alert("비밀번호를 확인해주세요")
        }

        $.ajax({
            type: 'POST',
            url: `/auth/api/v1/member`,
            data: JSON.stringify(data),
            contentType: 'application/json; charset=utf-8',
            dataType: "json"
        }).done(function (res) {
            alert("회원가입이 완료되었습니다.");
            alert(res + "여기 데이터가 안들어오는건가....")
            location.href = "/";
        }).fail(function (error) {
            alert(JSON.stringify(error) + "여기는 오류 처리의 현장입니다. 제발 확인해주세요");
        });
    },

    usernameValid: function (){
        let data ={
            username: $('input#username').val()
        };

        if (data.username == ""){
            return;
        }
        if (!checkUsername(data.username)) {
            return alert("아이디는 영문, 숫자만 가능하며 2 ~ 10자리까지 가능합니다.")
        }

        $.ajax({
            type: 'POST',
            url: `/auth/api/v1/member-username`,
            data: JSON.stringify(data),
            contentType: 'application/json; charset=utf-8',
        }).done(function (response) {
            if (!response){
                isPossibleUsername = true;
                checkedUsername = data.username;
                $('.checkUsername1').css("display", "inline-block");
                $('.checkUsername2').css("display", "none");
                alert("사용가능한 아이디입니다.");
            } else{
                $('.checkUsername1').css("display", "none");
                $('.checkUsername2').css("display", "inline-block");
                alert("이미 존재하는 아이디입니다.");
            }

        }).fail(function (error) {
            console.log(JSON.stringify(error));
        });
    },

    nicknameValid: function (){
        let data ={
            nickname: $('#nickname').val()
        }

        if (data.nickname == "") {
            return;
        }
        if (!checkNickname(data.nickname)) {
            return alert("닉네임은 한글, 영어, 숫자만 4 ~ 10자리로 입력 가능합니다");
        }

        $.ajax({
            type: 'POST',
            url: `/auth/api/v1/member-nickname`,
            data: JSON.stringify(data),
            contentType: 'application/json; charset=utf-8'
            // dataType: "json"  여기서 계속 오류가난다 왜냐면 데이터타입이 불린인데 json으로 받아와서 그럴걸 아마
        }).done(function (response) {
            if (!response){
                isPossibleNickname = true;
                checkedNickname = data.nickname;
                $('.checkNickname1').css("display", "inline-block");
                $('.checkNickname2').css("display", "none");
                alert("사용가능한 닉네임입니다.");
            } else{
                $('.checkNickname1').css("display", "none");
                $('.checkNickname2').css("display", "inline-block");
                alert("이미 존재하는 닉네임입니다.");
            }
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    // 일단 비밀번호 확인은 프론트에서만 진행하려고함
    // 이유 비밀번호와 같이 민감한 정보는 단순비교를 통해 진행하는게 좋을거같다는 내 생각
    checkPasswordEqual: function (){
        if ($('#password').val() !== $('#checkPasswordEqual').val()){
            $('.checkPw2').css("display", "inline-block");
            $('.checkPw1').css("display", "none");
        } else{
            $('.checkPw1').css("display", "inline-block");
            $('.checkPw2').css("display", "none");
            checkPasswordCheck = true
        }
    }



}

index.init();



// {"readyState":4,
// "responseText":"{\"message\":\"이미 존재하는 아이디 입니다.\",\"httpStatus\":\"BAD_REQUEST\"}",
// "responseJSON":{"message":"이미 존재하는 아이디 입니다.","httpStatus":"BAD_REQUEST"},
// "status":400,"statusText":"error"}