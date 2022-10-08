package com.soul.crm.data.remote.models.request.sign_up

data class SignUpRequest(
    val first_name: String,
    val gender: String?=null,
    val image: String?=null,
    val last_name: String,
    val password: String,
    val phone_number: String
)