package br.com.isaias.calourouninorte.data.api

import android.util.Log
import androidx.lifecycle.MutableLiveData
import br.com.isaias.calourouninorte.data.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*

class FirebaseSerivce {

    private val firebaseAuth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    private val firebaseDatabase: DatabaseReference by lazy {
        FirebaseDatabase.getInstance().reference.child("users")
    }

    private val userIsLogged = MutableLiveData<FirebaseUser?>()
    private val userIsRegistered = MutableLiveData<FirebaseUser?>()
    val userList = MutableLiveData<MutableList<User>?>()

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


    fun register(user: User) : MutableLiveData<FirebaseUser?> {
        try {
            firebaseAuth.createUserWithEmailAndPassword(user.email ?: "", user.password ?: "").addOnCompleteListener {
                if (it.isSuccessful){
                    user.UIID = firebaseAuth.currentUser?.uid
                    userIsRegistered.postValue(firebaseAuth.currentUser)
                    firebaseDatabase.child(user.UIID!!).setValue(user)
                }else{
                    userIsRegistered.postValue(null)
                }
            }
        }catch (e: Exception){}
        return userIsRegistered
    }

    fun logout() = firebaseAuth.signOut()

    fun currentUser() = firebaseAuth.currentUser

    fun fetchAllUsers() : MutableLiveData<MutableList<User>?> {
        firebaseDatabase.addListenerForSingleValueEvent(object: ValueEventListener{
            override fun onCancelled(error: DatabaseError) {
                throw error.toException()
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                val usersList : MutableList<User> = mutableListOf()
                for (recipe in snapshot.children){
                    usersList.add(recipe.getValue(User::class.java)!!)
                }
                userList.postValue(usersList)
            }
        })
        return userList
    }

}