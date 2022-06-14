package com.example.soccerapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.net.toUri
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.soccerapp.R
import com.example.soccerapp.data.datamodels.Result
import com.example.soccerapp.ui.SoccerFragmentDirections

class SoccerAdapter(
    private val dataset: List<Result>,
) : RecyclerView.Adapter<SoccerAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val iv_image: ImageView = itemView.findViewById(R.id.iv_image)
        val tv_value: TextView = itemView.findViewById(R.id.tv_value)
        val tv_country: TextView = itemView.findViewById(R.id.tv_country)
        val tv_name: TextView = itemView.findViewById(R.id.tv_name)
        val cl_item: ConstraintLayout = itemView.findViewById(R.id.cl_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        val itemLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_soccer, parent, false)

        return ItemViewHolder(itemLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        val item = dataset[position]
        val imgUri = item.image.toUri().buildUpon().scheme("https").build()

        holder.iv_image.load(imgUri)

        holder.cl_item.setOnClickListener {
            holder.itemView.findNavController()
                .navigate(SoccerFragmentDirections.actionSoccerFragmentToSoccerDetail(position))
        }

        holder.tv_name.text = item.name
        holder.tv_country.text = item.country
        holder.tv_value.text = "${item.value} Millionen €"
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}
