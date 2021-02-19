package com.allens.sample_proxy


class MainActivity : BaseActivity() {
    override fun doCreate() {
        intentAct<ProxyAct>("代理模式")
        intentAct<TemplateMethodAct>("模板方法模式")
    }
}