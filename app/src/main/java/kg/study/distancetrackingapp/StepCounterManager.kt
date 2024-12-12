package kg.study.distancetrackingapp

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager

class StepCounterManager(
    private val context: Context,
    private val onStepCountUpdated: (Int) -> Unit
) {
    private var initialStepCount: Int? = null
    private val sensorManager: SensorManager =
        context.getSystemService(Context.SENSOR_SERVICE) as SensorManager

    private val stepCounterSensor: Sensor? =
        sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)

    private val stepListener = object : SensorEventListener {
        override fun onSensorChanged(event: SensorEvent?) {
            if (event?.sensor?.type == Sensor.TYPE_STEP_COUNTER) {
                val steps = event.values[0].toInt()
                if (initialStepCount == null) {
                    initialStepCount = steps // Record the initial count
                }
                val stepsSinceStart = steps - (initialStepCount ?: 0)

                onStepCountUpdated(stepsSinceStart) // Call the callback with the new step count
            }
        }

        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
    }

    fun startListening() {
        sensorManager.registerListener(stepListener, stepCounterSensor, SensorManager.SENSOR_DELAY_UI)
    }

    fun stopListening() {
        sensorManager.unregisterListener(stepListener)
    }
}