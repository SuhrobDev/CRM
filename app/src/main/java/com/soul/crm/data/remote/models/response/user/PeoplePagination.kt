package com.soul.crm.data.remote.models.response.user

data class PeoplePagination(
    val count: Int,
    val next: Any,
    val previous: Any,
    val results: List<PeopleResult>
)