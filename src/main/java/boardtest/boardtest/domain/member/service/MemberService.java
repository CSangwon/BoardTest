package boardtest.boardtest.domain.member.service;

import boardtest.boardtest.domain.member.dto.*;
import boardtest.boardtest.domain.member.entity.Member;

public interface MemberService{
    Member signUp(MemberSaveRequestDto memberSaveRequestDto);

    Member signUp2(MemberSaveRequestDto2 memberSaveRequestDto2);

    void update(MemberSaveRequestDto2 memberSaveRequestDto2);

    boolean checkUsernameDuplication(UsernameSaveRequestDto dto);
    //    void checkUsernameDuplication(UsernameSaveRequestDto dto);
    boolean checkNicknameDuplication(NicknameSaveRequestDto dto);
    //    void checkNicknameDuplication(NicknameSaveRequestDto dto);

    //== 비밀번호 잊어버렸을 때 ==//

    //아이디 비빌번호가 DB에 있는지 확인
    boolean findUsernameCheck(String username, String email);

    //== 메일 보내기 ==//
    void sendSignConfirmEmail(Member member);




}
