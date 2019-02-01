package com.angelomelo.alternative.application.modules.events.events.eventDetail.ui.eventdetail

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.angelomelo.alternative.R
import com.angelomelo.alternative.application.domain.EventDate

class EventDateAdapter(private val mContext: Context, private val mEventDates: List<EventDate>) : RecyclerView.Adapter<EventDateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): EventDateViewHolder {
        val context = mContext
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.event_date_item, parent, false)

        return EventDateViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mEventDates.size
    }

    override fun onBindViewHolder(holder: EventDateViewHolder, position: Int) {
        holder.bindData(mEventDates[position])
    }


}