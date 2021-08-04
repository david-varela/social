package dev.davidvarela.social.domain.useCases


import dev.davidvarela.social.domain.NUM_COMMENTS
import dev.davidvarela.social.domain.POSTS
import dev.davidvarela.social.domain.Result
import dev.davidvarela.social.domain.USER
import dev.davidvarela.social.domain.entities.PostPreview
import dev.davidvarela.social.domain.entities.User
import dev.davidvarela.social.domain.interfaces.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SuccessFakeRepository : Repository {
    override fun posts(): Flow<Result<List<PostPreview>>> =
        flow { emit(Result.Success(POSTS)) }

    override fun user(userId: Int): Flow<Result<User>> =
        flow { emit(Result.Success(USER)) }

    override fun numComments(postId: Int): Flow<Result<Int>> =
        flow { emit(Result.Success(NUM_COMMENTS)) }
}
