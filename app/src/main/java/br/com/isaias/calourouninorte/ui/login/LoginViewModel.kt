package br.com.isaias.calourouninorte.ui.login

import android.util.Patterns
import androidx.lifecycle.*
import br.com.isaias.calourouninorte.data.model.User
import br.com.isaias.calourouninorte.data.repository.UserRepository
import br.com.isaias.calourouninorte.utils.isFilled
import br.com.isaias.calourouninorte.vo.Resource
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch

class LoginViewModel(private val userRepository: UserRepository): ViewModel() {

    val username = MutableLiveData<String>()

    val password =  MutableLiveData<String>()

    private val _areFieldsFilled = MutableLiveData<Boolean>()
    val areFieldsFilled: LiveData<Boolean> = _areFieldsFilled

    private val fieldsObserver = Observer<String> { validateSignInFields() }

    private val _userLogged = MutableLiveData<Resource<User>>()
    val userLogged : LiveData<Resource<User>> = _userLogged

    private val _userFirebaseLogged = MutableLiveData<Resource<FirebaseUser>>()
    val userFirebaseLogged : LiveData<Resource<FirebaseUser>> = _userFirebaseLogged

    val userInteraction = MutableLiveData<Boolean>(true)

    init {
        username.observeForever(fieldsObserver)
        password.observeForever(fieldsObserver)
        verifyIfUserIsLogged()
    }

    private fun verifyIfUserIsLogged() {
        viewModelScope.launch {
            val user = userRepository.getUser()
            if (user != null){
                _userLogged.value = Resource.success(user)
            }else{
                verifyIfFirebaseUserIsLogged()
            }
        }
    }

    private fun verifyIfFirebaseUserIsLogged() {
        val firebaseUser = userRepository.getFirebaseCurrentUser()
        if (firebaseUser != null){
            _userFirebaseLogged.value = Resource.success(firebaseUser)
        }else{
            _userFirebaseLogged.value = Resource.error(Exception())
        }
    }

    private fun validateSignInFields() {
        val isValid = username.value?.isFilled() ?: false &&
                Patterns.EMAIL_ADDRESS.matcher(username.value).matches() &&
                password.value?.isFilled() ?: false
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