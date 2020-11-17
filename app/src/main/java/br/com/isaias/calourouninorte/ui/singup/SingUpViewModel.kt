package br.com.isaias.calourouninorte.ui.singup

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.isaias.calourouninorte.data.model.User
import br.com.isaias.calourouninorte.data.repository.UserRepository
import br.com.isaias.calourouninorte.utils.isFilled
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch

class SingUpViewModel(private val repository: UserRepository) : ViewModel() {

    var name = MutableLiveData<String>()
    var email = MutableLiveData<String>()
    var matricula = MutableLiveData<String>()
    var password = MutableLiveData<String>()
    var confirmPassword = MutableLiveData<String>()

    private val _areFieldsFilled = MutableLiveData<Boolean>()

    val areFieldsFilled: LiveData<Boolean> = _areFieldsFilled
    private val fieldsObserver = Observer<String> { validateSignUpFields() }

    val userInteraction = MutableLiveData<Boolean>(true)

    private lateinit var user :User

    init {
        name.observeForever(fieldsObserver)
        email.observeForever(fieldsObserver)
        matricula.observeForever(fieldsObserver)
        password.observeForever(fieldsObserver)
        confirmPassword.observeForever(fieldsObserver)
    }
    private fun validateSignUpFields() {
        val isValid = matricula.value.isFilled()
                && password.value.isFilled()
                && confirmPassword.value.isFilled()
                && name.value.isFilled()
                && email.value.isFilled()
        _areFieldsFilled.postValue(isValid)
    }

    fun singUp() : MutableLiveData<FirebaseUser?>  {
        user = initUser()
        return repository.createUserInFirebase(user)
    }

    private fun initUser(): User = User(
        name = name.value ?: "",
        email = email.value ?: "",
        username = matricula.value ?: "",
        password = password.value ?: ""
    )

    fun createUser() {
        viewModelScope.launch {
            repository.insertUser(user)
        }
    }

    override fun onCleared() {
        super.onCleared()
        name.removeObserver(fieldsObserver)
        email.removeObserver(fieldsObserver)
        matricula.removeObserver(fieldsObserver)
        password.removeObserver(fieldsObserver)
        confirmPassword.removeObserver(fieldsObserver)
    }
}