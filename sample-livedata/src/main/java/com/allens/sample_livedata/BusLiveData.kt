package com.allens.sample_livedata

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.lang.reflect.Field


class BusLiveData<T> : MutableLiveData<T>() {

    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        super.observe(owner, observer)
        hook(observer = observer)

    }

    override fun observeForever(observer: Observer<in T>) {
        super.observeForever(observer)
        hook(observer = observer)
    }

    private fun hook(observer: Observer<in T>) {
        //get wrapper's version
        val classLiveData = LiveData::class.java
        val fieldObservers: Field = classLiveData.getDeclaredField("mObservers")
        fieldObservers.isAccessible = true
        val objectObservers = fieldObservers.get(this)
        val classObservers: Class<*> = objectObservers.javaClass
        val methodGet = classObservers.getDeclaredMethod("get", Any::class.java)
        methodGet.isAccessible = true
        val objectWrapperEntry = methodGet.invoke(objectObservers, observer)
        var objectWrapper: Any? = null
        if (objectWrapperEntry is Map.Entry<*, *>) {
            objectWrapper = objectWrapperEntry.value
        }
        if (objectWrapper == null) {
            println("Wrapper can not be bull!")
            return
        }
        val classObserverWrapper: Class<*> = objectWrapper.javaClass.superclass
        val fieldLastVersion: Field = classObserverWrapper.getDeclaredField("mLastVersion")
        fieldLastVersion.isAccessible = true
        //get livedata's version
        val fieldVersion: Field = classLiveData.getDeclaredField("mVersion")
        fieldVersion.isAccessible = true
        val objectVersion = fieldVersion.get(this)
        //set wrapper's version
        fieldLastVersion.set(objectWrapper, objectVersion)
    }
}