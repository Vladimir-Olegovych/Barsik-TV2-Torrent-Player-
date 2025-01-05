package com.baron.barsiktv2.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.baron.barsiktv2.MainViewModel
import com.baron.barsiktv2.NavigationGraph
import com.baron.barsiktv2.models.DataItem
import com.baron.barsiktv2.toSearchItemList
import com.baron.barsiktv2.ui.theme.SearchCard
import com.baron.barsiktv2.ui.theme.GrayBlack

@Composable
fun MainScreen(
    viewModel: MainViewModel,
    navController: NavController
) {
    val response by viewModel.search.collectAsState()
    val data by viewModel.data.collectAsState()
    val searchItemList = response.toSearchItemList()
    val loadedItems = rememberSaveable { ArrayList<DataItem>() }

    fun loadMoreItems() {
        if (searchItemList.isEmpty()) return
        val itemsToLoad = searchItemList.drop(loadedItems.size).take(5)
        itemsToLoad.forEach { item ->
            viewModel.info(item.sourceSearch.sourceId, item.searchResult.id)
            loadedItems.add(DataItem(item.sourceSearch, item.searchResult, data))
        }
    }

    Box(
        modifier = Modifier.background(GrayBlack).fillMaxSize()
    ) {
        Column {

            val query = remember { mutableStateOf("") }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp),
                    colors = OutlinedTextFieldDefaults.colors(Color.White),
                    value = query.value,
                    onValueChange = { query.value = it },
                    label = { Text("Поиск") }
                )

                Button(
                    onClick = {
                        loadedItems.clear()
                        viewModel.clearSearch()
                        viewModel.search(query.value, 0)
                        query.value = ""
                    },
                    colors = ButtonDefaults.buttonColors(Color.Blue)
                ) {
                    Text(text = "Искать", color = Color.White)
                }
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(40.dp),
                contentPadding = PaddingValues(5.dp),
                state = rememberLazyListState().apply {
                    if (isScrolledToEnd() || loadedItems.isEmpty()) loadMoreItems()
                }
            ) {
                items(loadedItems.size) { index ->
                    SearchCard(loadedItems[index]) {
                        viewModel.setDataItem(it)
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
