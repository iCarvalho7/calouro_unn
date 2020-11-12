package br.com.isaias.calourouninorte

import androidx.room.Room
import br.com.isaias.calourouninorte.data.api.FirebaseSerivce
import br.com.isaias.calourouninorte.data.database.AppDatabase
import br.com.isaias.calourouninorte.data.repository.UserRepository
import br.com.isaias.calourouninorte.ui.login.LoginViewModel
import br.com.isaias.calourouninorte.ui.singup.SingUpViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModelModule: Module = module {
    viewModel { LoginViewModel(get()) }
    viewModel { SingUpViewModel(get()) }
}

val module: Module = module {
    single<FirebaseSerivce> {FirebaseSerivce()}
    single<UserRepository> { UserRepository(get(), get()) }
}

val databaseModule = module {
    factory { AppDatabase.getInstance(get()).userDao() }
}