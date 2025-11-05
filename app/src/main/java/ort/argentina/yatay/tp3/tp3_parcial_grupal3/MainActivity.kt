package ort.argentina.yatay.tp3.tp3_parcial_grupal3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.theme.Tp3_parcial_grupal3Theme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Tp3_parcial_grupal3Theme {
                MainScreen()
            }
        }
    }
}