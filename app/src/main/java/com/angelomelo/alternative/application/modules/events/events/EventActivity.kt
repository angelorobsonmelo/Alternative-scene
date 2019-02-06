package com.angelomelo.alternative.application.modules.events.events

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.angelomelo.alternative.R
import com.angelomelo.alternative.application.modules.events.events.ui.event.EventFragment


class EventActivity : AppCompatActivity() {

    companion object {
        val eventId = "eventId"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.event_activity)

        if (savedInstanceState == null) {
            val fragment = EventFragment.newInstance()

            supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commitNow()
        }
    }

}
