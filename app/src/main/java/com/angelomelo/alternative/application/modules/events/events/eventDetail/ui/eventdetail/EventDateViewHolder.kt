package com.angelomelo.alternative.application.modules.events.events.eventDetail.ui.eventdetail

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.angelomelo.alternative.application.domain.EventDate
import kotlinx.android.synthetic.main.event_date_item.view.*

class EventDateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val date  = itemView.dateTextView
    private val price = itemView.priceTextView

    fun bindData(eventDate: EventDate) {
        date.text  = eventDate.date.toString()
        price.text = eventDate.price.toString()
    }


}