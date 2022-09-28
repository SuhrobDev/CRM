package com.soul.crm.domain.payment.repo

import com.soul.crm.data.base.BaseNetworkResult
import com.soul.crm.data.remote.models.response.payment.PaymentPagination
import com.soul.crm.data.remote.models.response.user.PeoplePagination
import kotlinx.coroutines.flow.Flow

interface PaymentRepo {
    suspend fun getPayment(page: Int): Flow<BaseNetworkResult<PaymentPagination>>
}