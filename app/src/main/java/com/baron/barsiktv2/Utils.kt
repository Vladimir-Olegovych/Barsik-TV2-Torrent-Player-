package com.baron.barsiktv2

import com.baron.barsiktv2.models.SearchItem
import com.baron.domain.models.SourceSearch

fun Array<SourceSearch>.toSearchItemList(): List<SearchItem> {



    val arrayList = ArrayList<SearchItem>()
    this.forEach { search ->
        search.results.forEach { result ->
            arrayList.add(SearchItem(search, result))
        }
    }
    return arrayList.toList()
}