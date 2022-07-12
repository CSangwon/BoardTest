package boardtest.boardtest.domain.member.dto;

import boardtest.boardtest.domain.BaseEntity;
import boardtest.boardtest.domain.member.entity.Member;
import boardtest.boardtest.domain.member.entity.Role;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MemberSaveRequestDto extends BaseEntity {

    private String username;
    private String password;
    private String name;
    private String email;
    private String nickname;
    private Role role;

    public Member toEntity(){
        return Member.builder()
                .username(username)
                .password(password)
                .name(name)
                .email(email)
                .nickname(nickname)
                .role(Role.USER)
                .build();
    }
}
