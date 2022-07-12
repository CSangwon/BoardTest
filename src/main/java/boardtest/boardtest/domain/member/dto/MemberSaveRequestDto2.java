package boardtest.boardtest.domain.member.dto;

import boardtest.boardtest.domain.BaseEntity;
import boardtest.boardtest.domain.member.entity.Member;
import boardtest.boardtest.domain.member.entity.Role;
import boardtest.boardtest.global.config.validation.ValidationGroups;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberSaveRequestDto2 extends BaseEntity {

    @NotBlank(message = "아이디 필수로 입력해야 합니다.", groups = ValidationGroups.NotEmptyGroup.class)
    @Pattern(regexp = "[a-zA-Z0-9]{2,15}",
            message = "아이디는 영문, 숫자 조합만 가능하며 2 ~ 15 자리 까지 가능합니다."
            , groups = ValidationGroups.PatternCheckGroup.class)
    private String username;

    @NotBlank(message = "비밀번호는 필수로 입력해야 합니다.", groups = ValidationGroups.NotEmptyGroup.class)
    @Pattern(regexp = "^(?=.*\\d)(?=.*[0-9a-zA-Z])(?=.*[~!@#$%^&*()=+])[0-9a-zA-Z\\d~!@#$%^&*()=+]{8,16}",
            message = "비밀번호는 영문과 숫자 특수문자 조합으로 8 ~ 16자리까지 가능합니다."
            , groups = ValidationGroups.PatternCheckGroup.class)
    private String password;

    @NotBlank(message = "이름은 필수 입력값입니다.", groups = ValidationGroups.NotEmptyGroup.class)
    @Pattern(regexp = "[가-힣]{2,4}",
            message = "올바른 이름을 입력해주세요"
            , groups = ValidationGroups.PatternCheckGroup.class)
    private String name;

    @NotBlank(message = "이메일은 필수로 입력해야 합니다.", groups = ValidationGroups.NotEmptyGroup.class)
    @Pattern(regexp = "^[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*\\.[a-zA-Z]{2,3}",
            message = "올바르지 않은 이메일 형식입니다."
            , groups = ValidationGroups.PatternCheckGroup.class)
    private String email;

    @NotBlank(message = "닉네임을 입력해주세요", groups = ValidationGroups.NotEmptyGroup.class)
    @Pattern(regexp = "^[가-힣|a-z|A-Z|0-9|]{4,10}",
            message = "닉네임은 한글, 영어, 숫자만 4 ~10자리로 입력 가능합니다"
            , groups = ValidationGroups.PatternCheckGroup.class)
    private String nickname;

    private Role role;


    // 갑자기 드는 의문점 여기서 보내줄 때 부터 암호화를 해줘야하는거 아닌가?
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
