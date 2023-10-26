package com.hfad.stopwatch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioGroup
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClick(view: View?) {
        val button = view as Button        // Button button = (Button)view;
        val textButton = button.text.toString()
        Snackbar.make(view, textButton, Snackbar.LENGTH_LONG)
            .show()
    }
    fun showIndex(view: View?) {
        val radioButton = view as RadioGroup
        val indexButton = radioButton.checkedRadioButtonId.toString()
        Snackbar.make(view, indexButton, Snackbar.LENGTH_LONG)
            .show()
    }

    fun nextActivity(view: View?) {
        val intent = Intent(this, TimerActivity::class.java)
        startActivity(intent)
    }


}