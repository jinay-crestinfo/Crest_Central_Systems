package com.crestinfosystems_jinay.crestcentralsystems.ui.Auth

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.crestinfosystems_jinay.crestcentralsystems.R
import com.crestinfosystems_jinay.crestcentralsystems.ui.dashboard.Dashboard
import com.crestinfosystems_jinay.crestcentralsystems.databinding.ActivityLoginBinding
import com.crestinfosystems_jinay.crestcentralsystems.viewModel.LoginViewModel


@Suppress("UNCHECKED_CAST")
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.updateBtn.setOnClickListener {
            viewModel.SignIn {
                val intent = Intent(this, Dashboard::class.java)
                startActivity(intent)
                this.finish()
            }
        }
        binding.textInputLayoutEmailEdit.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                viewModel.email.value = s.toString()
            }
        })
        binding.textInputLayoutPassEdit.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                viewModel.password.value = s.toString()
            }

        })
    }
}