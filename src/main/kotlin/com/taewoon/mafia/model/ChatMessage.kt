package com.taewoon.mafia.model

import javax.persistence.*

@Entity
@Table(name="chatMessages")
class ChatMessage(content:String,chatRoom: ChatRoom) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Int?=null

    @Column(nullable=false,length=300)
    var content:String=content

    @ManyToOne
    @JoinColumn(name="roomNo")
    val chatRoom:ChatRoom=chatRoom
}