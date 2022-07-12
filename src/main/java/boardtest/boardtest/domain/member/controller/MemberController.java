package boardtest.boardtest.domain.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {

    @GetMapping("/auth/member/save")
    public String userSave() {
        return "member/member-save";
    }

    @GetMapping("/auth/member/save2")
    public String userSave2() {
        return "member/member-save-ver2";
    }

    @GetMapping("/auth/member/save3")
    public String userSave3() {
        return "member/member-save-3";
    }


    @GetMapping("/auth/member/login")
    public String memberLogin() {
        return "member/member-login";
    }

    @GetMapping("/auth/member/update")
    public String userUpdate(){
        return "member/member-update";
    }
}
