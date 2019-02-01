package com.angelomelo.alternative.application.modules.events.events.eventDetail.ui.eventdetail

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.angelomelo.alternative.R
import kotlinx.android.synthetic.main.toolbar.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.angelomelo.alternative.application.domain.Event
import com.angelomelo.alternative.application.domain.EventDate
import kotlinx.android.synthetic.main.event_detail_fragment.*
import kotlinx.android.synthetic.main.event_fragment.*


class EventDetailFragment : Fragment() {

    companion object {
        fun newInstance() = EventDetailFragment()
    }

    private lateinit var viewModel: EventDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.event_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(EventDetailViewModel::class.java)

        val idEvent = arguments?.get("eventId") as Long

        viewModel.findOne(idEvent)


        initObservables()
    }

    private fun initObservables() {
        viewModel.eventsResponse.observe(this, Observer { response ->
            val eventDetail = response?.data

            titleTextView.text    = eventDetail?.title
            localityTextView.text = eventDetail?.locality?.name
            cityTextView.text     = "${eventDetail?.locality?.city?.name}, ${eventDetail?.locality?.city?.state?.name}"
            dateFormattedTextView.text = getFormatedDates(eventDetail?.eventDates)
        })
    }

    private fun getFormatedDates(eventDates: List<EventDate>?): String {
        val datesFormated: MutableList<String> = ArrayList()

        eventDates?.forEach {
            datesFormated.add(it.eventDateAndHourToString)
        }

        return datesFormated.joinToString(" - ")
    }

}
