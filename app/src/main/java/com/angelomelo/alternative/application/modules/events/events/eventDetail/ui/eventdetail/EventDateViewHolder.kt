package com.angelomelo.alternative.application.modules.events.events.eventDetail.ui.eventdetail

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.angelomelo.alternative.application.domain.EventDate
import kotlinx.android.synthetic.main.event_date_item.view.*
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

class EventDateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val date  = itemView.dateTextView
    private val price = itemView.priceTextView

    fun bindData(eventDate: EventDate) {
        val pattern = "dd/MM/yyyy"
        val simpleDateFormat = SimpleDateFormat(pattern, Locale("PT-br"))

        val dateFormat = simpleDateFormat.format(eventDate.date)

        val format = NumberFormat.getCurrencyInstance(Locale("PT-br"))
        format.currency = Currency.getInstance("BRL")
        format.minimumFractionDigits = 2
        val priceWithCurrency = format.format(eventDate.price)

        date.text  = dateFormat
        price.text = priceWithCurrency
    }


}