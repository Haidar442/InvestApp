package com.coding.investapp.presentation.education

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coding.investapp.data.remote.dto.PortfolioDto
import com.coding.investapp.data.remote.dto.toHistorical
import com.coding.investapp.data.remote.dto.toPortfolio
import com.coding.investapp.domain.model.Portfolio
import com.coding.investapp.domain.repository.RoomInvestRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EducationViewModel @Inject constructor(
    private val repository: RoomInvestRepository,

    ) : ViewModel() {
    private val _state = MutableStateFlow(HistoricalListState())
    val state: Flow<HistoricalListState> = _state
    private val _roomPortfolio = MutableStateFlow(Portfolio())
    val roomPortfolio: Flow<Portfolio> = _roomPortfolio
    init {
        getHistoricals()
    }

    private fun getHistoricals() {
        viewModelScope.launch {
            repository.getHistoricals().collectLatest { data ->
                println(" room data $data")
                val historicals = data.map {

                    it.toHistorical()
                }
                _state.value =
                    HistoricalListState(historicals = historicals)
            }
        }
    }

     fun getPortfolio(portfolioId :String ){

        viewModelScope.launch {
          val portfolio = repository.getPortfolioById(portfolioId)?.toPortfolio()
            if (portfolio != null) {
                _roomPortfolio.value = portfolio
            }
//                .collectLatest { data ->
//                println(" room data $data")
//                val historicals = data.map {
//
//                    it.toHistorical()
//                }
//                _state.value =
//                    HistoricalListState(historicals = historicals)
//            }
        }
    }
}