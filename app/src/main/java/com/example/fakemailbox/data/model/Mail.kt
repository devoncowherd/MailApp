package com.example.fakemailbox.data.model

import androidx.lifecycle.GeneratedAdapter
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class Mail(
    @Json(name = "id")
    val id : Int? = -1,
    @Json(name = "fromDate")
    val fromDate : Long? = -1,
    @Json(name = "priorityIndicator")
    val priorityIndicator : Boolean?,
    @Json(name = "readIndicator")
    val readIndicator : Boolean?
)
