package com.mohdsohaib.newsshot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.Adapter
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    val TAG = "JAMIA"
    lateinit var adapter: NewsAdapter
    private var results = mutableListOf<Results>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapter = NewsAdapter(this@MainActivity, results)
        newsList.adapter = adapter
        newsList.layoutManager = LinearLayoutManager(this@MainActivity)
        getNews()
    }

    private fun getNews() {
        val news = NewsService.newsInstence.getHeadline("in", 1)
        news.enqueue(object : Callback<News> {
            override fun onResponse(call: Call<News>, response: Response<News>) {
                val news = response.body()
                if (news != null) {
                    Log.d(TAG, news.toString())
                    results.addAll(news.results)
                    adapter.notifyDataSetChanged()
                }
            }
            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d(TAG, "Error in Fetching News", t)
            }
        })
    }
}