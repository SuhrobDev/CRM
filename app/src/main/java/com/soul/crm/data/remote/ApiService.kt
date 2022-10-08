package com.soul.crm.data.remote

import com.soul.crm.data.remote.models.request.sign_up.SignUpRequest
import com.soul.crm.data.remote.models.response.course.CoursePagination
import com.soul.crm.data.remote.models.response.payment.PaymentPagination
import com.soul.crm.data.remote.models.response.sign_up.SignUpResponse
import com.soul.crm.data.remote.models.response.user.PeoplePagination
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    //USER
    @GET("user")
    suspend fun getUsers(
        @Query("page") page: Int,
    ): Response<PeoplePagination>

    //ATTENDANCE
    @GET("attendance")
    suspend fun getAttendance(
        @Query("page") page: Int,
    ): Response<PeoplePagination>

    //COURSE
    @GET("course")
    suspend fun getCourse(
        @Query("page") page: Int,
    ): Response<CoursePagination>

    //LESSON
    @GET("lesson")
    suspend fun getLesson(
        @Query("page") page: Int,
    ): Response<PeoplePagination>

    //BALANCE
    @GET("balance")
    suspend fun getBalance(
        @Query("page") page: Int,
    ): Response<PeoplePagination>

    //COMMENTS
    @GET("comment_lesson")
    suspend fun getCommentLesson(
        @Query("page") page: Int,
    ): Response<PeoplePagination>

    //PAYMENTS
    @GET("payments")
    suspend fun getPayments(
        @Query("page") page: Int,
    ): Response<PaymentPagination>

    /**         STUDENTS        */

    @GET("student")
    suspend fun getStudents(
        @Query("page") page: Int,
    ): Response<PeoplePagination>

    @GET("student-group")
    suspend fun getStudentGroup(
        @Query("page") page: Int,
    ): Response<PeoplePagination>

    @GET("student-schedule")
    suspend fun getStudentSchedule(
        @Query("page") page: Int,
    ): Response<PeoplePagination>

    /**         TEACHERS        */

    @GET("teacher")
    suspend fun getTeachers(
        @Query("page") page: Int,
    ): Response<PeoplePagination>

    @GET("teacher_group")
    suspend fun getTeachersGroup(
        @Query("page") page: Int,
    ): Response<PeoplePagination>

    //GROUPS
    @GET("group")
    suspend fun getGroup(
        @Query("page") page: Int,
    ): Response<PeoplePagination>

    @GET("group-attends")
    suspend fun getGroupAttends(
        @Query("page") page: Int,
    ): Response<PeoplePagination>

    @GET("group-lesson-schedule")
    suspend fun getGroupLessonSchedule(
        @Query("page") page: Int,
    ): Response<PeoplePagination>

    /**         Sign Up         */
    @POST("register/")
    suspend fun signUp(
        @Body signUpRequest: SignUpRequest,
    ): Response<SignUpResponse>
}