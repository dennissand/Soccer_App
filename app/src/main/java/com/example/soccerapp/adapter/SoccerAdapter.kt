package com.example.soccerapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.soccerapp.R
import com.example.soccerapp.data.datamodels.Result

class SoccerAdapter(
    private val dataset: List<Result>,
) : RecyclerView.Adapter<SoccerAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val iv_image: ImageView = itemView.findViewById(R.id.iv_image)
        val tv_value: TextView = itemView.findViewById(R.id.tv_value)
        val tv_country: TextView = itemView.findViewById(R.id.tv_country)
        val tv_name: TextView = itemView.findViewById(R.id.tv_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        val itemLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_soccer, parent, false)

        return ItemViewHolder(itemLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        val item = dataset[position]
        val imgUri = item.image.toUri().buildUpon().scheme("https").build()

        holder.iv_image.load(imgUri) {
            transformations(RoundedCornersTransformation(20f))
        }
        //holder.iv_image.id = item.image.length
        holder.tv_name.text = item.name
        holder.tv_country.text = item.country
        holder.tv_value.text = "${item.value} Millionen €"
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}
