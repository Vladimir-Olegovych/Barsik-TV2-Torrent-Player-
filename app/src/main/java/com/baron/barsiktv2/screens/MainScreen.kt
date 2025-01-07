package com.baron.barsiktv2.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.baron.barsiktv2.MainViewModel
import com.baron.barsiktv2.NavigationGraph
import com.baron.barsiktv2.models.DataItem
import com.baron.barsiktv2.toSearchItemList
import com.baron.barsiktv2.ui.theme.GrayBlack
import com.baron.barsiktv2.ui.theme.SearchCard
import com.baron.barsiktv2.ui.theme.getMainColors
import com.baron.domain.models.TorrentInstance

@Composable
fun MainScreen(
    viewModel: MainViewModel,
    navController: NavController
) {
    val response by viewModel.search.collectAsState()
    val searchItemList = response.toSearchItemList()
    val loadedItems = rememberSaveable { ArrayList<DataItem>() }
    val keyboardController = LocalSoftwareKeyboardController.current

    @Composable
    fun loadMoreItems() {
        if (searchItemList.isEmpty()) return
        val itemsToLoad = searchItemList.drop(loadedItems.size).take(5)
        for (item in itemsToLoad) {
            val data = viewModel.info(item.sourceSearch.sourceId, item.searchResult.id)
            loadedItems.add(DataItem(item.sourceSearch, item.searchResult, data.value ?: continue))
        }
    }

    Box(
        modifier = Modifier.background(GrayBlack).fillMaxSize()
    ) {
        Column {

            val context = LocalContext.current
            val query = rememberSaveable { mutableStateOf("") }

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 8.dp, start = 8.dp),
                trailingIcon = {
                    Icon(imageVector = Icons.Filled.Search, contentDescription = "Search Icon")
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Search,
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        Toast.makeText(context, "Loading...", Toast.LENGTH_LONG).show()
                        loadedItems.clear()
                        viewModel.stopJobs()
                        viewModel.clearSearch()
                        viewModel.search(query.value, 0)
                        keyboardController?.hide()
                    }
                ),
                colors = getMainColors(),
                value = query.value,
                onValueChange = { query.value = it },
                label = { Text("Поиск") },
            )

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(5.dp),
                state = rememberLazyListState().apply {
                    if (isScrolledToEnd() || loadedItems.isEmpty()) loadMoreItems()
                }
            ) {
                items(loadedItems.size) { index ->
                    SearchCard(loadedItems[index]) {
                        viewModel.setDataItem(it)
                        viewModel.setTorrentInstance(TorrentInstance())
                        navController.navigate(NavigationGraph.TORRENT_SCREEN.name)
                    }
                }
            }
        }
    }
}

private fun LazyListState.isScrolledToEnd(): Boolean {
    return layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 1
}
