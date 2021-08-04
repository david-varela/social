package dev.davidvarela.social.data.remoteDataSource

import dev.davidvarela.social.data.entities.DataPostPreview
import dev.davidvarela.social.data.entities.DataUser
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Streaming

interface WebService {
    @GET("posts")
    fun posts(): Call<List<DataPostPreview>>

    @GET("users/{id}")
    fun user(@Path("id") id: Int): Call<DataUser>

    @Streaming
    @GET("comments")
    fun comments(@Query("postId") postId: Int): Call<ResponseBody>
}
