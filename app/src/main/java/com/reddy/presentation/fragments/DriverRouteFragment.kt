package com.reddy.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.reddy.databinding.FragmentDriverRouteBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DriverRouteFragment : Fragment() {

    private lateinit var binding: FragmentDriverRouteBinding
    private val viewModel by viewModel<DriverRouteViewModel>()

    private val driverId by lazy {
        arguments?.getString("driverId") ?: ""
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentDriverRouteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.driverRouteState.observe(viewLifecycleOwner) { route ->
            handleUiState(route)
        }

        // fetch route data for a driver
        viewModel.getRouteForDriver(driverId)
    }

    private fun handleUiState(state: DriverRouteState) {
        when (state) {
            is DriverRouteState.Loading -> {
                binding.progressBar.visibility = View.VISIBLE
                binding.tvErrorMessage.visibility = View.GONE
                binding.textView.visibility = View.GONE
            }
            is DriverRouteState.Success -> {
                binding.progressBar.visibility = View.GONE
                binding.tvErrorMessage.visibility = View.GONE
                binding.textView.visibility = View.VISIBLE
                binding.textView.text =
                    "Route Id=${state.route.id} " +
                            "\n DriverId ${driverId}  " +
                            "\n Route type=${state.route.routeType}  " +
                            "\n Route name=${state.route.name}"
            }
            is DriverRouteState.Error -> {
                binding.progressBar.visibility = View.GONE
                binding.tvErrorMessage.visibility = View.VISIBLE
                binding.textView.visibility = View.GONE
                binding.tvErrorMessage.text = state.message
            }
        }
    }
}

