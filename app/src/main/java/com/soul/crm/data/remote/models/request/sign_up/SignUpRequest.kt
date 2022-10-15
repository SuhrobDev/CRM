package com.soul.crm.data.remote.models.request.sign_up

data class SignUpRequest(
    val phone_number: String,
    val password: String,
    val image: String?=null,
    val first_name: String,
    val last_name: String,
    val gender: String?=null,
)