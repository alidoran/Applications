package com.alidoran.mvvm_hilt_room_retro_test.tools

import android.view.Gravity.CENTER
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.RelativeLayout
import androidx.core.view.iterator
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable.INFINITE
import com.alidoran.mvvm_hilt_room_retro_test.R

class ShowWait(
    private val viewGroup: ViewGroup
) {
    private fun addCustomWait(): Int {
        val relativeLayout =
            RelativeLayout(viewGroup.context)
        val relativeParams =
            RelativeLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
        val lottieParams =
            RelativeLayout.LayoutParams(600, 600)
        relativeLayout.setBackgroundColor(
            viewGroup.context.getColor(R.color.wait_transparent)
        )
        relativeLayout.gravity = CENTER
        val lottieLoading = LottieAnimationView(viewGroup.context)
        lottieLoading.setAnimation("lottie/movie-theatre.json")
        lottieLoading.repeatCount = INFINITE
        lottieLoading.speed = 1f
        lottieLoading.playAnimation()
        relativeLayout.addView(lottieLoading, lottieParams)
        val viewId = View.generateViewId()
        viewGroup.addView(relativeLayout, relativeParams)
        relativeLayout.id = viewId
        relativeLayout.isClickable = true
        return viewId
    }

    private fun removeCustomWait(
        waitViewId: Int
    ) {
        for (view in viewGroup) {
            if (view.id == waitViewId)
                viewGroup.removeView(view)
        }
    }

    fun addLiveCustomWait(
        lifecycleOwner: LifecycleOwner,
        liveData: LiveData<Boolean>) {
        var viewId = 0
        liveData.observe(lifecycleOwner) {
            if (it) {
                removeCustomWait(viewId)
                viewId = addCustomWait()
            } else {
                removeCustomWait(viewId)
            }
        }
    }
}