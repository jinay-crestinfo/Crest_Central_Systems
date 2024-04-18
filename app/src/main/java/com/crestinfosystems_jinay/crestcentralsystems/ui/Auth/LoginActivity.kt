package com.crestinfosystems_jinay.crestcentralsystems.ui.Auth

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.crestinfosystems_jinay.crestcentralsystems.R
import com.crestinfosystems_jinay.crestcentralsystems.databinding.ActivityLoginBinding
import com.crestinfosystems_jinay.crestcentralsystems.ui.dashboard.Dashboard
import com.crestinfosystems_jinay.crestcentralsystems.viewModel.LoginViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog


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
            if (viewModel.isCorrect.value!!) {
                viewModel.SignIn(
                    onApiFail = { errormessage ->
                        runOnUiThread {
                            val bottomSheetDialog = BottomSheetDialog(this)
                            bottomSheetDialog.setContentView(R.layout.bottom_sheet_error_view)
                            bottomSheetDialog.findViewById<TextView>(R.id.error_message)?.text =
                                errormessage
                            bottomSheetDialog.findViewById<androidx.cardview.widget.CardView>(R.id.cancel_button)
                                ?.setOnClickListener {
                                    bottomSheetDialog.cancel()
                                    binding.textInputLayoutEmailEdit.setText("")
                                    binding.textInputLayoutPassEdit.setText("")
                                }
                            bottomSheetDialog.show()
                        }

                    },
                    onSuccess = {
                        val intent = Intent(this, Dashboard::class.java)
                        startActivity(intent)
                        this.finish()
                    }
                )
            } else {
                Toast.makeText(this, "Please Enter Correct Value", Toast.LENGTH_SHORT).show()
            }
        }
        onTextEdit()
    }

    fun runOnUiThread(action: () -> Unit) {
        Handler(Looper.getMainLooper()).post(action)
    }

    private fun isValidEmail(mobile: String): Boolean {
        val pattern = Regex("^[a-zA-Z0-9_.+-]+@crestinfosystems\\.com\$")
        return pattern.containsMatchIn(mobile)
    }

    private fun onTextEdit() {
//        binding?.textInputLayoutMobilenumEdit?.setOnFocusChangeListener { v, hasFocus ->
//            binding?.textInputLayoutMobilenum?.isHintEnabled = !hasFocus
//        }
//        binding?.textInputLayoutOrganizationEdit?.setOnFocusChangeListener { v, hasFocus ->
//            binding?.textInputLayoutOrganization?.isHintEnabled = !hasFocus
//        }
        binding.textInputLayoutEmailEdit.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                viewModel.email.value = s.toString()
                if (!isValidEmail(s.toString())) {
                    viewModel.isCorrect.value = false
                    binding.textInputLayoutEmail.error = "Invalid EMAIL"
                } else {
                    viewModel.isCorrect.value = true
                    binding.textInputLayoutEmail.error = null
                }
            }
        })
        binding.textInputLayoutPassEdit.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                viewModel.password.value = s.toString()
                if (s.toString().length > 1) {
                    binding?.textInputLayoutPass?.error = null
                    viewModel.isCorrect.value = true
                } else {
                    binding?.textInputLayoutPass?.error = "Enter Correct Organization Name"
                    viewModel.isCorrect.value = false
                }
            }

        })
    }
}