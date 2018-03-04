package com.example.ronanvico.listview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {
    class HomeFeed(val data: List<Data>)
    class Data(val id: String,val name: String,val pwd: String)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listview = findViewById<ListView>(R.id.main_listview)
        fetchJson()

    }

    fun fetchJson()  {
        val url = "https://s3-sa-east-1.amazonaws.com/pontotel-docs/data.json"
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()


        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call?, response: Response?) {
                val body = response?.body()?.string()?.replace("\n","")?.replace(" ","")
                val gson = GsonBuilder().create()
                val homeFeed =  gson.fromJson(body ,HomeFeed::class.java)
                runOnUiThread {
                    main_listview.adapter = MyCustomAdapter(homeFeed)
                }
            }

            override fun onFailure(call: Call?, e: IOException?) {
                println("FUCK")//To change body of created functions use File | Settings | File Templates.
            }
        })

    }

}


