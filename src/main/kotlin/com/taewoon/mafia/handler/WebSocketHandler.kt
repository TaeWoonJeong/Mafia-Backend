package com.taewoon.mafia.handler

import com.taewoon.mafia.log.Log
import org.springframework.stereotype.Component
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler

@Component
class WebSocketHandler:TextWebSocketHandler() {
    companion object:Log
    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        val payload:String=message.payload
        logger.info("payload {}",payload)
        val textMessage:TextMessage= TextMessage("Welcome chatting Server")
        session.sendMessage(textMessage)
    }
}