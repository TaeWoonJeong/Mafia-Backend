package com.taewoon.mafia.repository

import com.taewoon.mafia.model.ChatMessage
import org.springframework.data.jpa.repository.JpaRepository

interface ChatMessageRepository : JpaRepository<ChatMessage,Int>{
}