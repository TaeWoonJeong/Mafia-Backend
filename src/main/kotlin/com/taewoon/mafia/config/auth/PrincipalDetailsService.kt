package com.taewoon.mafia.config.auth

import com.taewoon.mafia.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import kotlin.jvm.Throws

@Service
class PrincipalDetailsService(private val userRepository: UserRepository):UserDetailsService {

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails? {
        println("username :"+username)
        val userEntity=userRepository.findByUsername(username)
        if(userEntity!=null) {
            PrincipalDetails(userEntity)
        }
        return null
    }

}