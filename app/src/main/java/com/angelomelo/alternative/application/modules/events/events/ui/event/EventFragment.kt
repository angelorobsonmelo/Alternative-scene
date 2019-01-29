package com.angelomelo.alternative.application.modules.events.events.ui.event

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.OvershootInterpolator
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.angelomelo.alternative.R
import com.angelomelo.alternative.application.domain.Event
import com.angelomelo.alternative.application.domain.filter.EventFilter
import com.angelomelo.alternative.application.modules.events.events.commons.EndlessRecyclerOnScrollListener
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter
import kotlinx.android.synthetic.main.event_fragment.*

class EventFragment : androidx.fragment.app.Fragment() {

    companion object {
        fun newInstance() = EventFragment()
    }

    private lateinit var viewModel: EventViewModel
    var eventsLoaded: MutableList<Event> = ArrayList()
    private lateinit var mAdapter: EventAdapter
    lateinit var mLayoutManager: GridLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.event_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(EventViewModel()::class.java)
        mAdapter = EventAdapter(context!!, this.eventsLoaded)
        getEvents()
        initEventSuccessListener()
        initEventErrorListener()

        configureRecyclerView()
        initScrollListener()
    }

    private fun getEvents(currentPage: Int = 0) {
        viewModel.getEvents(EventFilter(true), currentPage)
    }

    private fun initEventSuccessListener() {
        viewModel.eventsResponse.observe(this, Observer { response ->
            val events = response?.data?.content

            this.eventsLoaded.addAll(events!!)
            this.mAdapter.notifyDataSetChanged()
        })
    }

    private fun initEventErrorListener() {
        viewModel.error.observe(this, Observer { error ->
            Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
        })
    }

    private fun configureRecyclerView() {
        mLayoutManager = GridLayoutManager(context, 2)
        recycler_events.layoutManager = mLayoutManager

        recycler_events.adapter = ScaleInAnimationAdapter(mAdapter).apply {
            setFirstOnly(true)
            setDuration(500)
            setInterpolator(OvershootInterpolator(.5f))
        }
    }

    private fun initScrollListener() {
        recycler_events.addOnScrollListener(object :
            EndlessRecyclerOnScrollListener(mLayoutManager) {
            override fun onLoadMore(currentPage: Int) {
                getEvents(currentPage)
            }
        })
    }

}
