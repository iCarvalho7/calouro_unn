package br.com.isaias.calourouninorte

import br.com.isaias.calourouninorte.data.api.FirebaseService
import br.com.isaias.calourouninorte.data.database.AppDatabase
import br.com.isaias.calourouninorte.data.repository.UserRepository
import br.com.isaias.calourouninorte.ui.chat.ChatViewModel
import br.com.isaias.calourouninorte.ui.login.LoginViewModel
import br.com.isaias.calourouninorte.ui.singup.SingUpViewModel
import br.com.isaias.calourouninorte.ui.students_list.StudentListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModelModule: Module = module {
    viewModel { LoginViewModel(get()) }
    viewModel { SingUpViewModel(get()) }
    viewModel { StudentListViewModel(get()) }
    viewModel { ChatViewModel(get()) }
}

val module: Module = module {
    single<FirebaseService> {FirebaseService()}
    single<UserRepository> { UserRepository(get(), get()) }
}

val databaseModule = module {
    factory { AppDatabase.getInstance(get()).userDao() }
}