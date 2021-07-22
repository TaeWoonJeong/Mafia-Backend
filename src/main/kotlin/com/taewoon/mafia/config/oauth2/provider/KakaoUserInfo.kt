package com.taewoon.mafia.config.oauth2.provider

class KakaoUserInfo(attributes:Map<String,Object>):OAuth2UserInfo  {
    private val attributes=attributes
    private val kakaoAccount=attributes["kakao_account"] as Map<String,Object>
    override fun getProviderId(): String {
        return attributes["id"].toString()
    }

    override fun getProvider(): String {
        return "kakao"
    }

    override fun getEmail(): String {
        return  kakaoAccount["email"] as String
    }

    override fun getName(): String {
        return kakaoAccount["profile"] as String
    }
}