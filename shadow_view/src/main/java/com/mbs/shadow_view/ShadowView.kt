package com.mbs.shadow_view

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat

@Suppress("unused", "MoveVariableDeclarationIntoWhen")
class ShadowView : RelativeLayout, View.OnClickListener {
    private var pClick: OnClickListener? = null
    private var init = true
    private val gd = GradientDrawable()
    private var rad = 0f
    private var shape = 0
    private var isClickTransferable = true
    private var isSelfClickable = true
    private var fromChild = false
    private var shadow: Shadow? = null
    private var shadowPosition = Shadow.Position.CENTER

    constructor(context: Context?) : super(context) {
        background =
            ColorDrawable(ContextCompat.getColor(getContext(), android.R.color.transparent))
    }

    constructor(context: Context?, attrs: AttributeSet) : super(context, attrs) {
        init(attrs)
    }

    private fun init(set: AttributeSet) {
        val ta = context.obtainStyledAttributes(set, R.styleable.ShadowView)
        var shadowColor = ta.getString(R.styleable.ShadowView_shadowColor)
        if (shadowColor == null) {
            shadowColor = "000000"
        }
        isClickTransferable = ta.getBoolean(R.styleable.ShadowView_clickTransferable, false)
        isSelfClickable = ta.getBoolean(R.styleable.ShadowView_selfClickable, true)
        val spread = ta.getInt(R.styleable.ShadowView_shadowSpread, 1)
        val shadowAlpha = ta.getInt(R.styleable.ShadowView_shadowAlpha, 255)
        rad = ta.getDimension(R.styleable.ShadowView_radius, 0f)
        val shadowShape = ta.getInteger(R.styleable.ShadowView_shape, 0)
        when (shadowShape) {
            1 -> shape = GradientDrawable.OVAL
            2 -> shape = GradientDrawable.LINE
            3 -> shape = GradientDrawable.RING
            else -> GradientDrawable.RECTANGLE
        }
        //ShadowPosition
        val sPosition = ta.getInteger(R.styleable.ShadowView_shadowPosition, 0)
        when (sPosition) {
            1 -> shadowPosition = Shadow.Position.RIGHT
            2 -> shadowPosition = Shadow.Position.LEFT
            3 -> shadowPosition = Shadow.Position.TOP
            4 -> shadowPosition = Shadow.Position.BOTTOM
        }
        val cornerRadius = floatArrayOf(rad, rad, rad, rad, rad, rad, rad, rad)
        this.shadow = Shadow(spread, shadowAlpha, shadowColor, shape, cornerRadius, shadowPosition)
        background = this.shadow!!.getShadow()
        setOnClickListener(this)
        ta.recycle()
    }

    override fun setOnClickListener(l: OnClickListener?) {
        if (init) {
            super.setOnClickListener(l)
            init = false
            return
        }
        pClick = l
    }

    override fun onClick(v: View) {
        if (!isSelfClickable && !fromChild) {
            return
        }
        val parent = parent
        if (parent is ShadowView && isClickTransferable) {
            parent.fromChild = true
            parent.onClick(parent)
        }

        if (pClick != null) pClick!!.onClick(v)
        fromChild = false
    }

    /**
     * We can not change background color due to our manipulations
     */
    override fun setBackgroundColor(color: Int) {
        throw RuntimeException("setBackgroundColor not supported!")
    }

    /**
     * Since our underlying technique of achieving curved edges is based on setting the background drawable resource to a gradient drawable,
     * we simply can not set another background during runtime.
     */
    override fun setBackgroundResource(resid: Int) {
        throw RuntimeException("setBackgroundResource not supported in ShadowView")
    }

}