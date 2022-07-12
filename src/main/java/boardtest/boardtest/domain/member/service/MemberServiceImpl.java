package boardtest.boardtest.domain.member.service;

import boardtest.boardtest.domain.email.EmailMessage;
import boardtest.boardtest.domain.email.EmailService;
import boardtest.boardtest.domain.member.dto.MemberSaveRequestDto2;
import boardtest.boardtest.domain.member.dto.NicknameSaveRequestDto;
import boardtest.boardtest.domain.member.dto.UsernameSaveRequestDto;
import boardtest.boardtest.domain.member.entity.Member;
import boardtest.boardtest.domain.member.dto.MemberSaveRequestDto;
import boardtest.boardtest.domain.member.repository.MemberRepository;
import boardtest.boardtest.global.config.exception.ApiRequestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final TemplateEngine templateEngine;

    // 회원가입
    @Override
    public Member signUp(MemberSaveRequestDto member) {
        String encodePassword = passwordEncoder.encode(member.getPassword());
        member.setPassword(encodePassword);
        return memberRepository.save(member.toEntity());
    }

    // 회원가입2
    @Override
    public Member signUp2(MemberSaveRequestDto2 memberSaveRequestDto2) throws ApiRequestException {
        String encodePassword = passwordEncoder.encode(memberSaveRequestDto2.getPassword());
        memberSaveRequestDto2.setPassword(encodePassword);

        Member member = new Member();
        member.emailCheckTokenGenerate();
        log.info(member.getEmail(), member.getEmailCheckToken());

        return memberRepository.save(memberSaveRequestDto2.toEntity());
    }

    @Override
    public void update(MemberSaveRequestDto2 dto2) {

        Member member = memberRepository.findById(dto2.toEntity().getId()).orElseThrow(() ->
                new IllegalArgumentException("해당 회원이 존재하지 않습니다."));

        member.update2(dto2.getNickname(), passwordEncoder.encode(dto2.getPassword()));
    }

    private Member defaultMember(){

        String encodePassword = passwordEncoder.encode("tkddnjs8528##");
        MemberSaveRequestDto2 member = MemberSaveRequestDto2.builder()
                .username("swchoi1997")
                .password(encodePassword)
                .name("최상원")
                .email("swchoi1997@naver.com")
                .nickname("숲속의 냉면")
                .build();
        return memberRepository.save(member.toEntity());
    }

    //== 아이디 중복 == //
    @Override
    @Transactional(readOnly = true)
    public boolean checkUsernameDuplication(UsernameSaveRequestDto dto) {
        return memberRepository.existsByUsername(dto.getUsername());
    }

    //== 비밀번호 잊어버렸을때 ==//


    /**
     * 아이디와 이메일을 가져와서 둘다 존재하고 입력한값이랑 맞는지 매칭해봐서 맞으면 true
     *
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public boolean findUsernameCheck(String username, String email) {
        Optional<Member> optionalMemberUsername = memberRepository.findByUsername(username);
        Optional<Member> optionalMemberEmail = memberRepository.findByEmail(email);
        if (optionalMemberUsername.isPresent() && optionalMemberEmail.isPresent()) {
            if (optionalMemberUsername.get().getName().equals(username) && optionalMemberEmail.get().getEmail().equals(email)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    //== 닉네임 중복 ==//
    @Override
    public boolean checkNicknameDuplication(NicknameSaveRequestDto dto) {
        return memberRepository.existsByNickname(dto.getNickname());
    }

    @Override
    public void sendSignConfirmEmail(Member member) {
        Context context = new Context();
        context.setVariable("link", "http://localhost:8090/api/member/checkEmailToken/" + member.getEmailCheckToken() + "/" + member.getEmail());
        context.setVariable("nickname", member.getNickname());
        context.setVariable("linkName", "이메일 인증하기");
        context.setVariable("message", "서비스를 이용하시려면 링크를 클릭하세요");
        String message = templateEngine.process("mail/simple-link", context);

        EmailMessage emailMessage = EmailMessage.builder()
                .to(member.getEmail())
                .title("회원가입 인증")
                .message(message)
                .build();
        emailService.sendEmail(emailMessage);
    }
}

//    @Override
//    @Transactional(readOnly = true)
//    public void checkUsernameDuplication(UsernameSaveRequestDto dto2) throws ApiRequestException {
//        log.info(dto2.getUsername());
//        boolean usernameDup = memberRepository.existsByUsername(dto2.getUsername());
//
//        if (usernameDup){
//            throw new ApiRequestException("이미 존재하는 아이디 입니다.");
//        }
//    }


//    @Override
//    @Transactional(readOnly = true)
//    public void checkNicknameDuplication(MemberSaveRequestDto2 dto2) throws ApiRequestException{
//        boolean nicknameDup = memberRepository.existsByNickname(dto2.toEntity().getNickname());
//        if (nicknameDup){
//            throw new ApiRequestException("이미 존재하는 닉네임 입니다.");
//        }
//    }

