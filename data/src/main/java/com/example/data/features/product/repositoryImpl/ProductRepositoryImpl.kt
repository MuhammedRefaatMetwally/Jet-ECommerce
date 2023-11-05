package com.example.data.features.product.repositoryImpl

import androidx.paging.Pager
import androidx.paging.PagingData
import com.example.data.features.product.dataSourceContract.ProductDataSource
import com.example.data.features.product.pagingSource.ProductsPagingSource
import com.example.domain.common.ResultWrapper
import com.example.domain.features.product.model.Product
import com.example.domain.features.product.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private  val dataSource: ProductDataSource,
) : ProductRepository {
    //
    override suspend fun getProducts(categoryId: String?): Flow<ResultWrapper<List<Product>>> {
        return dataSource.getProducts(categoryId)
    }

    override suspend fun getProductsPaging(categoryId: String?): Flow<PagingData<Product>>  {
       return dataSource.getProductsPaging(categoryId)
    }


}