package com.soul.crm.domain.sign_up.repo

import com.soul.crm.data.base.BaseNetworkResult
import com.soul.crm.data.remote.models.request.sign_up.SignUpRequest
import com.soul.crm.data.remote.models.response.sign_up.SignUpResponse
import kotlinx.coroutines.flow.Flow


interface SignUpRepo {
    suspend fun signUp(signUpRequest: SignUpRequest): Flow<BaseNetworkResult<SignUpResponse>>
}