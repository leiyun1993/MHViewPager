package com.githubly.mhviewpager.widget

import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

/**
 * 类名：MHPagerAdapter
 * 作者：Yun.Lei
 * 功能：
 * 创建日期：2018-09-18 11:16
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

class MHPagerAdapter<T>(val datas: MutableList<T>, val listener: MHViewPageListener<T>) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val imageView = ImageView(container.context)
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        container.addView(imageView, ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))
        listener.onSetData(imageView, datas[position], position)
        return imageView
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View?)
    }

    override fun isViewFromObject(p0: View, p1: Any): Boolean = p0 == p1

    override fun getCount(): Int = datas.size
}