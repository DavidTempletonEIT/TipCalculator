package com.example.myapplication


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main2.*


class PayPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val incoming =intent.getParcelableExtra<TipsEntry>("Data")
        totalBox.text = incoming.total.toString()
    }
}