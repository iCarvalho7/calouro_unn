package br.com.isaias.calourouninorte.ui.login

import androidx.databinding.ObservableField
import androidx.lifecycle.*
import br.com.isaias.calourouninorte.R
import br.com.isaias.calourouninorte.utils.Result
import br.com.isaias.calourouninorte.data.repository.UserRepository
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch
class LoginViewModel(private val userRepository: UserRepository): ViewModel() {

    private val _userInteraction = MutableLiveData<Boolean>(false)
    val userInteraction: MutableLiveData<Boolean> = _userInteraction
    
    val username = ObservableField("")
    val password = ObservableField("")

    var _createdUser = MutableLiveData<FirebaseUser?>()

    fun login(): MutableLiveData<FirebaseUser?>{
        _userInteraction.postValue(false)
        val response = userRepository.loginInFirebase(username.get() ?: "", password.get() ?: "")
        return response
    }
}