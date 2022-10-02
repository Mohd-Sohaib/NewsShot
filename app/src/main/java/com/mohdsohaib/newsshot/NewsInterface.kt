package com.mohdsohaib.newsshot

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://newsdata.io/"
const val API_KEY = "pub_118579d0952a950940cb516dda331486b41ef"

interface NewsInterface {

    @GET("api/1/news?apikey=$API_KEY")
    fun getHeadline(@Query("country") country: String, @Query("page") page: Int): Call<News>
}

//Retrofit singleton Object
object NewsService {
    val newsInstence: NewsInterface

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        newsInstence = retrofit.create(NewsInterface::class.java)
    }
}

