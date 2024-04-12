package com.crestinfosystems_jinay.crestcentralsystems.ui.Profile

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.crestinfosystems_jinay.crestcentralsystems.ui.Auth.LoginActivity
import com.crestinfosystems_jinay.crestcentralsystems.CrestCentralSystem
import com.crestinfosystems_jinay.crestcentralsystems.databinding.ActivityUserProfileBinding

class UserProfile : AppCompatActivity() {
    var binding: ActivityUserProfileBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityUserProfileBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding?.root)

        binding?.logout?.setOnClickListener {
            CrestCentralSystem.sharedPreferencesManager.accessToken = null
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }
}