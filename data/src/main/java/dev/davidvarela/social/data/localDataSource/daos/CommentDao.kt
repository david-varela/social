package dev.davidvarela.social.data.localDataSource.daos

import androidx.room.Dao
import androidx.room.Query
import dev.davidvarela.social.data.entities.DataComment

@Dao
interface CommentDao : BaseDao<DataComment> {
    @Query("SELECT numComments FROM datacomment WHERE postId = :postId")
    fun read(postId: Int): Int?

}
