package com.taewoon.mafia.config.oauth2

import com.taewoon.mafia.config.auth.PrincipalDetails
import com.taewoon.mafia.config.oauth2.provider.*
import com.taewoon.mafia.model.User
import com.taewoon.mafia.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Lazy
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.core.OAuth2AuthenticationException
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service
import kotlin.jvm.Throws

@Service
class PrincipalOauth2UserService(
    @Lazy private val bCryptPasswordEncoder: BCryptPasswordEncoder,
    private val userRepository: UserRepository)
    : DefaultOAuth2UserService() {

//    @Autowired
//    private lateinit var bCryptPasswordEncoder: BCryptPasswordEncoder

    @Throws(OAuth2AuthenticationException::class)
    override fun loadUser(userRequest: OAuth2UserRequest?): OAuth2User {
        println("왜안되지??")
        println("getClientRegistration : "+userRequest?.clientRegistration)
        println("getAccessToken : "+userRequest?.accessToken?.tokenValue)
        val oAuth2User:OAuth2User=super.loadUser(userRequest)
        println("getAttributes : "+oAuth2User.attributes)

        var oAuth2UserInfo: OAuth2UserInfo? =null
        if(userRequest?.clientRegistration?.registrationId.equals("google")) {
            println("구글 로그인 요청")
            oAuth2UserInfo=GoogleUserInfo(oAuth2User.attributes as Map<String, Object>)
        }
        else if(userRequest?.clientRegistration?.registrationId.equals("facebook")) {
            println("페이스북 로그인 요청")
            oAuth2UserInfo=FacebookUserInfo(oAuth2User.attributes as Map<String, Object>)
        }
        else if(userRequest?.clientRegistration?.registrationId.equals("naver")) {
            println("네이버 로그인 요청")
            oAuth2UserInfo=NaverUserInfo(oAuth2User.attributes["response"] as Map<String, Object>)
        } else if(userRequest?.clientRegistration?.registrationId.equals("kakao")) {
            println("카카오 로그인 요청")
            oAuth2UserInfo= KakaoUserInfo(oAuth2User.attributes as Map<String,Object>)
        }
        else {
            println("등록되지않은 오류")
        }

        val provider = oAuth2UserInfo?.getProvider()
        val providerId = oAuth2UserInfo?.getProviderId()
        val username = provider + "_" + providerId //naver_숫자
        val password = bCryptPasswordEncoder.encode("태운")
        val email = oAuth2UserInfo!!.getEmail()
        val role = "ROLE_USER"
        println("실험용:"+userRepository.findByUsername(username))
        var userEntity:User?=userRepository.findByUsername(username)
        if(userEntity==null) {
            println("OAuth 로그인이 최초입니다.")
            userEntity=User(username,password,email,role,provider,providerId)
            userRepository.save(userEntity)
        } else {
            println("회원가입이 되어있습니다.")
        }
        return PrincipalDetails(userEntity, oAuth2User.attributes as Map<String, Object>)
    }

}