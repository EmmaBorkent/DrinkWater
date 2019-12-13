package com.ishiki.mizuwodrinkwater.services

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.net.Uri
import com.ishiki.mizuwodrinkwater.R
import com.ishiki.mizuwodrinkwater.activities.MainActivity

class NotificationReceiver : BroadcastReceiver() {

    private lateinit var notificationManager: NotificationManager
    private lateinit var notificationChannel: NotificationChannel
    private lateinit var builder: Notification.Builder
    private val channelId = "com.ishiki.mizuwodrinkwater.fragments"
    private val description = "Water Reminder Notification"

    override fun onReceive(context: Context?, intent: Intent?) {

        notificationManager = context?.getSystemService(Context.NOTIFICATION_SERVICE)
                as NotificationManager

        val notificationIntent = Intent(context.applicationContext, MainActivity()::class.java)
        notificationIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        val pendingIntent = PendingIntent.getActivity(context, 0, intent,
            PendingIntent.FLAG_UPDATE_CURRENT)

        val notificationSound: Uri = RingtoneManager
            .getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val audioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_NOTIFICATION)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()

        notificationChannel = NotificationChannel(channelId, description,
            NotificationManager.IMPORTANCE_DEFAULT)
        notificationChannel.enableLights(true)
        notificationChannel.lightColor = Color.WHITE
        notificationChannel.setSound(notificationSound, audioAttributes)

        notificationManager.createNotificationChannel(notificationChannel)
        builder = Notification.Builder(context, channelId)
            .setContentTitle("Water Drink Reminder")
            .setContentText("Drink Wat Water!")
            .setSmallIcon(R.mipmap.ic_launcher)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)

        notificationManager.notify(0, builder.build())

    }
}