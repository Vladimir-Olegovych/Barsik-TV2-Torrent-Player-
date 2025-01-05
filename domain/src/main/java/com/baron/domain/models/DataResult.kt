package com.baron.domain.models

import com.fasterxml.jackson.annotation.JsonProperty

class DataResult {
    @JsonProperty("data")
    val data: String = ""
    @JsonProperty("image")
    val image: String = ""
    @JsonProperty("description")
    val description: String? = null
}