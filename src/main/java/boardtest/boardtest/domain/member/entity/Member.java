package boardtest.boardtest.domain.member.entity;

import boardtest.boardtest.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100, unique = true)
    private String username; // 아이디

    @Column(nullable = true, length = 100)
    private String password; // 소셜 로그인시 비밀번호가 들어오지 않기 때문에 null 로 만들어주었음

    @Column(nullable = false, length = 50)
    private String email;

    @Column(nullable = false, length = 20)
    private String nickname;

    @Column(nullable = false)
    private String name;

    private String picture; // Social 일때만 사용

    private boolean fromSocial;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    // 이에일 인증 여부
    private boolean emailVerified;

    // 이메일 인증 토큰
    private String emailCheckToken;

    // 이메일 인증 토큰 발급 시간
    private LocalDateTime emailCheckTokenGeneratedAt;

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoleKey(){
        return this.role.getKey();
    }

    public Member update(String name, String picture) {
        this.name = name;
        this.picture = picture;
//        this.nickname = nickname;

        return this;
    }

    public void update2(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
    }

    public void emailCheckTokenGenerate(){
        this.emailCheckToken = UUID.randomUUID().toString();
        this.emailCheckTokenGeneratedAt = LocalDateTime.now();
    }

    public void completeSignup(){
        this.emailVerified = true;
    }

    public boolean isValidToken(String token){
        return this.emailCheckToken.equals(token);
    }

    public boolean canSendConfirmEmail(){
        return this.emailCheckTokenGeneratedAt.isBefore(LocalDateTime.now().minusMinutes(30));
    }



}
