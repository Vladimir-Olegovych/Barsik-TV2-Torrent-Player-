package com.baron.domain.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class SearchResult {
    @JsonProperty("title")
    val title: String = ""
    @JsonProperty("id")
    val id: String = ""
    @JsonProperty("size")
    val size: String = ""
    @JsonProperty("date")
    val date: String = ""
}