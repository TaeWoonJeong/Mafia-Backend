package com.taewoon.mafia.config.oauth2.provider

class NaverUserInfo(attributes:Map<String,Object>):OAuth2UserInfo {

    val attributes=attributes

    override fun getProviderId(): String {
        return attributes["id"] as String
    }

    override fun getProvider(): String {
        return "naver"
    }

    override fun getEmail(): String {
        return attributes["email"] as String
    }

    override fun getName(): String {
        return attributes["name"] as String
    }

}