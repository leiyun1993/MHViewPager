package com.githubly.mhviewpager.widget

import android.view.View

/**
 * 类名：MHViewPageListener
 * 作者：Yun.Lei
 * 功能：
 * 创建日期：2018-09-18 11:43
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
interface MHViewPageListener<T> {

    fun onSetData(view: View, data: T, position: Int)

    fun onPageSelected(position: Int)
}