package com.angelomelo.alternative.application.modules.events.events.eventDetail.ui.eventdetail

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.angelomelo.alternative.R
import kotlinx.android.synthetic.main.toolbar.*
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.event_detail_fragment.*



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

        titleTextView.text = arguments?.get("title") as String
    }

}
