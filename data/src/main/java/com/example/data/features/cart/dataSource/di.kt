package com.example.data.features.cart.dataSource

import com.example.data.features.wishlist.dataSource.WishListDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class di {
    @Binds
    abstract fun bindCartDataSource(
        cartDataSourceImpl: WishListDataSourceImpl
    ):CartDataSource

}