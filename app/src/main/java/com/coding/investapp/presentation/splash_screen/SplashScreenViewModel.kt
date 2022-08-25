package com.coding.investapp.presentation.splash_screen


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coding.investapp.data.remote.dto.toHistorical
import com.coding.investapp.data.remote.dto.toOption
import com.coding.investapp.data.remote.dto.toPortfolio
import com.coding.investapp.domain.repository.InvestRepository
import com.coding.investapp.domain.repository.RoomInvestRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor
    (
    private val investRepository: InvestRepository,
    private val roomInvestRepository: RoomInvestRepository

) : ViewModel() {
    private val _state = MutableStateFlow(SplashState())
    val state: StateFlow<SplashState> = _state

    init {
        getRemoteData()
    }

    private fun getRemoteData() {
        viewModelScope.launch {
            try {

                val arePortfoliosInserted = async {
                    investRepository.getPortfoliosList().map {
                        roomInvestRepository.insertPortfolio(it)
                        it.toPortfolio()
                    }
                    true
                }
                val areOptionsInserted = async {
                    investRepository.getOptionsList().map {
                        roomInvestRepository.insertOption(it)
                        it.toOption()
                    }
                    true
                }
                val areHistoricalInserted = async {
                    investRepository.getHistoricalsList().map {
                        roomInvestRepository.insertHistorical(it)
                        it.toHistorical()
                    }
                    true
                }

                mutableListOf(arePortfoliosInserted,
                    areOptionsInserted,
                    areHistoricalInserted).awaitAll()

                _state.value = SplashState(isSuccess = true)

            } catch (e: HttpException) {
                _state.value =
                    SplashState(errorMessage = e.localizedMessage ?: "An unexpected error occured")

            } catch (e: IOException) {
                _state.value =
                    SplashState(errorMessage = "Couldn't reach server. Check your internet connection.")

            }

        }
    }

    fun goToListFragment(splashCallBack: () -> Unit) {
        viewModelScope.launch {
            delay(3000)
            splashCallBack()
        }

    }

    data class SplashState(
        val isLoading: Boolean = true,
        val isSuccess: Boolean = false,
        val errorMessage:String = ""
    )

}