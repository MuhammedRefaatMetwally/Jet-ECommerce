package com.example.data.features.product.pagingSource

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.api.WebServices
import com.example.domain.features.product.model.Product
import javax.inject.Inject

class ProductsPagingSource @Inject constructor(
    val webServices: WebServices,
    val categoryId: String? = null
) : PagingSource<Int, Product>() {
    override fun getRefreshKey(state: PagingState<Int, Product>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {
        return try {
            val currentPage = params.key ?: 1
            Log.w("paging", "page number:$currentPage ")
            val products = webServices.getProductsList(pageNumber = currentPage, categoryId = categoryId)
            LoadResult.Page(
                data = products.data!!,
                prevKey = if (currentPage == 1) null else currentPage,
                nextKey = if (products.data.isEmpty()) null else currentPage + 1
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }

    }
}