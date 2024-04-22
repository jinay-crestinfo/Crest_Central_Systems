package com.crestinfosystems_jinay.crestcentralsystems.ui.HRMS.subscreens

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.crestinfosystems_jinay.crestcentralsystems.R

class hrms_important_contacts : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hrms_important_contacts)
        var popUpBack = findViewById<ImageView?>(R.id.back_pressed_icon)
        popUpBack.setOnClickListener {
            onBackPressed()
        }
    }
}