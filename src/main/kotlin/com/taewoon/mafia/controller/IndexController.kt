package com.taewoon.mafia.controller

import com.taewoon.mafia.repository.UserRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.security.access.prepost.PreAuthorize

import org.springframework.security.access.annotation.Secured

import org.springframework.web.bind.annotation.PostMapping

import com.taewoon.mafia.config.auth.PrincipalDetails
import com.taewoon.mafia.model.User
import org.springframework.security.core.Authentication
import org.springframework.security.core.annotation.AuthenticationPrincipal

import org.springframework.security.oauth2.core.user.OAuth2User




@Controller
class IndexController(private val userRepository: UserRepository) {
    lateinit var bCryptPasswordEncoder: BCryptPasswordEncoder

    @GetMapping("/test/login")
    @ResponseBody
    fun testLogin(
        authentication: Authentication, @AuthenticationPrincipal userDetails: PrincipalDetails
    ): String? {
        println("/test/login ===============")
        val principalDetails = authentication.principal as PrincipalDetails
        println("authentication : " + principalDetails.getUser())
        println("userDetails : " + userDetails.getUser())
        return "세션 정보 확인하기"
    }

    @GetMapping("/test/oauth/login")
    @ResponseBody
    fun testOAuthLogin(
        authentication: Authentication, @AuthenticationPrincipal oAuth: OAuth2User
    ): String? {
        println("/test/oauth/login ===============")
        val oAuth2User = authentication.getPrincipal() as OAuth2User
        println("authentication : " + oAuth2User.attributes)
        println("oAuth2User : " + oAuth.attributes)
        return "OAuth 세션 정보 확인하기"
    }

    @GetMapping("/")
    fun index(): String? {
        return "index"
    }

    // OAuth로그인을 해도 PrincipalDetails
    // 일반로그인을 해도 PrincipalDetails
    // 위에 처럼 분기할 필요가 없다
    @GetMapping("/user")
    @ResponseBody
    fun user(@AuthenticationPrincipal principalDetails: PrincipalDetails): String? {
        println("aaaaa")
        System.out.println("principalDetails : " + principalDetails.getUser())
        return "user"
    }

    @GetMapping("/admin")
    @ResponseBody
    fun admin(): String? {
        return "admin"
    }

    @GetMapping("/manager")
    @ResponseBody
    fun manager(): String? {
        return "manager"
    }

    @GetMapping("/loginForm")
    fun loginForm(): String? {
        return "loginForm"
    }

    @GetMapping("/joinForm")
    fun joinForm(): String? {
        return "joinForm"
    }

    @PostMapping("/join")
    fun join(user: User): String? {
        println(user)
        user.role="ROLE_USER"
        val rawPassword: String = user.password
        val encPassword = bCryptPasswordEncoder.encode(rawPassword)
        user.password=encPassword
        userRepository.save(user)
        return "redirect:/loginForm" //redirect를 붙일시 loginForm이라는 함수가 호출됨
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/info")
    @ResponseBody
    fun info(): String? {
        return "개인정보"
    }

    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/data")
    @ResponseBody
    fun data(): String? {
        return "개인정보"
    }
}