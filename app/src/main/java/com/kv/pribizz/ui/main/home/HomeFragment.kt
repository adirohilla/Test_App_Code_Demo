package com.kv.pribizz.ui.main.home

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import com.kv.pribizz.R
import com.kv.pribizz.databinding.HomeFragmentBinding
import com.kv.pribizz.model.UserModel
import com.kv.pribizz.utils.NetworkResult
import com.kv.pribizz.utils.Utils
import com.kv.pribizz.utils.Utils.showSnackBar
import com.kv.pribizz.utils.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), View.OnClickListener {

    private val viewModel by viewModels<HomeViewModel>()
    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!
    var showEditProfile = false;
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListener()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getProfile()
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.ll_kyc -> {
//                val wallet = HomeFragmentDirections.homeFragmentToProfileFragment()
//                navigationTo(wallet)
            }
            R.id.ll_plan -> {
//                val wallet = HomeFragmentDirections.homeFragmentToPlanFragment()
//                navigationTo(wallet)
            }
            R.id.llinvestment -> {
//                val wallet = HomeFragmentDirections.homeFragmentToInvestmentHistoryFragment()
//                navigationTo(wallet)
            }
            R.id.ll_earning_history -> {
//                val earning = HomeFragmentDirections.homeFragmentToEarningFragment()
//                navigationTo(earning)
            }
            R.id.ll_my_network -> {
//                val network = HomeFragmentDirections.homeFragmentToNetworkFragment()
//                navigationTo(network)
            }
        }
    }

    private fun initListener() {
        binding.tvMarquee.isSelected = true
        binding.llinvestment.setOnClickListener(this)
        binding.llEarningHistory.setOnClickListener(this)
        binding.llMyNetwork.setOnClickListener(this)
        binding.llKyc.setOnClickListener(this)
        binding.llPlan.setOnClickListener(this)

        viewModel.response_profile.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {

                    response.data?.let {
                        binding.progressCircular.visibility = View.GONE
                        if (it.error) {
                            showSnackBar(
                                response.data.message
                            )
                        } else {
                            viewModel.updateuserLocal(it.data)
                            showEditProfileIfPending(it.data)
                        }
                    }
                }

                is NetworkResult.Error -> {
                    binding.progressCircular.visibility = View.GONE
                    response.message?.let {
                        Utils.showSnackBar1(
                            requireActivity(),
                            response.message
                        )
                    }
                }
                is NetworkResult.Loading -> {
                    hideKeyboard(requireActivity())
                    binding.progressCircular.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun showEditProfileIfPending(data: UserModel) {
        if ((TextUtils.isEmpty(data.pan_number) || TextUtils.isEmpty(data.account_no) || TextUtils.isEmpty(
                data.upi_id
            ))
            && !showEditProfile
            && !data.current_plan.isNullOrEmpty()
        ) {
//            val profile = HomeFragmentDirections.homeFragmentToProfileFragment()
//            navigationTo(profile)
            showSnackBar("Enter your bank details or upi to get first payout")
            showEditProfile = true;
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    fun navigationTo(navDirections: NavDirections) {
        view?.findNavController()?.navigate(navDirections)
    }
}