package com.coding.investapp.presentation.portfolio_list

import com.coding.investapp.domain.model.Portfolio

data class PortfolioListState(
    val isLoading: Boolean = false,
    val portfolios: List<Portfolio> = emptyList(),
    val error: String = ""
)
