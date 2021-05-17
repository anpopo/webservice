package com.anpopo.config.auth;

import com.anpopo.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().headers().frameOptions().disable()  // h2-console 사용을 위한 옵션  disable
                .and()
                .authorizeRequests()  // url 별 권한 관리를 설정하는 옵션 시작
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()  // 해당 url 은 전체 열람 가능
                .antMatchers("/api/v1/**").hasRole(Role.USER.name())  // 해당 api콜은 user 권한을 가진 사람만 가능
                .anyRequest().authenticated()  // 나머지 사용자들은 인증 사용자들만 허용
                .and()
                .logout()  // 로그아웃 설정 진입점
                .logoutSuccessUrl("/")  // 성공시 / 로 이동
                .and()
                .oauth2Login()  // OAuth2 로그인 기능에 대한 설정 진입
                .userInfoEndpoint()  // 로그인 이후 사용자 정보 가져올 설정 담당
                .userService(userService);  // 소셜 로그인 성공시 후속 조치를 진행할 인터페이스 구현체를 등록한다.
                                            // 리소스 서버(소셜서비스들)에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능을 명시할 수 있따.
    }
}
