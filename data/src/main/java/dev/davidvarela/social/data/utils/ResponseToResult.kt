package dev.davidvarela.social.data.utils

import dev.davidvarela.social.domain.Result
import retrofit2.Response
import java.io.IOException

fun <T> Response<T>.toResult() = if (this.isSuccessful) Result.Success(this.body()!!)
else Result.Error(IOException(this.errorBody()!!.string()))
