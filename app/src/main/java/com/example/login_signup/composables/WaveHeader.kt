package com.example.login_signup.composables

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.login_signup.ui.theme.LoginSignupTheme

@Composable
fun WaveHeader(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Canvas(modifier = Modifier.fillMaxWidth().height(200.dp)) {
            val waveHeight = 100.dp.toPx()
            val waveWidth = size.width / 4
            val wavePath = Path().apply {
                moveTo(0f, size.height)
                cubicTo(
                    waveWidth, size.height - waveHeight,
                    3 * waveWidth, size.height + waveHeight,
                    size.width, size.height
                )
                lineTo(size.width, 0f)
                lineTo(0f, 0f)
                close()
            }
            drawPath(path = wavePath, color = Color(0xFF6200EE))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WaveHeaderPreview() {
    LoginSignupTheme {
        WaveHeader()
    }
}


