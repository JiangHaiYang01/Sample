package com.allens.sample_kotin

fun main() {
    View().setOnClickListener(::onClick)
}

class View{
    fun setOnClickListener(action:(View)->Unit){}
}
fun onClick(view: View){
    println("fuck")
}