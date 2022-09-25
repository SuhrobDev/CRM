package com.soul.crm.data.repo.student

import com.soul.crm.data.base.BaseNetworkResult
import com.soul.crm.data.remote.ApiService
import com.soul.crm.data.remote.models.response.user.PeoplePagination
import com.soul.crm.domain.student.repo.StudentRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class StudentRepoImpl @Inject constructor(private val service: ApiService) : StudentRepo {
    override suspend fun getStudent(page: Int): Flow<BaseNetworkResult<PeoplePagination>> = flow {
        val response = service.getStudents(page = page)
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