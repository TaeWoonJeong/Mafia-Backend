package com.taewoon.mafia

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableJpaAuditing
@SpringBootApplication
class MafiaApplication

fun main(args: Array<String>) {
    runApplication<MafiaApplication>(*args)
}
