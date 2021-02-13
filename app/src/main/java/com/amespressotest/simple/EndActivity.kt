package com.amespressotest.simple

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_end.*

/** The Activity at the end. Shows the name transmitted to it. */
class EndActivity : AppCompatActivity() {

    companion object {
        /** The key to transmit the name of the user. */
        private const val KEY_NAME = "name"

        /**
         * Creates the Intent to create this Activity.
         * @param context a Context.
         * @param name the name of the user. It should be trimmed already.
         * @return the Intent.
         */
        fun createIntent(context: Context, name: String): Intent {
            val intent = Intent(context, EndActivity::class.java)
            intent.putExtra(KEY_NAME, name)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_end)

        // Gets the name from the previous Activity.
        val name = requireNotNull(intent.getStringExtra(KEY_NAME)) { "No user name was passed to this Activity, abnormal!" }
        helloName.text = getString(R.string.end_page_name_placeholder, name)
    }
}