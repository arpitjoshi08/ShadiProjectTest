package com.test.sampleapp.extensions

import android.app.Activity
import android.content.Intent
import com.test.sampleapp.presentation.tindercards.TinderAppViewActivity

fun Activity.navigateCardActivity() {
    startActivity(Intent(this, TinderAppViewActivity::class.java))
}
