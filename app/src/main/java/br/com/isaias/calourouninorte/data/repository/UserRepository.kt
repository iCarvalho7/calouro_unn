package br.com.isaias.calourouninorte.data.repository

import br.com.isaias.calourouninorte.data.model.User
import br.com.isaias.calourouninorte.utils.Result
import com.google.firebase.auth.FirebaseUser

interface UserRepository {
    suspend fun createUserInFirebase(user: User): Result<FirebaseUser?>
    suspend fun loginInFirebase(username: String, password: String): Result<FirebaseUser?>
}