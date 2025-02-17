package com.platform.mediacenter.data.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.platform.mediacenter.data.model.movie.MovieResponse
import com.platform.mediacenter.data.repository.MovieRepository
import javax.inject.Inject

class MoviesPagingSource @Inject constructor(private val repository: MovieRepository) :
    PagingSource<Int , MovieResponse.MovieResponseItem>(){
    override fun getRefreshKey(state: PagingState<Int, MovieResponse.MovieResponseItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieResponse.MovieResponseItem> {
        return try {
            val currentPage = params.key ?: 1
            val response = repository.getMovies(currentPage).data


            LoadResult.Page(
                data = listOf(response!![0]),
                prevKey = null,
                nextKey = currentPage + 1
            )
        }catch (e:Exception){
            LoadResult.Error(e)
        }
    }

}