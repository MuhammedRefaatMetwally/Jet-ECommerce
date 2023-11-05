package com.example.domain.features.product.usecase

import androidx.paging.PagingData
import com.example.domain.features.product.model.Product
import com.example.domain.features.product.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductsPagingUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    suspend fun invoke(categoryId: String? = null): Flow<PagingData<Product>> {
        return repository.getProductsPaging(categoryId)
    }


}