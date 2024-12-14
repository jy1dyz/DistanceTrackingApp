package kg.study.distancetrackingapp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import dagger.hilt.android.AndroidEntryPoint
import kg.study.distancetrackingapp.Constants.STEP_COUNT_UPDATED

@AndroidEntryPoint
class StepCounterService : Service() {

    private lateinit var stepCounterManager: StepCounterManager

    override fun onBind(p0: Intent?): IBinder? = null

    override fun onCreate() {
        super.onCreate()
        Log.w("TAG", "onCreate service")
        stepCounterManager = StepCounterManager(applicationContext) { steps ->
            sendStepsToVM(steps)
        }
        startNotification()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        stepCounterManager.startListening()
        Log.w("TAG", "onStartCommand")
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        stepCounterManager.stopListening()
    }

    private fun startNotification() {
        val notification = NotificationCompat.Builder(this, CHANNEL)
            .setContentTitle("Step Counter")
            .setContentText("Counting steps")
            .setSmallIcon(R.drawable.baseline_do_not_step_24)
            .build()
//        val notificationManager = NotificationManagerCompat.from(applicationContext)
//        notificationManager.notify(1, notification)
        startForeground(1, notification)
    }



    private fun sendStepsToVM(steps: Int) {
        val intent = Intent(STEP_COUNT_UPDATED).apply {
            putExtra("step_count", steps)
        }
        sendBroadcast(intent)
    }

    companion object {
        const val CHANNEL = "Step_Channel"

    }


}