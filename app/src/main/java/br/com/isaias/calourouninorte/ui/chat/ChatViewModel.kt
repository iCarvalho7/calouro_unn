package br.com.isaias.calourouninorte.ui.chat

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.isaias.calourouninorte.data.repository.UserRepository

class ChatViewModel(userRepository: UserRepository) : ViewModel() {
    val username = MutableLiveData<String>()
}