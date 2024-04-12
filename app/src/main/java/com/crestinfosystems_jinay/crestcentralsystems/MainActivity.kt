package com.crestinfosystems_jinay.crestcentralsystems

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.crestinfosystems_jinay.crestcentralsystems.ui.Auth.LoginActivity
import com.crestinfosystems_jinay.crestcentralsystems.ui.dashboard.Dashboard


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val sh = getSharedPreferences("MySharedPref", MODE_PRIVATE)
        println(CrestCentralSystem.sharedPreferencesManager.accessToken)
        val token = sh.getString("auth_token", "")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Handler(Looper.getMainLooper()).postDelayed(
            Runnable {
                if (CrestCentralSystem.sharedPreferencesManager.accessToken == null) {
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    this.finish()
                } else {
                    val intent = Intent(this, Dashboard::class.java)
                    startActivity(intent)
                    this.finish()
                }
            },
            2000
        )
    }
}