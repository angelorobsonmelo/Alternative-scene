package com.angelomelo.alternative.application.modules.events.events.ui.event

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.angelomelo.alternative.R
import com.angelomelo.alternative.application.domain.Event
import com.angelomelo.alternative.application.domain.filter.EventFilter
import com.angelomelo.alternative.application.modules.events.events.commons.EndlessRecyclerViewWithGridLayoutMenagerOnScrollListener
import kotlinx.android.synthetic.main.event_fragment.*


class EventFragment : Fragment() {

    companion object {
        fun newInstance() = EventFragment()
    }

    private lateinit var viewModel: EventViewModel
    private var eventsLoaded: MutableList<Event> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.event_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(EventViewModel()::class.java)
        val gridLayoutManager =  GridLayoutManager(context, 2)
        recycler_events.layoutManager =  gridLayoutManager
        getEvents()
        getEventSuccess()
        getEventError()

        recycler_events.addOnScrollListener(object : EndlessRecyclerViewWithGridLayoutMenagerOnScrollListener(gridLayoutManager) {
            override fun onLoadMore(currentPage: Int) {
                getEvents(currentPage)
            }

        })
    }

    private fun getEvents(currentPage: Int = 0) {
        viewModel.getEvents(EventFilter(true), currentPage)

    }

    private fun getEventSuccess() {
        viewModel.eventsResponse.observe(this, Observer { response ->
            val events = response?.data?.content

            this.eventsLoaded.addAll(events!!)

            val adapter                   = EventAdapter(context!!, this.eventsLoaded)
            recycler_events.adapter       = adapter
            adapter.notifyDataSetChanged()
        })
    }

    private fun getEventError() {
        viewModel.error.observe(this, Observer { error ->
            Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
        })
    }

}
