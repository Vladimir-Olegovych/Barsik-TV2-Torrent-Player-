package com.baron.barsiktv2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baron.barsiktv2.models.DataItem
import com.baron.domain.models.DataResult
import com.baron.domain.models.SourceSearch
import com.baron.domain.models.TorrentFile
import com.baron.domain.models.TorrentInstance
import com.baron.domain.usecase.BarsikRetrofitUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val barsikRetrofitUseCase: BarsikRetrofitUseCase
) : ViewModel() {

    private val _torrentFile = MutableStateFlow(Pair("", 0))
    val torrentFile: StateFlow<Pair<String, Int>> = _torrentFile.asStateFlow()

    private val _dataItem = MutableStateFlow(DataItem.EMPTY)
    val dataItem: StateFlow<DataItem> = _dataItem.asStateFlow()

    private val _torrentInstance = MutableStateFlow(TorrentInstance())
    val torrentInstance: StateFlow<TorrentInstance> = _torrentInstance.asStateFlow()

    private val _search = MutableStateFlow<Array<SourceSearch>>(emptyArray())
    val search: StateFlow<Array<SourceSearch>> = _search.asStateFlow()

    private val _data = MutableStateFlow(DataResult())
    val data: StateFlow<DataResult> = _data.asStateFlow()

    private val taskArray = ArrayList<Job>()

    fun setTorrentFile(hash: String, index: Int){
        _torrentFile.value = Pair(hash, index)
    }

    fun setTorrentInstance(torrentInstance: TorrentInstance){
        _torrentInstance.value = torrentInstance
    }

    fun setDataItem(dataItem: DataItem){
        _dataItem.value = dataItem
    }

    fun torrent(magnetUri: String) {
        taskArray.add(
            viewModelScope.launch(Dispatchers.IO) {
                _torrentInstance.value = barsikRetrofitUseCase.torrent(magnetUri) ?: return@launch
            }
        )
    }

    fun search(query: String, page: Int) {
        taskArray.add(
            viewModelScope.launch(Dispatchers.IO) {
                _search.value = barsikRetrofitUseCase.search(query, page) ?: return@launch
            }
        )
    }

    fun info(sourceId: String, entryId: String) {
        taskArray.add(
            viewModelScope.launch(Dispatchers.IO) {
                _data.value = barsikRetrofitUseCase.info(sourceId, entryId) ?: return@launch
            }
        )
    }

    fun clearSearch(){
        _data.value = DataResult()
        _search.value = emptyArray()
    }

    fun stopJobs() {
        taskArray.forEach { it.cancel() }
        taskArray.clear()
    }
}