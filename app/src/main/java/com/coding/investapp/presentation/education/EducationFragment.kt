package com.coding.investapp.presentation.education

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.coding.investapp.R
import com.coding.investapp.databinding.FragmentEducationBinding
import com.coding.investapp.presentation.SharedViewModel
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EducationFragment : Fragment(R.layout.fragment_education) {


    private val educationViewModel: EducationViewModel by viewModels()
    lateinit var binding: FragmentEducationBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEducationBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val actionbar = (activity as AppCompatActivity).supportActionBar
        actionbar?.setDisplayShowTitleEnabled(true)
        actionbar?.title = "Education"
        actionbar?.setHomeButtonEnabled(true);
        actionbar?.setDisplayHomeAsUpEnabled(true);

        getData()
        getPortfolio(ViewModelProvider(requireActivity())[SharedViewModel::class.java].portfolioId)

    }

    private fun getData() {
        val smartWealthList: ArrayList<Entry> = ArrayList()
        val benchmarkList: ArrayList<Entry> = ArrayList()
        lifecycleScope.launchWhenStarted {

            educationViewModel.state.collect { data ->
//                binding.progressBarMain.visibility = View.GONE
//                optionAdapter.submitData(data.options)
                for (i in data.historicals) {
                    smartWealthList.add(Entry(i.date_js.toFloat(), i.smartWealthValue.toFloat()))
                    benchmarkList.add(Entry(i.date_js.toFloat(), i.benchmarkValue.toFloat()))
                }
                populateLineChart(smartWealthList, benchmarkList)
            }
        }
    }

    private fun getPortfolio(id: String) {
        educationViewModel.getPortfolio(id)
        lifecycleScope.launchWhenStarted {
            educationViewModel.roomPortfolio.collect { portfolio ->
                binding.portfolioIdTxt.text = portfolio.id
                binding.portfolioCreatedatTxt.text = portfolio.created_at
                binding.inestTypeTxt.text = portfolio.investment_type
                binding.riskScoreTxt.text = portfolio.modified_risk_score.toString()

            }
        }
    }


    private fun populateLineChart(dataset1: ArrayList<Entry>, dataset2: ArrayList<Entry>) {

        val lines = ArrayList<LineDataSet>()

        val lDataSet1 = LineDataSet(dataset1, "smartWealthValue")
        lDataSet1.color = Color.GREEN
        lDataSet1.setCircleColor(Color.GREEN)
        lines.add(lDataSet1)
        val lDataSet2 = LineDataSet(dataset2, "benchmarkValue")
        lDataSet1.color = Color.BLACK
        lDataSet1.setCircleColor(Color.BLACK)
        lines.add(lDataSet2)

        binding.ourLineChart.data = LineData(lines as List<ILineDataSet>?)
        binding.ourLineChart.invalidate()
    }



}