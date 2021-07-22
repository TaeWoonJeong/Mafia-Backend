package com.taewoon.mafia.config

import com.taewoon.mafia.config.oauth2.PrincipalOauth2UserService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.lang.Exception
import kotlin.jvm.Throws

@Configuration
@EnableWebSecurity
class SecurityConfig(private val principalOauth2UserService:PrincipalOauth2UserService): WebSecurityConfigurerAdapter() {

    @Bean
    fun encodePwd(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.csrf().disable()
        http.authorizeRequests()
            .antMatchers("/user/**").authenticated()
            .antMatchers("/manager/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
            .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
            .anyRequest().permitAll()
            .and()
            .formLogin()
            .loginPage("/loginForm")
            .loginProcessingUrl("/login") // /login주소가 호출이 되면 시큐리티가 낚아채서 대신 로그인 진행
            .defaultSuccessUrl("/")
            .and()
            .oauth2Login()
            .loginPage("/loginForm")
            .userInfoEndpoint()
            .userService(principalOauth2UserService)
    }
}