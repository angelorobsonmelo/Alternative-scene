package com.angelomelo.alternative.application.modules.events.events.eventDetail.ui.eventdetail

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.angelomelo.alternative.application.domain.EventDate
import kotlinx.android.synthetic.main.event_date_item.view.*
import java.text.SimpleDateFormat

class EventDateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val date  = itemView.dateTextView
    private val price = itemView.priceTextView

    fun bindData(eventDate: EventDate) {
        val pattern = "dd/MM/yyyy"
        val simpleDateFormat = SimpleDateFormat(pattern)

        val dateFormat = simpleDateFormat.format(eventDate.date)

        println(date)
        date.text  = dateFormat
        price.text = "R$"+ eventDate.price.toString()
    }


}