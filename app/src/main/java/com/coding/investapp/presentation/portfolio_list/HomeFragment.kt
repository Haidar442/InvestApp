package com.coding.investapp.presentation.portfolio_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.coding.investapp.R
import com.coding.investapp.databinding.FragmentPortfolioListBinding
import com.coding.investapp.presentation.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_portfolio_list) {


    private val homeFragmentViewModel: HomeFragmentViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by activityViewModels()
    //    private lateinit var githubRepoAdapter: GithubRepoAdapter
    private lateinit var portfolioAdapter: PortfolioAdapter
    lateinit var binding: FragmentPortfolioListBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPortfolioListBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val actionbar = (activity as AppCompatActivity).supportActionBar

        actionbar?.setDisplayShowTitleEnabled(true)
        actionbar?.title = "Home"
        actionbar?.setHomeButtonEnabled(false);
        actionbar?.setDisplayHomeAsUpEnabled(false);
        setupRecyclerView()

        getData()
        portfolioAdapter.onItemClick = {
            sharedViewModel.portfolioId = it.id
            findNavController().navigate(HomeFragmentDirections.actionPortfolioFragmentToGoalStatusFragment())
        }
    }

    private fun getData() {
        lifecycleScope.launchWhenStarted {

            homeFragmentViewModel.state.collect { data ->
                binding.progressBarMain.visibility = View.GONE
                portfolioAdapter.submitData(data.portfolios)
                portfolioAdapter.notifyDataSetChanged()

            }
        }
    }


    private fun setupRecyclerView() = binding.rvGithubRepoList.apply {
        portfolioAdapter = PortfolioAdapter()
        adapter = portfolioAdapter
        layoutManager = LinearLayoutManager(context)
    }
}