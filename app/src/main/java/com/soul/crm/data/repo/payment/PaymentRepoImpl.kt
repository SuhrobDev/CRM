package com.soul.crm.data.repo.payment

import com.soul.crm.data.base.BaseNetworkResult
import com.soul.crm.data.remote.ApiService
import com.soul.crm.data.remote.models.response.payment.PaymentPagination
import com.soul.crm.data.remote.models.response.user.PeoplePagination
import com.soul.crm.domain.payment.repo.PaymentRepo
import com.soul.crm.domain.student.repo.StudentRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PaymentRepoImpl @Inject constructor(private val service: ApiService) : PaymentRepo {
    override suspend fun getPayment(page: Int): Flow<BaseNetworkResult<PaymentPagination>> = flow {
        val response = service.getPayments(page = page)
        emit(BaseNetworkResult.Loading(true))
        if (response.isSuccessful) {
            emit(BaseNetworkResult.Loading(false))
            response.body()?.let {
                emit(BaseNetworkResult.Success(it))
            }
        } else {
            emit(BaseNetworkResult.Loading(false))
            emit(BaseNetworkResult.Error(response.message()))
        }
    }
}