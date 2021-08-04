package dev.davidvarela.social.domain.useCases

import dev.davidvarela.social.domain.Result
import dev.davidvarela.social.domain.interfaces.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class NumCommentsUseCase(private val repository: Repository) {
    operator fun invoke(postId: Int): Flow<Result<Int>> = flow {
        repository.numComments(postId).collect { numComments ->
            // transformations would be here, if necessary
            emit(numComments)
        }
    }.flowOn(Dispatchers.Default)
}
