package com.baron.barsiktv2.screens.navgraph

import kotlinx.serialization.Serializable

sealed interface Screen {

    @Serializable
    data object Main : Screen

    @Serializable
    data class Torrent(val data: String) : Screen

    @Serializable
    data object Video : Screen

}