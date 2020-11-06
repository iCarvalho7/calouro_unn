package br.com.isaias.calourouninorte.data.repository

import androidx.lifecycle.MutableLiveData
import br.com.isaias.calourouninorte.data.api.FirebaseSerivce
import br.com.isaias.calourouninorte.data.dao.UserDao
import br.com.isaias.calourouninorte.data.model.User
import com.google.firebase.auth.FirebaseUser

class UserRepository(private val serivce: FirebaseSerivce, private val userDao: UserDao) {
    suspend fun insertUser(user: User) = userDao.insert(user)
    suspend fun removeUser(user: User) = userDao.delete(user)
    suspend fun getUser() = userDao.getCurrentUser()
    fun createUserInFirebase(user: User): MutableLiveData<FirebaseUser?> =
        serivce.register(user.email, user.password)

    fun loginInFirebase(username: String, password: String): MutableLiveData<FirebaseUser?> =
        serivce.login(username, password)

    suspend fun logoutFirebaseUser() = serivce.logout()
    suspend fun getFirebaseCurrentUser() = serivce.currentUser()
}