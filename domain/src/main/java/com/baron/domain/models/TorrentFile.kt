package com.baron.domain.models

import com.fasterxml.jackson.annotation.JsonProperty

class TorrentFile  {
    @JsonProperty("path")
    val path: String = ""
    @JsonProperty("display_path")
    val displayPath: String = ""
    @JsonProperty("content_type")
    val contentType: String = ""
    @JsonProperty("length")
    val length: Long = 0L
}