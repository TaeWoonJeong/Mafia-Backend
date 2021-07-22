package com.taewoon.mafia.controller

import com.taewoon.mafia.dto.ChatMessageDto
import com.taewoon.mafia.dto.enum.MessageType
import com.taewoon.mafia.service.ChatService
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.simp.SimpMessageSendingOperations
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class ChatController(
    private val messagingTemplate:SimpMessageSendingOperations
) {
    @MessageMapping("/chat/message")
    fun message(chatMessageDto: ChatMessageDto) {
        if(chatMessageDto.type.equals(MessageType.ENTER))
            chatMessageDto.message=chatMessageDto.sender+"님이 입장하셨습니다."
        messagingTemplate.convertAndSend("/sub/chat/room/"+chatMessageDto.roomId,chatMessageDto)
    }
}