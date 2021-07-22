package com.taewoon.mafia.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class User (username:String,
            password:String,
            email:String,
            role:String,
            provider:String?,
            providerId:String?
) :CreatedAtEntity() {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    val id:Int?=null
    val username:String=username
    var password:String=password
    var email:String=email
    var role:String=role
    val provider:String?=provider
    val providerId:String?=providerId
}