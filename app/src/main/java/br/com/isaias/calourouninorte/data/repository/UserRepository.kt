package br.com.isaias.calourouninorte.data.repository

import androidx.lifecycle.MutableLiveData
import br.com.isaias.calourouninorte.data.api.FirebaseService
import br.com.isaias.calourouninorte.data.dao.UserDao
import br.com.isaias.calourouninorte.data.model.User
import com.google.firebase.auth.FirebaseUser

class UserRepository(private val serivce: FirebaseService, private val userDao: UserDao) {
    suspend fun insertUser(user: User) = userDao.insert(user)
    suspend fun removeUser(user: User) = userDao.delete(user)
    suspend fun getUser() = userDao.getCurrentUser()
    fun createUserInFirebase(user: User): MutableLiveData<FirebaseUser?> =
        serivce.register(user)

    fun loginInFirebase(username: String, password: String): MutableLiveData<FirebaseUser?> =
        serivce.login(username, password)

    fun logoutFirebaseUser() = serivce.logout()
    fun getFirebaseCurrentUser() = serivce.currentUser()
    fun getAllUsers() = serivce.fetchAllUsers()
}