package com.baron.domain.usecase

import com.baron.domain.models.SourceSearch
import com.baron.domain.models.TorrentInstance
import com.baron.domain.models.DataResult
import com.baron.domain.repository.BarsikRetrofitRepository

class BarsikRetrofitUseCase(private val barsikRetrofitRepository: BarsikRetrofitRepository) {
    suspend fun torrent(magnetUri: String): TorrentInstance? =
        barsikRetrofitRepository.torrent(magnetUri)

    suspend fun search(query: String, page: Int): Array<SourceSearch>? =
        barsikRetrofitRepository.search(query, page)

    suspend fun info(sourceId: String, entryId: String): DataResult? =
        barsikRetrofitRepository.info(sourceId, entryId)
}