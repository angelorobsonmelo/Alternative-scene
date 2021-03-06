package com.angelomelo.alternative.application.modules.events.events.ui.event

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.OvershootInterpolator
import android.widget.AdapterView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.angelomelo.alternative.R
import com.angelomelo.alternative.application.domain.Event
import com.angelomelo.alternative.application.domain.filter.EventFilter
import com.angelomelo.alternative.application.modules.events.events.commons.EndlessRecyclerOnScrollListener
import com.angelomelo.alternative.application.modules.events.events.commons.RecyclerItemClickListener
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter
import kotlinx.android.synthetic.main.event_fragment.*
import android.content.Intent
import com.angelomelo.alternative.application.modules.events.events.EventActivity
import com.angelomelo.alternative.application.modules.events.events.eventDetail.EventDetailActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.event_activity.*
import com.angelomelo.alternative.application.modules.commons.BottomNavigationBehavior
import androidx.coordinatorlayout.widget.CoordinatorLayout



class EventFragment : androidx.fragment.app.Fragment() {

    companion object {
        fun newInstance() = EventFragment()
    }

    private lateinit var viewModel: EventViewModel
    var mEventsLoaded: MutableList<Event> = ArrayList()
    private lateinit var mAdapter: EventAdapter
    lateinit var mLayoutManager: GridLayoutManager
    private var mBottomNavigationView: BottomNavigationView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(com.angelomelo.alternative.R.layout.event_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(EventViewModel()::class.java)
        mAdapter = EventAdapter(context!!, this.mEventsLoaded)
        mBottomNavigationView = activity?.main_nav
        getEvents()
        initEventSuccessListener()
        initEventErrorListener()

        configureRecyclerView()
        initScrollListener()
        initRecyclerItemClickListener()
        configureNavMain()
    }

    private fun initRecyclerItemClickListener() {
        recycler_events.addOnItemTouchListener(
            RecyclerItemClickListener(
                context!!,
                recycler_events,
                object : RecyclerItemClickListener.OnItemClickListener {
                    override fun onItemClick(view: View, position: Int) {
                        val intent = Intent(context, EventDetailActivity::class.java)
                        val event = mEventsLoaded[position]

                        intent.putExtra(EventActivity.eventId, event.id)
                        startActivity(intent)
                    }

                    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                    }

                    override fun onLongItemClick(view: View?, position: Int) {

                    }

                }
            )
        )
    }

    private fun getEvents(currentPage: Int = 0) {
        viewModel.getEvents(EventFilter(true), currentPage)
    }

    private fun initEventSuccessListener() {
        viewModel.success.observe(this, Observer { response ->
            val events = response?.data?.content

            this.mEventsLoaded.addAll(events!!)
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
            setFirstOnly(false)
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


    private fun configureNavMain() {
        val layoutParams = mBottomNavigationView?.layoutParams as CoordinatorLayout.LayoutParams
        layoutParams.behavior = BottomNavigationBehavior()

        mBottomNavigationView?.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
//                    replaceFragment(HomeFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.nav_notification -> {
//                    replaceFragment(EventFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.nav_person -> {
//                    replaceFragment(PersonFragment())
                    return@setOnNavigationItemSelectedListener true
                }
            }
            return@setOnNavigationItemSelectedListener false
        }
    }


}

