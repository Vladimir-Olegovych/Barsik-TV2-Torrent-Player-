package com.baron.domain.models

import com.fasterxml.jackson.annotation.JsonProperty

class TorrentInstance {
    @JsonProperty("files")
    val torrentFile: Array<TorrentFile> = emptyArray()
    @JsonProperty("hash")
    val hash: String = ""
    @JsonProperty("length")
    val length: Long = 0L
}