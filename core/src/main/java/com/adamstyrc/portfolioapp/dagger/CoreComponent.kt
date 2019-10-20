package com.adamstyrc.portfolioapp.dagger

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import com.adamstyrc.network.NetworkModule
import com.google.gson.Gson
import dagger.BindsInstance
import dagger.Component
import okhttp3.OkHttpClient
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Component(modules = [
    CoreDataModule::class,
    NetworkModule::class,
    ViewModelFactoryModule::class
])
@Singleton
interface CoreComponent {

    @Component.Builder interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): CoreComponent
    }

    fun okHttpClient(): OkHttpClient
    fun viewModelFactory(): ViewModelProvider.Factory
}
