package dev.davidvarela.social.data.remoteDataSource

import dev.davidvarela.social.data.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RemoteProvider {
    val WEBSERVICE: WebService = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL) // To inject in case of different endpoints for testing and production
        .addConverterFactory(MoshiConverterFactory.create()).build()
        .create(WebService::class.java)
}
