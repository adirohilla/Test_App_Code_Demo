package com.kv.pribizz.ui.splash

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.kv.pribizz.BuildConfig
import com.kv.pribizz.R
import com.kv.pribizz.databinding.SplashActivityBinding
import com.kv.pribizz.ui.login.LoginActivity
import com.kv.pribizz.ui.main.MainActivity
import com.kv.pribizz.utils.CommonIntent
import com.kv.pribizz.utils.NetworkResult
import com.kv.pribizz.utils.Utils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext


@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    lateinit var binding: SplashActivityBinding
    val viewModel by viewModels<SplashViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.splash_activity)
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel

        Utils.fullScreen(window)

        initListenr()
    }

    private fun initListenr() {
        viewModel.response.observe(this) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    response.data?.let {
                        if (!it.error) {
                            if (!BuildConfig.VERSION_NAME.equals(it.data.version)) {
                                showAppUpdateDialog();
                            } else {
                                gotoNextScreen()
                            }
                        }
                    }
                }

                is NetworkResult.Error -> {
                    response.message?.let {
                        Utils.showSnackBar1(
                            this@SplashActivity,
                            response.message
                        )
                    }
                }

                is NetworkResult.Loading -> {

                }
            }
        }
    }

    private fun showAppUpdateDialog() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        val alertDialog: AlertDialog = builder.create()

        alertDialog.setTitle("New Update Available")
        alertDialog.setMessage("Please Update your app")
        alertDialog.setCancelable(false)
        alertDialog.setButton(
            DialogInterface.BUTTON_POSITIVE, "Update",
            { dialog, whichButton ->
                CommonIntent.openBrowser("http://pribizzchoice.com/", this@SplashActivity)
            })
        alertDialog.show()
    }

    fun gotoNextScreen() {
        lifecycleScope.launchWhenStarted {
            viewModel.dataStoreManager.isLogin().collect {
                delay(1000)
                withContext(Dispatchers.Main) {
                    if (it) {
                        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                        finish()
                    } else {
                        startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
                        finish()
                    }
                }
            }
        }

    }
}