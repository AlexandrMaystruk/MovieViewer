package com.gmail.maystruks08.filmviewer.utils

import android.content.Context
import android.util.AttributeSet
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.gmail.maystruks08.filmviewer.R

class FixedDrawerLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : DrawerLayout(context, attrs) {

    private var isLandscapeMode: Boolean = false

    init {
        val styleTypedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.FixedDrawerLayout, 0, 0)
        isLandscapeMode = styleTypedArray.getBoolean(R.styleable.FixedDrawerLayout_isLandscapeMode, false)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)
        if (isLandscapeMode) {
            openDrawer(GravityCompat.START, false)
            setDrawerLockMode(LOCK_MODE_LOCKED_OPEN)
        } else setDrawerLockMode(LOCK_MODE_UNLOCKED)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val customWidthMeasureSpec = MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.EXACTLY)
        val customHeightMeasureSpec = MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(heightMeasureSpec), MeasureSpec.EXACTLY)
        super.onMeasure(customWidthMeasureSpec, customHeightMeasureSpec)
    }
}