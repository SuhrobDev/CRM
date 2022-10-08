package com.soul.crm.data.repo.sign_up

import android.util.Log
import com.soul.crm.data.base.BaseNetworkResult
import com.soul.crm.data.remote.ApiService
import com.soul.crm.data.remote.models.request.sign_up.SignUpRequest
import com.soul.crm.data.remote.models.response.sign_up.SignUpResponse
import com.soul.crm.domain.sign_up.repo.SignUpRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SignUpRepoImpl @Inject constructor(private val service: ApiService) : SignUpRepo {
    override suspend fun signUp(signUpRequest: SignUpRequest): Flow<BaseNetworkResult<SignUpResponse>> = flow {
        val response = service.signUp(signUpRequest)
        emit(BaseNetworkResult.Loading(true))
        Log.d("OOO", "signUp: $signUpRequest")
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