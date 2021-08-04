package dev.davidvarela.social.data.remoteDataSource

import com.squareup.moshi.JsonReader
import dev.davidvarela.social.domain.Result
import dev.davidvarela.social.data.DataSource
import dev.davidvarela.social.data.entities.DataPostPreview
import dev.davidvarela.social.data.entities.DataUser
import dev.davidvarela.social.data.utils.toResult
import okio.BufferedSource
import java.io.IOException

class RemoteDataSource(private val webService: WebService) : DataSource {
    private fun countComments(json: BufferedSource) = with(JsonReader.of(json)) {
        var output = 0
        use {
            beginArray()
            while (hasNext()) {
                output++
                beginObject()
                while (hasNext()) {
                    skipName()
                    skipValue()
                }
                endObject()
            }
            endArray()
            output
        }
    }

    override fun posts(): Result<List<DataPostPreview>> = webService.posts().execute().toResult()

    override fun user(userId: Int): Result<DataUser> = webService.user(userId).execute().toResult()

    override fun numComments(postId: Int): Result<Int> {
        val response = webService.comments(postId).execute()
        if (response.isSuccessful)
            response.body()?.source()?.let { return Result.Success(countComments(it)) }
        return Result.Error(IOException())
    }
}
