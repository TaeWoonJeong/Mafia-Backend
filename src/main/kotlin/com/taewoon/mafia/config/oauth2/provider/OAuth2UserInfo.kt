package com.taewoon.mafia.config.oauth2.provider

interface OAuth2UserInfo {
    fun getProviderId():String
    fun getProvider():String
    fun getEmail():String
    fun getName():String
}