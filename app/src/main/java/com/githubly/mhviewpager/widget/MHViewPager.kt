package com.githubly.mhviewpager.widget

import android.content.Context
import android.graphics.Bitmap
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.util.Log
import android.widget.ImageView


/**
 * 类名：MHViewPager
 * 作者：Yun.Lei
 * 功能：
 * 创建日期：2018-09-18 10:48
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
class MHViewPager<T> : ViewPager {


    var mFixedDuration: Int = 1000       //默认ViewPager切换速度
        set(value) {
            field = value
            setScrollerSpeed(mFixedDuration)
        }
    private var mAdapter: MHPagerAdapter<T>? = null
    private var defHeight: Int = 0
    private var heightArray: IntArray? = null
    private var mListener: MHViewPageListener<T>? = null

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)


    init {
        if (mFixedDuration > 0) {
            setScrollerSpeed(mFixedDuration)
        }
        addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrollStateChanged(position: Int) {
            }

            override fun onPageScrolled(position: Int, offset: Float, offsetPixels: Int) {
                if (heightArray == null || position == heightArray!!.size - 1 || defHeight == 0) {
                    return
                }
                //计算ViewPager即将变化到的高度
                val sourceHeights1 = if (heightArray!![position] == 0) {
                    defHeight
                } else {
                    heightArray!![position]
                }
                val sourceHeights2 = if (heightArray!![position + 1] == 0) {
                    defHeight
                } else {
                    heightArray!![position + 1]
                }
                val height = (sourceHeights1 * (1 - offset) + sourceHeights2 * offset).toInt()
                Log.e("height", "" + height)
                //为ViewPager设置高度
                layoutParams = layoutParams.apply {
                    this.height = height
                }
            }

            override fun onPageSelected(position: Int) {
                mListener?.onPageSelected(position)
            }

        })
    }

    override fun setOnPageChangeListener(listener: OnPageChangeListener) {
        addOnPageChangeListener(listener)
    }


    fun setData(data: MutableList<T>, listener: MHViewPageListener<T>) {
        if (data.isNotEmpty()) {
            mAdapter = MHPagerAdapter(data, listener)
            adapter = mAdapter
            heightArray = IntArray(data.size)
            mListener = listener
        }
    }

    fun bindSource(loadedImage: Bitmap, position: Int, imageView: ImageView
                   , maxHeight: Int = (context.resources.displayMetrics.heightPixels * 0.75).toInt()) {
        val scale = loadedImage.height.toFloat() / loadedImage.width
        var height = (scale * context.resources.displayMetrics.widthPixels).toInt()
        if (height > maxHeight) {
            height = maxHeight
        }
        setSourceHeights(height, position)
        imageView.setImageBitmap(loadedImage)
    }

    /**
     * 设置加载出的图片的高度参数
     */
    private fun setSourceHeights(height: Int, position: Int) {
        if (height > 0 && (heightArray?.size ?: 0) > 0 && (heightArray?.size ?: 0) > position) {
            heightArray!![position] = height
            if (position == 0 && defHeight == 0) {//初始化默认高度
                defHeight = height
                layoutParams = layoutParams.apply {
                    this.height = height
                }
            }
        }
    }

    /**
     * 重设ViewPager滚动速度
     */
    private fun setScrollerSpeed(fixedDuration: Int) {
        try {
            val mScroller = ViewPager::class.java.getDeclaredField("mScroller")
            mScroller.isAccessible = true
            val scroller = FixedSpeedScroller(context, fixedDuration)
            mScroller.set(this, scroller)
        } catch (e: Exception) {
            e.printStackTrace()

        }
    }
}