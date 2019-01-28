package com.angelomelo.alternative.application.modules.events.events.commons

import android.widget.AbsListView


abstract class EndlessGridViewOnScrollListener : AbsListView.OnScrollListener {

    private var previousTotal = 0
    private var loading = true
    private val visibleThreshold = 5
    private var lastVisibleItem = 0
    private var lastY = 0
    private var currentPage = 0

    override fun onScrollStateChanged(view: AbsListView, scrollState: Int) {}

    override fun onScroll(view: AbsListView, firstVisibleItem: Int, visibleItemCount: Int, totalItemCount: Int) {
        var top = 0
        if (view.getChildAt(0) != null) {
            top = view.getChildAt(0).top
        }

        if (firstVisibleItem > lastVisibleItem) {

        } else if (firstVisibleItem < lastVisibleItem) {
            //scroll up
        } else {
            if (top < lastY) {
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
            } else if (top > lastY) {
                //scroll up
            }
        }

        lastVisibleItem = firstVisibleItem
        lastY = top
    }

    abstract fun onLoadMore(currentPage: Int)
}