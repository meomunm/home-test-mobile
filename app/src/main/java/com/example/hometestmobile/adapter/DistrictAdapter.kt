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
import com.example.hometestmobile.models.District
import com.example.hometestmobile.utils.Constant
import com.example.hometestmobile.utils.Util

class DistrictAdapter(private val context: Context) : RecyclerView.Adapter<DistrictAdapter.ViewHolder>()  {
    var onItemClick: OnItemClick? = null
    var districtList: List<District>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DistrictAdapter.ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_rcv, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        districtList?.let {
            return it.size
        }
        return 0
    }

    override fun onBindViewHolder(holder: DistrictAdapter.ViewHolder, position: Int) {
        districtList?.get(position)?.let { holder.bind(it, position, onItemClick) }
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private var tvNameCity: TextView = itemView.findViewById(R.id.tv_name_city)

        fun bind(district: District, position: Int, listener: OnItemClick?) {
            Util.getPrefStringValue(context, Constant.REF_DISTRICT)?.let {
                if (it.equals(district.name)) {
                    tvNameCity.background =
                        ContextCompat.getDrawable(context, R.drawable.selected_border_item_rcv)
                } else {
                    tvNameCity.background =
                        ContextCompat.getDrawable(context, R.drawable.boder_item_rcv)
                }
            }
            tvNameCity.text = district.name
            itemView.setOnClickListener {
                listener?.onItemClickListener(position)
            }
        }
    }
}