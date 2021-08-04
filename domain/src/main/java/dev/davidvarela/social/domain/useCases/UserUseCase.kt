package dev.davidvarela.social.domain.useCases

import dev.davidvarela.social.domain.Result
import dev.davidvarela.social.domain.entities.User
import dev.davidvarela.social.domain.interfaces.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class UserUseCase(private val repository: Repository) {
    operator fun invoke(userId: Int): Flow<Result<User>> = flow {
        repository.user(userId).collect { user ->
            // transformations would be here, if necessary
            emit(user)
        }
    }.flowOn(Dispatchers.Default)
}
