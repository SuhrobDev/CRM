package com.soul.crm.data.remote.models.response.payment

data class PaymentPagination(
    val count: Int,
    val next: Any,
    val previous: Any,
    val results: List<PaymentResult>
)