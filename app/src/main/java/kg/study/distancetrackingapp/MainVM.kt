package kg.study.distancetrackingapp

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import java.math.BigDecimal
import java.math.RoundingMode
import javax.inject.Inject

@HiltViewModel
class MainVM @Inject constructor() : ViewModel(), ContainerHost<StepsState, StepsSideEffect> {

    override val container: Container<StepsState, StepsSideEffect> =
        container(StepsState()) {

        }

    fun updateSteps(count: Int) = intent {
        val tempDistance = (0.8 * count)
        val roundedDistance = BigDecimal(tempDistance).setScale(2, RoundingMode.HALF_UP)
        reduce {
            state.copy(steps = count, distance = roundedDistance.toDouble())
        }
    }

}