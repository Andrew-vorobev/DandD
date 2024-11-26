package com.example.dandd.presentation.ui.fragment.home

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dandd.domain.converter.ProfileToProfileView
import com.example.dandd.domain.usecase.ProfileUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val profileUseCase: ProfileUseCase,
    private val mapper: ProfileToProfileView
) : ViewModel() {
    private val _homeState = MutableStateFlow(HomeState())
    val homeState: StateFlow<HomeState> = _homeState.asStateFlow()

    init {
        load()
    }

    fun loadReport(context: Context) {
        viewModelScope.launch {
            downloadPdfFile(context)
        }
    }

    private fun downloadPdfFile(context: Context) {
        val request = DownloadManager.Request(Uri.parse(homeState.value.link)).apply {
            setTitle("отчёт")
            setDescription("Загрузка...")
            setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, NAME_FILE)
            setMimeType("application/pdf")
        }

        val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        downloadManager.enqueue(request)
    }

    private fun load() {
        viewModelScope.launch {
            profileUseCase.getProfile().collect { profile ->
                val state = mapper.convertToView(profile)
                _homeState.value = HomeState(
                    name = state.name,
                    description = state.description,
                    photo = state.photo,
                    link = state.link
                )
            }
        }

    }
    companion object{
        const val NAME_FILE = "file.pdf"
    }
}