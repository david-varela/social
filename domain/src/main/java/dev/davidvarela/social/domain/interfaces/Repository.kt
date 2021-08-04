package dev.davidvarela.social.domain.interfaces

import dev.davidvarela.social.domain.Result
import dev.davidvarela.social.domain.entities.PostPreview
import dev.davidvarela.social.domain.entities.User
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun posts(): Flow<Result<List<PostPreview>>>
    fun user(userId: Int): Flow<Result<User>>
    fun numComments(postId: Int): Flow<Result<Int>>
}
