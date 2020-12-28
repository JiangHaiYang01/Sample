package com.allens.sample_anim_frame

import android.annotation.SuppressLint
import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import com.allens.sample_anim_frame.databinding.ActivityMainBinding
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    private var animationDrawable: AnimationDrawable? = null

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val image = AppCompatImageView(this)
        binding.linear.addView(image)



        binding.linear.addView(MaterialButton(this).apply {
            text = "xml方式"
            setOnClickListener {


                // 方式1 使用xml
                //找到 帧动画的资源
                animationDrawable =
                    ContextCompat.getDrawable(context, R.drawable.anim_frame) as AnimationDrawable
                //设置image 的 background
                image.background = animationDrawable
                //也可直接在 xml 中 设置 ImageView background
            }
        })


        binding.linear.addView(MaterialButton(this).apply {
            text = "动态添加"
            setOnClickListener {


                animationDrawable = AnimationDrawable()

                //添加图片信息
                for (index in 0..2) {
                    val resId = resources.getIdentifier("farm_$index", "drawable", packageName)
                    animationDrawable?.addFrame(resources.getDrawable(resId, null), 100)
                }
                //设置循环播放
                animationDrawable?.isOneShot = false
                //将AnimationDrawable 设置给imageView
                image.setImageDrawable(animationDrawable)
            }
        })

        binding.linear.addView(MaterialButton(this).apply {
            text = "开始"
            setOnClickListener {
                //开始播放帧动画
                animationDrawable?.start()
            }
        })

        binding.linear.addView(MaterialButton(this).apply {
            text = "结束"
            setOnClickListener {
                //开始播放帧动画
                animationDrawable?.stop()
            }
        })


    }
}