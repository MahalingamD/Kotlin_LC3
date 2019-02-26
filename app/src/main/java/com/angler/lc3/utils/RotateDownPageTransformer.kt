package com.angler.lc3.utils

import android.support.v4.view.ViewPager
import android.view.View

/*******************************************************************
 * * * * *   * * * *   *     *       Created by OCN.Yang
 * *     *   *         * *   *       Time:2017/12/7 15:37.
 * *     *   *         *   * *       Email address:ocnyang@gmail.com
 * * * * *   * * * *   *     *.Yang  Web site:www.ocnyang.com
 */


class RotateDownPageTransformer : ViewPager.PageTransformer {
    private val mMaxRotate = DEFAULT_MAX_ROTATE

    override fun transformPage(view: View, position: Float) {
        if (position < -1) { // [-Infinity,-1)
            // This page is way off-screen to the left.
            view.rotation = mMaxRotate * -1
            view.pivotX = view.width.toFloat()
            view.pivotY = view.height.toFloat()

        } else if (position <= 1) { // [-1,1]

            if (position < 0)
            //[0，-1]
            {
                view.pivotX = view.width * (0.5f + 0.5f * -position)
                view.pivotY = view.height.toFloat()
                view.rotation = mMaxRotate * position
            } else
            //[1,0]
            {
                view.pivotX = view.width.toFloat() * 0.5f * (1 - position)
                view.pivotY = view.height.toFloat()
                view.rotation = mMaxRotate * position
            }
        } else { // (1,+Infinity]
            // This page is way off-screen to the right.
            view.rotation = mMaxRotate
            view.pivotX = (view.width * 0).toFloat()
            view.pivotY = view.height.toFloat()
        }
    }

    companion object {
        private val DEFAULT_MAX_ROTATE = 15.0f
    }
}
