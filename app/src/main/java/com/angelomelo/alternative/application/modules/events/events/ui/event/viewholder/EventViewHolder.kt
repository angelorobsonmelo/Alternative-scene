package com.angelomelo.alternative.application.modules.events.events.ui.event.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import com.angelomelo.alternative.application.domain.Event
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.event_item.view.*

class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val mTitle = itemView.titleEventTextView
    private val mDescription = itemView.dateEventTextView
    private val imageflyer = itemView.folterImageView
    private val mLocality = itemView.eventLocalTextView
    private val mDates = itemView.dateEventTextView

    fun bindData(event: Event) {
        Picasso.get()
            .load(event.photoUrl)
            .resize(imageflyer.width, 100)
            .centerCrop()
            .into(imageflyer)

        mTitle.text = event.title
        mLocality.text = "${event.locality.name} - ${event.locality.city.name}, ${event.locality.city.state.name} "

        mDates.text = event.eventDates.map { it.date }.toString()

//        event.eventDates.forEach {
//            dates = "${it.date}, ${it.hour}"
//        }

//        func getFunctionFormatedToString(functions: [Function]) -> String {
//            let functionsArrayString = functions.map { $0.function! }
//            return functionsArrayString.joined(separator: ", ")
//        }
    }

}
