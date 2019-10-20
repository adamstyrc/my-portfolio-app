package com.adamstyrc.portfolioapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.adamstyrc.portfolioapp.PortfolioApplication
import com.adamstyrc.portfolioapp.R
import com.adamstyrc.portfolioapp.coreComponent
import com.adamstyrc.portfolioapp.ui.viewmodel.HomeViewModel

class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProviders.of(this, coreComponent().viewModelFactory())
            .get(HomeViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_home, container, false)
        return root
    }
}