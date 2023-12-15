package com.kv.pribizz.ui.main

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.google.android.material.navigation.NavigationView
import com.kv.pribizz.R
import com.kv.pribizz.databinding.MainActivityBinding
import com.kv.pribizz.model.BaseResponseModel
import com.kv.pribizz.ui.login.LoginActivity
import com.kv.pribizz.utils.NetworkResult
import com.kv.pribizz.utils.Utils.showSnackBar
import com.kv.pribizz.utils.Utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
    NavController.OnDestinationChangedListener {
    lateinit var binding: MainActivityBinding
    val viewModel by viewModels<MainViewModel>()
    lateinit var navHostFragment: NavHostFragment
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<MainActivityBinding?>(this, R.layout.main_activity)
            .apply {
                lifecycleOwner = this@MainActivity
                viewModel = viewModel
            }
        binding.appBar.viewModel = viewModel
        initView()
    }

    private fun initView() {
        setSupportActionBar(binding.appBar.toolbar)
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment
            ),
            binding.drawerLayout
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)
        binding.appBar.contentMain.bottomNavView.setupWithNavController(navController)
        binding.navView.setNavigationItemSelectedListener(this)
        navController.addOnDestinationChangedListener(this)

        val view = binding.navView.getHeaderView(0)

        val tvName = view.findViewById<TextView>(R.id.tv_name)
        val tvEmail = view.findViewById<TextView>(R.id.tv_email)
        val tvMasterId = view.findViewById<TextView>(R.id.tv_master_id)
        val civProfileImage = view.findViewById<CircleImageView>(R.id.civ_profile_image)
        lifecycleScope.launchWhenStarted {
            viewModel.userModel.collect {
                withContext(Dispatchers.Main) {
                    tvName.text = it?.name
                    tvEmail.text = it?.username
                    tvMasterId.text = "Referral Code : " + it?.referral_code
                    Glide.with(civProfileImage).load(it?.profile_image)
                        .placeholder(R.drawable.ic_user)
                        .error(R.drawable.ic_user).into(civProfileImage)
                }
            }
        }
        managerToolbarItemClick()
    }

    private fun managerToolbarItemClick() {
        binding.appBar.ivBell.setOnClickListener({
//            navController.navigate(R.id.notificationFragment)
        })
        binding.appBar.ivUser.setOnClickListener({
//            navController.navigate(R.id.profileFragment)
        })
    }

    fun setHeaderTitle(title: String) {
        supportActionBar?.title = title
    }

    /**
     * If using the default action bar this must be overridden.
     * This will handle back actions initiated by the the back arrow
     * at the start of the action bar.
     */
    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(
            Navigation.findNavController(this, R.id.nav_host_fragment),
            binding.drawerLayout
        )

//        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return when (item.itemId) {
            R.id.menu_network -> {
//                navController.navigate(R.id.networkFragment)
                return true
            }
            R.id.menu_all_plans -> {
//                navController.navigate(R.id.planFragment)
                return true
            }
            R.id.menu_earning -> {
//                navController.navigate(R.id.earningFragment)
                return true
            }
            R.id.menu_wallet -> {
//                navController.navigate(R.id.investmentHistoryFragment)
                return true
            }
            R.id.menu_shopping -> {
//                navController.navigate(R.id.shoppingFragment)
                return true
            }

            R.id.menu_contact_us -> {
//                navController.navigate(R.id.contactUsFragment)
                return true
            }

            R.id.menu_about_us -> {
//                navController.navigate(R.id.aboutUsFragment)
                return true
            }
            R.id.menu_refer -> {
//                navController.navigate(R.id.referFragment)
                return true
            }
            R.id.menu_logout -> {
                showLogoutConfirmationDialog()
                return true
            }
            else -> {
                false
            }
        }
    }

    private fun requestForLogoutUser() {
        viewModel.response_logout.observe(this) { response ->
            when (response) {
                is NetworkResult.Success -> {

                    response.data?.let {
                        if (!it.error) {
                            onLogoutResponse(it)
                        }
                    }
                    binding.appBar.contentMain.progressCircular.visibility = View.GONE
                }

                is NetworkResult.Error -> {
                    binding.appBar.contentMain.progressCircular.visibility = View.GONE
                    response.message?.let {
                        showSnackBar(
                            response.message
                        )
                    }
                }

                is NetworkResult.Loading -> {
                    binding.appBar.contentMain.progressCircular.visibility = View.VISIBLE
                }
            }
        }
        viewModel.logoutUser()
    }

    fun onLogoutResponse(baseResponseModel: BaseResponseModel) {
        showToast(baseResponseModel.message)
        val intent = Intent(this, LoginActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or
                    Intent.FLAG_ACTIVITY_CLEAR_TASK or
                    Intent.FLAG_ACTIVITY_NEW_TASK
        }
        startActivity(intent)
        finish()
    }

    fun showLogoutConfirmationDialog() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        val alertDialog: AlertDialog = builder.create()

        alertDialog.setTitle(resources.getText(R.string.logout) as String)
        alertDialog.setMessage(
            resources.getText(R.string.are_you_sure_to_logout) as String
        )
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Yes",
            { dialog, whichButton ->
                requestForLogoutUser()
            })

        alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "No",
            { dialog, whichButton -> })
        alertDialog.show()
    }


    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        val destination_id = destination.id
        binding.appBar.ivBell.visibility = View.VISIBLE
        binding.appBar.ivUser.visibility = View.VISIBLE
        if (destination_id == R.id.homeFragment) {
            binding.appBar.contentMain.bottomNavView.visibility = View.GONE
        } else {
            binding.appBar.contentMain.bottomNavView.visibility = View.GONE

            binding.appBar.ivUser.visibility = View.GONE
            binding.appBar.ivBell.visibility = View.GONE
        }
    }
}