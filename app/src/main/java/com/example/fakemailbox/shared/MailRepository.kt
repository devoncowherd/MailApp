package com.example.fakemailbox.shared

import com.example.fakemailbox.data.model.Mail
import com.example.fakemailbox.data.network.MailService
import retrofit2.Response
import javax.inject.Inject

class MailRepository @Inject constructor (
    private val mailService: MailService
        ) {

    suspend fun fetchMailList() : Response<MutableList<Mail>> {
        return mailService.fetchMailList()
    }
}