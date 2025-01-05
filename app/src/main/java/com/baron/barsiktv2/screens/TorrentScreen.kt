package com.baron.barsiktv2.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.baron.barsiktv2.MainViewModel
import com.baron.barsiktv2.NavigationGraph
import com.baron.barsiktv2.ui.theme.GrayBlack
import com.baron.barsiktv2.ui.theme.TorrentCard

@Composable
fun TorrentScreen(viewModel: MainViewModel,
                  navController: NavController
){
    val dataItem by viewModel.dataItem.collectAsState()
    val torrentInstance by viewModel.torrentInstance.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.torrent(dataItem.dataResult.data)
    }

    Box(
        modifier = Modifier.background(GrayBlack).fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            val list = torrentInstance.torrentFile
            items(list.size) { index ->
                TorrentCard(list[index]) {
                    if(!it.contentType.startsWith("video")) return@TorrentCard
                    viewModel.setTorrentFile(torrentInstance.hash, index)
                    navController.navigate(NavigationGraph.VIDEO_SCREEN.name)
                }
            }
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            viewModel.stopJobs()
        }
    }
}