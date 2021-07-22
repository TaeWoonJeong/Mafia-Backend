package com.taewoon.mafia.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.taewoon.mafia.dto.ChatRoomDto
import org.springframework.stereotype.Service

@Service
class ChatService(
    private val objectMapper:ObjectMapper,
    private val chatRooms:LinkedHashMap<String,ChatRoomDto>
) {

}