package br.com.isaias.calourouninorte.ui.singup

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.isaias.calourouninorte.data.model.User
import br.com.isaias.calourouninorte.data.repository.UserRepository
import br.com.isaias.calourouninorte.utils.Result
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch

class SingUpViewModel(private val repository: UserRepository) : ViewModel() {

    var name = ObservableField("")
    var email = ObservableField("")
    var username = ObservableField("")
    var password = ObservableField("")
    var confirmPassword = ObservableField("")

    private val _createdUser = MutableLiveData<FirebaseUser?>()
    val user : LiveData<FirebaseUser?> = _createdUser

    fun singUp() {
        viewModelScope.launch {
            val user = initUser()
            val result = repository.createUserInFirebase(user)
            if (result is Result.Success) _createdUser.postValue(result.data) else _createdUser.postValue(null)
        }
    }

    private fun initUser(): User = User(
        name = name.get() ?: "",
        email = email.get() ?: "",
        username = username.get() ?: "",
        password = password.get() ?: ""
    )
}