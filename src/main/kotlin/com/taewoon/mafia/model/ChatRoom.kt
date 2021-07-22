package com.taewoon.mafia.model

import javax.persistence.*

//인증까지 추가해서 나중에 누가 만들었는지 추가하기
@Entity
@Table(name="chatRooms")
class ChatRoom(title:String) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Int?=null

    @Column(nullable=false,length=20)
    var title:String=title

    @OneToMany(mappedBy="chatRoom",fetch = FetchType.EAGER,cascade = [CascadeType.ALL],orphanRemoval = true)
    val messages:MutableList<ChatMessage>?=null
}