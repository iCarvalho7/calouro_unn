package br.com.isaias.calourouninorte.ui.login

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.isaias.calourouninorte.utils.Result
import androidx.lifecycle.viewModelScope
import br.com.isaias.calourouninorte.data.repository.UserRepository
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch

class LoginViewModel(private val userRepository: UserRepository): ViewModel() {

    val username = ObservableField("")
    val password = ObservableField("")

    private val _createdUser = MutableLiveData<FirebaseUser?>()
    val user : LiveData<FirebaseUser?> = _createdUser

    fun login(){
        viewModelScope.launch {
            val result = userRepository.loginInFirebase(username.get() ?: "", password.get() ?: "")
          if (result is Result.Success)  _createdUser.postValue(result.data) else _createdUser.postValue(null)
        }
    }
}