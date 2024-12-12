package kg.study.distancetrackingapp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MainScreen(vm: MainVM) {

    val steps by vm.steps.collectAsState()
    val distance by vm.distance.collectAsState()

    Steps(steps = steps, distance = distance)
}

@Composable
fun Steps(steps: Int, distance: Double) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column {
            Text(
                text = "$steps steps",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.LightGray
            )
            Spacer(modifier = Modifier.size(12.dp))
            Text(
                text = "$distance m",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.LightGray
            )
        }
    }
}