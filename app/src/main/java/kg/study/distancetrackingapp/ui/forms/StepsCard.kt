package kg.study.distancetrackingapp.ui.forms

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kg.study.distancetrackingapp.ui.theme.PurpleGrey40
import kg.study.distancetrackingapp.ui.theme.PurpleGrey80

@Composable
fun StepsCard(steps: Int, distance: Double) {
    Card(
        shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = PurpleGrey80),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 48.dp),
        border = BorderStroke(0.5.dp, Color.LightGray)
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            ColoredText(title = "$steps steps", color = PurpleGrey40)
            Spacer(modifier = Modifier.size(12.dp))
            ColoredText(title = "$distance m", color = PurpleGrey40)
        }
    }
}