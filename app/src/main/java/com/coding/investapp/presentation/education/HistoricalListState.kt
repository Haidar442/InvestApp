package com.coding.investapp.presentation.education

import com.coding.investapp.domain.model.Historical

data class HistoricalListState(
    val isLoading: Boolean = false,
    val historicals: List<Historical> = emptyList(),
    val error: String = ""
)
