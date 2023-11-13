package com.example.data.features.products.dataSourceImpl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.data.api.WebServices
import com.example.data.features.products.dataSourceContract.ProductDataSource
import com.example.data.features.products.pagingSource.ProductsPagingSource
import com.example.data.safeAPiCall
import com.example.domain.common.ResultWrapper
import com.example.domain.features.products.model.Product
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductsDataSourceImpl @Inject constructor(
    private val webServices: WebServices,
) : ProductDataSource {
    //
    override suspend fun getProducts(categoryId: String?): Flow<ResultWrapper<List<Product?>?>> {
        return safeAPiCall { webServices.getProductsList(categoryId = categoryId) }
    }

    override suspend fun getProductsPaging(categoryId: String?): Flow<PagingData<Product>> {
        return Pager(
            config = PagingConfig(pageSize = 8),
            pagingSourceFactory = { ProductsPagingSource(webServices,categoryId) }
        ).flow
    }

    override suspend fun getSpecificProduct(productId: String): Flow<ResultWrapper<Product>> {
        return safeAPiCall { webServices.getSpecificProduct(productId) }
    }

}