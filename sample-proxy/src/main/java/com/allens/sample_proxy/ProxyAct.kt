package com.allens.sample_proxy

import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy

/**
 * 代理模式
 */
class ProxyAct : BaseActivity() {

    override fun doCreate() {
        addButton("静态代理") {
            doProxyStatically()
        }
        addButton("JDK动态代理") {
            doProxyDynamic()
        }
        addButton("CgLib动态代理") {
            doProxyDynamicCGLib()
        }
    }

    private fun doProxyDynamicCGLib() {

    }

    private fun doProxyDynamic() {
        val proxy = ProxyDynamic(ProxyABuyIPhone()).getProxy() as OnProxyToDoListener
        proxy.onBuyIPhone("红色")
    }

    private fun doProxyStatically() {
        val proxyBuyIPhone = ProxyBuyIPhone()
        ProxyConsole(proxyBuyIPhone).onBuyIPhone("红色")

        val proxyABuyIPhone = ProxyABuyIPhone()
        ProxyConsole(proxyABuyIPhone).onBuyIPhone("黑色")
    }
}


class ProxyDynamic constructor(private val any: Any) : InvocationHandler {

    fun getProxy(): Any {
        return Proxy.newProxyInstance(
            any::class.java.classLoader,
            any::class.java.interfaces,
            this
        );
    }

    override fun invoke(proxy: Any?, method: Method?, args: Array<out Any>?): Any? {
//        println("proxy:$proxy")
        print("method:${method?.name} ")
        args?.forEach {
            print("[$it],")
        }
        println("\n")
        println("执行之前")
        val invoke = method?.invoke(any, *(args ?: emptyArray()))
        println("执行之后")
        return invoke
    }
}


interface OnProxyToDoListener {
    //定义买个手机。定义一个想要买的颜色
    fun onBuyIPhone(color: String)
}


//代理对象 正在负责干事情的人。你的代购朋友
class ProxyBuyIPhone : OnProxyToDoListener {
    override fun onBuyIPhone(color: String) {
        println("购买 $color 颜色手机")
    }
}

class ProxyABuyIPhone : OnProxyToDoListener {
    override fun onBuyIPhone(color: String) {
        println("代购A 购买 $color 颜色手机")
    }
}

//代理的控制对象。
class ProxyConsole constructor(private val listener: OnProxyToDoListener) :
    OnProxyToDoListener {
    override fun onBuyIPhone(color: String) {
        println("把钱给了人家")
        listener.onBuyIPhone(color)
        println("检查手机是否有问题")
    }
}