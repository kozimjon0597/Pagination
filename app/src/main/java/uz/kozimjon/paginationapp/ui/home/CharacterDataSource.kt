package uz.kozimjon.paginationapp.ui.home

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import uz.kozimjon.paginationapp.api.ApiClient
import uz.kozimjon.paginationapp.model.CharacterResponse

class CharacterDataSource: PagingSource<Int, CharacterResponse.ResultData>() {

    override fun getRefreshKey(state: PagingState<Int, CharacterResponse.ResultData>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterResponse.ResultData> {
        return try {
            val nextPage: Int = params.key ?: FIRST_PAGE_INDEX
            val response = ApiClient.instance.getCharacter(nextPage)

            LoadResult.Page(data = response.results,
                    prevKey = getPrevPageNumber(response),
                    nextKey = getNextPageNumber(response))
        }
        catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    private fun getPrevPageNumber(response: CharacterResponse): Int? {
        var prevPageNumber: Int? = null
        if(response.info.prev != null) {
            val uri = Uri.parse(response.info.prev)
            val prevPageQuery = uri.getQueryParameter("page")

            prevPageNumber = prevPageQuery?.toInt()
        }

        return prevPageNumber
    }

    private fun getNextPageNumber(response: CharacterResponse): Int? {
        var nextPageNumber: Int? = null
        if(response.info.next != null) {
            val uri = Uri.parse(response.info.next)
            val nextPageQuery = uri.getQueryParameter("page")
            nextPageNumber = nextPageQuery?.toInt()
        }

        return nextPageNumber
    }

    companion object {
        private const val FIRST_PAGE_INDEX = 1
    }
}