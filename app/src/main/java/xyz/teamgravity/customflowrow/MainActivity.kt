package xyz.teamgravity.customflowrow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import xyz.teamgravity.customflowrow.ui.theme.CustomFlowRowTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CustomFlowRowTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FlowRow {
                        repeat(50) {
                            Box(
                                modifier = Modifier
                                    .width(Random.nextInt(50, 200).dp)
                                    .height(100.dp)
                                    .background(Color(Random.nextLong(0xFFFFFFFF)))
                            )
                        }
                    }
                }
            }
        }
    }
}