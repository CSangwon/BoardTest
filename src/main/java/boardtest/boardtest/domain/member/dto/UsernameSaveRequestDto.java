package boardtest.boardtest.domain.member.dto;

import boardtest.boardtest.global.config.validation.ValidationGroups;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsernameSaveRequestDto {

    @NotBlank(message = "아이디 필수로 입력해야 합니다.", groups = ValidationGroups.NotEmptyGroup.class)
    @Pattern(regexp = "[a-zA-Z0-9]{2,15}",
            message = "아이디는 영문, 숫자 조합만 가능하며 2 ~ 15 자리 까지 가능합니다."
            , groups = ValidationGroups.PatternCheckGroup.class)
    private String username;

}
