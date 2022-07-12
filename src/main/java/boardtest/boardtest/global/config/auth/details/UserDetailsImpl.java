package boardtest.boardtest.global.config.auth.details;

import boardtest.boardtest.domain.member.entity.Member;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Getter
public class UserDetailsImpl implements UserDetails, OAuth2User {

    private Member member;
    private Map<String, Object> attributes;

    //일반 사용자
    public UserDetailsImpl(Member member) {
        this.member = member;
    }

    // OAuth 사용자
    public UserDetailsImpl(Member member, Map<String, Object> attributes) {
        this.member = member;
        this.attributes = attributes;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(() -> member.getRoleKey());

        return collection;
    }


    //사용자 비밀번호
    @Override
    public String getPassword() {
        return member.getPassword();
    }

    //사용자 아이디
    @Override
    public String getUsername() {
        return member.getUsername();
    }

    public String getNickname(){
        return member.getNickname();
    }

    public String getEmail(){
        return member.getEmail();
    }

    public Long getId(){
        return member.getId();
    }

    //계정 만료 여부 -> true(만료되지 않음)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //계정 잠김 여부 -> true(잠기지 않음)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //비밀번호 만료 여부 -> true(만료되지 않음)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //계정이 활성화 된건지 -> true(활성화)
    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }
}
