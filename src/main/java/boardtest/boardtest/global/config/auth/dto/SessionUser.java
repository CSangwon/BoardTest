package boardtest.boardtest.global.config.auth.dto;

import boardtest.boardtest.domain.member.entity.Member;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String name;
    private String username;
    private String picture;

    public SessionUser(Member member) {
        this.name = member.getName();
        this.username = member.getUsername();
        this.picture = member.getPicture();
    }

}
