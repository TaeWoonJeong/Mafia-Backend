package com.taewoon.mafia.config.auth

import com.taewoon.mafia.model.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.oauth2.core.user.OAuth2User

class PrincipalDetails() : UserDetails,OAuth2User{
    constructor(user:User) : this() {
        this.user=user
    }
    constructor(user: User,attributes:Map<String,Object>):this(){
        this.user=user
        this.attributes=attributes
    }
    private lateinit var user: User
    private lateinit var attributes:Map<String,Object>

    fun getUser():User{
        return user
    }
    override fun getName(): String {
        return "그냥 로그인"
    }

    override fun getAttributes(): Map<String, Object> {
        return attributes
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        var collect:ArrayList<GrantedAuthority> = ArrayList()
        collect.add(GrantedAuthority{ user.role })
        return collect
    }

    override fun getPassword(): String {
        return user.password
    }

    override fun getUsername(): String {
        return user.username
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}