package com.kv.pribizz.ui.signup

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.gson.Gson
import com.kv.pribizz.R
import com.kv.pribizz.databinding.SignupActivityBinding
import com.kv.pribizz.utils.NetworkResult
import com.kv.pribizz.utils.Utils
import com.kv.pribizz.utils.Utils.showSnackBar1
import com.kv.pribizz.utils.Utils.showToast1
import com.kv.pribizz.utils.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SignupActivity : AppCompatActivity() {

    lateinit var binding: SignupActivityBinding
    val viewModel by viewModels<SignupViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.signup_activity)
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel

        Utils.fullScreen(window)
        Utils.showPasswordEditText(this, binding.tietPassword)
        Utils.showPasswordEditText(this, binding.tietPassword1)

//        lifecycleScope.launch {
//            // repeatOnLifecycle launches the block in a new coroutine every time the
//            // lifecycle is in the STARTED state (or above) and cancels it when it's STOPPED.
//            repeatOnLifecycle(Lifecycle.State.STARTED) {
//                // Trigger the flow and start listening for values.
//                // Note that this happens when lifecycle is STARTED and stops
//                // collecting when the lifecycle is STOPPED
//                viewModel.error_msg.collectLatest { msg ->
//                    // New value received
//                    showSnackBar(this@SignupActivity, msg)
//                }
//            }
//        }
        viewModel.response.observe(this) { response ->
            when (response) {
                is NetworkResult.Success -> {

                    response.data?.let {
                        showToast1(this@SignupActivity, response.data.message.toString())
                        if (!it.error) {
                            finish()
                        }
                    }
                    binding.progressCircular.visibility = View.GONE
                }

                is NetworkResult.Error -> {
                    binding.progressCircular.visibility = View.GONE
                    response.message?.let { showSnackBar1(this@SignupActivity, response.message) }
                }

                is NetworkResult.Loading -> {
                    hideKeyboard(this@SignupActivity)
                    binding.progressCircular.visibility = View.VISIBLE
                }
            }
        }
    }

    fun onClickLogin(view: View) {
        finish()
    }
}