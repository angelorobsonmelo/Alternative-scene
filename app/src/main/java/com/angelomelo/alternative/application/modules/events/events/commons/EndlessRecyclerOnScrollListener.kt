package com.angelomelo.alternative.application.modules.events.events.commons

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

abstract class EndlessRecyclerOnScrollListener(private val mLinearLayoutManager: LinearLayoutManager) :
    RecyclerView.OnScrollListener() {

    private var previousTotal = 0
    private var loading = true
    private val visibleThreshold = 5
    internal var firstVisibleItem: Int = 0
    internal var visibleItemCount: Int = 0
    internal var totalItemCount: Int = 0

    private var currentPage = 1

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        visibleItemCount = recyclerView.childCount
        totalItemCount = mLinearLayoutManager.itemCount
        firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition()

        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false
                previousTotal = totalItemCount
            }
        }

        if (!loading && totalItemCount - visibleItemCount <= firstVisibleItem + visibleThreshold) {

            currentPage++

            onLoadMore(currentPage)

            loading = true
        }
    }

    abstract fun onLoadMore(currentPage: Int)
}