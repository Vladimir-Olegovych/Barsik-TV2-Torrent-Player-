package com.baron.barsiktv2.models

import com.baron.domain.models.DataResult
import com.baron.domain.models.SearchResult
import com.baron.domain.models.SourceSearch
import java.io.Serializable

data class DataItem(
    val sourceSearch: SourceSearch,
    val searchResult: SearchResult,
    val dataResult: DataResult
): Serializable {
    companion object {
        val EMPTY = DataItem(SourceSearch(), SearchResult(), DataResult())
    }
}