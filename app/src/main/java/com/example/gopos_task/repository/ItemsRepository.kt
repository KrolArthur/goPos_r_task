package com.example.gopos_task.repository

import com.example.gopos_task.api.ApiService
import com.example.gopos_task.api.AuthService
import com.example.gopos_task.model.ResponseItemsData
import com.example.gopos_task.model.auth.ResponseTokenData
import com.example.gopos_task.utils.Resource
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Named

class ItemsRepository @Inject constructor(
    private val api: ApiService
) {
     suspend fun getItems(token: String): Resource<ResponseItemsData> {
        return try {
            val response = api.getItems("Bearer $token")
            val result = response.body()
            if(response.isSuccessful && result != null) {
                Resource.Success(result)
            } else {
                Resource.Error(response.message())
            }
        } catch(e: Exception) {
            Resource.Error(e.message ?: "An error occured")
        }
    }
}