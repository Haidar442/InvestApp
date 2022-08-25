package com.coding.investapp.presentation.goal_status

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coding.investapp.data.remote.dto.toOption
import com.coding.investapp.domain.repository.RoomInvestRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GoalStatusViewModel @Inject constructor(
    private val repository: RoomInvestRepository,

    ) : ViewModel() {
    private val _state = MutableStateFlow(OptionListState())
    val state: Flow<OptionListState> = _state


    init {
        getOptions()
    }

    private fun getOptions() {
        viewModelScope.launch {
            repository.getOptions().collectLatest { data ->
                println(" room data $data")
                val options = data.map {

                    it.toOption()
                }
                _state.value =
                    OptionListState(options = options)
            }
        }
    }
}