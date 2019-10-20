package com.adamstyrc.portfolioapp

import android.app.Activity
import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import com.adamstyrc.portfolioapp.dagger.CoreComponent
import com.adamstyrc.portfolioapp.dagger.DaggerCoreComponent

class PortfolioApplication : Application() {

    private val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.builder()
            .application(this)
            .build()
    }

    companion object {
        @JvmStatic
        fun coreComponent(context: Context) =
            (context.applicationContext as PortfolioApplication).coreComponent
    }
}

fun Activity.coreComponent() = PortfolioApplication.coreComponent(this)

fun Fragment.coreComponent() = activity!!.coreComponent()
