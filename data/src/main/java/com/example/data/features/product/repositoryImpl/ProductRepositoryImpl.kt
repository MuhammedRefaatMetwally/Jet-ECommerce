package com.example.data.features.product.repositoryImpl

import com.example.data.features.product.dataSourceContract.ProductDataSource
import com.example.domain.common.ResultWrapper
import com.example.domain.features.product.model.Product
import com.example.domain.features.product.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private  val dataSource: ProductDataSource
) : ProductRepository {
    override suspend fun getProducts(): Flow<ResultWrapper<List<Product?>?>> {
        return dataSource.getProducts()
    }


}