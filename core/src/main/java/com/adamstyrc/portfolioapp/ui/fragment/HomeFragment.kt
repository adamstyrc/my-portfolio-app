package com.adamstyrc.portfolioapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.adamstyrc.portfolioapp.PortfolioApplication
import com.adamstyrc.portfolioapp.R
import com.adamstyrc.portfolioapp.coreComponent
import com.adamstyrc.portfolioapp.dagger.utils.CircleTransform
import com.adamstyrc.portfolioapp.ui.view.MaterialLabelView
import com.adamstyrc.portfolioapp.ui.viewmodel.HomeViewModel
import com.google.android.flexbox.FlexboxLayout
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel

    private lateinit var layoutParams: FlexboxLayout.LayoutParams

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_home, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        layoutParams = FlexboxLayout.LayoutParams(
            FlexboxLayout.LayoutParams.WRAP_CONTENT,
            FlexboxLayout.LayoutParams.WRAP_CONTENT
        ).apply {
            val skillLabelMargin = resources.getDimension(R.dimen.small_margin).toInt()
            setMargins(
                skillLabelMargin,
                skillLabelMargin,
                skillLabelMargin,
                skillLabelMargin
            )
        }

        viewModel = ViewModelProviders.of(this, coreComponent().viewModelFactory())
            .get(HomeViewModel::class.java)

        viewModel.user.observe(this, Observer { user ->
            if (user != null) {
                user.name?.let {
                    tvGeneralTitle.text = it
                }

                if (user.avatar != null) {
                    Picasso.get()
                        .load(user.avatar)
                        .placeholder(R.mipmap.ic_launcher)
                        .transform(CircleTransform())
                        .resize(200, 200)
                        .centerCrop()
                        .into(ivAvatar)
                }

                layoutSkillsBox.removeAllViews()
                user.skills?.skills?.forEach { skill ->
                    val materialLabelView = MaterialLabelView(activity!!)
                    materialLabelView.setLabel(skill)
                    layoutSkillsBox.addView(materialLabelView, layoutParams)
                }

                user.description?.let {
                    tvDescription.text = it
                }
            }
        })

        viewModel.snackbar().observe(this, Observer { text ->
            text?.let {
                Snackbar.make(layoutRoot, text, Snackbar.LENGTH_SHORT).show()
                viewModel.onSnackbarShown()
            }
        })
    }
}