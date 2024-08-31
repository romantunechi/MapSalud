package com.cinicaragua.mapsalud.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.cinicaragua.mapsalud.Entities.Centro
import com.cinicaragua.mapsalud.Repositories.MainRepository
import kotlinx.coroutines.Dispatchers

class MainViewModel(private  val repo : MainRepository) : ViewModel() {
    
fun selectAllCentros() : LiveData<List<Centro>>{
    return repo.selectAllCentros().asLiveData(Dispatchers.IO)
}

fun selectCentro(id : Int) : LiveData<Centro>{
    return repo.SelectCentro(id).asLiveData(Dispatchers.IO)
}

}