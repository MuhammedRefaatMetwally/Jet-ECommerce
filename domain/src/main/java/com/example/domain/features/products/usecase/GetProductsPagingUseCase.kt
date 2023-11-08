package com.example.domain.features.products.usecase

import androidx.paging.PagingData
import com.example.domain.features.products.model.Product
import com.example.domain.features.products.repository.ProductsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductsPagingUseCase @Inject constructor(
    private val repository: ProductsRepository
) {
    suspend fun invoke(categoryId: String? = null): Flow<PagingData<Product>> {
        return repository.getProductsPaging(categoryId)
    }


}