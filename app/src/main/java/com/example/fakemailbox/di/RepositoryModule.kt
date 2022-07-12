package com.example.fakemailbox.di

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.fakemailbox.data.network.MailService
import com.example.fakemailbox.shared.MailRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {


    @Provides
    @ViewModelScoped
    fun provideMailRepository(
        mailService : MailService
    ) : MailRepository {
        return MailRepository(mailService)
    }
}