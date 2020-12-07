package br.com.isaias.calourouninorte.data.model

import java.util.*


data class Message (
    val id : String = UUID.randomUUID().toString(),
    var content : String = "",
    val isRecived : Boolean = false
)