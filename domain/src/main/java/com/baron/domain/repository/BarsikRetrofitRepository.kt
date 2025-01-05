package com.baron.domain.repository

import com.baron.domain.models.SourceSearch
import com.baron.domain.models.TorrentInstance
import com.baron.domain.models.DataResult

interface BarsikRetrofitRepository {
    suspend fun torrent(magnetUri: String): TorrentInstance?

    suspend fun search(query: String, page: Int): Array<SourceSearch>?

    suspend fun info(sourceId: String, entryId: String): DataResult?
}