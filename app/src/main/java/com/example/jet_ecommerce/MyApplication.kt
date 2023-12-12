package com.example.jet_ecommerce

import android.app.Application
import com.example.data.api.AppSharedReferences
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