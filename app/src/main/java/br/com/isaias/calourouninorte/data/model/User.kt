package br.com.isaias.calourouninorte.data.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties

@Entity
@IgnoreExtraProperties
data class User (
    @PrimaryKey(autoGenerate = true)
    var id : Long? = null,
    var name: String? = "",
    var email: String? = "",
    var username : String? = "",
    var password : String? = "",
    var UIID : String? = ""
)
