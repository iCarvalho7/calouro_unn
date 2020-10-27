package br.com.isaias.calourouninorte.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import br.com.isaias.calourouninorte.data.model.User

@Dao
interface UserDao {

    @Delete
    fun delete(user: User)

    @Query("SELECT * FROM user LIMIT 1")
    fun getCurrentUser() : User

    @Insert
    fun insert(user: User)
}