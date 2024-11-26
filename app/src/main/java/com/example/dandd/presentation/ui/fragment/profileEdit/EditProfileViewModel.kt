package com.example.dandd.presentation.ui.fragment.profileEdit

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dandd.domain.converter.ProfileToProfileView
import com.example.dandd.domain.usecase.ProfileUseCase
import com.example.dandd.presentation.ui.model.profile.ProfileView
import com.example.dandd.presentation.ui.receiver.Receiver
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.LocalTime
import org.threeten.bp.ZoneId
import org.threeten.bp.format.DateTimeFormatter

class EditProfileViewModel(
    private val useCase: ProfileUseCase,
    private val converter: ProfileToProfileView
) : ViewModel() {
    private var _profileState = MutableStateFlow(EditProfileState())
    val profileState = _profileState.asStateFlow()

    fun save(back: () -> Unit, context: Context) {
        validateTimer()
        if (profileState.value.timeError == null) {
            viewModelScope.launch {
                useCase.saveProfile(
                    converter.convertToItem(
                        ProfileView(
                            name = profileState.value.name,
                            description = profileState.value.description,
                            photo = profileState.value.photoUri,
                            link = "https://elibrary.ru/download/elibrary_44394445_69180276.pdf"
                        )
                    )
                )
                saveAlarm(context)
                back()
            }
        }
    }

    fun updateName(newName: String) {
        _profileState.value.name = newName
    }

    fun updateDescription(newDescription: String) {
        _profileState.value.description = newDescription
    }

    fun updatePhotoUri(newUri: Uri) {
        _profileState.value.photoUri = newUri
    }

    fun toggleTimer() {
        _profileState.value.isNeedToShowTimePicker = !_profileState.value.isNeedToShowTimePicker
    }

    fun updateTimeInput(input: String) {
        _profileState.value.timeString = input
        validateTimer()
    }
    fun updateTimeTimer(hour: Int, minute: Int) {
        _profileState.value.time = LocalTime.of(hour, minute)
        _profileState.value.timeString = _profileState.value.time.format(formatter)
        _profileState.value.timeError = null
    }

    private fun saveAlarm(context: Context) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val dateTime = LocalDateTime.of(LocalDate.now(), profileState.value.time)
        val timeInMillis = dateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()

        val notifyIntent = Intent(context, Receiver::class.java)

        notifyIntent.putExtras(
            Bundle().apply {
                putString("NOTIFICATION", "Сработало уведомление")
            }
        )

        val notifyPendingIntent = PendingIntent.getBroadcast(
            context,
            0,
            notifyIntent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        try {
            alarmManager.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                timeInMillis,
                notifyPendingIntent
            )
        } catch (e: SecurityException) {
            Log.e("alarmManager", "Failed to set reminder")
        }
    }

    private fun validateTimer() {
        try {
            _profileState.value.time = LocalTime.parse(profileState.value.timeString, formatter)
            _profileState.value.timeError = null
        } catch (e: Exception) {
            _profileState.value.timeError = "Неправильный ввод"
        }
    }

    companion object {
        private val formatter = DateTimeFormatter.ofPattern("HH:mm")
    }
}