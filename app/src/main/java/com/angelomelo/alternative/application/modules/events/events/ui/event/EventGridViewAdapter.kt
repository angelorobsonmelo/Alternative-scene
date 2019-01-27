package com.angelomelo.alternative.application.modules.events.events.ui.event

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.angelomelo.alternative.R
import com.angelomelo.alternative.application.domain.Event
import com.angelomelo.alternative.application.modules.events.events.ui.event.viewholder.EventViewHolder


class EventGridViewAdapter(val mContext: Context, val mEvents: List<Event>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val viewHolder: EventViewHolder?

        if (convertView == null) {
            val layoutInflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

            convertView = layoutInflater.inflate(R.layout.event_item, parent, false)
            convertView.tag = EventViewHolder(convertView)

            return convertView
        } else {
            viewHolder = convertView.tag as EventViewHolder
        }

        viewHolder.bindData(mEvents[position])

        return convertView
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