package com.example.gopos_task.repository

import com.example.gopos_task.api.AuthService
import com.example.gopos_task.model.auth.ResponseTokenData
import com.example.gopos_task.utils.Resource
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Named

@Module
@InstallIn(ViewModelComponent::class)
class AuthRepository @Inject constructor(
    private val api: AuthService
) {
    suspend fun getToken(params: Map<String, String>): Resource<ResponseTokenData> {
        return try {
            val response = api.getNewToken(params)
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