package com.example.data.features.wishlist.repository

import com.example.data.features.wishlist.repository.WishListRepositoryImpl
import com.example.domain.features.cart.repository.CartRepository
import com.example.domain.features.wishlist.repository.WishListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class di {
    @Binds
    abstract fun bindCartRepository(wishListRepositoryImpl: WishListRepositoryImpl): WishListRepository
}