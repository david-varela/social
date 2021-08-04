package dev.davidvarela.social.domain.useCases

import dev.davidvarela.social.domain.Result
import dev.davidvarela.social.domain.entities.PostPreview
import dev.davidvarela.social.domain.entities.User
import dev.davidvarela.social.domain.interfaces.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ErrorFakeRepository: Repository {
    override fun posts(): Flow<Result<List<PostPreview>>> = flow {
        emit(Result.Error(java.lang.RuntimeException()))
    }

    override fun user(userId: Int): Flow<Result<User>> = flow {
        emit(Result.Error(java.lang.RuntimeException()))
    }

    override fun numComments(postId: Int): Flow<Result<Int>> = flow {
        emit(Result.Error(java.lang.RuntimeException()))
    }
}
