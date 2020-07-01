package com.oguncan.countriesmvvm.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oguncan.countriesmvvm.model.Country
import com.oguncan.countriesmvvm.service.CountryDatabase
import kotlinx.coroutines.launch
import java.util.*

class DetailViewModel(application: Application) : BaseViewModel(application = application){
    val countryLiveData = MutableLiveData<Country>()

    fun getDataFromRoom(uuid: Int){
        launch {
            val dao = CountryDatabase(getApplication()).countryDAO()
            val country = dao.getCountry(uuid)
            countryLiveData.value = country
        }
    }

}