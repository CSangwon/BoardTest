package boardtest.boardtest.domain.member.controller;

import boardtest.boardtest.domain.member.dto.MemberSaveRequestDto2;
import boardtest.boardtest.domain.member.dto.NicknameSaveRequestDto;
import boardtest.boardtest.domain.member.dto.UsernameSaveRequestDto;
import boardtest.boardtest.domain.member.entity.Member;
import boardtest.boardtest.domain.member.repository.MemberRepository;
import boardtest.boardtest.domain.member.service.MemberService;
import boardtest.boardtest.global.config.exception.ApiRequestException;
import boardtest.boardtest.global.config.jwt.JwtTokenProvider;
import boardtest.boardtest.global.config.validation.ValidationSequence;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;

@Slf4j
@RequiredArgsConstructor
@RestController
public class MemberApiController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

//    //회원가입
//    @PostMapping("/auth/api/v1/member")
//    public Member signUp(@Valid @RequestBody MemberSaveRequestDto memberSaveRequestDto) {
//        return memberService.signUp(memberSaveRequestDto);
//    }

    //회원가입2
//    @PostMapping("/auth/api/v1/member")
//    public Member signUp2(@Validated(ValidationSequence.class) @RequestBody MemberSaveRequestDto2 memberSaveRequestDto) {
//        return memberService.signUp2(memberSaveRequestDto);
//    }

    @PostMapping("/auth/api/v1/member")
    public ResponseEntity<?> signup3(@Validated(ValidationSequence.class) @RequestBody MemberSaveRequestDto2 memberSaveRequestDto){
        memberService.signUp2(memberSaveRequestDto);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @PutMapping("/auth/api/v1/member/update")
    public ResponseEntity<String> update(@RequestBody MemberSaveRequestDto2 dto2) {
        memberService.update(dto2);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @PostMapping("/auth/api/v1/member-username")
    public boolean checkUsername(@Valid @RequestBody UsernameSaveRequestDto dto, Errors errors){
        if(errors.hasErrors()){
            return false;
        }
        return memberService.checkUsernameDuplication(dto);
    }

    @PostMapping("/auth/api/v1/member-nickname")
    public boolean checkNickname(@Valid @RequestBody NicknameSaveRequestDto dto){
        return memberService.checkNicknameDuplication(dto);
    }

}
