package com.coding.investapp.presentation.splash_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.coding.investapp.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job

@AndroidEntryPoint
class SplashFragment : Fragment() {

    private var job: Job? = null
    private val viewModel: SplashScreenViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.goToListFragment {
            findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToPortfolioFragment())
        }
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val actionbar = (activity as AppCompatActivity).supportActionBar
        actionbar?.setDisplayShowTitleEnabled(false)
        actionbar?.setHomeButtonEnabled(false)
        actionbar?.setDisplayHomeAsUpEnabled(false)

        job = lifecycleScope.launchWhenStarted {
            viewModel.state.collect { data ->
                if (data.errorMessage.isNotBlank()) {
                    Toast.makeText(requireContext(), data.errorMessage, Toast.LENGTH_LONG).show()
                } else if (data.isSuccess) {
                    Toast.makeText(requireContext(), "All data inserted successfully", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        job?.cancel()
    }
}
