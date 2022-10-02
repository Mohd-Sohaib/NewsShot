package com.mohdsohaib.newsshot

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_layout.view.*

class NewsAdapter(val context: Context, val results: List<Results>) :
    Adapter<NewsAdapter.ResultViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false)
        return ResultViewHolder(view)
    }

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        val result = results[position]
        holder.newsTitle.text = result.title
        holder.newsDescription.text = result.description
        Glide.with(context).load(result.image_url).into(holder.newsImage)
        holder.itemView.setOnClickListener {
            Toast.makeText(context, result.title, Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return results.size
    }

    class ResultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var newsImage = itemView.newsImage
        var newsTitle = itemView.newsTitle
        var newsDescription = itemView.newsDescription
    }
}