package com.example.hometestmobile.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.hometestmobile.R
import com.example.hometestmobile.customView.OnItemClick
import com.example.hometestmobile.models.Cities
import com.example.hometestmobile.models.City
import com.example.hometestmobile.utils.Constant
import com.example.hometestmobile.utils.Util

class CitiesAdapter(private val context: Context) :
    RecyclerView.Adapter<CitiesAdapter.ViewHolder>() {
    var onItemClick: OnItemClick? = null
    var cityList: Cities? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    private fun initData() {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_rcv, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        cityList?.let {
            return it.cities.size
        }
        return 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        cityList?.cities?.get(position)?.let { holder.bind(it, position, onItemClick) }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvNameCity: TextView = itemView.findViewById(R.id.tv_name_city)

        fun bind(city: City, position: Int, listener: OnItemClick?) {
            Util.getPrefStringValue(context, Constant.REF_CITY)?.let {
                if (it.equals(city.name)) {
                    tvNameCity.background =
                        ContextCompat.getDrawable(context, R.drawable.selected_border_item_rcv)
                } else {
                    tvNameCity.background =
                        ContextCompat.getDrawable(context, R.drawable.boder_item_rcv)
                }
            }

            tvNameCity.text = city.name
            itemView.setOnClickListener {
                listener?.onItemClickListener(position)
            }
        }
    }
}