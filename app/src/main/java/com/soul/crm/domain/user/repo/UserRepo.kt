package com.soul.crm.domain.user.repo

import com.soul.crm.data.base.BaseNetworkResult
import com.soul.crm.data.remote.models.response.user.PeoplePagination
import kotlinx.coroutines.flow.Flow

interface UserRepo {
    suspend fun getUser(page: Int): Flow<BaseNetworkResult<PeoplePagination>>
}