package dev.shreyansh.peco.Receivers

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Icon
import dev.shreyansh.peco.R
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import dev.shreyansh.peco.Views.AddPersonalTaskFragment
import dev.shreyansh.peco.Views.MainActivity

class AlarmReceiver:BroadcastReceiver(){
    override fun onReceive(context: Context?, intent: Intent?) {


        var notify_manager: NotificationManager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        var main_activity_intent = Intent(context, MainActivity::class.java)
        var pi: PendingIntent = PendingIntent.getActivities(context, 0, arrayOf(main_activity_intent), 0)


        var notification: Notification = NotificationCompat.Builder(context)
                .setContentTitle("test")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pi)
                .setAutoCancel(true)
                .build()

        notify_manager.notify(0, notification)

    }
}