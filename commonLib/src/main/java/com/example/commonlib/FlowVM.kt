package com.example.commonlib

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.commonlib.base.BaseViewModel
import com.example.commonlib.base.StateLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

fun <T> BaseViewModel.launchUIFlow(
    response: suspend CoroutineScope.() -> T,
    liveData: StateLiveData<T>
) {
    viewModelScope.launch {
        flow {
            emit(response)
        }.onStart {
        }.flowOn(Dispatchers.IO).catch { cause ->
            run {
                throw  cause
            }
        }.onCompletion {
        }.collectLatest {
            liveData.value=it.invoke(this)
        }
    }
}