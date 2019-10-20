package com.adamstyrc.portfolioapp.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.cardview.widget.CardView
import com.adamstyrc.portfolioapp.R
import kotlinx.android.synthetic.main.view_material_label.view.*

class MaterialLabelView : CardView {

    constructor(context: Context) : this(context, null, 0)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        LayoutInflater.from(context)
            .inflate(R.layout.view_material_label, this, true)

        val attrsArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.MaterialLabelView)

        attrsArray.getString(R.styleable.MaterialLabelView_text)
            ?.let { text ->
                tvContent.text = text
            }

        elevation = context.resources.getDimension(R.dimen.small_margin)
    }

    fun setLabel(skill: String) {
        tvContent.text = skill
    }
}