package com.baron.barsiktv2.models

import com.baron.domain.models.DataResult
import com.baron.domain.models.SearchResult
import com.baron.domain.models.SourceSearch

data class DataItem(
    val sourceSearch: SourceSearch,
    val searchResult: SearchResult,
    val dataResult: DataResult
) {
    companion object {
        val EMPTY = DataItem(SourceSearch(), SearchResult(), DataResult())
    }
}