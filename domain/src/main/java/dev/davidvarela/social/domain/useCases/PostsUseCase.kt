package dev.davidvarela.social.domain.useCases

import dev.davidvarela.social.domain.Result
import dev.davidvarela.social.domain.entities.PostPreview
import dev.davidvarela.social.domain.interfaces.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class PostsUseCase(private val repository: Repository) {
    operator fun invoke(): Flow<Result<List<PostPreview>>> = flow {
        repository.posts().collect { posts ->
            // transformations would be here, if necessary
            emit(posts)
        }
    }.flowOn(Dispatchers.Default)
}
