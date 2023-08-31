package com.ashu.mftracker.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ashu.mftracker.databinding.FragmentHomeBinding
import com.ashu.mftracker.global.network.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(),
    androidx.appcompat.widget.SearchView.OnQueryTextListener {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel by viewModels<HomeViewModel>()

    lateinit var homeRecyclerAdapter: HomeRecyclerAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        viewModel.retrieveMutualFunds()

        binding.svMutualFund.setOnQueryTextListener(this)

        binding.rvMutualFunds.layoutManager = LinearLayoutManager(requireContext())
        homeRecyclerAdapter = HomeRecyclerAdapter()
        binding.rvMutualFunds.adapter = homeRecyclerAdapter
        viewModel.fetchMutualFunds.observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
//                    binding.rvMutualFunds.visibility = View.VISIBLE
                    homeRecyclerAdapter.loadData(it.data)
                }

                Status.ERROR -> {
//                    binding.rvMutualFunds.visibility = View.GONE
                }

                else -> {
//                    binding.rvMutualFunds.visibility = View.GONE
                }
            }
        }

        homeRecyclerAdapter.onItemClick = {
            viewModel.fetchMutualFundNav(it.schemeCode)
        }

        viewModel.fetchMutualFundNav.observe(this) {
            when(it.status) {
                Status.SUCCESS -> {
                    Log.d("navData", it.data.toString())
                }
                else -> {

                }
            }
        }

        return root
    }

    override fun onQueryTextSubmit(p0: String?): Boolean {
        homeRecyclerAdapter.filter.filter(p0)
        return false
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        homeRecyclerAdapter.filter.filter(p0)
        return false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}