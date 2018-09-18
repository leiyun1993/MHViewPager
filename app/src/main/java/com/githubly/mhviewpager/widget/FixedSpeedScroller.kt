package com.githubly.mhviewpager.widget

import android.content.Context
import android.widget.Scroller

/**
 * 类名：FixedSpeedScroller
 * 描述：控制ViewPager的滚动速度
 * 创建人：Yun.Lei on 2018/9/18
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
class FixedSpeedScroller(context: Context, private var mDuration: Int = 1000) : Scroller(context) {

    override fun startScroll(startX: Int, startY: Int, dx: Int, dy: Int, duration: Int) {
        // Ignore received duration, use fixed one instead
        super.startScroll(startX, startY, dx, dy, mDuration)
    }

    override fun startScroll(startX: Int, startY: Int, dx: Int, dy: Int) {
        // Ignore received duration, use fixed one instead
        super.startScroll(startX, startY, dx, dy, mDuration)
    }
}