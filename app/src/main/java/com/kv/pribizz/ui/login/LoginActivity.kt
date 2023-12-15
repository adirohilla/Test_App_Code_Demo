package com.kv.pribizz.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.kv.pribizz.R
import com.kv.pribizz.databinding.LoginActivityBinding
import com.kv.pribizz.model.UserModel
import com.kv.pribizz.ui.main.MainActivity
import com.kv.pribizz.ui.signup.SignupActivity
import com.kv.pribizz.utils.Constants
import com.kv.pribizz.utils.NetworkResult
import com.kv.pribizz.utils.Utils
import com.kv.pribizz.utils.Utils.showToast
import com.kv.pribizz.utils.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    lateinit var binding: LoginActivityBinding
    val viewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.login_activity)
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel

        Utils.fullScreen(window)
        Utils.showPasswordEditText(this, binding.tietPassword)

        initListener()
        if (intent.getBooleanExtra(Constants.UNAUTHORIZED, false)) {
            showToast("Unauthorized")
        }
    }

    private fun initListener() {

        viewModel.response.observe(this) { response ->
            when (response) {
                is NetworkResult.Success -> {

                    response.data?.let {
                        Utils.showToast1(this@LoginActivity, response.data.message.toString())
                        if (!it.error) {
                            onLoginSuccess(it.data)
                        }
                    }
                    binding.progressCircular.visibility = View.GONE
                }

                is NetworkResult.Error -> {
                    binding.progressCircular.visibility = View.GONE
                    response.message?.let {
                        Utils.showSnackBar1(
                            this@LoginActivity,
                            response.message
                        )
                    }
                }

                is NetworkResult.Loading -> {
                    hideKeyboard(this@LoginActivity)
                    binding.progressCircular.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun onLoginSuccess(data: UserModel) {
        viewModel.userLogin(data)
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    fun onClickSignup(view: View) {
        startActivity(Intent(this, SignupActivity::class.java))
    }
}