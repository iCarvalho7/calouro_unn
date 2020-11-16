package br.com.isaias.calourouninorte.ui.login

import android.util.Patterns
import androidx.lifecycle.*
import br.com.isaias.calourouninorte.data.repository.UserRepository
import br.com.isaias.calourouninorte.utils.isFilled
import com.google.firebase.auth.FirebaseUser

class LoginViewModel(private val userRepository: UserRepository): ViewModel() {

    val username = MutableLiveData<String>()

    val password =  MutableLiveData<String>()

    private val _areFieldsFilled = MutableLiveData<Boolean>()

    val areFieldsFilled: LiveData<Boolean> = _areFieldsFilled
    private val fieldsObserver = Observer<String> { validateSignInFields() }

    val userInteraction = MutableLiveData<Boolean>(true)

    init {
        username.observeForever(fieldsObserver)
        password.observeForever(fieldsObserver)
    }

    private fun validateSignInFields() {
        val isValid = username.value?.isFilled() ?: false && Patterns.EMAIL_ADDRESS.matcher(username.value).matches() && password.value?.isFilled() ?: false
        _areFieldsFilled.postValue(isValid)
    }

    fun login(isClick : Boolean): MutableLiveData<FirebaseUser?>{
        if (isClick) userInteraction.postValue(false)
        return userRepository.loginInFirebase(username.value ?: "", password.value ?: "")
    }

    override fun onCleared() {
        super.onCleared()
        username.removeObserver(fieldsObserver)
        password.removeObserver(fieldsObserver)
    }

}