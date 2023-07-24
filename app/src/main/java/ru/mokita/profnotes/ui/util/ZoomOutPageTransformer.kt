package ru.mokita.profnotes.ui.util

import android.view.View
import androidx.viewpager2.widget.ViewPager2

private const val MIN_SCALE = 0.85f

class ZoomOutPageTransformer : ViewPager2.PageTransformer {

    override fun transformPage(view: View, position: Float) {
        view.apply {
            val pageWidth = width
            val pageHeight = height
            when {
                position < -1 -> {
                    alpha = 0f
                }
                position <= 1 -> {
                    val scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position))
                    val vertMargin = pageHeight * (1 - scaleFactor)
                    val horzMargin = pageWidth * (1 - scaleFactor)
                    translationX = if (position < 0) {
                        horzMargin - vertMargin
                    } else {
                        horzMargin + vertMargin
                    }

                    scaleX = scaleFactor
                    scaleY = scaleFactor

                }
                else -> {
                    alpha = 0f
                }
            }
        }
    }
}