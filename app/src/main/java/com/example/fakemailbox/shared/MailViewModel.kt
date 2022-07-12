package com.example.fakemailbox.shared

import android.app.Application
import androidx.lifecycle.*
import com.example.fakemailbox.data.model.Mail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MailViewModel @Inject constructor(
    private val mailRepository : MailRepository,
    private val app : Application
) : ViewModel() {

   fun fetchMailList() : MutableLiveData<MutableList<Mail>?>{
       var result : MutableLiveData<MutableList<Mail>?> = MutableLiveData<MutableList<Mail>?>()
       viewModelScope.launch(Dispatchers.IO) {
           val response = mailRepository.fetchMailList().body()
           result.postValue(response)
       }
       return result
   }
}