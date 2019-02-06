package com.angelomelo.alternative.application.modules.events.events.eventDetail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.angelomelo.alternative.R
import com.angelomelo.alternative.application.modules.events.events.eventDetail.ui.eventdetail.EventDetailFragment

class EventDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.event_detail_activity)

        if (savedInstanceState == null) {
            val fragment = EventDetailFragment.newInstance()
            fragment.arguments = intent.extras
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commitNow()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
