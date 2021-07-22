package com.taewoon.mafia.repository

import com.taewoon.mafia.model.ChatRoom
import org.springframework.data.jpa.repository.JpaRepository

interface ChatRoomRepository : JpaRepository<ChatRoom,Int> {
}