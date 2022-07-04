package com.example.gopos_task.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gopos_task.model.Data
import com.example.gopos_task.model.ob_mobel.Item
import com.example.gopos_task.objectbox.ItemsBox
import com.example.gopos_task.objectbox.PrepareDataService
import com.example.gopos_task.repository.AuthRepository
import com.example.gopos_task.repository.ItemsRepository
import com.example.gopos_task.utils.DispatcherProvider
import com.example.gopos_task.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import io.objectbox.BoxStore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class MainViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val itemsRepository: ItemsRepository,
    private val dispatchers: DispatcherProvider,
    private val itemsBox: ItemsBox
) : ViewModel() {

    init {
        PrepareDataService(authRepository, itemsRepository, dispatchers, itemsBox).prepareData()
    }

    fun getData(): List<Item>{
        return itemsBox.getItemsBox().all
    }
}