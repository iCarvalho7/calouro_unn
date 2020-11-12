package br.com.isaias.calourouninorte.ui.login

import android.util.Patterns
import androidx.databinding.ObservableField
import androidx.lifecycle.*
import br.com.isaias.calourouninorte.R
import br.com.isaias.calourouninorte.utils.Result
import br.com.isaias.calourouninorte.data.repository.UserRepository
import br.com.isaias.calourouninorte.utils.isFilled
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch
class LoginViewModel(private val userRepository: UserRepository): ViewModel() {

    private val _userInteraction = MutableLiveData<Boolean>(false)
    val userInteraction: MutableLiveData<Boolean> = _userInteraction
    
    val username = MutableLiveData<String>()
    val password =  MutableLiveData<String>()

    var _createdUser = MutableLiveData<FirebaseUser?>()

    private val _areFieldsFilled = MutableLiveData<Boolean>()
    val areFieldsFilled: LiveData<Boolean> = _areFieldsFilled

    private val fieldsObserver = Observer<String> { validateSignInFields() }

    init {
        username.observeForever(fieldsObserver)
        password.observeForever(fieldsObserver)
    }

    private fun validateSignInFields() {
        val isValid = username.value?.isFilled() ?: false && Patterns.EMAIL_ADDRESS.matcher(username.value).matches() && password.value?.isFilled() ?: false
        _areFieldsFilled.postValue(isValid)
    }

    fun login(): MutableLiveData<FirebaseUser?>{
        val response = userRepository.loginInFirebase(username.value ?: "", password.value ?: "")
        return response
    }

    override fun onCleared() {
        super.onCleared()
        username.removeObserver(fieldsObserver)
        password.removeObserver(fieldsObserver)
    }

}