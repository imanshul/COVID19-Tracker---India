package com.atechgeek.covid19tracker_india.views.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.atechgeek.covid19tracker_india.R
import com.atechgeek.covid19tracker_india.models.StateData
import kotlinx.android.synthetic.main.item_state_data.view.*
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by Anshul Thakur on 21/3/20.
 */

class StateListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val stateWiseList = ArrayList<StateData>()

    fun addData(list: List<StateData>) {
        stateWiseList.clear()
        stateWiseList.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return StateWiseListViewHolder(
            inflater.inflate(
                R.layout.item_state_data,
                parent,
                false
            )
        )

    }

    override fun getItemCount() = stateWiseList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as StateWiseListViewHolder).bind(stateWiseList.get(position))
    }


    class StateWiseListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(stateData: StateData) {
            itemView.apply {
                tvStateName.text = stateData.name
                tvConfiremedCount.text = stateData.confirmedCases.toString()
                tvRecoveredCount.text = stateData.recoveredCases.toString()
                tvtDeathCount.text = stateData.deaths.toString()
                tvActiveCount.text = stateData.activeCases.toString()
            }
        }
    }


}