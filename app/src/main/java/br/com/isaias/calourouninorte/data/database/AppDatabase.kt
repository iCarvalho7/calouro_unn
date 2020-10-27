package br.com.isaias.calourouninorte.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.isaias.calourouninorte.data.dao.UserDao
import br.com.isaias.calourouninorte.data.model.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}