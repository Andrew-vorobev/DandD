package com.example.dandd.presentation.ui.fragment.profileEdit

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dandd.domain.converter.ProfileToProfileView
import com.example.dandd.domain.usecase.ProfileUseCase
import com.example.dandd.presentation.ui.model.profile.ProfileView
import kotlinx.coroutines.launch

class EditProfileViewModel(
    private val useCase: ProfileUseCase,
    private val converter: ProfileToProfileView
) : ViewModel() {
    private val _profileState = MutableEditProfileState()
    val profileState = _profileState as EditProfileState

    fun save(back: () -> Unit) {
        viewModelScope.launch {
            useCase.saveProfile(
                converter.convertToItem(
                    ProfileView(
                        name = profileState.name,
                        description = profileState.description,
                        photo = profileState.photoUri,
                        link = "https://elibrary.ru/download/elibrary_44394445_69180276.pdf"
                    )
                )
            )
            back()
        }
    }

    fun updateName(newName: String) {
        _profileState.name = newName
    }

    fun updateDescription(newDescription: String) {
        _profileState.description = newDescription
    }

    fun updatePhotoUri(newUri: Uri) {
        _profileState.photoUri = newUri
    }

    private class MutableEditProfileState : EditProfileState {
        override var name: String by mutableStateOf("")
        override var description: String by mutableStateOf("")
        override var photoUri: Uri by mutableStateOf(Uri.EMPTY)
    }
}