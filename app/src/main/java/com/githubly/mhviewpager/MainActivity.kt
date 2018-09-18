package com.githubly.mhviewpager

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomViewTarget
import com.bumptech.glide.request.transition.Transition
import com.githubly.mhviewpager.widget.MHViewPageListener
import com.githubly.mhviewpager.widget.MHViewPager

class MainActivity : AppCompatActivity() {


    //http://img.hb.aicdn.com/fa7be73547c402acc9c7a1e8717cba4d87a487c51adaa-4aL4RO_fw658
    //http://img.hb.aicdn.com/78357472c41451bb06ddf8e1bf63f4fe38d054a32b5e4-IuY8EO_fw658
    //http://img.hb.aicdn.com/e8cbd6ac1b44cf290debbf1ebcdfac6bdf20487f46146-uz70dU_fw658
    //http://img.hb.aicdn.com/a9d10755cdf6fef52d529a807810c937dd4c70552bb46-sAwWOc_fw658

    private lateinit var mViewPager: MHViewPager<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val datas = mutableListOf(
                "http://img.hb.aicdn.com/fa7be73547c402acc9c7a1e8717cba4d87a487c51adaa-4aL4RO_fw658",
                "http://img.hb.aicdn.com/78357472c41451bb06ddf8e1bf63f4fe38d054a32b5e4-IuY8EO_fw658",
                "http://img.hb.aicdn.com/e8cbd6ac1b44cf290debbf1ebcdfac6bdf20487f46146-uz70dU_fw658",
                "http://img.hb.aicdn.com/a9d10755cdf6fef52d529a807810c937dd4c70552bb46-sAwWOc_fw658"
        )
        mViewPager = findViewById(R.id.viewPager)
        mViewPager.setData(datas, object : MHViewPageListener<String> {
            override fun onSetData(view: View, data: String, position: Int) {
                Glide.with(this@MainActivity).asBitmap().load(data).into(object : CustomViewTarget<ImageView, Bitmap>(view as ImageView) {
                    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                        mViewPager.bindSource(resource, position, view as ImageView)
                    }

                    override fun onLoadFailed(errorDrawable: Drawable?) {
                    }

                    override fun onResourceCleared(placeholder: Drawable?) {
                    }
                })

            }

            override fun onPageSelected(position: Int) {

            }

        })

    }


}

