package com.taewoon.mafia.controller

import com.taewoon.mafia.domain.Chat
import com.taewoon.mafia.domain.Greeting
import com.taewoon.mafia.domain.HelloMessage
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller
import org.springframework.web.util.HtmlUtils
import kotlin.jvm.Throws

@Controller
class GreetingController {
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    @Throws(Exception::class)
    fun greeting(message:HelloMessage):Greeting {
//        Thread.sleep(100)
        println(message.name)
        return Greeting("Hello, "+message.name)
    }

    @MessageMapping("/chat")
    @SendTo("/topic/chat")
    @Throws(Exception::class)
    fun chat(chat: Chat):Chat {
        return Chat(chat.name,chat.message)
    }
}