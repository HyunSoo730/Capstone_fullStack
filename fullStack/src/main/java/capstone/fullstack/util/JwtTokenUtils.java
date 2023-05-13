package capstone.fullstack.util;

import capstone.fullstack.domain.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Slf4j
public class JwtTokenUtils {


    public static String generateToken(User user, String secretKLey, Long expireTime) {
        Claims claims = Jwts.claims();
        claims.put("userId", String.valueOf(user.getUserId()));   //유니크한 값을 userId로 설정. 이걸로 찾아야함.
        Date date = new Date(System.currentTimeMillis());
        Date expireDate = new Date(System.currentTimeMillis() + expireTime);
        Key key = getKey(secretKLey);


        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(date)
                .setExpiration(expireDate)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }


    //여기서 userId반환.
    public static Long fetchUserId(String token, String key) {
        return Long.valueOf(extractClaims(token, key).get("userId", String.class));
        //클레임명에 넣어뒀던 userId로부터 추출.
    }

    /**
     * 토큰 유효 여부 확인
     */
    public Boolean isValidToken(String token, User user, String key) {
        log.info("토큰 유효성 검증 ={}", token);
        Long userId = getUserIdFromToken(token, key);
        boolean check = userId.equals(user.getUserId()) && isExpired(token, key);
        return check;
    }

    /**
     * Cliam에서 userId 가져오기
     * 토큰과 시크릿 키를 파라미터로.
     */
    public Long getUserIdFromToken(String token, String key) {
        String id = String.valueOf(extractClaims(token, key).get("userId", String.class));
        Long userId = Long.valueOf(id);
        return userId;
    }

    /**
     * 토큰 만료되었는지 확인.
     */
    public static boolean isExpired(String token, String key) {
        Claims claims = extractClaims(token, key);   //Claims 가져와서
        Date expiredDate = claims.getExpiration();  //만료기한 꺼냄.

        return expiredDate.before(new Date()); //그후 만료기한이 지났는지 확인.
    }

    /**
     * JWT 토큰을 복호화해서 토큰에 들어있는 정보를 꺼내는 메서드
     * 즉 토큰의 Claim 디코딩
     */
    private static Claims extractClaims(String token, String key) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey(key))
                .build()
                .parseClaimsJws(token)
                .getBody();
        // 토큰으로 사용자 claims 반환? --> 여기서 uesrId 추출?
    }

    /**
     * 시크릿 키 복호화 ???
     */
    private static Key getKey(String key) {
        byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}