package com.example.soccerapp.adapter

import android.annotation.SuppressLint
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
import com.example.soccerapp.data.datamodels.Soccer
import com.example.soccerapp.ui.SoccerFragmentDirections

class SoccerAdapter(
    private var dataset: List<Soccer>,
) : RecyclerView.Adapter<SoccerAdapter.ItemViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: List<Soccer>) {
        dataset = list
        notifyDataSetChanged()
    }

    // der Viewholder weiß welche Teile des Layout beim Recycling angepasst werden
    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivImage: ImageView = itemView.findViewById(R.id.iv_image)
        val tvValue: TextView = itemView.findViewById(R.id.tv_value)
        val tvCountry: TextView = itemView.findViewById(R.id.tv_country)
        val tvName: TextView = itemView.findViewById(R.id.tv_name)
        val clItem: ConstraintLayout = itemView.findViewById(R.id.cl_item)
    }

    // hier werden neue ViewHolder erstellt
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        // das itemLayout wird gebaut
        val itemLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_soccer, parent, false)

        // und in einem ViewHolder zurückgegeben
        return ItemViewHolder(itemLayout)
    }

    // hier findet der Recyclingprozess statt
    // die vom ViewHolder bereitgestellten Parameter werden verändert
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        val item = dataset[position]
        val imgUri = item.image.toUri().buildUpon().scheme("https").build()

        holder.ivImage.load(imgUri)

        holder.clItem.setOnClickListener {
            holder.itemView.findNavController()
                .navigate(SoccerFragmentDirections.actionSoccerFragmentToSoccerDetail(position))
        }

        holder.tvName.text = item.name
        holder.tvCountry.text = item.country
        holder.tvValue.text = "${item.value} Millionen €"
    }

    // damit der LayoutManager weiß wie lang die Liste ist
    override fun getItemCount(): Int {
        return dataset.size
    }
}
