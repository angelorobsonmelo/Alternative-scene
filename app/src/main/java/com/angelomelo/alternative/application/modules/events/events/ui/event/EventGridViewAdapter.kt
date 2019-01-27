package com.angelomelo.alternative.application.modules.events.events.ui.event

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.angelomelo.alternative.R
import com.angelomelo.alternative.application.domain.Event
import kotlinx.android.synthetic.main.event_item.view.*

class EventGridViewAdapter(val mContext: Context, val mEvents: List<Event>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = LayoutInflater.from(mContext).inflate(R.layout.event_item, parent, false)

        val gridItem = view.item_event_text

        gridItem.text = mEvents[position].title

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