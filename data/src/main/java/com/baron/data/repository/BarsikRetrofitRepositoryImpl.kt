package com.baron.data.repository

import com.baron.domain.models.SourceSearch
import com.baron.domain.models.TorrentInstance
import com.baron.data.network.BarsikRetrofit
import com.baron.domain.models.DataResult
import com.baron.domain.repository.BarsikRetrofitRepository
import kotlinx.coroutines.delay

class BarsikRetrofitRepositoryImpl(private val barsikRetrofit: BarsikRetrofit): BarsikRetrofitRepository {
    override suspend fun torrent(magnetUri: String): TorrentInstance? {
        return process(8) { barsikRetrofit.torrent(magnetUri) }
    }

    override suspend fun search(query: String, page: Int): Array<SourceSearch>? {
        return process(8) { barsikRetrofit.search(query, page) }
    }

    override suspend fun info(sourceId: String, entryId: String): DataResult? {
        return process(1) { barsikRetrofit.info(sourceId, entryId) }
    }

    private suspend fun <T> process(
        maxAttempts: Int,
        request: suspend () -> T?,
    ): T? {
        for (i in 0 until maxAttempts){
            try {
                return request.invoke()
            } catch (e: Throwable){
                println(e.message)
                delay(DELAY)
            }
        }
        return null
    }

    companion object {
        const val DELAY = 1100L
    }
}