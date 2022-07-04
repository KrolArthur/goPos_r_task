package com.example.gopos_task.model.auth

data class ResponseTokenData(
    val access_token: String,
    val expires_in: Int,
    val refresh_token: String,
    val scope: String,
    val token_type: String
)