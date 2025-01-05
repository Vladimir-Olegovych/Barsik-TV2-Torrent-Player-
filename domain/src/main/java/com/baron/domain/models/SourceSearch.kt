package com.baron.domain.models

import com.fasterxml.jackson.annotation.JsonProperty


class SourceSearch {
    @JsonProperty("results")
    val results: Array<SearchResult> = emptyArray()
    @JsonProperty("source_name")
    val sourceName: String = ""
    @JsonProperty("source_id")
    val sourceId: String = ""
}