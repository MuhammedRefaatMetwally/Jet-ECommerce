package com.example.data.features.products.repositoryImpl

import androidx.paging.PagingData
import com.example.data.features.products.dataSourceContract.ProductDataSource
import com.example.domain.common.ResultWrapper
import com.example.domain.features.products.model.Product
import com.example.domain.features.products.repository.ProductsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(
    private  val dataSource: ProductDataSource,
) : ProductsRepository {
    //
    override suspend fun getProducts(categoryId: String?): Flow<ResultWrapper<List<Product?>?>> {
        return dataSource.getProducts(categoryId)
    }

    override suspend fun getProductsPaging(categoryId: String?): Flow<PagingData<Product>> {
       return dataSource.getProductsPaging(categoryId)
    }

    override suspend fun getSpecificProduct(productId: String): Flow<ResultWrapper<Product>> {
        return  dataSource.getSpecificProduct(productId)
    }


}