package com.allens.sample_retrofit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.allens.sample_retrofit.databinding.ActivityMainBinding
import com.google.android.material.button.MaterialButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity(), Callback<Repo> {
    private val viewBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)

        viewBinding.linear.addView(createButton("使用") {
            doRetrofitTest()
        })
    }

    private fun doRetrofitTest() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(ApiService::class.java)
        val repos = service.listRepos("octocat")
//        repos.execute()
        repos.enqueue(object : Callback<Repo> {
            override fun onResponse(call: Call<Repo>, response: Response<Repo>) {
            }

            override fun onFailure(call: Call<Repo>, t: Throwable) {
            }
        })




    }

    private fun createButton(title: String, onClick: () -> Unit): MaterialButton {
        return MaterialButton(this).apply {
            text = title
            setOnClickListener {
                onClick()
            }
        }
    }

    override fun onResponse(call: Call<Repo>, response: Response<Repo>) {
        TODO("Not yet implemented")
    }

    override fun onFailure(call: Call<Repo>, t: Throwable) {
        TODO("Not yet implemented")
    }
}

