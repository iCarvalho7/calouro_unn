package br.com.isaias.calourouninorte.ui.students_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.isaias.calourouninorte.data.model.User
import br.com.isaias.calourouninorte.data.repository.UserRepository

class StudentListViewModel(private val userRepository: UserRepository): ViewModel() {

    private var _userList = MutableLiveData<MutableList<User>?>()

    init {
        fetchUsersFromFirebase()
    }

    fun fetchUsersFromFirebase() = userRepository.getAllUsers()
}