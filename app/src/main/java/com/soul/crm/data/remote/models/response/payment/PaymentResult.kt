package com.soul.crm.data.remote.models.response.payment

data class PaymentResult(
    val full_name: String,
    val id: Int,
    val balance: String,
    val payment_date: String,
    val payed_amount:String
)