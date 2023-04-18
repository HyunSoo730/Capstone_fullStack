//package capstone.fullstack.jwt;
//
//import capstone.fullstack.repository.UserRepository;
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.algorithms.Algorithm;
//import com.auth0.jwt.exceptions.JWTVerificationException;
//import com.auth0.jwt.exceptions.TokenExpiredException;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//@RequiredArgsConstructor
//@Component
//public class JwtRequestFilter extends OncePerRequestFilter {
//
//    private final 2UserRepository userRepository;
//
//    @Override //(3)
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//
//        //(4)
//        String jwtHeader = ((HttpServletRequest)request).getHeader(JwtProperties.HEADER_STRING);
//
//        //(5)
//        if(jwtHeader == null || !jwtHeader.startsWith(JwtProperties.TOKEN_PREFIX)) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        //(6)
//        String token = jwtHeader.replace(JwtProperties.TOKEN_PREFIX, "");
//
//        Long userId = null;
//
//        //(7)
//        try {
//            userId = JWT.require(Algorithm.HMAC512(JwtProperties.SECRET)).build().verify(token)
//                    .getClaim("id").asLong();
//
//        } catch (TokenExpiredException e) {
//            e.printStackTrace();
//            request.setAttribute(JwtProperties.HEADER_STRING, "토큰이 만료되었습니다.");
//        } catch (JWTVerificationException e) {
//            e.printStackTrace();
//            request.setAttribute(JwtProperties.HEADER_STRING, "유효하지 않은 토큰입니다.");
//        }
//
//        //(8)
//        request.setAttribute("userId", userId);
//
//        //(9)
//        filterChain.doFilter(request, response);
//    }
//}