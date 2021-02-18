package com.allens.sample_livedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.lifecycle.MutableLiveData
import com.flywith24.wrapperlivedata.*
import com.google.android.material.button.MaterialButton


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val default =  BusLiveData<Int>()

        default.postValue(9999)

//        default.observe(this){
//            println("default生了变化:${it}")
//        }

        default.observeForever {
            println("default生了变化:${it}")
        }


        val linear =  findViewById<LinearLayout>(R.id.linear)
        linear.addView(MaterialButton(this).apply {
            text  = "text"
            setOnClickListener {
                for(index in 1..100){
                    println("发送 $index")
//                    default.postValue(index)
                    default.value = index
                }
            }
        })
    }
}