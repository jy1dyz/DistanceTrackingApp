package kg.study.distancetrackingapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.math.RoundingMode
import javax.inject.Inject

@HiltViewModel
class MainVM @Inject constructor(): ViewModel() {

    val steps = MutableStateFlow(0)
    val distance = MutableStateFlow(0.0)

    fun updateSteps(count: Int) {
        viewModelScope.launch {
            steps.value = count
            val tempDistance = (0.8 * count)
            val roundedDistance = BigDecimal(tempDistance).setScale(2, RoundingMode.HALF_UP)
            distance.value = roundedDistance.toDouble()
        }
    }
}