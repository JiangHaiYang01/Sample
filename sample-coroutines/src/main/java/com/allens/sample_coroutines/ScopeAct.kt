package com.allens.sample_coroutines

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.*

class ScopeAct : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        println("todo->onCreate")
        val linear = findViewById<LinearLayout>(R.id.linear)
        linear.addView(MaterialButton(this).apply {
            text = "测试"
            setOnClickListener {
                lifecycleScope.launch {
                    var i = 0
                    while (i < 10) {
                        i++
                        Thread.sleep(100)
                        println("index $i isActive $isActive" )
                        if(!isActive){
                            break
                        }
                        delay(100)
//
//                        withContext(Dispatchers.Main) {
//                            println("index in main $i")
//                        }
                        if (i == 3) {
                            cancel()
                        }
                    }
                }
            }
        })
    }

    private fun doWork() {
        println("todo->do work")
    }

//    override fun onResume() {
//        super.onResume()
//        print("->onResume")
//    }
//
//    override fun onStart() {
//        super.onStart()
//        print("->onStart")
//    }

}

fun main() {
    Test().todo("a")
    Test().todo(age = 0)
}

class Test(){

}
fun Test.todo(name:String?=null,age:Int?=0){

}

fun Test.todo(age:Int?=0){

}