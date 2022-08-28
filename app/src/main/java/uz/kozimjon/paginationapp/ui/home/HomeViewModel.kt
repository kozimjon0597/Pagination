package uz.kozimjon.paginationapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import uz.kozimjon.paginationapp.model.CharacterResponse

class HomeViewModel : ViewModel() {

    fun getCharacter(): Flow<PagingData<CharacterResponse.ResultData>> {
        return Pager(PagingConfig(1), pagingSourceFactory = { CharacterDataSource() })
            .flow.cachedIn(viewModelScope)
    }
}