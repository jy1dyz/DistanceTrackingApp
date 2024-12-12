package kg.study.distancetrackingapp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kg.study.distancetrackingapp.ui.forms.StepsCard
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun MainScreen(vm: MainVM) {

    val state by vm.collectAsState()
    Steps(steps = state.steps, distance = state.distance)
}

@Composable
fun Steps(steps: Int, distance: Double) {
    StepsCard(steps = steps, distance = distance)

}