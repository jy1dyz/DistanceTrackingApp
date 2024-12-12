package kg.study.distancetrackingapp

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import dagger.hilt.android.AndroidEntryPoint
import kg.study.distancetrackingapp.ui.theme.DistanceTrackingAppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val vm by viewModels<MainVM>()

    private val stepCounterManager by lazy {
        StepCounterManager(applicationContext) { steps ->
            vm.updateSteps(steps)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        checkPermission()
        stepCounterManager.startListening()
        setContent {
            DistanceTrackingAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(vm)
                }
            }
        }
    }

    private fun checkPermission() {
        if (ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.ACTIVITY_RECOGNITION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACTIVITY_RECOGNITION), 100)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        stepCounterManager.stopListening()
    }
}