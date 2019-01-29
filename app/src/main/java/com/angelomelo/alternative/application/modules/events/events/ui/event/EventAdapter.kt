package com.angelomelo.alternative.application.modules.events.events.ui.event

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.angelomelo.alternative.R
import com.angelomelo.alternative.application.domain.Event
import com.angelomelo.alternative.application.modules.events.events.ui.event.viewholder.EventViewHolder


class EventAdapter(private val mContext: Context, private val mEvents: List<Event>) : RecyclerView.Adapter<EventViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): EventViewHolder {
        val context = mContext
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.event_item, parent, false)

        return EventViewHolder(view)
    }

    override fun getItemCount(): Int {
       return mEvents.size
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
         holder.bindData(mEvents[position])
    }


}