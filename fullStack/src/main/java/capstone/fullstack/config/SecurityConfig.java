//package capstone.fullstack.config;
//
//import capstone.fullstack.jwt.CustomAuthenticationEntryPoint;
//import capstone.fullstack.jwt.JwtRequestFilter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//
//                .and()
//                .httpBasic().disable()
//                .formLogin().disable()
//                .addFilter(corsFilter);
//
//        http.authorizeRequests()
//                .requestMatchers(FRONT_URL+"/main/**")
//                .authenticated()
//                .anyRequest().permitAll()
//
//                .and()
//                //(1)
//                .exceptionHandling()
//                .authenticationEntryPoint(new CustomAuthenticationEntryPoint());
//
//        //(2)
//        http.addFilterBefore(new JwtRequestFilter(), UsernamePasswordAuthenticationFilter.class);
//    }
//}
