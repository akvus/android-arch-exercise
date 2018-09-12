package com.waysnpaths.carspoc.ui.main.fragment.carslist

import android.annotation.SuppressLint
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.waysnpaths.carspoc.R
import com.waysnpaths.carspoc.ui.main.model.VehicleVendor
import io.reactivex.subjects.PublishSubject

// todo could use DiffUtil instead for better performance and compatibility with LiveData
class CarsAdapter(private val list: MutableList<VehicleVendor> = mutableListOf()) : RecyclerView.Adapter<CarsAdapter.ViewHolder>() {
    val cardClickListener: PublishSubject<VehicleVendor> = PublishSubject.create()

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.rv_cars_item, parent, false))
    }

    override fun getItemCount() = list.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val vehicleVendor = list[position]
        vehicleVendor.vehicle.vehicle?.vehMakeModel?.name?.let {
            holder.tvName.text = it
            // todo what if an unexpected null
        }
        vehicleVendor.vendor.name?.let {
            holder.tvVendor.text = it
            // todo what if an unexpected null
        }
        holder.tvPrice.text = "${vehicleVendor.vehicle.totalCharge?.estimatedTotalAmount} ${vehicleVendor.vehicle.totalCharge?.currencyCode}"

        holder.card.setOnClickListener {
            cardClickListener.onNext(list[holder.adapterPosition])
        }
    }

    class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val ivPhoto: ImageView = item.findViewById(R.id.ivPhoto)
        val tvName: TextView = item.findViewById(R.id.tvName)
        val tvVendor: TextView = item.findViewById(R.id.tvVendor)
        val tvPrice: TextView = item.findViewById(R.id.tvPrice)
        val card: CardView = item.findViewById(R.id.card)
    }

    fun setList(newList: List<VehicleVendor>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }
}