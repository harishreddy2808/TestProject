package com.reddy.presentation.fragments

import android.os.Bundle
import android.view.*
import androidx.core.os.bundleOf
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.reddy.R
import com.reddy.databinding.FragmentDriverListBinding
import com.reddy.presentation.viewmodel.DriverUiState
import com.reddy.presentation.viewmodel.DriverViewModel
import com.reddy.presentation.viewmodel.SortOrder
import org.koin.androidx.viewmodel.ext.android.viewModel

class DriverFragment : Fragment(), MenuProvider {

    private lateinit var binding: FragmentDriverListBinding
    private val driverViewModel by viewModel<DriverViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentDriverListBinding.inflate(inflater, container, false)
        setupRecyclerView()
        driverViewModel.uiState.observe(viewLifecycleOwner) { handleUiState(it) }
        driverViewModel.getDrivers(SortOrder.FIRST_NAME)
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
        return binding.root
    }

    private fun setupRecyclerView() {
        val driverAdapter = DriverAdapter { driverId ->
            val bundle = bundleOf("driverId" to driverId.toString())
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)
        }

        binding.rvDriver.apply {
            adapter = driverAdapter
            setHasFixedSize(true)
        }
    }


    private fun handleUiState(state: DriverUiState) {
        when (state) {
            is DriverUiState.Loading -> {
                binding.progressBar.visibility = View.VISIBLE
                binding.tvErrorMessage.visibility = View.GONE
                binding.rvDriver.visibility = View.GONE
            }
            is DriverUiState.Success -> {
                binding.progressBar.visibility = View.GONE
                binding.tvErrorMessage.visibility = View.GONE
                binding.rvDriver.visibility = View.VISIBLE
                (binding.rvDriver.adapter as DriverAdapter).submitList(state.drivers)
            }
            is DriverUiState.Error -> {
                binding.progressBar.visibility = View.GONE
                binding.tvErrorMessage.visibility = View.VISIBLE
                binding.rvDriver.visibility = View.GONE
                binding.tvErrorMessage.text = state.message
            }
        }
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_main, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.sort_by_first_name -> {
                driverViewModel.onSortOrderChanged(SortOrder.FIRST_NAME)
                // handle sort by first name
                true
            }
            R.id.sort_by_last_name -> {
                driverViewModel.onSortOrderChanged(SortOrder.LAST_NAME)
                // handle sort by last name
                true
            }
            else -> false
        }
    }
}
