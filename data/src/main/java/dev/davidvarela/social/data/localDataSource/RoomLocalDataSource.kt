package dev.davidvarela.social.data.localDataSource

import dev.davidvarela.social.domain.Result
import dev.davidvarela.social.data.entities.DataComment
import dev.davidvarela.social.data.entities.DataPostPreview
import dev.davidvarela.social.data.entities.DataUser

class RoomLocalDataSource(private val db: AppDataBase) : LocalDataSource {
    override fun posts(): Result<List<DataPostPreview>> =
        Result.Success(db.postPreviewDao().readAll())

    override fun createPosts(posts: List<DataPostPreview>) {
        db.postPreviewDao().insert(*posts.toTypedArray())
    }

    override fun user(userId: Int): Result<DataUser> {
        db.userDao().read(userId)?.let { return Result.Success(it) }
        return Result.Error(NoSuchElementException())
    }

    override fun createUser(dataUser: DataUser) {
        db.userDao().insert(dataUser)
    }

    override fun numComments(postId: Int): Result<Int> {
        db.commentDao().read(postId)?.let { return Result.Success(it) }
        return Result.Error(NoSuchElementException())
    }

    override fun saveComments(vararg comments: DataComment) {
        db.commentDao().insert(*comments)
    }
}
