package boardtest.boardtest.repository;

import boardtest.boardtest.domain.member.entity.Member;
import boardtest.boardtest.domain.member.repository.MemberRepository;
import boardtest.boardtest.domain.member.entity.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void 회원가입_테스트() throws Exception{
        //given
        String username = "test";
        String nickname = "nickname";

        memberRepository.save(Member.builder()
                .username(username)
                .password("1111")
                .email("test@naver.com")
                .nickname(nickname)
                .role(Role.USER)
                .build());
        //when
        List<Member> memberList = memberRepository.findAll();

        //then
        Member member = memberList.get(0);
        assertThat(member.getUsername()).isEqualTo(username);
        assertThat(member.getNickname()).isEqualTo(nickname);
    }

}