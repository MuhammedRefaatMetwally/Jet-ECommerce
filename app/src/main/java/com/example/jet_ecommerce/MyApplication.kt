package com.example.jet_ecommerce

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import com.example.data.api.AppSharedReferences
import com.example.jet_ecommerce.ui.features.main.wishlist.WishListViewModel
import com.stripe.android.BuildConfig
import com.stripe.android.PaymentConfiguration
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
      AppSharedReferences.init(this)

    }
}