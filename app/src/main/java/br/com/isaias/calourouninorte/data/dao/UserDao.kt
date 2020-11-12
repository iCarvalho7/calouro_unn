package br.com.isaias.calourouninorte.data.dao

import androidx.room.*
import br.com.isaias.calourouninorte.data.model.User

@Dao
interface UserDao {

    @Delete
    suspend fun delete(user: User)

    @Query("SELECT * FROM user LIMIT 1")
    suspend fun getCurrentUser() : User?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)
}