package com.amespressotest.simple

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_start.*

/** The first shown Activity. */
class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_start)

        continueButton.setOnClickListener {
            gotoNextPage()
        }
    }

    /** Opens the next page. */
    private fun gotoNextPage() {
        startActivity(AskIdentityActivity.createIntent(this))
    }
}