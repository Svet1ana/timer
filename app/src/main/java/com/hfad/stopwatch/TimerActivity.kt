package com.hfad.stopwatch

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.Button
import android.widget.Chronometer

class TimerActivity : AppCompatActivity() {

    lateinit var stopwatch: Chronometer //хронометр
    var running = false // Хронометр работает?
    var offset: Long = 0 // Базовое смещение

    val OFFSET_KEY = "offset"
    val RUNNIING_KEY = "running"
    val BASE_KEY = "base"

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)

        // получение ссылки на секундомер
        stopwatch = findViewById<Chronometer>(R.id.stopwatch)

        // Восстановление предыдущего состояния
        if (savedInstanceState != null) {
            offset = savedInstanceState.getLong(OFFSET_KEY)
            running = savedInstanceState.getBoolean(RUNNIING_KEY)
            if (running) {
                stopwatch.base = savedInstanceState.getLong(BASE_KEY)
                stopwatch.start()
            } else setBaseTime()
        }

        //Кнопка start запускает секундомер, если он не работает
        val startButton = findViewById<Button>(R.id.star_button)
        startButton.setOnClickListener() {
            if (!running) {
                setBaseTime()
                stopwatch.start()
                running = true
            }
        }

        //Кнопка pause останавливает секундомер, если он не работает
        val pauseButton = findViewById<Button>(R.id.pause_button)
        pauseButton.setOnClickListener() {
            if (running) {
                saveOffset()
                stopwatch.stop()
                running = false
            }
        }

        // Кнопка reset обвноляет offset и базовое время
        val resetButton = findViewById<Button>(R.id.reset_button)
        resetButton.setOnClickListener() {
            offset = 0
            setBaseTime()
        }
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        savedInstanceState.putLong(OFFSET_KEY, offset)
        savedInstanceState.putBoolean(RUNNIING_KEY, running)
        savedInstanceState.putLong(BASE_KEY, stopwatch.base)
        super.onSaveInstanceState(savedInstanceState)
    }

    //Обновляет время stopwatch.base
    fun setBaseTime() {
        stopwatch.base = SystemClock.elapsedRealtime() - offset
    }

    // Сохраняет offset
    fun saveOffset() {
        offset = SystemClock.elapsedRealtime() - stopwatch.base
    }

    fun nextActivity(view: View?) {
        val intent = Intent(this, ThirdActivity::class.java)
        startActivity(intent)
    }
    fun previousActivity(view: View?) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}