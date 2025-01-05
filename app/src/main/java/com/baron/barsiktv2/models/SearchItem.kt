package com.baron.barsiktv2.models

import com.baron.domain.models.SearchResult
import com.baron.domain.models.SourceSearch

data class SearchItem(
    val sourceSearch: SourceSearch,
    val searchResult: SearchResult,
)