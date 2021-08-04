package dev.davidvarela.social

import dev.davidvarela.social.data.DataSource
import dev.davidvarela.social.data.RepositoryImpl
import dev.davidvarela.social.data.localDataSource.AppDataBase
import dev.davidvarela.social.data.localDataSource.LocalDataSource
import dev.davidvarela.social.data.localDataSource.RoomLocalDataSource
import dev.davidvarela.social.data.remoteDataSource.RemoteDataSource
import dev.davidvarela.social.data.remoteDataSource.RemoteProvider
import dev.davidvarela.social.domain.interfaces.Repository
import dev.davidvarela.social.domain.useCases.NumCommentsUseCase
import dev.davidvarela.social.domain.useCases.PostsUseCase
import dev.davidvarela.social.domain.useCases.UserUseCase
import dev.davidvarela.social.presentation.viewmodels.PostDetailViewModel
import dev.davidvarela.social.presentation.viewmodels.PostsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

@JvmField
val appModule = module {
    single { AppDataBase.getInstance(androidContext()) }
    single<LocalDataSource> { RoomLocalDataSource(get()) }
    single<DataSource> { RemoteDataSource(RemoteProvider.WEBSERVICE) }
    factory<Repository> { RepositoryImpl(get(), get()) }
    single { PostsUseCase(get()) }
    single { UserUseCase(get()) }
    single { NumCommentsUseCase(get()) }
    viewModel { PostsViewModel(get()) }
    viewModel { PostDetailViewModel(get(), get()) }
}
