package kg.study.distancetrackingapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kg.study.distancetrackingapp.Constants.STEP_COUNT_UPDATED
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import java.math.BigDecimal
import java.math.RoundingMode
import javax.inject.Inject

@HiltViewModel
class MainVM @Inject constructor() : ViewModel(), ContainerHost<StepsState, StepsSideEffect> {

    override val container: Container<StepsState, StepsSideEffect> =
        container(StepsState())

    fun updateSteps(count: Int) = intent {
        val tempDistance = (0.8 * count)
        val roundedDistance = BigDecimal(tempDistance).setScale(2, RoundingMode.HALF_UP)
        reduce {
            state.copy(steps = count, distance = roundedDistance.toDouble())
        }
    }

    fun registerReceiver(context: Context) {
        val filter = IntentFilter(STEP_COUNT_UPDATED)
        context.registerReceiver(stepsReceiver, filter, Context.RECEIVER_NOT_EXPORTED)
    }

    fun unregisterReceiver(context: Context) {
        context.unregisterReceiver(stepsReceiver)
    }

    private val stepsReceiver = object: BroadcastReceiver() {
        override fun onReceive(p0: Context?, p1: Intent?) {
            val steps = p1?.getIntExtra("step_count", 0) ?: 0
            Log.w("TAG", "onReceive $steps")
            updateSteps(steps)
        }
    }

}