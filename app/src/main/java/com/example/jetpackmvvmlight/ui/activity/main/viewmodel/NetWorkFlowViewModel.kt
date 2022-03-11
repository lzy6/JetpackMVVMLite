package com.example.jetpackmvvmlight.ui.activity.main.viewmodel

import com.example.commonlib.base.StateLiveData
import com.example.commonlib.launchUIFlow
import com.example.jetpackmvvmlight.app.base.BaseAPPViewModel
import com.example.jetpackmvvmlight.entity.User
import kotlinx.coroutines.flow.MutableStateFlow

class NetWorkFlowViewModel : BaseAPPViewModel() {

    val testFlow=MutableStateFlow("1")
    val singEntityLiveData = StateLiveData<User>()

    fun singEntity()=launchUIFlow(
        response = { api.singEntity().data!! },
        liveData = singEntityLiveData
    )
}