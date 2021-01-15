package com.test.sampleapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.test.sampleapp.extensions.navigateCardActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setOnClicnListener()
    }

    private fun setOnClicnListener() {
        navigateToCardBtn?.setOnClickListener({ v ->
            navigateCardActivity()
        })
    }
}
