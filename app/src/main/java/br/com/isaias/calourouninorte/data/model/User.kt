package br.com.isaias.calourouninorte.data.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity
data class User (
    @PrimaryKey(autoGenerate = true)
    var id : Long? = null,
    var name: String = "",
    var email: String = "",
    var username : String = "",
    var password : String
)
