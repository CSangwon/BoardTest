package boardtest.boardtest.global.config.jwt;


import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {


    @Value("${spring.jwt.secretKey}")
    private String secretKey;

    //토큰 유효시간 30분
    private long tokenValidTime = 30 * 60 * 1000L;

    private final UserDetailsService userDetailsService;

    @PostConstruct // 객체 초기화 secretKey를 Base64로 인코딩
    protected void init(){
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    //JWT token create
    public String createToken(String userPk, String roles) {
        Claims claims = Jwts.claims().setSubject(userPk); // JWT payload에 저장되는 정보단위
        claims.put("roles", roles); // 정보는 key / value 쌍으로 지정된다.
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims) // 정보저장
                .setIssuedAt(now) // 토근 발행시간 정보
                .setExpiration(new Date(now.getTime() + tokenValidTime)) //set Expire Time
                .signWith(SignatureAlgorithm.ES256, secretKey) // 사용할 암호화 알고리즘 Es256, signature에 들어갈 secret value setting
                .compact();
    }

    // Jwt토근에서 인증 정보 조회
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    //토근에서 회원정보 추출
    private String getUsername(String token) {
        try{
            return Jwts.parser().setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        }catch(ExpiredJwtException e){
            return e.getClaims().getSubject();
        }
    }

    //Request의 Header에서 token 값 가져오기
    public String resolveToken(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getHeader("X-AUTH-TOKEN");
    }

    //토근의 유효성 + 만료일자 확인
    public boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }
}
