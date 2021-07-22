package com.taewoon.mafia.controller

import com.taewoon.mafia.service.ChatService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/chat")
class ChatRoomController(chatService: ChatService) {
    @GetMapping("/room")
    fun rooms(model:Model):String {
        return "/chat/room"
    }

}