package com.coding.investapp.presentation.goal_status

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.coding.investapp.R
import com.coding.investapp.databinding.FragmentGoalStatusBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class GoalStatusFragment : Fragment(R.layout.fragment_goal_status) {


    private val goalStatusViewModel: GoalStatusViewModel by viewModels()
    private lateinit var optionAdapter: OptionAdapter
    lateinit var binding: FragmentGoalStatusBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGoalStatusBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val actionbar = (activity as AppCompatActivity).supportActionBar
        actionbar?.setDisplayShowTitleEnabled(true)
        actionbar?.title = "Goal Status"
        actionbar?.setHomeButtonEnabled(true);
        actionbar?.setDisplayHomeAsUpEnabled(true);

        setupRecyclerView()

        getData()
        optionAdapter.onItemClick = {
//            val bundle = Bundle()
//            bundle.putParcelable("githubRepo", it)
            binding.continueButton.isEnabled = true
        }
        binding.continueButton.setOnClickListener {
            findNavController().navigate(GoalStatusFragmentDirections.actionGoalStatusFragmentToEducationFragment())

        }
//        binding.topAppBar.setNavigationOnClickListener {
//            findNavController().navigate(GoalStatusFragmentDirections.actionGoalStatusFragmentToPortfolioFragment())
//        }
    }

    private fun getData() {
        lifecycleScope.launchWhenStarted {

            goalStatusViewModel.state.collect { data ->
                binding.progressBarMain.visibility = View.GONE
                optionAdapter.submitData(data.options)

            }
        }
    }

    private fun setupRecyclerView() = binding.optionsrecyclerView.apply {
        val radius = resources.getDimensionPixelSize(R.dimen.radius)
        val dotsHeight = resources.getDimensionPixelSize(R.dimen.dots_height)
        val color = ContextCompat.getColor(context!!, R.color.primary)
        optionAdapter = OptionAdapter()
        adapter = optionAdapter
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        addItemDecoration(DotsIndicatorDecoration(radius, radius * 2, dotsHeight, color, color))
        PagerSnapHelper().attachToRecyclerView(this)
    }

}