package br.com.isaias.calourouninorte.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.versionedparcelable.ParcelField
import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
@IgnoreExtraProperties
data class User (
    @PrimaryKey(autoGenerate = true)
    var id : Long? = null,
    var name: String? = "",
    var email: String? = "",
    var username : String? = "",
    var password : String? = "",
    var UIID : String? = ""
) : Parcelable
