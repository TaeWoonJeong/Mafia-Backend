package com.taewoon.mafia.repository

import com.taewoon.mafia.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository:JpaRepository<User,Int> {
    fun findByUsername(username:String):User?
}