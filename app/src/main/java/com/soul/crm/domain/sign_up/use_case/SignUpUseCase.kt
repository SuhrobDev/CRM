package com.soul.crm.domain.sign_up.use_case

import com.soul.crm.data.base.BaseNetworkResult
import com.soul.crm.data.remote.models.request.sign_up.SignUpRequest
import com.soul.crm.data.remote.models.response.sign_up.SignUpResponse
import com.soul.crm.domain.sign_up.repo.SignUpRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SignUpUseCase @Inject constructor(private val signUpRepo: SignUpRepo) {
    suspend fun signUp(
        signUpRequest: SignUpRequest,
    ): Flow<BaseNetworkResult<SignUpResponse>> {
        return signUpRepo.signUp(signUpRequest)
    }
}