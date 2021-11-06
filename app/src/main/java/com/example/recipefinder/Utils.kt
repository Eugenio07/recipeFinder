package com.example.recipefinder

import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.Transformation

fun expand(v: View) {
    val a = expandAction(v)
    v.startAnimation(a)
}

private fun expandAction(v: View): Animation {
    v.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    val targtetHeight = v.measuredHeight

    v.layoutParams.height = 0
    v.visibility = View.VISIBLE
    val a = object : Animation() {
        override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
            v.layoutParams.height = if (interpolatedTime == 1f)
                ViewGroup.LayoutParams.WRAP_CONTENT
            else
                (targtetHeight * interpolatedTime).toInt()
            v.requestLayout()
        }

        override fun willChangeBounds(): Boolean {
            return true
        }
    }

    a.duration = (targtetHeight / v.context.resources.displayMetrics.density).toInt().toLong()
    v.startAnimation(a)
    return a
}

fun collapse(v: View) {
    val initialHeight = v.measuredHeight

    val a = object : Animation() {
        override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
            if (interpolatedTime == 1f) {
                v.visibility = View.GONE
            } else {
                v.layoutParams.height = initialHeight - (initialHeight * interpolatedTime).toInt()
                v.requestLayout()
            }
        }

        override fun willChangeBounds(): Boolean {
            return true
        }
    }

    a.duration = (initialHeight / v.context.resources.displayMetrics.density).toInt().toLong()
    v.startAnimation(a)
}

@JvmOverloads
fun toggleArrow(show: Boolean, view: View, delay: Boolean = true) {
    if (show) {
        view.animate().setDuration((if (delay) 200 else 0).toLong()).rotation(180f)
    } else {
        view.animate().setDuration((if (delay) 200 else 0).toLong()).rotation(0f)
    }
}