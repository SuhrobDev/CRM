package com.soul.crm.data.repo.user

import com.soul.crm.data.base.BaseNetworkResult
import com.soul.crm.data.remote.ApiService
import com.soul.crm.data.remote.models.response.user.PeoplePagination
import com.soul.crm.domain.teacher.repo.TeacherRepo
import com.soul.crm.domain.user.repo.UserRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepoImpl @Inject constructor(private val service: ApiService) : UserRepo {
    override suspend fun getUser(page: Int): Flow<BaseNetworkResult<PeoplePagination>> = flow {
        val response = service.getUsers(page = page)
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