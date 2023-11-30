package com.example.data.features.cart.repository

import com.example.data.features.wishlist.repository.WishListRepositoryImpl
import com.example.domain.features.cart.repository.CartRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class di {
    @Binds
    abstract fun bindCartRepository(cartRepositoryImpl: WishListRepositoryImpl): CartRepository
}