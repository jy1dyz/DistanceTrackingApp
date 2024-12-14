package kg.study.distancetrackingapp

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log

class StepCounterManager(
    private val context: Context,
    private val onStepCountUpdated: (Int) -> Unit
) {

    private val sensorManager: SensorManager =
        context.getSystemService(Context.SENSOR_SERVICE) as SensorManager

    private val stepCounterSensor: Sensor? =
        sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)

    /**
     * function to get all types of Sensor in device
     * */
    fun getAllSensors() {
        val sensors = sensorManager.getSensorList(Sensor.TYPE_ALL)
        sensors.forEach {
            Log.w("TAG", "sensors $it")
        }

    }

    private val stepListener = object : SensorEventListener {
        override fun onSensorChanged(event: SensorEvent?) {
            if (event?.sensor?.type == Sensor.TYPE_STEP_COUNTER) {
                val steps = event.values[0].toInt()

                Log.w("TAG", "onSensorChanged $steps")
                onStepCountUpdated(steps) // Call the callback with the new step count
            }
        }

        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
    }

    fun startListening() {
        Log.w("TAG", "startListening ")
        sensorManager.registerListener(
            stepListener,
            stepCounterSensor,
            SensorManager.SENSOR_DELAY_UI
        )
    }

    fun stopListening() {
        sensorManager.unregisterListener(stepListener)
    }
}