package com.soul.crm.data.remote.models.response.payment

data class PaymentResult(
    val full_name: String,
    val id: Int,
    val user_balance: String,
    val date: String,
    val value:String
)