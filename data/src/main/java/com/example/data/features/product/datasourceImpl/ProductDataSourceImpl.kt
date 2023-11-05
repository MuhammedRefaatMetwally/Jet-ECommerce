package com.example.data.features.product.dataSourceImpl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.data.api.WebServices
import com.example.data.features.product.dataSourceContract.ProductDataSource
import com.example.data.features.product.pagingSource.ProductsPagingSource
import com.example.data.safeAPiCall
import com.example.domain.common.ResultWrapper
import com.example.domain.features.product.model.Product
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductDataSourceImpl @Inject constructor(
    private val webServices: WebServices,
) : ProductDataSource {
    override suspend fun getProducts(categoryId: String?): Flow<ResultWrapper<List<Product>>> {
        return safeAPiCall { webServices.getProductsList(categoryId = categoryId) }
    }

    override suspend fun getProductsPaging(categoryId: String?): Flow<PagingData<Product>> {
        return Pager(
            config = PagingConfig(pageSize = 8),
            pagingSourceFactory = { ProductsPagingSource(webServices) }
        ).flow
    }

}