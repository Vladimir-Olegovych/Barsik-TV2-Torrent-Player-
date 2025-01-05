package com.baron.barsiktv2

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Surface
import com.baron.barsiktv2.screens.MainScreen
import com.baron.barsiktv2.screens.TorrentScreen
import com.baron.barsiktv2.screens.VideoScreen
import com.baron.barsiktv2.ui.theme.BarsikTV2Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalTvMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContent {
            BarsikTV2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    shape = RectangleShape
                ) { AppSetup() }
            }
        }
    }
}
@Composable
fun AppSetup() {
    val viewModel = hiltViewModel<MainViewModel>()
    val navController = rememberNavController()
    NavHost(navController, startDestination = NavigationGraph.MAIN_SCREEN.name) {
        composable(NavigationGraph.MAIN_SCREEN.name) { MainScreen(viewModel, navController) }
        composable(NavigationGraph.TORRENT_SCREEN.name) { TorrentScreen(viewModel, navController) }
        composable(NavigationGraph.VIDEO_SCREEN.name) { VideoScreen(viewModel, navController) }
    }
}