package com.angelomelo.alternative.application.modules.events.events.ui.event

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.Toast
import com.angelomelo.alternative.R
import com.angelomelo.alternative.application.domain.filter.EventFilter
import kotlinx.android.synthetic.main.event_fragment.*

class EventFragment : Fragment() {

    companion object {
        fun newInstance() = EventFragment()
    }

    private lateinit var viewModel: EventViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.event_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(EventViewModel()::class.java)
        viewModel.getEvents(EventFilter(true), 0)

        getEventSuccess()
        getEventError()
    }

    private fun getEventSuccess() {
        viewModel.eventsResponse.observe(this, Observer { response ->
            val events = response?.data?.content

            val adapter = EventGridViewAdapter(context!!, events!!)

            grid_events.adapter = adapter
        })
    }

    private fun getEventError() {
        viewModel.error.observe(this, Observer { error ->
            Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
        })
    }

}
