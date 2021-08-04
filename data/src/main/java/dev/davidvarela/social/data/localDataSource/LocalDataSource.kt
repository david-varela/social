package dev.davidvarela.social.data.localDataSource

import dev.davidvarela.social.data.DataSource
import dev.davidvarela.social.data.entities.DataComment
import dev.davidvarela.social.data.entities.DataPostPreview
import dev.davidvarela.social.data.entities.DataUser

interface LocalDataSource: DataSource {
    fun createPosts(posts: List<DataPostPreview>)
    fun createUser(dataUser: DataUser)
    fun saveComments(vararg comments: DataComment)
}
