package br.com.isaias.calourouninorte.data.repository.implementation

import br.com.isaias.calourouninorte.data.dao.UserDao
import br.com.isaias.calourouninorte.data.model.User
import br.com.isaias.calourouninorte.data.repository.UserRepository
import br.com.isaias.calourouninorte.utils.Result
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlin.Exception

class UserRepositoryImpl(private val userDao: UserDao) : UserRepository {

    private var auth: FirebaseAuth = Firebase.auth

    override suspend fun createUserInFirebase(user: User): Result<FirebaseUser?> = try{
        var newUser : FirebaseUser? = null
        auth.createUserWithEmailAndPassword(user.email, user.password).addOnCompleteListener {
            if (it.isSuccessful) {
                newUser = auth.currentUser!!
                userDao.insert(user)
            }
        }
         if (newUser != null) Result.Success(newUser) else Result.Error(Exception())
    }catch (e: Exception){
        Result.Error(e)
    }

    override suspend fun loginInFirebase(username: String, password: String): Result<FirebaseUser?> = try {
        var newUser: FirebaseUser? = null
        auth.signInWithEmailAndPassword(username, password).addOnCompleteListener {
            if (it.isSuccessful) {
                newUser = auth.currentUser!!
            }
        }
        if (newUser != null) Result.Success(newUser) else Result.Error(Exception())
    } catch (e: Exception) {
        Result.Error(e)
    }
}