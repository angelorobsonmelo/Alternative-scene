package com.angelomelo.alternative.application.modules.events.events.ui.event

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Toast
import com.angelomelo.alternative.R
import com.angelomelo.alternative.application.domain.Event
import com.angelomelo.alternative.application.domain.filter.EventFilter
import kotlinx.android.synthetic.main.event_fragment.*


class EventFragment : Fragment() {

    companion object {
        fun newInstance() = EventFragment()
    }

    private lateinit var viewModel: EventViewModel
    private var totalElements = 0
    private var currentPage   = 0
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
        viewModel.getEvents(EventFilter(true), currentPage)

        grid_events.setOnScrollListener(object : AbsListView.OnScrollListener {
            override fun onScroll(
                view: AbsListView?,
                firstVisibleItem: Int,
                visibleItemCount: Int,
                totalItemCount: Int
            ) {

            }

            override fun onScrollStateChanged(view: AbsListView?, scrollState: Int) {
                if (scrollState == 1) {
                    if (view!!.count == eventsLoaded.size  && eventsLoaded.size != totalElements) {
                        currentPage += 1
                        viewModel.getEvents(EventFilter(true), currentPage)
                        print("Carregando mais contacts")
                    }
                }
            }

        })

        getEventSuccess()
        getEventError()
    }

    private fun getEventSuccess() {
        viewModel.eventsResponse.observe(this, Observer { response ->
            totalElements = response?.data?.totalElements!!
            val events = response.data.content

            if (currentPage == 0) { this.eventsLoaded = ArrayList() }

            this.eventsLoaded.addAll(events!!)

            val adapter = EventGridViewAdapter(context!!, this.eventsLoaded)
            grid_events.adapter = adapter
            adapter.notifyDataSetChanged()
        })
    }

    private fun getEventError() {
        viewModel.error.observe(this, Observer { error ->
            Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
        })
    }

}
