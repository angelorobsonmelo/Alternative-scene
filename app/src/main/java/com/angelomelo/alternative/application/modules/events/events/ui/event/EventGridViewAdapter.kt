package com.angelomelo.alternative.application.modules.events.events.ui.event

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.angelomelo.alternative.R
import com.angelomelo.alternative.application.domain.Event
import com.angelomelo.alternative.application.modules.events.events.ui.event.viewholder.EventViewHolder


class EventGridViewAdapter(private val mContext: Context, private val mEvents: List<Event>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = LayoutInflater.from(mContext).inflate(R.layout.event_item, parent, false)

        val holder = EventViewHolder(view)
        holder.bindData(mEvents[position])

        return view
    }

    override fun getItem(position: Int): Any {
      return mEvents[position]
    }

    override fun getItemId(position: Int): Long {
       return mEvents[position].id
    }

    override fun getCount(): Int {
       return mEvents.size
    }


}