package com.coding.investapp.presentation.portfolio_list


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coding.investapp.data.remote.dto.toPortfolio
import com.coding.investapp.domain.repository.RoomInvestRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    private val repository: RoomInvestRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(PortfolioListState())
    val state: Flow<PortfolioListState> = _state


    init {
        getPortfolios()
    }

    private fun getPortfolios() {
        viewModelScope.launch {
            repository.getPortfolios().collectLatest { data ->
                println(" room data $data")
                val portfolios = data.map {

                    it.toPortfolio() }
                _state.value =
                    PortfolioListState(portfolios = portfolios)
            }
        }
    }

}
