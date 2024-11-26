package com.example.dandd.presentation.ui.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.dungeonanddragonsapp.R
import kotlinx.coroutines.channels.Channel

class ReceiverChannelManger(
    private val notificationManager: NotificationManagerCompat,
    context: Context
) {
    private val defaultChannel = Channel(
        index = context.resources.getString(R.string.receiver_id),
        name = context.resources.getString(R.string.receiver_name)
    )

    fun createNotificationChannels(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannelsSafety()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannelsSafety() {
        createChannel(defaultChannel)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createChannel(defaultChannel: Channel) {
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(
            defaultChannel.index,
            defaultChannel.name,
            importance
        ).apply {
            enableLights(true)
            enableVibration(true)
            setShowBadge(true)
            lockscreenVisibility = NotificationCompat.VISIBILITY_PRIVATE
        }
        notificationManager.createNotificationChannel(channel)
    }

    private class Channel (
        val index: String,
        val name: String
    )
}