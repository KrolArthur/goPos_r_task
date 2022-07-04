package com.example.gopos_task.api

import com.example.gopos_task.model.auth.ResponseTokenData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface AuthService {
    @GET("oauth/token")
    suspend fun getNewToken(
        @QueryMap parameters: Map<String, String>
    ): Response<ResponseTokenData>

    @GET("oauth/token")
    suspend fun getRefreshedToken(
        @QueryMap parameters: Map<String, String>
    ): Response<ResponseTokenData>
}