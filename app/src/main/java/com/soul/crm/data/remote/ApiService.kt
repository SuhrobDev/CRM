package com.soul.crm.data.remote

import com.soul.crm.data.remote.models.response.user.PeoplePagination
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("user")
    suspend fun getUsers(
        @Query("page") page: Int,
    ): Response<PeoplePagination>

    @GET("student")
    suspend fun getStudents(
        @Query("page") page: Int,
    ): Response<PeoplePagination>

    @GET("teacher")
    suspend fun getTeachers(
        @Query("page") page: Int,
    ): Response<PeoplePagination>
}