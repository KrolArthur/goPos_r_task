package com.example.gopos_task.api

import com.example.gopos_task.model.ResponseItemsData
import dagger.Provides
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.QueryMap

interface ApiService {
    @GET("api/v3/27/items")
    suspend fun getItems(
        @Header("Authorization") token: String
    ): Response<ResponseItemsData>
}