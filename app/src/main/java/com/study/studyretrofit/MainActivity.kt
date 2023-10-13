package com.study.studyretrofit

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.study.studyretrofit.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private val TAG = "---MainActivity"
    private lateinit var binding: ActivityMainBinding
    var data = ArrayList<Products>()
    private var count = 0


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
                        Log.d(TAG,data.toString())
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

    // Function to handle the "Add" button click
    fun onAddClick(view: View) {
        // Increase the count and update the TextView
        count++
        findViewById<TextView>(R.id.tv_stockCount).text = count.toString()
    }

    // Function to handle the "Minus" button click
    fun onMinusClick(view: View) {
        // Decrease the count (if it's greater than 0) and update the TextView
        if (count > 0) {
            count--
            findViewById<TextView>(R.id.tv_stockCount).text = count.toString()
        }
    }

}