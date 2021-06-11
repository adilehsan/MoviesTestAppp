package com.ideologer.moviestestappp.utils

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.ideologer.moviestestappp.R

class ProgressDialog(context: Context) : AlertDialog(context) {
    private val messageTextView: TextView

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_progress_dialogue, null)
        messageTextView = view.findViewById(R.id.message)
        setView(view)
    }

    override fun setMessage(message: CharSequence?) {
        this.messageTextView.text = message.toString()
    }

}