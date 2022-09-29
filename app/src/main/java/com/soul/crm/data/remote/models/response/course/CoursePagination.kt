package com.soul.crm.data.remote.models.response.course

data class CoursePagination(
    val count: Int,
    val next: Any,
    val previous: Any,
    val results: List<CourseResult>
)