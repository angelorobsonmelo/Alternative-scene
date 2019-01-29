package com.angelomelo.alternative.application.modules.events.events.ui.event.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.angelomelo.alternative.R
import com.angelomelo.alternative.application.domain.Event
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.event_item.view.*

class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val imageflyer = itemView.folterImageView
    private val mTitle = itemView.titleEventTextView
    private val mLocality = itemView.eventLocalTextView
    private val mDates = itemView.dateEventTextView

    fun bindData(event: Event) {
        Picasso.get()
            .load(event.photoUrl)
            .placeholder(R.drawable.heavy_metal_default)
            .into(imageflyer)

        mDates.text = getFormatedDates(event)

        mTitle.text = event.title
        mLocality.text = "${event.locality.name} - ${event.locality.city.name}, ${event.locality.city.state.name} "
    }

    private fun getFormatedDates(event: Event): String {
        val datesFormated: MutableList<String> = ArrayList()

        event.eventDates.forEach {
            datesFormated.add(it.eventDateAndHourToString)
        }

        return datesFormated.joinToString(" - ")
    }

}
