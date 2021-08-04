package dev.davidvarela.social.data

import dev.davidvarela.social.domain.Result
import dev.davidvarela.social.data.entities.DataPostPreview
import dev.davidvarela.social.data.entities.DataUser

interface DataSource {
    fun posts(): Result<List<DataPostPreview>>
    fun user(userId: Int): Result<DataUser>
    fun numComments(postId: Int): Result<Int>
}
