package com.allens.sample_avrcp

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothProfile
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    val TAG = "allens-avrcp"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        BluetoothAdapter.getDefaultAdapter()
            .getProfileProxy(this, object : BluetoothProfile.ServiceListener {
                override fun onServiceConnected(profile: Int, proxy: BluetoothProfile) {
                    Log.i(TAG,"onServiceConnected proxy:$proxy")


                }

                override fun onServiceDisconnected(profile: Int) {
                    Log.i(TAG,"onServiceDisconnected profile:$profile")
                }
            }, 12)//BluetoothProfile.AVRCP_CONTROLLER
    }
}