package boardtest.boardtest.controller;

import boardtest.boardtest.domain.member.entity.Member;
import boardtest.boardtest.domain.member.entity.Role;
import boardtest.boardtest.domain.member.dto.MemberSaveRequestDto;
import boardtest.boardtest.domain.member.repository.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MemberApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;



    @AfterEach
    public void clean() throws Exception {
        memberRepository.deleteAll();
    }

    @Test
    public void 회원가입() throws Exception{
        //given
        String username = "test";
        String nickname = "nickname";

        MemberSaveRequestDto memberSaveRequestDto = MemberSaveRequestDto.builder()
                .username(username)
                .password(passwordEncoder.encode("1111"))
                .email("test@naver.com")
                .nickname(nickname)
                .role(Role.USER)
                .build();

        String url = "http://localhost:" + port + "/auth/api/v1/member";

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, memberSaveRequestDto, Long.class);
        //then

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Member> memberList = memberRepository.findAll();

        assertThat(memberList.get(0).getUsername()).isEqualTo(username);
        assertThat(memberList.get(0).getNickname()).isEqualTo(nickname);
    }
}