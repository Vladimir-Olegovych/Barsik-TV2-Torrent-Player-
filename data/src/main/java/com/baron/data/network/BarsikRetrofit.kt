package com.baron.data.network

import com.baron.domain.models.SourceSearch
import com.baron.domain.models.TorrentInstance
import com.baron.domain.models.DataResult
import retrofit2.http.GET
import retrofit2.http.Query

interface BarsikRetrofit {

    @GET("torrent/list")
    suspend fun torrent(
        @Query("magnet_uri") magnetUri: String,
    ): TorrentInstance

    @GET("sources/search")
    suspend fun search(
        @Query("query") query: String,
        @Query("page") page: Int
    ): Array<SourceSearch>

    @GET("sources/data")
    suspend fun info(
        @Query("source_id") sourceId: String,
        @Query("entry_id") entryId: String
    ): DataResult

    companion object {
        const val BASE_URL = "http://192.168.1.200:8082/api/v1/"
        const val TIMEOUT = 30_000L
    }
}