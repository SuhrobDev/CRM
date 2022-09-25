package com.soul.crm.domain.student.repo

import com.soul.crm.data.base.BaseNetworkResult
import com.soul.crm.data.remote.models.response.user.PeoplePagination
import kotlinx.coroutines.flow.Flow

interface StudentRepo {
    suspend fun getStudent(page: Int): Flow<BaseNetworkResult<PeoplePagination>>
}