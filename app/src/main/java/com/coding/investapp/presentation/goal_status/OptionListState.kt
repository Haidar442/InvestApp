package com.coding.investapp.presentation.goal_status

import com.coding.investapp.domain.model.Option

data class OptionListState(
    val isLoading: Boolean = false,
    val options: List<Option> = emptyList(),
    val error: String = ""
)
