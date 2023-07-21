package com.study.studyretrofit

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.study.studyretrofit.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var data = ArrayList<Products>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        getAllProducts()
    }

    private fun getAllProducts() {
        val retrofit = ServiceBuilder.builderService(ServiceInterface::class.java)
        val call: Call<ApiResponse> = retrofit.getAllProducts()

        call.enqueue(object : Callback<ApiResponse>{
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                try {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        data = responseBody.products
                        Log.d("data",data.toString())
                    }


                    val adapter = ProductAdapter(data)
                    binding.recyclerView.adapter=adapter
                }
                catch (ex: java.lang.Exception){
                    ex.printStackTrace()
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Log.e("Failed","Api Failed due to "+t.message)
            }
        })
    }


}