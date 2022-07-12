package com.example.fakemailbox.data.network

import com.example.fakemailbox.data.model.Mail
import com.skydoves.sandwich.ApiResponse
import retrofit2.Response
import retrofit2.http.GET

interface MailService {

    @GET("v1/57a3ba0c-621f-4ef5-bbf1-9d54411261ed")
    suspend fun fetchMailList() : Response<MutableList<Mail>>
}