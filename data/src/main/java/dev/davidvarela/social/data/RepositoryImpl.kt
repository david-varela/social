package dev.davidvarela.social.data

import dev.davidvarela.social.domain.Result
import dev.davidvarela.social.domain.Result.Success
import dev.davidvarela.social.domain.entities.PostPreview
import dev.davidvarela.social.domain.entities.User
import dev.davidvarela.social.domain.interfaces.Repository
import dev.davidvarela.social.data.entities.DataComment
import dev.davidvarela.social.data.entities.DataPostPreview
import dev.davidvarela.social.data.localDataSource.LocalDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RepositoryImpl(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: DataSource
) : Repository {

    override fun posts(): Flow<Result<List<PostPreview>>> = flow {
        var posts = localDataSource.posts()
        if (posts is Success) emit(Success(posts.data.map(DataPostPreview::toPostPreview)))
        posts = remoteDataSource.posts()
        if (posts is Success) emit(Success(posts.data.map(DataPostPreview::toPostPreview)))
        if (posts is Success) localDataSource.createPosts(posts.data)
    }.flowOn(Dispatchers.IO)

    override fun user(userId: Int): Flow<Result<User>> = flow {
        val localResult = localDataSource.user(userId)
        if (localResult is Success) emit(Success(localResult.data.toUser()))
        val dataUserResult = remoteDataSource.user(userId)
        if (dataUserResult is Success) {
            emit(Result from { dataUserResult.data.toUser() })
            localDataSource.createUser(dataUserResult.data)
        }
    }.flowOn(Dispatchers.IO)

    override fun numComments(postId: Int): Flow<Result<Int>> = flow {
        val result = localDataSource.numComments(postId)
        if (result is Success) emit(result)
        val dataCommentResult = remoteDataSource.numComments(postId)
        emit(dataCommentResult)
        if (dataCommentResult is Success)
            localDataSource.saveComments(DataComment(postId, dataCommentResult.data))
    }.flowOn(Dispatchers.IO)
}