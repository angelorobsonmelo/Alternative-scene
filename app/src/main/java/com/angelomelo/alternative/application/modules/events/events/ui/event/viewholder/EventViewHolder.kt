package com.angelomelo.alternative.application.modules.events.events.ui.event.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import com.angelomelo.alternative.application.domain.Event

class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

//    private  val mText = itemView.item_event_text

    fun bindData(event: Event) {
//        mText.text = event.title
    }

}
