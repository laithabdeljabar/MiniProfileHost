package com.example.miniprofile

import android.app.Activity
import android.os.Bundle
import android.widget.TextView

class MiniProfileActivity : Activity() {
    companion object {
        const val EXTRA_TOKEN = "extra_token"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val token = intent.getStringExtra(EXTRA_TOKEN) ?: "-"
        val textView = TextView(this).apply {
            text = "Mini Profile SDK — Token: $token"
            textSize = 20f
        }
        setContentView(textView)
    }
}
