package br.com.isaias.calourouninorte.data.api

import androidx.lifecycle.MutableLiveData
import br.com.isaias.calourouninorte.data.model.User
import br.com.isaias.calourouninorte.utils.Result
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class FirebaseSerivce {

    private val firebaseAuth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    val userIsLogged = MutableLiveData<FirebaseUser?>()


    fun login(email: String, password: String): MutableLiveData<FirebaseUser?> {
        try {
            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    userIsLogged.postValue(firebaseAuth.currentUser)
                } else {
                    userIsLogged.postValue(null)
                }
            }
        }catch (e: Exception){}
        return userIsLogged
    }


    fun register(email: String, password: String) : MutableLiveData<FirebaseUser?> {
        val userIsRegistred = MutableLiveData<FirebaseUser?>()
        try {
            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful){
                    userIsRegistred.postValue(firebaseAuth.currentUser)
                }else{
                    userIsRegistred.postValue(null)
                }
            }
        }catch (e: Exception){}
        return userIsRegistred
    }

    suspend fun logout() = firebaseAuth.signOut()

    suspend fun currentUser() = firebaseAuth.currentUser

}