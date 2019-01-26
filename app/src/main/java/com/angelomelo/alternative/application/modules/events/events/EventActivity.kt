package com.angelomelo.alternative.application.modules.events.events

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.angelomelo.alternative.R
import com.angelomelo.alternative.application.modules.events.events.ui.event.EventFragment

class EventActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.event_activity)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, EventFragment.newInstance())
                .commitNow()
        }
    }

}
