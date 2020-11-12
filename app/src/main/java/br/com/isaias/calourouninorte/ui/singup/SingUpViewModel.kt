package br.com.isaias.calourouninorte.ui.singup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.isaias.calourouninorte.data.model.User
import br.com.isaias.calourouninorte.data.repository.UserRepository
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch

class SingUpViewModel(private val repository: UserRepository) : ViewModel() {

    var name = MutableLiveData<String>()
    var email = MutableLiveData<String>()
    var username = MutableLiveData<String>()
    var password = MutableLiveData<String>()
    var confirmPassword = MutableLiveData<String>()

    private lateinit var user :User

    fun singUp() : MutableLiveData<FirebaseUser?>  {
        user = initUser()
        return repository.createUserInFirebase(user)
    }

    private fun initUser(): User = User(
        name = name.value ?: "",
        email = email.value ?: "",
        username = username.value ?: "",
        password = password.value ?: ""
    )

    fun createUser() {
        viewModelScope.launch {
            repository.insertUser(user)
        }
    }
}