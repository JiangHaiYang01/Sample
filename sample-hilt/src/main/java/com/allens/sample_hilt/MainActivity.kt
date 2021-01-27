package com.allens.sample_hilt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Inject
import javax.inject.Qualifier

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var truck: Truck

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        truck.deliver()
    }

}

//卡车
class Truck @Inject constructor(private val _driver: Driver) {


    @BindGasEngine
    @Inject
    lateinit var gasEngine: Engine

    @BindElectricEngine
    @Inject
    lateinit var electricEngine: Engine


    fun deliver() {
        gasEngine.start()
        println("${_driver.name} 正在运送货物。")
        gasEngine.shutdown()
    }
}

//司机
class Driver @Inject constructor() {
    val name = "小牛"
}

//引擎
interface Engine {
    //启用引擎
    fun start()

    //关闭引擎
    fun shutdown()
}

//汽油车
class GasEngine @Inject constructor() : Engine {
    override fun start() {
        println("汽油车 启用引擎")
    }

    override fun shutdown() {
        println("汽油车 关闭引擎")
    }
}

//新能源车
class ElectricEngine @Inject constructor() : Engine {
    override fun start() {
        println("新能源车 启用引擎")
    }

    override fun shutdown() {
        println("新能源车 关闭引擎")
    }
}




//@Module 注解，表示这一个用于提供依赖注入实例的模块
@Module
@InstallIn(ActivityComponent::class)
abstract class EngineModule {

    @BindGasEngine
    @Binds
    abstract fun bindEngine(gasEngine: GasEngine): Engine

    @BindElectricEngine
    @Binds
    abstract fun bindElectricEngine(electricEngine: ElectricEngine): Engine

}


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BindGasEngine

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BindElectricEngine











