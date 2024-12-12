package kg.study.distancetrackingapp.ui.forms

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun BoldText(title: String, modifier: Modifier = Modifier) {
    Text(
        text = title,
        modifier = modifier,
        fontSize = 16.sp,
        fontStyle = FontStyle.Normal,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun ColoredText(title: String, modifier: Modifier = Modifier, color: Color) {
    Text(
        text = title,
        modifier = modifier,
        fontSize = 16.sp,
        fontStyle = FontStyle.Normal,
        fontWeight = FontWeight.Bold,
        color = color
    )
}

@Composable
fun UsualText(title: String, modifier: Modifier = Modifier) {
    Text(
        text = title,
        modifier = modifier,
        fontSize = 14.sp,
        fontStyle = FontStyle.Normal,
        fontWeight = FontWeight.Normal
    )
}