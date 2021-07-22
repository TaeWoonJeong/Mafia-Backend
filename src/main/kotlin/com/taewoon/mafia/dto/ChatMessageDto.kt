package com.taewoon.mafia.dto

import com.taewoon.mafia.dto.enum.MessageType

class ChatMessageDto(
    val type:MessageType,
    val roomId:String,
    val sender:String,
    var message:String
)