package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import java.math.BigDecimal
import java.math.RoundingMode

class MainActivity : AppCompatActivity() {


    lateinit var tipsEntry: TipsEntry
    var currency = BigDecimal("1")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tipsEntry= TipsEntry(
            BigDecimal("0"),
            BigDecimal("15"),
            BigDecimal("0"),
            BigDecimal("0")
        )
        Cost.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(text: Editable) {}
            override fun beforeTextChanged(text: CharSequence, start: Int,
                                           count: Int, after: Int) {}

            override fun onTextChanged(text: CharSequence, start: Int, before: Int, count: Int) {
                tipsEntry.amount=if(text.isNotEmpty()) BigDecimal(text.toString()) else BigDecimal("0")
                updateText()
            }
        })
        switch1.setOnCheckedChangeListener { buttonView, isChecked ->
            updateText()
        }
        negative.setOnClickListener{
            tipsEntry.percent= BigDecimal("10")
            updateText()
        }
        neutral.setOnClickListener{
            tipsEntry.percent= BigDecimal("15")
            updateText()
        }
        positive.setOnClickListener{
            tipsEntry.percent= BigDecimal("20")
            updateText()
        }
        pay.setOnClickListener {
            val intent = Intent(this,PayPage::class.java)
            intent.putExtra("Data", tipsEntry)
            startActivity(intent)
        }

    }




    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable("Data", tipsEntry)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        if(savedInstanceState.containsKey("Data")) {
            tipsEntry = savedInstanceState.getParcelable("Data")!!
            updateText()
        }
    }

    private fun updateText() {
        tipsEntry.amount=tipsEntry.amount.setScale(2, RoundingMode.CEILING)
        tipsEntry.percent=tipsEntry.percent.setScale(2, RoundingMode.CEILING)
        tipsEntry.tips=(tipsEntry.amount * (tipsEntry.percent / 100.toBigDecimal()))
            .setScale(2, RoundingMode.CEILING)
        if(switch1.isChecked){
            tipsEntry.total = (tipsEntry.amount + tipsEntry.tips).setScale(0, RoundingMode.CEILING)

        }
        else {
            tipsEntry.total = (tipsEntry.amount+ tipsEntry.tips).setScale(2, RoundingMode.CEILING)
        }
        tipView.text = tipsEntry.tips.toString()
        totalView.text = tipsEntry.total.toString()
        current_tip.text = tipsEntry.percent.toString()+"%"
    }


}
