package com.angelomelo.alternative.application.modules.events.events.eventDetail.ui.eventdetail

import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.angelomelo.alternative.R
import com.angelomelo.alternative.application.domain.Event
import com.angelomelo.alternative.application.domain.EventDate
import com.angelomelo.alternative.application.modules.events.events.EventActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.event_detail_fragment.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.android.synthetic.main.toolbar.view.*


class EventDetailFragment : Fragment() {

    companion object {
        fun newInstance() = EventDetailFragment()
    }

    private lateinit var viewModel: EventDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.event_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(EventDetailViewModel::class.java)
        setHasOptionsMenu(true)
        val toolbar = toolbarDetail as Toolbar
        setSupportActionBar(toolbar)

        val idEvent = arguments?.get(EventActivity.eventId) as Long

        viewModel.findOne(idEvent)

        initObservables()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater!!.inflate(R.menu.menu_toolbar, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val itemMenuId            = item!!.itemId
        val favorite = R.id.favorite
        val share = R.id.share

        when (itemMenuId) {
            favorite -> {
                Toast.makeText(context, "Favorite pressed", Toast.LENGTH_LONG).show()
            }
            share -> {
                Toast.makeText(context, "Share pressed", Toast.LENGTH_LONG).show()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun setSupportActionBar(toolbar: Toolbar) {
        val appCompatActivity = activity as AppCompatActivity?

        appCompatActivity?.setSupportActionBar(toolbar)
        appCompatActivity?.supportActionBar?.setDisplayShowTitleEnabled(false)
        appCompatActivity?.supportActionBar?.setDisplayHomeAsUpEnabled(true)

        toolbar.toolbar_title.text = getString(R.string.event_detail_title)
    }

    private fun initObservables() {
        viewModel.eventsResponse.observe(this, Observer { response ->
            val eventDetail = response?.data

            configureView(eventDetail)
            configureRecyclerView(eventDetail)
        })
    }

    private fun configureView(eventDetail: Event?) {
        titleTextView.text         = eventDetail?.title
        localityTextView.text      = eventDetail?.locality?.name
        cityTextView.text          = getString(R.string.city_and_state, eventDetail?.locality?.city?.name, eventDetail?.locality?.city?.state?.name)
        dateFormattedTextView.text = getFormattedDateToString(eventDetail?.eventDates)
        descriptionTextView.text   = eventDetail?.description
        promoterTextView.text      = eventDetail?.userApp?.name
        localityNameTextView.text  = eventDetail?.locality?.name
        cityNameTextView.text      = getString(R.string.city_and_state, eventDetail?.locality?.city?.name, eventDetail?.locality?.city?.state?.name
        )

        Picasso.get()
            .load(eventDetail?.photoUrl)
            .placeholder(R.drawable.heavy_metal_default)
            .into(flyerImage)
    }

    private fun configureRecyclerView(eventDetail: Event?) {
        val adapter = EventDateAdapter(context!!, eventDetail?.eventDates!!)
        event_date_recycler_view.isNestedScrollingEnabled = false
        event_date_recycler_view.addItemDecoration(DividerItemDecoration(context, LinearLayout.VERTICAL))
        event_date_recycler_view.adapter = adapter
        event_date_recycler_view.layoutManager = LinearLayoutManager(context)
    }

    private fun getFormattedDateToString(eventDates: List<EventDate>?): String {
        val formattedDate: MutableList<String> = ArrayList()

        eventDates?.forEach {
            formattedDate.add(it.eventDateAndHourToString)
        }

        return formattedDate.joinToString(" - ")
    }

}
