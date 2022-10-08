package com.soul.crm.data.remote.models.response.sign_up

data class SignUpResponse(
    val expiry_date: String,
    val token: String,
    val user: User
)